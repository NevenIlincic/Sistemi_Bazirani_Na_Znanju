package com.ftn.sbnz.service.dto;

import enums.Intensity;
import enums.Location;
import lombok.Data;

@Data
public class PenaltyCheckDTO {

    private boolean contact;
    private Intensity intensity;
    private boolean ballControl;
    private boolean ballContactFirst;
    private boolean openFoot;
    private Location location;
}
