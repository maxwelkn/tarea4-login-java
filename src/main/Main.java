package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.UIManager;

import vista.LoginFrame;

public class Main {
    public static void main(String[] args) {
        // Personaliza los colores de fondo de los mensajes emergentes
        UIManager.put("OptionPane.background", new Color(245, 247, 250));
        UIManager.put("Panel.background", new Color(245, 247, 250));

        // Cambia la fuente del texto y botones de los JOptionPane
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 13));

        // Define el estilo general de los botones
        UIManager.put("Button.background", new Color(52, 152, 219));
        UIManager.put("Button.foreground", Color.WHITE);

        // Inicia la ventana de login
        new LoginFrame();
    }
}