package latina.vista.comandos;

import latina.VistaPrincipal;

public interface Comando {
    public void ejecutar(Object object, VistaPrincipal vista);
}
