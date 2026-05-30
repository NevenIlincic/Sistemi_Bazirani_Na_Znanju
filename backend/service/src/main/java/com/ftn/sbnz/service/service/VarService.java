package com.ftn.sbnz.service.service;

import com.ftn.sbnz.service.dto.IncidentDTO;
import com.ftn.sbnz.service.dto.RecommendationDTO;
import com.ftn.sbnz.service.dto.VarRequestDTO;
import enums.Intensity;
import enums.Location;
import models.GameState;
import models.Incident;
import models.PenaltyCriteria;
import models.Recommendation;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.QueryResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class VarService {

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private KieSession kieSession;

    public List<RecommendationDTO> getIncidentRecommendation(@RequestBody VarRequestDTO varRequestDTO) {
        Set<String> firedRules = new HashSet<>();
        kieSession.addEventListener(new org.kie.api.event.rule.DefaultAgendaEventListener() {
            @Override
            public void afterMatchFired(org.kie.api.event.rule.AfterMatchFiredEvent event) {
                firedRules.add(event.getMatch().getRule().getName());
            }
        });


        Incident incident = this.buildIncidentObject(varRequestDTO.getIncident());
        GameState gameState = new GameState();
        gameState.setIncidentId(varRequestDTO.getIncident().getIncidentId());
        gameState.setLocation(varRequestDTO.getGameState().getLocation());
        gameState.setDistanceFromGoal(varRequestDTO.getGameState().getDistanceFromGoal());
        gameState.setMovingDirection(varRequestDTO.getGameState().getMovingDirection());
        gameState.setBallControl(varRequestDTO.getGameState().isBallControl());
        gameState.setNumDefendersAhead(varRequestDTO.getGameState().getNumDefendersAhead());
        gameState.setGoalScored(varRequestDTO.getGameState().isGoalScored());

        kieSession.insert(incident);
        kieSession.insert(gameState);


        kieSession.getAgenda().getAgendaGroup("CLEANUP").setFocus();
        kieSession.getAgenda().getAgendaGroup("FINAL-DECISION").setFocus();
        kieSession.getAgenda().getAgendaGroup("DOGSO").setFocus();
        kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();

        kieSession.fireAllRules();

        for (String rule: firedRules){
            System.out.println("Aktivirano pravilo: " + rule);
        }
        //printFactsInSession();
        Collection<?> objects = kieSession.getObjects(o -> o instanceof Recommendation);
        List<RecommendationDTO> allRecommendationsDTOs = new ArrayList<>();
        for (Object object: objects){
            Recommendation recommendation = (Recommendation) object;
            allRecommendationsDTOs.add(new RecommendationDTO(recommendation));
            kieSession.delete(kieSession.getFactHandle(recommendation));
        }

        return allRecommendationsDTOs;

    }
    public void checkIsPenalty(VarRequestDTO varRequestDTO) {
        String contact = "YES";
        String contactIntensity = "LOW";
        String dangerousIntensity = varRequestDTO.getIncident().getIntensity().toString();
        String ballControl = "YES";
        String ballContactFirst = "YES";
        String openFoot = "NO";
        if (!varRequestDTO.getIncident().getContact()){
            contact = "NO";
        }
        if (varRequestDTO.getIncident().getIntensity() != Intensity.LOW){
            contactIntensity = "MEDIUM_HIGH";
        }
        if (!varRequestDTO.getGameState().isBallControl()){
            ballControl = "NO";
        }
        if (varRequestDTO.getIncident().getBallContactFirst() != null && !varRequestDTO.getIncident().getBallContactFirst()){
            ballContactFirst = "NO";
        }
        if (varRequestDTO.getIncident().getOpenFoot() != null && varRequestDTO.getIncident().getOpenFoot()){
            openFoot = "YES";
        }
        this.kieSession.insert(new PenaltyCriteria("PENALTY", null, null, null, "AND"));
        kieSession.insert(new PenaltyCriteria("FOUL_CRITERIA", null, null, "PENALTY", "OR"));
        kieSession.insert(new PenaltyCriteria("LOCATION", varRequestDTO.getGameState().getLocation().toString(), "PENALTY_AREA", "PENALTY", null));
        kieSession.insert(new PenaltyCriteria("CONTACT_FOUL", null, null, "FOUL_CRITERIA", "AND"));
        kieSession.insert(new PenaltyCriteria("DANGEROUS_PLAY", null, null, "FOUL_CRITERIA", "AND"));
        kieSession.insert(new PenaltyCriteria("CONTACT", contact, "YES", "CONTACT_FOUL", null));
        kieSession.insert(new PenaltyCriteria("CONTACT_INTENSITY", contactIntensity, "MEDIUM_HIGH", "CONTACT_FOUL", null));
        kieSession.insert(new PenaltyCriteria("BALL_CONTROL", ballControl, "YES", "CONTACT_FOUL", null));
        kieSession.insert(new PenaltyCriteria("BALL_CONTACT_FIRST", ballContactFirst, "NO", "CONTACT_FOUL", null));

        kieSession.insert(new PenaltyCriteria("OPEN_FOOT", openFoot, "YES", "DANGEROUS_PLAY", null));
        kieSession.insert(new PenaltyCriteria("DANGEROUS_INTENSITY", dangerousIntensity, "HIGH", "DANGEROUS_PLAY", null));
        // Pozivaš upit
        QueryResults results = kieSession.getQueryResults("isSatisfied", "PENALTY");

        if (results.size() > 0) {
            System.out.println("Penal je potvrđen na osnovu svih uslova!");
        }else{
            System.out.println("Nije penal!");
        }
        Collection<FactHandle> handles = kieSession.getFactHandles(obj -> obj instanceof PenaltyCriteria);
        for (FactHandle handle : handles) {
            kieSession.delete(handle);
        }
    }

    private Incident buildIncidentObject(IncidentDTO incidentDTO) {
        var builder = Incident.builder();
        builder.incidentId(incidentDTO.getIncidentId());
        builder.playerId(incidentDTO.getPlayerId());
        if (incidentDTO.getContact() != null) {builder.contact(incidentDTO.getContact());}
        if (incidentDTO.getPlayerDown() != null) {builder.playerDown(incidentDTO.getPlayerDown());}
        if (incidentDTO.getBallContactFirst() != null) {builder.ballContactFirst(incidentDTO.getBallContactFirst());}
        if (incidentDTO.getAttackerHandContact() != null) {builder.attackerHandContact(incidentDTO.getAttackerHandContact());}
        if (incidentDTO.getHandEnlargesBody() != null){builder.handEnlargesBody(incidentDTO.getHandEnlargesBody());}
        if (incidentDTO.getOpenFoot() != null) {builder.openFoot(incidentDTO.getOpenFoot());}
        if (incidentDTO.getContactFromBehind() != null) {builder.contactFromBehind(incidentDTO.getContactFromBehind());}
        if (incidentDTO.getBallIntention() != null) {builder.ballIntention(incidentDTO.getBallIntention());}
        if (incidentDTO.getDangerousPlay() != null) {builder.dangerousPlay(incidentDTO.getDangerousPlay());}
        if (incidentDTO.getHandPosition() != null) {builder.handPosition(incidentDTO.getHandPosition());}
        if (incidentDTO.getContactPoint() != null) {builder.contactPoint(incidentDTO.getContactPoint());}
        if (incidentDTO.getTackleControl() != null) {builder.tackleControl(incidentDTO.getTackleControl());}
        if (incidentDTO.getFoulType() != null) {builder.foulType(incidentDTO.getFoulType());}
        if (incidentDTO.getIntensity() != null) {builder.intensity(incidentDTO.getIntensity());}

        return builder.build();
    }
}
