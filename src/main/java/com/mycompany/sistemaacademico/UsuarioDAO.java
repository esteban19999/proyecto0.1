/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaacademico;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author -PC
 */
public class UsuarioDAO implements DAO {

    private final Connection conexion;

    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean insertar(Object registro) {
        Usuario usuario = (Usuario) registro;
        String sql = "INSERT INTO usuarios(nombre, password, rol, imagenURL) VALUES (?, ?, ?, ?)";

        try {
            var ps = conexion.prepareStatement(sql);

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getRol().toString());
            ps.setString(4, usuario.getImagenURL());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Object registro) {
        Usuario usuario = (Usuario) registro;
        String sql = "UPDATE usuarios SET nombre = ?, password = ?, rol = ?, imagenURL = ? WHERE idUsuario = ?";

        try {
            var ps = conexion.prepareStatement(sql);

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getRol().toString());
            ps.setString(4, usuario.getImagenURL());
            ps.setInt(5, usuario.getIdUsuario());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE idUsuario = ?";

        try {
            var ps = conexion.prepareStatement(sql);

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Usuario> listar() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        String sql = "SELECT * FROM usuarios";
        
        try {
            var ps = conexion.prepareStatement(sql);
            
            var rs = ps.executeQuery();
            
            while(rs.next()){
                Usuario usuario = new Usuario();
                
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRol(Rol.valueOf(rs.getString("rol")));
                usuario.setImagenURL(rs.getString("imagenURL"));
                
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al listar");
        } 

        return usuarios;
    }

    @Override
    public Usuario buscarPorID(int id) {
        String sql = "SELECT * FROM usuarios WHERE idUsuario = ?";
        
        try {
            var ps = conexion.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            var rs = ps.executeQuery();
            
            if(rs.next()){
                Usuario usuario = new Usuario();
                
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRol(Rol.valueOf(rs.getString("rol")));
                usuario.setImagenURL(rs.getString("imagenURL"));
                
                return usuario;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 

        return null;         
    }

}
