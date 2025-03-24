
package org.hobby_car;

import javax.swing.*;
    import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ManualControlSimulation extends JFrame implements KeyListener {
  private static final int WINDOW_WIDTH = 800;
  private static final int WINDOW_HEIGHT = 600;
  private static final int DELAY = 222;

  private Controller controller;
  private float steeringWheelAngle = 0;
  private float steeringWheelRotation = 0;
  private boolean[] keyPressed = new boolean[256];
  private Timer updateTimer;

  public ManualControlSimulation(Controller controller) throws InterruptedException {
    this.controller = controller;
    controller.start();

    // Thiết lập JFrame
    setTitle("Key Press Example");
    setSize(300, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null); // Sử dụng layout null để tùy chỉnh vị trí

    // Thêm KeyListener vào JFrame
    addKeyListener(this);
    setFocusable(true); // Đảm bảo JFrame có thể nhận tiêu điểm
    requestFocusInWindow(); // Yêu cầu JFrame có tiêu điểm

    setLocationRelativeTo(null); // Đặt vị trí cửa sổ ở giữa màn hình
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

  private void updateSimulation() {

    // Điều chỉnh góc bánh lái dựa trên phím mũi tên
    if (keyPressed[KeyEvent.VK_RIGHT]) {
      if (steeringWheelRotation <= 0.9){
        steeringWheelRotation += 0.1F;
      }
    }
    if (keyPressed[KeyEvent.VK_LEFT]) {
      if (steeringWheelRotation >= -0.9){
        steeringWheelRotation -= 0.1F;
      }
    }

    // Điều khiển phanh và tăng tốc
    if (controller != null && controller.getSimulation() != null) {
      System.out.println(keyPressed[KeyEvent.VK_UP]);
      controller.getSimulation().getBrakePedal().setBraking(keyPressed[KeyEvent.VK_DOWN]);
      controller.getSimulation().getAcceleratorPedal().setAccelerating(keyPressed[KeyEvent.VK_UP]);
      controller.getSimulation().getSteeringWheel().setRotation(steeringWheelRotation);
    }


  }
}