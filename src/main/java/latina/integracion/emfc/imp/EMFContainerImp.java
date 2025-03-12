package latina.integracion.emfc.imp;

import latina.integracion.emfc.EMFContainer;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMFContainerImp extends EMFContainer {

    private EntityManagerFactory emf;

    public EMFContainerImp() {
        emf = Persistence.createEntityManagerFactory("latina");
    }

    @Override
    public EntityManagerFactory getEMF() {
        return emf;
    }
}
