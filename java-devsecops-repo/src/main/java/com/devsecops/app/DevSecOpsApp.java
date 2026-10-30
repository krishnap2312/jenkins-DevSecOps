package com.devsecops.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DevSecOpsApp {

    public static void main(String[] args) {
        SpringApplication.run(DevSecOpsApp.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to CI/CD Pipeline! The Java App is successfully running on Kubernetes.";
    }
}