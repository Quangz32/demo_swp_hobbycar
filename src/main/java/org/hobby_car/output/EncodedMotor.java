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

  private void rotateLeft() {
    angle--;
  }

  private void rotateRight() {
    angle++;
  }
}
