package scs.mpp.exam.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scs.mpp.exam.model.Player;
import scs.mpp.exam.repository.PlayerRepository;

@RestController
@RequestMapping("/player")
public class PlayerRestController {
    private PlayerRepository repository = new PlayerRepository();

    @GetMapping("/{user}")
    private ResponseEntity<?> loginUser(@PathVariable("user") String user, @RequestParam("password") String password) {
        Player player = repository.checkUser(user, password);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<?> loginWithBody(@RequestBody Player player){
        Player playerS = repository.checkUser(player.getUserName(), player.getPassword());
        return new ResponseEntity<>(playerS, HttpStatus.OK);
    }
}
