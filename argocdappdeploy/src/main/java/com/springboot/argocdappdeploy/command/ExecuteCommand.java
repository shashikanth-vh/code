package com.springboot.argocdappdeploy.command;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Thread.sleep;

@RestController
public class ExecuteCommand {

    @Value("${ARGO-TOKEN}")
    private String argoToken;

    @Value("${APP-NAME}")
    private String appName;

    @Value("${APP-REPO}")
    private String appRepo;

    @Value("${APP}")
    private String app;

    @PostConstruct
    private void init() {
        try {
            sleep(120000);

            Shell.executeCommand(argoToken, appName, appRepo, app);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/createApp")
    public String createApplication() {
        Shell.executeCommand(argoToken, appName, appRepo, app);
        return "deployed";
    }
}
