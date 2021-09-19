package com.ds.microservices.vaccinationcenterservice.model;

import com.ds.microservices.vaccinationcenterservice.bean.VaccinationCenterBean;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VaccinationCenterDetails {

    private VaccinationCenterBean vaccinationCenter;

    private List<Citizen> citizens;
}
