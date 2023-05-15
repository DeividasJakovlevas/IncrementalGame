package org.example.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ResourceGeneratorType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "generated_amount")
    private double generatedAmount;

    @ManyToOne
    @JoinColumn(name = "generated_resource_id")
    private ResourceType generatedResource;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resource_generator_type_id")
    private List<ResourceGeneratorCost> costs;

    public ResourceGeneratorType() {
    }

    public ResourceGeneratorType(Long id, String name, double generatedAmount, ResourceType generatedResource, List<ResourceGeneratorCost> costs) {
        this.id = id;
        this.name = name;
        this.generatedAmount = generatedAmount;
        this.generatedResource = generatedResource;
        this.costs = costs;
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

    public double getGeneratedAmount() {
        return generatedAmount;
    }

    public void setGeneratedAmount(double generatedAmount) {
        this.generatedAmount = generatedAmount;
    }

    public ResourceType getGeneratedResource() {
        return generatedResource;
    }

    public void setGeneratedResource(ResourceType generatedResource) {
        this.generatedResource = generatedResource;
    }

    public List<ResourceGeneratorCost> getCosts() {
        return costs;
    }

    public void setCosts(List<ResourceGeneratorCost> costs) {
        this.costs = costs;
    }
}
