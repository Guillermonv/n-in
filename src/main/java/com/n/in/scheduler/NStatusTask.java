package com.n.in.scheduler;

import com.n.in.provider.unplash.client.UnsplashClient;
import com.n.in.model.repository.NRepository;
import com.n.in.service.WorkflowExecutionService;
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
    WorkflowExecutionService workflowExecutionService;

    @Autowired
    NRepository nRepository;

    @Autowired
    UnsplashClient unsplashClient;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 1000)
    public void createNs() throws Exception {
        workflowExecutionService.executeWorkflow(1L);
        log.info("N Created at {}", dateFormat.format(System.currentTimeMillis()));
    }
    }