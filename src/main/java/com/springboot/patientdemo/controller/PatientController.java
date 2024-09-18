package com.springboot.patientdemo.controller;

import com.springboot.patientdemo.entity.Patient;
import com.springboot.patientdemo.service.PatientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    private PatientServiceImpl patientService;

    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> patients() {
        List<Patient> patients = patientService.findAll();
        if (patients.isEmpty()) {
            throw new RuntimeException("No patients found");
        }
        return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> patient(@PathVariable int id) {
        return new ResponseEntity<>(patientService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/patients")
    public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient patient) {
        return new ResponseEntity<>(patientService.save(patient), HttpStatus.CREATED);
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable int id, @Valid @RequestBody Patient updatedPatient) {
        Patient existingPatient = patientService.findById(id);

        if (existingPatient == null) {
            throw new RuntimeException("No patient found with id " + id);
        }

        existingPatient.setFirstName(updatedPatient.getFirstName());
        existingPatient.setLastName(updatedPatient.getLastName());
        existingPatient.setGender(updatedPatient.getGender());
        existingPatient.setAge(updatedPatient.getAge());
        existingPatient.setEmail(updatedPatient.getEmail());
        existingPatient.setPhoneNumber(updatedPatient.getPhoneNumber());

        return new ResponseEntity<>(patientService.save(existingPatient), HttpStatus.OK);
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable int id) {
        patientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
