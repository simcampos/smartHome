/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.room;

import org.springframework.stereotype.Component;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;

/**
 * Implementation of the {@link IRoomFactory} interface, responsible for creating {@link Room}
 * instances. This factory encapsulates the logic for room creation, ensuring that all necessary
 * validations and initializations are performed before a {@link Room} object is returned to the
 * caller.
 */
@Component
public class RoomFactoryImpl implements IRoomFactory {

  /**
   * Creates a new {@link Room} instance using the provided house ID, room name, dimension, and room
   * floor. This method ensures that a {@link Room} object is instantiated with valid and non-null
   * parameters, leveraging the Room constructor for validation and initialization.
   *
   * @param houseID   the house ID where the room is located, must not be null
   * @param roomName  the name of the room, must not be null
   * @param dimension the dimension of the room, must not be null
   * @param roomFloor the floor where the room is located, must not be null
   * @return a fully initialized {@link Room} instance
   * @throws IllegalArgumentException if any of the parameters are null, handled by the {@link Room}
   *                                  constructor
   */
  @Override
  public Room createRoom(
      HouseID houseID, RoomName roomName, Dimension dimension, RoomFloor roomFloor)
      throws IllegalArgumentException {
    return new Room(houseID, roomName, dimension, roomFloor);
  }

  /**
   * Creates a new {@link Room} instance using the provided house ID, room name, dimension, room
   * floor, and room ID.
   *
   * @param houseID
   * @param roomName
   * @param dimension
   * @param roomFloor
   * @param roomID
   * @return
   * @throws IllegalArgumentException
   */
  @Override
  public Room createRoom(HouseID houseID, RoomName roomName, Dimension dimension,
      RoomFloor roomFloor, RoomID roomID) throws IllegalArgumentException {
    return new Room(houseID, roomName, dimension, roomFloor, roomID);
  }
}
