package org.example.repository;

import org.example.models.ResourceGeneratorType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceGeneratorTypeRepository extends JpaRepository<ResourceGeneratorType,Long> {
    ResourceGeneratorType findByName(String name);
}
