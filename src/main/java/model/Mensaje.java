/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author idurfer
 */
public class Mensaje implements Serializable {
    
    private String usuarioOrigen;
    private String texto;

    public Mensaje(String usuarioOrigen, String texto) {
        this.usuarioOrigen = usuarioOrigen;
        this.texto = texto;
    }

    public String getUsuarioOrigen() {
        return usuarioOrigen;
    }

    public String getTexto() {
        return texto;
    }

    @Override
    public String toString() {
        return usuarioOrigen + ": " + texto;
    }
}