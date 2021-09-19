package com.ds.microservices.citizenservice.controller;

import com.ds.microservices.citizenservice.bean.CitizenBean;
import com.ds.microservices.citizenservice.service.CitizenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citizenService")
public class CitizenController {
    private static final Logger logger = LogManager.getLogger(CitizenController.class);

    @Autowired
    private CitizenService citizenService;


    @GetMapping("/citizen/{id}")
    public CitizenBean getCitizenById(@PathVariable int id) {
        logger.info("[CitizenController.java:23] get citizen by id: {}", id);
        return citizenService.findByCitizenId(id);
    }

    @GetMapping("/CitizensByVaccinationCenter/{vaccinationCenterId}")
    public List<CitizenBean> getCitizensByVaccinationCenterId(@PathVariable int vaccinationCenterId) {
        logger.info("[CitizenController.java:29] get list of registered citizens by vaccination center id: {}", vaccinationCenterId);
        return citizenService.getCitizenByVaccinationCenterId(vaccinationCenterId);
    }

    @PostMapping("/AddVaccinatedCitizen")
    public CitizenBean addVaccinatedCitizen(@RequestBody CitizenBean citizenBean) {
        logger.info("[CitizenController.java:35] add new vaccinated citizen: {}", citizenBean);
        citizenService.addVaccinatedCitizen(citizenBean);
        return citizenBean;
    }

}
