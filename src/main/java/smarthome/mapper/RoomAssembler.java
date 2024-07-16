/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import smarthome.ddd.IAssembler;
import smarthome.domain.room.Room;
import smarthome.utils.Validator;
import smarthome.utils.dto.RoomDTO;

@Component
public class RoomAssembler implements IAssembler<Room, RoomDTO> {


  /**
   * Method to convert a Room into a RoomDTO.
   *
   * @param room is the Room to be converted.
   * @return the RoomDTO.
   */
  @Override
  public RoomDTO domainToDTO(Room room) {

    Validator.validateNotNull(room, "Room");

    String roomName = room.getName().toString();
    String dimension = room.getDimension().toString();
    int roomFloor = room.getFloor().getFloor();
    String roomID = room.getID().toString();

    return new RoomDTO(roomName, dimension, roomFloor, roomID);
  }

  /**
   * Method to convert a list of Rooms into a list of RoomDTOs.
   *
   * @param rooms is the list of Rooms to be converted.
   * @return the list of RoomDTOs.
   */
  @Override
  public List<RoomDTO> domainToDTO(List<Room> rooms) {
    if (rooms == null) {
      throw new IllegalArgumentException("The list of Rooms cannot be null.");
    }
    return rooms.stream().map(this::domainToDTO).toList();
  }

}
