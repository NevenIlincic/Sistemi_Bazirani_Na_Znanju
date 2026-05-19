package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private int playerJerseyNumber;
    private boolean hasYellowCard;
    private int numSoftFouls;
    private boolean isAgressive;
    private boolean hasIncreasedDisciplinaryRisk;
    private int disciplinaryAssessmentIntensity;
}
