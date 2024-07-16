/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils.entry_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ActuatorTypeEntryDTO {

  @NotBlank(message = "ActuatorTypeDescription cannot be empty")
  public String actuatorTypeDescription;
  @NotBlank(message = "Unit cannot be empty")
  public String unit;
}
