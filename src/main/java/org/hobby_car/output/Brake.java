package org.hobby_car.output;

public class Brake {

  private boolean isWorking;
  //mermaid

  public Brake(boolean isWorking) {
    this.isWorking = isWorking;
  }

  public boolean isWorking() {
    return isWorking;
  }

  public void setWorking(boolean working) {
    isWorking = working;
  }
}
