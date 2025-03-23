package org.hobby_car;

import org.hobby_car.const1.Const;
import org.hobby_car.input.AcceleratorPedal;
import org.hobby_car.input.BrakePedal;
import org.hobby_car.input.ChangeModeButton;
import org.hobby_car.input.Remote;
import org.hobby_car.input.SteeringWheel;
import org.hobby_car.input.Switch;
import org.hobby_car.output.Brake;
import org.hobby_car.output.Camera;
import org.hobby_car.output.DCMotor;
import org.hobby_car.output.EncodedMotor;
import org.hobby_car.threads.DrivingScript;
import org.hobby_car.threads.FetchAcceleratorPedal;
import org.hobby_car.threads.FetchBrakePedal;
import org.hobby_car.threads.FetchChangeModeButton;
import org.hobby_car.threads.FetchRemote;
import org.hobby_car.threads.FetchSteeringWheel;
import org.hobby_car.threads.FetchSwitch;

public class Controller {


  public Controller() {
  }

  public static void main(String[] args) throws InterruptedException {


    //Input
    ChangeModeButton changeModeButton = new ChangeModeButton(true);
    Switch _switch = new Switch(true);
    AcceleratorPedal acceleratorPedal = new AcceleratorPedal(false);
    BrakePedal brakePedal = new BrakePedal(false);
    SteeringWheel steeringWheel = new SteeringWheel(0);
    Remote remote = new Remote(true, false,
        false, false, false);

    //Output
    Brake brake = new Brake(false);
    Camera camera = new Camera(false);
    DCMotor dcMotor =  new DCMotor(false);
    EncodedMotor encodedMotor = new EncodedMotor(0);

    //giả lập người điều khiển
    DriverSimulation simulation = new DriverSimulation();



    Thread thread1 = new FetchChangeModeButton(changeModeButton, simulation);
    Thread thread2 = new FetchSwitch(_switch, simulation);
    Thread thread3 = new FetchAcceleratorPedal(acceleratorPedal, simulation);
    Thread thread4 = new FetchSteeringWheel(steeringWheel, simulation);
    Thread thread5 = new FetchBrakePedal(brakePedal, simulation);
    Thread thread6 = new FetchRemote(remote, simulation);

    //
    Thread thread7 = new DrivingScript(simulation);

    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();
    thread5.start();
    thread6.start();
    thread7.start();

    while (true){
      System.out.println("Mode - isManual : " + changeModeButton.isManual() );
      System.out.println("Swith - isFoward : " + _switch.isForward() );
      System.out.println("Accelerating -  : " + acceleratorPedal.isAccelerating() );
      System.out.println("Braking -  : " + brakePedal.isBraking() );
      System.out.println("Steering Wheel -  : " + steeringWheel.getRotation() );
      System.out.println("Remote -  : " + remote.toString());
      System.out.println("....");

      if (changeModeButton.isManual()) {
            brake.setWorking(brakePedal.isBraking());
            camera.setWorking(!_switch.isForward());
        System.out.println("*****"+ steeringWheel.getRotation());
            encodedMotor.setAngle(45 * steeringWheel.getRotation());
            dcMotor.setRunning(!brakePedal.isBraking() && acceleratorPedal.isAccelerating());
      } else {
        System.out.println("###"+ steeringWheel.getRotation());

        brake.setWorking(remote.isBraking());
        camera.setWorking(!remote.isForward());
        if (remote.isTurningRight() ){
          encodedMotor.rotateRight();
        }
        if (remote.isTurningLeft()){
          encodedMotor.rotateLeft();
        }
        dcMotor.setRunning(!remote.isBraking() && remote.isAccelerating());
      }

      System.out.println("Brake - working: " + brake.isWorking());
      System.out.println("Camera - woking: " + camera.isWorking());
      System.out.println("Encoded Motor - Angle: " + encodedMotor.getAngle());
      System.out.println("DC Motor - runnig: " + dcMotor.isRunning());
      System.out.println("==========");

      Thread.sleep(Const.DELAY);
    }




  }

}
