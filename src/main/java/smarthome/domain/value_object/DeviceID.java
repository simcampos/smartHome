/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IDomainID;

public class DeviceID implements IDomainID {

  private final String id;

  /**
   * Constructor for DeviceID
   *
   * @param deviceID String
   */
  public DeviceID(String deviceID) {
    validateDeviceID(deviceID);
    this.id = deviceID.trim();
  }

  /**
   * Validates the DeviceID. It should not be null, blank, or empty.
   *
   * @param deviceID String
   */
  private void validateDeviceID(String deviceID) {
    if (deviceID == null || deviceID.isBlank()) {
      throw new IllegalArgumentException(
          "The value of 'deviceID' should not null, blank, or empty.");
    }
  }

  /**
   * Getter for ID
   *
   * @return id
   */
  public String getID() {
    return id;
  }

  /**
   * Equals method for DeviceID
   *
   * @param o Object
   * @return boolean
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o instanceof DeviceID objectDeviceID) {

      return this.id.equals(objectDeviceID.id);
    }
    return false;
  }

  /**
   * HashCode method for DeviceID
   *
   * @return the hashcode as an int
   */
  public int hashCode() {
    return id.hashCode();
  }

  /**
   * toString method for DeviceID
   *
   * @return the id as a string
   */
  @Override
  public String toString() {
    return id;
  }
}
