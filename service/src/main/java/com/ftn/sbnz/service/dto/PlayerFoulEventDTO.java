package com.ftn.sbnz.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerFoulEventDTO {

    private int playerJerseyNumber;
    private boolean isContact;

}
