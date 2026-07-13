package dao;
import metier.Patient;
import java.util.*;


public interface IPatientDAO {
    void save(Patient p);
    Patient findById(int id);
    List<Patient> findAll();
    void update(Patient p);
    void delete(int id);
}
