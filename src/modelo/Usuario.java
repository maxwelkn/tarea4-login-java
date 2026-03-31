package modelo;

public class Usuario extends Persona {
    private int id;
    private String nombreUsuario;
    private String contrasena;

    public Usuario() {
    }

    public Usuario(int id, String nombreUsuario, String nombre, String apellido, String telefono, String correo, String contrasena) {
        super(nombre, apellido, telefono, correo);
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public Usuario(String nombreUsuario, String nombre, String apellido, String telefono, String correo, String contrasena) {
        super(nombre, apellido, telefono, correo);
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}