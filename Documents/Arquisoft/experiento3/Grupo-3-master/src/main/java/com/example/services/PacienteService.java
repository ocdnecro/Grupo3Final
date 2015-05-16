/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services;

import com.example.PersistenceManager;
import com.example.models.Episodio;
import com.example.models.Paciente;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Path("/Paciente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PacienteService {

    @PersistenceContext(unitName = "mongoPU")
    EntityManager entityManager;

    //static private Clinica clinica = Clinica.darInstancia();

    @PostConstruct
    public void init() {
        try {
            entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        } catch (Exception e) {
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            e.printStackTrace();
        }
    }

    @POST
    @Path("/agregarPaciente")
    public List<Paciente> agregarPacientes(List<Paciente> pac) {
        for (Paciente pacient : pac) {
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(pacient);
                entityManager.getTransaction().commit();
                entityManager.refresh(pacient);
            } catch (Throwable t) {
                t.printStackTrace();
                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }
            } finally {
                entityManager.clear();
                entityManager.close();
            }
        }

        return pac;
    }

    @POST
    @Path("/agregarEpisodio")
    public List<Episodio> agregarEpisodio(List<Episodio> pEpisodios) {
        for (Episodio nEpisodio : pEpisodios) {
            //TypedQuery<Episodio> query = (TypedQuery<Episodio>) entityManager.createQuery("SELECT c FROM Episodio c WHERE c.cedula = :nCedula ORDER BY C.fecha");
            //List<Episodio> episodios =query.setParameter("nCedula", cedula).getResultList();
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(nEpisodio);
                entityManager.getTransaction().commit();
                entityManager.refresh(nEpisodio);
            } catch (Throwable t) {
                t.printStackTrace();
                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }
            } finally {
                entityManager.clear();
                entityManager.close();
            }
        }
        return pEpisodios;
    }

    @GET
    @Path("/obtenerEpisodios/{cedula}/{fechaInicial}/{fechaFinal}")
    public List<Episodio> verEpisodioFecha(@PathParam("cedula") int cedula, @PathParam("fechaInicial") String fechaInicial, @PathParam("fechaFinal") String fechaFinal) {//int ced, String fechainicial, String fechafinal)         
        TypedQuery<Episodio> query = (TypedQuery<Episodio>) entityManager.createQuery("SELECT c FROM Episodio c WHERE c.cedula = :nCedula AND c.fecha >= :fechaInicial AND c.fecha <= :fechaFinal");
        query.setParameter("nCedula", cedula);
        query.setParameter("fechaInicial", fechaInicial);
        query.setParameter("fechaFinal", fechaFinal);
        List<Episodio> episodios =query.getResultList();
                
        return episodios;
    }

    @GET
    @Path("/obtenerEpisodios/{cedula}/{id}")
    public List<Episodio> verEpisodioFecha(@PathParam("cedula") int cedula, @PathParam("id") String id) {        
        TypedQuery<Episodio> query = (TypedQuery<Episodio>) entityManager.createQuery("SELECT c FROM Episodio c WHERE c.id = :nId");
        query.setParameter("nId", id);
        List<Episodio> episodios =query.getResultList();
                
        return episodios;
    }

    @GET
    @Path("/obtenerEpisodios/{cedula}")
    public List<Episodio> verEpisodioFecha(@PathParam("cedula") int cedula) {
        TypedQuery<Episodio> query = (TypedQuery<Episodio>) entityManager.createQuery("SELECT c FROM Episodio c WHERE c.cedula = :nCedula ORDER BY C.fecha");
        List<Episodio> episodios =query.setParameter("nCedula", cedula).getResultList();
                
        return episodios;
    }
}
