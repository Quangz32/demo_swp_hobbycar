package org.hobby_car.threads;

import org.hobby_car.DriverSimulation;

public class DrivingScript extends Thread{
  DriverSimulation simulation;

  public DrivingScript(DriverSimulation simulation) {
    this.simulation = simulation;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(2000);
      simulation.getSteeringWheel().setRotation(1);
      Thread.sleep(1000);
      simulation.getSteeringWheel().setRotation((float) -0.5);
      Thread.sleep(1000);
      simulation.getAcceleratorPedal().setAccelerating(true);



//      Thread.sleep(1000);
//      simulation.getChangeModeButton().setManual(false);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

//    while (true){
//      try {
//        Thread.sleep(Const.DELAY);
//      } catch (InterruptedException e) {
//        System.out.println("Luồng đã bị gián đoạn: " + e.getMessage());
//        break; // Thoát khỏi vòng lặp khi bị gián đoạn
//      }
//    }
  }
}
