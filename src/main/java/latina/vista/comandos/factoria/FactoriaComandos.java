package latina.vista.comandos.factoria;

import latina.vista.Eventos;
import latina.vista.comandos.Comando;
import latina.vista.comandos.factoria.imp.FactoriaComandosImp;

public abstract class FactoriaComandos {
    private static FactoriaComandos instancia;

    public static FactoriaComandos getInstance() {
        if (instancia == null)
            instancia = new FactoriaComandosImp();
        return instancia;
    }

    public abstract Comando crearComando(Eventos evento);
}
