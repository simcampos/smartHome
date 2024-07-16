/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

/**
 * This class represents the data transfer object for the room data
 * to be received from the client.
 */

package smarthome.utils.entry_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RoomEntryDTO {

  @NotBlank (message = "Name cannot be empty")
  public String name;

  @NotBlank (message = "Floor cannot be empty")
  public int floor;

  @NotBlank (message = "Width cannot be empty")
  public int width;

  @NotBlank (message = "Length cannot be empty")
  public int length;

  @NotBlank (message = "Height cannot be empty")
  public int height;
}
