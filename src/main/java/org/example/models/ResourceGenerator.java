package org.example.models;

import jakarta.persistence.*;

@Entity
public class ResourceGenerator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "level")
    private int level;

    @ManyToOne
    private ResourceGeneratorType generatorType;

    @ManyToOne
    private Player player;

    public ResourceGenerator() {
    }

    public ResourceGenerator(Long id, int level, ResourceGeneratorType generatorType, Player player) {
        this.id = id;
        this.level = level;
        this.generatorType = generatorType;
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ResourceGeneratorType getGeneratorType() {
        return generatorType;
    }

    public void setGeneratorType(ResourceGeneratorType generatorType) {
        this.generatorType = generatorType;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
