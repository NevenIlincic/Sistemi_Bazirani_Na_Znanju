package com.ftn.sbnz.service.dto;

import enums.Direction;
import enums.Distance;
import enums.Location;
import lombok.Data;

@Data
public class GameStateDTO {

    private Location location;
    private Distance distanceFromGoal;
    private Direction movingDirection;
    private boolean ballControl;
    private int numDefendersAhead;
    private boolean goalScored;
}
