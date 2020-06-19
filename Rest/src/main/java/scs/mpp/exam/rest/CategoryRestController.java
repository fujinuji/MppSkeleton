package scs.mpp.exam.rest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scs.mpp.exam.model.GameCategory;
import scs.mpp.exam.services.Services;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {
    private Services services;

    public CategoryRestController() {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        services=(Services) factory.getBean("service");
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<?> getCategories(@PathVariable("gameId") String gameId) {
        List<GameCategory> categories = services.getCategories(gameId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
