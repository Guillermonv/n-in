package com.n.in.tasks;

import com.n.in.client.UnsplashClient;
import com.n.in.dto.NDto;
import com.n.in.dto.unplash.UnsplashSearchResponse;
import com.n.in.mapper.NMapper;
import com.n.in.service.NService;
import com.n.in.utils.ImageDownloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class NStatusTask {

    private static final Logger log = LoggerFactory.getLogger(NStatusTask.class);

    @Autowired
    UnsplashClient unsplashClient;

    @Autowired
    NService nService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

   // @Scheduled(fixedRate = 1000)
    public void createNs() throws Exception {
        NDto ndto = NDto.builder()
                .status("initiated").
                created(LocalDateTime.now()).build();
        NMapper mapper = new NMapper();

        nService.save(mapper.toEntity(ndto));

        log.info("Stored Object {}", ndto.getId());
    }

    //@Scheduled(fixedRate = 1500)
    public void processInitiatedNs() {

        nService.findByStatusAndImageUrlIsNull("initiated")
                .stream()
                .forEach(entity -> {

                    // Buscar imagen en Unsplash
                    UnsplashSearchResponse response = null;
                    try {
                        response = unsplashClient.searchPhotos(entity.getName());
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
                                nService.save(entity);

                            }, () -> log.info("No images found for entity {}", entity.getId()));
                });

    }
}