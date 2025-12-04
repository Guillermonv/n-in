package com.n.in.strategy;


import com.n.in.model.NDto;
import com.n.in.provider.groq.client.GroqClient;
import com.n.in.provider.groq.reponse.Message;
import com.n.in.provider.groq.request.GroqRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroqStrategy implements IAClientStrategy {

    @Autowired
    private GroqClient groqClient;

    @Override
    public NDto generate() {
        GroqRequest req = new GroqRequest();
        req.setModel("llama-3.3-70b-versatile");

        Message msg = new Message();
        msg.setRole("user");
        msg.setContent("Genera un dato real verificable...");
        req.setMessages(List.of(msg));

        var res = groqClient.sendPrompt(req);

        return NDto.builder()
                .type("IA")
                .subType("GROQ")
                .status("initiated")
                .description(res.getChoices().get(0).getMessage().getContent())
                .lastUpdated(LocalDateTime.now())
                .created(LocalDateTime.now())
                .build();
    }
}
