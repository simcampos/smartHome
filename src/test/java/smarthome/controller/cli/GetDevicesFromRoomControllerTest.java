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

class GetDevicesFromRoomControllerTest {

  /**
   * Test to check if the GetDevicesFromRoomController constructor instantiates the controller
   * correctly.
   */
  @Test
  void shouldInstantiateGetDevicesFromRoomController_WhenParametersAreValid() {
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
    GetDevicesFromRoomController getDevicesFromRoomController =
        new GetDevicesFromRoomController(
            roomServiceImpl, deviceServiceImpl, roomAssembler, deviceAssembler);

    // Assert
    assertNotNull(getDevicesFromRoomController);
  }

  /**
   * Test to check if the GetDevicesFromRoomController constructor throws an exception when the room
   * service is null.
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
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new GetDevicesFromRoomController(
                    roomServiceImpl, deviceServiceImpl, roomAssembler, deviceAssembler));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if the GetDevicesFromRoomController constructor throws an exception when the
   * device service is null.
   */
  @Test
  void shouldThrowException_WhenDeviceServiceIsNull() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = new HouseRepository();

    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    IDeviceService deviceServiceImpl = null;

    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    String expectedMessage = "Device service is required";

    // Act
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new GetDevicesFromRoomController(
                    roomServiceImpl, deviceServiceImpl, roomAssembler, deviceAssembler));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if the GetDevicesFromRoomController constructor throws an exception when the
   * device assembler is null.
   */
  @Test
  void shouldThrowException_WhenRoomAssemblerIsNull() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = null;
    IHouseRepository houseRepository = new HouseRepository();

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);

    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    String expectedMessage = "Room assembler is required";

    // Act
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new GetDevicesFromRoomController(
                    roomServiceImpl, deviceServiceImpl, roomAssembler, deviceAssembler));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if the GetDevicesFromRoomController constructor throws an exception when the
   * device assembler is null.
   */
  @Test
  void shouldThrowException_WhenDeviceAssemblerIsNull() {
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

    IAssembler<Device, DeviceDTO> deviceAssembler = null;

    String expectedMessage = "Device assembler is required";

    // Act
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new GetDevicesFromRoomController(
                    roomServiceImpl, deviceServiceImpl, roomAssembler, deviceAssembler));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to get rooms from a house.
   */
  @Test
  void shouldGetRoomsFromHouse_WhenGivenValidHouseID() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = new HouseRepository();
    IHouseFactory houseFactory = new HouseFactoryImpl();

    IHouseService houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();

    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    GetDevicesFromRoomController getDevicesFromRoomController =
        new GetDevicesFromRoomController(
            roomServiceImpl, deviceServiceImpl, roomAssembler, deviceAssembler);

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
    String name2 = "Quarto da Maria";
    RoomName roomName2 = new RoomName(name2);
    String name1 = "Quarto do Joao";
    RoomName roomName1 = new RoomName(name1);

    int width = 10;
    int length = 10;
    int height = 10;
    Dimension dimension = new Dimension(width, length, height);

    int floor = 2;
    RoomFloor roomFloor = new RoomFloor(floor);

    roomServiceImpl.addRoom(roomName1, dimension, roomFloor);
    roomServiceImpl.addRoom(roomName2, dimension, roomFloor);

    List<Room> rooms = roomRepository.findAll();

    List<RoomDTO> expectedRoomsDTOList = roomAssembler.domainToDTO(rooms);
    String expectedRoomDTOName1 = expectedRoomsDTOList.get(0).roomName;
    String expectedRoomDTOID1 = expectedRoomsDTOList.get(0).roomId;
    String expectedRoomDTOName2 = expectedRoomsDTOList.get(1).roomName;
    String expectedRoomDTOID2 = expectedRoomsDTOList.get(1).roomId;

    List<String> expectedList =
        List.of(expectedRoomDTOName1, expectedRoomDTOID1, expectedRoomDTOName2, expectedRoomDTOID2);

    // Act
    List<RoomDTO> roomsDTOList = getDevicesFromRoomController.getRooms();

    String actualRoomDTOName1 = roomsDTOList.get(0).roomName;
    String actualRoomDTOID1 = roomsDTOList.get(0).roomId;
    String actualRoomDTOName2 = roomsDTOList.get(1).roomName;
    String actualRoomDTOID2 = roomsDTOList.get(1).roomId;

    List<String> actualList =
        List.of(actualRoomDTOName1, actualRoomDTOID1, actualRoomDTOName2, actualRoomDTOID2);

    // Assert
    assertEquals(expectedList, actualList);
  }

  /**
   * Test to throw an exception when the room ID does not exist in the repository.
   */
  @Test
  void shouldThrowException_WhenRoomIDDoesNotExistInRepository() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = new HouseRepository();
    IHouseFactory houseFactory = new HouseFactoryImpl();

    new HouseServiceImpl(houseFactory, houseRepository);
    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    new PostalCodeFactory();

    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    GetDevicesFromRoomController getDevicesFromRoomController =
        new GetDevicesFromRoomController(
            roomServiceImpl, deviceServiceImpl, roomAssembler, deviceAssembler);

    String roomID = "123";
    RoomID nonExistentRoomID = new RoomID(roomID);

    String roomName = "Quarto da Maria";
    String dimension = "10x10x10";
    int floor = 2;
    RoomDTO roomDTO = new RoomDTO(roomName, dimension, floor, nonExistentRoomID.toString());

    String expectedMessage = "Room not found for ID: " + nonExistentRoomID;

    // Act & Assert
    Exception exception =
        assertThrows(
            EntityNotFoundException.class,
            () -> getDevicesFromRoomController.getDevicesFromRoom(roomDTO));

    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to get devices from a room.
   */
  @Test
  void shouldGetDevicesFromRoom_WhenParametersAreValid() {
    // Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = new HouseRepository();
    IHouseFactory houseFactory = new HouseFactoryImpl();

    IHouseService houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
    IRoomService roomServiceImpl =
        new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();

    IDeviceService deviceServiceImpl =
        new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    GetDevicesFromRoomController getDevicesFromRoomController =
        new GetDevicesFromRoomController(
            roomServiceImpl, deviceServiceImpl, roomAssembler, deviceAssembler);

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
    String name2 = "Quarto da Maria";
    int width = 10;
    int length = 10;
    int height = 10;
    int floor = 2;

    RoomName roomName2 = new RoomName(name2);
    RoomFloor roomFloor = new RoomFloor(floor);
    Dimension dimension = new Dimension(width, length, height);

    Room room2 = roomServiceImpl.addRoom(roomName2, dimension, roomFloor);

    RoomID roomID = room2.getID();
    String name1 = "Lampada";
    String nameDevice = "Ar Condicionado";
    DeviceName deviceName = new DeviceName(name1);
    DeviceName deviceName2 = new DeviceName(nameDevice);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");

    deviceServiceImpl.addDevice(roomID, deviceName, deviceTypeID);
    deviceServiceImpl.addDevice(roomID, deviceName2, deviceTypeID);

    List<RoomDTO> roomsDTOList = getDevicesFromRoomController.getRooms();
    RoomDTO roomDTO = roomsDTOList.get(0);

    List<Device> devices = deviceServiceImpl.getDevicesByRoomId(roomID);
    List<DeviceDTO> deviceDTOListExpected = deviceAssembler.domainToDTO(devices);

    String expectedDeviceDTOID = deviceDTOListExpected.get(0).deviceID;
    String expectedDeviceDTOName = deviceDTOListExpected.get(0).deviceName;
    String expectedDeviceDTOID2 = deviceDTOListExpected.get(1).deviceID;
    String expectedDeviceDTOName2 = deviceDTOListExpected.get(1).deviceName;

    List<String> expectedDeviceDTOList =
        List.of(
            expectedDeviceDTOID,
            expectedDeviceDTOName,
            expectedDeviceDTOID2,
            expectedDeviceDTOName2);

    // Act
    List<DeviceDTO> devicesDTOList = getDevicesFromRoomController.getDevicesFromRoom(roomDTO);

    String actualDeviceDTOID = devicesDTOList.get(0).deviceID;
    String actualDeviceDTOName = devicesDTOList.get(0).deviceName;
    String actualDeviceDTOID2 = devicesDTOList.get(1).deviceID;
    String actualDeviceDTOName2 = devicesDTOList.get(1).deviceName;

    List<String> actualDeviceDTOList =
        List.of(actualDeviceDTOID, actualDeviceDTOName, actualDeviceDTOID2, actualDeviceDTOName2);

    // Assert
    assertEquals(expectedDeviceDTOList, actualDeviceDTOList);
  }
}
