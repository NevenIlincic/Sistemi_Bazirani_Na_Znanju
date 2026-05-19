package com.ftn.sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/var")
public class VarController {

    @Autowired
    private VarService varService;

//    @GetMapping(value = "/get_incident", consumes = MediaType.APPLICATION_JSON_VALUE)

}
