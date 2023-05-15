package org.example.repository;

import org.example.models.Player;
import org.example.models.ResourceGenerator;
import org.example.models.ResourceGeneratorCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResourceGeneratorRepository extends JpaRepository<ResourceGenerator,Long> {
    Optional<ResourceGenerator> findByGeneratorType_Name(String generatorTypeName);
}
