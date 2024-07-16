/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

/**
 * This class represents the data transfer object for the log data
 * to be received from the client.
 */

package smarthome.utils.entry_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LogEntryDTO {

  @NotBlank (message = "DeviceID cannot be empty")
  public String deviceID;

  @NotBlank (message = "LogTypeID cannot be empty")
  public String timeStart;

  @NotBlank (message = "LogTypeID cannot be empty")
  public String timeEnd;

}
