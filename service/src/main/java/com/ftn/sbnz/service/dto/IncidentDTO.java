package com.ftn.sbnz.service.dto;

import enums.ContactPoint;
import enums.FoulType;
import enums.HandPosition;
import enums.TackleControl;
import lombok.Builder;
import lombok.Data;

@Data
public class IncidentDTO {
    private int incidentId;
    private boolean contact;
    private boolean playerDown;
    private boolean ballContactFirst;
    private boolean attackerHandContact;
    private boolean handEnlargesBody;
    private boolean openFoot;
    private boolean contactFromBehind;
    private boolean ballIntention;
    private boolean dangerousPlay;
    private HandPosition handPosition;
    private ContactPoint contactPoint;
    private TackleControl tackleControl;
    private FoulType foulType;
    private int playerJerseyNumber;
}
