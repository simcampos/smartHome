/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import smarthome.ddd.IAssembler;
import smarthome.domain.house.House;
import smarthome.domain.house.HouseFactoryImpl;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.repository.IRoomRepository;
import smarthome.domain.room.IRoomFactory;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomName;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.mapper.RoomAssembler;
import smarthome.persistence.mem.HouseRepository;
import smarthome.persistence.mem.RoomRepository;
import smarthome.service.HouseServiceImpl;
import smarthome.service.IHouseService;
import smarthome.service.IRoomService;
import smarthome.service.RoomServiceImpl;
import smarthome.utils.dto.RoomDTO;

/**
 * Test class for the AddRoomToHouseController class.
 */
class AddRoomToHouseControllerTest {

  /**
   * Tests the instantiation of AddRoomToHouseController with valid parameters.
   */
  @Test
  void shouldInstantiateAddRoomToHouseController_WhenParametersAreValid() {
    // Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = mock(IHouseRepository.class);

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);

    //Act
    AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(
        roomServiceImpl, roomAssembler);

    // Assert
    assertNotNull(addRoomToHouseController);

  }

  /**
   * Tests throwing an exception when house is not found.
   */
  @Test
  void shouldThrowException_WhenHouseIsNotFound() {
    // Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = mock(IHouseRepository.class);

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);
    AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(
        roomServiceImpl, roomAssembler);

    // Act
    assertThrows(IllegalArgumentException.class, () -> {
      addRoomToHouseController.addRoom("Living Room", 1, 10, 10, 10);
    });

  }

  /**
   * Tests returning a RoomDTO when a room is successfully added.
   */
  @Test
  void shouldReturnRoomDTO_WhenRoomIsAdded() {
    // Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = mock(IHouseRepository.class);

    IHouseFactory houseFactory = new HouseFactoryImpl();
    IHouseService houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

    String street = "Rua Isep";
    String doorNumber = "122A";
    String postalCode = "4000-009";
    String countryCode = "PT";

    Address newAddress = new Address(street, doorNumber, postalCode, countryCode,
        new PostalCodeFactory());

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseServiceImpl.addHouse(newAddress, newGPS);
    when(houseRepository.getTheHouse()).thenReturn(Optional.of(house));

    HouseID houseID = house.getID();

    String name = "Living Room";
    int floor = 1;
    int width = 10;
    int length = 10;
    int height = 10;

    RoomName roomName = new RoomName(name);
    RoomFloor roomFloor = new RoomFloor(floor);
    Dimension dimension = new Dimension(width, length, height);

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);
    AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(
        roomServiceImpl, roomAssembler);

    Room room = roomServiceImpl.addRoom(roomName, dimension, roomFloor);
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));

    RoomDTO expectedRoomDTO = roomAssembler.domainToDTO(room);

    // Act
    RoomDTO roomDTO = addRoomToHouseController.addRoom(name, floor, width,
        length, height);

    // Assert
    assertEquals(expectedRoomDTO.roomName, roomDTO.roomName);
  }

  /**
   * Tests throwing an exception when room name is not provided.
   */
  @Test
  void shouldThrowException_WhenRoomNameIsNotProvided() {
    // Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = mock(IHouseRepository.class);

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);
    AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(
        roomServiceImpl, roomAssembler);

    // Act
    assertThrows(IllegalArgumentException.class, () -> {
      addRoomToHouseController.addRoom(null, 1, 10, 10, 10);
    });

  }

  /**
   * Tests throwing an exception when room floor is not valid.
   */
  @Test
  void shouldThrowException_WhenRoomFloorIsNotValid() {
    // Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = mock(IHouseRepository.class);

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);
    AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(
        roomServiceImpl, roomAssembler);

    // Act
    assertThrows(IllegalArgumentException.class, () -> {
      addRoomToHouseController.addRoom("Living Room", -1000, 10, 10, 10);
    });
  }

  /**
   * Tests throwing an exception when room width is not valid.
   */
  @Test
  void shouldThrowException_WhenRoomWidthIsNotValid() {
    // Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = mock(IHouseRepository.class);

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);
    AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(
        roomServiceImpl, roomAssembler);

    // Act
    assertThrows(IllegalArgumentException.class, () -> {
      addRoomToHouseController.addRoom("Living Room", 1, -1000, 10, 10);
    });
  }

  /**
   * Tests throwing an exception when room length is not valid.
   */
  @Test
  void shouldThrowException_WhenRoomLengthIsNotValid() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = new HouseRepository();

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);
    AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(
        roomServiceImpl, roomAssembler);

    // Act
    assertThrows(IllegalArgumentException.class, () -> {
      addRoomToHouseController.addRoom("Living Room", 1, 10, -1000, 10);
    });
  }

  /**
   * Tests throwing an exception when room height is not valid.
   */
  @Test
  void shouldThrowException_WhenRoomHeightIsNotValid() {
    // Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = mock(IHouseRepository.class);

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);
    AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(
        roomServiceImpl, roomAssembler);

    // Act
    assertThrows(IllegalArgumentException.class, () -> {
      addRoomToHouseController.addRoom("Living Room", 1, 10, 10, -1000);
    });
  }

  /**
   * Tests throwing an exception when room service is null.
   */
  @Test
  void shouldThrowException_WhenRoomServiceIsNull() {
    //Arrange
    IRoomService roomService = null;
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();

    String expectedMessage = "Room service is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new AddRoomToHouseController(roomService, roomAssembler);
    });

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Tests throwing an exception when room assembler is null.
   */
  @Test
  void shouldThrowException_WhenRoomAssemblerIsNull() {
    //Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IRoomService roomService = new RoomServiceImpl(roomRepository, roomFactory,
        mock(IHouseRepository.class));
    IAssembler<Room, RoomDTO> roomAssembler = null;

    String expectedMessage = "Room assembler is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new AddRoomToHouseController(roomService, roomAssembler);
    });

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }
}