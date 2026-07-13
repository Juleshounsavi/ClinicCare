package metier;
import jakarta.persistence.*;
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
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String specialite;


    @ToString.Exclude
    @OneToMany(mappedBy = "medecin")
    private List<Consultation> consultations;



}
