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
import smarthome.domain.value_object.DeviceTypeID;
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

class GetListOfAllDevicesGroupedByFunctionalityControllerTest {

  /**
   * Test to ensure that GetListOfAllDevicesGroupedByFunctionality can be instantiated
   * successfully.
   */
  @Test
  void shouldInstantiateGetListOfAllDevicesGroupedByFunctionality_whenConstructorArgumentsAreValid() {
    //Arrange
    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IRoomRepository roomRepository = new RoomRepository();
    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    IDeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
    IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
    IDeviceTypeService deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository,
        deviceTypeFactory);

    //Act
    GetListOfAllDevicesGroupedByFunctionalityController getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionalityController(
        deviceServiceImpl, deviceAssembler, deviceTypeServiceImpl);

    //Assert
    assertNotNull(getListOfAllDevicesGroupedByFunctionality);
  }

  /**
   * Test to ensure that GetListOfAllDevicesGroupedByFunctionality throws an
   * IllegalArgumentException when the DeviceService is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenGetListOfAllDevicesGroupedByFunctionalityIsCalledWithNullDeviceService() {
    //Arrange
    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();
    IDeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
    IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
    IDeviceTypeService deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository,
        deviceTypeFactory);

    String expectedMessage = "Device service is required";

    //Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new GetListOfAllDevicesGroupedByFunctionalityController(null, deviceAssembler,
            deviceTypeServiceImpl));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test to ensure that GetListOfAllDevicesGroupedByFunctionality throws an
   * IllegalArgumentException when the DeviceAssembler is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenGetListOfAllDevicesGroupedByFunctionalityIsCalledWithNullDeviceAssembler() {
    //Arrange
    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IRoomRepository roomRepository = new RoomRepository();
    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);
    IDeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();

    IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
    IDeviceTypeService deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository,
        deviceTypeFactory);

    String expectedMessage = "Device assembler is required";

    //Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new GetListOfAllDevicesGroupedByFunctionalityController(deviceServiceImpl, null,
            deviceTypeServiceImpl));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test to ensure that GetListOfAllDevicesGroupedByFunctionality throws an
   * IllegalArgumentException when the DeviceTypeRepository is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenGetListOfAllDevicesGroupedByFunctionalityIsCalledWithNullDeviceTypeRepository() {
    //Arrange
    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IRoomRepository roomRepository = new RoomRepository();
    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);
    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    String expectedMessage = "Device type service is required";

    //Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new GetListOfAllDevicesGroupedByFunctionalityController(deviceServiceImpl,
            deviceAssembler, null));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  void shouldThrowIllegalArgumentException_WhenGetDevicesDTOGroupedByFunctionalityIsCalledWithEmptyDevicesList() {
    //Arrange
    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IRoomRepository roomRepository = new RoomRepository();
    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    IDeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();

    IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
    IDeviceTypeService deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository,
        deviceTypeFactory);

    GetListOfAllDevicesGroupedByFunctionalityController getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionalityController(
        deviceServiceImpl, deviceAssembler, deviceTypeServiceImpl);

    //Act & Assert
    assertThrows(IllegalArgumentException.class,
        getListOfAllDevicesGroupedByFunctionality::getDevicesDTOGroupedByFunctionality,
        "No devices found.");
  }

  /**
   * Test to ensure that GetDevicesDTOGroupedByFunctionality returns a map of devices grouped by
   * functionality, when devices have different type.
   */
  @Test
  void shouldReturnMapOfDevicesGroupedByFunctionality_WhenGetDevicesDTOGroupedByFunctionalityIsCalledWithValidDevicesListAndDevicesHaveDifferentType() {
    //Arrange
    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IRoomRepository roomRepository = new RoomRepository();
    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    IDeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
    IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
    IDeviceTypeService deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository,
        deviceTypeFactory);

    IDeviceTypeFactory impDeviceTypeFactory = new DeviceTypeFactoryImpl();
    IHouseFactory houseFactory = new HouseFactoryImpl();
    IHouseRepository houseRepository = new HouseRepository();
    IHouseService houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
    IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    GetListOfAllDevicesGroupedByFunctionalityController getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionalityController(
        deviceServiceImpl, deviceAssembler, deviceTypeServiceImpl);

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

    House house = houseServiceImpl.addHouse(newAddress, newGPS);

    /* Create a room */
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);

    String strRoomName = "Bedroom";
    RoomName roomName = new RoomName(strRoomName);
    Dimension dimension = new Dimension(2, 2, 2);
    RoomFloor roomFloor = new RoomFloor(1);
    HouseID houseID = house.getID();

    Room room = roomServiceImpl.addRoom(roomName, dimension, roomFloor);

    /* Create and save devices */
    RoomID roomID = room.getID();

    String name = "Light";
    DeviceName deviceName = new DeviceName(name);
    String strDeviceTypeID1 = "Bedroom Light";
    String strDeviceTypeID2 = "Kitchen Light";
    TypeDescription deviceTypeDescription1 = new TypeDescription(strDeviceTypeID1);
    TypeDescription deviceTypeDescription2 = new TypeDescription(strDeviceTypeID2);

    DeviceType deviceType1 = impDeviceTypeFactory.createDeviceType(deviceTypeDescription1);
    DeviceType deviceType2 = impDeviceTypeFactory.createDeviceType(deviceTypeDescription2);

    deviceTypeRepository.save(deviceType1);
    deviceTypeRepository.save(deviceType2);

    Device device1 = deviceServiceImpl.addDevice(roomID, deviceName,
        deviceType1.getID());
    Device device2 = deviceServiceImpl.addDevice(roomID, deviceName,
        deviceType2.getID());

    //Act
    Map<DeviceType, List<DeviceDTO>> result = getListOfAllDevicesGroupedByFunctionality.getDevicesDTOGroupedByFunctionality();

    //Assert
    assertEquals(result.get(deviceType1).get(0).deviceID, device1.getID().getID());
    assertEquals(result.get(deviceType2).get(0).deviceID, device2.getID().getID());
  }

  /**
   * Test to ensure that GetDevicesDTOGroupedByFunctionality returns a map of devices grouped by
   * functionality, when devices have the same type.
   */
  @Test
  void shouldReturnMapOfDevicesGroupedByFunctionality_WhenGetDevicesDTOGroupedByFunctionalityIsCalledWithValidDevicesListAndDevicesHaveSameType() {
    //Arrange
    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IRoomRepository roomRepository = new RoomRepository();
    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    IDeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
    IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
    IDeviceTypeService deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository,
        deviceTypeFactory);

    IDeviceTypeFactory impDeviceTypeFactory = new DeviceTypeFactoryImpl();
    IHouseFactory houseFactory = new HouseFactoryImpl();
    IHouseRepository houseRepository = new HouseRepository();
    IHouseService houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
    IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    GetListOfAllDevicesGroupedByFunctionalityController getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionalityController(
        deviceServiceImpl, deviceAssembler, deviceTypeServiceImpl);

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

    House house = houseServiceImpl.addHouse(newAddress, newGPS);

    /* Create a room */
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);

    String strRoomName = "Bedroom";
    RoomName roomName = new RoomName(strRoomName);
    Dimension dimension = new Dimension(2, 2, 2);
    RoomFloor roomFloor = new RoomFloor(1);
    HouseID houseID = house.getID();

    Room room = roomServiceImpl.addRoom(roomName, dimension, roomFloor);

    /* Create and save devices */
    RoomID roomID = room.getID();

    String name1 = "Light1";
    String name2 = "Light2";
    DeviceName deviceName1 = new DeviceName(name1);
    DeviceName deviceName2 = new DeviceName(name2);
    String strDeviceTypeID = "Bedroom Light";
    TypeDescription deviceTypeDescription = new TypeDescription(strDeviceTypeID);

    DeviceType deviceType = impDeviceTypeFactory.createDeviceType(deviceTypeDescription);

    deviceTypeRepository.save(deviceType);

    Device device1 = deviceServiceImpl.addDevice(roomID, deviceName1,
        deviceType.getID());
    Device device2 = deviceServiceImpl.addDevice(roomID, deviceName2,
        deviceType.getID());

    //Act
    Map<DeviceType, List<DeviceDTO>> result = getListOfAllDevicesGroupedByFunctionality.getDevicesDTOGroupedByFunctionality();

    //Assert
    assertEquals(result.get(deviceType).get(0).deviceID, device1.getID().getID());
    assertEquals(result.get(deviceType).get(1).deviceID, device2.getID().getID());

  }

  /**
   * Test to verify if an exception is thrown if the device type is not found.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenGetDevicesDTOGroupedByFunctionalityIsCalledWithInvalidDeviceType() {
    //Arrange
    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IRoomRepository roomRepository = new RoomRepository();
    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);
    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    IDeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
    IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
    IDeviceTypeService deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository,
        deviceTypeFactory);

    IDeviceTypeFactory impDeviceTypeFactory = new DeviceTypeFactoryImpl();
    IHouseFactory houseFactory = new HouseFactoryImpl();
    IHouseRepository houseRepository = new HouseRepository();
    IHouseService houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
    IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    GetListOfAllDevicesGroupedByFunctionalityController getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionalityController(
        deviceServiceImpl, deviceAssembler, deviceTypeServiceImpl);

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

    House house = houseServiceImpl.addHouse(newAddress, newGPS);

    /* Create a room */
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);

    String strRoomName = "Bedroom";
    RoomName roomName = new RoomName(strRoomName);
    Dimension dimension = new Dimension(2, 2, 2);
    RoomFloor roomFloor = new RoomFloor(1);
    HouseID houseID = house.getID();

    Room room = roomServiceImpl.addRoom(roomName, dimension, roomFloor);

    /* Create and save devices */
    RoomID roomID = room.getID();

    String name = "Light";
    DeviceName deviceName = new DeviceName(name);

    String strDeviceTypeID = "Wrong Type Description";
    TypeDescription deviceTypeDescription = new TypeDescription(strDeviceTypeID);

    DeviceType deviceType = mock(DeviceType.class);
    when(deviceType.getID()).thenReturn(new DeviceTypeID("Wrong Type ID"));
    when(deviceType.getDescription()).thenReturn(deviceTypeDescription);

    Device device = deviceServiceImpl.addDevice(roomID, deviceName,
        deviceType.getID());

    String expectedMessage = "DeviceType not found.";

    // Act & Assert
    Exception e = assertThrows(IllegalArgumentException.class,
        getListOfAllDevicesGroupedByFunctionality::getDevicesDTOGroupedByFunctionality);

    // Assert
    String actualMessage = e.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

}
