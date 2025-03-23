package org.hobby_car.input;

public class Switch {

  private boolean isForward;

  public Switch(boolean isForward) {
    this.isForward = isForward;
  }

  public boolean isForward() {
    return isForward;
  }

  public void setForward(boolean forward) {
    isForward = forward;
  }
}
