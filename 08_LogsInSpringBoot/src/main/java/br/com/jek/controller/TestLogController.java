package br.com.jek.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestLogController {

    private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

    @GetMapping("/test")
    public String testLog(){

        logger.error("This is an ERROR log");
        logger.warn("This is an WARN log");
        logger.info("This is an INFO log");
        logger.debug("This is an DEBUG log");

        return "Logs generated successfully";
    }
}
