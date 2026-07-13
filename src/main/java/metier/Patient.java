package metier;


import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import metier.Consultation;
import lombok.ToString;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String groupeSanguin;

    @ToString.Exclude
    @OneToMany(mappedBy = "patient")
    private List<Consultation> consultations;
}
