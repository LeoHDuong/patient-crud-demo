package com.springboot.patientdemo.mapper;

import com.springboot.patientdemo.dto.request.PatientRequest;
import com.springboot.patientdemo.dto.response.PatientResponse;
import com.springboot.patientdemo.entity.Patient;

public class PatientMapper {
    public static Patient toPatient(PatientRequest patientRequest) {
        Patient patient = new Patient();
        patient.setFirstName(patientRequest.getFirstName());
        patient.setLastName(patientRequest.getLastName());
        patient.setGender(patientRequest.getGender());
        patient.setAge(patientRequest.getAge());
        patient.setEmail(patientRequest.getEmail());
        patient.setPhoneNumber(patientRequest.getPhoneNumber());
        return patient;
    }

    public static PatientResponse toPatientResponse(Patient patient) {
        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setId(patient.getId());
        patientResponse.setFirstName(patient.getFirstName());
        patientResponse.setLastName(patient.getLastName());
        patientResponse.setGender(patient.getGender());
        patientResponse.setAge(patient.getAge());
        patientResponse.setEmail(patient.getEmail());
        patientResponse.setPhoneNumber(patient.getPhoneNumber());
        return patientResponse;
    }
}
