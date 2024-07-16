/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.humidity_sensor;

import smarthome.ddd.IValueObject;

public class HumiditySensorValue implements IValueObject {

  private int humidityValue;

  /**
   * Constructs a HumiditySensorValue with a specified humidity percentage.
   *
   * @param iValue The humidity value as a percentage.
   * @throws IllegalArgumentException if the provided value is outside the range of 0 to 100.
   */
  public HumiditySensorValue(int iValue) {
    validateValue(iValue);
  }

  /**
   * Validates the humidity value by ensuring it falls within the acceptable range of 0 to 100.
   *
   * @param iValue The humidity value to validate.
   * @throws IllegalArgumentException if the value is not between 0 and 100.
   */
  private void validateValue(int iValue) throws IllegalArgumentException {
    if (iValue < 0 || iValue > 100) {
      throw new IllegalArgumentException("Humidity value must be between 0 and 100");
    } else {
      this.humidityValue = iValue;
    }
  }

  /**
   * Returns a string representation of the humidity value.
   *
   * @return The humidity value as a string.
   */
  public String toString() {
    return this.humidityValue + "";
  }

  /**
   * Compares this HumiditySensorValue to another object. This method returns true if and only if
   * the other object is a HumiditySensorValue with the same value.
   *
   * @param obj The object to compare.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof HumiditySensorValue other) {
      return this.humidityValue == other.humidityValue;
    }
    return false;
  }

  /**
   * Returns the hash code of the humidity value.
   *
   * @return The hash code of the humudity value.
   */
  @Override
  public int hashCode() {
    return Integer.hashCode(humidityValue);
  }


}
