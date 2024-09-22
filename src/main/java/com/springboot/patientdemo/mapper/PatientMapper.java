package com.springboot.patientdemo.mapper;

import com.springboot.patientdemo.dto.request.PatientRequest;
import com.springboot.patientdemo.dto.response.PatientResponse;
import com.springboot.patientdemo.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    Patient toPatient(PatientRequest patientRequest);
    PatientResponse toPatientResponse(Patient patient);
    void updatePatient(@MappingTarget Patient patient, PatientRequest patientRequest);
}
