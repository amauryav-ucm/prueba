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

    public TRol(String nombre, double salario){
        this.nombre = nombre;
        this.salario = salario;
    }

    public String toString(){
        return nombre + Double.toString(salario);
    }
}
