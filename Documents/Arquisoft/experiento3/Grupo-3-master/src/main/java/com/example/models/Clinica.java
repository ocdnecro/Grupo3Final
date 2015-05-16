 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open bbbb  the template in the editor.
 */
package com.example.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

@NoSql(dataFormat = DataFormatType.MAPPED)
@Entity
@XmlRootElement
/**
 * Clase Clínica
 *
 * @author estudiante
 */
public class Clinica implements Serializable {

    //-----------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------
    /**
     * Constante de serialización
     */
    private static final long serialVersionUID = 1L;

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    /**
     * Identificador del doctor en la BD
     */
    @Id
    @GeneratedValue
    @Field(name = "_id")
    private String id;

    /**
     * Lista de doctores asociados a la clínica
     */
    @OneToMany
    private List<Doctor> doctores;

    /**
     * Lista de pacientes de la clínica
     */
    @OneToMany
    private List<Paciente> pacientes;

    /**
     * Instancia de la clase
     */
    private static Clinica instancia;

    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------
    /**
     * Constructor de la clase Doctor (sin argumentos)
     */
    private Clinica() {
        doctores = new ArrayList();
        pacientes = new ArrayList();
    }

    //------------------------------------------------------------------------------------------------------
    // Métodos
    //------------------------------------------------------------------------------------------------------
    // GETTERS ----------------------------------
    /**
     * Retorna la lista de pacientes de la clínica
     *
     * @return La lista de pacientes
     */
    public List<Paciente> getPacientes() {
        return pacientes;
    }

    /**
     * Retorna la lista de doctores de la clínica
     *
     * @return La lista de doctores
     */
    public List<Doctor> getDoctores() {
        return doctores;
    }

    /**
     * El id de la clínica
     *
     * @return El id de la clínica
     */
    public String getId() {
        return id;
    }

    // INSTANCE ----------------------------------
    public static Clinica darInstancia() {
        if (instancia == null) {
            instancia = new Clinica();
        }
        return instancia;
    }

    // SETTERS ----------------------------------
    /**
     * Modifica la lista de doctores
     *
     * @param nDoctores La lista de doctores
     */
    public void setDoctor(ArrayList<Doctor> nDoctores) {
        doctores = nDoctores;
    }

    /**
     * Mdifica la lista de pacientes
     *
     * @param nPacientes La lista de pacientes
     */
    public void setPacientes(ArrayList<Paciente> nPacientes) {
        pacientes = nPacientes;
    }

    /**
     * Modifica el id de la clínica
     *
     * @param pId Nuevo id de la clínica
     */
    public void setId(String pId) {
        id = pId;
    }

    // ADDER ----------------------------------
    /**
     * Método que agrega un episodio a un paciente
     *
     * @param pEpisodio El episodio a agregar
     */
    public void addEpisodio(Episodio pEpisodio) {
        Paciente paciente = buscarPaciente(pEpisodio.getCedula());
        if (null != paciente) {
            paciente.addEpisodio(pEpisodio);
        }
    }

    /**
     * Método que agrega un doctor asociado a la clínica
     *
     * @param pDoctor El doctor a agregar
     */
    public void addDoctor(Doctor pDoctor) {
        Doctor doctor = buscarDoctor(pDoctor.getCedula());
        if (doctor == null) {
            doctores.add(pDoctor);
        }
    }

    /**
     * Método que agrega un paciente a la clínica
     *
     * @param pPaciente
     */
    public void addPaciente(Paciente pPaciente) {
        // TODO falta agregar la relación con el doctor
        Paciente paciente = buscarPaciente(pPaciente.getCedula());
        if (paciente == null) {
            pacientes.add(pPaciente);
        }
    }

    //SEARCH ----------------------------------
    /**
     * Retorna el doctor con la cédula enviada por parámetro
     *
     * @param pCedula La cédula del doctor buscado
     * @return El doctor buscado. null de lo contrario
     */
    public Doctor buscarDoctor(int pCedula) {
        for (Doctor doctor : doctores) {
            if (doctor.getCedula() == pCedula) {
                return doctor;
            }
        }
        return null;
    }

    /**
     * Retorna el paciente con la cédula pasada por parámetro
     *
     * @param pCedula La cédula del paciente buscado
     * @return El paciente buscado. null de lo contrario
     */
    public Paciente buscarPaciente(int pCedula) {
        for (Paciente paciente : pacientes) {
            if (paciente.getCedula() == pCedula) {
                return paciente;
            }
        }
        return null;
    }

    /**
     * Retorna todos los episodios del paciente con cédula dada
     *
     * @param cedulaPaciente La cédula del paciente solicitado
     * @param cedulaDoctor La cédula del doctor que atiende al paciente
     * @return La lista de episodios del paciente solicitadoo
     */
    public List<Episodio> conslutarEpisodiosDoctor(int cedulaPaciente, int cedulaDoctor) {
        // TODO revisar si doctores tiene asociado el paciente
        Paciente paciente = buscarPaciente(cedulaPaciente);
        return paciente.getEpisodios();
    }
    
    /**
     * Retorna el episodio con el id
     * @param id El identificador del episodio
     * @param cedulaPaciente La cédula del paciente solicitado
     * @param cedulaDoctor La cédula del doctor que atiende al paciente
     * @return 
     */
    public Episodio conslutarEpisodioDoctor(String id, int cedulaPaciente, int cedulaDoctor) {
        // TODO revisar si doctores tiene asociado el paciente
        Paciente paciente = buscarPaciente(cedulaPaciente);
        return paciente.getEpisodio(id);
    }
 
    /**
     * Retorna una lista con los episodios cuya fecha coincide con los parámetros dados
     * @param cedulaPaciente La cédula del paciente solicitado
     * @param cedulaDoctor La cédula del doctor que atiende al paciente
     * @param fechaInicial Fecha inicial del intervalo a buscar
     * @param fechaFinal Fecha final del intervalo a buscar
     * @return Lista con los episodios resultantes
     */
    public ArrayList<Episodio> conslutarEpisodiosDoctor(int cedulaPaciente, int cedulaDoctor, String fechaInicial, String fechaFinal) {
        // TODO revisar si doctores tiene asociado el paciente
        Paciente paciente = buscarPaciente(cedulaPaciente);
        return paciente.getEpisodiosFechas(fechaInicial, fechaFinal);
    }
    
    /**
     * Retorna todos los episodios del paciente con cédula dada
     *
     * @param cedulaPaciente La cédula del paciente solicitado
     * @return La lista de episodios del paciente solicitadoo
     */
    public List<Episodio> conslutarEpisodiosPaciente(int cedulaPaciente) {
        // TODO revisar si doctores tiene asociado el paciente
        Paciente paciente = buscarPaciente(cedulaPaciente);
        return paciente.getEpisodios();
    }
    
    /**
     * Retorna el episodio con el id
     * @param id El identificador del episodio
     * @param cedulaPaciente La cédula del paciente solicitado
     * @return 
     */
    public Episodio conslutarEpisodioPaciente(String id, int cedulaPaciente) {
        // TODO revisar si doctores tiene asociado el paciente
        Paciente paciente = buscarPaciente(cedulaPaciente);
        return paciente.getEpisodio(id);
    }
 
    /**
     * Retorna una lista con los episodios cuya fecha coincide con los parámetros dados
     * @param cedulaPaciente La cédula del paciente solicitado
     * @param fechaInicial Fecha inicial del intervalo a buscar
     * @param fechaFinal Fecha final del intervalo a buscar
     * @return Lista con los episodios resultantes
     */
    public ArrayList<Episodio> conslutarEpisodiosPaciente(int cedulaPaciente, String fechaInicial, String fechaFinal) {
        // TODO revisar si doctores tiene asociado el paciente
        Paciente paciente = buscarPaciente(cedulaPaciente);
        return paciente.getEpisodiosFechas(fechaInicial, fechaFinal);
    }

}
