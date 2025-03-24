package org.hobby_car.output;

public class EncodedMotor {

  private float angle;


  public EncodedMotor(float angle) {
    this.angle = angle;
  }

  public float getAngle() {
    return angle;
  }

  public void setAngle(float angle) {
    this.angle = angle;
  }

  public void rotateLeft() {
    angle-=5;
  }

  public void rotateRight() {
    angle+=5;
  }
}
