package com.ftn.sbnz.service.controller;


import com.ftn.sbnz.service.dto.PlayerDTO;
import com.ftn.sbnz.service.dto.PlayerFoulEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ftn.sbnz.service.service.MatchEventService;

import java.util.List;

@RestController
@RequestMapping("/api/match-event")
@CrossOrigin("*")
public class MatchEventController {

    @Autowired
    private MatchEventService matchEventService;

    @PostMapping(value = "/small-foul", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> noteSmallFoul(@RequestBody PlayerFoulEventDTO playerFoulEventDTO) {
        this.matchEventService.recordSmallIncident(playerFoulEventDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/advance-time")
    public ResponseEntity<?> advanceTime() {
        this.matchEventService.advanceClockTime();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/players", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> insertPlayers(@RequestBody List<PlayerDTO> playersDTO) {
        this.matchEventService.insertPlayers(playersDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
