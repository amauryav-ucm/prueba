package latina.vista.controlador.comandos.rol;

import latina.negocio.factoria.SAFactory;
import latina.negocio.rol.SARol;
import latina.negocio.rol.TRol;
import latina.vista.controlador.comandos.Comando;

public class RegistrarRol implements Comando {

    @Override
    public Object ejecutar(Object object) {
        SARol rol = SAFactory.getInstance().createSARol();
        int id = rol.altaRol((TRol)object);

        return null;
    }
}
