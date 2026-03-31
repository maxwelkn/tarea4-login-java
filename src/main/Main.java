package main;

import dao.ConexionBD;

public class Main {
    public static void main(String[] args) {
        ConexionBD conexionBD = ConexionBD.getInstancia();

        if (conexionBD.getConexion() != null) {
            System.out.println("La aplicacion se conecto correctamente a MySQL.");
        } else {
            System.out.println("No se pudo conectar a MySQL.");
        }
    }
}