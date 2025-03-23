package org.hobby_car;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.hobby_car.input.AcceleratorPedal;
import org.hobby_car.input.BrakePedal;
import org.hobby_car.input.ChangeModeButton;
import org.hobby_car.input.Remote;
import org.hobby_car.input.SteeringWheel;
import org.hobby_car.input.Switch;
import org.hobby_car.threads.FetchChangeModeButton;

public class Controller implements KeyListener {


  public static void main(String[] args) {

    ChangeModeButton changeModeButton = new ChangeModeButton(true);
    Switch _switch = new Switch(true);
    AcceleratorPedal acceleratorPedal = new AcceleratorPedal(true);
    BrakePedal brakePedal = new BrakePedal(true);
    SteeringWheel steeringWheel = new SteeringWheel(0);
    Remote remote = new Remote(true, true,
        true, true, true);

    Thread thread1 = new FetchChangeModeButton(changeModeButton);
    thread1.start();

  }

  @Override
  public void keyTyped(KeyEvent e) {
    System.out.println("Key Pressed: " + e.getKeyCode());
  }

  @Override
  public void keyPressed(KeyEvent e) {
    System.out.println("Key Pressed: " + e.getKeyCode());
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
