package pl.dmcs.springbootjsp_iwa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.springbootjsp_iwa.model.Patient;
import pl.dmcs.springbootjsp_iwa.model.Prescription;
import pl.dmcs.springbootjsp_iwa.model.User;
import pl.dmcs.springbootjsp_iwa.repository.PrescriptionRepository;
import pl.dmcs.springbootjsp_iwa.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    private PrescriptionRepository prescriptionRepository;
    private UserRepository userRepository;

    @Autowired
    public PrescriptionController(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @RequestMapping(value = "/findPrescriptions",method = RequestMethod.GET/*, produces = "application/xml"*/)
    //@GetMapping
    public List<Prescription> findAllPrescriptions() { return prescriptionRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    //@PostMapping
    public ResponseEntity<Prescription> addPrescription(@RequestBody Prescription prescription) {

        // Commented out due to simplify http requests sent from angular app
//        if (student.getAddress().getId() <= 0 )
//        {
//            addressRepository.save(student.getAddress());
//        }
        // Commented out due to simplify http requests sent from angular app
        prescriptionRepository.save(prescription);
        return new ResponseEntity<Prescription>(prescription, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    //@DeleteMapping("/{id}")
    public ResponseEntity<Prescription> deletePrescription (@PathVariable("id") long id) {
        Prescription prescription = prescriptionRepository.findById(id);
        if (prescription == null) {
            System.out.println("Patient not found!");
            return new ResponseEntity<Prescription>(HttpStatus.NOT_FOUND);
        }
        prescriptionRepository.deleteById(id);
        return new ResponseEntity<Prescription>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    //@PutMapping("/{id}")
    public ResponseEntity<Prescription> updatePrescription(@RequestBody Prescription prescription, @PathVariable("id") long id) {
        prescription.setId(id);
        prescriptionRepository.save(prescription);
        return new ResponseEntity<Prescription>(prescription, HttpStatus.OK);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Prescription>> getPrescriptionsByPatientId(@PathVariable Long patientId) {
        System.out.println("log in");
        List<Prescription> prescriptions = prescriptionRepository.findByPatientId(patientId);
        System.out.println("log out");
        return new ResponseEntity<>(prescriptions, HttpStatus.OK);

    }
    /*
    @GetMapping("/patient/{id}")
    public ResponseEntity<List<Prescription>> getPrescriptionsByPatient(@PathVariable("id") long id){
        List<Prescription> allPrescriptions = prescriptionRepository.findAll();
        List<Prescription> selectedPrescriptions = new ArrayList<>();

        for(Prescription potentialPrescription : allPrescriptions){
            if(potentialPrescription.getPatient().getUser().getId() == id){
                selectedPrescriptions.add(potentialPrescription);
            }
        }

        if(selectedPrescriptions.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(selectedPrescriptions, HttpStatus.OK);
    }*/


    @RequestMapping(method = RequestMethod.PUT)
//@PutMapping
    public ResponseEntity<List<Prescription>> updateAllPrescriptions(@RequestBody List<Prescription> prescriptions) {
        for (Prescription prescription : prescriptions) {
            prescriptionRepository.save(prescription);
        }
        return new ResponseEntity<List<Prescription>>(prescriptions, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PATCH)
//@PatchMapping("/{id}")
    public ResponseEntity<Prescription> patchPrescriptions(@PathVariable("id") long id, @RequestBody Map<String, Object> updates) {
        Prescription prescription = prescriptionRepository.findById(id);
        if (prescription == null) {
            System.out.println("Prescription not found!");
            return new ResponseEntity<Prescription>(HttpStatus.NOT_FOUND);
        }
        partialUpdate(prescription, updates);
        return new ResponseEntity<Prescription>(prescription, HttpStatus.OK);
    }


    private void partialUpdate(Prescription prescription, Map<String, Object> updates) {
        if (updates.containsKey("medicineName")) {
            prescription.setMedicineName((String) updates.get("medicineName"));
        }
        if (updates.containsKey("dosage")) {
            prescription.setDosage((String) updates.get("dosage"));
        }
        if (updates.containsKey("instructions")) {
            prescription.setInstructions((String) updates.get("instructions"));
        }

        prescriptionRepository.save(prescription);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    //@DeleteMapping("/{id}")
    public ResponseEntity<Prescription> deletePrescriptions() {
        prescriptionRepository.deleteAll();
        return new ResponseEntity<Prescription>(HttpStatus.NO_CONTENT);
    }
}
