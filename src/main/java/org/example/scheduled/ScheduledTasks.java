package org.example.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.example.models.Player;
import org.example.models.PlayerResource;
import org.example.models.ResourceGenerator;
import org.example.models.ResourceType;
import org.example.service.PlayerService;
import org.example.service.ResourceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);


    private final PlayerService playerService;
    private final ResourceGeneratorService generatorService;

    public ScheduledTasks(PlayerService playerService, ResourceGeneratorService generatorService) {
        this.playerService = playerService;
        this.generatorService = generatorService;
    }
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("tick");
        // Get all players
        List<Player> players = playerService.getAllPlayers();

        // Iterate over each player
        for (Player player : players) {
            // Get the player's generators
            List<ResourceGenerator> generators = player.getGenerators();
            Map<String,PlayerResource> resMap = player.getPlayerResources().stream().collect(Collectors.toMap(
                    res -> res.getResourceType().getName(),           // Key mapping function
                    res -> res  // Value mapping function
            ));

            List<PlayerResource> resources = player.getPlayerResources();
            resources.forEach(res->System.out.println(res.getResourceType().getName())); ;
            // Increase player resources based on generator values
            for (ResourceGenerator generator : generators) {
                ResourceType generatedResource = generator.getGeneratorType().getGeneratedResource();
                double generatedAmount = generator.getGeneratorType().getGeneratedAmount() * generator.getLevel();
                PlayerResource res = resMap.get(generatedResource.getName());
                res.setAmount(res.getAmount() + generatedAmount);
                //save to list and save playerResource
                System.out.println(res.getAmount());
            }
        }

    }
}