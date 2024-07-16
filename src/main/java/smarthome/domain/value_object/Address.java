/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IValueObject;
import smarthome.domain.value_object.postal_code.IPostalCode;
import smarthome.domain.value_object.postal_code.IPostalCodeFactory;

public class Address implements IValueObject {

  private static final int COUNTRY_CODE_LENGTH = 2;

  private final String street;
  private final String doorNumber;
  private final String countryCode; // ISO 3166-1 alpha-2 country code
  private final IPostalCode postalCode;

  public Address(String street, String doorNumber, String postalCode, String countryCode,
      IPostalCodeFactory factory) {
    validateStreet(street);
    validateDoorNumber(doorNumber);
    validateCountryCode(countryCode);
    this.street = street;
    this.doorNumber = doorNumber;
    this.countryCode = countryCode;
    this.postalCode = factory.createPostalCode(postalCode, countryCode);
  }

  /**
   * Street validation method.
   *
   * @param street is the street of the address.
   */
  private void validateStreet(String street) {
    if (street == null ||
        street.trim().isEmpty() ||
        !street.matches("^[a-zA-Z0-9 ]+$")) {
      throw new IllegalArgumentException("Invalid street");
    }
  }

  /**
   * Door number validation method.
   *
   * @param doorNumber is the door number of the address.
   */

  private void validateDoorNumber(String doorNumber) {
    if (doorNumber == null ||
        doorNumber.trim().isEmpty() ||
        !doorNumber.matches("^[a-zA-Z0-9 ]+$")) {
      throw new IllegalArgumentException("Invalid door number");
    }
  }

  private void validateCountryCode(String countryCode) {
    if (countryCode == null ||
        countryCode.length() != COUNTRY_CODE_LENGTH ||
        !countryCode.matches("^[A-Z]+$")) {
      throw new IllegalArgumentException("Invalid country code");
    }
  }

  /**
   * Equals method for Address.
   *
   * @param object Object.
   * @return boolean.
   */
  public boolean equals(Object object) {

    if (this == object) {
      return true;
    }

    if (object instanceof Address address) {

      return this.street.equals(address.street) &&
          this.doorNumber.equals(address.doorNumber) &&
          this.countryCode.equals(address.countryCode) &&
          this.postalCode.equals(address.postalCode);
    }
    return false;
  }

  /**
   * hashCode method for Address.
   */
  public int hashCode() {
    return this.street.hashCode() + this.doorNumber.hashCode() + this.countryCode.hashCode()
        + this.postalCode.hashCode();
  }

  /**
   * Getter for street.
   *
   * @return _street.
   */
  public String getStreet() {
    return this.street;
  }

  /**
   * Getter for door number.
   *
   * @return _doorNumber.
   */
  public String getDoorNumber() {
    return this.doorNumber;
  }

  /**
   * Getter for country code.
   *
   * @return _countryCode.
   */
  public String getCountryCode() {
    return this.countryCode;
  }

  /**
   * Getter for postal code.
   *
   * @return _postalCode.
   */
  public IPostalCode getPostalCode() {
    return this.postalCode;
  }

  /**
   * Address object to string
   *
   * @return String
   */
  public String toString() {
    return this.street + ", " + this.doorNumber;
  }


}
