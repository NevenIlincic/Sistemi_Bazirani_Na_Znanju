package model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerFoulEvent {
    private int playerJerseyNumber;
    private Date timestamp;
    private boolean isContact;
    private boolean isConsecutive;
}
