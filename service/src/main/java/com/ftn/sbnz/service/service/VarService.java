package com.ftn.sbnz.service.service;

import com.ftn.sbnz.service.dto.RecommendationDTO;
import com.ftn.sbnz.service.dto.VarRequestDTO;
import models.GameState;
import models.Incident;
import models.Recommendation;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class VarService {

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private KieSession kieSession;

    public List<RecommendationDTO> getIncidentRecommendation(@RequestBody VarRequestDTO varRequestDTO) {
        kieSession.addEventListener(new org.kie.api.event.rule.DefaultAgendaEventListener() {
            @Override
            public void afterMatchFired(org.kie.api.event.rule.AfterMatchFiredEvent event) {
                System.out.println(">>> Okinuto pravilo: " + event.getMatch().getRule().getName());
            }
        });
        Incident incident = Incident.builder()
                .incidentId(varRequestDTO.getIncident().getIncidentId())
                .playerJerseyNumber(varRequestDTO.getIncident().getPlayerJerseyNumber())
                .contact(varRequestDTO.getIncident().isContact())
                .playerDown(varRequestDTO.getIncident().isPlayerDown())
                .ballContactFirst(varRequestDTO.getIncident().isBallContactFirst())
                .attackerHandContact(varRequestDTO.getIncident().isAttackerHandContact())
                .handEnlargesBody(varRequestDTO.getIncident().isHandEnlargesBody())
                .openFoot(varRequestDTO.getIncident().isOpenFoot())
                .contactFromBehind(varRequestDTO.getIncident().isContactFromBehind())
                .ballIntention(varRequestDTO.getIncident().isBallIntention())
                .dangerousPlay(varRequestDTO.getIncident().isDangerousPlay())
                .handPosition(varRequestDTO.getIncident().getHandPosition())
                .contactPoint(varRequestDTO.getIncident().getContactPoint())
                .tackleControl(varRequestDTO.getIncident().getTackleControl())
                .foulType(varRequestDTO.getIncident().getFoulType())
                .build();

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

        kieSession.getAgenda().getAgendaGroup("MAIN").setFocus();
        kieSession.fireAllRules();
        kieSession.getAgenda().getAgendaGroup("DOGSO").setFocus();
        kieSession.fireAllRules();
        kieSession.getAgenda().getAgendaGroup("FINAL-DECISION").setFocus();
        kieSession.fireAllRules();

        //printFactsInSession();
        Collection<Recommendation> objects = (Collection<Recommendation>) kieSession.getObjects(o -> o instanceof Recommendation);
        List<Recommendation> allRecommendations = new ArrayList<>(objects);
        List<RecommendationDTO> allRecommendationDTOs = new ArrayList<>(allRecommendations.size());
        for (Recommendation recommendation: allRecommendations){
            allRecommendationDTOs.add(new RecommendationDTO(recommendation));
            kieSession.delete(kieSession.getFactHandle(recommendation));
        }

        return allRecommendationDTOs;

    }

    public void printFactsInSession() {
        org.kie.api.definition.KiePackage kp = kieSession.getKieBase().getKiePackage("rules");

        if (kp == null) {
            System.out.println("Paket 'rules' nije pronađen!");
            return;
        }

        // 1. Nađemo FactType bez lambdi
        org.kie.api.definition.type.FactType foundType = null;
        for (org.kie.api.definition.type.FactType ft : kp.getFactTypes()) {
            if (ft.getSimpleName().equals("UnsportsmanlikeConduct")) {
                foundType = ft;
                break;
            }
        }

        if (foundType != null) {
            // 2. Umesto lambde, koristimo kieSession.getObjects() bez filtera
            // i ručno proveravamo tip objekta u petlji. Ovo je 100% bezbedno.
            int count = 0;
            for (Object fact : kieSession.getObjects()) {
                if (foundType.getFactClass().isInstance(fact)) {
                    count++;
                    int jersey = (int) foundType.get(fact, "playerJerseyNumber");
                    System.out.println("Pronađen UnsportsmanlikeConduct za igrača broj: " + jersey);
                }
            }
            System.out.println("Broj UnsportsmanlikeConduct objekata u memoriji: " + count);
        } else {
            System.out.println("FactType 'UnsportsmanlikeConduct' nije pronađen!");
        }
    }
}
