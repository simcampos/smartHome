/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import smarthome.domain.house.House;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.repository.IRoomRepository;
import smarthome.domain.room.IRoomFactory;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.persistence.mem.HouseRepository;
import smarthome.persistence.mem.RoomRepository;

class RoomServiceImplTest {

  /**
   * Test the constructor of the RoomService class.
   */
  @Test
  void shouldInstantiateRoomService_whenGivenValidParameters() {
    // Arrange
    RoomServiceImpl roomServiceImpl;
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = mock(IRoomFactory.class);
    IHouseRepository houseRepository = mock(IHouseRepository.class);

    // Act
    roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    // Assert
    assertNotNull(roomServiceImpl);
  }

  /**
   * Test the addRoom method of the RoomService class with a valid houseID, roomName, dimension and
   * roomFloor.
   */
  @Test
  void shouldAddARoom_WhenGivenValidParameters() {
    // Arrange
    RoomServiceImpl roomServiceImpl;
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = mock(IRoomFactory.class);
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = mock(RoomFloor.class);
    House mockHouse = mock(House.class);
    Room mockRoom = mock(Room.class);
    when(houseRepository.getTheHouse()).thenReturn(Optional.of(mockHouse));
    when(mockHouse.getID()).thenReturn(mock(HouseID.class));
    when(roomFactory.createRoom(any(HouseID.class), any(RoomName.class), any(Dimension.class),
        any(RoomFloor.class))).thenReturn(mockRoom);

    // Act
    Room room = roomServiceImpl.addRoom(roomName, dimension, roomFloor);
    // Assert
    assertEquals(mockRoom, room);
  }

  /**
   * Test the addRoom method of the RoomService class with an invalid houseID.
   */
  @Test
  void shouldThrowException_whenAddingRoomWithoutHouseBeingConfigured() {
    // Arrange
    RoomServiceImpl roomServiceImpl;
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = mock(IRoomFactory.class);
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = mock(RoomFloor.class);
    when(houseRepository.getTheHouse()).thenReturn(Optional.empty());

    // Act+Assert
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> roomServiceImpl.addRoom(roomName, dimension, roomFloor));
    assertEquals("Configure the house before adding rooms.", exception.getMessage());
  }

  /**
   * Test the getRooms method of the RoomService class.
   */
  @Test
  void shouldReturnAllRooms_whenGetRoomsIsCalled() {
    // Arrange
    RoomServiceImpl roomServiceImpl;
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = mock(IRoomFactory.class);
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
    Room mockRoom = mock(Room.class);
    when(roomRepository.findAll()).thenReturn(List.of(mockRoom));

    int expected = 1;

    // Act
    List<Room> rooms = roomServiceImpl.getAllRooms();

    // Assert
    assertEquals(expected, rooms.size());
  }

  /**
   * Test the getRooms method of the RoomService class when there are no rooms.
   */
  @Test
  void shouldReturnEmptyList_whenGetRoomsIsCalledAndThereAreNoRooms() {
    // Arrange
    RoomServiceImpl roomServiceImpl;
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = mock(IRoomFactory.class);
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
    when(roomRepository.findAll()).thenReturn(List.of());

    int expected = 0;

    // Act
    List<Room> rooms = roomServiceImpl.getAllRooms();

    // Assert
    assertEquals(expected, rooms.size());
  }

  /**
   * Test the getRooms method of the RoomService class when there are multiple rooms.
   */
  @Test
  void shouldReturnAllRooms_whenGetRoomsIsCalledAndThereAreMultipleRooms() {
    // Arrange
    RoomServiceImpl roomServiceImpl;
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = mock(IRoomFactory.class);
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
    Room mockRoom1 = mock(Room.class);
    Room mockRoom2 = mock(Room.class);
    when(roomRepository.findAll()).thenReturn(List.of(mockRoom1, mockRoom2));

    int expected = 2;

    // Act
    List<Room> rooms = roomServiceImpl.getAllRooms();

    // Assert
    assertEquals(expected, rooms.size());
  }

  /**
   * Test the getRoomById method of the RoomService class with a valid roomID.
   */
  @Test
  void shouldReturnRoom_whenGetRoomByIdIsCalledWithValidRoomID() {
    // Arrange
    RoomServiceImpl roomServiceImpl;
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = mock(IRoomFactory.class);
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
    RoomID roomID = mock(RoomID.class);
    Room mockRoom = mock(Room.class);
    when(roomRepository.ofIdentity(any(RoomID.class))).thenReturn(Optional.of(mockRoom));

    // Act
    Optional<Room> room = roomServiceImpl.getRoomById(roomID);

    // Assert
    assertEquals(mockRoom, room.get());
  }
}