package service;

import DTO.PlayerDTO;
import DTO.PlayerFoulEventDTO;
import models.Player;
import models.PlayerFoulEvent;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MatchEventService {

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private KieSession kieSession;

    public void insertPlayers(List<PlayerDTO> playersDTO) {
        for (PlayerDTO playerDTO : playersDTO) {
            Player player = Player.builder().playerJerseyNumber(playerDTO.getPlayerJerseyNumber()).build();
            this.kieSession.insert(player);
        }
        this.kieSession.fireAllRules();
    }

    public void recordSmallIncident(PlayerFoulEventDTO playerFoulEventDTO) {
        PlayerFoulEvent playerFoulEvent = new PlayerFoulEvent(
                playerFoulEventDTO.getPlayerJerseyNumber(),
                new Date(),
                playerFoulEventDTO.isContact()
                );
        this.kieSession.insert(playerFoulEvent);
        this.kieSession.fireAllRules();
    }
}
