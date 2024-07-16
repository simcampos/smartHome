/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IDomainID;
import smarthome.domain.sensor.temperature_sensor.TemperatureSensor;

public class ActuatorModelID implements IDomainID {

  private final String id;

  /**
   * Constructor for the ActuatorModelID class.
   * @param id is the ID of the actuator model.
   * @throws IllegalArgumentException if the ID is null, blank, or empty.
   */
  public ActuatorModelID(String id) throws IllegalArgumentException {
    validationActuatorModelID(id);
    this.id = id;
  }

  private void validationActuatorModelID(String actuatorModelID) {
    if (actuatorModelID == null || actuatorModelID.isBlank()) {
      throw new IllegalArgumentException(
          "The value of 'actuatorModelID' should not null, blank, or empty.");
    }
  }

  /**
   * Returns the ID of the actuator model.
   * @return the ID of the actuator model.
   */
  @Override
  public String getID() {
    return id;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   * @param o the reference object with which to compare.
   * @return true if this object is the same as the obj argument; false otherwise.
   */
  public boolean equals(Object o) {
    if (o instanceof ActuatorModelID actuatorModelID){
      return this.id.equals(actuatorModelID.id);
    }
    return false;
  }


  /**
   * Returns a hash code value for the object.
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
