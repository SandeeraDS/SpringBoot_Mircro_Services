package com.ds.microservices.vaccinationcenterservice.service;

import com.ds.microservices.vaccinationcenterservice.bean.VaccinationCenterBean;
import com.ds.microservices.vaccinationcenterservice.model.Citizen;
import com.ds.microservices.vaccinationcenterservice.model.VaccinationCenterDetails;
import com.ds.microservices.vaccinationcenterservice.repository.VaccinationCenterRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 20)
public class VaccinationCenterService {
    private static final Logger logger = LogManager.getLogger(VaccinationCenterService.class);

    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;

    @Autowired
    private RestTemplate restTemplate;

    public VaccinationCenterBean addVaccinationCenterDetails(VaccinationCenterBean vaccinationCenterBean) {
        logger.info("[VaccinationCenterService.java:29] addVaccinationCenterDetails");
        vaccinationCenterRepository.save(vaccinationCenterBean);
        return vaccinationCenterBean;
    }

    public VaccinationCenterBean getByVaccinationCenterId(int id) {
        logger.info("[VaccinationCenterService.java:35] getByVaccinationCenterId");
        return vaccinationCenterRepository.findById(id);
    }

    public List<VaccinationCenterBean> getAllVaccinationCenters() {
        logger.info("[VaccinationCenterService.java:40] getAllVaccinationCenters");
        return vaccinationCenterRepository.findAll();
    }

    @HystrixCommand(fallbackMethod = "handleCitizenDownTime")
    public VaccinationCenterDetails getVaccinationCenterWithCitizenDetails(int vaccinationCenterId) {
        logger.info("[VaccinationCenterService.java:45] getVaccinationCenterWithCitizenDetails");
        VaccinationCenterDetails vaccinationCenterDetails = new VaccinationCenterDetails();
        VaccinationCenterBean vaccinationCenterBean = vaccinationCenterRepository.findById(vaccinationCenterId);
        logger.info("[VaccinationCenterService.java:48] vaccination center info: {}", vaccinationCenterBean);
        vaccinationCenterDetails.setVaccinationCenter(vaccinationCenterBean);

        logger.info("[VaccinationCenterService.java:51] get citizen details");
        List<Citizen> listOfCitizens = restTemplate.getForObject("http://CITIZEN-SERVICE/citizenService/CitizensByVaccinationCenter/" + vaccinationCenterId, List.class);
        logger.info("[VaccinationCenterService.java:53] citizens list data size of: {} and info : {}", listOfCitizens.size(), listOfCitizens);
        vaccinationCenterDetails.setCitizens(listOfCitizens);

        return vaccinationCenterDetails;
    }

    private VaccinationCenterDetails handleCitizenDownTime(int vaccinationCenterId) {
        logger.info("[VaccinationCenterService.java:45] handleCitizenDownTime");
        logger.info("citizen service down. so start sending only vaccination center info to client");
        VaccinationCenterDetails vaccinationCenterDetails = new VaccinationCenterDetails();
        VaccinationCenterBean vaccinationCenterBean = vaccinationCenterRepository.findById(vaccinationCenterId);
        logger.info("[VaccinationCenterService.java:48] vaccination center info: {}", vaccinationCenterBean);
        vaccinationCenterDetails.setVaccinationCenter(vaccinationCenterBean);

        return vaccinationCenterDetails;
    }
}
