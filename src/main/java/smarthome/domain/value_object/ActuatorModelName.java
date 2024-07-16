/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IValueObject;

public class ActuatorModelName implements IValueObject {

  private final String name;

  /**
   * Class constructor.
   *
   * @param name The sensor model name to set.
   */
  public ActuatorModelName(String name) {
    validateActuatorModelName(name);
    this.name = name.trim();
  }

  /**
   * Validates the sensor model name.
   *
   * @param name The sensor model name to validate.
   */
  private void validateActuatorModelName(String name) {
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
  public String getActuatorModelName() {
    return name;
  }

  /**
   * Compares this instance with another instance.
   *
   * @param object The object to compare with.
   * @return True if the objects are equal, false otherwise.
   */
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }

    if (object instanceof ActuatorModelName actuatorModelName) {

      return this.name.equals(actuatorModelName.name);
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

}
