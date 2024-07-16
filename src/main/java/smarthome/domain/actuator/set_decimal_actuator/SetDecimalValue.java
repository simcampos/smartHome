/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator.set_decimal_actuator;

import smarthome.ddd.IActuatorValue;
import smarthome.ddd.IValueObject;

/**
 * Represents a decimal value that can be set for an actuator.
 */
public class SetDecimalValue implements IActuatorValue {

  private final double value;

  /**
   * Constructs a SetDecimalValue object with the specified decimal value.
   *
   * @param value The decimal value to be set.
   */
  public SetDecimalValue(double value) {
    this.value = value;
  }

  /**
   * Gets the decimal value.
   *
   * @return The decimal value.
   */
  public double getValue() {
    return value;
  }

  /**
   * Returns a string representation of the decimal value.
   *
   * @return A string representation of the decimal value.
   */
  @Override
  public String toString() {
    return Double.toString(value);
  }

  /**
   * Overrides the equals method to compare two SetDecimalValue objects.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof SetDecimalValue setDecimalValue) {
      double epsilon = 0.001;
      return Math.abs(value - setDecimalValue.value) < epsilon;
    }
    return false;
  }

  /**
   * Overrides the hashCode method to return the hash code of the decimal value.
   */
  @Override
  public int hashCode() {
    return Double.hashCode(value);
  }
}

