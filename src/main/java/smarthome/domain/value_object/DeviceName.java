/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IValueObject;

public class DeviceName implements IValueObject {

  private final String name;

  /**
   * Constructor of the DeviceName class.
   *
   * @param name The name of the device. Must not be null, empty, or blank.
   */
  public DeviceName(String name) {
    validateDeviceName(name);
    this.name = name.trim();
  }

  /**
   * Sets the device name.
   *
   * @param name The name of the device. Must not be null, empty, or blank.
   */
  private void validateDeviceName(String name) {
    if (name == null || name.isEmpty() || name.isBlank()) {
      throw new IllegalArgumentException("The device name cannot be null, blank, or empty.");
    }

    if (!name.matches("[a-zA-Z0-9 ]+")) {
      throw new IllegalArgumentException("The device name can only contain letters and numbers.");
    }
  }

  /**
   * Gets the name of the device.
   *
   * @return The name of the device.
   */
  public String getName() {
    return name;
  }

  /**
   * Compares the current object with another object of the same type.
   *
   * @param o The object to compare with.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o instanceof DeviceName deviceName) {
      return this.name.equals(deviceName.name);
    }
    return false;
  }

  /**
   * Returns the hash code of the object.
   *
   * @return The hash code of the object.
   */
  @Override
  public int hashCode() {
    return name.hashCode();
  }

  /**
   * Returns the string representation of the object.
   *
   * @return The string representation of the object.
   */
  @Override
  public String toString() {
    return "Device name: " + name;
  }
}
