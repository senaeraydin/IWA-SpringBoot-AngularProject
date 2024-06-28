package pl.dmcs.springbootjsp_iwa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.springbootjsp_iwa.model.Appointment;
import pl.dmcs.springbootjsp_iwa.repository.AppointmentRepository;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api/appointments")
public class AppointmentController {
    private AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @RequestMapping(method = RequestMethod.GET/*, produces = "application/xml"*/)
    //@GetMapping
    public List<Appointment> getAppointments() { return appointmentRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    //@PostMapping
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {

        // Commented out due to simplify http requests sent from angular app
//        if (student.getAddress().getId() <= 0 )
//        {
//            addressRepository.save(student.getAddress());
//        }
        // Commented out due to simplify http requests sent from angular app
        appointmentRepository.save(appointment);
        return new ResponseEntity<Appointment>(appointment, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    //@DeleteMapping("/{id}")
    public ResponseEntity<Appointment> deleteAppointment (@PathVariable("id") long id) {
        Appointment appointment = appointmentRepository.findById(id);
        if (appointment == null) {
            System.out.println("Appointment not found!");
            return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
        }
        appointmentRepository.deleteById(id);
        return new ResponseEntity<Appointment>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    //@PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment, @PathVariable("id") long id) {
        appointment.setId(id);
        appointmentRepository.save(appointment);
        return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
//@PutMapping
    public ResponseEntity<List<Appointment>> updateAllAppointments(@RequestBody List<Appointment> appointments) {
        for (Appointment appointment : appointments) {
            appointmentRepository.save(appointment);
        }
        return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PATCH)
//@PatchMapping("/{id}")
    public ResponseEntity<Appointment> patchAppointments(@PathVariable("id") long id, @RequestBody Map<String, Object> updates) {
        Appointment appointment = appointmentRepository.findById(id);
        if (appointment == null) {
            System.out.println("Appointment not found!");
            return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
        }
        partialUpdate(appointment, updates);
        return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
    }
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable Long patientId) {
        System.out.println("enter");
        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
        System.out.println("pre return");
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    private void partialUpdate(Appointment appointment, Map<String, Object> updates) {
        if (updates.containsKey("date")) {
            appointment.setDate((String) updates.get("date"));
        }
        if (updates.containsKey("time")) {
            appointment.setTime((String) updates.get("time"));
        }
        if (updates.containsKey("description")) {
            appointment.setDescription((String) updates.get("description"));
        }

        appointmentRepository.save(appointment);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    //@DeleteMapping("/{id}")
    public ResponseEntity<Appointment> deleteAppointments() {
        appointmentRepository.deleteAll();
        return new ResponseEntity<Appointment>(HttpStatus.NO_CONTENT);
    }
}
