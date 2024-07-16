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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import smarthome.domain.device.Device;
import smarthome.domain.device.IDeviceFactory;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.IRoomRepository;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.persistence.mem.DeviceRepository;
import smarthome.persistence.mem.RoomRepository;
import smarthome.utils.dto.DeviceDTO;

class DeviceServiceImplTest {

  /**
   * Test the constructor of the DeviceService class.
   */
  @Test
  void shouldInstantiateDeviceService_WhenGivenValidParameters() {
    // Arrange
    IDeviceRepository deviceRepository = mock(DeviceRepository.class);
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = mock(RoomRepository.class);

    // Act
    IDeviceService result = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    // Assert
    assertNotNull(result);
  }

  /**
   * Test the constructor of the DeviceService class with a null DeviceRepository.
   */
  @Test
  void shouldThrowException_WhenGivenNullDeviceRepository() {
    // Arrange
    IDeviceRepository deviceRepository = null;
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = mock(RoomRepository.class);

    String expectedMessage = "Device repository is required";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
    });

    String actualMessage = exception.getMessage();

    // Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test the constructor of the DeviceService class with a null DeviceFactory.
   */
  @Test
  void shouldThrowException_WhenGivenNullDeviceFactory() {
    // Arrange
    IDeviceRepository deviceRepository = mock(DeviceRepository.class);
    IDeviceFactory deviceFactory = null;
    IRoomRepository roomRepository = mock(RoomRepository.class);

    String expectedMessage = "Device factory is required";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
    });

    String actualMessage = exception.getMessage();

    // Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test the constructor of the DeviceService class with a null RoomRepository.
   */
  @Test
  void shouldThrowException_WhenGivenNullRoomRepository() {
    // Arrange
    IDeviceRepository deviceRepository = mock(DeviceRepository.class);
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = null;

    String expectedMessage = "Room repository is required";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
    });

    String actualMessage = exception.getMessage();

    // Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test the addDevice method of the DeviceService class with a valid roomID, deviceName and
   * deviceStatus.
   */
  @Test
  void shouldAddADevice_WhenGivenValidParameters() {
    // Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = mock(DeviceName.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = mock(IRoomRepository.class);

    DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    Room mockRoom = mock(Room.class);
    Device mockDevice = mock(Device.class);

    when(roomRepository.ofIdentity(roomID)).thenReturn(Optional.of(mockRoom));
    when(deviceFactory.createDevice(any(RoomID.class), any(DeviceName.class)
        , any(DeviceTypeID.class))).thenReturn(mockDevice);

    //Act
    Device device = deviceServiceImpl.addDevice(roomID, deviceName, deviceTypeID);

    //Assert
    assertNotNull(device);
  }

  /**
   * Test the addDevice method of the DeviceService class with an invalid roomID.
   */
  @Test
  void shouldThrowException_WhenGivenInvalidRoomID() {
    // Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = mock(DeviceName.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = mock(IRoomRepository.class);

    DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    when(roomRepository.ofIdentity(roomID)).thenReturn(Optional.empty());

    String expectedMessage = "Room with ID " + roomID + " not found.";

    //Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      deviceServiceImpl.addDevice(roomID, deviceName, deviceTypeID);
    });

    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);

  }

  /**
   * Test the getAllDevices method of the DeviceService class with a valid
   */
  @Test
  void shouldReturnAllDevices_WhenDevicesExist() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = mock(IRoomRepository.class);

    DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    Device mockDevice = mock(Device.class);
    Device mockDevice2 = mock(Device.class);
    when(deviceRepository.findAll()).thenReturn(List.of(mockDevice, mockDevice2));

    int expectedSize = 2;

    // Act
    List<Device> deviceList = deviceServiceImpl.getAllDevices();
    int result = deviceList.size();

    // Assert
    assertEquals(expectedSize, result);

  }

  /**
   * Test the getDevices method when there are no devices.
   */
  @Test
  void shouldReturnEmptyList_WhenNoDevicesExist() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = mock(IRoomRepository.class);

    DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    when(deviceRepository.findAll()).thenReturn(List.of());

    int expectedSize = 0;

    // Act
    List<Device> deviceList = deviceServiceImpl.getAllDevices();
    int result = deviceList.size();

    // Assert
    assertEquals(expectedSize, result);
  }

  /**
   * Test the getDeviceById method of the DeviceService class with a valid deviceID.
   */
  @Test
  void shouldReturnDevice_WhenGetDeviceByIdIsCalledWithValidDeviceID() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = mock(IRoomRepository.class);

    DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    DeviceID deviceID = mock(DeviceID.class);
    Device mockDevice = mock(Device.class);

    when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.of(mockDevice));

    // Act
    Optional<Device> device = deviceServiceImpl.getDeviceByID(deviceID);

    // Assert
    assertTrue(device.isPresent());
  }

  /**
   * Test the getDeviceById method of the DeviceService class with an invalid deviceID.
   */
  @Test
  void shouldReturnEmptyOptional_WhenGetDeviceByIdIsCalledWithInvalidDeviceID() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = mock(IRoomRepository.class);

    DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    DeviceID deviceID = mock(DeviceID.class);

    when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.empty());

    // Act
    Optional<Device> device = deviceServiceImpl.getDeviceByID(deviceID);

    // Assert
    assertTrue(device.isEmpty());
  }

  /**
   * Test the getDeviceListByRoomId method of the DeviceService class with a valid roomID.
   */
  @Test
  void shouldGetDeviceListByRoomId_WhenGivenValidRoomId() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = mock(IRoomRepository.class);

    DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    Room room = mock(Room.class);
    RoomID roomID = room.getID();

    Device mockDevice = mock(Device.class);
    Device mockDevice2 = mock(Device.class);

    when(deviceRepository.findByRoomID(roomID)).thenReturn(List.of(mockDevice, mockDevice2));

    int expected = 2;

    // Act
    List<Device> deviceList = deviceServiceImpl.getDevicesByRoomId(roomID);

    // Assert
    assertEquals(expected, deviceList.size());
  }

  /**
   * Tests deactivateDeviceByID method of the DeviceService class with a valid deviceID.
   */
  @Test
  void shouldDeactivateDevice_WhenGivenValidDeviceID() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = mock(IRoomRepository.class);

    DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    DeviceID deviceIdDouble = mock(DeviceID.class);
    Device deviceDouble = mock(Device.class);

    when(deviceRepository.ofIdentity(deviceIdDouble)).thenReturn(Optional.of(deviceDouble));

    // Act
    Device device = deviceServiceImpl.deactivateDeviceByID(deviceIdDouble);

    // Assert
    assertEquals(deviceDouble, device);
  }

  /**
   * Tests deactivateDeviceByID method of the DeviceService class with an invalid deviceID.
   */
  @Test
  void shouldThrowException_WhenGivenInvalidDeviceID() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = mock(IRoomRepository.class);

    DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    DeviceID deviceIdDouble = mock(DeviceID.class);

    when(deviceRepository.ofIdentity(deviceIdDouble)).thenReturn(Optional.empty());

    String expectedMessage = "Device with ID " + deviceIdDouble + " not found.";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {

      deviceServiceImpl.deactivateDeviceByID(deviceIdDouble);
    });

    String actualMessage = exception.getMessage();

    // Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test the getDevicesByTypeDescriptionFromMap method of the DeviceService class with a valid
   * deviceMap and typeDescription.
   */
  @Test
  void shouldReturnDevicesByTypeDescription_WhenGivenValidParameters() {
    // Arrange
    IDeviceRepository deviceRepository = mock(DeviceRepository.class);
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = mock(RoomRepository.class);

    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    String strTypeDescriptionTemp = "Temperature";
    TypeDescription typeDescriptionTemp = mock(TypeDescription.class);
    when(typeDescriptionTemp.toString()).thenReturn(strTypeDescriptionTemp);
    DeviceType deviceTypeTemp = mock(DeviceType.class);
    when(deviceTypeTemp.getDescription()).thenReturn(typeDescriptionTemp);

    String strTypeDescriptionLight = "Light";
    TypeDescription typeDescriptionLight = mock(TypeDescription.class);
    when(typeDescriptionLight.toString()).thenReturn(strTypeDescriptionLight);
    DeviceType deviceTypeLight = mock(DeviceType.class);
    when(deviceTypeLight.getDescription()).thenReturn(typeDescriptionLight);

    DeviceDTO deviceDTOTemp = mock(DeviceDTO.class);
    DeviceDTO deviceDTOLight = mock(DeviceDTO.class);

    List<DeviceDTO> deviceDTOTempList = new ArrayList<>();
    deviceDTOTempList.add(deviceDTOTemp);

    List<DeviceDTO> deviceDTOLightList = new ArrayList<>();
    deviceDTOLightList.add(deviceDTOLight);

    Map<DeviceType, List<DeviceDTO>> deviceMap = new LinkedHashMap<>();
    deviceMap.put(deviceTypeTemp, deviceDTOTempList);
    deviceMap.put(deviceTypeLight, deviceDTOLightList);

    // Act
    List<DeviceDTO> result = deviceServiceImpl.getDevicesByTypeDescriptionFromMap(deviceMap,
        strTypeDescriptionTemp);

    // Assert
    assertEquals(deviceDTOTemp, result.get(0));
  }

  /**
   * Test the getDevicesByTypeDescriptionFromMap method of the DeviceService class with a null
   * deviceMap.
   */
  @Test
  void shouldThrowException_WhenGivenNullDeviceMap() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = mock(IRoomRepository.class);

    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    Map<DeviceType, List<DeviceDTO>> deviceMap = null;
    String typeDescription = "Temperature";

    String expectedMessage = "Device map is required";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      deviceServiceImpl.getDevicesByTypeDescriptionFromMap(deviceMap, typeDescription);
    });

    String actualMessage = exception.getMessage();

    // Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test the getDevicesByTypeDescriptionFromMap method of the DeviceService class with a null
   * typeDescription.
   */
  @Test
  void shouldThrowException_WhenGivenNullTypeDescription() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = mock(IRoomRepository.class);

    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    Map<DeviceType, List<DeviceDTO>> deviceMap = new LinkedHashMap<>();
    String typeDescription = null;

    String expectedMessage = "Type description is required";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      deviceServiceImpl.getDevicesByTypeDescriptionFromMap(deviceMap, typeDescription);
    });

    String actualMessage = exception.getMessage();

    // Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test the getDevicesFromListByRoomId method of the DeviceService class when given valid
   * parameters.
   */
  @Test
  void shouldReturnDevicesInRoom_WhenGivenValidParameters() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = mock(IRoomRepository.class);

    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    String strRoomID = "RoomID";
    RoomID roomID = mock(RoomID.class);
    when(roomID.getID()).thenReturn(strRoomID);

    DeviceDTO deviceDTO = new DeviceDTO("DeviceID", strRoomID, "DeviceName", "DeviceStatus");
    DeviceDTO deviceDTO2 = new DeviceDTO("DeviceID2", "RoomID2", "DeviceName2", "DeviceStatus2");

    List<DeviceDTO> deviceDTOList = new ArrayList<>();
    deviceDTOList.add(deviceDTO);
    deviceDTOList.add(deviceDTO2);

    int expectedSize = 1;

    // Act
    List<DeviceDTO> result = deviceServiceImpl.getDevicesFromListByRoomId(deviceDTOList, roomID);

    // Assert
    assertEquals(deviceDTO, result.get(0));
    assertEquals(expectedSize, result.size());
  }

  /**
   * Test the getDevicesFromListByRoomId method of the DeviceService class when given a null list of
   * devices.
   */
  @Test
  void shouldThrowException_WhenGivenNullListOfDevices() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = mock(IRoomRepository.class);

    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    List<DeviceDTO> deviceDTOList = null;
    RoomID roomID = mock(RoomID.class);

    String expectedMessage = "List of DevicesDTO is required";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      deviceServiceImpl.getDevicesFromListByRoomId(deviceDTOList, roomID);
    });

    // Assert
    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test the getDevicesFromListByRoomId method of the DeviceService class when given a null
   * roomID.
   */
  @Test
  void shouldThrowException_WhenGivenNullRoomID() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = mock(IRoomRepository.class);

    IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    List<DeviceDTO> deviceDTOList = new ArrayList<>();
    RoomID roomID = null;

    String expectedMessage = "A Room ID is required";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      deviceServiceImpl.getDevicesFromListByRoomId(deviceDTOList, roomID);
    });

    // Assert
    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test the getDeviceByRoomID method of the DeviceService class when given a valid DeviceID
   */
  @Test
  void shouldGetDeviceListByDeviceId_WhenGivenValidDeviceId() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    IRoomRepository roomRepository = mock(IRoomRepository.class);

    DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory,
        roomRepository);

    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);
    Device mockDevice = mock(Device.class);

    when(deviceRepository.findByDeviceTypeID(deviceTypeID)).thenReturn(List.of(mockDevice));

    // Act
    List<Device> deviceList = deviceServiceImpl.getDevicesByDeviceTypeID(deviceTypeID);

    // Assert
    assertEquals(mockDevice, deviceList.get(0));
    assertEquals(1, deviceList.size());
  }
}