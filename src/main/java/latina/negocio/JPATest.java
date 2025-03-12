package latina.negocio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class JPATest {
    EntityManager em;

    public JPATest(){
        em = Persistence.createEntityManagerFactory("test").createEntityManager();
    }
}
