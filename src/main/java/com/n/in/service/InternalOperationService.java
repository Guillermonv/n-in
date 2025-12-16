package com.n.in.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.n.in.model.Step;
import com.n.in.model.dto.NDto;
import com.n.in.model.mapper.NMapper;
import com.n.in.model.repository.NRepository;
import com.n.in.provider.gemini.response.GeminiResponse;
import com.n.in.utils.NParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
public class InternalOperationService {
        @Autowired
        private NRepository nRepository;

        @Autowired
        private NMapper nMapper;

        public Object handleInternal(Long execution, Step step, String previousOutput) throws JsonProcessingException {
            NDto nDto = new NDto();

            NParser.parse(previousOutput,nDto );
            nDto.setExecutionId(execution);
           // nDto.setCreated(LocalDateTime.now());
           // nDto.setLastUpdated(LocalDateTime.now());
           // nDto.setSubCategory(step.getAgent().getProvider());
           // nDto.setCategory(step.getOperationType());
            nRepository.save(nMapper.toEntity(nDto));

            return Map.of(
                    "step_prompt", step.getPrompt(),
                    "previous_output", previousOutput,
                    "combined", step.getPrompt() + " | " + previousOutput,
                    "result","Stored"
            );
        }
}


