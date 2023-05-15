package org.example.service.impl;

import org.example.models.*;
import org.example.models.PlayerDTO.GeneratorDTO;
import org.example.models.PlayerDTO.PlayerDTO;
import org.example.models.PlayerDTO.ResourceDTO;
import org.example.repository.PlayerRepository;
import org.example.repository.ResourceGeneratorTypeRepository;
import org.example.repository.ResourceTypeRepository;
import org.example.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private PlayerRepository playerRepo;
    private ResourceTypeRepository resourceTypeRepository;
    private ResourceGeneratorTypeRepository resourceGeneratorTypeRepository;

    public PlayerServiceImpl(PlayerRepository playerRepo, ResourceTypeRepository resourceTypeRepository, ResourceGeneratorTypeRepository resourceGeneratorTypeRepository) {
        this.playerRepo = playerRepo;
        this.resourceTypeRepository = resourceTypeRepository;
        this.resourceGeneratorTypeRepository = resourceGeneratorTypeRepository;
    }

    @Override
    public Player createPlayer(String name) {
        Player player = new Player();
        player.setName(name);

        // Retrieve resource types from the database
        List<ResourceType> resourceTypes = resourceTypeRepository.findAll();

        // Create and associate starting resources
        List<PlayerResource> playerResources = new ArrayList<>();
        for (ResourceType resourceType : resourceTypes) {
            PlayerResource playerResource = new PlayerResource();
            playerResource.setPlayer(player);
            playerResource.setResourceType(resourceType);
            playerResource.setAmount(0.0); // Set the initial amount as desired
            playerResources.add(playerResource);
        }
        player.setPlayerResources(playerResources);

        // Retrieve generator types from the database
        List<ResourceGeneratorType> generatorTypes = resourceGeneratorTypeRepository.findAll();

        // Create and associate level 1 generators for each resource type
        List<ResourceGenerator> generators = new ArrayList<>();
        for (ResourceGeneratorType generatorType : generatorTypes) {
            ResourceGenerator generator = new ResourceGenerator();
            generator.setPlayer(player);
            generator.setGeneratorType(generatorType);
            generator.setLevel(1);
            generators.add(generator);
        }
        player.setGenerators(generators);
        return playerRepo.save(player);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepo.findAll();
    }


    @Override
    public PlayerDTO getPlayerInfo(String name) {
        Player player = playerRepo.findByName(name);
        PlayerDTO dto = new PlayerDTO();
        dto.setName(player.getName());

        List<ResourceDTO> resourceDTOs = new ArrayList<>();
        for (PlayerResource playerResource : player.getPlayerResources()) {
            ResourceDTO resourceDTO = new ResourceDTO(playerResource.getResourceType().getName(), playerResource.getAmount());
            resourceDTOs.add(resourceDTO);
        }
        dto.setResources(resourceDTOs);

        List<GeneratorDTO> generatorDTOs = new ArrayList<>();
        for (ResourceGenerator generator : player.getGenerators()) {
            GeneratorDTO generatorDTO = new GeneratorDTO(generator.getGeneratorType().getName(), generator.getLevel());
            generatorDTOs.add(generatorDTO);
        }
        dto.setGenerators(generatorDTOs);

        return dto;
    }

    @Override
    public List<Player> findAllWithPlayerResources() {
        return playerRepo.findAllWithPlayerResources();
    }
}
