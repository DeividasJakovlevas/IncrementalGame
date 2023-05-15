package org.example.models.PlayerDTO;

import java.util.List;

public class PlayerDTO {
    private String name;
    private List<ResourceDTO> resources;
    private List<GeneratorDTO> generators;

    public PlayerDTO() {
    }

    public PlayerDTO(String name, List<ResourceDTO> resources, List<GeneratorDTO> generators) {
        this.name = name;
        this.resources = resources;
        this.generators = generators;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ResourceDTO> getResources() {
        return resources;
    }

    public void setResources(List<ResourceDTO> resources) {
        this.resources = resources;
    }

    public List<GeneratorDTO> getGenerators() {
        return generators;
    }

    public void setGenerators(List<GeneratorDTO> generators) {
        this.generators = generators;
    }
}
