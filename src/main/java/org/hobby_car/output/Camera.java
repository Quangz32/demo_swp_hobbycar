package org.hobby_car.output;

public class Camera {

  private boolean isWorking;

  public Camera(boolean isWorking) {
    this.isWorking = isWorking;
  }

  public boolean isWorking() {
    return isWorking;
  }

  public void setWorking(boolean working) {
    isWorking = working;
  }
}
