package service.User;
import service.CrudAbstractFactory;
import jakarta.persistence.*;


import java.util.Base64;
import java.util.List;


public class UserServiceFactory extends CrudAbstractFactory<User> {

    private EntityManager entityManager = entityManager();

    public UserServiceFactory() {
        super(User.class);
    }

    @Override
    protected EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        entityManager = emf.createEntityManager();
        return entityManager;
    }

    public User register(String username, String password) {
        User user = findByUsernamePass(username, password);

        if (user != null) {
            return user;
        } else {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setActive(true);
            create(user);
        }
        return null;
    }


    public User login(String username, String password) {

        try {
            Query query = entityManager().createNamedQuery("findByUsername");
            query.setParameter("username", username);
            //query.setParameter("password", password);
            User user = (User) query.getSingleResult();

            if (user != null) {
                String encodedPassword=user.getPassword();
                byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
                String decodedPassword=new String(decodedBytes);
                if (decodedPassword.equals(password) && user.getUsername().equals(username)) {
                    return user;
                }

            }
        }catch (NoResultException e) {
            System.out.println("Error: "+e.getMessage());

        }
        return null;
    }




    public User findByUsername(String username) {
        try {
            Query query = entityManager.createNamedQuery("findByUsername");
            query.setParameter("username", username);
            List<User> users = query.getResultList();
            return users.isEmpty() ? null : users.get(0);
        } catch (Exception e) {
            throw new RuntimeException("Error in findByUsername: " + e.getMessage());

        }
    }

    public User findByUsernamePass(String username, String password) {
        if (!username.isEmpty() || !password.isEmpty()) {
            return null;
        }

            Query query = entityManager.createNamedQuery("findByUsernamePass");
            query.setParameter("username", username);
            query.setParameter("password", password);
            try {
                User user = (User) query.getSingleResult();
                return user;
            } catch (NoResultException | NonUniqueResultException exception) {
                System.err.println(exception.getMessage());
                return null;
            }
        }



        public void emOpen () {
            EntityManager entityManager = entityManager();
            entityManager.getTransaction().begin();
        }


}
