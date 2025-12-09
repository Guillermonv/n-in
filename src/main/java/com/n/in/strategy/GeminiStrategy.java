package com.n.in.strategy;


import com.n.in.model.AgentEntity;
import com.n.in.model.NDto;
import com.n.in.model.repository.AgentRepository;
import com.n.in.provider.gemini.client.GeminiClient;
import com.n.in.provider.gemini.request.Content;
import com.n.in.provider.gemini.request.GeminiRequest;
import com.n.in.provider.gemini.request.Part;
import com.n.in.utils.NParser;
import com.n.in.utils.enums.ProviderEnum;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GeminiStrategy implements IAClientStrategy {

    @Autowired
    private GeminiClient geminiClient;

    @Autowired
    private GroqStrategy groqStrategy;

    @Autowired
    private AgentRepository agentRepository;

    @Override
    public NDto generate() {
        try {
            return callGemini();
        } catch (Exception e) {
            System.out.println( e.getMessage());
            System.out.println("Gemini falló → usando fallback Groq");
            e.getMessage();
            return groqStrategy.generate();
        }
    }

    private NDto callGemini() {

        GeminiRequest req = new GeminiRequest();
        Content c = new Content();
        Part p = new Part();
        Optional<AgentEntity> agent = agentRepository.findById(Long.valueOf(ProviderEnum.GEMINI.getId()));
        if(agent.isPresent()){
            p.setText(agent.get().getPrompt());
        }
        c.setParts(List.of(p));
        req.setContents(List.of(c));
        var res = geminiClient.sendPrompt(req,agent.get().getSecret());
        System.out.println(res);
        NDto nDto = NDto.builder()
                .type("IA")
                .subType(ProviderEnum.GEMINI.getName())
                .status("initiated")
                .lastUpdated(LocalDateTime.now())
                .created(LocalDateTime.now())
                .build();
        NParser.parse(res.getCandidates().get(0).getContent().getParts().get(0).getText(),nDto);
        return nDto;
    }
}