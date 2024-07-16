/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.room;

import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;

/**
 * Interface defining a factory for creating {@link Room} instances. Provides a method to create a
 * room with specific house ID, room name, dimension, and room floor.
 */

public interface IRoomFactory {

  /**
   * Creates and returns a new {@link Room} instance with the provided house ID, room name,
   * dimension, and room floor.
   *
   * @param houseID   the house ID where the room is located
   * @param roomName  the name of the room
   * @param dimension the dimension of the room
   * @param roomFloor the floor where the room is located
   * @return a newly created Room instance
   */
  Room createRoom(HouseID houseID, RoomName roomName, Dimension dimension, RoomFloor roomFloor);

  /**
   * Creates and returns a new {@link Room} instance with the provided house ID, room name,
   * dimension, room floor, and room ID.
   *
   * @param houseID
   * @param roomName
   * @param dimension
   * @param roomFloor
   * @param roomID
   * @return
   */
  Room createRoom(HouseID houseID, RoomName roomName, Dimension dimension, RoomFloor roomFloor,
      RoomID roomID);

}
