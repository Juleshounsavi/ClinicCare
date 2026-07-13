package dao;

import jakarta.persistence.*;
import metier.Medecin;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedecinDAOTest {

    private static EntityManagerFactory emf;
    private IMedecinDAO medecinDAO;

    @BeforeAll
    static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("ClinicCareTestPU");
    }

    @BeforeEach
    void setUp() {
        medecinDAO = new IMedecinDAOImpl(emf);
    }

    @AfterAll
    static void tearDownClass() {
        emf.close();
    }

    @Test
    void save_thenFindById_shouldReturnSameMedecin() {
        Medecin m = new Medecin();
        m.setNom("Benali");
        m.setPrenom("Youssef");
        m.setSpecialite("Cardiologue");

        medecinDAO.save(m);

        Medecin found = medecinDAO.findById(m.getId());

        assertNotNull(found);
        assertEquals("Benali", found.getNom());
        assertEquals("Cardiologue", found.getSpecialite());
    }

    @Test
    void findAll_shouldReturnAllSavedMedecins() {
        Medecin m1 = new Medecin();
        m1.setNom("Idrissi");
        m1.setPrenom("Nabil");
        m1.setSpecialite("Generaliste");
        medecinDAO.save(m1);

        Medecin m2 = new Medecin();
        m2.setNom("Chraibi");
        m2.setPrenom("Laila");
        m2.setSpecialite("Pediatre");
        medecinDAO.save(m2);

        List<Medecin> all = medecinDAO.findAll();

        assertTrue(all.size() >= 2);
    }

    @Test
    void delete_shouldRemoveMedecin() {
        Medecin m = new Medecin();
        m.setNom("Temporaire");
        m.setPrenom("Test");
        m.setSpecialite("Test");
        medecinDAO.save(m);

        int id = m.getId();
        medecinDAO.delete(id);

        Medecin deleted = medecinDAO.findById(id);
        assertNull(deleted);
    }
}