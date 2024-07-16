/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IValueObject;

/**
 * Value object for time difference in minutes
 */
public class TimeDelta implements IValueObject {

  private final int minutes;

  /**
   * Constructor for timeDelta
   *
   * @param minutes
   */
  public TimeDelta(int minutes) {
    validateMinutes(minutes);
    this.minutes = minutes;
  }

  /**
   * Validate minutes
   *
   * @param minutes
   */
  private void validateMinutes(int minutes) {
    if (minutes < 0) {
      throw new IllegalArgumentException("Minutes must be a positive integer");
    }
  }

  /**
   * Get minutes
   *
   * @return minutes
   */
  public int getMinutes() {
    return this.minutes;
  }

  /**
   * Check if two timeDelta objects are equal
   *
   * @param object
   * @return boolean
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof TimeDelta timeDelta) {
      return this.minutes == timeDelta.minutes;
    } else {
      return false;
    }
  }

  /**
   * Get hash code for timeDelta object
   *
   * @return hash code
   */

  @Override
  public int hashCode() {
    Integer integerMinutes = this.minutes;
    return integerMinutes.hashCode();
  }

  /**
   * Get string representation of timeDelta object
   *
   * @return string
   */
  @Override
  public String toString() {
    return "timeDelta:" + "minutes=" + minutes;
  }

}
