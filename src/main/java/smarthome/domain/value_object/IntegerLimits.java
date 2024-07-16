/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IValueObject;

public class IntegerLimits implements IValueObject {

  private final int lowerLimit;
  private final int upperLimit;

  /**
   * Constructor for SetIntegerActuatorLimits
   *
   * @param lowerLimit lower limit
   * @param upperLimit upper limit
   */
  public IntegerLimits(int lowerLimit, int upperLimit) {
    validateLimits(lowerLimit, upperLimit);
    this.lowerLimit = lowerLimit;
    this.upperLimit = upperLimit;
  }

  /**
   * Validates the limits
   *
   * @param lowerLimit lower limit
   * @param upperLimit upper limit
   */
  private void validateLimits(int lowerLimit, int upperLimit) {
    if (lowerLimit > upperLimit) {
      throw new IllegalArgumentException("Lower limit cannot be greater than upper limit");
    }
  }

  /**
   * Gets the lower limit
   *
   * @return lower limit
   */
  public int getLowerLimit() {
    return lowerLimit;
  }

  /**
   * Gets the upper limit
   *
   * @return upper limit
   */
  public int getUpperLimit() {
    return upperLimit;
  }

  /**
   * Checks if the object is equal to this
   *
   * @param o object to compare
   * @return true if the object is equal to this, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof IntegerLimits that) {
      return lowerLimit == that.lowerLimit && upperLimit == that.upperLimit;
    }
    return false;
  }

  /**
   * Gets the hash code of the object
   *
   * @return hash code
   */
  @Override
  public int hashCode() {
    return Integer.hashCode(lowerLimit) + Integer.hashCode(upperLimit);
  }
}
