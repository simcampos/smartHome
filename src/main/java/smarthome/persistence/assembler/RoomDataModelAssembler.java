/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.assembler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import smarthome.domain.room.IRoomFactory;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.persistence.data_model.RoomDataModel;
import smarthome.utils.Validator;

@Component
public class RoomDataModelAssembler implements IDataModelAssembler<RoomDataModel, Room> {

  private final IRoomFactory roomFactory;

  /**
   * Class constructor
   *
   * @param roomFactory is the factory used to create Room instances.
   */
  public RoomDataModelAssembler(IRoomFactory roomFactory) {
    Validator.validateNotNull(roomFactory, "Room Factory");
    this.roomFactory = roomFactory;
  }


  /**
   * Converts a RoomDataModel instance to a Room instance.
   *
   * @param roomDataModel is the domain entity to be converted.
   * @return a Room instance.
   */
  @Override
  public Room toDomain(RoomDataModel roomDataModel) {
    Validator.validateNotNull(roomDataModel, "Room Data Model");

    RoomID roomID = new RoomID(roomDataModel.getRoomID());
    HouseID houseID = new HouseID(roomDataModel.getHouseID());
    RoomFloor roomFloor = new RoomFloor(roomDataModel.getFloor());
    RoomName roomName = new RoomName(roomDataModel.getRoomName());
    Dimension dimension = new Dimension(roomDataModel.getWidth(), roomDataModel.getDepth(),
        roomDataModel.getHeight());

    Room room = roomFactory.createRoom(houseID, roomName, dimension, roomFloor, roomID);

    return room;
  }

  /**
   * Converts a list of RoomDataModel instances to a list of Room instances.
   *
   * @param roomDataModels is the list of domain entities to be converted.
   * @return a list of Room instances.
   */
  @Override
  public List<Room> toDomain(List<RoomDataModel> roomDataModels) {
    List<Room> rooms = new ArrayList<>();

    for (RoomDataModel roomDataModel : roomDataModels) {
      Room room = toDomain(roomDataModel);
      rooms.add(room);
    }

    return rooms;
  }
}
