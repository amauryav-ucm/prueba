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
        EntityManager em = null;
        int id = 0;
        try {
            em = EMFContainer.getInstance().getEMF().createEntityManager();
            em.getTransaction().begin();
            @SuppressWarnings("unchecked")
            List<Object> l = em.createNamedQuery("Rol.findBynombre").setParameter("nombre", rol.getNombre()).getResultList();
            if(!l.isEmpty())
            {
                em.getTransaction().rollback();
                return -1;
            }
            else if(rol.getSalario() <= 0)
            {
                em.getTransaction().rollback();
                return -2;
            }
            else if (!rol.getNombre().matches("[A-Z ]+"))//Solo permite todo en mayuscula y sin numeros
            {
                em.getTransaction().rollback();
                return -3;
            }
            else
            {
                Rol mirol = new Rol(rol);
                em.persist(mirol);
                em.getTransaction().commit();
                id = mirol.getId();
            }
        }catch (Exception e) {
            if (em != null && em.getTransaction().isActive())
                em.getTransaction().rollback();
        } finally {
            if (em != null)
                em.close();
        }

        return id;
    }

}
