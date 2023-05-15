package org.example.models;


import jakarta.persistence.*;

@Entity
public class ResourceType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "level_required")
    private int levelRequired;

    public ResourceType() {
    }

    public ResourceType(Long id, String name, int levelRequired) {
        this.id = id;
        this.name = name;
        this.levelRequired = levelRequired;
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

    public int getLevelRequired() {
        return levelRequired;
    }

    public void setLevelRequired(int levelRequired) {
        this.levelRequired = levelRequired;
    }
}
