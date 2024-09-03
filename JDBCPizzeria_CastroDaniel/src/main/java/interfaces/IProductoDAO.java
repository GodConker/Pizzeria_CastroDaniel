/**
 *
 */
package interfaces;

import java.util.List;
import objetos.Producto;

/**
 *
 * @author daniel
 */
public interface IProductoDAO {

    public boolean agregar(Producto producto);

    public boolean actualizar(Producto producto);

    public boolean eliminar(int id);

    public Producto consultar(int id);

    public List<Producto> consultar();

}
