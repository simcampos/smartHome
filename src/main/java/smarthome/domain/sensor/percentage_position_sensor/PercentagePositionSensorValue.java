/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.percentage_position_sensor;

import smarthome.ddd.IValueObject;

public class PercentagePositionSensorValue implements IValueObject {

  private int percentage;

  public PercentagePositionSensorValue(int percentage) {
    validatePercentage(percentage);
  }

  private void validatePercentage(int percentage) {
    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("The percentage must be between 0 and 100.");
    }
    this.percentage = percentage;
  }

  public String toString() {
    return this.percentage + "";
  }

}
