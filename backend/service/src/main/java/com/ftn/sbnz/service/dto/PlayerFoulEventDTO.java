package com.ftn.sbnz.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerFoulEventDTO {

    private String playerId;
    private boolean isContact;

}
