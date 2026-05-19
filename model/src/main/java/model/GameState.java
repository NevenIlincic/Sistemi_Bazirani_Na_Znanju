package model;

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
    private Location location;
    private Distance distanceFromGoal;
    private Direction movingDirection;
    private boolean hasBallControll;
    private int numDefendersAhead;
    private boolean isGoalScored;
}
