package org.hobby_car.input;

public class BrakePedal {

  private boolean isBraking;

  public BrakePedal(boolean isBraking) {
    this.isBraking = isBraking;
  }

  public boolean isBraking() {
    return isBraking;
  }

  public void setBraking(boolean braking) {
    isBraking = braking;
  }
}
