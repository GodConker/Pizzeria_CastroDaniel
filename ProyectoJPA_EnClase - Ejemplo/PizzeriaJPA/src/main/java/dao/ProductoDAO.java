/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import persistencia.Producto;

/**
 *
 * @author danie
 */
public class ProductoDAO {
    private final EntityManager em;

    public ProductoDAO(EntityManager em) {
        this.em = em;
    }

    public List<Producto> buscarProductosPrecioMayorA(float precio) {
        TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p WHERE p.precio > :precio", Producto.class);
        query.setParameter("precio", precio);
        return query.getResultList();
    }
}
