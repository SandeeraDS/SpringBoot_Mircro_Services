package com.ds.microservices.citizenservice.repository;

import com.ds.microservices.citizenservice.bean.CitizenBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitizenRepository extends JpaRepository<CitizenBean, Integer> {

    List<CitizenBean> findByVaccinationCenterId(int vaccinationCenterId);

    CitizenBean findById(int id);

}
