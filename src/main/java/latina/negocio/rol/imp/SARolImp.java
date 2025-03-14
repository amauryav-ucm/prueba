package latina.negocio.rol.imp;

import java.util.List;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import latina.integracion.emfc.EMFContainer;
import jakarta.persistence.EntityManager;
import latina.negocio.rol.Rol;
import latina.negocio.rol.SARol;
import latina.negocio.rol.TRol;

public class SARolImp implements SARol {

    @Override
    public int altaRol(TRol tRol) {
        EntityManager em = null;
        EntityTransaction trans = null;
        int id = 0;
        try {
            em = crearEntityManager();
            trans = em.getTransaction();
            trans.begin();
            Query buscarPorNombre = em.createNamedQuery("Rol.findBynombre");
            buscarPorNombre.setParameter("nombre", tRol.getNombre());
            @SuppressWarnings("unchecked")
            List<Object> l = buscarPorNombre.getResultList();
            if(!l.isEmpty() && ((Rol)l.get(0)).getNombre().equals(tRol.getNombre()))
            {
                trans.rollback();
                return -1;
            }
            else if(tRol.getSalario() <= 0)
            {
                trans.rollback();
                return -2;
            }
            else if (!tRol.getNombre().matches("[A-Z ]+"))//Solo permite todo en mayuscula y sin numeros
            {
                trans.rollback();
                return -3;
            }
            else
            {
                Rol mirol = new Rol(tRol);
                em.persist(mirol);
                trans.commit();
                id = mirol.getId();
            }
        }catch (Exception e) {
            if (trans != null && trans.isActive())
                trans.rollback();
        } finally {
            if (em != null)
                em.close();
        }

        return id;
    }

    protected EntityManager crearEntityManager(){
        return EMFContainer.getInstance().getEMF().createEntityManager();
    }

}
