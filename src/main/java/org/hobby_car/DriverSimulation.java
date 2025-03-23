package org.hobby_car;

import org.hobby_car.input.AcceleratorPedal;
import org.hobby_car.input.BrakePedal;
import org.hobby_car.input.ChangeModeButton;
import org.hobby_car.input.Remote;
import org.hobby_car.input.SteeringWheel;
import org.hobby_car.input.Switch;

public class DriverSimulation {

  private Remote remote;
  private SteeringWheel steeringWheel;
  private ChangeModeButton changeModeButton;
  private BrakePedal brakePedal;
  private AcceleratorPedal acceleratorPedal;
  private Switch _switch;

  public DriverSimulation() {
     changeModeButton = new ChangeModeButton(true);
     _switch = new Switch(true);
     acceleratorPedal = new AcceleratorPedal(false);
     brakePedal = new BrakePedal(false);
     steeringWheel = new SteeringWheel(0);
     remote = new Remote(true, false,
         false, false, false);
  }

  public Remote getRemote() {
    return remote;
  }

  public Switch get_switch() {
    return _switch;
  }

  public AcceleratorPedal getAcceleratorPedal() {
    return acceleratorPedal;
  }

  public BrakePedal getBrakePedal() {
    return brakePedal;
  }

  public ChangeModeButton getChangeModeButton() {
    return changeModeButton;
  }

  public SteeringWheel getSteeringWheel() {
    return steeringWheel;
  }
}
