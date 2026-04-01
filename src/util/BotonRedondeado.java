package util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class BotonRedondeado extends JButton {

    // Constructor que aplica el estilo base del boton
    public BotonRedondeado(String texto) {
        super(texto);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Suaviza los bordes para que el boton se vea mas limpio
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Cambia el color cuando el boton esta siendo presionado
        if (getModel().isArmed()) {
            g2d.setColor(new Color(30, 110, 200));
        } else {
            g2d.setColor(new Color(40, 130, 230));
        }

        // Dibuja el fondo redondeado del boton
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(g2d);
        g2d.dispose();
    }
}