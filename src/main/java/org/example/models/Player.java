package org.example.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<PlayerResource> playerResources;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<ResourceGenerator> generators;

    public Player() {
    }

    public Player(Long id, String name, List<PlayerResource> playerResources, List<ResourceGenerator> generators) {
        this.id = id;
        this.name = name;
        this.playerResources = playerResources;
        this.generators = generators;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlayerResource> getPlayerResources() {
        return playerResources;
    }

    public void setPlayerResources(List<PlayerResource> playerResources) {
        this.playerResources = playerResources;
    }

    public List<ResourceGenerator> getGenerators() {
        return generators;
    }

    public void setGenerators(List<ResourceGenerator> generators) {
        this.generators = generators;
    }
}
