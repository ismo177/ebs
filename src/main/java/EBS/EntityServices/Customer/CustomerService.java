package EBS.EntityServices.Customer;

import EBS.EntityServices.AbstractService;
import jakarta.persistence.*;

public class CustomerService extends AbstractService<Customer>  {


    private EntityManager entityManager=entityManager();

    public CustomerService() {
        super(Customer.class);
    }

    @Override
    public EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        entityManager = emf.createEntityManager();
        return entityManager;
    }


    public Customer findByName(String name) {
            EntityManager em = entityManager();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("findByName");
            query.setParameter("name", name);
        try{
            Customer customer = (Customer) query.getSingleResult();
            return customer;
        }catch (NoResultException | NonUniqueResultException exception){
            System.err.println(exception.getMessage());
            return null;
        }
    }


    public void emOpen() {
        EntityManager entityManager =entityManager();
        entityManager.getTransaction().begin();
    }

}
