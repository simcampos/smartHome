/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IDomainID;

public class ActuatorTypeID implements IDomainID {

  private final String id;

  /**
   * Constructor for the ActuatorTypeID class.
   *
   * @param actuatorTypeID is the ID of the actuator type.
   */
  public ActuatorTypeID(String actuatorTypeID) throws IllegalArgumentException {
    validationActuatorTypeID(actuatorTypeID);
    this.id = actuatorTypeID.trim();

  }

  /**
   * Validates the actuator type ID.
   *
   * @param actuatorTypeID is the ID of the actuator type.
   */
  private void validationActuatorTypeID(String actuatorTypeID) {
    if (actuatorTypeID == null || actuatorTypeID.trim().isBlank()) {
      throw new IllegalArgumentException(
          "The value of 'actuatorTypeID' should not null, blank, or empty.");
    }
  }

  /**
   * Equals method for ActuatorTypeID.
   *
   * @param object Object.
   * @return boolean.
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }

    if (object instanceof ActuatorTypeID actuatorTypeID) {

      return this.id.equals(actuatorTypeID.id);
    }
    return false;
  }

  /**
   * Getter for ID.
   *
   * @return the ID of the actuator type.
   */
  @Override
  public String getID() {
    return this.id;
  }

  /**
   * HashCode method for ActuatorTypeID.
   *
   * @return the hashcode as an int.
   */
  public int hashCode() {
    return this.id.hashCode();
  }

  /**
   * toString method for ActuatorTypeID.
   *
   * @return String.
   */
  @Override
  public String toString() {
    return id;
  }

}
