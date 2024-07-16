/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

public class SensorModelName {

  private final String name;

  /**
   * Class constructor.
   *
   * @param name The sensor model name to set.
   */
  public SensorModelName(String name) {
    validateSensorModelName(name);
    this.name = name.trim();
  }

  /**
   * Validates the sensor model name.
   *
   * @param name The sensor model name to validate.
   */
  private void validateSensorModelName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("The device name cannot be null, blank, or empty.");
    }

    if (!name.matches("[a-zA-Z0-9 ]+")) {
      throw new IllegalArgumentException("The device name can only contain letters and numbers.");
    }
  }

  /**
   * Gets the sensor model name.
   *
   * @return The sensor model name.
   */
  public String getSensorModelName() {
    return name;
  }

  /**
   * Compares this instance with another instance.
   *
   * @param object The other instance to compare with.
   * @return true if the instances are equal, false otherwise.
   */
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }

    if (object instanceof SensorModelName sensorModelName) {

      return this.name.equals(sensorModelName.name);
    }
    return false;
  }

  /**
   * @return The hash code of the object.
   */
  @Override
  public int hashCode() {
    return name.hashCode();
  }

  /**
   * @return The string representation of the object.
   */
  @Override
  public String toString() {
    return name;
  }

}
