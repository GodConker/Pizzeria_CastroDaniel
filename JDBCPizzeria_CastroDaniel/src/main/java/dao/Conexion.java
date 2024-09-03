/**
 *
 */
package dao;

import interfaces.IConexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class Conexion implements IConexion {

    private String cadena = "jdbc:mysql://127.0.0.1:3306/pizzeria_bda";
    private String usuario = "root";
    private String pwd = "13137Cas";

    @Override
    public Connection crearConexion() {
        try {
            Connection c = DriverManager.getConnection(cadena, usuario, pwd);
            return c;
        } catch (SQLException e) {
            System.out.println("Hubo un error de conexión");
        }
        return null;
    }

}
