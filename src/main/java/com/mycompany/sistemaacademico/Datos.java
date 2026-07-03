package com.mycompany.sistemaacademico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Datos {
    public static java.sql.Connection conexion;

    public static java.sql.Connection getConexion() {
        return conexion;
    }

    private Usuario usuarioActual;
    private static Datos instancia;
    
    private final String URL = "jdbc:mysql://localhost:3306/bd";
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

    public ArrayList<String[]> obtenerTodosLosDocentes() {
        ArrayList<String[]> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM docentes";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String[] docente = {String.valueOf(rs.getInt("id")), rs.getString("nombre")};
                lista.add(docente);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }

    public ArrayList<String[]> obtenerTodosLosEstudiantes() {
        ArrayList<String[]> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM estudiantes";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String[] estudiante = {String.valueOf(rs.getInt("id")), rs.getString("nombre")};
                lista.add(estudiante);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
}
