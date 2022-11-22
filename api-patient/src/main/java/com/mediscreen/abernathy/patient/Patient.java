package com.mediscreen.abernathy.patient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public Long id;

    public String family;
    public String given;
    public LocalDate dob;
    public String sex;
    public String address;
    public Long phone;

}
