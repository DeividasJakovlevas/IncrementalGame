package org.example.service;

import org.example.models.Player;
import org.example.models.PlayerDTO.PlayerDTO;
import org.example.models.PlayerResource;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    Player createPlayer(String name);
    List<Player> getAllPlayers();
    PlayerDTO getPlayerInfo(String name);
    List<Player> findAllWithPlayerResources();
    void savePlayerResources(List<PlayerResource> resources);
}
