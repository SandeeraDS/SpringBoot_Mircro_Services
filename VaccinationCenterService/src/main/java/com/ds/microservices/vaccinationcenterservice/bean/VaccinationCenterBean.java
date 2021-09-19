package com.ds.microservices.vaccinationcenterservice.bean;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "VACCINATION_CENTER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VaccinationCenterBean {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "CENTER_NAME")
    private String centerName;
    @Column(name = "CENTER_ADDRESS")
    private String centerAddress;

}
