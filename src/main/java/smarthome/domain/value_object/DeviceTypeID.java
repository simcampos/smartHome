/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IDomainID;

public class DeviceTypeID implements IDomainID {

  private final String id;

  /**
   * Class constructor
   *
   * @param deviceTypeID The device type ID to set.
   */

  public DeviceTypeID(String deviceTypeID) {
    validateId(deviceTypeID);
    this.id = deviceTypeID;
  }

  private void validateId(String deviceTypeID) {
    if (deviceTypeID == null || deviceTypeID.isBlank()) {
      throw new IllegalArgumentException("'deviceTypeID' must be a non-empty string.");
    }
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

    if (object instanceof DeviceTypeID deviceTypeID) {

      return this.id.equals(deviceTypeID.id);
    }
    return false;
  }

  /**
   * Gets the device type ID.
   *
   * @return The device type ID.
   */

  public String getID() {
    return id;
  }


  /**
   * @return The hash code of the device type ID.
   */
  @Override
  public int hashCode() {
    return id.hashCode();
  }

  /**
   * @return The string representation of the device type ID.
   */

  @Override
  public String toString() {
    return id;
  }
}
