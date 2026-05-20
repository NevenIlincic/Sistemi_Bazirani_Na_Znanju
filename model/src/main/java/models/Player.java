package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {
    private int playerJerseyNumber;
    private boolean yellowCard;
    private boolean aggressive;
    private boolean increasedDisciplinaryRisk;
    @Default
    private int numSoftFouls = 0;
    @Default
    private int disciplinaryAssessmentIntensity = 0;


}
