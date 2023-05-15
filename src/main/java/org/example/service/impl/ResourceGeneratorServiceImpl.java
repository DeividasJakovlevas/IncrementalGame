package org.example.service.impl;

import org.example.models.*;
import org.example.repository.PlayerRepository;
import org.example.repository.PlayerResourceRepository;
import org.example.repository.ResourceGeneratorRepository;
import org.example.repository.ResourceGeneratorTypeRepository;
import org.example.service.ResourceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceGeneratorServiceImpl implements ResourceGeneratorService {
    private PlayerRepository playerRepository;
    private PlayerResourceRepository playerResourceRepository;
    private ResourceGeneratorRepository resourceGeneratorRepository;
    private ResourceGeneratorTypeRepository resourceGeneratorTypeRepository;

    @Autowired
    public ResourceGeneratorServiceImpl(PlayerRepository playerRepository,
                                        ResourceGeneratorRepository resourceGeneratorRepository,
                                        ResourceGeneratorTypeRepository resourceGeneratorTypeRepository,
                                        PlayerResourceRepository playerResourceRepository) {
        this.playerRepository = playerRepository;
        this.resourceGeneratorRepository = resourceGeneratorRepository;
        this.resourceGeneratorTypeRepository = resourceGeneratorTypeRepository;
        this.playerResourceRepository = playerResourceRepository;
    }

    @Override
    public boolean buyResourceGenerator(String playerName, String generatorTypeName) {
        Player player = playerRepository.findByName(playerName);
        Optional<ResourceGenerator> generator = resourceGeneratorRepository.findByGeneratorType_Name(generatorTypeName);
        if(generator.isPresent()){
            return upgradeResourceGenerator(player,generator.get());
        }

        ResourceGeneratorType generatorType = resourceGeneratorTypeRepository.findByName(generatorTypeName);

        boolean hasEnoughResources = checkPlayerResources(player, generatorType.getCosts());

        if(hasEnoughResources){
            ResourceGenerator resourceGenerator = new ResourceGenerator();
            resourceGenerator.setLevel(1);
            resourceGenerator.setGeneratorType(generatorType);
            resourceGenerator.setPlayer(player);
            resourceGeneratorRepository.save(resourceGenerator);
            deductGeneratorCost(player,generatorType.getCosts());
        }
        return hasEnoughResources;
    }
    private boolean upgradeResourceGenerator(Player player, ResourceGenerator generator) {
        System.out.println(generator.getGeneratorType().getCosts());

        boolean hasEnoughResources = checkPlayerResources(player, generator.getGeneratorType().getCosts(), generator.getLevel());
        if(hasEnoughResources){
            generator.setLevel(generator.getLevel()+1);
            resourceGeneratorRepository.save(generator);
            deductGeneratorCost(player,generator.getGeneratorType().getCosts(),generator.getLevel());
        }
        return hasEnoughResources;
    }

    private boolean checkPlayerResources(Player player, List<ResourceGeneratorCost> costs) {
        return checkPlayerResources(player,costs,0);
    }
    private boolean checkPlayerResources(Player player, List<ResourceGeneratorCost> costs, int level) {
        for (ResourceGeneratorCost cost : costs) {
            ResourceType resourceType = cost.getResourceType();
            double requiredAmount = cost.getBasePrice() * Math.pow(cost.getPriceGrowth(),level);
            System.out.println(requiredAmount);
            PlayerResource playerResource = playerResourceRepository.findByPlayerAndResourceType(player, resourceType);
            if (playerResource == null || playerResource.getAmount() < requiredAmount) {
                return false;
            }
        }

        return true;
    }
    private void deductGeneratorCost(Player player, List<ResourceGeneratorCost> costs) {
        deductGeneratorCost(player, costs, 0);
    }
    private void deductGeneratorCost(Player player, List<ResourceGeneratorCost> costs, int level) {
        for (ResourceGeneratorCost cost : costs) {
            ResourceType resourceType = cost.getResourceType();
            double requiredAmount = cost.getBasePrice() * Math.pow(cost.getPriceGrowth(),level);

            PlayerResource playerResource = playerResourceRepository.findByPlayerAndResourceType(player, resourceType);
            if (playerResource != null) {
                double currentAmount = playerResource.getAmount();
                playerResource.setAmount(currentAmount - requiredAmount);
                playerResourceRepository.save(playerResource);
            }
        }
    }

}
