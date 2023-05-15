package org.example.repository;

import org.example.models.Player;
import org.example.models.PlayerResource;
import org.example.models.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerResourceRepository extends JpaRepository<PlayerResource,Long> {
    PlayerResource findByPlayerAndResourceType(Player player, ResourceType resourceType);
}
