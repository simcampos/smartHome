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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import smarthome.ddd.IAssembler;
import smarthome.domain.device.Device;
import smarthome.domain.device.DeviceFactoryImpl;
import smarthome.domain.device.IDeviceFactory;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.device_type.DeviceTypeFactoryImpl;
import smarthome.domain.device_type.IDeviceTypeFactory;
import smarthome.domain.house.House;
import smarthome.domain.house.HouseFactoryImpl;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.IDeviceTypeRepository;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.repository.IRoomRepository;
import smarthome.domain.room.IRoomFactory;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.postal_code.IPostalCodeFactory;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.mapper.DeviceAssembler;
import smarthome.mapper.RoomAssembler;
import smarthome.persistence.mem.DeviceRepository;
import smarthome.persistence.mem.DeviceTypeRepository;
import smarthome.persistence.mem.HouseRepository;
import smarthome.persistence.mem.RoomRepository;
import smarthome.service.DeviceServiceImpl;
import smarthome.service.DeviceTypeServiceImpl;
import smarthome.service.HouseServiceImpl;
import smarthome.service.IDeviceService;
import smarthome.service.IDeviceTypeService;
import smarthome.service.IHouseService;
import smarthome.service.IRoomService;
import smarthome.service.RoomServiceImpl;
import smarthome.utils.dto.DeviceDTO;
import smarthome.utils.dto.RoomDTO;

class GetDevicesByRoomAndTemperatureFunctionalityControllerTest {

  /**
   * Test to check if the class is instantiated given valid parameters to the constructor
   */
  @Test
  void shouldInstantiateConstructor_whenParametersAreValid() {
    //Arrange
    IDeviceService deviceService = new DeviceServiceImpl(new DeviceRepository(),
        new DeviceFactoryImpl(), new RoomRepository());

    //Act
    GetDevicesByRoomAndTemperatureFunctionalityController getDevicesByRoomAndTemperatureFunctionalityController = new GetDevicesByRoomAndTemperatureFunctionalityController(
        deviceService);

    //Assert
    assertNotNull(getDevicesByRoomAndTemperatureFunctionalityController);
  }

  /**
   * Test to check if exception is thrown when the constructor receives a null parameter
   */
  @Test
  void shouldThrowException_whenDeviceServiceIsNull() {
    //Arrange
    IDeviceService deviceService = null;

    String expectedMessage = "Device service is required";

    //Act
    Exception e = assertThrows(IllegalArgumentException.class,
        () -> new GetDevicesByRoomAndTemperatureFunctionalityController(deviceService));

    //Assert
    String actualMessage = e.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test to get devices with temperature functionality from a room
   */
  @Test
  void shouldReturnDevicesWithTemperatureFunctionalityFromARoom_WhenGetDevicesByRoomAndTemperatureFunctionalityIsCalled() {
    //Arrange
    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IRoomRepository roomRepository = new RoomRepository();
    IDeviceService deviceService = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);
    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    IDeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
    IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
    IDeviceTypeService deviceTypeService = new DeviceTypeServiceImpl(deviceTypeRepository,
        deviceTypeFactory);

    IHouseRepository houseRepository = new HouseRepository();

    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IRoomService roomService = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    IDeviceTypeFactory impDeviceTypeFactory = new DeviceTypeFactoryImpl();
    IHouseFactory houseFactory = new HouseFactoryImpl();
    IHouseService houseService = new HouseServiceImpl(houseFactory, houseRepository);
    IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();

    GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(roomService,
        roomAssembler);
    GetListOfAllDevicesGroupedByFunctionalityController getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionalityController(
        deviceService, deviceAssembler, deviceTypeService);
    GetDevicesByRoomAndTemperatureFunctionalityController getDevicesByRoomAndTemperatureFunctionalityController = new GetDevicesByRoomAndTemperatureFunctionalityController(
        deviceService);

    /* Create a house */
    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress = new Address(street, doorNumber, postalCode, countryCode,
        postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseService.addHouse(newAddress, newGPS);

    /* Create a room */
    String strRoomName = "Bedroom";
    RoomName roomName = new RoomName(strRoomName);
    Dimension dimension = new Dimension(2, 2, 2);
    RoomFloor roomFloor = new RoomFloor(1);
    HouseID houseID = house.getID();

    Room room = roomService.addRoom(roomName, dimension, roomFloor);

    /* Create and save devices */
    RoomID roomID = room.getID();

    String name1 = "Light1";
    String name2 = "Light2";
    DeviceName deviceName1 = new DeviceName(name1);
    DeviceName deviceName2 = new DeviceName(name2);
    String strDeviceTypeID = "Bedroom Light";
    TypeDescription deviceTypeDescription = new TypeDescription(strDeviceTypeID);

    String strDeviceTypeID2 = "Temperature";
    TypeDescription deviceTypeDescription2 = new TypeDescription(strDeviceTypeID2);

    DeviceType deviceType = impDeviceTypeFactory.createDeviceType(deviceTypeDescription);
    DeviceType deviceType2 = impDeviceTypeFactory.createDeviceType(deviceTypeDescription2);

    deviceTypeRepository.save(deviceType);
    deviceTypeRepository.save(deviceType2);

    deviceService.addDevice(roomID, deviceName1, deviceType.getID());
    Device device1 = deviceService.addDevice(roomID, deviceName2,
        deviceType2.getID());
    Device device2 = deviceService.addDevice(roomID, deviceName2,
        deviceType2.getID());

    /* Get list of Rooms */
    List<RoomDTO> rooms = getListOfRoomsController.getRooms();

    /* Get map of devices grouped by functionality */
    Map<DeviceType, List<DeviceDTO>> map = getListOfAllDevicesGroupedByFunctionality.getDevicesDTOGroupedByFunctionality();

    /* get list from a room */
    RoomDTO roomDTO = rooms.get(0);

    DeviceDTO deviceDTO1 = deviceAssembler.domainToDTO(device1);
    DeviceDTO deviceDTO2 = deviceAssembler.domainToDTO(device2);
    List<DeviceDTO> expected = new ArrayList<>();
    expected.add(deviceDTO1);
    expected.add(deviceDTO2);

    //Act
    List<DeviceDTO> devicesTemperature = getDevicesByRoomAndTemperatureFunctionalityController.getDevicesByRoomAndTemperatureFunctionality(
        map, roomDTO);

    //Assert
    assertEquals(expected.toString(), devicesTemperature.toString());
  }

  /**
   * Test to get devices without temperature functionality from a room
   */
  @Test
  void shouldReturnEmptyDeviceListByTypeDescription_WhenGetDevicesByTypeDescriptionIsCalledWithZeroTempDevices() {
    //Arrange
    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IRoomRepository roomRepository = new RoomRepository();
    IDeviceService deviceService = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);
    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    IDeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
    IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
    IDeviceTypeService deviceTypeService = new DeviceTypeServiceImpl(deviceTypeRepository,
        deviceTypeFactory);

    IHouseRepository houseRepository = new HouseRepository();

    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IRoomService roomService = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    IDeviceTypeFactory impDeviceTypeFactory = new DeviceTypeFactoryImpl();
    IHouseFactory houseFactory = new HouseFactoryImpl();
    IHouseService houseService = new HouseServiceImpl(houseFactory, houseRepository);
    IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();

    GetListOfAllDevicesGroupedByFunctionalityController getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionalityController(
        deviceService, deviceAssembler, deviceTypeService);
    GetDevicesByRoomAndTemperatureFunctionalityController getDevicesByRoomAndTemperatureController = new GetDevicesByRoomAndTemperatureFunctionalityController(
        deviceService);

    /* Create a house */
    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress = new Address(street, doorNumber, postalCode, countryCode,
        postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseService.addHouse(newAddress, newGPS);

    /* Create a room */
    String strRoomName = "Bedroom";
    RoomName roomName = new RoomName(strRoomName);
    Dimension dimension = new Dimension(2, 2, 2);
    RoomFloor roomFloor = new RoomFloor(1);
    HouseID houseID = house.getID();

    Room room = roomService.addRoom(roomName, dimension, roomFloor);

    RoomDTO roomDTO = roomAssembler.domainToDTO(room);

    /* Create and save devices */
    RoomID roomID = room.getID();

    String name1 = "Light1";
    String name2 = "Light2";
    DeviceName deviceName1 = new DeviceName(name1);
    DeviceName deviceName2 = new DeviceName(name2);
    String strDeviceTypeID = "Bedroom Light";
    TypeDescription deviceTypeDescription = new TypeDescription(strDeviceTypeID);

    String strDeviceTypeID2 = "Humidty";
    TypeDescription deviceTypeDescription2 = new TypeDescription(strDeviceTypeID2);

    DeviceType deviceType = impDeviceTypeFactory.createDeviceType(deviceTypeDescription);
    DeviceType deviceType2 = impDeviceTypeFactory.createDeviceType(deviceTypeDescription2);

    deviceTypeRepository.save(deviceType);
    deviceTypeRepository.save(deviceType2);

    deviceService.addDevice(roomID, deviceName1, deviceType.getID());
    deviceService.addDevice(roomID, deviceName2, deviceType2.getID());


    /* Get map of devices grouped by functionality */
    Map<DeviceType, List<DeviceDTO>> map = getListOfAllDevicesGroupedByFunctionality.getDevicesDTOGroupedByFunctionality();

    int expectedListSize = 0;

    //Act
    List<DeviceDTO> devicesTemperature = getDevicesByRoomAndTemperatureController.getDevicesByRoomAndTemperatureFunctionality(
        map, roomDTO);

    //
    assertEquals(expectedListSize, devicesTemperature.size());

  }

  /**
   * Test to get devices by room and temperature when the provided map is null
   */
  @Test
  void shouldThrowException_whenMapIsNull() {
    //Arrange
    IDeviceService deviceService = new DeviceServiceImpl(new DeviceRepository(),
        new DeviceFactoryImpl(), new RoomRepository());

    GetDevicesByRoomAndTemperatureFunctionalityController getDevicesByRoomAndTemperatureFunctionalityController = new GetDevicesByRoomAndTemperatureFunctionalityController(
        deviceService);

    RoomDTO roomDTO = mock(RoomDTO.class);

    String expectedMessage = "A Map is required";

    //Act
    Exception e = assertThrows(IllegalArgumentException.class,
        () -> getDevicesByRoomAndTemperatureFunctionalityController.getDevicesByRoomAndTemperatureFunctionality(
            null, roomDTO));

    //Assert
    String actualMessage = e.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test to get devices by room and temperature when the provided roomDTO is null
   */
  @Test
  void shouldThrowException_whenRoomDTOIsNull() {
    //Arrange
    IDeviceService deviceService = new DeviceServiceImpl(new DeviceRepository(),
        new DeviceFactoryImpl(), new RoomRepository());

    GetDevicesByRoomAndTemperatureFunctionalityController getDevicesByRoomAndTemperatureFunctionalityController = new GetDevicesByRoomAndTemperatureFunctionalityController(
        deviceService);

    Map<DeviceType, List<DeviceDTO>> map = mock(Map.class);

    String expectedMessage = "Room DTO is required";

    //Act
    Exception e = assertThrows(IllegalArgumentException.class,
        () -> getDevicesByRoomAndTemperatureFunctionalityController.getDevicesByRoomAndTemperatureFunctionality(
            map, null));

    //Assert
    String actualMessage = e.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }


}
