package dao;

import jakarta.persistence.*;
import metier.Patient;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PatientDAOTest {

    private static EntityManagerFactory emf;
    private IPatientDAO patientDAO;

    @BeforeAll
    static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("ClinicCareTestPU");
    }

    @BeforeEach
    void setUp() {
        patientDAO = new IPatientDAOImpl(emf);
    }

    @AfterAll
    static void tearDownClass() {
        emf.close();
    }

    @Test
    void save_thenFindById_shouldReturnSamePatient() {
        Patient p = new Patient();
        p.setNom("Koffi");
        p.setPrenom("Jules");
        p.setDateNaissance(LocalDate.of(2002, 5, 15));
        p.setGroupeSanguin("O+");

        patientDAO.save(p);

        Patient found = patientDAO.findById(p.getId());

        assertNotNull(found);
        assertEquals("Koffi", found.getNom());
        assertEquals("Jules", found.getPrenom());
    }

    @Test
    void findAll_shouldReturnAllSavedPatients() {
        Patient p1 = new Patient();
        p1.setNom("Alami");
        p1.setPrenom("Sara");
        patientDAO.save(p1);

        Patient p2 = new Patient();
        p2.setNom("Bennani");
        p2.setPrenom("Omar");
        patientDAO.save(p2);

        List<Patient> all = patientDAO.findAll();

        assertTrue(all.size() >= 2);
    }

    @Test
    void update_shouldModifyPatientData() {
        Patient p = new Patient();
        p.setNom("Fassi");
        p.setPrenom("Amine");
        patientDAO.save(p);

        p.setNom("FassiModifie");
        patientDAO.update(p);

        Patient updated = patientDAO.findById(p.getId());
        assertEquals("FassiModifie", updated.getNom());
    }

    @Test
    void delete_shouldRemovePatient() {
        Patient p = new Patient();
        p.setNom("Temporaire");
        p.setPrenom("Test");
        patientDAO.save(p);

        int id = p.getId();
        patientDAO.delete(id);

        Patient deleted = patientDAO.findById(id);
        assertNull(deleted);
    }
}