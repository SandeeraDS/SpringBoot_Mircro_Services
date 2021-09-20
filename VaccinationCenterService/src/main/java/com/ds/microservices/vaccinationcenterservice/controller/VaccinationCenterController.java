package com.ds.microservices.vaccinationcenterservice.controller;

import com.ds.microservices.vaccinationcenterservice.bean.VaccinationCenterBean;
import com.ds.microservices.vaccinationcenterservice.model.VaccinationCenterDetails;
import com.ds.microservices.vaccinationcenterservice.service.VaccinationCenterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccinationCenterService")
public class VaccinationCenterController {
    private static final Logger logger = LogManager.getLogger(VaccinationCenterController.class);

    @Autowired
    private VaccinationCenterService vaccinationCenterService;

    @GetMapping("/GetVaccinationCenter/{id}")
    public VaccinationCenterBean getVaccinationCenterById(@PathVariable int id) {
        logger.info("[VaccinationCenterController.java:25] get vaccination center details by id: {}", id);
        return vaccinationCenterService.getByVaccinationCenterId(id);
    }

    @GetMapping("/GetAllVaccinationCenters")
    public List<VaccinationCenterBean> getAllVaccinationCenters() {
        logger.info("[VaccinationCenterController.java:31] get all vaccination centers");
        return vaccinationCenterService.getAllVaccinationCenters();
    }


    @PostMapping("/AddVaccinationCenter")
    public VaccinationCenterBean addVaccinationCenter(@RequestBody VaccinationCenterBean vaccinationCenterBean) {
        logger.info("[VaccinationCenterController.java:38] add vaccination center: {}", vaccinationCenterBean);
        return vaccinationCenterService.addVaccinationCenterDetails(vaccinationCenterBean);
    }

    @GetMapping("/GetVaccinationCenterDetails/{id}")
    public ResponseEntity<VaccinationCenterDetails> getVaccinationCenterDetailsByVaccinationCenterId(@PathVariable int id) {
        logger.info("[VaccinationCenterController.java:44] get vaccination details with registered citizen list for vaccination id: {}", id);
        return new ResponseEntity<>(vaccinationCenterService.getVaccinationCenterWithCitizenDetails(id), HttpStatus.OK);
    }
}
