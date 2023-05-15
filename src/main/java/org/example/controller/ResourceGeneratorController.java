package org.example.controller;

import org.example.service.PlayerService;
import org.example.service.ResourceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resource/generator")
public class ResourceGeneratorController {
    @Autowired
    private ResourceGeneratorService resourceGeneratorService;

    @PostMapping("/buy")
    public ResponseEntity<?> buyGenerator(@RequestParam("playerName") String playerName, @RequestParam("generatorName") String generatorName) {
        if(resourceGeneratorService.buyResourceGenerator(playerName,generatorName)){
            System.out.println("ok");
            return ResponseEntity.ok().build();
        }
        else{
            // should this be an exception ..(?)
            System.out.println("not enough resources");
            return ResponseEntity.ok("Not enough resources");
        }
    }
}
