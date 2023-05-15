package org.example.service;

import org.example.models.Player;
import org.example.models.PlayerDTO.PlayerDTO;

import java.util.Optional;

public interface PlayerService {
    Player createPlayer(String name);
    PlayerDTO getPlayerInfo(String name);
}
