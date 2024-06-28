package pl.dmcs.springbootjsp_iwa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.springbootjsp_iwa.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findById(long id);

    Patient findByUserId(long userId);
}
