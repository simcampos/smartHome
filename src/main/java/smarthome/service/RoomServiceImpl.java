/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarthome.ddd.IRepository;
import smarthome.domain.house.House;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.room.IRoomFactory;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;

@Service
public class RoomServiceImpl implements IRoomService {

  private final IRepository<RoomID, Room> roomRepository;
  private final IRoomFactory roomFactory;
  private final IHouseRepository houseRepository;

  /**
   * Constructor for RoomService.
   *
   * @param roomRepository  The repository for rooms.
   * @param roomFactory     The factory for creating rooms.
   * @param houseRepository The repository for houses.
   */
  @Autowired
  public RoomServiceImpl(IRepository<RoomID, Room> roomRepository, IRoomFactory roomFactory,
      IHouseRepository houseRepository) {
    this.roomRepository = roomRepository;
    this.roomFactory = roomFactory;
    this.houseRepository = houseRepository;
  }

  /**
   * Adds a new room to the house with the provided house ID.
   *
   * @param roomName  The name of the room.
   * @param dimension The dimensions of the room.
   * @param roomFloor The floor of the room.
   * @return The room that was added.
   */
  @Override
  public Room addRoom(RoomName roomName, Dimension dimension,
      RoomFloor roomFloor) {
    Optional<House> houseOptional = houseRepository.getTheHouse();
    if (houseOptional.isEmpty()) {
      throw new IllegalArgumentException("Configure the house before adding rooms.");
    }
    HouseID houseID = houseOptional.get().getID();
    Room room = roomFactory.createRoom(houseID, roomName, dimension, roomFloor);
    roomRepository.save(room);
    return room;
  }

  /**
   * Returns all the rooms in the repository.
   *
   * @return A list of all rooms.
   */
  @Override
  public List<Room> getAllRooms() {
    return roomRepository.findAll();
  }

  /**
   * Returns the room with the given id.
   *
   * @param roomID The ID of the room to return.
   * @return The room with the given ID.
   */
  @Override
  public Optional<Room> getRoomById(RoomID roomID) throws EntityNotFoundException {
    Optional<Room> room = roomRepository.ofIdentity(roomID);
    if (room.isEmpty()) {
      throw new EntityNotFoundException("Room not found for ID: " + roomID);
    }
    return room;
  }

}
