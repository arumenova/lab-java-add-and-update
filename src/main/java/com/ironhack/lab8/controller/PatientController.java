package com.ironhack.lab8.controller;

import com.ironhack.lab8.model.Patient;
import com.ironhack.lab8.repository.PatientRepository;
import com.ironhack.lab8.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/patients")

public class PatientController {

    private final PatientService patientService;
    private final PatientRepository patientRepository;

    public PatientController(PatientService patientService, PatientRepository patientRepository) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;

    }

    @PostMapping("/populate")
    public ResponseEntity<List<Patient>> populatePatients() {
        List<Patient> patients = patientService.setPatients();
        return new ResponseEntity<>(patients, HttpStatus.CREATED);
    }

    @PostMapping("/set")
    public ResponseEntity<List<Patient>> setPatients() {
        List<Patient> patients = patientService.setPatients();
        return new ResponseEntity<>(patients, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Patient>> getPatients() {
        List<Patient> patients = patientService.getPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping("/dob/{dateOfBirth}")
    public ResponseEntity<List<Patient>> getBydateOfBirth(@PathVariable String dateOfBirth) {
        LocalDate dob = LocalDate.parse(dateOfBirth);
        List<Patient> patients = patientService.getByDateOfBirth(dob);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/admittedBy/{admittedBy}")
    public ResponseEntity<List<Patient>> getByAdmittedBy(@PathVariable int admittedBy) {
        List<Patient> patients = patientService.getByAdmittedBy(admittedBy);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/doctorsStatus/{status}")
    public ResponseEntity<List<Patient>> getPatientsByDoctorsStatus(@PathVariable String status) {
        List<Patient> patients = patientService.getPatientsByDoctorsStatus(status);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
 public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient patient1=patientService.createPatient(patient);
        return new ResponseEntity<>(patient1, HttpStatus.CREATED);
    }

@PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        Patient patient1=patientService.updatePatient(updatedPatient,id);
        return new ResponseEntity<>(patient1, HttpStatus.OK);
}

}
