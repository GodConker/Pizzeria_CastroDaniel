/**
 *
 */
package com.mycompany.pizzeriajpa;

import dao.IngredienteDAO;
import dao.ProductoDAO;
import dao.VentaDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import persistencia.Ingrediente;
import persistencia.Producto;
import persistencia.TipoIngrediente;
import persistencia.Venta;

/**
 *
 * @author Laboratorios
 */
public class PizzeriaJPA {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_pizzeria");
        EntityManager em = emf.createEntityManager();
////        Producto pizza = em.find(Producto.class, Long.valueOf(3));
////
//////        Producto pizzaPepperoni = new Producto("Pizza de anchoas", "pizza de miedo", (float) 99.9);
//////        em.persist(pizzaPepperoni);
//////        em.getTransaction().commit();
//////        
//////        System.out.println("Pizza guardada.");
////        Producto producto = em.find(Producto.class, 3);
////        if (producto != null) {
////            em.remove(producto);
////            em.getTransaction().commit();
////            System.out.println("Pizza Eliminada.");
////        } else {
////            System.out.println("No existe la pizza con ese id.");
////        }
//        TipoIngrediente tipo = new TipoIngrediente("Refrigerados");
//        List<Ingrediente> ingredientes = new ArrayList<>();
//
//        ingredientes.add(new Ingrediente("Salami", 10, tipo));
//        ingredientes.add(new Ingrediente("Queso Mozzarella", 50, tipo));
//
//        // Establecer la relación
//        tipo.setIngredientes(ingredientes);
//
//        em.getTransaction().begin();
//
//        // Primero persistir el TipoIngrediente
//        em.persist(tipo);
//
//        
//        em.getTransaction().commit();
//        System.out.println("Operacion terminada.");
//
//        em.close();
//        emf.close();

        EntityTransaction transaction = em.getTransaction();

        try {
            // Iniciar la transacción
            transaction.begin();

            // Crear y persistir 5 productos (asegúrate de que esto solo se haga una vez, quizás en otro método)
            List<Producto> productos = new ArrayList<>();
            productos.add(new Producto("Pizza Margherita", "Clásica con tomate y albahaca", 200f));
            productos.add(new Producto("Pizza Pepperoni", "Con pepperoni extra", 250f));
            productos.add(new Producto("Pizza Cuatro Quesos", "Con cuatro tipos de queso", 280f));
            productos.add(new Producto("Pizza Hawaiana", "Con piña y jamón", 220f));
            productos.add(new Producto("Pizza BBQ", "Con pollo BBQ", 300f));

            for (Producto producto : productos) {
                em.persist(producto);
            }

            // Crear y persistir 5 tipos de ingrediente
            List<TipoIngrediente> tiposIngrediente = new ArrayList<>();
            tiposIngrediente.add(new TipoIngrediente("Lacteos"));
            tiposIngrediente.add(new TipoIngrediente("Carnicos"));
            tiposIngrediente.add(new TipoIngrediente("Vegetales"));
            tiposIngrediente.add(new TipoIngrediente("Especias"));
            tiposIngrediente.add(new TipoIngrediente("Mariscos"));

            for (TipoIngrediente tipoIngrediente : tiposIngrediente) {
                em.persist(tipoIngrediente);
            }

            // Crear y persistir 5 ingredientes
            List<Ingrediente> ingredientes = new ArrayList<>();
            ingredientes.add(new Ingrediente("Queso Mozzarella", 50, tiposIngrediente.get(0)));
            ingredientes.add(new Ingrediente("Pepperoni", 30, tiposIngrediente.get(1)));
            ingredientes.add(new Ingrediente("Pinha", 20, tiposIngrediente.get(2)));
            ingredientes.add(new Ingrediente("Oregano", 10, tiposIngrediente.get(3)));
            ingredientes.add(new Ingrediente("Camarones", 40, tiposIngrediente.get(4)));

            for (Ingrediente ingrediente : ingredientes) {
                em.persist(ingrediente);
            }

            // Crear y persistir algunas ventas
            List<Venta> ventas = new ArrayList<>();
            ventas.add(new Venta(600f, java.sql.Date.valueOf("2024-08-15"))); // Venta del 15 de agosto
            ventas.add(new Venta(450f, java.sql.Date.valueOf("2024-09-01"))); // Venta del 1 de septiembre
            ventas.add(new Venta(300f, java.sql.Date.valueOf("2024-09-20"))); // Venta del 20 de septiembre

            for (Venta venta : ventas) {
                em.persist(venta);
            }

            // Forzar que se persistan los cambios
            em.flush();

            // Consultas después de agregar los registros
            ProductoDAO productoDAO = new ProductoDAO(em);
            IngredienteDAO ingredienteDAO = new IngredienteDAO(em);
            VentaDAO ventaDAO = new VentaDAO(em);

            // --- Productos con precio mayor a 250 ---
            System.out.println("Productos con precio mayor a 250:");
            List<Producto> productosCaros = productoDAO.buscarProductosPrecioMayorA(250);
            Set<Producto> productosUnicos = new HashSet<>(productosCaros);
            productosUnicos.forEach(System.out::println);

            // --- Ingredientes cuyo nombre empieza o termina con 'A' ---
            System.out.println("\nIngredientes cuyo nombre empieza o termina con 'A':");
            List<Ingrediente> ingredientesConA = ingredienteDAO.buscarIngredientesPorNombreA();
            Set<Ingrediente> ingredientesUnicos = new HashSet<>(ingredientesConA);
            ingredientesUnicos.forEach(System.out::println);

            // --- Ventas entre agosto y septiembre de 2024 ---
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaInicioLocal = LocalDate.parse("2024-08-01", dtf);
            LocalDate fechaFinLocal = LocalDate.parse("2024-09-30", dtf);

            Date fechaInicio = java.sql.Date.valueOf(fechaInicioLocal);
            Date fechaFin = java.sql.Date.valueOf(fechaFinLocal);

            System.out.println("\nVentas entre agosto y septiembre de 2024:");
            List<Venta> ventasConsultadas = ventaDAO.buscarVentasEntreFechas(fechaInicio, fechaFin);
            ventasConsultadas.forEach(System.out::println);

            // Confirmar (commit) la transacción
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();  // Revertir la transacción si ocurre un error
            }
            e.printStackTrace();
        } finally {
            em.close();  // Cerrar el EntityManager
            emf.close();
        }
    }
}
