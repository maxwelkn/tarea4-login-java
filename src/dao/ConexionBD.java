package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // Instancia unica de la clase para reutilizar la conexion
    private static ConexionBD instancia;
    private Connection conexion;

    // Datos de conexion a la base de datos
    private final String URL = "jdbc:mysql://localhost:3306/tarea4_login";
    private final String USUARIO = "root";
    private final String CONTRASENA = "Maxwel1234@";

    private ConexionBD() {
        try {
            // Establece la conexion con MySQL
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexion exitosa a la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    // Devuelve la unica instancia de la clase
    public static ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    // Devuelve la conexion creada
    public Connection getConexion() {
        return conexion;
    }
}