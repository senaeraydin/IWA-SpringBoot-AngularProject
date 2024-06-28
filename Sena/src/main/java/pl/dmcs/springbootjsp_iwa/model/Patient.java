package pl.dmcs.springbootjsp_iwa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Patient {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String age;

    @OneToOne(cascade = CascadeType.ALL)
    private  User user;

    @JsonManagedReference(value="patient-appointment")
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    private Set<Appointment> appointments;

    @JsonManagedReference
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
