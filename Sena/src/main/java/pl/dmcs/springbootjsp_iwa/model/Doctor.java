package pl.dmcs.springbootjsp_iwa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Doctor {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String speciality;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @JsonManagedReference(value="jsonBackReferenceDoctor")
    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    private Set<Appointment> appointments;

    @JsonManagedReference(value="prescription-doctor")
    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    private Set<Prescription> prescriptions;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
