package com.springboot.patientdemo.dao;

import com.springboot.patientdemo.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
