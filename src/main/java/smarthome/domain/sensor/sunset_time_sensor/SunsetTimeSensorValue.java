/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.sunset_time_sensor;


import java.time.LocalTime;
import smarthome.ddd.IValueObject;

public class SunsetTimeSensorValue implements IValueObject {

  private final LocalTime value;

  /**
   * Creates a new SunsetTimeValue with a given value.
   *
   * @param value the value to be set.
   */

  public SunsetTimeSensorValue(LocalTime value) {
    validateValue(value);
    this.value = value;
  }

  /**
   * Validates the value being passed in the constructor.
   */
  private void validateValue(LocalTime value) {
    if (value == null) {
      throw new IllegalArgumentException("Time is required");
    }
  }

  /**
   * Returns the value of the SunsetTimeValue as a String.
   */
  @Override
  public String toString() {
    int hours = this.value.getHour();
    int minutes = this.value.getMinute();
    int seconds = this.value.getSecond();
    return String.format("Sunset Time: %02d:%02d:%02d", hours, minutes, seconds);
  }


}