/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaacademico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MateriaDAO {

    private final Connection conexion;

    // Constructor que recibe tu conexión activa
    public MateriaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // 1. Obtener las materias asignadas a un docente específico
    public ArrayList<String[]> obtenerMateriasPorDocente(int idDocente) {
        ArrayList<String[]> lista = new ArrayList<>();
        String sql = "SELECT m.codigo, m.nombre FROM materias m " +
                     "INNER JOIN docente_materias dm ON m.id = dm.materia_id " +
                     "WHERE dm.docente_id = ?";
        try (PreparedStatement ps = this.conexion.prepareStatement(sql)) {
            ps.setInt(1, idDocente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String[] materia = {rs.getString("codigo"), rs.getString("nombre")};
                    lista.add(materia);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener materias por docente: " + e.getMessage());
        }
        return lista;
    }

    // 2. Registrar una nueva materia en el sistema
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

    // 3. Asignar una materia a un docente en la tabla intermedia
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

    // 4. Asignar una materia a un estudiante (¡Ahora compatible con tu base de datos!)
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

    // 5. Obtener TODAS las materias (Esencial para llenar tus tablas visuales de JTable)
    public ArrayList<String[]> obtenerTodasLasMaterias() {
        ArrayList<String[]> lista = new ArrayList<>();
        String sql = "SELECT codigo, nombre FROM materias";
        try (PreparedStatement ps = this.conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String[] materia = {rs.getString("codigo"), rs.getString("nombre")};
                lista.add(materia);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todas las materias: " + e.getMessage());
        }
        return lista;
    }

    // 6. Eliminar una materia usando su código único
    public boolean eliminar(String codigoMateria) {
        String sql = "DELETE FROM materias WHERE codigo = ?";
        try (PreparedStatement ps = this.conexion.prepareStatement(sql)) {
            ps.setString(1, codigoMateria);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar materia: " + e.getMessage());
            return false;
        }
    }
}

