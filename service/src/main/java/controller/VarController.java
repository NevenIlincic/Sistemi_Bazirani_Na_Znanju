package controller;

import service.VarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/var")
public class VarController {

    @Autowired
    private VarService varService;

//    @GetMapping(value = "/get_incident", consumes = MediaType.APPLICATION_JSON_VALUE)

}
