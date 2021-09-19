package com.ds.microservices.citizenservice;

import com.ds.microservices.citizenservice.bean.CitizenBean;
import com.ds.microservices.citizenservice.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class CitizenServiceApplication {

    @Autowired
    private CitizenRepository citizenRepository;

    public static void main(String[] args) {
        SpringApplication.run(CitizenServiceApplication.class, args);
    }

    @PostConstruct
    public void generateCitizenData() {

        Random rand = new Random();
        List<CitizenBean> list = new ArrayList<>();

        for (int x = 0; x < 1000; x++) {
            list.add(new CitizenBean(0, "citizen No " + (x + 1), rand.nextInt(10)+1));
        }
        citizenRepository.saveAll(list);
    }

}
