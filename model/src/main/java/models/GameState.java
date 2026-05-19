package models;

import enums.Direction;
import enums.Distance;
import enums.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameState {
    private int incidentId;
    private Location location;
    private Distance distanceFromGoal;
    private Direction movingDirection;
    private boolean ballControl;
    private int numDefendersAhead;
    private boolean goalScored;
}
