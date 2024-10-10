/**
 *
 */
package com.mycompany.pizzeriajpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistencia.Ingrediente;
import persistencia.Producto;
import persistencia.TipoIngrediente;

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

        
    }
}
