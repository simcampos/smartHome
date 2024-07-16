/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.data_model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import smarthome.domain.room.Room;
import smarthome.utils.Validator;

@Entity
@Table(name = "Room")
public class RoomDataModel {

  @Id
  private String roomID;
  private String houseID;
  private String roomName;
  private int width;
  private int depth;
  private int height;
  private int floor;
  @Version
  private long version;

  /**
   * Empty class constructor
   */
  public RoomDataModel() {
  }

  /**
   * Class constructor with parameter room
   *
   * @param room
   */
  public RoomDataModel(Room room) {
    Validator.validateNotNull(room, "Room");
    this.roomID = room.getID().getID();
    this.houseID = room.getHouseID().getID();
    this.roomName = room.getName().getRoomName();
    this.width = room.getDimension().getWidth();
    this.depth = room.getDimension().getDepth();
    this.height = room.getDimension().getHeight();
    this.floor = room.getFloor().getFloor();
  }

  /**
   * Method to return the room ID.
   *
   * @return
   */
  public String getRoomID() {
    return this.roomID;
  }

  /**
   * Method to return the house ID.
   *
   * @return
   */
  public String getHouseID() {
    return this.houseID;
  }

  /**
   * Method to return the room name.
   *
   * @return
   */
  public String getRoomName() {
    return this.roomName;
  }

  /**
   * Method to return the room width.
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Method to return the room depth.
   *
   * @return
   */
  public int getDepth() {
    return this.depth;
  }

  /**
   * Method to return the room height.
   *
   * @return
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Method to return the room floor.
   *
   * @return
   */
  public int getFloor() {
    return this.floor;
  }

  /**
   * Method to update the room data model from the domain.
   *
   * @param room is the room to be updated.
   * @return true if the room was updated, false if not.
   */
  public boolean updateFromDomain(Room room) {
    this.roomID = room.getID().getID();
    this.houseID = room.getHouseID().getID();
    this.roomName = room.getName().getRoomName();
    this.width = room.getDimension().getWidth();
    this.depth = room.getDimension().getDepth();
    this.height = room.getDimension().getHeight();
    this.floor = room.getFloor().getFloor();

    return true;
  }
}
