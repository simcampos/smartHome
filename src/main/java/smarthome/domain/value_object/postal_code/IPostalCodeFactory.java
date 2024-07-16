/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object.postal_code;

public interface IPostalCodeFactory {

  /**
   * Creates a PostalCode object based on the given postal code and country code.
   *
   * @param postalCode  The postal code value.
   * @param countryCode The country code to determine the implementation class.
   * @return A PostalCode object instantiated based on the provided postal code and country code.
   */
  IPostalCode createPostalCode(String postalCode, String countryCode);
}
