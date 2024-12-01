/**
 * .
 */
package negocio;

import dao.ProductoDAO;
import interfaces.IConexion;
import interfaces.IProductosNegocio;
import java.util.List;
import objetos.Producto;

/**
 *
 * @author Laboratorios
 */
public class ProductosNegocio implements IProductosNegocio {
    
    private ProductoDAO productoDao;
    
    public ProductosNegocio(IConexion conexion){
        productoDao = new ProductoDAO(conexion);
    }

    @Override
    public List<Producto> obtenerProductos() {
        return productoDao.consultar();
    }

    @Override
    public Producto obtenerProductoPorId(int id) {
        return productoDao.consultar(id);
    }

    @Override
    public void agregarProducto(Producto producto) {
        productoDao.agregar(producto);
    }

    @Override
    public void actualizarProducto(Producto producto) {
        productoDao.actualizar(producto);
    }

    @Override
    public void eliminarProducto(int id) {
        productoDao.eliminar(id);
    }
    
}
