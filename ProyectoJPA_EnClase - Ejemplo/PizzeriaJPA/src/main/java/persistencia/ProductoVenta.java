package persistencia;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "producto_venta")
public class ProductoVenta implements Serializable {

    @EmbeddedId
    private ProductoVentaPK id; // Clave compuesta

    @ManyToOne
    @MapsId("productoId") // Mapea productoId a la clave compuesta
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @MapsId("ventaId") // Mapea ventaId a la clave compuesta
    @JoinColumn(name = "venta_id")
    private Venta venta;

    private int cantidad;
    private float precio; // Precio del producto en la venta
    private float subtotal; // Subtotal calculado

    public ProductoVenta() {
    }

    public ProductoVenta(ProductoVentaPK id, Producto producto, Venta venta, int cantidad, float precio, float subtotal) {
        this.id = id;
        this.producto = producto;
        this.venta = venta;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
    }

    // Getters y Setters

    public ProductoVentaPK getId() {
        return id;
    }

    public void setId(ProductoVentaPK id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
        if (this.id == null) {
            this.id = new ProductoVentaPK();
        }
        this.id.setProductoId(producto.getId());
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
        if (this.id == null) {
            this.id = new ProductoVentaPK();
        }
        this.id.setVentaId(venta.getId());
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "ProductoVenta { " +
                "producto = " + producto +
                ", venta = " + venta +
                ", cantidad = " + cantidad +
                ", precio = " + precio +
                " }";
    }
}