package pl.dmcs.springbootjsp_iwa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.springbootjsp_iwa.model.Patient;
import pl.dmcs.springbootjsp_iwa.repository.PatientRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private PatientRepository patientRepository;

    @Autowired
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @RequestMapping(method = RequestMethod.GET/*, produces = "application/xml"*/)
    //@GetMapping
    public List<Patient> findAllPatients() { return patientRepository.findAll();
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    //@PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {

        // Commented out due to simplify http requests sent from angular app
//        if (student.getAddress().getId() <= 0 )
//        {
//            addressRepository.save(student.getAddress());
//        }
        // Commented out due to simplify http requests sent from angular app
        patientRepository.save(patient);
        return new ResponseEntity<Patient>(patient, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    //@DeleteMapping("/{id}")
    public ResponseEntity<Patient> deletePatient (@PathVariable("id") long id) {
        Patient patient = patientRepository.findById(id);
        if (patient == null) {
            System.out.println("Patient not found!");
            return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
        }
        patientRepository.deleteById(id);
        return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    //@PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient, @PathVariable("id") long id) {
        patient.setId(id);
        patientRepository.save(patient);
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }

    @GetMapping("/{id}/patient")
    public ResponseEntity<Patient> getPatientByUserId(@PathVariable("id") long userId) {
        Patient patient = patientRepository.findByUserId(userId);
        if (patient != null) {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @RequestMapping(method = RequestMethod.PUT)
//@PutMapping
    public ResponseEntity<List<Patient>> updateAllPatients(@RequestBody List<Patient> patients) {
        for (Patient patient : patients) {
            patientRepository.save(patient);
        }
        return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PATCH)
//@PatchMapping("/{id}")
    public ResponseEntity<Patient> patchPatients(@PathVariable("id") long id, @RequestBody Map<String, Object> updates) {
        Patient patient = patientRepository.findById(id);
        if (patient == null) {
            System.out.println("Patient not found!");
            return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
        }
        partialUpdate(patient, updates);
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }


    private void partialUpdate(Patient patient, Map<String, Object> updates) {
        if (updates.containsKey("name")) {
            patient.setName((String) updates.get("name"));
        }
        if (updates.containsKey("age")) {
            patient.setAge((String) updates.get("age"));
        }

        patientRepository.save(patient);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    //@DeleteMapping("/{id}")
    public ResponseEntity<Patient> deletePatients() {
        patientRepository.deleteAll();
        return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
    }
}
