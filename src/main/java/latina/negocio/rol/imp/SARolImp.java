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
    public int altaRol(TRol rol) {
        EntityManager em = crearEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        Query buscarPorNombre = em.createNamedQuery("Rol.findBynombre");
        buscarPorNombre.setParameter("nombre", rol.getNombre());
        @SuppressWarnings("unchecked")
        List<Object> l = buscarPorNombre.getResultList();
        if(!l.isEmpty()) return -1;
        if(rol.getSalario()<=0) return -2;
        if (!rol.getNombre().matches("[A-Z ]+")) return -3; //Solo permite todo en mayuscula y sin numeros
        Rol mirol = new Rol(rol);
        em.persist(mirol);
        trans.commit();
        em.close();
        return mirol.getId();
    }

    protected EntityManager crearEntityManager(){
        return EMFContainer.getInstance().getEMF().createEntityManager();
    }

}
