/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.ddd;

public interface IDomainID extends IValueObject {

  /**
   * Method to get the unique identifier of the domain entity.
   *
   * @return the unique identifier of the domain entity.
   */
  String getID();

  /**
   * Method to check if the domain entity is equal to another object.
   *
   * @param o is the object to be compared.
   * @return true if the domain entity is equal to the object, false otherwise.
   */
  boolean equals(Object o);

  /**
   * Method to get the hash code of the domain entity.
   *
   * @return the hash code of the domain entity.
   */
  int hashCode();


}
