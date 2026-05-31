package com.ftn.sbnz.service.cep_Tests;

import models.PenaltyCriteria;
import models.Player;
import models.PlayerFoulEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.time.SessionPseudoClock;

import java.util.Collection;
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
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");
        player.setDisciplinaryAssessmentIntensity(0);
        this.kieSession.insert(player);

        for (int i = 0; i < 5; i++){
            PlayerFoulEvent playerFoulEvent = new PlayerFoulEvent(
                    player.getPlayerId(),
                    this.clock.getCurrentTime(),
                    false
            );
            this.kieSession.insert(playerFoulEvent);
            this.clock.advanceTime(30, TimeUnit.SECONDS);
        }
        this.kieSession.getAgenda().getAgendaGroup("CEP").setFocus();
        this.kieSession.fireAllRules();

        assertTrue(player.isAggressive());
        clock.advanceTime(11, TimeUnit.MINUTES);
    }

    @Test
    public void shouldNotSetAggressiveToTrue(){
        Player player = new Player();
        player.setAggressive(false);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");
        player.setDisciplinaryAssessmentIntensity(0);
        this.kieSession.insert(player);

        for (int i = 0; i < 5; i++){
            PlayerFoulEvent playerFoulEvent = new PlayerFoulEvent(
                    player.getPlayerId(),
                    this.clock.getCurrentTime(),
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
        this.kieSession.getAgenda().getAgendaGroup("CEP").setFocus();
        this.kieSession.fireAllRules();

        assertFalse(player.isAggressive());
    }

    @Test
    public void shouldIncreaseDisciplinaryRisk(){
        Player player = new Player();
        player.setAggressive(false);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");
        player.setDisciplinaryAssessmentIntensity(0);
        player.setIncreasedDisciplinaryRisk(false);
        this.kieSession.insert(player);

        for (int i = 0; i <= 2; i++){
            PlayerFoulEvent playerFoulEvent = new PlayerFoulEvent(
                    player.getPlayerId(),
                    this.clock.getCurrentTime(),
                    false
            );
            this.kieSession.insert(playerFoulEvent);
            this.clock.advanceTime(30, TimeUnit.SECONDS);
        }
        this.kieSession.getAgenda().getAgendaGroup("CEP").setFocus();
        this.kieSession.fireAllRules();

        assertTrue(player.isIncreasedDisciplinaryRisk());
    }

    @Test
    public void shouldNotIncreaseDisciplinaryRiskToTrue(){
        Player player = new Player();
        player.setAggressive(false);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");
        player.setIncreasedDisciplinaryRisk(false);
        this.kieSession.insert(player);

        for (int i = 0; i <= 2; i++){
            PlayerFoulEvent playerFoulEvent = new PlayerFoulEvent(
                    player.getPlayerId(),
                    this.clock.getCurrentTime(),
                    false
            );
            this.kieSession.insert(playerFoulEvent);
            if (i == 2) {
                this.clock.advanceTime(8, TimeUnit.MINUTES);
            }
            else{
                this.clock.advanceTime(3, TimeUnit.MINUTES);
            }

        }
        this.kieSession.getAgenda().getAgendaGroup("CEP").setFocus();
        this.kieSession.fireAllRules();

        assertFalse(player.isIncreasedDisciplinaryRisk());
    }

    @Test
    public void shouldIncreaseDisciplinaryAssessmentIntensity(){
        Player player = new Player();
        player.setAggressive(false);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");
        player.setDisciplinaryAssessmentIntensity(0);
        this.kieSession.insert(player);

        PlayerFoulEvent playerFoulEvent1 = new PlayerFoulEvent(
                player.getPlayerId(),
                this.clock.getCurrentTime(),
                false
        );
        this.clock.advanceTime(30, TimeUnit.SECONDS);
        PlayerFoulEvent playerFoulEvent2 = new PlayerFoulEvent(
                player.getPlayerId(),
                this.clock.getCurrentTime(),
                false
        );

        this.kieSession.insert(playerFoulEvent1);
        this.kieSession.insert(playerFoulEvent2);

        this.kieSession.getAgenda().getAgendaGroup("CEP").setFocus();
        this.kieSession.fireAllRules();
        assertTrue(player.getDisciplinaryAssessmentIntensity() != 0);
    }

    @Test
    public void shouldNotIncreaseDisciplinaryAssessmentIntensity(){
        Player player = new Player();
        player.setAggressive(false);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");
        player.setDisciplinaryAssessmentIntensity(0);
        this.kieSession.insert(player);

        PlayerFoulEvent playerFoulEvent1 = new PlayerFoulEvent(
                player.getPlayerId(),
                this.clock.getCurrentTime(),
                false
        );
        this.clock.advanceTime(30, TimeUnit.SECONDS);
        PlayerFoulEvent playerFoulEvent2 = new PlayerFoulEvent(
                "FC Arsenal-Bukayo-Saka-10",
                this.clock.getCurrentTime(),
                false
        );
        this.clock.advanceTime(10, TimeUnit.SECONDS);
        PlayerFoulEvent playerFoulEvent3 = new PlayerFoulEvent(
                player.getPlayerId(),
                this.clock.getCurrentTime(),
                false
        );

        this.kieSession.insert(playerFoulEvent1);
        this.kieSession.insert(playerFoulEvent2);
        this.kieSession.insert(playerFoulEvent3);

        this.kieSession.getAgenda().getAgendaGroup("CEP").setFocus();
        this.kieSession.fireAllRules();
        assertEquals(0,player.getDisciplinaryAssessmentIntensity());
    }

    @Test
    public void shouldGetUnsportsmanlikeConduct(){
        Player player = new Player();
        player.setAggressive(false);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");
        player.setDisciplinaryAssessmentIntensity(0);
        this.kieSession.insert(player);

        for (int i = 0; i < 5; i++){
            PlayerFoulEvent playerFoulEvent = new PlayerFoulEvent(
                    player.getPlayerId(),
                    this.clock.getCurrentTime(),
                    true
            );
            this.kieSession.insert(playerFoulEvent);
            this.clock.advanceTime(30, TimeUnit.SECONDS);
        }
        this.kieSession.getAgenda().getAgendaGroup("CEP").setFocus();
        this.kieSession.fireAllRules();

        FactType factType = kieSession.getKieBase().getFactType("rules", "UnsportsmanlikeConduct");

        boolean exists = kieSession.getFactHandles(obj -> factType.getFactClass().isInstance(obj))
                .stream()
                .map(handle -> kieSession.getObject(handle))
                .anyMatch(obj -> player.getPlayerId().equals(factType.get(obj, "playerId")));
       assertTrue(exists);
    }

    @Test
    public void shouldRemoveUnsportsmanlikeConduct(){
        Player player = new Player();
        player.setAggressive(false);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");
        player.setDisciplinaryAssessmentIntensity(0);
        this.kieSession.insert(player);

        for (int i = 0; i < 5; i++){
            PlayerFoulEvent playerFoulEvent = new PlayerFoulEvent(
                    player.getPlayerId(),
                    this.clock.getCurrentTime(),
                    true
            );
            this.kieSession.insert(playerFoulEvent);
            this.clock.advanceTime(30, TimeUnit.SECONDS);
        }
        this.kieSession.getAgenda().getAgendaGroup("CEP").setFocus();
        this.kieSession.fireAllRules();

        this.clock.advanceTime(15, TimeUnit.MINUTES);

        this.kieSession.getAgenda().getAgendaGroup("CEP").setFocus();
        this.kieSession.fireAllRules();

        FactType factType = kieSession.getKieBase().getFactType("rules", "UnsportsmanlikeConduct");

        boolean exists = kieSession.getFactHandles(obj -> factType.getFactClass().isInstance(obj))
                .stream()
                .map(handle -> kieSession.getObject(handle))
                .anyMatch(obj -> player.getPlayerId().equals(factType.get(obj, "playerId")));
        assertFalse(exists);
    }
}
