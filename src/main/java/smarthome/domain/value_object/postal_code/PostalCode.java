/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object.postal_code;

public class PostalCode implements IPostalCode {
  private String postalCode;

  public PostalCode(String postalCode, PostalCodePattern pattern) {
    if (!pattern.validate(postalCode)) {
      throw new IllegalArgumentException("Invalid postal code format");
    }
    this.postalCode = postalCode;
  }

  @Override
  public String getCode() {
    return postalCode;
  }
}

