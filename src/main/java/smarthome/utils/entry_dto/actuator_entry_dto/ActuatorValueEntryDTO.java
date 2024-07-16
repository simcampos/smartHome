/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils.entry_dto.actuator_entry_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ActuatorValueEntryDTO {

  @NotBlank(message = "DeviceID cannot be empty")
  public final String deviceID;

  @NotBlank(message = "ActuatorID cannot be empty")
  public final String actuatorID;

  public final int value;

}
