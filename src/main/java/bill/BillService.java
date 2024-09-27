package bill;

import service.AbstractService;
import service.Customer.Customer;
import jakarta.persistence.*;

import java.util.List;

public class BillService extends AbstractService<Bill>  {


    private EntityManager entityManager=entityManager();


    public BillService() {
        super(Bill.class);
    }

    @Override
    protected EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        entityManager = emf.createEntityManager();
        return entityManager;
    }



    public Bill findByMonthCustomer(String month, Customer customer){
        EntityManager em=entityManager();
        Query query=em.createNamedQuery("findByMonthCustomer");
        query.setParameter("month", month);
        query.setParameter("customer", customer );
        try{
            return (Bill) query.getSingleResult();
        }catch(NoResultException e){
            System.out.println("Bill not found "+ e.getMessage());
        }
        return null;
    }



    public List<Bill> findByInvoiceStatus(boolean invoiceStatus) {
        EntityManager em = entityManager();
        Query query = em.createNamedQuery("findByInvoiceStatus");
        try {
            query.setParameter("invoiceStatus", invoiceStatus );
            return  (List<Bill>) query.getResultList();


        } catch (NoResultException e) {
            System.out.println("List of Bills not found " + e.getMessage());
        }
        return null;
    }

    public void deleteBills(Customer customer){
        EntityManager em=entityManager();
        Query query=em.createNamedQuery("deleteByCustomer");
        try{
        query.setParameter("customer", customer );
        query.executeUpdate();
        }catch(TransactionRequiredException ex){
            System.out.println("TransactionRequiredException " + ex.getMessage());

        }


    }


}
