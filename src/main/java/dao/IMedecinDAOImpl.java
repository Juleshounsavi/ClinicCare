package dao;
import metier.Medecin;
import java.util.*;
import jakarta.persistence.*;

public class IMedecinDAOImpl implements IMedecinDAO{

    private final EntityManagerFactory emf;
    public IMedecinDAOImpl(EntityManagerFactory emf){
        this.emf = emf;
    }

    @Override
    public void save(Medecin m){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(m);
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
    public Medecin findById(int id){
        EntityManager em = emf.createEntityManager();
        try{
            return em.find(Medecin.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<Medecin> findAll(){
        EntityManager em = emf.createEntityManager();
        String query = "SELECT m FROM Medecin m";
        try{
            return em.createQuery(query, Medecin.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public void update(Medecin m){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(m);
            em.getTransaction().commit();
        } catch (Exception e) {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int id){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Medecin m = em.find(Medecin.class, id);
            if(m != null){
                em.remove(m);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }
}
