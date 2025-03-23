package org.hobby_car.input;

public class ChangeModeButton {

  private boolean isManual;

  public ChangeModeButton(boolean isManual) {
    this.isManual = isManual;
  }

  public boolean isManual() {
    return isManual;
  }

  public void setManual(boolean manual) {
    isManual = manual;
  }
}
