/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
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
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomName;
import smarthome.domain.value_object.postal_code.IPostalCodeFactory;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.mapper.DeviceAssembler;
import smarthome.service.DeviceServiceImpl;
import smarthome.service.IDeviceService;
import smarthome.service.IRoomService;
import smarthome.service.RoomServiceImpl;
import smarthome.utils.dto.DeviceDTO;

class DeactivateDeviceControllerTest {

  /**
   * Test to verify that the US08DeactivateDevice constructor returns a non-null object.
   */
  @Test
  void shouldReturnNotNull_WhenUS08DeactivateDeviceIsConstructed() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);
    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    // Act
    DeactivateDeviceController result = new DeactivateDeviceController(deviceServiceImpl,
        deviceAssembler);

    // Assert
    assertNotEquals(null, result);
  }

  /**
   * Test to verify that the US08DeactivateDevice constructor throws an exception when the
   * DeviceService is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenUS08DeactivateDeviceIsConstructedAndDeviceServiceIsNull() {
    // Arrange
    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();
    IDeviceService deviceService = null;

    String expectedMessage = "Device service is required";

    // Act
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class, () -> {
          new DeactivateDeviceController(deviceService, deviceAssembler);
        });

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to verify that the US08DeactivateDevice constructor throws an exception when the
   * DeviceAssembler is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenUS08DeactivateDeviceIsConstructedAndDeviceAssemblerIsNull() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    IAssembler<Device, DeviceDTO> deviceAssembler = null;

    String expectedMessage = "Device assembler is required";

    // Act
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class, () -> {
          new DeactivateDeviceController(deviceServiceImpl, deviceAssembler);
        });

    // Assert
    assertEquals(expectedMessage, exception.getMessage());

  }

  /**
   * Test to verify that the requestAllDevices method returns all devices.
   */
  @Test
  void shouldReturnAllDevices_WhenRequestAllDevicesIsCalled() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);
    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);
    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();
    IHouseFactory houseFactory = new HouseFactoryImpl();
    IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(
        deviceServiceImpl, deviceAssembler);

    // Add a house
    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";
    Address address = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
    GPS gps = new GPS(41.5514, -8.4221);
    House house = houseFactory.createHouse(address, gps);
    when(houseRepository.getTheHouse()).thenReturn(Optional.of(house));
    houseRepository.save(house);

    // Add a room
    HouseID houseID = house.getID();
    RoomName roomName = new RoomName("Living Room");
    Dimension dimension = new Dimension(10, 10, 10);
    RoomFloor roomFloor = new RoomFloor(1);
    Room room = roomServiceImpl.addRoom(roomName, dimension, roomFloor);
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));

    // Add a device
    DeviceName deviceName = new DeviceName("Light bulb");
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");
    Device device = deviceServiceImpl.addDevice(room.getID(), deviceName,
        deviceTypeID);
    when(deviceRepository.findAll()).thenReturn(List.of(device));

    // Act
    List<DeviceDTO> devices = deactivateDeviceController.requestAllDevices();

    // Assert
    assertEquals(devices.get(0).deviceID, device.getID().toString());
  }

  /**
   * Test to verify that the requestAllDevices method returns no devices.
   */
  @Test
  void shouldReturnNoDevices_WhenRequestAllDevicesIsCalled() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);
    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();
    IHouseFactory houseFactory = new HouseFactoryImpl();
    IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(
        deviceServiceImpl, deviceAssembler);

    // Add a house
    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";
    Address address = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
    GPS gps = new GPS(41.5514, -8.4221);
    House house = houseFactory.createHouse(address, gps);
    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    houseRepository.save(house);

    // Act
    List<DeviceDTO> devices = deactivateDeviceController.requestAllDevices();

    // Assert
    assertTrue(devices.isEmpty());
  }

  /**
   * Test to verify that the requestDeactivateDevice method returns the deactivated device.
   */
  @Test
  void shouldReturnDeactivatedDevice_WhenRequestDeactivateDeviceIsCalled() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);
    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);
    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();
    IHouseFactory houseFactory = new HouseFactoryImpl();
    IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(
        deviceServiceImpl, deviceAssembler);

    // Add a house
    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";
    Address address = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
    GPS gps = new GPS(41.5514, -8.4221);
    House house = houseFactory.createHouse(address, gps);
    when(houseRepository.getTheHouse()).thenReturn(Optional.of(house));
    houseRepository.save(house);

    // Add a room
    RoomName roomName = new RoomName("Living Room");
    Dimension dimension = new Dimension(10, 10, 10);
    RoomFloor roomFloor = new RoomFloor(1);
    Room room = roomServiceImpl.addRoom(roomName, dimension, roomFloor);
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));

    // Add a device
    DeviceName deviceName = new DeviceName("Lightbulb");
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");
    Device device = deviceServiceImpl.addDevice(room.getID(), deviceName,
        deviceTypeID);
    DeviceID deviceId = device.getID();

    when(deviceRepository.ofIdentity(deviceId)).thenReturn(Optional.of(device));

    DeviceDTO deviceDTO = new DeviceDTO(device.getID().toString(), room.getID().toString(),
        deviceName.toString(), deviceStatus.toString());
    // Act
    DeviceDTO deactivatedDevice = deactivateDeviceController.requestDeactivateDevice(deviceDTO);

    // Assert
    assertEquals("OFF", deactivatedDevice.deviceStatus);
  }

  /**
   * Test to verify that the requestDeactivateDevice method returns the device not found message.
   */
  @Test
  void shouldReturnDeviceNotFound_WhenRequestDeactivateDeviceIsCalledAndDeviceIsNotFound() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);
    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);
    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();
    IHouseFactory houseFactory = new HouseFactoryImpl();
    IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(
        deviceServiceImpl, deviceAssembler);

    // Add a house
    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";
    Address address = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
    GPS gps = new GPS(41.5514, -8.4221);
    House house = houseFactory.createHouse(address, gps);
    when(houseRepository.getTheHouse()).thenReturn(Optional.of(house));
    houseRepository.save(house);

    // Add a room
    RoomName roomName = new RoomName("Living Room");
    Dimension dimension = new Dimension(10, 10, 10);
    RoomFloor roomFloor = new RoomFloor(1);
    Room room = roomServiceImpl.addRoom(roomName, dimension, roomFloor);
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));

    DeviceDTO deviceDTO = new DeviceDTO("does_not_exist", room.getID().toString(), "Lightbulb",
        "OFF");
    // Act
    DeviceDTO deactivatedDevice = deactivateDeviceController.requestDeactivateDevice(deviceDTO);

    // Assert
    assertEquals("Device not found.", deactivatedDevice.deviceID);
  }
}