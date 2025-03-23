package org.hobby_car.threads;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.hobby_car.DriverSimulation;
import org.hobby_car.const1.Const;
import org.hobby_car.input.ChangeModeButton;
import org.hobby_car.input.Switch;

public class FetchSwitch extends Thread {
  private final Switch aSwitch;
  private DriverSimulation simulation;

  public FetchSwitch(
      Switch aSwitch,
      DriverSimulation simulation) {
    this.aSwitch = aSwitch;
    this.simulation = simulation;
  }

  @Override
  public void run() {
    while (true){

      boolean newValue = simulation.get_switch().isForward();
      aSwitch.setForward(newValue);
//      System.out.println("new Switch: " + newValue);

      try {
        Thread.sleep(Const.DELAY);
      } catch (InterruptedException e) {
        System.out.println("Luồng đã bị gián đoạn: " + e.getMessage());
        break; // Thoát khỏi vòng lặp khi bị gián đoạn
      }
    }
  }


}
