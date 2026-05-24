package models;

import enums.ContactPoint;
import enums.FoulType;
import enums.HandPosition;
import enums.Intensity;
import enums.TackleControl;
import lombok.Builder;
import lombok.Data;
import lombok.Builder.Default;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.util.Date;

@Data
@Builder
@Role(Role.Type.EVENT)
@Timestamp("timestamp")
public class Incident {
    private int incidentId;
    private String playerId;
    private boolean contact;
    private boolean playerDown;
    private boolean ballContactFirst;
    private boolean attackerHandContact;
    private boolean handEnlargesBody;
    private boolean openFoot;
    private boolean contactFromBehind;
    private boolean ballIntention;
    private boolean dangerousPlay;

    @Default
    private Date timestamp = new Date();
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


}
