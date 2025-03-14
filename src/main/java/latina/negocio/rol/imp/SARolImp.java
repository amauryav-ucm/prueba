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
        em = EMFContainer.getInstance().getEMF().createEntityManager();
        em.getTransaction().begin();        
        @SuppressWarnings("unchecked")
        List<Object> l = em.createNamedQuery("Rol.findBynombre").setParameter("nombre", rol.getNombre()).getResultList();
        if(!l.isEmpty()) return -1;
        if(rol.getSalario()<=0) return -2;
        if (!rol.getNombre().matches("[A-Z ]+")) return -3; //Solo permite todo en mayuscula y sin numeros
        Rol mirol = new Rol(rol);
        em.persist(mirol);
        em.getTransaction().commit();
        return mirol.getId();
    }

}
