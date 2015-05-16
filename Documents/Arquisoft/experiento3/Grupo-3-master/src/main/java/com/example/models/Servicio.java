package com.example.models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author estudiante
 */
public class Servicio
{
    private String mensaje;
    public Servicio(String men)
    {
        mensaje = men;
    }
    public String darMensaje()
    {
        return mensaje;
    }
}
