/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IDomainID;

public class HouseID implements IDomainID {

  private final String id;

  /**
   * Constructor for HouseID
   *
   * @param houseID String
   */
  public HouseID(String houseID) {
    validateHouseID(houseID);
    this.id = houseID.trim();
  }

  /**
   * Validates the HouseID It should not be null, blank, or empty
   */
  private void validateHouseID(String houseID) {
    if (houseID == null || houseID.isBlank()) {
      throw new IllegalArgumentException(
          "The value of 'houseID' should not null, blank, or empty.");
    }
  }

  /**
   * Getter for ID
   *
   * @return _id
   */
  public String getID() {
    return id;
  }

  /**
   * Equals method for HouseID
   *
   * @param o Object
   * @return boolean
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o instanceof HouseID objectHouseId) {

      return this.id.equals(objectHouseId.id);
    }
    return false;
  }


  /**
   * HashCode method for HouseID
   *
   * @return the hashcode as an int
   */
  public int hashCode() {
    return id.hashCode();
  }

  /**
   * toString method for HouseID
   *
   * @return the HouseID as a string
   */
  @Override
  public String toString() {
    return id;
  }
}
