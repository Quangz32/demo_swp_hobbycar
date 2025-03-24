
package org.hobby_car;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
    import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ManualControlSimulation extends JFrame implements KeyListener {
  private static final int WINDOW_WIDTH = 1000;
  private static final int WINDOW_HEIGHT = 750;
  private static final int DELAY = 222;

  private Controller controller;
  private float aSteeringWheelRotation = 0;
  private boolean aIsForward = true;
  private boolean[] keyPressed = new boolean[256];
  private Timer updateTimer;

  //Thông tin để hiển thị lên GUI (9 cái đầu - bằng chữ)
  boolean isManuel = true; //Chỉ demo manual
  boolean isForward;
  boolean isAccelerating;
  boolean isBraking;
  float steeringWheelRotation;

  boolean isCameraWorking;
  boolean isDCMotorWorking; //Để ý xem đang tiến hay lùi nữa nhe
  float encodedMotorAngle;
  boolean isBrakeWorking;

  //HIển thị hình ảnh xe
  float x = WINDOW_WIDTH/2, y= WINDOW_HEIGHT/2; //toạ độ xe
  float heading = 0;
  float direction; //Hướng xe so với hướng 12h màn hình
  //Nếu tài năng thì hiển thị cả góc lái bánh xe (10đ)



  public ManualControlSimulation(Controller controller) throws InterruptedException {
    this.controller = controller;
    controller.start();

    // JFrame setup
    setTitle("Manual Control Simulation");
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout()); // Use BorderLayout for better layout management

    // Add the InfoPanel
    InfoPanel infoPanel = new InfoPanel(controller);
    add(infoPanel, BorderLayout.WEST); // Add it to the left side

    // Other setup
    addKeyListener(this);
    setFocusable(true);
    requestFocusInWindow();
    setLocationRelativeTo(null);

    setupUpdateTimer();

    // Update the panel periodically
    Timer refreshPanelTimer = new Timer(20, e -> infoPanel.repaint());
    refreshPanelTimer.start();
  }

  private void setupUpdateTimer() {
    updateTimer = new Timer(20, e -> {
      updateSimulation();
      fetchCarStatic();

    });
    updateTimer.start();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      ManualControlSimulation example = null;
      try {
        example = new ManualControlSimulation(new Controller());
        example.setVisible(true); // Hiển thị JFrame
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
//      example.getCont
    });
  }

  public Controller getController (){
    return controller;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    if (e.getKeyChar() == 'M' || e.getKeyChar() == 'm') {
      aIsForward = !aIsForward;
    }
    updateSimulation();
  }


  @Override
  public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();
    if (keyCode >= 0 && keyCode < keyPressed.length) {
      keyPressed[keyCode] = true;
    }
    updateSimulation();
//    System.out.println("Key Pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int keyCode = e.getKeyCode();
    if (keyCode >= 0 && keyCode < keyPressed.length) {
      keyPressed[keyCode] = false;
    }
//    System.out.println("Key pressed: " + KeyEvent.getKeyText(keyCode));
    updateSimulation();
  }

  private void fetchCarStatic(){
    //Thông tin để hiển thị lên GUI (9 cái đầu - bằng chữ)
     isForward = controller._switch.isForward();
     isAccelerating = controller.acceleratorPedal.isAccelerating();
     isBraking = controller.brakePedal.isBraking();
    steeringWheelRotation = controller.steeringWheel.getRotation();

     isCameraWorking = controller.camera.isWorking();
     isDCMotorWorking = controller.dcMotor.isRunning(); //Để ý xem đang tiến hay lùi nữa nhe
     encodedMotorAngle = controller.encodedMotor.getAngle();
     isBrakeWorking = controller.brake.isWorking();


     //Tính toán vị trí, hướng xe ##
    if (isDCMotorWorking) {
      // Xe đang tiến và đạp ga
      double hs = isForward ? 1 : -1;
      double distance = 10; // Khoảng cách di chuyển
      double dx = hs * distance * Math.cos(Math.toRadians(heading)); // Theo trục x
      double dy = hs * distance * Math.sin(Math.toRadians(heading)); // Theo trục y
      x += dx;
      y += dy;

      // Cập nhật heading dựa trên góc lái
      heading += steeringWheelRotation * 5; // Nhân với một hệ số để tăng độ nhạy
      heading = heading % 360; // Đảm bảo heading nằm trong khoảng từ 0 đến 360
      if (heading < 0) {
        heading += 360; // Điều chỉnh nếu heading âm
      }
    }



    //HIển thị hình ảnh xe
//     x, y; //toạ độ xe
//     direction; //Hướng xe so với hướng 12h màn hình
    //Nếu tài năng thì hiển thị cả góc lái bánh xe (10đ)

  }
  private void updateSimulation() {
    // Điều chỉnh góc bánh lái dựa trên phím mũi tên
    if (keyPressed[KeyEvent.VK_RIGHT]) {
      if (aSteeringWheelRotation <= 0.95){
        aSteeringWheelRotation += 0.05F;
      }
    }
    if (keyPressed[KeyEvent.VK_LEFT]) {
      if (aSteeringWheelRotation >= -0.95){
        aSteeringWheelRotation -= 0.05F;
      }
    }

    // Điều khiển phanh và tăng tốc
    if (controller != null && controller.getSimulation() != null) {
      controller.getSimulation().get_switch().setForward(aIsForward);
      controller.getSimulation().getBrakePedal().setBraking(keyPressed[KeyEvent.VK_DOWN]);
      controller.getSimulation().getAcceleratorPedal().setAccelerating(keyPressed[KeyEvent.VK_UP]);
      controller.getSimulation().getSteeringWheel().setRotation(aSteeringWheelRotation);
    }
  }

}