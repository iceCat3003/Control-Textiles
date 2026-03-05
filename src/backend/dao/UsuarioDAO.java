/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.dao;

import backend.db.ConexionDB;
import backend.enums.EstadoUsuario;
import backend.enums.NivelAcceso;
import backend.exceptions.CadenaInvalidaException;
import backend.exceptions.CadenaLargaException;
import backend.exceptions.CadenaVaciaException;
import backend.exceptions.NumeroNegativoException;
import backend.exceptions.ParametroNullException;
import backend.modelos.Rol;
import backend.modelos.Usuario;
import backend.modelos.Rol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author icecat
 */
public class UsuarioDAO implements InterfazDAO<Usuario>{

    @Override
    public ArrayList<Usuario> obtenerRegistros() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario obtenerPorID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Usuario loginUsuario(String usuario, String contrasenia){
        Usuario u = null;
        Rol rol = null;
        String sql = "SELECT Usuarios.idUsuario,\n" + "Usuarios.nombre1, "+
                "Usuarios.apellido1, " +
                "Usuarios.apellido2, " +
                "Usuarios.telefono, " +
                "Usuarios.usuario, " +
                "Usuarios.contrasenia, " +
                "Usuarios.estadoUsuario, " +
                "Usuarios.nivelAcceso, " +
                "Usuarios.idRol, " +
                "Roles.nombreRol, " +
                "Usuarios.imagen, " +
                "Usuarios.salario " +
                "FROM Usuarios " +
                "INNER JOIN Roles ON Usuarios.idRol=Roles.idRol " +
                "WHERE Usuarios.usuario=? AND Usuarios.contrasenia=?";
        ConexionDB conexion = new ConexionDB();
        try (
                Connection con = conexion.conectar();
                PreparedStatement prep = con.prepareStatement(sql);
                ) {
            prep.setString(1, usuario);
            prep.setString(1, contrasenia);
            ResultSet rs = prep.executeQuery();
            try {
                while (rs.next()) {
                    u.setIdUsuario(rs.getInt("idUsuario"));
                    u.setNombre1(rs.getString("nombre1"));
                    u.setNombre2(rs.getString("nombre2"));
                    u.setApellido1(rs.getString("apellido1"));
                    u.setApellido2(rs.getString("apellido2"));
                    u.setTelefono(rs.getString("telefono"));
                    u.setUsuario(rs.getString("usuario"));
                    u.setContrasenia(rs.getString("contrasenia"));
                    u.setEstadoUsuario(EstadoUsuario.valueOf(rs.getString("estadoUsuario")));
                    u.setNivelAcceso(NivelAcceso.valueOf(rs.getString("nivelAcceso")));
                    rol.setIdRol(rs.getInt("idRol"));
                    rol.setNombreRol(rs.getString("nombreRol"));
                    u.setRol(rol);
                    u.setImagen(rs.getBytes("imagen"));
                    u.setSalario(rs.getBigDecimal("salario"));
                }
            } catch (ParametroNullException ex) {
                System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (CadenaLargaException ex) {
                System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (CadenaInvalidaException ex) {
                System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (CadenaVaciaException ex) {
                System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (NumeroNegativoException ex) {
                System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } catch (SQLException ex) {
            System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return u;
    }

    @Override
    public boolean insertar(Usuario objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(Usuario objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean borrar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
