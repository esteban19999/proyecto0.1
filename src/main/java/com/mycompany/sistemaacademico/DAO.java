/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sistemaacademico;

import java.util.ArrayList;

/**
 *
 * @author -PC
 * @param <T>
 */
public interface DAO<E> {
    
    boolean insertar(E registro);
    boolean actualizar(E registro);
    boolean eliminar(int id);
    ArrayList<E> listar();
    E buscarPorID(int id);
    
}
