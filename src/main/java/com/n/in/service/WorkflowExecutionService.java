package com.n.in.service;

import com.n.in.model.Execution;
import com.n.in.model.Step;
import com.n.in.model.StepExecution;
import com.n.in.model.Workflow;
import com.n.in.model.repository.ExecutionRepository;
import com.n.in.model.repository.StepExecutionRepository;
import com.n.in.model.repository.WorkflowRepository;
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
  //  private final InternalOperationHandler internalHandler;

    @Transactional
    public Execution executeWorkflow(Long workflowId) {

        Workflow workflow = workflowRepository.findById(workflowId)
                .orElseThrow(() -> new RuntimeException("Workflow no encontrado"));

        // 1) CREAR LA EJECUCIÓN
        Execution execution = new Execution();
        execution.setWorkflow(workflow);
        execution.setStatus("RUNNING");
        execution = executionRepository.save(execution);

        // 2) EJECUTAR STEP POR STEP
        for (Step step : workflow.getSteps().stream()
                .sorted(Comparator.comparing(Step::getOrderIndex))
                .toList()) {

            StepExecution stepExec = new StepExecution();
            stepExec.setExecutions(execution);
            stepExec.setStep(step);
            stepExec.setStatus("RUNNING");
            stepExec = stepExecutionRepository.save(stepExec);

            try {
                // ejecutar paso y obtener output
                String output = runStep(step);

               stepExec.setOutput(output);
                stepExec.setStatus("DONE");
            } catch (Exception e) {
                stepExec.setOutput("ERROR: " + e.getMessage());
                stepExec.setStatus("ERROR");
            }

            stepExecutionRepository.save(stepExec);
        }

        // 3) FINALIZAR EJECUCIÓN
        execution.setStatus("COMPLETED");
        return executionRepository.save(execution);
    }

    private String runStep(Step step) {

        // CASO 1 → INTERNAL
       /* if ("internal".equalsIgnoreCase(step.getOperationType())) {
            Object res = internalHandler.handleInternal(step);
            return res != null ? res.toString() : "";
        }*/

        // CASO 2 → STRATEGIES (GEMINI / GROQ)
        if (step.getAgent() == null) {
            throw new IllegalArgumentException("El step requiere un agent para operación: " + step.getOperationType());
        }

        IAClientStrategy strategy = clientFactory.getStrategy(step.getAgent());
        Object result = strategy.generate(step);

        return result != null ? result.toString() : "";
    }
}
