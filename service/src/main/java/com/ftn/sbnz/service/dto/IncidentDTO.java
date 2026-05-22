package com.ftn.sbnz.service.dto;

import enums.*;
import lombok.Builder;
import lombok.Data;

@Data
public class IncidentDTO {
    private int incidentId;
    private Boolean contact;
    private Boolean playerDown;
    private Boolean ballContactFirst;
    private Boolean attackerHandContact;
    private Boolean handEnlargesBody;
    private Boolean openFoot;
    private Boolean contactFromBehind;
    private Boolean ballIntention;
    private Boolean dangerousPlay;
    private HandPosition handPosition;
    private ContactPoint contactPoint;
    private TackleControl tackleControl;
    private Intensity intensity;
    private FoulType foulType;
    private int playerJerseyNumber;
}
