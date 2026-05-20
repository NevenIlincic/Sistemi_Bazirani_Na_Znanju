package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.service.dto.RecommendationDTO;
import com.ftn.sbnz.service.dto.VarRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ftn.sbnz.service.service.VarService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/var")
public class VarController {

    @Autowired
    private VarService varService;

    @PostMapping(value = "/get-incident", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecommendationDTO> getIncidentRecommendation(@RequestBody VarRequestDTO varRequestDTO) {
        RecommendationDTO recommendationDTO = this.varService.getIncidentRecommendation(varRequestDTO);
        return new ResponseEntity<>(recommendationDTO, HttpStatus.OK);
    }

}
