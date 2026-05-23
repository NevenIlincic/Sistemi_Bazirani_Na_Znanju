package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kie.api.definition.type.PropertyReactive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PropertyReactive
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
