package com.n.in.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.n.in.model.Execution;
import com.n.in.model.Step;
import com.n.in.model.StepExecution;
import com.n.in.model.Workflow;
import com.n.in.model.repository.ExecutionRepository;
import com.n.in.model.repository.StepExecutionRepository;
import com.n.in.model.repository.WorkflowRepository;
import com.n.in.provider.gemini.response.GeminiResponse;
import com.n.in.strategy.IAClientFactory;
import com.n.in.strategy.IAClientStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class WorkflowExecutionService {

    private final WorkflowRepository workflowRepository;
    private final ExecutionRepository executionRepository;
    private final StepExecutionRepository stepExecutionRepository;

    private final IAClientFactory clientFactory;

    private final InternalOperationService internalOperationService;

    @Transactional
    public Execution executeWorkflow(Long workflowId) {

        Workflow workflow = workflowRepository.findById(workflowId)
                .orElseThrow(() -> new RuntimeException("Workflow no encontrado"));

        Execution execution = new Execution();
        execution.setWorkflow(workflow);
        execution.setStatus("RUNNING");
        execution = executionRepository.save(execution);

        String previousOutput = null;

        for (Step step : workflow.getSteps().stream()
                .sorted(Comparator.comparing(Step::getOrderIndex))
                .toList()) {

            StepExecution stepExec = new StepExecution();
            stepExec.setExecution(execution);
            stepExec.setStep(step);
            stepExec.setStatus("RUNNING");
            stepExec = stepExecutionRepository.save(stepExec);

            try {
                // PASAR EL OUTPUT PREVIO AL STEP
                String output = runStep(stepExec.getExecution().getId(),step, previousOutput);

                stepExec.setOutput(output);
                stepExec.setStatus("DONE");

                previousOutput = output; // <---- guardar para el siguiente step

            } catch (Exception e) {

                stepExec.setOutput("ERROR: " + e.getMessage());
                stepExec.setStatus("ERROR");

                previousOutput = null; // reset en caso de error (o lo mantenés según tu caso)
            }

            stepExecutionRepository.save(stepExec);
        }

        execution.setStatus("COMPLETED");
        return executionRepository.save(execution);
    }

    private String runStep(Long execution, Step step, String previousOutput) throws JsonProcessingException {

        // si el step necesita el output anterior, podemos reemplazar un token como {{prev}}
        String finalPrompt = step.getPrompt();

        if (previousOutput != null && finalPrompt != null) {
            finalPrompt = finalPrompt.replace("{{previous_output}}", previousOutput);
        }

        // CASO 1 → INTERNAL
        if ("internal".equalsIgnoreCase(step.getOperationType())) {
            return internalOperationService.handleInternal(execution, step, previousOutput).toString();
        }

        // CASO 2 → STRATEGIES
        if (step.getAgent() == null) {
            throw new IllegalArgumentException("El step requiere un agent para operación: " + step.getOperationType());
        }

        // reemplazo de prompt temporal
        Step temp = new Step();
        temp.setPrompt(finalPrompt);
        temp.setAgent(step.getAgent());
        temp.setOperationType(step.getOperationType());

        IAClientStrategy strategy = clientFactory.getStrategy(step.getAgent());
        Object result = strategy.generate(temp);
        if("GEMINI".equalsIgnoreCase(step.getAgent().getProvider())) {
            GeminiResponse response = (GeminiResponse) result;
            result = response.getCandidates().get(0).getContent().getParts().get(0).getText();
        }
        return result != null ? result.toString() : "";
    }
}