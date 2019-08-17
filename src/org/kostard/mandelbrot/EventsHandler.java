package org.kostard.mandelbrot;

import java.awt.event.*;

public class EventsHandler implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
    private Main main;
    private int x, y;
    public EventsHandler(Main main) {
        this.main = main;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        float dx = (x - mouseEvent.getX()) * 2f;
        float dy = (y - mouseEvent.getY()) * 1.5f;
        x = mouseEvent.getX();
        y = mouseEvent.getY();

        main.moveCamera(dx / (main.WIDTH * main.zoom), dy / (main.HEIGHT * main.zoom));
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        float d = 1 - (float) mouseWheelEvent.getUnitsToScroll() / 10f;
        main.increaseZoom(d);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyChar()) {
            case '+':
                main.increaseZoom(1.3f);
                break;
            case '-':
                main.increaseZoom(0.8f);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
