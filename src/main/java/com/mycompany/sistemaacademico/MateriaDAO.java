/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaacademico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MateriaDAO {

    private final Connection conexion;

    public MateriaDAO(java.sql.Connection conexion) {
        this.conexion = conexion;
    }
    public java.util.ArrayList<String[]> obtenerMateriasPorDocente(int idDocente) {
    java.util.ArrayList<String[]> lista = new java.util.ArrayList<>();
    String sql = "SELECT m.codigo, m.nombre FROM materias m " +
                 "INNER JOIN docente_materias dm ON m.id = dm.materia_id " +
                 "WHERE dm.docente_id = ?";
    try (java.sql.PreparedStatement ps = this.conexion.prepareStatement(sql)) {
        ps.setInt(1, idDocente);
        try (java.sql.ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String[] materia = {rs.getString("codigo"), rs.getString("nombre")};
                lista.add(materia);
            }
        }
    } catch (java.sql.SQLException e) {
        System.out.println("Error al obtener materias: " + e.getMessage());
    }
    return lista;
}


    public boolean registrarMateria(String nombre, String codigo) {
        String sql = "INSERT INTO materias (nombre, codigo) VALUES (?, ?)";
        try (PreparedStatement ps = this.conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al registrar materia: " + e.getMessage());
            return false;
        }
    }

    public boolean asignarMateriaADocente(int docenteId, int materiaId) {
        String sql = "INSERT INTO docente_materias (docente_id, materia_id) VALUES (?, ?)";
        try (PreparedStatement ps = this.conexion.prepareStatement(sql)) {
            ps.setInt(1, docenteId);
            ps.setInt(2, materiaId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al asignar materia a docente: " + e.getMessage());
            return false;
        }
    }

    public boolean asignarMateriaAEstudiante(int estudianteId, int materiaId) {
        String sql = "INSERT INTO estudiante_materias (estudiante_id, materia_id) VALUES (?, ?)";
        try (PreparedStatement ps = this.conexion.prepareStatement(sql)) {
            ps.setInt(1, estudianteId);
            ps.setInt(2, materiaId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al asignar materia a estudiante: " + e.getMessage());
            return false;
        }
    }
}

