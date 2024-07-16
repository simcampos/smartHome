/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.switch_sensor;

import smarthome.ddd.IValueObject;

public class SwitchSensorValue implements IValueObject {

  private final boolean bValue;

  /**
   * Constructs a SwitchSensorValue with a specified state.
   *
   * @param bValue The state of the switch sensor.
   */
  public SwitchSensorValue(boolean bValue) {
    this.bValue = bValue;
  }

  /**
   * Returns a string representation of the switch sensor value.
   *
   * @return The switch sensor value as a string.
   */
  public String toString() {
    return this.bValue ? "On" : "Off";
  }

  /**
   * Method to check if the switch sensor value is equal to another switch sensor value.
   */
  public boolean equals(Object o) {
    if (o instanceof SwitchSensorValue switchSensorValue) {
      return this.bValue == switchSensorValue.bValue;
    }
    return false;
  }

  /**
   * Method to generate a hash code for the switch sensor value.
   */
  public int hashCode() {
    return Boolean.hashCode(this.bValue);
  }
}
