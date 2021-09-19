package com.ds.microservices.vaccinationcenterservice;

import com.ds.microservices.vaccinationcenterservice.bean.VaccinationCenterBean;
import com.ds.microservices.vaccinationcenterservice.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class VaccinationCenterServiceApplication {

    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;

    public static void main(String[] args) {
        SpringApplication.run(VaccinationCenterServiceApplication.class, args);
    }


    @PostConstruct
    public void generateVaccinationCenterData() {
        Random rand = new Random();
        List<VaccinationCenterBean> list = new ArrayList<>();

        for (int x = 0; x < 10; x++) {
            list.add(new VaccinationCenterBean(0, "center " + (x + 1), "address No " + (rand.nextInt(1000) + 1000)));
        }
        vaccinationCenterRepository.saveAll(list);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
