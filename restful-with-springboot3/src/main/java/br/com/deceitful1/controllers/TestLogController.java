package br.com.deceitful1.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/test/v1")
public class TestLogController
{
    private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

    @GetMapping
    public String testLog()
    {
        logger.info("this is a INFO log");
        logger.debug("this is a DEBUG log");
        logger.warn("this is a WARN log");
        logger.error("this is a ERROR log");
        return "Logs generated successfully";
    }

}
