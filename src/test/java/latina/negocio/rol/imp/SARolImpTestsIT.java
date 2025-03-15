package latina.negocio.rol.imp;

import jakarta.persistence.EntityManager;
import latina.integracion.emfc.EMFContainer;
import latina.negocio.factoria.SAFactory;
import latina.negocio.rol.Rol;
import latina.negocio.rol.SARol;
import latina.negocio.rol.TRol;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class SARolImpTestsIT {


    @Test
    public void registrarRolExitoso() {


        SARol sa = SAFactory.getInstance().createSARol();

        EntityManager em = EMFContainer.getInstance().getEMF().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Rol").executeUpdate();
        em.getTransaction().commit();
        em.close();
        TRol tRol = new TRol("LIMPIEZA", 8.00, true);
        Rol rol = new Rol(tRol);


        int id = sa.altaRol(tRol);

        //Exito
        assertTrue(id > 0);
    }

    @Test
    public void registarRolRepetido() {
        SARol sa = SAFactory.getInstance().createSARol();

        EntityManager em = EMFContainer.getInstance().getEMF().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Rol").executeUpdate();
        em.getTransaction().commit();
        em.close();
        TRol tRol = new TRol("LIMPIEZA", 8.00, true);
        Rol rol = new Rol(tRol);
        int id = sa.altaRol(tRol);
        id = sa.altaRol(tRol);
        //Como ya existe el nombre
        /* id = sa.altaRol(tRol);*/
        assertEquals(-1, id);

    }


    @Test
    public void registrarRolSalarioO() {
        SARol sa = SAFactory.getInstance().createSARol();

        EntityManager em = EMFContainer.getInstance().getEMF().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Rol").executeUpdate();
        em.getTransaction().commit();
        em.close();
        TRol tRol = new TRol("LIMPIEZA", 8.00, true);
        Rol rol = new Rol(tRol);
        //Salario = 0
        tRol.setSalario(0);
        int id = sa.altaRol(tRol);
        assertEquals(-2, id);
    }

    @Test
    public void registrarRolSalarioN() {
        SARol sa = SAFactory.getInstance().createSARol();

        EntityManager em = EMFContainer.getInstance().getEMF().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Rol").executeUpdate();
        em.getTransaction().commit();
        em.close();
        TRol tRol = new TRol("LIMPIEZA", 8.00, true);
        Rol rol = new Rol(tRol);
        //Salario < 0
        tRol.setSalario(-5);
        int id = sa.altaRol(tRol);
        assertEquals(-2, id);
    }

    @Test
    public void registrarRolNombreIncorrecto() {
        SARol sa = SAFactory.getInstance().createSARol();

        EntityManager em = EMFContainer.getInstance().getEMF().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Rol").executeUpdate();
        em.getTransaction().commit();
        em.close();
        TRol tRol = new TRol("LIMPIEZA", 8.00, true);
        Rol rol = new Rol(tRol);
        //Salario < 0
        tRol.setNombre("letrado");
        int id = sa.altaRol(tRol);
        assertEquals(-3, id);
    }

    @Test
    public void registrarRolNombreIncorrecto2() {
        SARol sa = SAFactory.getInstance().createSARol();

        EntityManager em = EMFContainer.getInstance().getEMF().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Rol").executeUpdate();
        em.getTransaction().commit();
        em.close();
        TRol tRol = new TRol("LIMPIEZA", 8.00, true);
        Rol rol = new Rol(tRol);
        //Salario < 0
        tRol.setNombre("CANTANTE1234");
        int id = sa.altaRol(tRol);
        assertEquals(-3, id);
    }

    @Test
    public void registrarRolNombreIncorrecto3() {
        SARol sa = SAFactory.getInstance().createSARol();

        EntityManager em = EMFContainer.getInstance().getEMF().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Rol").executeUpdate();
        em.getTransaction().commit();
        em.close();
        TRol tRol = new TRol("LIMPIEZA", 8.00, true);
        Rol rol = new Rol(tRol);
        //Salario < 0
        tRol.setNombre(".PIANISTA-");
        int id = sa.altaRol(tRol);
        assertEquals(-3, id);
    }
}

