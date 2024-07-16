/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IDomainID;

public class ActuatorID implements IDomainID {

  private final String id;


  /**
   * Constructor of the class ActuatorID.
   *
   * @param actuatorID is the ID of the actuator.
   */
  public ActuatorID(String actuatorID) {
    validateId(actuatorID);
    this.id = actuatorID;
  }

  /**
   * Validates the ID of the actuator.
   *
   * @param actuatorID is the ID of the actuator.
   */
  private void validateId(String actuatorID) {
    if (actuatorID == null || actuatorID.isBlank()) {
      throw new IllegalArgumentException("'actuatorID' must be a non-empty string.");
    }

  }

  /**
   * Equals method for ActuatorID.
   *
   * @param object Object.
   * @return boolean.
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }

    if (object instanceof ActuatorID actuatorID) {

      return this.id.equals(actuatorID.id);
    }
    return false;
  }

  /**
   * Getter for ID.
   *
   * @return the ID of the actuator.
   */
  @Override
  public String getID() {
    return this.id;
  }

  /**
   * HashCode method for ActuatorID.
   *
   * @return int.
   */
  public int hashCode() {
    return this.id.hashCode();
  }

  /**
   * toString method for ActuatorID.
   *
   * @return String.
   */
  @Override
  public String toString() {
    return "ActuatorID: " + this.id;
  }
}
