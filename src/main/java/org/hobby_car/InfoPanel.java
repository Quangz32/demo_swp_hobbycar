package org.hobby_car;

import java.awt.*;
import javax.swing.*;

public class InfoPanel extends JPanel {

  private Controller controller;

  public InfoPanel(Controller controller) {
    this.controller = controller;
    setPreferredSize(new Dimension(300, 200)); // Adjust size as needed
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Fetch values from the controller
    boolean isForward = controller._switch.isForward();
    boolean isAccelerating = controller.acceleratorPedal.isAccelerating();
    boolean isBraking = controller.brakePedal.isBraking();
    float steeringWheelRotation = controller.steeringWheel.getRotation();

    boolean isCameraWorking = controller.camera.isWorking();
    boolean isDCMotorWorking = controller.dcMotor.isRunning();
    float encodedMotorAngle = controller.encodedMotor.getAngle();
    boolean isBrakeWorking = controller.brake.isWorking();

    // Draw the information
    g.setColor(Color.BLACK);
    g.setFont(new Font("Arial", Font.PLAIN, 14));

    int y = 20; // Initial vertical position
    int lineHeight = 20; // Line height for spacing

    g.drawString("Manual Control Simulation Info:", 10, y);
    y += lineHeight;

    g.drawString("Is Forward: " + isForward, 10, y);
    y += lineHeight;

    g.drawString("Is Accelerating: " + isAccelerating, 10, y);
    y += lineHeight;

    g.drawString("Is Braking: " + isBraking, 10, y);
    y += lineHeight;

    g.drawString("Steering Wheel Rotation: " + steeringWheelRotation, 10, y);
    y += lineHeight;

    g.drawString("Is Camera Working: " + isCameraWorking, 10, y);
    y += lineHeight;

    g.drawString("Is DC Motor Working: " + isDCMotorWorking, 10, y);
    y += lineHeight;

    g.drawString("Encoded Motor Angle: " + encodedMotorAngle, 10, y);
    y += lineHeight;

    g.drawString("Is Brake Working: " + isBrakeWorking, 10, y);
  }
}