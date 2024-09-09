package EBS.EntityServices.Tax;

import EBS.EntityServices.AbstractService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TaxService extends AbstractService<Tax>  {

    EntityManager entityManager=entityManager();

    public TaxService() {
        super(Tax.class);
    }
    @Override
    protected EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        entityManager = emf.createEntityManager();
        return entityManager;
    }


}
