package org.kostard.mandelbrot;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main extends JComponent {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private BufferedImage buffer;
    public float centerPosX = 0;
    public float centerPosY = 0;
    public float zoom = 0.6f;

    public static void main(String[] args) {
        Main main = new Main();
    }
    private JFrame frame;

    public Main() {
        frame = new JFrame("Mandelbrot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        renderMandelbrot();

        EventsHandler eventsHandler = new EventsHandler(this);

        frame.addMouseListener(eventsHandler);
        frame.addMouseMotionListener(eventsHandler);
        frame.addMouseWheelListener(eventsHandler);
        frame.addKeyListener(eventsHandler);
        frame.setResizable(false);
        frame.setContentPane(this);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void addNotify() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    private Color calculateColorForPoint(int pX, int pY) {
        float w = (float) WIDTH * zoom / 2;
        float h = (float) HEIGHT * zoom / 2;
        float d = Math.max(w, h);
        float x0 = centerPosX + (float) (pX - WIDTH / 2) / d;
        float y0 = centerPosY + (float) (pY - HEIGHT / 2) / d;

        float x = 0, y = 0;

        int iterations = 0;
        int max_iterations = 100;
        float xTemp;

        while (x * x + y * y <= 4 && iterations < max_iterations) {
            xTemp = x * x - y * y + x0;
            y = 2 * x * y + y0;
            x = xTemp;
            iterations++;
        }
        return new Color(0, iterations * 255 / max_iterations, iterations * 255 / max_iterations);
    }

    private void renderMandelbrot() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                buffer.setRGB(x, y, calculateColorForPoint(x, y).getRGB());
            }
        }
    }

    public void moveCamera(float dx, float dy) {
        centerPosX += dx;
        centerPosY += dy;
        renderMandelbrot();
        frame.repaint();
    }

    public void increaseZoom(float dz) {
        zoom *= dz;
        renderMandelbrot();
        frame.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(buffer, 0, 0, null);
    }
}
