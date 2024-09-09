package EBS.EntityServices;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;


public abstract class AbstractService<E> {

    private final Class<E> entityClass;

    public AbstractService(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager entityManager();

    public void create(E entity){
        try (EntityManager em=entityManager()){
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (TransactionRequiredException ex) {
            System.out.println("error:" + ex.getMessage());
            if (entityManager().getTransaction().isActive()) {
                entityManager().getTransaction().rollback();
            }
            entityManager().close();
        }


    }

    public void edit(E entity) {
        try (EntityManager em=entityManager()){
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (TransactionRequiredException | NonUniqueResultException ex) {
            System.out.println("error:" + ex.getMessage());
            if (entityManager().getTransaction().isActive()) {
                entityManager().getTransaction().rollback();
            }
            entityManager().close();
        }

    }
    public void delete(E entity){

        try (EntityManager em=entityManager()){
            em.getTransaction().begin();
            em.remove(em.merge(entity));
            em.getTransaction().commit();
        } catch (TransactionRequiredException ex) {
            System.out.println("error:" + ex.getMessage());
            if (entityManager().getTransaction().isActive()) {
                entityManager().getTransaction().rollback();
            }
            entityManager().close();
        }

    }

    public E find(Object id){
        try (EntityManager em=entityManager()){
            em.getTransaction().begin();
            em.getTransaction().commit();
            return em.find(entityClass, id);
        } catch (NoResultException | NonUniqueResultException ex) {
            System.out.println("error:" + ex.getMessage());
            if (entityManager().getTransaction().isActive()) {
                entityManager().getTransaction().rollback();
            }
            entityManager().close();
        }
        return null;
    }


    public List<E> findAll(){
        try{
        CriteriaQuery criteriaQuery = entityManager().getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return entityManager().createQuery(criteriaQuery).getResultList();
    }catch(IllegalStateException | NoResultException | NonUniqueResultException ex){
            System.out.println("error:" + ex.getMessage());
        }
        return null;
    }

    public void clear(E entity){
        try (EntityManager em=entityManager()){
            em.getTransaction().begin();
            em.clear();
        }catch (TransactionRequiredException | NonUniqueResultException ex){
            System.out.println("error:" + ex.getMessage());
        }
    }
    public void emOpen() {
        EntityManager entityManager=entityManager();
        entityManager().getTransaction().begin();
    }
}
