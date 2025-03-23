package org.hobby_car.threads;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.hobby_car.DriverSimulation;
import org.hobby_car.const1.Const;
import org.hobby_car.input.AcceleratorPedal;
import org.hobby_car.input.BrakePedal;
import org.hobby_car.input.ChangeModeButton;
import org.hobby_car.input.Switch;

public class FetchBrakePedal extends Thread {
  private final BrakePedal input;
  private DriverSimulation simulation;

  public FetchBrakePedal(
      BrakePedal input,
      DriverSimulation simulation) {
    this.input = input;
    this.simulation = simulation;
  }

  @Override
  public void run() {
    while (true){

      boolean newValue = simulation.getBrakePedal().isBraking();
      input.setBraking(newValue);
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
