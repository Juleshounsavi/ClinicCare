

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import dao.*;
import metier.*;
import java.time.LocalDate;
import java.util.*;


public class Main {
    public static void main(String[] args) {

        Map<String, String> props = new HashMap<>();
        props.put("jakarta.persistence.jdbc.password", System.getenv("DB_PASSWORD"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinicCarePU", props);
        System.out.println("Connexion réussie !");

        IPatientDAO patientDAO = new IPatientDAOImpl(emf);
        IMedecinDAO medecinDAO = new IMedecinDAOImpl(emf);
        IConsultationDAO consultationDAO = new IConsultationDAOImpl(emf);


        // --- Vérifier findAll ---
        System.out.println("=== Toutes les consultations ===");
        List<Consultation> cons = consultationDAO.findAll();
        for(Consultation c: cons){
            System.out.println(c);
        }

        emf.close();

    }
}