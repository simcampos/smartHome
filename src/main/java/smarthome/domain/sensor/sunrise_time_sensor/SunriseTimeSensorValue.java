/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.sunrise_time_sensor;


import java.time.LocalTime;
import smarthome.ddd.IValueObject;

public class SunriseTimeSensorValue implements IValueObject {

  private LocalTime value;

  /**
   * Creates a new SunriseTimeValue with a given value.
   *
   * @param value the value to be set.
   */

  public SunriseTimeSensorValue(LocalTime value) {
    setValue(value);
  }

  /**
   * Gets the value of the SunriseTimeValue.
   *
   * @return the value of the SunriseTimeValue.
   */
  private void setValue(LocalTime value) {
    if (value == null) {
      throw new IllegalArgumentException("Time is required");
    } else {
      this.value = value;
    }
  }

  /**
   * Clones the SunriseTimeValue.
   *
   * @return a new SunriseTimeValue with the same value.
   */
  public String toString() {
    int hours = this.value.getHour();
    int minutes = this.value.getMinute();
    int seconds = this.value.getSecond();
    return String.format("Sunrise Time: %02d:%02d:%02d", hours, minutes, seconds);
  }

  /**
   * Equals method for SunriseTimeValue.
   */
  public boolean equals(Object obj) {
    if (obj instanceof SunriseTimeSensorValue sunriseTimeSensorValue) {
      return this.value.equals(sunriseTimeSensorValue.value);
    }
    return false;
  }

  /**
   * HashCode method for SunriseTimeValue.
   */
  public int hashCode() {
    return value.hashCode();
  }

}
