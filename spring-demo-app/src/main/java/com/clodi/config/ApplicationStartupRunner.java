package com.clodi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartupRunner.class);

    @Value("${server.port}")
    private String serverPort;

    @Override
    public void run(String...args) throws Exception {
        logger.info("Server port is: {}", serverPort);
    }
}