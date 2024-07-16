/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IValueObject;
import smarthome.utils.Validator;

public class ReadingValue implements IValueObject {

  private final String value;

  /**
   * Constructor of the class ReadingValue.
   *
   * @param value is the value of the reading.
   */
  public ReadingValue(String value) {
    Validator.validateNotNull(value, "Reading Value");
    this.value = value;
  }

  /**
   * Getter for the reading value.
   *
   * @return readingValue.
   */
  public String getValue() {
    return value;
  }

  /**
   * Equals method for ReadingValue.
   *
   * @param o Object.
   * @return boolean.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof ReadingValue readingValue) {
      return value.equals(readingValue.value);
    }
    return false;
  }

  /**
   * HashCode method for ReadingValue.
   *
   * @return the hashcode as an int.
   */
  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
