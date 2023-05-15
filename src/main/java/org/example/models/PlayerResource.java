package org.example.models;

import jakarta.persistence.*;

@Entity
public class PlayerResource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "amount")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "player")
    private Player player;

    @ManyToOne
    private ResourceType resourceType;

    public PlayerResource() {
    }

    public PlayerResource(Long id, double amount, Player player, ResourceType resourceType) {
        this.id = id;
        this.amount = amount;
        this.player = player;
        this.resourceType = resourceType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }
}
