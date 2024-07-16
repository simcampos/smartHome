/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IValueObject;

public class SensorName implements IValueObject {

  private final String name;

  /**
   * Class constructor
   *
   * @param name The sensor name to set.
   */
  public SensorName(String name) {
    validateSensorName(name);
    this.name = name.trim();
  }

  /**
   * Sets the sensor name after validating it.
   *
   * @param name The sensor name to set.
   */
  private void validateSensorName(String name) {
    if (name == null || name.isEmpty() || name.isBlank()) {
      throw new IllegalArgumentException("The sensor name cannot be null, blank, or empty.");
    }

    if (!name.matches("[a-zA-Z0-9 ]+")) {
      throw new IllegalArgumentException("The sensor name can only contain letters and numbers.");
    }
  }

  /**
   * Gets the sensor name.
   *
   * @return The sensor name.
   */
  public String getSensorName() {
    return name;
  }

  /**
   * Compares this instance with another instance.
   *
   * @param object the other instance to compare with
   * @return true if the instances are equal, false otherwise
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }

    if (object instanceof SensorName sensorName) {

      return this.name.equals(sensorName.name);
    }
    return false;
  }

  /**
   * Returns the hash code of the object.
   */
  @Override
  public int hashCode() {
    return name.hashCode();
  }

  /**
   * @return The string representation of the sensor name.
   */
  public String toString() {
    return name;
  }
}
