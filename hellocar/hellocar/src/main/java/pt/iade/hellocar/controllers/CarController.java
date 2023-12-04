package pt.iade.hellocar.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/car")
public class CarController {
    private Logger logger = LoggerFactory.getLogger(CarController.class);

    @GetMapping(path = "/localizacao/{lat}/{loc}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGreeting(@PathVariable("lat") float latitude, @PathVariable("loc") float longitude) {
        logger.info("Saying Hello to the world");
        return "Hello World";
    }
}


