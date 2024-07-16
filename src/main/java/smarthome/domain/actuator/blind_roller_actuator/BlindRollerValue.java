/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator.blind_roller_actuator;

import smarthome.ddd.IActuatorValue;

public class BlindRollerValue implements IActuatorValue {

  private final int value;

  /**
   * Constructor for BlindRollerValue
   *
   * @param value It must be between 0 and 100.
   */
  public BlindRollerValue(int value) {
    validateValue(value);
    this.value = value;
  }

  /**
   * Validates the value of the blind roller.
   *
   * @param value The value to be validated.
   */
  private void validateValue(int value) {
    if (value < 0 || value > 100) {
      throw new IllegalArgumentException("The value must be between 0 and 100.");
    }
  }

  /**
   * Gets the value of the blind roller.
   *
   * @return The value of the blind roller.
   */
  public String toString() {
    return this.value + "";
  }

}
