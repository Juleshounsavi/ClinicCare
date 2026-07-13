package dao;
import jakarta.persistence.EntityManagerFactory;
import metier.Patient;
import java.util.*;
import jakarta.persistence.*;

public class IPatientDAOImpl implements IPatientDAO{

    private final EntityManagerFactory emf;
    public IPatientDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(Patient p){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

    @Override
    public Patient findById(int id){
        EntityManager em = emf.createEntityManager();
        try{
            return em.find(Patient.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<Patient> findAll(){
        EntityManager em = emf.createEntityManager();
        String query = "SELECT p FROM Patient p";
        try{
            return em.createQuery(query, Patient.class).getResultList();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            em.close();
        }

        return null;
    }

    @Override
    public void update(Patient p){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int id){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Patient p = em.find(Patient.class, id);
            if(p != null){
                em.remove(p);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
