package com.ds.microservices.vaccinationcenterservice.repository;

import com.ds.microservices.vaccinationcenterservice.bean.VaccinationCenterBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenterBean, Integer> {
    VaccinationCenterBean findById(int id);

}
