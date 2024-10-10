/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author danie
 */
@Embeddable
public class ProductoVentaPK implements Serializable {

    private Long productoId;
    private Long ventaId;

    public ProductoVentaPK() {
    }

    public ProductoVentaPK(Long productoId, Long ventaId) {
        this.productoId = productoId;
        this.ventaId = ventaId;
    }

    // Getters and Setters

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getVentaId() {
        return ventaId;
    }

    public void setVentaId(Long ventaId) {
        this.ventaId = ventaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductoVentaPK)) return false;

        ProductoVentaPK that = (ProductoVentaPK) o;

        if (!productoId.equals(that.productoId)) return false;
        return ventaId.equals(that.ventaId);
    }

    @Override
    public int hashCode() {
        int result = productoId.hashCode();
        result = 31 * result + ventaId.hashCode();
        return result;
    }
}