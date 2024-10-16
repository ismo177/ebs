package service.Tax;

import service.CrudAbstractFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TaxServiceFactory extends CrudAbstractFactory<Tax> {

    EntityManager entityManager=entityManager();

    public TaxServiceFactory() {
        super(Tax.class);
    }
    @Override
    protected EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        entityManager = emf.createEntityManager();
        return entityManager;
    }


}
