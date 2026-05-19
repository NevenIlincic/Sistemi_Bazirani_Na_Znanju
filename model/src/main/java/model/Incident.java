package model;

import enums.ContactPoint;
import enums.FoulType;
import enums.HandPosition;
import enums.Intensity;
import enums.TackleControl;
import lombok.Builder;
import lombok.Data;
import lombok.Builder.Default;

@Data
@Builder
public class Incident {
    private boolean isContact;
    private boolean isPlayerDown;
    private boolean isBallContactFirst;
    private boolean isAttackerHandContact;
    private boolean doesHandEnlargesBody;
    private boolean isOpenFoot;
    private boolean isContactFromBehind;
    private boolean isBallItention;
    @Default
    private HandPosition handPosition = HandPosition.NATURAL;
    @Default
    private ContactPoint contactPoint = ContactPoint.LOWER_LEG;
    @Default
    private TackleControl tackleControl = TackleControl.CONTROLLED;
    @Default
    private Intensity intensity = Intensity.LOW;
    @Default
    private FoulType foulType = FoulType.NON_EXISTENT;
    private Player player;
}
