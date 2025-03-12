package latina.integracion.emfc;

import latina.integracion.emfc.imp.EMFContainerImp;
import jakarta.persistence.EntityManagerFactory;

public abstract class EMFContainer {

    private static EMFContainer emfc;

    public static EMFContainer getInstance() {
        if (emfc == null)
            emfc = new EMFContainerImp();
        return emfc;
    }

    public abstract EntityManagerFactory getEMF();
}
