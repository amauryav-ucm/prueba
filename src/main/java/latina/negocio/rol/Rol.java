package latina.negocio.rol;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "nombre") })
@NamedQueries({
        @NamedQuery(name = "Rol.findBynombre", query = "select obj from Rol obj where :nombre = obj.nombre ") })
public class Rol {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String nombre;

    private boolean activo;

    public boolean isActivo(){return this.activo;}

    public void setActivo(boolean activo){
        this.activo = activo;
    }

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

    public Rol() {
    }

    public Rol(TRol rol) {
        this.nombre = rol.getNombre();
        this.salario = rol.getSalario();
        this.activo = rol.isActivo();
    }
}