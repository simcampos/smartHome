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

public class ModelPath implements IValueObject, IDomainID {

  private String path;

  /**
   * Class constructor.
   *
   * @param path The path to set.
   */
  public ModelPath(String path) {
    validatePath(path);
  }

  /**
   * Validates the path.
   *
   * @param path The path to validate.
   */
  private void validatePath(String path) throws IllegalArgumentException {
    if (path == null || path.trim().isEmpty()) {
      throw new IllegalArgumentException("Please enter a valid path.");
    } else {
      this.path = path;
    }
  }

  /**
   * Gets the path.
   *
   * @return The path.
   */
  @Override
  public String toString() {
    return path;
  }

  /**
   * Gets the path.
   *
   * @return The path.
   */
  @Override
  public String getID() {
    return this.path;
  }


  /**
   * Compares the current object with another object of the same type.
   *
   * @param o The object to compare with.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelPath modelPath = (ModelPath) o;
    return path.equals(modelPath.path);
  }

  /**
   * @return the hash code of the object.
   */
  @Override
  public int hashCode() {
    return path.hashCode();
  }


}
