package scs.mpp.exam.rest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scs.mpp.exam.model.Player;
import scs.mpp.exam.model.WordAnswer;
import scs.mpp.exam.repository.PlayerRepository;
import scs.mpp.exam.services.Services;

import java.util.List;

@RestController
@RequestMapping("/words")
public class WordsRestController {
    private Services services;

    public WordsRestController() {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        services=(Services) factory.getBean("service");
    }

    @GetMapping()
    private ResponseEntity<?> loginUser(@RequestParam("gameId") String gameId, @RequestParam("category") String category) {
        List<WordAnswer> word = services.getWord(gameId, category);
        return new ResponseEntity<>(word, HttpStatus.OK);
    }
}
