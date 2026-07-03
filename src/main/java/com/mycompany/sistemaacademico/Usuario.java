/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaacademico;

/**
 *
 * @author -PC
 */
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String password;
    private Rol rol;
    private String imagenURL = "";

    public Usuario(int idUsuario, String nombre, String password, Rol rol, String imagenURL) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
        this.imagenURL = imagenURL;
    }
    
    public Usuario(String nombre, String password, Rol rol, String imagenURL) {
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
        this.imagenURL = imagenURL;
    }    

    public Usuario(String nombre, String password, Rol rol) {
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
    }

    public Usuario() {
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + 
                 ", rol=" + rol + ", imagenURL=" + imagenURL + '}';
    }
    
    
}
