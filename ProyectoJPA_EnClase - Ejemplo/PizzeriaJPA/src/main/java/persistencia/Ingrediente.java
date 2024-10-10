/**
 * 
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "ingrediente")
public class Ingrediente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
   private String nombre;
   private Integer cantidad;
   @ManyToOne
   @JoinColumn(name = "tipo_id")
   private TipoIngrediente tipo;

    public Ingrediente() {
    }

    public Ingrediente(String nombre, Integer cantidad, TipoIngrediente tipo) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public Ingrediente(Long id, String nombre, Integer cantidad, TipoIngrediente tipo) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public TipoIngrediente getTipo() {
        return tipo;
    }

    public void setTipo(TipoIngrediente tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingrediente)) {
            return false;
        }
        Ingrediente other = (Ingrediente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
public String toString() {
    return "Ingrediente { " +
            "nombre = '" + nombre + '\'' +
            ", cantidad = " + cantidad +
            ", tipoIngrediente = " + tipo.getDescripcion() + // Si deseas mostrar el nombre del tipo de ingrediente
            " }";
}
    
}
