package com.ftn.sbnz.service.DOGSO_Tests;

import enums.*;
import models.GameState;
import models.Incident;
import models.Recommendation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DogsoTests {

    private static KieContainer kieContainer;
    private KieSession kieSession;

    @BeforeAll
    public static void setup(){
        kieContainer = KieServices.Factory.get().newKieClasspathContainer();
    }

    @BeforeEach
    public void setupKieSession(){
        kieSession = kieContainer.newKieSession("cepConfigKsessionRealtimeClock");
    }

    @Test
    public void shouldActivateDoubleJeopardy(){
        int incidentId = 1;
        Incident incident = Incident.builder().
                incidentId(incidentId)
                .foulType(FoulType.CARELESS)
                .ballIntention(true)
                .build();

        GameState gameState = new GameState(
                incidentId, Location.PENALTY_AREA, Distance.CLOSE, Direction.TOWARDS_GOAL, true, 0, false
        );
        kieSession.insert(gameState);
        kieSession.insert(incident);

        kieSession.getAgenda().getAgendaGroup("DOGSO").setFocus();
        int firedRulesCount = kieSession.fireAllRules();

        Collection<?> recommendations = kieSession.getObjects(o -> o instanceof Recommendation);

        assertEquals(1, recommendations.size(), "Should have only one recommendation.");

        Recommendation rec = (Recommendation) recommendations.iterator().next();

        assertEquals(3, firedRulesCount);
        assertEquals(ProposedAction.YELLOW_CARD, rec.getAction());
        assertTrue(rec.getMessage().contains("DoubleJeopardy activated!"));
    }

    @Test
    public void shouldRecommendRedCardWithoutBallIntention(){
        int incidentId = 1;
        Incident incident = Incident.builder().
                incidentId(incidentId)
                .foulType(FoulType.CARELESS)
                .ballIntention(false)
                .build();

        GameState gameState = new GameState(
                incidentId, Location.PENALTY_AREA, Distance.CLOSE, Direction.TOWARDS_GOAL, true, 0, false
        );
        kieSession.insert(gameState);
        kieSession.insert(incident);

        kieSession.getAgenda().getAgendaGroup("DOGSO").setFocus();
        int firedRulesCount = kieSession.fireAllRules();

        Collection<?> recommendations = kieSession.getObjects(o -> o instanceof Recommendation);

        assertEquals(1, recommendations.size(), "Should have only one recommendation.");

        Recommendation rec = (Recommendation) recommendations.iterator().next();

        assertEquals(2, firedRulesCount);
        assertEquals(ProposedAction.RED_CARD, rec.getAction());
        assertTrue(rec.getMessage().contains("DoubleJeopardy not activated - RED CARD"));
    }

    @Test
    public void shouldRecommendRedCardOutsidePenaltyBox(){
        int incidentId = 1;
        Incident incident = Incident.builder().
                incidentId(incidentId)
                .foulType(FoulType.CARELESS)
                .ballIntention(false)
                .build();

        GameState gameState = new GameState(
                incidentId, Location.OUTSIDE_PENALTY_AREA, Distance.CLOSE, Direction.TOWARDS_GOAL, true, 0, false
        );
        kieSession.insert(gameState);
        kieSession.insert(incident);

        kieSession.getAgenda().getAgendaGroup("DOGSO").setFocus();
        int firedRulesCount = kieSession.fireAllRules();

        Collection<?> recommendations = kieSession.getObjects(o -> o instanceof Recommendation);

        assertEquals(1, recommendations.size(), "Should have only one recommendation.");

        Recommendation rec = (Recommendation) recommendations.iterator().next();

        assertEquals(2, firedRulesCount);
        assertEquals(ProposedAction.RED_CARD, rec.getAction());
        assertTrue(rec.getMessage().contains("DoubleJeopardy not activated - RED CARD"));
    }
}
