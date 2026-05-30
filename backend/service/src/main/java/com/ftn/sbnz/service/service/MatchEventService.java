package com.ftn.sbnz.service.service;

import com.ftn.sbnz.service.dto.PlayerDTO;
import com.ftn.sbnz.service.dto.PlayerFoulEventDTO;
import models.Player;
import models.PlayerFoulEvent;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.time.SessionPseudoClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class MatchEventService {

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private KieSession kieSession;

    public void insertPlayers(List<PlayerDTO> playersDTO) {
        for (PlayerDTO playerDTO : playersDTO) {
            Player player = Player.builder().playerId(playerDTO.getPlayerId()).build();
            this.kieSession.insert(player);
        }
        this.kieSession.fireAllRules();
        System.out.println("Uspesno ubačeno " + playersDTO.size() + " igrača u radnu memoriju meča!");
    }

    public void recordSmallIncident(PlayerFoulEventDTO playerFoulEventDTO) {
        Set<String> firedRules = new HashSet<>();
        kieSession.addEventListener(new org.kie.api.event.rule.DefaultAgendaEventListener() {
            @Override
            public void afterMatchFired(org.kie.api.event.rule.AfterMatchFiredEvent event) {
                firedRules.add(event.getMatch().getRule().getName());
            }
        });
        PlayerFoulEvent playerFoulEvent = new PlayerFoulEvent(
                playerFoulEventDTO.getPlayerId(),
                this.kieSession.getSessionClock().getCurrentTime(),
                playerFoulEventDTO.isContact()
                );
        this.kieSession.insert(playerFoulEvent);
        kieSession.getAgenda().getAgendaGroup("CEP").setFocus();
        this.kieSession.fireAllRules();
        for (String rule: firedRules){
            System.out.println("Aktivirano CEP pravilo: " + rule);
        }

    }

    public void advanceClockTime(){
        SessionPseudoClock clock = this.kieSession.getSessionClock();
        clock.advanceTime(2, TimeUnit.MINUTES);
        System.out.println(clock.getCurrentTime());
    }
}
