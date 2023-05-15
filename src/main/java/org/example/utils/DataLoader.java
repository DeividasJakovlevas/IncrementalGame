package org.example.utils;


import org.example.models.ResourceGeneratorCost;
import org.example.models.ResourceGeneratorType;
import org.example.models.ResourceType;
import org.example.repository.ResourceGeneratorCostRepository;
import org.example.repository.ResourceGeneratorTypeRepository;
import org.example.repository.ResourceTypeRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ResourceTypeRepository resourceTypeRepository;
    private final ResourceGeneratorCostRepository resourceGeneratorCostRepository;
    private final ResourceGeneratorTypeRepository resourceGeneratorTypeRepository;

    public DataLoader(ResourceTypeRepository resourceTypeRepository, ResourceGeneratorCostRepository resourceGeneratorCostRepository,ResourceGeneratorTypeRepository resourceGeneratorTypeRepository) {
        this.resourceTypeRepository = resourceTypeRepository;
        this.resourceGeneratorCostRepository = resourceGeneratorCostRepository;
        this.resourceGeneratorTypeRepository = resourceGeneratorTypeRepository;
    }

    public List<ResourceType> generateBaseResourceTypeData() {
        List<ResourceType> resourceTypes = new ArrayList<>();

        ResourceType gold = new ResourceType();
        gold.setName("Gold");
        gold.setLevelRequired(1);
        resourceTypes.add(gold);

        ResourceType wood = new ResourceType();
        wood.setName("Wood");
        wood.setLevelRequired(1);
        resourceTypes.add(wood);

        ResourceType stone = new ResourceType();
        stone.setName("Stone");
        stone.setLevelRequired(2);
        resourceTypes.add(stone);

        ResourceType iron = new ResourceType();
        iron.setName("Iron");
        iron.setLevelRequired(3);
        resourceTypes.add(iron);

        return resourceTypes;
    }


    public List<List<ResourceGeneratorCost>> generateBaseCostData(List<ResourceType> resourceTypes, List<ResourceGeneratorType> generatorTypes) {
        List<List<ResourceGeneratorCost>> costs = new ArrayList<>();

        for (ResourceGeneratorType generatorType : generatorTypes) {
            List<ResourceGeneratorCost> generatorTypeCosts = new ArrayList<>();
            for (ResourceType resourceType : resourceTypes) {
                ResourceGeneratorCost cost = new ResourceGeneratorCost();
                cost.setResourceType(resourceType);
                cost.setGeneratorType(generatorType);
                cost.setBasePrice(10.0);
                cost.setPriceGrowth(1.1f);
                generatorTypeCosts.add(cost);
            }
            costs.add(generatorTypeCosts);
        }

        return costs;
    }

    public List<ResourceGeneratorType> generateBaseGeneratorTypeData(List<ResourceType> resourceTypes) {

        List<ResourceGeneratorType> generatorTypes = new ArrayList<>();

        for (ResourceType resourceType : resourceTypes) {
            ResourceGeneratorType generatorType = new ResourceGeneratorType();
            generatorType.setName(resourceType.getName() + " Generator");
            generatorType.setGeneratedAmount(2.0); // Set the generated amount as desired
            generatorType.setGeneratedResource(resourceType);
            generatorTypes.add(generatorType);
        }

        return generatorTypes;
    }
    @Override
    public void run(String... args) throws Exception {
        List<ResourceType> resourceTypes = generateBaseResourceTypeData();
        List<ResourceGeneratorType> resourceGenTypes = generateBaseGeneratorTypeData(resourceTypes);
        List<List<ResourceGeneratorCost>> resourceGenCosts = generateBaseCostData(resourceTypes,resourceGenTypes);

        resourceTypeRepository.saveAll(resourceTypes);
        resourceGeneratorTypeRepository.saveAll(resourceGenTypes);

        for (int i = 0; i < resourceGenTypes.size(); i++) {
            ResourceGeneratorType generatorType = resourceGenTypes.get(i);
            List<ResourceGeneratorCost> costs = resourceGenCosts.get(i);

            resourceGeneratorCostRepository.saveAll(costs);
            generatorType.setCosts(costs);
        }

        resourceGeneratorTypeRepository.saveAll(resourceGenTypes);
    }

}
