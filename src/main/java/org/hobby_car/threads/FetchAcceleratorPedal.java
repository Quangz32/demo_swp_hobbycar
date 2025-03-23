package org.hobby_car.threads;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.hobby_car.DriverSimulation;
import org.hobby_car.const1.Const;
import org.hobby_car.input.AcceleratorPedal;
import org.hobby_car.input.ChangeModeButton;
import org.hobby_car.input.Switch;

public class FetchAcceleratorPedal extends Thread {
  private final AcceleratorPedal input;
  private DriverSimulation simulation;

  public FetchAcceleratorPedal(
      AcceleratorPedal input,
      DriverSimulation simulation) {
    this.input = input;
    this.simulation = simulation;
  }

  @Override
  public void run() {
    while (true){

      boolean newValue = simulation.getAcceleratorPedal().isAccelerating();
      input.setAccelerating(newValue);
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
