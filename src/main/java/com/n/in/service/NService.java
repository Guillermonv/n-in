package com.n.in.service;

import com.n.in.provider.groq.client.GroqClient;
import com.n.in.provider.unplash.client.UnsplashClient;
import com.n.in.model.NDto;
import com.n.in.provider.groq.request.GroqRequest;
import com.n.in.provider.groq.reponse.GroqResponse;
import com.n.in.provider.groq.reponse.MessageDto;
import com.n.in.provider.unplash.response.UnsplashSearchResponse;
import com.n.in.mapper.NMapper;
import com.n.in.model.NEntity;
import com.n.in.repository.NRepository;
import com.n.in.tasks.NStatusTask;
import com.n.in.utils.ImageDownloader;
import com.n.in.utils.NParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class NService {
    @Autowired
    NRepository repository;

    @Autowired
    UnsplashClient unsplashClient;

    @Autowired
    GroqClient groqClient;

    private static final Logger log = LoggerFactory.getLogger(NStatusTask.class);

    public void createN() throws Exception {
        GroqRequest groqRequest = new GroqRequest();
        groqRequest.setModel("llama-3.3-70b-versatile");
        MessageDto message = new MessageDto();
        message.setRole("user");
        message.setContent("Genera un dato real, verificable y poco conocido sobre Argentina, el cuerpo humano o un deporte. Debe ser sorprendente pero 100% verdadero y respaldado por instituciones oficiales, estudios científicos o registros verificables. Devuélvelo EXACTAMENTE en este formato, con cada campo en una única línea y con un salto de línea entre ellos: Título: [título breve] Mensaje: [texto periodístico de 13 a 15 líneas en un solo bloque, sin saltos de línea internos, sin opinión, citando fuentes oficiales] Prompt-imagen: [máximo 20 palabras, breve, solo basado en el hecho] IMPORTANTE: - No agregues texto antes ni después. - No agregues saltos de línea dentro del mensaje. - No agregues explicaciones. - Mantén la estructura EXACTA: “Título: …”, “Mensaje: …”, “Prompt-imagen: …”");
        groqRequest.setMessages(Arrays.asList(message));
        GroqResponse response = groqClient.sendPrompt(groqRequest);
        NDto ndto = NDto.builder().type("IA").subType("Groq")
                .status("initiated").
                created(LocalDateTime.now()).build();
        NParser.parse(response.getChoices().get(0).getMessage().getContent(), ndto);
        NMapper mapper = new NMapper();


        save(mapper.toEntity(ndto));

    }
    public void processInitiatedNs() {

     findByStatusAndImageUrlIsNull("initiated")
             .stream()
                .forEach(entity -> {

        // Buscar imagen en Unsplash
        UnsplashSearchResponse response = null;
        try {
            response = unsplashClient.searchPhotos(entity.getImagePrompt());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Optional.ofNullable(response.getResults())
                .filter(results -> !results.isEmpty())
                .map(results -> results.get(0).getUrls().getRegular())
                .ifPresentOrElse(imageUrl -> {

                    // Descargar imagen
                    ImageDownloader.downloadImageToTmp(imageUrl, entity.getId() + ".jpg");

                    log.info("Image downloaded for entity {} from URL {}", entity.getId(), imageUrl);

                    // Actualizar entidad
                    entity.setImageUrl(imageUrl);
                    entity.setStatus("pending");
                    save(entity);

                }, () -> log.info("No images found for entity {}", entity.getId()));
    });

}
    public NEntity save(NEntity n) {
        return repository.save(n);
    }

    public List<NEntity> findByStatusAndImageUrlIsNull(String status) {
        return repository.findByStatusAndImageUrlIsNull(status);
    }

}
