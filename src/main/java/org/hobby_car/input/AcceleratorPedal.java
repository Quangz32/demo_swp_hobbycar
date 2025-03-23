package org.hobby_car.input;

public class AcceleratorPedal {

  private boolean isAccelerating;

  public AcceleratorPedal(boolean isAccelerating) {
    this.isAccelerating = isAccelerating;
  }

  public boolean isAccelerating() {
    return isAccelerating;
  }

  public void setAccelerating(boolean accelerating) {
    isAccelerating = accelerating;
  }
}
