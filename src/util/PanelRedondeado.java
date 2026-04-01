package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class PanelRedondeado extends JPanel {

    // Color de fondo y radio de las esquinas del panel
    private Color colorFondo;
    private int radio;

    // Constructor para definir el estilo del panel
    public PanelRedondeado(Color colorFondo, int radio) {
        this.colorFondo = colorFondo;
        this.radio = radio;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Suaviza los bordes del panel redondeado
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibuja el fondo con esquinas redondeadas
        g2d.setColor(colorFondo);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);

        g2d.dispose();
        super.paintComponent(g);
    }
}