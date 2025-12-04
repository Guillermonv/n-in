package com.n.in.tasks;

import com.n.in.service.NService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;


@Component
public class NStatusTask {

    private static final Logger log = LoggerFactory.getLogger(NStatusTask.class);

    @Autowired
    NService nService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  // @Scheduled(fixedRate = 1000)
    public void createNs() throws Exception {
        nService.createN();
        log.info("N Created at {}", dateFormat.format(System.currentTimeMillis()));
    }

    @Scheduled(fixedRate = 1000)
    public void createNsv2() throws Exception {
        nService.createNv2();
        log.info("N Created at {}", dateFormat.format(System.currentTimeMillis()));
    }


 //   @Scheduled(fixedRate = 1500)
    public void updateInitiatedNs() {
        nService.processInitiatedNs();
    }
}