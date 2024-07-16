/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IDomainID;
import smarthome.ddd.IValueObject;

public class SensorTypeDescription implements IValueObject, IDomainID {

  private String description;

  public SensorTypeDescription(String description) {
    validate(description);
  }


  /**
   * Validates the description of the sensor type.
   *
   * @param description is the description of the sensor type.
   */
  private void validate(String description) {
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException(
          "The value of 'description' should not null, blank, or empty.");
    } else if (description.length() > 50) {
      throw new IllegalArgumentException("The description cannot have more than 50 characters.");
    } else {
      this.description = description;
    }
  }

  /**
   * Getter for the ID of the sensor type.
   *
   * @return the ID of the sensor type.
   */
  @Override
  public String getID() {
    return description;
  }

  /**
   * HashCode method for SensorTypeDescription.
   *
   * @return the hashcode as an int.
   */
  @Override
  public int hashCode() {
    return description.hashCode();
  }

  /**
   * Equals method for SensorTypeDescription.
   *
   * @param o Object.
   * @return boolean.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof SensorTypeDescription objectDescription) {
      return (this.description.equals(objectDescription.description));
    }
    return false;
  }
}
