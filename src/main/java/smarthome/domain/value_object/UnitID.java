/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IDomainID;

/**
 * This class ensures that the unit ID adheres to specific validation rules before it is
 * assigned.
 */
public class UnitID implements IDomainID {

  private final String unitID;

  /**
   * Constructs a new unitID instance after validating the provided ID.
   *
   * @param unitID The string representation of the unit ID. It must not be null, empty, or blank.
   * @throws IllegalArgumentException if the UnitID is null, empty, or blank.
   */
  public UnitID(String unitID) {
    validateID(unitID);
    this.unitID = unitID.trim();
  }

  /**
   * Validates the given unit ID.
   *
   * @param unitID The unit ID to validate.
   * @throws IllegalArgumentException if the UnitID is null, empty, or blank.
   */
  private void validateID(String unitID) {
    if (unitID == null || unitID.isBlank()) {
      throw new IllegalArgumentException(
          "The value of 'UnitID' should not null, blank, or empty.");
    }
  }

  /**
   * Retrieves the unit ID.
   *
   * @return The unit ID as a string.
   */
  public String getID() {
    return unitID;
  }

  /**
   * Checks if this unitID is equal to another object. Two unitID instances are
   * considered equal if their IDs are equal.
   *
   * @param o The object to compare this instance against.
   * @return true if the given object is an instance of unitID and has an equal ID; false
   * otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o instanceof UnitID objectUnitId) {
      return this.unitID.equals(objectUnitId.unitID);
    }
    return false;
  }

  /**
   * Generates a hash code for this unitID.
   *
   * @return The hash code of the unit ID.
   */
  @Override
  public int hashCode() {
    return unitID.hashCode();
  }

  /**
   * Returns the string representation of the unit ID.
   *
   * @return The unit ID as a string.
   */
  @Override
  public String toString() {
    return unitID;
  }
}
