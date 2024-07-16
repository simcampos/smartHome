/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IValueObject;

public class DeviceStatus implements IValueObject {

  private final boolean status;

  /**
   * Constructor of the class DeviceStatus.
   *
   * @param status is the status of the device.
   */
  public DeviceStatus(boolean status) {
    this.status = status;
  }

  /**
   * Equals method for DeviceStatus.
   *
   * @param object Object.
   * @return boolean.
   */
  public boolean equals(Object object) {

    if (this == object) {
      return true;
    }

    if (object instanceof DeviceStatus deviceStatus) {
      return this.status == deviceStatus.status;
    }
    return false;
  }

  /**
   * Getter for status.
   *
   * @return _status.
   */
  public boolean getStatus() {
    return status;
  }

  /**
   * HashCode method for DeviceStatus.
   *
   * @return the hashcode as an int.
   */
  public int hashCode() {
    return Boolean.hashCode(status);
  }

  @Override
  public String toString() {
    return status ? "ON" : "OFF";
  }
}
