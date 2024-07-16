/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

/**
 * This class represents the data transfer object for the house data
 * to be received from the client.
 */

package smarthome.utils.entry_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HouseEntryDTO {

  @NotBlank(message = "Street cannot be empty")
  public final String street;

  @NotBlank(message = "Door number cannot be empty")
  public final String doorNumber;

  @NotBlank(message = "Postal code cannot be empty")
  public final String postalCode;

  @NotBlank(message = "Country code cannot be empty")
  public final String countryCode;

  public final double latitude;

  public final double longitude;
}

