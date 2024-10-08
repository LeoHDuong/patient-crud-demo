package com.springboot.patientdemo.mapper;

import com.springboot.dto.PatientRequest;
import com.springboot.dto.PatientResponse;
import com.springboot.patientdemo.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    Patient toPatient (PatientRequest patientRequest);
    PatientResponse toPatientResponse(Patient patient);
    void updatePatient(@MappingTarget Patient patient, PatientRequest patientRequest);
}
