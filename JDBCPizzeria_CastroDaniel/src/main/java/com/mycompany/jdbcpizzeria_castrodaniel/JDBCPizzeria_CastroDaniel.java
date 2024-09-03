/**
 * 
 */
package com.mycompany.jdbcpizzeria_castrodaniel;

import dao.Conexion;
import dao.ProductoDAO;
import interfaces.IConexion;
import interfaces.IProductoDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import objetos.Producto;

/**
 *
 * @author daniel
 */
public class JDBCPizzeria_CastroDaniel {

    public static void main(String[] args) {
//
//        String cadenaConexion = "jdbc:mysql://127.0.0.1:3306/pizzeria_bda";
//        String user = "root";
//        String pwd = "13137Cas";
//        String i = "INSERT INTO Productos (nombre, precio, descripcion) VALUES (?,?,?)";
//        String buscarProducto = "SELECT * FROM Productos WHERE precio < ?";
//        try (Connection c = DriverManager.getConnection(cadenaConexion, user, pwd); PreparedStatement insert = c.prepareStatement(i, Statement.RETURN_GENERATED_KEYS)) {
//
////            insert.setString(1, "Pizza de champiñones");
////            insert.setFloat(2, 100.0f);  
////            insert.setString(3, "Pizza sencilla con base de tomate y queso, con topping de pepperonis.");
////
////            insert.executeUpdate();
//
//            PreparedStatement busqueda = c.prepareStatement(buscarProducto);
//            busqueda.setFloat(1, 100);
//            
//            ResultSet resultados = busqueda.executeQuery();
//            
//            if(resultados.next()){
//               String nombre = resultados.getString("nombre");
//               float precio = resultados.getFloat("precio");
//               String descripcion = resultados.getString("descripcion");
//               
//                System.out.println(nombre+" $"+precio);
//                System.out.println(descripcion);
//            }
//
//            // Puedes obtener las claves generadas si es necesario
//            try (ResultSet generatedKeys = insert.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    long id = generatedKeys.getLong(1);
//                    System.out.println("ID de nuevo producto insertado: " + id);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        
       

        /* Inicializamos la instancia de la clase conexion para 
           poder hacer posible la conexión a la base de datos */
        IConexion conexion = new Conexion(); 
        
        /* Inicializamos una instancia de la interfaz de IProductoDAO
         llamando a la instancia como productoDAO que la recibe la clase
        ProductoDAO que recibe como parámetro la conexión a la base de datos. */
        IProductoDAO productoDAO = new ProductoDAO(conexion);
        

        // Creamos un nuevo producto
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre("Pizza con anchoas, producto de prueba.");
        nuevoProducto.setPrecio(175.99f);
        nuevoProducto.setDescripcion("Este es un producto de prueba de una Pizza con anchoas.");

        // Agregar el producto a la base de datos
        boolean productoAgregado = productoDAO.agregar(nuevoProducto);
        if (productoAgregado) {
            System.out.println("Producto agregado exitosamente.");
        } else {
            System.out.println("Error al agregar el producto.");
        }

        // Consultar todos los productos
        List<Producto> listaProductos = productoDAO.consultar();
        System.out.println("Lista de productos:");
        for (Producto producto : listaProductos) {
            System.out.println("ID: " + producto.getId() + ", Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio());
        }

        // Consultar un producto por ID
        Producto productoConsultado = productoDAO.consultar(1); // Aquí 
        // podemos cambiar el id del producto que querramos buscar o consultar.
        if (productoConsultado != null) {
            System.out.println("Producto encontrado: " + productoConsultado.getNombre());
        } else {
            System.out.println("Producto no encontrado.");
        }

        // Actualizar un producto existente
        Producto productoParaActualizar = new Producto();
        productoParaActualizar.setId(1); // Aquí 
        // podemos cambiar el id del producto que querramos actualizar.
        productoParaActualizar.setNombre("Producto Actualizado");
        productoParaActualizar.setPrecio(159.99f);
        productoParaActualizar.setDescripcion("Descripción actualizada");

        boolean productoActualizado = productoDAO.actualizar(productoParaActualizar);
        if (productoActualizado) {
            System.out.println("Producto actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar el producto.");
        }

        // Eliminar un producto por ID
        boolean productoEliminado = productoDAO.eliminar(1); // Aquí 
        // podemos cambiar el id del producto que querramos eliminar.
        if (productoEliminado) {
            System.out.println("Producto eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar el producto.");
        }
    }
}
