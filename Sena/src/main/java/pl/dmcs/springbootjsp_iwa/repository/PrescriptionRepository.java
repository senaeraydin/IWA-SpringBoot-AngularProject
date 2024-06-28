package pl.dmcs.springbootjsp_iwa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.springbootjsp_iwa.model.Prescription;

import java.util.List;

@Repository
public interface PrescriptionRepository  extends JpaRepository<Prescription,Long> {
    Prescription findById(long id);


    List<Prescription> findByPatientId(Long patientId);
}
