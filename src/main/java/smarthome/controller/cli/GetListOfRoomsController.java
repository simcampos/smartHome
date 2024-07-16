/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.cli;

import java.util.Collections;
import java.util.List;
import smarthome.ddd.IAssembler;
import smarthome.domain.room.Room;
import smarthome.service.IRoomService;
import smarthome.utils.Validator;
import smarthome.utils.dto.RoomDTO;

public class GetListOfRoomsController {

  private final IRoomService roomService;
  private final IAssembler<Room, RoomDTO> roomAssembler;


  /**
   * Constructor for the GetListOfRoomsController class.
   *
   * @param roomService   The room service.
   * @param roomAssembler The room assembler.
   */
  public GetListOfRoomsController(IRoomService roomService,
      IAssembler<Room, RoomDTO> roomAssembler) {
    Validator.validateNotNull(roomService, "Room service");
    Validator.validateNotNull(roomAssembler, "Room assembler");

    this.roomAssembler = roomAssembler;
    this.roomService = roomService;
  }

  /**
   * Gets the list of rooms.
   *
   * @return The list of rooms.
   */
  public List<RoomDTO> getRooms() {

    List<Room> listOfRooms = roomService.getAllRooms();
    if (listOfRooms.isEmpty()) {
      return Collections.emptyList();
    }
    List<RoomDTO> listOfRoomsDTO = roomAssembler.domainToDTO(listOfRooms);

    return List.copyOf(listOfRoomsDTO);
  }
}
