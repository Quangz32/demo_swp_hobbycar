package org.hobby_car.input;

public class SteeringWheel {

  private float rotation; //From -1 to 1

  public SteeringWheel(float rotation) {
    this.rotation = rotation;
  }

  public float getRotation() {
    return rotation;
  }

  public void setRotation(float rotation) {
    this.rotation = rotation;
  }
}
