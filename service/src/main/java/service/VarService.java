package service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VarService {

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private KieSession kieSession;

}
