/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.modelos;

/**
 *
 * @author icecat
 */
public class Rol {
    private int idRol;
    private String nombreRol;

    public Rol() {}

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        if(nombreRol!=null || nombreRol.length()>0) {
            return nombreRol;
        } else {
            return "NO DISPONIBLE";
        }
    }

    public void setNombreRol(String nombreRol) {
        if(nombreRol.length()>0) {
            this.nombreRol = nombreRol.trim();
        } else {
            this.nombreRol = null;
        }
    }
    
    
}
