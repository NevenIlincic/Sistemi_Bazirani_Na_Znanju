package com.ftn.sbnz.service.inter_rules_Tests;

import enums.ContactPoint;
import enums.FoulType;
import enums.Intensity;
import enums.TackleControl;
import models.Incident;
import models.Player;
import models.PlayerFoulEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Match;
import org.kie.api.time.SessionPseudoClock;
import static org.junit.jupiter.api.Assertions.*;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.internal.runtime.helper.BatchExecutionHelper; // Ako je starija verzija
// Najvažniji import:
import org.kie.api.runtime.rule.AgendaFilter;

import java.util.concurrent.TimeUnit;

public class InterRulesTests {

    private static KieContainer kieContainer;
    private KieSession kieSession;
    private SessionPseudoClock clock;

    @BeforeAll
    public static void setup() {
        kieContainer = KieServices.Factory.get().newKieClasspathContainer("");
    }

    @BeforeEach
    public void setUpKieSession() {
        this.kieSession = kieContainer.newKieSession("cepConfigKsessionPseudoClock");
        this.clock = this.kieSession.getSessionClock();
    }

    @AfterEach
    public void clearUpSession() {
        this.kieSession.dispose();
    }

    @Test
    public void shouldSetHighIntensityForAggresivePlayer() {
        Player player = new Player();
        player.setAggressive(true);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");
        player.setDisciplinaryAssessmentIntensity(0);

        Incident incident = Incident.builder()
                .playerId(player.getPlayerId())
                .foulType(FoulType.NON_EXISTENT)
                .intensity(Intensity.LOW)
                .build();

        this.kieSession.insert(player);
        this.kieSession.insert(incident);

        this.kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();
        this.kieSession.fireAllRules();

        assertEquals(Intensity.HIGH, incident.getIntensity());
    }

    @Test
    public void shouldSetTackleIntensityWhenIncreasedDisciplinaryRiskLOW() {
        Player player = new Player();
        player.setIncreasedDisciplinaryRisk(true);
        player.setAggressive(false);
        player.setDisciplinaryAssessmentIntensity(0);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");

        Incident incident = Incident.builder()
                .incidentId(1)
                .playerId(player.getPlayerId())
                .foulType(FoulType.NON_EXISTENT)
                .intensity(Intensity.LOW)
                .build();

        this.kieSession.insert(player);
        this.kieSession.insert(incident);

        this.kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();
        this.kieSession.fireAllRules();

//        this.kieSession.fireAllRules(new AgendaFilter() {
//            @Override
//            public boolean accept(Match match) {
//                return match.getRule().getName().equals("Set tackle intensity when increased disciplinary risk - LOW");
//            }
//        });

        assertEquals(Intensity.MEDIUM, incident.getIntensity());
    }

    @Test
    public void shouldSetTackleIntensityWhenIncreasedDisciplinaryRiskMEDIUM() {
        Player player = new Player();
        player.setIncreasedDisciplinaryRisk(true);
        player.setAggressive(false);
        player.setDisciplinaryAssessmentIntensity(0);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");

        Incident incident = Incident.builder()
                .incidentId(1)
                .playerId(player.getPlayerId())
                .foulType(FoulType.NON_EXISTENT)
                .intensity(Intensity.MEDIUM)
                .build();

        this.kieSession.insert(player);
        this.kieSession.insert(incident);

        this.kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();
        this.kieSession.fireAllRules();

//        this.kieSession.fireAllRules(new AgendaFilter() {
//            @Override
//            public boolean accept(Match match) {
//                return match.getRule().getName().equals("Set tackle intensity when increased disciplinary risk - MEDIUM");
//            }
//        });

        assertEquals(Intensity.HIGH, incident.getIntensity());
    }

    @Test
    public void shouldSetTackleIntensityWhenDisciplinaryAssessmentIntensityLOW() {
        Player player = new Player();
        player.setIncreasedDisciplinaryRisk(false);
        player.setAggressive(false);
        player.setDisciplinaryAssessmentIntensity(5);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");

        Incident incident = Incident.builder()
                .incidentId(1)
                .playerId(player.getPlayerId())
                .foulType(FoulType.NON_EXISTENT)
                .intensity(Intensity.LOW)
                .build();

        this.kieSession.insert(player);
        this.kieSession.insert(incident);

        this.kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();
        this.kieSession.fireAllRules();

        assertEquals(Intensity.MEDIUM, incident.getIntensity());

    }

    @Test
    public void shouldSetTackleIntensityWhenDisciplinaryAssessmentIntensityMEDIUM() {
        Player player = new Player();
        player.setIncreasedDisciplinaryRisk(false);
        player.setAggressive(false);
        player.setDisciplinaryAssessmentIntensity(5);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");

        Incident incident = Incident.builder()
                .incidentId(1)
                .playerId(player.getPlayerId())
                .foulType(FoulType.NON_EXISTENT)
                .intensity(Intensity.MEDIUM)
                .build();

        this.kieSession.insert(player);
        this.kieSession.insert(incident);

        this.kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();
        this.kieSession.fireAllRules();

        assertEquals(Intensity.HIGH, incident.getIntensity());
    }

    @Test
    public void shouldSetFoulTypeToRecklessWhenUnsportsmanlikeConduct() {
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

        Incident incident = Incident.builder()
                .incidentId(1)
                .playerId(player.getPlayerId())
                .foulType(FoulType.NON_EXISTENT)
                .intensity(Intensity.MEDIUM)
                .build();

        this.kieSession.insert(incident);
        this.kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();
        this.kieSession.fireAllRules();

        assertEquals(FoulType.RECKLESS, incident.getFoulType());
    }

    @Test
    public void shouldSetFoulTypeToCarelessWhenDangerousPlay(){
        Player player = new Player();
        player.setIncreasedDisciplinaryRisk(false);
        player.setAggressive(false);
        player.setDisciplinaryAssessmentIntensity(5);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");

        Incident incident = Incident.builder()
                .incidentId(1)
                .playerId(player.getPlayerId())
                .foulType(FoulType.NON_EXISTENT)
                .contact(false)
                .dangerousPlay(true)
                .build();

        this.kieSession.insert(player);
        this.kieSession.insert(incident);
        this.kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();
        this.kieSession.fireAllRules();

        assertEquals(FoulType.CARELESS, incident.getFoulType());
    }

    @Test
    public void shouldNotChangeFoulTypeWhenNonExistent(){
        Player player = new Player();
        player.setIncreasedDisciplinaryRisk(false);
        player.setAggressive(false);
        player.setDisciplinaryAssessmentIntensity(5);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");

        Incident incident = Incident.builder()
                .incidentId(1)
                .playerId(player.getPlayerId())
                .foulType(FoulType.NON_EXISTENT)
                .contact(false)
                .dangerousPlay(false)
                .build();

        this.kieSession.insert(player);
        this.kieSession.insert(incident);
        this.kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();
        this.kieSession.fireAllRules();

        assertEquals(FoulType.NON_EXISTENT, incident.getFoulType());
    }

    @Test
    public void shouldChangeToCarelessFoulType(){
        Player player = new Player();
        player.setIncreasedDisciplinaryRisk(false);
        player.setAggressive(false);
        player.setDisciplinaryAssessmentIntensity(0);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");

        Incident incident = Incident.builder()
                .incidentId(1)
                .playerId(player.getPlayerId())
                .foulType(FoulType.NON_EXISTENT)
                .contact(true)
                .intensity(Intensity.LOW)
                .tackleControl(TackleControl.CONTROLLED)
                .build();

        this.kieSession.insert(player);
        this.kieSession.insert(incident);
        this.kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();
        this.kieSession.fireAllRules();

        assertEquals(FoulType.CARELESS, incident.getFoulType());
    }

    @Test
    public void shouldChangeToRecklessFoulTypeIntensityMedium(){
        Player player = new Player();
        player.setIncreasedDisciplinaryRisk(false);
        player.setAggressive(false);
        player.setDisciplinaryAssessmentIntensity(0);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");

        Incident incident = Incident.builder()
                .incidentId(1)
                .playerId(player.getPlayerId())
                .foulType(FoulType.NON_EXISTENT)
                .contact(true)
                .intensity(Intensity.MEDIUM)
                .build();

        this.kieSession.insert(player);
        this.kieSession.insert(incident);
        this.kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();
        this.kieSession.fireAllRules();

        assertEquals(FoulType.RECKLESS, incident.getFoulType());
    }

    @Test
    public void shouldChangeToRecklessFoulTypeContactFromBehind(){
        Player player = new Player();
        player.setIncreasedDisciplinaryRisk(false);
        player.setAggressive(false);
        player.setDisciplinaryAssessmentIntensity(0);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");

        Incident incident = Incident.builder()
                .incidentId(1)
                .playerId(player.getPlayerId())
                .foulType(FoulType.NON_EXISTENT)
                .contact(true)
                .tackleControl(TackleControl.NOT_CONTROLLED)
                .contactFromBehind(true)
                .build();

        this.kieSession.insert(player);
        this.kieSession.insert(incident);
        this.kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();
        this.kieSession.fireAllRules();

        assertEquals(FoulType.RECKLESS, incident.getFoulType());
    }

    @Test
    public void shouldChangeToExcessiveFoulTypeWhenHighIntensity(){
        Player player = new Player();
        player.setIncreasedDisciplinaryRisk(false);
        player.setAggressive(false);
        player.setDisciplinaryAssessmentIntensity(0);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");

        Incident incident = Incident.builder()
                .incidentId(1)
                .playerId(player.getPlayerId())
                .foulType(FoulType.NON_EXISTENT)
                .contact(true)
                .intensity(Intensity.HIGH)
                .build();

        this.kieSession.insert(player);
        this.kieSession.insert(incident);
        this.kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();
        this.kieSession.fireAllRules();

        assertEquals(FoulType.EXCESSIVE, incident.getFoulType());
    }

    @Test
    public void shouldChangeToRecklessFoulTypeWhenOpenFootAboveJoint(){
        Player player = new Player();
        player.setIncreasedDisciplinaryRisk(false);
        player.setAggressive(false);
        player.setDisciplinaryAssessmentIntensity(0);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");

        Incident incident = Incident.builder()
                .incidentId(1)
                .playerId(player.getPlayerId())
                .foulType(FoulType.NON_EXISTENT)
                .contact(true)
                .openFoot(true)
                .contactPoint(ContactPoint.ABOVE_JOINT)
                .build();

        this.kieSession.insert(player);
        this.kieSession.insert(incident);
        this.kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();
        this.kieSession.fireAllRules();

        assertEquals(FoulType.EXCESSIVE, incident.getFoulType());
    }

    @Test
    public void shouldIncreaseNumSoftFoulsWhenCarelessFoulType(){
        Player player = new Player();
        player.setIncreasedDisciplinaryRisk(false);
        player.setAggressive(false);
        player.setDisciplinaryAssessmentIntensity(0);
        player.setNumSoftFouls(0);
        player.setPlayerId("FC Barcelona-Lamine-Yamal-10");

        Incident incident = Incident.builder()
                .incidentId(1)
                .playerId(player.getPlayerId())
                .foulType(FoulType.NON_EXISTENT)
                .contact(false)
                .dangerousPlay(true)
                .build();

        this.kieSession.insert(player);
        this.kieSession.insert(incident);
        this.kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();
        this.kieSession.fireAllRules();

        assertTrue(player.getNumSoftFouls() != 0);
    }
}
