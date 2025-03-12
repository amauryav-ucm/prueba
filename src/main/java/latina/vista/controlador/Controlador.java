package latina.vista.controlador;

import latina.vista.controlador.imp.ControladorImp;

public abstract class Controlador {

    private static Controlador controlador;

    public static Controlador getInstance() {
        if (controlador == null) {controlador = new ControladorImp();}
        return controlador;
    }

    public abstract void accion(int evento, Object param);
}
