package latina.negocio.rol.imp;

import java.util.List;

import latina.integracion.emfc.EMFContainer;
import jakarta.persistence.EntityManager;
import latina.negocio.rol.Rol;
import latina.negocio.rol.SARol;
import latina.negocio.rol.TRol;

public class SARolImp implements SARol {

    @Override
    public int altaRol(TRol rol) {
        System.out.println("123");
        int id = 0;
        EntityManager em = null;
        try {
            em = EMFContainer.getInstance().getEMF().createEntityManager();
            em.getTransaction().begin();
            @SuppressWarnings("unchecked")
            List<Object> l = em.createNamedQuery("Rol.findBynombre").setParameter("nombre", rol.getNombre()).getResultList();
            if (!l.isEmpty()) {
                em.getTransaction().rollback();
                id = -1;
            }
            else if (rol.getSalario() <= 0) {
                em.getTransaction().rollback();
                id = -2;
            }
            else
            {
                Rol mirol = new Rol(rol);
                em.persist(mirol);
                em.getTransaction().commit();
                id = mirol.getId();
            }

        }catch(Exception e)
        {
            if (em != null && em.getTransaction().isActive())
                em.getTransaction().rollback();
        } finally {
            if (em != null)
                em.close();
        }
        return id;
    }

}
