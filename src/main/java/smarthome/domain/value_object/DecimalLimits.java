/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IValueObject;

/**
 * Represents the limits within which a decimal actuator can set values.
 */
public class DecimalLimits implements IValueObject {

  private final double lowerLimit;

  private final double upperLimit;

  /**
   * Constructs a SetDecimalActuatorLimits object with the specified lower and upper limits.
   *
   * @param lowerLimit The lower limit for the actuator.
   * @param upperLimit The upper limit for the actuator.
   * @throws IllegalArgumentException if the lower limit is greater than the upper limit.
   */
  public DecimalLimits(double lowerLimit, double upperLimit) {
    validateLimits(lowerLimit, upperLimit);
    this.lowerLimit = lowerLimit;
    this.upperLimit = upperLimit;
  }

  /**
   * Validates the provided limits.
   *
   * @param lowerLimit The lower limit for the actuator.
   * @param upperLimit The upper limit for the actuator.
   */
  private void validateLimits(double lowerLimit, double upperLimit) {
    if (lowerLimit > upperLimit) {
      throw new IllegalArgumentException("Lower limit cannot be greater than upper limit");
    }
  }

  /**
   * Gets the lower limit for the actuator.
   *
   * @return The lower limit for the actuator.
   */
  public double getLowerLimit() {
    return lowerLimit;
  }

  /**
   * Gets the upper limit for the actuator.
   *
   * @return The upper limit for the actuator.
   */
  public double getUpperLimit() {
    return upperLimit;
  }

  /**
   * Overrides the equals method to compare two SetDecimalActuatorLimits objects.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof DecimalLimits decimalLimits) {
      double epsilon = 0.001;
      double value = Math.abs(lowerLimit - decimalLimits.lowerLimit);
      double value2 = Math.abs(upperLimit - decimalLimits.upperLimit);
      return Math.min(value, epsilon) == value && Math.min(value2, epsilon) == value2;

    }
    return false;
  }

  @Override
  public int hashCode() {
    return Double.hashCode(lowerLimit) + Double.hashCode(upperLimit);
  }

  /**
   * Overrides the toString method to return a string representation of the SetDecimalActuatorLimits
   * object.
   */
  public String toString() {
    return "Lower limit: " + this.lowerLimit + ", Upper limit: " + this.upperLimit;
  }
}

