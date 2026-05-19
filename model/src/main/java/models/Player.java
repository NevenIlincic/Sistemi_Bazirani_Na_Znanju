package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private int playerJerseyNumber;
    private boolean yellowCard;
    private int numSoftFouls;
    private boolean aggressive;
    private boolean increasedDisciplinaryRisk;
    private int disciplinaryAssessmentIntensity;
}
