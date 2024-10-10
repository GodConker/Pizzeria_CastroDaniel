/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import persistencia.Ingrediente;

/**
 *
 * @author danie
 */
public class IngredienteDAO {
    private EntityManager em;

    public IngredienteDAO(EntityManager em) {
        this.em = em;
    }

    public List<Ingrediente> buscarIngredientesPorNombreA() {
        TypedQuery<Ingrediente> query = em.createQuery("SELECT i FROM Ingrediente i WHERE i.nombre LIKE 'A%' OR i.nombre LIKE '%A'", Ingrediente.class);
        return query.getResultList();
    }
}
