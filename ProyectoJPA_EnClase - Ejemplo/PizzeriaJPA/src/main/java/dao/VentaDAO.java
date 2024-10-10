/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import persistencia.Venta;

/**
 *
 * @author danie
 */
public class VentaDAO {
    private EntityManager em;

    public VentaDAO(EntityManager em) {
        this.em = em;
    }

    public List<Venta> buscarVentasEntreFechas(Date fechaInicio, Date fechaFin) {
        TypedQuery<Venta> query = em.createQuery("SELECT v FROM Venta v WHERE v.fecha BETWEEN :fechaInicio AND :fechaFin", Venta.class);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.getResultList();
    }
}
