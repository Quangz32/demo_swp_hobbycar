package org.hobby_car.threads;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.hobby_car.DriverSimulation;
import org.hobby_car.const1.Const;
import org.hobby_car.input.ChangeModeButton;
import org.hobby_car.input.Remote;
import org.hobby_car.input.SteeringWheel;
import org.hobby_car.input.Switch;

public class FetchRemote extends Thread {
  private final Remote input;
  private DriverSimulation simulation;

  public FetchRemote(
      Remote input,
      DriverSimulation simulation) {
    this.input = input;
    this.simulation = simulation;
  }

  @Override
  public void run() {
    while (true){

      Remote newValue = simulation.getRemote();
      input.setAccelerating(newValue.isAccelerating());
      input.setBraking(newValue.isBraking());
      input.setForward(newValue.isForward());
      input.setTurningLeft(newValue.isTurningLeft());
      input.setTurningRight(newValue.isTurningRight());

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
