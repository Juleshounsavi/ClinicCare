package dao;

import jakarta.persistence.*;
import metier.Consultation;
import metier.Medecin;
import metier.Patient;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConsultationDAOTest {

    private static EntityManagerFactory emf;
    private IPatientDAO patientDAO;
    private IMedecinDAO medecinDAO;
    private IConsultationDAO consultationDAO;

    @BeforeAll
    static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("ClinicCareTestPU");
    }

    @BeforeEach
    void setUp() {
        patientDAO = new IPatientDAOImpl(emf);
        medecinDAO = new IMedecinDAOImpl(emf);
        consultationDAO = new IConsultationDAOImpl(emf);
    }

    @AfterAll
    static void tearDownClass() {
        emf.close();
    }

    @Test
    void save_thenFindByPatientId_shouldReturnConsultation() {
        Patient p = new Patient();
        p.setNom("Koffi");
        p.setPrenom("Jules");
        patientDAO.save(p);

        Medecin m = new Medecin();
        m.setNom("Benali");
        m.setPrenom("Youssef");
        m.setSpecialite("Cardiologue");
        medecinDAO.save(m);

        Consultation c = new Consultation();
        c.setDate(LocalDate.now());
        c.setDiagnostic("Hypertension");
        c.setPatient(p);
        c.setMedecin(m);
        consultationDAO.save(c);

        List<Consultation> results = consultationDAO.findByPatientId(p.getId());

        assertEquals(1, results.size());
        assertEquals("Hypertension", results.get(0).getDiagnostic());
    }

    @Test
    void save_thenFindByMedecinId_shouldReturnConsultation() {
        Patient p = new Patient();
        p.setNom("Alami");
        p.setPrenom("Sara");
        patientDAO.save(p);

        Medecin m = new Medecin();
        m.setNom("Chraibi");
        m.setPrenom("Laila");
        m.setSpecialite("Pediatre");
        medecinDAO.save(m);

        Consultation c = new Consultation();
        c.setDate(LocalDate.now());
        c.setDiagnostic("Grippe");
        c.setPatient(p);
        c.setMedecin(m);
        consultationDAO.save(c);

        List<Consultation> results = consultationDAO.findByMedecinId(m.getId());

        assertEquals(1, results.size());
        assertEquals("Grippe", results.get(0).getDiagnostic());
    }

    @Test
    void findAll_shouldReturnAllConsultations() {
        List<Consultation> all = consultationDAO.findAll();
        assertNotNull(all);
    }
}