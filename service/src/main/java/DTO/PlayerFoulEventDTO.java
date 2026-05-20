package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class PlayerFoulEventDTO {

    private int playerJerseyNumber;
    private boolean isContact;

}
