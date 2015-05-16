/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

/**
 * Clase Episodio
 *
 * @author estudiante
 */
@NoSql(dataFormat = DataFormatType.MAPPED)
@Entity
@XmlRootElement
public class Episodio implements Serializable {

    @Id
    @GeneratedValue
    @Field(name = "_id")
    private String id;
    //------------------------------------------------------------------------------------------------------
    // Atributos
    //------------------------------------------------------------------------------------------------------

    //@ManyToOne Paciente paciente;
    /**
     * Fecha de ocurrencia del Episodio
     */
    private String fecha;

    /**
     * Nivel de dolor del episodio
     */
    private double nivelDolor;

    /**
     * Localización del dolor
     */
    private String localizacion;

    /**
     * Intensidad de la dolencia
     */
    private double intensidad;

    /**
     * Nivel de alivio obtenido por los medicamentos
     */
    private double nivelAlivio;

    /**
     * Lista de medicamentos tomados para el episodio
     */
    private List<String> medicamentos;

    /**
     * Alimentos consumidos antes del episodio
     */
    private List<String> alimentos;

    /**
     * Bebidas consumidas antes del episodio
     */
    private List<String> bebidas;

    /**
     * Cédula del paciente que registra el evento
     */
    private int cedula;

    //------------------------------------------------------------------------------------------------------
    // Constructores
    //------------------------------------------------------------------------------------------------------
    /**
     * Constructor de la clase
     */
    public Episodio() {

    }

    /**
     * Constructor de la clase
     *
     * @param pCedula La cédula del paciente que registra el episodio
     * @param dia Dia de ocurrencia del episodio.
     * @param mes Mes de ocurrencia del episodio.
     * @param anhio Año de ocurrencia del episodio.
     * @param hora Hora de ocurrencia del episodio.
     * @param minuto Minuto de ocurrencia del episodio.
     * @param pNivelDolor Nivel del dolor rgistrado para el episodio.
     * pNivelDolor >= 0.
     * @param pLocalizacion Localización del dolor. pLocalizacion != null &&
     * pLocalizacion != "".
     * @param pIntensidad Intensidad del dolor del episodio. pIntensidad >= 0.
     * @param pNivelAlivio Nivel de dolor del episodio. pNivelAlivio >= 0.
     * @param pMedicamentos Medicamentos tomados para el dolor.
     * @param pAlimentos Alimentos consumidos antes del episodio.
     * @param pBebidas Bebidas consumidas antes del episodio.
     */
    public Episodio(int pCedula, String dia, String mes, String anhio, String hora, String minuto, double pNivelDolor, String pLocalizacion, double pIntensidad, double pNivelAlivio, ArrayList<String> pMedicamentos, ArrayList<String> pAlimentos, ArrayList<String> pBebidas) {

        cedula = pCedula;
        fecha = anhio + mes + dia + hora + minuto;
        nivelDolor = pNivelDolor;
        localizacion = pLocalizacion;
        intensidad = pIntensidad;
        nivelAlivio = pNivelAlivio;
        medicamentos = pMedicamentos;
        alimentos = pAlimentos;
        bebidas = pBebidas;

        refactor();

    }

    //------------------------------------------------------------------------------------------------------
    // Métodos
    //------------------------------------------------------------------------------------------------------
    // GETTERS ----------------------------------
    /**
     * Retorna la fecha del episodio
     *
     * @return La fecha del episodio
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Retorna el Id del episodio
     * @return El Id
     */
    public String getId(){
        return id;
    }

    /**
     * Retorna el nuvel de dolor registrado en el episodio
     *
     * @return El nivel de dolor
     */
    public double getNivelDolor() {
        return nivelDolor;
    }

    /**
     * Retorna la localización del dolor
     *
     * @return La localización del dolor
     */
    public String getLocalizacion() {
        return localizacion;
    }

    /**
     * Retorna la intensidad registrada en el episodio
     *
     * @return La intensidad del episodio
     */
    public double getIntensidad() {
        return intensidad;
    }

    /**
     * Retorna el alivio del dolor provecado por los medicamentos
     *
     * @return El alivio del dolor
     */
    public double getAlivio() {
        return nivelAlivio;
    }

    /**
     * Retorna la lista de medicamenos tomados para el episodio
     *
     * @return La lista de medicamentos
     */
    public List<String> getMedicamentos() {
        if (medicamentos == null) {
            medicamentos = new ArrayList<String>();
        }
        return medicamentos;
    }

    /**
     * Retorna la lista de alimentos Consumidas antes del episodio
     *
     * @return La lista de alimentos
     */
    public List<String> getAlimentos() {
        return alimentos;
    }

    /**
     * La lista de bebidas consumidas antes del episodio
     *
     * @return La lista de bebidas
     */
    public List<String> getBebidas() {
        return bebidas;
    }

    /**
     * Retorna la cédula del paciente que registró el episodio
     *
     * @return La cedula del paciente
     */
    public int getCedula() {
        return cedula;
    }

    // SETERS -----------------------------------
    /**
     * Agrega una fecha al episodio
     *
     * @param pFecha La fecha en la que ocurrio el episodio. pFecha =
     * año+mes+dia+hora+minuto
     */
    public void setFecha(String pFecha) {
        fecha = pFecha;
    }
    
    /**
     * Retorna la cédula del paciente que registró el episodio
     * @param pCedula
     */
    public void setCedula(int pCedula) {
        cedula = pCedula;
    }
    
    /**
     * Modifica el Id del episodio
     * @param pId El nuevo Id
     */
    public void setId(String pId){
        id = pId;
    }

    /**
     * Agrega el nivel de dolor al episodio
     *
     * @param pNivelDolor Nivel de dolor del epiosdio
     */
    public void setNivelDolor(double pNivelDolor) {
        nivelDolor = pNivelDolor;
    }

    /**
     * Agrega la localización del dolor al episodio
     *
     * @param pLocalizacion Localización del dolor
     */
    public void setLocalizacion(String pLocalizacion) {
        localizacion = pLocalizacion;
    }

    /**
     * Agrega la intensidad al episodio
     *
     * @param pIntensidad La intensidad del dolor
     */
    public void setIntensidad(double pIntensidad) {
        intensidad = pIntensidad;
    }

    /**
     * Agrega un nivel de alivio dado por los medicamentos
     *
     * @param pNivelAlivio El nivel de alivio
     */
    public void setNivelAlivio(double pNivelAlivio) {
        nivelAlivio = pNivelAlivio;
    }

    /**
     * Agrega una lista de medicamentos al episodio
     *
     * @param pMedicamentos La lista de medicamentos
     */
    public void setMedicamentos(ArrayList<String> pMedicamentos) {
        medicamentos = pMedicamentos;
    }

    /**
     * Agrega una lista de alimento consumidos antes del episodio
     *
     * @param pAlimentos La lista de alimentos
     */
    public void setAlimentos(ArrayList<String> pAlimentos) {
        alimentos = pAlimentos;
    }

    /**
     * Agrega una lista de bebidas consumidas antes del episodio
     *
     * @param pBebidas La lista de bebidas
     */
    public void setBebidas(ArrayList<String> pBebidas) {
        bebidas = pBebidas;
    }

    // REFACTOR -----------------------------------
    /**
     * Inicializa los arreglos si son nulos
     */
    private void refactor() {
        if (medicamentos == null) {
            medicamentos = new ArrayList<String>();
        }
        if (alimentos == null) {
            alimentos = new ArrayList<String>();
        }
        if (bebidas == null) {
            bebidas = new ArrayList<String>();
        }
    }
}
