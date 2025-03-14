package latina.vista.controlador;

import latina.VistaPrincipal;
import latina.vista.Eventos;
import latina.vista.controlador.imp.ControladorImp;

public abstract class Controlador {

    private static Controlador controlador;
    private VistaPrincipal vistaP;

    public static Controlador getInstance(VistaPrincipal vista) {
        if (controlador == null) {controlador = new ControladorImp(vista);}
        return controlador;
    }

    public abstract void accion(Eventos evento, Object param);
}
