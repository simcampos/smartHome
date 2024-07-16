/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.temperature_sensor;

import smarthome.ddd.IValueObject;


public class TemperatureSensorValue implements IValueObject {

  /**
   * Represents the value of the temperature measured by the sensor. This class is used to
   * encapsulate the numeric value of the temperature unit.
   */
  private double temperatureValue;

  /**
   * Constructs a new TemperatureSensorValue with the specified temperature unit.
   *
   * @param dValue The numeric value of the temperature, in degrees Celsius.
   */
  public TemperatureSensorValue(double dValue) {
    validateValue(dValue);
  }

  /**
   * Validates the temperature value by ensuring it falls above or equal to -273.15 degrees
   * Celsius.
   *
   * @param dValue The temperature value to validate.
   * @throws IllegalArgumentException if the value is not above -273.15.
   */

  private void validateValue(double dValue) throws IllegalArgumentException {
    if (dValue < -273.15) {
      throw new IllegalArgumentException("Temperature value must be above or equal to -273.15");
    } else {
      this.temperatureValue = dValue;
    }
  }

  /**
   * Returns a string representation of the temperature unit. This method allows the
   * temperature value to be easily printed or logged.
   *
   * @return A string representation of the temperature value, in degrees Celsius.
   */
  @Override
  public String toString() {
    return this.temperatureValue + "";
  }

  /**
   * Compares this TemperatureSensorValue to another object. This method returns true if and only if
   * the other object is a TemperatureSensorValue with the same value.
   *
   * @param obj The object to compare.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof TemperatureSensorValue other) {
      return this.temperatureValue == other.temperatureValue;
    }
    return false;
  }

  /**
   * Returns the hash code of the temperature value.
   *
   * @return The hash code of the temperature value.
   */
  @Override
  public int hashCode() {
    return Double.hashCode(temperatureValue);
  }

}
