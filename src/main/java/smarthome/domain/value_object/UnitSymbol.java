/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IValueObject;

public class UnitSymbol implements IValueObject {

  private final String unit;

  /**
   * Constructs a new UnitSymbol instance after validating the provided unit.
   *
   * @param unit The string representation of the unit. It must not be null, empty, or blank.
   */
  public UnitSymbol(String unit) {
    unitValidation(unit);
    this.unit = unit;
  }

  /**
   * Validates the given unit.
   *
   * @param unit The unit to validate.
   * @throws IllegalArgumentException if the unit is null, empty, or blank.
   */
  private void unitValidation(String unit) {
    if (unit == null || unit.trim().isEmpty() || unit.length() > 5) {
      throw new IllegalArgumentException("Invalid unit");
    }
  }

  /**
   * Retrieves the unit.
   *
   * @return The unit as a string.
   */
  public String getSymbol() {
    return unit;
  }

  /**
   * Checks if this UnitSymbol is equal to another object. Two UnitSymbol instances are considered
   * equal if their units are equal.
   *
   * @param object The object to compare this instance against.
   * @return true if the given object is an instance of UnitSymbol and has an equal unit; false
   * otherwise.
   */
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof UnitSymbol unit) {

      return this.unit.equals(unit.unit);
    }
    return false;
  }

  /**
   * Returns the hash code of the unit.
   *
   * @return The hash code of the unit.
   */
  @Override
  public int hashCode() {
    return unit.hashCode();
  }

  /**
   * Getter for the unit
   *
   * @return The unit as a string.
   */
  public String getUnit() {
    return unit;
  }

  /**
   * Returns the string representation of the unit.
   *
   * @return The string representation of the unit.
   */
  @Override
  public String toString() {
    return "Unit:" + unit;
  }

}
