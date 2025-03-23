package org.hobby_car.threads;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.hobby_car.input.ChangeModeButton;

public class FetchChangeModeButton extends Thread implements KeyListener {

  private final ChangeModeButton changeModeButton;

  public FetchChangeModeButton(ChangeModeButton changeModeButton) {
    this.changeModeButton = changeModeButton;
  }

  @Override
  public void run() {
    while (true){
//      boolean isManual = ....;
      changeModeButton.setManual(!changeModeButton.isManual());
//      System.out.println("new ChangeModeButton: " + changeModeButton.isManual());

      try {
        // Đợi 100ms trước khi lặp lại
        Thread.sleep(100);
      } catch (InterruptedException e) {
        // Xử lý trường hợp luồng bị gián đoạn
        System.out.println("Luồng đã bị gián đoạn: " + e.getMessage());
        break; // Thoát khỏi vòng lặp khi bị gián đoạn
      }
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    System.out.println("Key Typed: " + e.getKeyChar());
  }

  @Override
  public void keyPressed(KeyEvent e) {
    System.out.println("Key Typed: " + e.getKeyChar());
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
