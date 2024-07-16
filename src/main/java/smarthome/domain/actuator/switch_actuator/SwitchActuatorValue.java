/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator.switch_actuator;

import smarthome.ddd.IActuatorValue;
import smarthome.ddd.IValueObject;

public class SwitchActuatorValue implements IActuatorValue {


  private boolean value;

  /**
   * Instantiates a new Switch actuator value.
   *
   * @param value the value
   */

  public SwitchActuatorValue(boolean value) {
    this.value = value;
  }


  /**
   * Performs change the value of the actuator.
   *
   * @return the boolean
   */
  public boolean performAction() {
    value = !value;
    return value;
  }


  /**
   * Returns the value of the actuator as a string.
   *
   * @return the string
   */

  @Override
  public String toString() {
    return value ? "On" : "Off";
  }

  /**
   * Method to compare two SwitchActuatorValue objects.
   *
   * @param o the o
   * @return the boolean
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof SwitchActuatorValue switchActuatorValue) {
      return switchActuatorValue.value == this.value;
    }
    return false;
  }

  /**
   * Method to get the hash code of the SwitchActuatorValue object.
   *
   * @return the hash code
   */
  @Override
  public int hashCode() {
    return Boolean.hashCode(this.value);
  }

}
