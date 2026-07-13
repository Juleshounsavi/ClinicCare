package dao;
import jakarta.persistence.*;
import metier.*;

import java.util.List;

public class IConsultationDAOImpl implements IConsultationDAO{

    private final EntityManagerFactory emf;
    public IConsultationDAOImpl(EntityManagerFactory emf){
        this.emf = emf;
    }


    @Override
    public void save(Consultation c){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(c);
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
    public List<Consultation> findAll(){
        EntityManager em = emf.createEntityManager();
        try{
            String query = "SELECT c FROM Consultation c";
            return em.createQuery(query, Consultation.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();;
        }
        return null;
    }

    @Override
    public List<Consultation> findByPatientId(int id){
        EntityManager em = emf.createEntityManager();
        try{
            String query = "SELECT c FROM Consultation c WHERE c.patient.id = :id";
            return em.createQuery(query, Consultation.class).setParameter("id", id).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<Consultation> findByMedecinId(int id){
        EntityManager em = emf.createEntityManager();
        try{
            String query = "SELECT c FROM Consultation c WHERE c.medecin.id = :id";
            return em.createQuery(query, Consultation.class).setParameter("id", id).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }
}
