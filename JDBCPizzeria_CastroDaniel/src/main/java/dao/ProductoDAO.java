/**
 *
 */
package dao;

import interfaces.IConexion;
import interfaces.IProductoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Producto;

/**
 *
 * @author daniel
 */
public class ProductoDAO implements IProductoDAO {

    private IConexion conexion;

    public ProductoDAO(IConexion bd) {
        this.conexion = bd;
    }

    @Override
    public boolean agregar(Producto producto) {
        Connection bd = conexion.crearConexion();
        String insertar = "INSERT INTO producto (nombre, precio, descripcion) VALUES (?,?,?)";
        try {
            PreparedStatement i = bd.prepareStatement(insertar);
            i.setString(1, producto.getNombre());
            i.setFloat(2, producto.getPrecio());
            i.setString(3, producto.getDescripcion());
            

            i.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizar(Producto producto) {
        try {
            Connection bd = conexion.crearConexion();
            String actualizarProducto = "UPDATE Producto SET nombre = ?, precio = ?, descripcion = ? WHERE id = ?";
            PreparedStatement actualizacion = bd.prepareStatement(actualizarProducto);
            actualizacion.setString(1, producto.getNombre());
            actualizacion.setFloat(2, producto.getPrecio());
            actualizacion.setString(3, producto.getDescripcion());
            actualizacion.setInt(4, producto.getId());

            int filasAfectadas = actualizacion.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        try {
            Connection bd = conexion.crearConexion();
            String eliminarProducto = "DELETE FROM Producto WHERE id = ?";
            PreparedStatement eliminacion = bd.prepareStatement(eliminarProducto);
            eliminacion.setInt(1, id);

            int filasAfectadas = eliminacion.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Producto consultar(int id) {

        try {
            Connection bd = conexion.crearConexion();
            String buscarProducto = "SELECT * FROM Producto WHERE id < ?";
            PreparedStatement busqueda = bd.prepareStatement(buscarProducto);
            busqueda.setFloat(1, id+1);

            ResultSet resultados = busqueda.executeQuery();

            if (resultados.next()) {
                Producto p = new Producto();
                p.setId(resultados.getInt("id"));
                p.setNombre(resultados.getString("nombre"));
                p.setPrecio(resultados.getFloat("precio"));
                p.setDescripcion(resultados.getString("descripcion"));

                return p;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Producto> consultar() {
        List<Producto> productos = new ArrayList<>();

        try {
            Connection bd = conexion.crearConexion();
            String buscarProductos = "SELECT * FROM Producto";
            PreparedStatement busqueda = bd.prepareStatement(buscarProductos);

            ResultSet resultados = busqueda.executeQuery();

            while (resultados.next()) {
                Producto p = new Producto();
                p.setId(resultados.getInt("id"));
                p.setNombre(resultados.getString("nombre"));
                p.setPrecio(resultados.getFloat("precio"));
                p.setDescripcion(resultados.getString("descripcion"));

                productos.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productos;
    }

}
