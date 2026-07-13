package dao;
import metier.Consultation;
import metier.Patient;
import metier.Medecin;
import java.util.*;

public interface IConsultationDAO {
    void save(Consultation c);
    List<Consultation> findAll();
    List<Consultation> findByPatientId(int id);
    List<Consultation> findByMedecinId(int id);

}
