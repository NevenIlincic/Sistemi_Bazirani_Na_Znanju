package com.ftn.sbnz.service.CEP_Tests;

import models.Player;
import models.PlayerFoulEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.time.SessionPseudoClock;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

public class CEPTests1 {
    private static KieContainer kieContainer;
    private KieSession kieSession;
    private SessionPseudoClock clock;

    @BeforeAll
    public static void setup(){
        kieContainer = KieServices.Factory.get().newKieClasspathContainer("");
    }

    @BeforeEach
    public void setUpKieSession(){
        this.kieSession = kieContainer.newKieSession("cepConfigKsessionPseudoClock");
        this.clock = this.kieSession.getSessionClock();
    }

    @AfterEach
    public void clearUpSession(){
        this.kieSession.dispose();
    }

    @Test
    public void shouldSetAggressiveTrue(){
        Player player = new Player();
        player.setAggressive(false);
        player.setPlayerJerseyNumber(10);
        player.setDisciplinaryAssessmentIntensity(0);
        this.kieSession.insert(player);

        for (int i = 0; i < 5; i++){
            PlayerFoulEvent playerFoulEvent = new PlayerFoulEvent(
                    player.getPlayerJerseyNumber(),
                    new Date(clock.getCurrentTime()),
                    false
            );
            this.kieSession.insert(playerFoulEvent);
            this.clock.advanceTime(30, TimeUnit.SECONDS);
        }

        this.kieSession.fireAllRules();

        assertTrue(player.isAggressive());
        clock.advanceTime(11, TimeUnit.MINUTES);
    }

    @Test
    public void shouldNotSetAggressiveToTrue(){
        Player player = new Player();
        player.setAggressive(false);
        player.setPlayerJerseyNumber(10);
        player.setDisciplinaryAssessmentIntensity(0);
        this.kieSession.insert(player);

        for (int i = 0; i < 5; i++){
            PlayerFoulEvent playerFoulEvent = new PlayerFoulEvent(
                    player.getPlayerJerseyNumber(),
                    new Date(clock.getCurrentTime()),
                    false
            );
            this.kieSession.insert(playerFoulEvent);
            if (i == 4) {
                this.clock.advanceTime(8, TimeUnit.MINUTES);
            }
            else{
                this.clock.advanceTime(3, TimeUnit.MINUTES);
            }

        }

        this.kieSession.fireAllRules();

        assertFalse(player.isAggressive());
    }
}
