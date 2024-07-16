/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.dew_point_sensor;

import smarthome.ddd.IValueObject;

public class DewPointValue implements IValueObject {

  private final int dewPointValue;

  /**
   * Constructor of the class.
   *
   * @param dewPointValue The value of the dew point.
   */
  public DewPointValue(int dewPointValue) {
    if (dewPointValue < -70) {
      throw new IllegalArgumentException("The value of the dew point cannot be lower than -70.");
    }
    this.dewPointValue = dewPointValue;
  }

  /**
   * Gets the value of the dew point.
   *
   * @return The value of the dew point.
   */
  public String toString() {
    return this.dewPointValue + "";
  }


  /**
   * Equals method for DewPointValue.
   *
   * @param obj The object to compare.
   */
  public boolean equals(Object obj) {
    if (obj instanceof DewPointValue dewPointValue) {
      return this.dewPointValue == dewPointValue.dewPointValue;
    }
    return false;

  }

  /**
   * HashCode method for DewPointValue.
   *
   * @return The hash code.
   */

  public int hashCode() {
    return Integer.hashCode(dewPointValue);
  }

}
