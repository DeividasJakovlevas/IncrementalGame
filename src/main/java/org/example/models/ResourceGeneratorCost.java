package org.example.models;

import jakarta.persistence.*;

@Entity
public class ResourceGeneratorCost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private ResourceType resourceType;
    @ManyToOne
    private ResourceGeneratorType generatorType;

    @Column(name = "base_price")
    private double basePrice;

    @Column(name = "price_growth")
    private float priceGrowth;

    public ResourceGeneratorCost() {
    }

    public ResourceGeneratorCost(Long id, ResourceType resourceType, ResourceGeneratorType generatorType, double basePrice, float priceGrowth) {
        this.id = id;
        this.resourceType = resourceType;
        this.generatorType = generatorType;
        this.basePrice = basePrice;
        this.priceGrowth = priceGrowth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public ResourceGeneratorType getGeneratorType() {
        return generatorType;
    }

    public void setGeneratorType(ResourceGeneratorType generatorType) {
        this.generatorType = generatorType;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public float getPriceGrowth() {
        return priceGrowth;
    }

    public void setPriceGrowth(float priceGrowth) {
        this.priceGrowth = priceGrowth;
    }
}
