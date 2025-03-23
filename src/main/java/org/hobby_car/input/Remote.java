package org.hobby_car.input;

public class Remote {

  private boolean isForward, isAccelerating, isBraking, isTurningLeft, isTurningRight;


  public Remote(boolean isForward, boolean isAccelerating, boolean isBraking, boolean isTurningLeft,
      boolean isTurningRight) {
    this.isForward = isForward;
    this.isAccelerating = isAccelerating;
    this.isBraking = isBraking;
    this.isTurningLeft = isTurningLeft;
    this.isTurningRight = isTurningRight;
  }

  public boolean isForward() {
    return isForward;
  }

  public void setForward(boolean forward) {
    isForward = forward;
  }

  public boolean isAccelerating() {
    return isAccelerating;
  }

  public void setAccelerating(boolean accelerating) {
    isAccelerating = accelerating;
  }

  public boolean isBraking() {
    return isBraking;
  }

  public void setBraking(boolean braking) {
    isBraking = braking;
  }

  public boolean isTurningLeft() {
    return isTurningLeft;
  }

  public void setTurningLeft(boolean turningLeft) {
    isTurningLeft = turningLeft;
  }

  public boolean isTurningRight() {
    return isTurningRight;
  }

  public void setTurningRight(boolean turningRight) {
    isTurningRight = turningRight;
  }
}
