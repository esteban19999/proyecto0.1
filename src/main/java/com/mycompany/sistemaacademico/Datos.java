/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaacademico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author -PC
 */
public class Datos {
    private Usuario usuarioActual;
    private static Datos instancia;
    private Connection conexion;
    
    private final String URL = "jdbc:mysql://localhost:3306/db";
    private final String USER = "user";
    private final String PASSWORD = "1234";    

    public Datos() {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion exitosa");
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            System.out.println(ex.getMessage());
            System.out.println("Conexion fallida");
        }        
    }
    
    public static Datos getInstancia(){
        if(instancia == null) instancia = new Datos();
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    
    public String validarCredenciales(String nombreUsuario, char[] passwordUsuario){
        UsuarioDAO dao = new UsuarioDAO(conexion);
        for(Usuario u : dao.listar()){
            if(u.getNombre().equals(nombreUsuario)){
                if(u.getPassword().length() != passwordUsuario.length){
                    System.out.println("Longitud Incorrecta");
                    return "Contraseña Incorrecta";
                }
                for(int i = 0;i < passwordUsuario.length;i++){
                    if(u.getPassword().charAt(i) != passwordUsuario[i]){
                        System.out.println("Contraseña Incorrecta");
                        return "Contraseña Incorrecta";
                    }
                }
                usuarioActual = u;
                System.out.println("Contraseña Correcta");
                return "";
            }
        }
        System.out.println("Usuario incorrecto");
        return "Usuario incorrecto";
    }
    
    public String convertirCharAString(char[] arreglo){
        String resultado = "";
        for(char c : arreglo){
            resultado += c;
        }
        return resultado;    
    }
}
