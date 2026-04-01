package modelo;

public class Persona {
    // Datos personales basicos del usuario
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;

    // Constructor vacio
    public Persona() {
    }

    // Constructor para inicializar los datos personales
    public Persona(String nombre, String apellido, String telefono, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    // Asigna el nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    // Asigna el apellido
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    // Asigna el telefono
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    // Asigna el correo
    public void setCorreo(String correo) {
        this.correo = correo;
    }
}