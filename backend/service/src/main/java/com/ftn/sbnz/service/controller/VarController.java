package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.service.dto.RecommendationDTO;
import com.ftn.sbnz.service.dto.VarRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ftn.sbnz.service.service.VarService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/var")
@CrossOrigin("*")
public class VarController {

    @Autowired
    private VarService varService;

    @PostMapping(value = "/recommendations", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RecommendationDTO>> getIncidentRecommendation(@RequestBody VarRequestDTO varRequestDTO) {
        System.out.println(varRequestDTO);
        List<RecommendationDTO> recommendationsDTO = this.varService.getIncidentRecommendation(varRequestDTO);
        return new ResponseEntity<>(recommendationsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/penalty-check", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPenaltyCheck(@RequestBody VarRequestDTO varRequestDTO) {
        this.varService.checkIsPenalty(varRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
