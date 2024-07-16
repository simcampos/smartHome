/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

/**
 * This class represents the data transfer object for the Actuator data for an Actuator with decimal limits
 * to be received from the client.
 */

package smarthome.utils.entry_dto.actuator_entry_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ActuatorWithDecimalLimitsEntryDTOImp implements IActuatorEntryDTO {

  @NotBlank(message = "DeviceID cannot be empty")
  public final String deviceID;

  @NotBlank(message = "ActuatorModelPath cannot be empty")
  public final String actuatorModelPath;

  @NotBlank(message = "ActuatorTypeID cannot be empty")
  public final String actuatorTypeID;

  @NotBlank(message = "ActuatorName cannot be empty")
  public final String actuatorName;

  @NotBlank(message = "MinLimit cannot be empty")
  public final double minLimit;

  @NotBlank(message = "MaxLimit cannot be empty")
  public final double maxLimit;
}
