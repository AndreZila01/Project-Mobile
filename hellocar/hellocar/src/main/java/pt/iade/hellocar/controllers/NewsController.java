package pt.iade.hellocar.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/greeter")
public class NewsController {
    private Logger logger = LoggerFactory.getLogger(NewsController.class);

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGreeting() {
        logger.info("Saying Hello to the world");
        return "Hello World";
    }
}