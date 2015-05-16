/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services;

import com.example.PersistenceManager;
import com.example.models.Doctor;
import com.example.models.Episodio;
import com.example.models.Paciente;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Path("/Doctor")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DoctorService {

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
    @Path("/agregarDoctor")
    public List<Doctor> crearDoctor(List<Doctor> doctores) {
        for (Doctor doctor : doctores) {
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(doctor);
                entityManager.getTransaction().commit();
                entityManager.refresh(doctor);
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
        return doctores;
    }

    @PUT
    @Path("/agregarPaciente/{cedulaDoctor}/{cedulaPaciente}")
    public List<Paciente> crearDoctor(@PathParam("cedulaDoctor") int cedulaDoctor, @PathParam("cedulaPaciente") int cedulaPaciente) {
        TypedQuery<Doctor> doctor = (TypedQuery<Doctor>) entityManager.createQuery("SELECT c FROM Doctor c WHERE c.cedula = :cedulaDoctor");
        List<Doctor> doctores = doctor.setParameter("cedulaDoctor", cedulaDoctor).getResultList();

        TypedQuery<Paciente> paciente = (TypedQuery<Paciente>) entityManager.createQuery("SELECT c FROM Paciente c WHERE c.cedula = :cedulaPaciente");
        List<Paciente> pacientes = paciente.setParameter("cedulaPaciente", cedulaPaciente).getResultList();

        if (!doctores.isEmpty() && !pacientes.isEmpty()) {
            try {
                entityManager.getTransaction().begin();
                Paciente p = pacientes.get(0);
                Doctor d = doctores.get(0);
                p.setCedulaDoctor(cedulaDoctor);
                d.addPaciente(cedulaPaciente);
                entityManager.getTransaction().commit();
            } catch (Throwable t) {
                t.printStackTrace();
                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }
            } finally {
                entityManager.clear();
                entityManager.close();
            }
            return pacientes;
        }
        return new ArrayList<Paciente>();
    }

    @GET
    @Path("/consultarEpisodiosPaciente/{cedulaDoctor}/{cedulaPaciente}")
    public List<Episodio> conslutarEpisodiosDoctor(@PathParam("cedulaPaciente") int cedulaPaciente, @PathParam("cedulaDoctor") int cedulaDoctor) {
        // Falta revisar si el paciente es parte es atendido por el doctor actual
        
        TypedQuery<Episodio> query = (TypedQuery<Episodio>) entityManager.createQuery("SELECT c FROM Episodio c WHERE c.cedula = :cedulaPaciente");
        List<Episodio> episodios = query.setParameter("cedulaPaciente", cedulaPaciente).getResultList();

        return hashEpisodio(episodios); 
    }

    @GET
    @Path("/consultarEpisodiosPaciente/{cedulaDoctor}/{cedulaPaciente}/{fechaInicial}/{fechaFinal}")
    public List<Episodio> conslutarEpisodiosDoctor(@PathParam("cedulaPaciente") int cedulaPaciente, @PathParam("cedulaDoctor") int cedulaDoctor, @PathParam("fechaInicial") String fechaInicial, @PathParam("fechaFinal") String fechaFinal) {
        // Falta revisar si el paciente es parte es atendido por el doctor actual
        
        TypedQuery<Episodio> query = (TypedQuery<Episodio>) entityManager.createQuery("SELECT c FROM Episodio c WHERE c.cedula = :cedulaPaciente AND c.fecha >= :fechaInicial AND c.fecha <= :fechaFinal");
        query.setParameter("cedulaPaciente", cedulaPaciente);
        query.setParameter("fechaInicial", fechaInicial);
        query.setParameter("fechaFinal", fechaFinal);
        List<Episodio> episodios =query.getResultList();
                
        return hashEpisodio(episodios);
    }

    @GET
    @Path("/consultarEpisodiosPaciente/{cedulaPaciente}/{cedulaDoctor}/{id}")
    public List<Episodio> consultarEpisodio(@PathParam("cedulaPaciente") int cedulaPaciente, @PathParam("cedulaDoctor") int cedulaDoctor, @PathParam("id") String id) {
        // Falta revisar si el paciente es parte es atendido por el doctor actual
        
        TypedQuery<Episodio> query = (TypedQuery<Episodio>) entityManager.createQuery("SELECT c FROM Episodio c WHERE c.id = :nId");
        List<Episodio> episodios = query.setParameter("nId", cedulaPaciente).getResultList();
        
        return hashEpisodio(episodios);
    }

    public List<Episodio> hashEpisodio(List<Episodio> lista)
    {
        String original = new String();
        
        for (Episodio lista1 : lista) {
            original += lista1.toString();
        }
        try{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(original.getBytes());
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
                Episodio hash = new Episodio();
                hash.setId(sb.toString());
                lista.add(hash);
                System.out.println("Digest(in hex format):: " + sb.toString());
        }
        catch(Exception a){System.out.println("Se jodio esta miÃ©rcoles");}
        return lista;
    }
}
