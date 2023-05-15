package org.example.repository;

import org.example.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {
    Player findByName(String name);
    @Query("SELECT DISTINCT p FROM Player p LEFT JOIN FETCH p.playerResources")
    List<Player> findAllWithPlayerResources();
}
