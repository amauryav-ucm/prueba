package latina.negocio.rol;

public class TRol {
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private String nombre;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    private double salario;
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    private boolean activo;
    public boolean isActivo(){
        return activo;
    }
    public void setActivo(boolean activo)
    {
        this.activo = activo;
    }
    public TRol(String nombre, double salario, boolean activo){
        this.nombre = nombre;
        this.salario = salario;
        this.activo = activo;
    }

    public String toString(){
        return nombre + Double.toString(salario);
    }
}