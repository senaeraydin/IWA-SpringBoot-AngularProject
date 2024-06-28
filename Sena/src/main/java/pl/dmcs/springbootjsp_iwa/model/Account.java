package pl.dmcs.springbootjsp_iwa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;


@Entity
public class Account {

    @Id
    @GeneratedValue
    private long id;
    private String accountName;

    @JsonIgnore
    // Commented out due to simplify http requests sent from angular app
//    @OneToOne(mappedBy = "account")
//    private Student student;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }


    // Commented out due to simplify http requests sent from angular app
//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }

}
