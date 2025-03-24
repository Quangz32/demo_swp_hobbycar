package org.hobby_car;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import org.hobby_car.DriverSimulation;
import org.hobby_car.output.EncodedMotor;

public class CarGame extends JFrame {
    private CarPanel carPanel;
    private Controller controller;

    public CarGame() {
        setTitle("Dieu khien o to");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        controller = new Controller();
        controller.start();

        carPanel = new CarPanel(controller);
        add(carPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CarGame());
    }
}

class CarPanel extends JPanel implements KeyListener {
    private int carX = 400;
    private int carY = 300;
    private float carAngle = 0;
    private int speed = 0;
    private boolean isMoving = false;
    private final int CAR_WIDTH = 60;
    private final int CAR_HEIGHT = 120;
    private Controller controller;
    EncodedMotor encodedMotor = new EncodedMotor(0);


    public CarPanel(Controller controller) {
        this.controller = controller;
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        AffineTransform originalTransform = g2d.getTransform();

        g2d.translate(carX, carY);
        g2d.rotate(Math.toRadians(carAngle));

        // Vẽ thân xe
        g2d.setColor(Color.BLUE);
        g2d.fillRect(-CAR_WIDTH/2, -CAR_HEIGHT/2, CAR_WIDTH, CAR_HEIGHT);
        
        // Vẽ cửa sổ
        g2d.setColor(Color.WHITE);
        g2d.fillRect(-CAR_WIDTH/3, -CAR_HEIGHT/3, CAR_WIDTH/2, CAR_HEIGHT/3);

        g2d.setTransform(originalTransform);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                controller.getRemote().setAccelerating(true);
                speed = 5;
                isMoving = true;
                System.out.println("Tăng tốc - Tốc độ: " + speed);
                break;
            case KeyEvent.VK_DOWN:
                controller.getRemote().setBraking(true);
                speed = 0;
                isMoving = true;
                System.out.println("Phanh - Tốc độ: " + speed);
                break;
            case KeyEvent.VK_LEFT:
                controller.getRemote().setTurningLeft(true);
                encodedMotor.rotateLeft();
                carAngle = encodedMotor.getAngle();
                System.out.println("Rẽ trái - Góc: " + carAngle);
                break;
            case KeyEvent.VK_RIGHT:
                controller.getRemote().setTurningRight(true);
                encodedMotor.rotateRight();
                carAngle = encodedMotor.getAngle();
                System.out.println("Rẽ phải - Góc: " + carAngle);
                break;
            case KeyEvent.VK_SPACE:
                speed = 0;
                isMoving = false;
                System.out.println("Dừng xe");
                break;
        }

        if (isMoving) {
            double rad = Math.toRadians(carAngle);
            carX += (int) (speed * Math.sin(rad));
            carY -= (int) (speed * Math.cos(rad));

            carX = Math.max(CAR_WIDTH/2, Math.min(getWidth() - CAR_WIDTH/2, carX));
            carY = Math.max(CAR_HEIGHT/2, Math.min(getHeight() - CAR_HEIGHT/2, carY));
            
            System.out.println("Vị trí xe - X: " + carX + ", Y: " + carY);
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        // Reset trạng thái khi thả phím
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                controller.getAcceleratorPedal().setAccelerating(false);
                break;
            case KeyEvent.VK_DOWN:
                controller.getBrakePedal().setBraking(false);
                break;
        }
    }
}
