package models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
public class PlayerFoulEvent {
    private int playerJerseyNumber;
    private Date timestamp;
    private boolean contact;

//    private boolean consecutive;


//    @Override
//    public boolean equals(Object o) {
//        if (o == null){
//            return false;
//        }
//        if (getClass() != o.getClass()){
//            return false;
//        }
//        PlayerFoulEvent playerFoulEvent = (PlayerFoulEvent) o;
//        if (this.playerJerseyNumber != playerFoulEvent.playerJerseyNumber){ return false; }
//
//        if (!this.timestamp.equals(playerFoulEvent.timestamp)){ return false; }
//        if (this.contact != playerFoulEvent.contact){ return false; }
//        if (this.consecutive != playerFoulEvent.consecutive){ return false; }
//        return true;
//    }
}

