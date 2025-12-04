package com.n.in.scheduler;

import com.n.in.provider.unplash.client.UnsplashClient;
import com.n.in.provider.unplash.response.UnsplashSearchResponse;
import com.n.in.repository.NRepository;
import com.n.in.service.NService;
import com.n.in.utils.ImageDownloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Optional;


@Component
public class NStatusTask {

    private static final Logger log = LoggerFactory.getLogger(NStatusTask.class);

    @Autowired
    NService nService;

    @Autowired
    NRepository nRepository;

    @Autowired
    UnsplashClient unsplashClient;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

   @Scheduled(fixedRate = 1000)
    public void createNs() throws Exception {
        nService.createWithGemini();
        log.info("N Created at {}", dateFormat.format(System.currentTimeMillis()));
    }

  //  @Scheduled(fixedRate = 1000)
    public void createNsv2() throws Exception {
        nService.createWithGroq();
        log.info("N Created at {}", dateFormat.format(System.currentTimeMillis()));
    }


 //@Scheduled(fixedRate = 1500)
 public void processInitiatedNs() {

     nRepository.findByStatusAndImageUrlIsNull("initiated")
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
                             nRepository.save(entity);

                         }, () -> log.info("No images found for entity {}", entity.getId()));
             });

 }
}