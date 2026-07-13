package dao;
import metier.Medecin;
import java.util.*;

public interface IMedecinDAO {
    void save(Medecin m);
    Medecin findById(int id);
    List<Medecin> findAll();
    void update(Medecin m);
    void delete(int id);
}
