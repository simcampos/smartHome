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

public class TypeDescription implements IValueObject, IDomainID {

  private String description;

  public TypeDescription(String description) {
    validate(description);
  }

  /**
   * Method to validate the description.
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
   * Getter for the description.
   *
   * @return the description of the sensor type.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Equals method for TypeDescription.
   *
   * @param o Object.
   * @return boolean.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o instanceof TypeDescription objectDescription) {
      return this.description.equals(objectDescription.description);
    }
    return false;
  }

  /**
   * Method to get the string representation of the object.
   *
   * @return the description of the object.
   */
  @Override
  public String toString() {
    return description;
  }

  /**
   * Get the id of the object as a String
   *
   * @return the id of the object
   */
  @Override
  public String getID() {
    return description;
  }

  /**
   * Get the hash code of the object
   *
   * @return the hash code of the object
   */
  @Override
  public int hashCode() {
    return description.hashCode();
  }
}
