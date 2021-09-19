package com.ds.microservices.citizenservice.service;

import com.ds.microservices.citizenservice.bean.CitizenBean;
import com.ds.microservices.citizenservice.controller.CitizenController;
import com.ds.microservices.citizenservice.repository.CitizenRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, timeout = 20)
public class CitizenService {
    private static final Logger logger = LogManager.getLogger(CitizenService.class);

    private final CitizenRepository citizenRepository;

    public CitizenService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    public CitizenBean findByCitizenId(int citizenId) {
        logger.info("[CitizenService.java:26] findByCitizenId");
        return citizenRepository.findById(citizenId);
    }

    public List<CitizenBean> getCitizenByVaccinationCenterId(int vaccinationCenterId) {
        logger.info("[CitizenService.java:31] getCitizenByVaccinationCenterId");
        return citizenRepository.findByVaccinationCenterId(vaccinationCenterId);
    }

    public void addVaccinatedCitizen(CitizenBean citizenBean) {
        logger.info("[CitizenService.java:36] addVaccinatedCitizen");
        citizenRepository.save(citizenBean);
    }
}
