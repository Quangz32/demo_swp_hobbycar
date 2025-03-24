package org.hobby_car;

import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.awt.*;

public class CarPanel extends JPanel {

  private Controller controller;
  private float x, y, heading; // Car's position and heading (direction)
  private int carWidth = 40;  // Width of the car
  private int carHeight = 80; // Height of the car

  public CarPanel(Controller controller) {
    this.controller = controller;
    this.x = 500; // Initial x-coordinate of the car
    this.y = 375; // Initial y-coordinate of the car
    this.heading = 0; // Initial heading (facing "north")
    setPreferredSize(new Dimension(700, 750)); // Adjust size as necessary
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    // Fetch the current car data
    boolean isDCMotorWorking = controller.dcMotor.isRunning();
    boolean isForward = controller._switch.isForward();
    float encodedMotorAngle = controller.encodedMotor.getAngle();

    // Calculate car's new position and heading
    if (isDCMotorWorking) {
      // Update heading based on the steering wheel rotation
      if (isForward) {
        heading += encodedMotorAngle / 2; // Adjust the multiplier for sensitivity
      } else {
        heading -= encodedMotorAngle / 2; // Reverse the heading change when moving backward
      }
      heading = heading % 360; // Keep the heading within 0-360 degrees
      if (heading < 0) {
        heading += 360;
      }

      double directionMultiplier = isForward ? 1 : -1;
      double distance = 10; // Distance to move per update
      double dx = directionMultiplier * distance * Math.sin(Math.toRadians(heading));
      double dy = -directionMultiplier * distance * Math.cos(Math.toRadians(heading));
      x += dx;
      y += dy;

      // Keep the car within the boundaries
      if (x > 1200) x = 1200;
      if (x < 100) x = 100;
      if (y > 800) y = 800;
      if (y < 100) y = 100;
    }

    // Clear the panel
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, getWidth(), getHeight());

    // Draw the car
    g2d.setColor(Color.BLUE);
    g2d.translate(x, y); // Move the origin to the car's position
    g2d.rotate(Math.toRadians(heading)); // Rotate the car based on its heading
    g2d.fillRect(-carWidth / 2, -carHeight / 2, carWidth, carHeight); // Draw the car centered at (x, y)

    // Draw the red rectangles (wheels)
    g2d.setColor(Color.RED);
    int wheelWidth = 10;  // Width of the red rectangles (wheels)
    int wheelHeight = 30; // Height of the red rectangles (wheels)

    // Front-left wheel
    AffineTransform originalTransform = g2d.getTransform(); // Save current transform
    g2d.translate(-carWidth / 2, -carHeight / 2); // Move to front-left wheel position
    g2d.rotate(Math.toRadians(encodedMotorAngle)); // Rotate the wheel based on encodedMotorAngle
    g2d.fillRect(-wheelWidth / 2, -wheelHeight / 2, wheelWidth, wheelHeight); // Draw the wheel
    g2d.setTransform(originalTransform); // Restore original transform

    // Front-right wheel
    g2d.translate(carWidth / 2, -carHeight / 2); // Move to front-right wheel position
    g2d.rotate(Math.toRadians(encodedMotorAngle)); // Rotate the wheel based on encodedMotorAngle
    g2d.fillRect(-wheelWidth / 2, -wheelHeight / 2, wheelWidth, wheelHeight); // Draw the wheel
    g2d.setTransform(originalTransform); // Restore original transform

    g2d.setTransform(new AffineTransform()); // Reset the transformation

    // Draw car direction and position info
    g2d.setColor(Color.BLACK);
    g2d.setFont(new Font("Arial", Font.PLAIN, 14));
    g2d.drawString(String.format("Position: (%.2f, %.2f)", x, y), 10, 20);
    g2d.drawString(String.format("Heading: %.2f°", heading), 10, 40);

    // Vẽ camera khi xe lùi
    if (!isForward) {
      g2d.setColor(Color.GREEN);
      g2d.fillRect(getWidth() - 100, 20, 180, 100);
      g2d.setColor(Color.BLACK);
      g2d.setFont(new Font("Arial", Font.BOLD, 16));
      g2d.drawString("CAMERA", getWidth() - 90, 55);
    }
  }
}