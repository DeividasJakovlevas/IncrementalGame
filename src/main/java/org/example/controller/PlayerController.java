package org.example.controller;

import org.example.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping("/create")
    public ResponseEntity<?> createPlayer(@RequestParam("name") String name) {

        playerService.createPlayer(name);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/info")
    public ResponseEntity<?> getPlayerInfo(@RequestParam("name") String name) {
        return ResponseEntity.ok(playerService.getPlayerInfo(name));
    }
}
