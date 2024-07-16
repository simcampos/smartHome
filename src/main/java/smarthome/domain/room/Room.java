/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.room;

import java.util.UUID;
import smarthome.ddd.IAggregateRoot;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.utils.Validator;


public class Room implements IAggregateRoot<RoomID> {

  private final HouseID houseID;
  private final RoomFloor floor;
  private final RoomName name;
  private final Dimension dimension;
  private RoomID id;

  /**
   * Constructs a new Room instance with the specified house ID, room name, dimension, and room
   * floor.
   *
   * @param houseID   The house ID where the room is located. Must not be null.
   * @param name  The name of the room. Must not be null.
   * @param dimension The dimension of the room. Must not be null.
   * @param floor The floor where the room is located. Must not be null.
   */
  Room(HouseID houseID, RoomName name, Dimension dimension, RoomFloor floor) {
    Validator.validateNotNull(houseID, "HouseID");
    Validator.validateNotNull(name, "RoomName");
    Validator.validateNotNull(dimension, "Dimension");
    Validator.validateNotNull(floor, "RoomFloor");
    generateRoomID();
    this.houseID = houseID;
    this.floor = floor;
    this.name = name;
    this.dimension = dimension;
  }

  /**
   * Constructs a new Room instance with the specified house ID, room name, dimension, room floor,
   * and room ID.
   *
   * @param houseID   The house ID where the room is located.
   * @param name  The name of the room.
   * @param dimension The dimension of the room.
   * @param floor The floor where the room is located.
   * @param roomID    The room ID.
   */
  Room(HouseID houseID, RoomName name, Dimension dimension, RoomFloor floor,
      RoomID roomID) {
    Validator.validateNotNull(houseID, "HouseID");
    Validator.validateNotNull(name, "RoomName");
    Validator.validateNotNull(dimension, "Dimension");
    Validator.validateNotNull(floor, "RoomFloor");
    Validator.validateNotNull(roomID, "RoomID");
    id = roomID;
    this.houseID = houseID;
    this.floor = floor;
    this.name = name;
    this.dimension = dimension;
  }

  /**
   * Generates a new RoomID object.
   */
  private void generateRoomID() {
    id = new RoomID(UUID.randomUUID().toString());
  }


  /**
   * Method to return the room ID.
   *
   * @return the room ID.
   */
  @Override
  public RoomID getID() {
    return id;
  }

  /**
   * Method to return the house ID.
   *
   * @return the house ID.
   */
  public HouseID getHouseID() {
    return houseID;
  }

  /**
   * Method to return the room name.
   *
   * @return the room name.
   */
  public RoomName getName() {
    return name;
  }

  /**
   * Method to return the room dimension.
   *
   * @return the room dimension.
   */
  public Dimension getDimension() {
    return dimension;
  }

  /**
   * Method to return the room floor.
   *
   * @return the room floor.
   */
  public RoomFloor getFloor() {
    return floor;
  }

  /**
   * Checks if this Room instance is equal to another object.
   *
   * @param object The object to compare.
   * @return true if the objects are equal, false if they are different.
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof Room room) {
      return id.equals(room.id);
    }
    return false;
  }

  /**
   * Method to return the values of the object in a string.
   *
   * @return the values of the object in a string.
   */
  @Override
  public String toString() {
    return "Room:" + "houseID= " + houseID + ", roomID= " + id + ", roomName= " + name
        + ", dimension= " + dimension + ", roomFloor= " + floor;
  }

  /**
   * Method to return the hash code of the object.
   *
   * @return the hash code of the object.
   */
  public int hashCode() {
    return id.hashCode();
  }
}
