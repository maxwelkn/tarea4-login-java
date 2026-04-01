package modelo;

public class Usuario extends Persona {
    // Datos propios del usuario en el sistema
    private int id;
    private String nombreUsuario;
    private String contrasena;

    // Constructor vacio
    public Usuario() {
    }

    // Constructor con id, usado cuando el usuario ya existe en la base de datos
    public Usuario(int id, String nombreUsuario, String nombre, String apellido, String telefono, String correo, String contrasena) {
        super(nombre, apellido, telefono, correo);
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    // Constructor sin id, usado al registrar un nuevo usuario
    public Usuario(String nombreUsuario, String nombre, String apellido, String telefono, String correo, String contrasena) {
        super(nombre, apellido, telefono, correo);
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public int getId() {
        return id;
    }

    // Asigna el id del usuario
    public void setId(int id) {
        this.id = id;
    } 

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    // Asigna el nombre de usuario
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    // Asigna la contrasena del usuario
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}