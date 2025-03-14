package latina.negocio.rol.imp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import latina.negocio.rol.Rol;
import latina.negocio.rol.TRol;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SARolImpTest {

    @Test
    void altaRol() {

    }

    @Test
    void testNombreRepetido() {
        // Como son test unitarios, no pueden conectarse con la base de datos ni con nada de eso y se deben crear stubs
        // para las funciones

        // Creamos un rol falso que estaria en teoria dentro de la base de datos
        Rol rolEnBD = new Rol();
        rolEnBD.setActivo(true);
        rolEnBD.setNombre("mirol");
        rolEnBD.setSalario(10);
        // Lo metemos en una lista porque es lo que devuelve la query
        List<Object> resultList = new ArrayList<>();
        resultList.add(rolEnBD);

        // Creamos una query falsa que cuando se le pida los resultados devuelva la lista que creamos antes
        Query buscarPorNombre = mock(Query.class);
        when(buscarPorNombre.getResultList()).thenReturn(resultList);

        // Creamos una transaccion falsa para devolver cuando se pida al EM
        EntityTransaction transaction = mock(EntityTransaction.class);

        // Creamos un EntityManager falso que al pedirle la query findByNombre devuelva la query falsa de arriba
        // y cuando se le pida la transaccion devuelva la falsa
        EntityManager entityManager = mock(EntityManager.class);
        when(entityManager.createNamedQuery("Rol.findBynombre")).thenReturn(buscarPorNombre);
        when(entityManager.getTransaction()).thenReturn(transaction);

        // Creamos un SA que vamos a espiar para controlar la creacion del entityManager
        SARolImp sa = Mockito.spy(new SARolImp());

        // Cuando el SA llame a crearEntityManager, va a devolver el falso
        doReturn(entityManager).when(sa).crearEntityManager();

        // Creamos el transfer con el mismo nombre para que de error
        TRol trol = new TRol("mirol", 0, true);

        // Llamamos a la funcion
        int resultado = sa.altaRol(trol);

        // Como el nombre es repetido deberia devolver -1
        assertEquals(-1, resultado);
    }
}