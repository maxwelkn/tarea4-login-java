package util;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PanelDegradado extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int ancho = getWidth();
        int alto = getHeight();

        // Crea un fondo con efecto degradado
        GradientPaint gp = new GradientPaint(
                0, 0, new Color(170, 220, 235),
                ancho, alto, new Color(0, 120, 190)
        );

        // Pinta el panel con el degradado
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, ancho, alto);
    }
}