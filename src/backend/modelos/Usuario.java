/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.modelos;

import backend.enums.NivelAcceso;
import backend.enums.EstadoUsuario;
import backend.exceptions.CadenaInvalidaException;
import backend.exceptions.CadenaLargaException;
import backend.exceptions.CadenaVaciaException;
import backend.exceptions.NumeroNegativoException;
import backend.exceptions.ParametroNullException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Clase contenedora de atributos para la tabla Usuarios de la base de datos
 * 
 * @author Daniel Espinoza Cortés
 */
public class Usuario {
    private int idUsuario;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String usuario;
    private String contrasenia;
    private EstadoUsuario estadoUsuario;
    private NivelAcceso nivelAcceso;
    private Rol rol;
    private byte[] imagen;
    private BigDecimal salario;

    public Usuario() {}

    /**
     * Devuelve el idUsuario del Usuario
     * 
     * @return el campo idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el idUsuario del Usuario
     * 
     * @param idUsuario 
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene unicamente el campo del primer nombre del Usuario
     * 
     * @return el campo nombre1
     */
    public String getNombre1() {
        return nombre1;
    }
    
    /**
     * <p>Guarda el primer nombre del usuario siguiendo las reglas y validaciones
     * de la base de datos
     * 
     * @param nombre1
     *          Es el nombre1 como se especifica en la base de datos
     * 
     * @throws CadenaInvalidaException
     *         <p>Cuando la cadena contiene caracteres que no sean alfanumericos, 
     *         vocales con tilde o espacio
     * 
     * @throws CadenaLargaException
     *         <p>Cuando la longitud de la cadena excede 20 caracteres
     * 
     * @throws CadenaVaciaException
     *         <p>Cuando la cadena está vacía o el parámetro es null
    */
    public void setNombre1(String nombre1)
            throws CadenaInvalidaException, CadenaLargaException, CadenaVaciaException {
        if (nombre1!=null && !nombre1.isBlank()) {
            if(nombre1.length() <= 20) {
                if (nombre1.matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ\\s'-]+$")) {
                    this.nombre1 = nombre1.trim();
                } else {
                    throw new CadenaInvalidaException(
                            "El primer nombre solamente puede contener letras y espacios");
                }
            } else {
                throw new CadenaLargaException(
                        "El primer nombre debe tener un máximo de 20 caracteres");
            }
        } else {
            throw new CadenaInvalidaException(
                    "El primer nombre no puede estar vacío");
        }
    }

    /**
     * Devuelve el segundo nombre del Usuario
     * 
     * @return el campo nombre2 que podrá ser null
     */
    public String getNombre2() {
        return nombre2;
    }

    /**
     * <p>Guarda el segundo nombre del usuario siguiendo las reglas y validaciones 
     * de la base de datos
     * 
     * @param nombre2
     *          <p>Este es el nombre2 como se especifica en la base de datos. 
     *          El parámetro puede ser null
     * 
     * @throws CadenaInvalidaException
     *         <p>cuando contiene números o si contiene caracteres especiales 
     *         (ñ y vocales con acento son válidos)
     * 
     * @throws CadenaLargaException
     *         <p>Cuando la cadena excede 20 caracteres
    */
    public void setNombre2(String nombre2)
            throws CadenaInvalidaException, CadenaLargaException{
        if (nombre2 != null && !nombre2.isBlank()) {
            if (nombre2.length() <= 20) {
                if (nombre2.matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ\\s'-]+$")) {
                    this.nombre2 = nombre2.trim();
                } else {
                    throw new CadenaInvalidaException(
                            "El segundo nombre solamente puede contener letras y espacios");
                }
            } else {
                throw new CadenaLargaException(
                        "El segundo nombre debe tener un máximo de 20 caracteres");
            }
        } else {
            this.nombre2 = null;
        }
    }
    
    /**
     * Devuelve el primer nombre y el segundo nombre del Usuario separado
     * por un espacio como una sola cadena
     * 
     * @return nombre1+" "+nombre2 o si nombre2 es null entonces nombre1
     */
    public String getNombres() {
        return nombre2 != null? nombre1+" "+nombre2 : nombre1;
    }

    /**
     * Devuelve el primer apellido del Usuario
     * 
     * @return el campo apellido1
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Establece el primer apellido del Usuario, no puede ser null
     * 
     * @param apellido1
     *         Correspondiente a la columna apellido1 en la base de datos
     * 
     * @throws CadenaInvalidaException 
     *         <p>Cuando el parametro contiene números o símbolos
     * 
     * @throws CadenaLargaException
     *         <p>Cuando la longitud de la cadena excede 20 caracteres
     * 
     * @throws CadenaVaciaException
     *         <p>Cuando la cadena está vacía o es null
     */
    public void setApellido1(String apellido1)
            throws CadenaInvalidaException, CadenaLargaException, CadenaVaciaException {
        if (apellido1 != null && !apellido1.isBlank()) {
            if (apellido1.length() <= 20) {
                if (apellido1.matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ\\s'-]+$")) {
                    this.apellido1 = apellido1.trim();
                } else {
                    throw new CadenaInvalidaException(
                            "El primer apellido solamente puede contener letras y espacios");
                }
            } else {
                throw new CadenaLargaException(
                        "El primer apellido debe tener un máximo de 20 caracteres");
            }
        } else {
            throw new CadenaVaciaException(
                    "El primer apellido no puede estar vacío");
        }
    }

    /**
     * Devuelve el segundo apellido del Usuario
     * 
     * @return apellido2 que podrá ser null
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Establece el segundo apellido del Usuario, éste puede ser null
     * 
     * @param apellido2
     *         Una cadena que solamente puede contener letras mayúsculas, 
     *         minúsculas y espacios, puede ser null
     * 
     * @throws CadenaInvalidaException 
     *         <p>Cuando la cadena contiene números o símbolos
     * 
     * @throws CadenaLargaException
     *         <p>Cuando la longitud de la cadena excede 20 caracteres
     */
    public void setApellido2(String apellido2)
            throws CadenaInvalidaException,
            CadenaLargaException{
        if (apellido2 != null && !apellido2.isBlank()) {
            if (apellido2.length() <= 20) {
                if (apellido2.matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ\\s'-]+$")) {
                    this.apellido2 = apellido2.trim();
                } else {
                    throw new CadenaInvalidaException(
                            "El segundo apellido solamente puede contener letras y espacios");
                }
            } else {
                throw new CadenaLargaException(
                        "El segundo apellido debe tener un máximo de 20 caracteres");
            }
        } else {
            this.apellido2 = null;
        }
    }
    
    /**
     * Devuelve una cadena que contiene el primer y segundo apellido del Usuario
     * separados por un espacio, si el segundo apellido es null solamente
     * devuelve el primer apellido
     * 
     * @return apellido1+" "+apellido2 o si apellido2 es null, apellido1
     */
    public String getApellidos() {
        return apellido2 == null? apellido1 : apellido1+" "+apellido2;
    }
    
    /**
     * Devuelve una cadena con primer nombre, segundo nombre, primer apellido y
     * segundo apellido del usuario separado por espacios
     * 
     * @return nombre1+" "+nombre2+" "+apellido1+" "+apellido2
     */
    public String getNombreCompleto() {
        return getNombres()+" "+getApellidos();
    }

    /**
     * Devuelve el número de teléfono del Usuario
     * 
     * @return el campo telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * <p>Establece el número de teléfono del Usuario de acuerdo a las condiciones
     * establecidas por la base de datos
     * 
     * @param telefono
     *         <p>Solo admite valores numéricos, la longitud de la cadena siempre
     *         debe ser de 10 caracteres
     * @throws CadenaInvalidaException 
     *         <p>Cuando la cadena no contiene exactamente 10 caracteres o contiene
     *         caracteres no numericos
     * 
     * @throws CadenaVaciaException
     *         <p>Cuando la cadena está vacía
     * 
     * @throws ParametroNullException
     *         <p>Cuando el parametro es null
     */
    public void setTelefono(String telefono)
            throws CadenaInvalidaException,
            CadenaVaciaException,
            ParametroNullException {
        if (telefono !=null) {
            if (!telefono.isBlank()) {
                if (telefono.length()==10 && telefono.chars().allMatch(Character::isDigit)) {
                    this.telefono = telefono;
                } else {
                    throw new CadenaInvalidaException(
                            "El numero de teléfono de teléfono debe contener solamente "
                                    + "10 numeros, sin espacios ni guiones u otros símbolos");
                }
            } else {
                throw new CadenaVaciaException("El número de teléfono está vacio");
            }
        } else {
            throw new ParametroNullException("El teléfono no puede ser null");
        }
    }

    public String getUsuario() {
        return usuario;
    }

    /**
     * <p>Establece el nombre de usuario validando que no contenga caracteres
     * fuera de letras, números y guión bajo
     * 
     * @param usuario
     *          <p>Conforme a las condiciones establecidas por la base de datos
     * 
     * @throws CadenaInvalidaException
     *          <p>Cuando la cadena contiene caracteres que no son alfanumericos
     *          o guión bajo 
     * 
     * @throws CadenaLargaException
     *         <p>Cuando la longitud de la cadena excede 20 caracteres
     * 
     * @throws CadenaVaciaException
     *         <p>Cuando la cadena está vacía
     * 
     * @throws ParametroNullException
     *         <p>Cuando el parametro es null
     */
    public void setUsuario(String usuario)
            throws CadenaInvalidaException,
            CadenaLargaException,
            CadenaVaciaException,
            ParametroNullException {
        if (usuario != null) {
            if (!usuario.isBlank()) {
                if (usuario.length()<=20) {
                    if(usuario.matches("^[A-Za-z0-9_]+$")) {
                        this.usuario = usuario.trim();
                    } else {
                        throw new CadenaInvalidaException(
                                "El nombre de usuario solo puede contener letras, "
                                        + "números y guión bajo");
                    }
                } else {
                    throw new CadenaLargaException(
                            "El nombre de usuario debe tener un máximo de 20 caracteres");
                }
            } else {
                throw new CadenaVaciaException("El nombre de usuario es obligatorio");
            }
        } else {
            throw new ParametroNullException("El nombre de usuario no puede ser null");
        }
    }

    /**
     * Devuelve la contraseña del Usuario en texto plano
     * 
     * @return el campo contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Establece la contraseña del Usuario
     * 
     * @param contrasenia
     *          Puede contener un máximo de 255 caracteres
     * 
     * @throws CadenaLargaException
     *         Cuando la longitud de la cadena excede 255 caracteres
     */
    public void setContrasenia(String contrasenia) throws CadenaLargaException {
        if (contrasenia.length() <= 255) {
            this.contrasenia = contrasenia;
        } else {
            throw new CadenaLargaException(
                    "La contraseña debe contener un máximo de 255 caracteres");
        }
    }

    /**
     * <p>Devuelve el valor del enum correspondiente al estatus del Usuario: 
     * ACTIVO, INACTIVO, etc.
     * @return el campo enum estadoUsuario
     */
    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    /**
     * Establece el estatus del Usuario
     * @param estadoUsuario 
     *          <p>Debe corresponder con el un posible valor en la base de datos
     */
    public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    /**
     * Devuelve el nivel de acceso para la aplicación del Usuario
     * 
     * @return un valor correspondiente a los niveles de acceso
     */
    public NivelAcceso getNivelAcceso() {
        return nivelAcceso;
    }

    /**
     * Establece el nivel de acceso del Usuario
     * 
     * @param nivelAcceso 
     */
    public void setNivelAcceso(NivelAcceso nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    /**
     * <p>Devuelve un objeto Rol, debido a que los roles de usuario son una tabla
     * 
     * @return 
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * <p>Establece el objeto Rol del Usuario
     * 
     * @param rol 
     *         El rol deberá tener sus atributos inicializados
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * <p>Devuelve el arreglo de bytes correspondiente a la imagen de Usuario
     * 
     * @return Una imagen como arreglo de bytes
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * <p>Establece la imagen de Usuario como arreglo de bytes
     * 
     * @param imagen 
     *         imagen convertida a byte[]
     */
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    /**
     * Devuelve el valor del salario del Usuario
     * @return un BigDecimal con el salario del Usuario
     */
    public BigDecimal getSalario() {
        return salario;
    }

    /**
     * Establece el salario del Usuario
     * 
     * @param salario 
     *         <p>Debe ser un valor con 10 posiciones enteras y 2 de decimales,
     *         conforma a la base de datos
     * 
     * @throws NumeroNegativoException
     *         <p>Cuando el valor es negativo
     * 
     * @throws IllegalArgumentException
     *         <p>Cuando el formato DECIMAL(10,2) de MySQL no se cumple
     */
    public void setSalario(BigDecimal salario)
            throws NumeroNegativoException,
            ParametroNullException {
        if (salario.compareTo(BigDecimal.ZERO)<0){
            throw new NumeroNegativoException("El salario debe ser 0 mayor");
        }
        this.salario.setScale(2, RoundingMode.HALF_UP);
        
        BigInteger parteEntera = salario.toBigInteger();
        int digitosEnteros = parteEntera.toString().length();
        int digitosDecimales = salario.scale();
        
        if (digitosEnteros > 10) {
            throw new IllegalArgumentException("La parte entera no puede tener más de 10 dígitos");
        }
        if (digitosDecimales > 2) {
            throw new IllegalArgumentException("El valor no puede tener más de 2 decimales");
        }
        
        this.salario = salario;
    }
    
}
