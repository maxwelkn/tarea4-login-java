package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class PanelRedondeado extends JPanel {

    private Color colorFondo;
    private int radio;

    public PanelRedondeado(Color colorFondo, int radio) {
        this.colorFondo = colorFondo;
        this.radio = radio;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(colorFondo);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);

        g2d.dispose();
        super.paintComponent(g);
    }
}