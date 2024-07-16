/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object.postal_code;

public class PostalCodeFactory implements IPostalCodeFactory {
  public IPostalCode createPostalCode(String postalCode, String countryCode) {
    switch (countryCode) {
      case "US":
        return new PostalCode(postalCode, PostalCodePattern.US);
      case "CA":
        return new PostalCode(postalCode, PostalCodePattern.CA);
      case "PT":
        return new PostalCode(postalCode, PostalCodePattern.PT);
      case "ES":
        return new PostalCode(postalCode, PostalCodePattern.ES);
      default:
        throw new IllegalArgumentException(
          "No valid postal code strategy found for: " + countryCode);
    }
  }
}

