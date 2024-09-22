package com.springboot.patientdemo.controller;

import com.springboot.patientdemo.dto.request.PatientRequest;
import com.springboot.patientdemo.dto.response.PatientResponse;
import com.springboot.patientdemo.service.PatientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    private final PatientServiceImpl patientService;

    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponse>> patients() {
        return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientResponse> patient(@PathVariable int id) {
        return new ResponseEntity<>(patientService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/patients")
    public ResponseEntity<PatientResponse> addPatient(@Valid @RequestBody PatientRequest patientRequest) {
        return new ResponseEntity<>(patientService.createPatient(patientRequest), HttpStatus.CREATED);
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable int id, @Valid @RequestBody PatientRequest patientRequest) {
        return new ResponseEntity<>(patientService.updatePatientById(id,patientRequest), HttpStatus.OK);
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable int id) {
        patientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
