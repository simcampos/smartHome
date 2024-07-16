/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IValueObject;

public class RoomFloor implements IValueObject {

  private int floor;

  public RoomFloor(int floor) {
    validateFloor(floor);
  }

  /**
   * Validates the floor number.
   *
   * @param floor is the floor number.
   */
  private void validateFloor(int floor) {
    if (Math.max(floor, -35) != floor) {
      throw new IllegalArgumentException("Invalid floor number.");
    }
    if (Math.min(floor, 162) != floor) {
      throw new IllegalArgumentException("Invalid floor number.");
    }
    this.floor = floor;
  }

  /**
   * Getter for the floor number.
   *
   * @return floor.
   */
  public int getFloor() {
    return floor;
  }

  /**
   * Equals method for RoomFloor.
   *
   * @param o Object.
   * @return boolean.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o instanceof RoomFloor objectRoomFloor) {
      return this.floor == objectRoomFloor.floor;
    }
    return false;
  }

  /**
   * HashCode method for RoomFloor.
   *
   * @return the hashcode as an int.
   */
  @Override
  public int hashCode() {
    return Integer.hashCode(floor);
  }


  /**
   * toString method for RoomFloor.
   *
   * @return the floor number as a string.
   */
  @Override
  public String toString() {
    return "RoomFloor:" +
        " floor=" + floor;
  }

}
