package com.ds.microservices.vaccinationcenterservice.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Citizen {


    private int id;

    private String name;

    private int vaccinationCenterId;


}
