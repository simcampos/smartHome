/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

/**
 * This class represents the data transfer object for the Unit data
 * to be received from the client.
 */

package smarthome.utils.entry_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UnitEntryDTO {

  @NotBlank (message = "Description cannot be empty")
  public String description;

  @NotBlank (message = "UnitSymbol cannot be empty")
  public String unitSymbol;
}
