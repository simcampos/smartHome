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

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.ddd.IAggregateRoot;
import smarthome.ddd.IAssembler;
import smarthome.domain.device.Device;
import smarthome.domain.device.DeviceFactoryImpl;
import smarthome.domain.device.IDeviceFactory;
import smarthome.domain.house.House;
import smarthome.domain.house.HouseFactoryImpl;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.repository.IRoomRepository;
import smarthome.domain.room.IRoomFactory;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.domain.value_object.postal_code.IPostalCodeFactory;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.mapper.DeviceAssembler;
import smarthome.mapper.RoomAssembler;
import smarthome.persistence.mem.DeviceRepository;
import smarthome.persistence.mem.HouseRepository;
import smarthome.persistence.mem.RoomRepository;
import smarthome.service.DeviceServiceImpl;
import smarthome.service.HouseServiceImpl;
import smarthome.service.IDeviceService;
import smarthome.service.IHouseService;
import smarthome.service.IRoomService;
import smarthome.service.RoomServiceImpl;
import smarthome.utils.dto.DeviceDTO;
import smarthome.utils.dto.RoomDTO;
import smarthome.utils.entry_dto.DeviceEntryDTO;

class AddDeviceToRoomControllerTest {

  /**
   * Test to verify if the AddDeviceToRoomController is being instantiated correctly.
   */
  @Test
  void shouldInstantiateAddDeviceToRoomController_WhenParametersAreValid() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = new HouseRepository();

    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();

    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    // Act
    AddDeviceToRoomController addDeviceToRoomController =
        new AddDeviceToRoomController(
            roomServiceImpl, roomAssembler, deviceServiceImpl, deviceAssembler);

    // Assert
    assertNotNull(addDeviceToRoomController);
  }

  /**
   * Test to verify if an exception is thrown when the RoomService is null.
   */
  @Test
  void shouldThrowException_WhenRoomServiceIsNull() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IRoomService roomServiceImpl = null;

    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();

    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    String expectedMessage = "Room service is required";

    // Act
    Throwable exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddDeviceToRoomController(
                    roomServiceImpl, roomAssembler, deviceServiceImpl, deviceAssembler));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to verify if an exception is thrown when the RoomAssembler is null.
   */
  @Test
  void shouldThrowException_WhenRoomAssemblerIsNull() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, new RoomFactoryImpl(), new HouseRepository());
    IAssembler<Room, RoomDTO> roomAssembler = null;

    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();

    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    String expectedMessage = "Room assembler is required";

    // Act
    Throwable exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddDeviceToRoomController(
                    roomServiceImpl, roomAssembler, deviceServiceImpl, deviceAssembler));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to verify if an exception is thrown when the DeviceService is null.
   */
  @Test
  void shouldThrowException_WhenDeviceServiceIsNull() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, new RoomFactoryImpl(), new HouseRepository());
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IDeviceService deviceServiceImpl = null;

    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    String expectedMessage = "Device service is required";

    // Act
    Throwable exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddDeviceToRoomController(
                    roomServiceImpl, roomAssembler, deviceServiceImpl, deviceAssembler));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to verify if an exception is thrown when the DeviceAssembler is null.
   */
  @Test
  void shouldThrowException_WhenDeviceAssemblerIsNull() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, new RoomFactoryImpl(), new HouseRepository());
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();

    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();

    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
    IAssembler<Device, DeviceDTO> deviceAssembler = null;

    String expectedMessage = "Device assembler is required";

    // Act
    Throwable exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddDeviceToRoomController(
                    roomServiceImpl, roomAssembler, deviceServiceImpl, deviceAssembler));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Tests retrieving a list of RoomDTOs, checking if the returned data matches the expected.
   */
  @Test
  void shouldReturnListOfRoomDTOs_WhenGetAllRoomsIsCalled() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = new HouseRepository();

    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();

    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    AddDeviceToRoomController addDeviceToRoomController =
        new AddDeviceToRoomController(
            roomServiceImpl, roomAssembler, deviceServiceImpl, deviceAssembler);

    IHouseFactory houseFactory = new HouseFactoryImpl();
    IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    IHouseService houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress =
        new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseServiceImpl.addHouse(newAddress, newGPS);

    HouseID houseID = house.getID();

    RoomName roomName = new RoomName("Living Room");

    int width = 10;
    int length = 10;
    int height = 10;

    Dimension dimension = new Dimension(width, length, height);

    int floor = 1;
    RoomFloor roomFloor = new RoomFloor(floor);

    roomServiceImpl.addRoom(roomName, dimension, roomFloor);

    List<Room> rooms = roomRepository.findAll();

    List<RoomDTO> expectedRoomDTOs = roomAssembler.domainToDTO(rooms);
    String expectedRoomName = expectedRoomDTOs.get(0).roomName;
    String expectedRoomID = expectedRoomDTOs.get(0).roomId;

    List<String> expectedList = List.of(expectedRoomName, expectedRoomID);

    // Act
    List<RoomDTO> roomDTOs = addDeviceToRoomController.getAllRooms();

    // Assert
    String actualRoomName = roomDTOs.get(0).roomName;
    String actualRoomID = roomDTOs.get(0).roomId;
    List<String> actualList = List.of(actualRoomName, actualRoomID);

    assertEquals(expectedList, actualList);
  }

  /**
   * Confirms that a DeviceDTO is returned correctly when a device is added to a room.
   */
  @Test
  void shouldReturnDeviceDTO_WhenAddDeviceToRoomIsCalled() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = new HouseRepository();

    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    IHouseFactory houseFactory = new HouseFactoryImpl();
    IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    IHouseService houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

    // Room
    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress =
        new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    IAggregateRoot<HouseID> house = houseServiceImpl.addHouse(newAddress, newGPS);

    HouseID houseID = house.getID();

    RoomName roomName = new RoomName("Living Room");

    int width = 10;
    int length = 10;
    int height = 10;

    Dimension dimension = new Dimension(width, length, height);

    int floor = 1;
    RoomFloor roomFloor = new RoomFloor(floor);

    roomServiceImpl.addRoom(roomName, dimension, roomFloor);

    List<Room> rooms = roomRepository.findAll();

    RoomDTO roomDTO = roomAssembler.domainToDTO(rooms).get(0);

    // VOs
    String roomID = roomDTO.roomId;
    String deviceName = "Lamp";
    boolean deviceStatus = true;
    String deviceTypeID = "1";

    RoomID roomIdVO = new RoomID(roomID);
    DeviceName deviceNameVO = new DeviceName(deviceName);
    DeviceTypeID deviceTypeIDVO = new DeviceTypeID(deviceTypeID);

    // Device
    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

    Device device =
        deviceServiceImpl.addDevice(roomIdVO, deviceNameVO, deviceTypeIDVO);

    // DeviceDTO
    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();
    AddDeviceToRoomController addDeviceToRoomController =
        new AddDeviceToRoomController(
            roomServiceImpl, roomAssembler, deviceServiceImpl, deviceAssembler);

    DeviceDTO expectedDeviceDTO = deviceAssembler.domainToDTO(device);

    DeviceEntryDTO deviceDataDTO = new DeviceEntryDTO(deviceTypeID, deviceName, roomID);

    // Act
    DeviceDTO deviceDTO = addDeviceToRoomController.addDeviceToRoom(deviceDataDTO);

    // Assert
    assertEquals(expectedDeviceDTO.deviceName, deviceDTO.deviceName);
  }

  /**
   * Ensures that an exception is thrown when the specified room does not exist.
   */
  @Test
  void shouldThrowException_WhenRoomDoesNotExist() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = new HouseRepository();

    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();

    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    AddDeviceToRoomController addDeviceToRoomController =
        new AddDeviceToRoomController(
            roomServiceImpl, roomAssembler, deviceServiceImpl, deviceAssembler);

    DeviceEntryDTO deviceDataDTO = new DeviceEntryDTO("1", "Lamp", "1");

    // Act + Assert
    assertThrows(
        EntityNotFoundException.class,
        () -> {
          addDeviceToRoomController.addDeviceToRoom(deviceDataDTO);
        });
  }
}
