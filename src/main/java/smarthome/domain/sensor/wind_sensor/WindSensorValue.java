/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.wind_sensor;

import lombok.Getter;
import smarthome.ddd.IValueObject;

@Getter
public class WindSensorValue implements IValueObject {

  private final double speed;
  private final double direction;

  /**
   * Constructor of the class.
   *
   * @param speed     The speed of the wind.
   * @param direction The direction of the wind in radians.
   */
  public WindSensorValue(double speed, double direction) {
    speedValidator(speed);
    directionValidator(direction);
    this.speed = speed;
    this.direction = direction;
  }

  /**
   * Method to validate the speed of the wind.
   * @param speed The speed of the wind.
   */
  private void speedValidator(double speed) {
    if (speed < 0) {
      throw new IllegalArgumentException("Speed cannot be negative" + speed);
    }
  }

  /**
   * Method to validate the direction of the wind.
   * @param direction The direction of the wind.
   */
  private void directionValidator(double direction) {
    if (direction < 0 || direction > 2 * Math.PI) {
      throw new IllegalArgumentException(
          "Direction must be between 0 (inclusive) and 2π (exclusive) radians");
    }
  }

  /**
   * Compares this WindSensorValue to another object.
   *
   * @param obj The object to compare.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof WindSensorValue other) {
      return this.speed == other.speed && this.direction == other.direction;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Double.hashCode(speed) + Double.hashCode(direction);
  }

}
