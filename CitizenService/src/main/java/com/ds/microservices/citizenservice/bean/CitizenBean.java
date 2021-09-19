package com.ds.microservices.citizenservice.bean;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CITIZEN")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CitizenBean implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VACCINATION_CENTER_ID")
    private int vaccinationCenterId;


}
