package org.hobby_car.output;

public class DCMotor {

  private boolean isRunning;
  //mermaid

  public DCMotor(boolean isRunning) {
    this.isRunning = isRunning;
  }

  public boolean isRunning() {
    return isRunning;
  }

  public void setRunning(boolean running) {
    isRunning = running;
  }
}
