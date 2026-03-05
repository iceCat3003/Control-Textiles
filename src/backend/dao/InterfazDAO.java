/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package backend.dao;

import java.util.ArrayList;

/**
 *
 * @author icecat
 */
public interface InterfazDAO<T> {
    public ArrayList <T> obtenerRegistros();
    public T obtenerPorID(int id);
    public boolean insertar (T objeto);
    public boolean actualizar (T objeto);
    public boolean borrar (int id);
}
