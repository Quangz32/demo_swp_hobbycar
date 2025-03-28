package org.hobby_car.threads;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.hobby_car.DriverSimulation;
import org.hobby_car.const1.Const;
import org.hobby_car.input.ChangeModeButton;

public class FetchChangeModeButton extends Thread {
  private final ChangeModeButton changeModeButton;
  private DriverSimulation simulation;

  public FetchChangeModeButton(
      ChangeModeButton changeModeButton,
      DriverSimulation simulation) {
    this.changeModeButton = changeModeButton;
    this.simulation = simulation;
  }

  @Override
  public void run() {
    while (true){

        boolean newValue = simulation.getChangeModeButton().isManual();
        changeModeButton.setManual(newValue);
//        System.out.println("new ChangeModeBtton: " + newValue);

      try {
        Thread.sleep(Const.DELAY);
      } catch (InterruptedException e) {
        System.out.println("Luồng đã bị gián đoạn: " + e.getMessage());
        break; // Thoát khỏi vòng lặp khi bị gián đoạn
      }
    }
  }


}
