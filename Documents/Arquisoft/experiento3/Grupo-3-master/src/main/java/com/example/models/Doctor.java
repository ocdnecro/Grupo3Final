package com.example.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

/**
 *
 * @author Mauricio
 */
@NoSql(dataFormat = DataFormatType.MAPPED)
@Entity
@XmlRootElement
public class Doctor implements Serializable {

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
     * Nombre del doctor
     */
    private String nombre;
    /**
     * Apellido del doctor
     */
    private String apellido;
    /**
     * Cédula del doctor
     */
    private int cedula;
    /**
     * Nombre del doctor
     */
    private ArrayList<Integer> cedulaPacientes;

    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------
    /**
     * Constructor de la clase Doctor (sin argumentos)
     */
    public Doctor() {

    }

    /**
     * Constructor de la clase Doctor (con argumentos)
     *
     * @param pNombre El nombre del doctor
     * @param pApellido El apellido del doctor
     * @param pCedula La cédula del doctor
     */
    public Doctor(String pNombre, String pApellido, int pCedula) {
        nombre = pNombre;
        apellido = pApellido;
        cedula = pCedula;
        cedulaPacientes = new ArrayList<Integer>();
    }

    //------------------------------------------------------------------------------------------------------
    // Métodos
    //------------------------------------------------------------------------------------------------------
    // GETTERS ----------------------------------
    /**
     * Retorna el nombre del doctor
     *
     * @return El nombre del doctor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el apellido del doctor
     *
     * @return El apellido del doctor
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Retorna la cédula del doctor
     *
     * @return La cédula del doctor
     */
    public int getCedula() {
        return cedula;
    }

    /**
     * Retorna la lista de cedulas de los pacientes asociados al doctor
     *
     * @return La lista de cédulas de pacientes del doctor
     */
    public ArrayList<Integer> getCedulaPacientes() {
        return cedulaPacientes;
    }

    /**
     * Retorna el Id del doctor
     *
     * @return El Id del doctor
     */
    public String getId() {
        return id;
    }

    // SETTERS ----------------------------------
    /**
     * Modifica el nombre del doctor
     *
     * @param pNombre Nuevo nombre del doctor
     */
    public void setNombre(String pNombre) {
        nombre = pNombre;
    }

    /**
     * Modifica el apellido del doctor
     *
     * @param pApellido Nuevo apellido del doctor
     */
    public void setApellido(String pApellido) {
        apellido = pApellido;
    }

    /**
     * Modifica a cédula del doctor
     *
     * @param pCedula Nuevo cédula del doctor
     */
    public void setCedula(int pCedula) {
        cedula = pCedula;
    }

    /**
     * Modifica a id del doctor
     *
     * @param pId Nuevo id del doctor
     */
    public void setId(String pId) {
        id = pId;
    }

    /**
     * Modifica las cédulas asociadas al doctor
     *
     * @param pCedulaPacientes Lista de pacientes a agregar
     */
    public void setCedulaPacientes(ArrayList<Integer> pCedulaPacientes) {
        cedulaPacientes = pCedulaPacientes;
    }

    // ADDER ----------------------------------
    /**
     * Método que agrega un paciente
     *
     * @param cedulaPaciente La cédula del paciente a agregar
     */
    public void addPaciente(int cedulaPaciente) {
        cedulaPacientes.add(cedulaPaciente);
    }

    // ASKER ----------------------------------
    
    /**
     * Método que responde si el paciente pasado por parámetro pertenece al doctor
     * @param pCedula La cédula del paciente solicitado
     * @return True si el paciente es atendido por el doctor. False de los contrario.
     */
    public boolean contienePaciente(int pCedula) {
        for (Integer cedulaPaciente : cedulaPacientes) {
            if (cedulaPaciente == pCedula) {
                return true;
            }
        }
        return false;
    }

}
