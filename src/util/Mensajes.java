package util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Mensajes {

    public static void mostrarInfo(JFrame ventana, String mensaje) {
        JOptionPane.showMessageDialog(
                ventana,
                mensaje,
                "Informacion",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void mostrarError(JFrame ventana, String mensaje) {
        JOptionPane.showMessageDialog(
                ventana,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public static boolean confirmar(JFrame ventana, String mensaje) {
        int respuesta = JOptionPane.showConfirmDialog(
                ventana,
                mensaje,
                "Confirmacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        return respuesta == JOptionPane.YES_OPTION;
    }
}