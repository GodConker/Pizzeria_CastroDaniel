/**
 *
 */
package interfaces;

import java.util.List;
import objetos.Producto;

/**
 *
 * @author Daniel
 */
public interface IProductosNegocio {

    List<Producto> obtenerProductos();
    
    Producto obtenerProductoPorId(int id);
    
    void agregarProducto(Producto producto);
    
    void actualizarProducto(Producto producto);
    
    void eliminarProducto(int id);

}
