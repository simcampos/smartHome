/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IValueObject;

public class RoomName implements IValueObject {

  private final String name;

  /**
   * Class constructor.
   *
   * @param name The room name to set.
   */
  public RoomName(String name) {
    validateRoomName(name);
    this.name = name.trim();
  }

  /**
   * Sets the room name after validating it.
   *
   * @param name The room name to set.
   */
  private void validateRoomName(String name) {
    if (name == null || name.isEmpty() || name.isBlank()) {
      throw new IllegalArgumentException("The room name cannot be null, blank, or empty.");
    }

    if (!name.matches("[a-zA-Z0-9 ]+")) {
      throw new IllegalArgumentException("The room name can only contain letters and numbers.");
    }
  }

  /**
   * Gets the room name.
   *
   * @return The room name.
   */
  public String getRoomName() {
    return name;
  }
    /*
    /The method getRoomName should be replaced by a generic method in the ValueObject interface
    */

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

    if (o instanceof RoomName roomName) {
      return this.name.equals(roomName.name);
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
   * Returns the string representation of the object.
   *
   * @return The string representation of the object.
   */
  @Override
  public String toString() {
    return this.name;
  }
}
