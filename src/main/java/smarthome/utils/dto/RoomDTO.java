/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils.dto;

import org.springframework.hateoas.RepresentationModel;
import smarthome.ddd.IDTO;

/**
 * Data Transfer Object (DTO) representing information about a room.
 */
public class RoomDTO extends RepresentationModel<RoomDTO> implements IDTO {

  public final String roomName;
  public final String dimensions;
  public final int floor;
  public final String roomId;

  /**
   * Constructs a new RoomDTO object with the specified room details.
   *
   * @param roomName      The name of the room.
   * @param roomDimension The dimensions of the room.
   * @param roomFloor     The floor of the room.
   * @param roomID        The unique identifier of the room.
   */
  public RoomDTO(String roomName, String roomDimension, int roomFloor, String roomID) {
    this.roomName = roomName;
    this.dimensions = roomDimension;
    this.floor = roomFloor;
    this.roomId = roomID;
  }

  /**
   * Returns a string representation of the RoomDTO object.
   *
   * @return A string representation of the RoomDTO object.
   */
  @Override
  public String toString() {
    return roomName + ' ' + dimensions + ' ' + floor + ' ' + roomId;
  }
}
