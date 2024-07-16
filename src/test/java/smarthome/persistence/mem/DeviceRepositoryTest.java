/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.mem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import smarthome.domain.device.Device;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.RoomID;

class DeviceRepositoryTest {

  /**
   * Test the save method of the DeviceRepository class with a valid Device.
   */
  @Test
  void shouldSaveDevice_WhenGivenValidDevice() {
    //Arrange
    Device Device = mock(Device.class);
    DeviceID DeviceID = mock(DeviceID.class);
    when(Device.getID()).thenReturn(DeviceID);
    DeviceRepository DeviceRepository = new DeviceRepository();
    //Act
    Device savedDevice = DeviceRepository.save(Device);
    //Assert
    assertEquals(Device, savedDevice);
  }

  /**
   * Test the save method of the DeviceRepository class with a null Device.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenGivenNullDevice() {
    //Arrange
    Device Device = null;
    DeviceRepository DeviceRepository = new DeviceRepository();
    String expectedMessage = "Device is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> DeviceRepository.save(Device));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test the save method of the DeviceRepository class with an already existing Device.
   */
  @Test
  void shouldThrowException_WhenDeviceAlreadyExists() {
    //Arrange
    Device Device = mock(Device.class);
    DeviceID DeviceID = mock(DeviceID.class);
    when(Device.getID()).thenReturn(DeviceID);
    DeviceRepository DeviceRepository = new DeviceRepository();

    DeviceRepository.save(Device);
    String expectedMessage = "Device already exists.";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> DeviceRepository.save(Device));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test the findAll method of the DeviceRepository class.
   */
  @Test
  void shouldReturnAllDevices_WhenFindAllIsCalled() {
    //Arrange
    Device firstDevice = mock(Device.class);
    DeviceID firstDeviceID = mock(DeviceID.class);
    when(firstDevice.getID()).thenReturn(firstDeviceID);
    Device secondDevice = mock(Device.class);
    DeviceID secondDeviceID = mock(DeviceID.class);
    when(secondDevice.getID()).thenReturn(secondDeviceID);

    DeviceRepository DeviceRepository = new DeviceRepository();

    DeviceRepository.save(firstDevice);
    DeviceRepository.save(secondDevice);
    List<Device> expectedList = List.of(firstDevice, secondDevice);
    //Act
    List<Device> allDevices = DeviceRepository.findAll();
    //Assert
    assertEquals(expectedList, allDevices);
  }

  /**
   * Test the findAll method of the DeviceRepository class when no Devices are saved.
   */
  @Test
  void shouldReturnEmptyList_WhenNoDevicesAreSaved() {
    //Arrange
    DeviceRepository DeviceRepository = new DeviceRepository();
    //Act
    List<Device> allDevices = DeviceRepository.findAll();
    //Assert
    assertTrue(allDevices.isEmpty());
  }

  /**
   * Test the ofIdentity method of the DeviceRepository class with a valid DeviceID.
   */
  @Test
  void shoudReturnDevice_WhenGivenValidDeviceID() {
    //Arrange
    Device Device = mock(Device.class);
    DeviceID DeviceID = mock(DeviceID.class);
    when(Device.getID()).thenReturn(DeviceID);
    DeviceRepository DeviceRepository = new DeviceRepository();
    DeviceRepository.save(Device);
    //Act
    Device returnedDevice = DeviceRepository.ofIdentity(DeviceID).get();
    //Assert
    assertEquals(Device, returnedDevice);
  }

  /**
   * Test the ofIdentity method of the DeviceRepository class with an invalid DeviceID.
   */
  @Test
  void shouldReturnOptinalEmpty_WhenGivenInvalidDeviceID() {
    //Arrange
    DeviceRepository DeviceRepository = new DeviceRepository();

    Device Device = mock(Device.class);
    DeviceID DeviceID = mock(DeviceID.class);
    when(Device.getID()).thenReturn(DeviceID);
    DeviceRepository.save(Device);

    DeviceID nonExistentDeviceID = mock(DeviceID.class);
    //Act
    Optional<Device> returnedDevice = DeviceRepository.ofIdentity(nonExistentDeviceID);
    //Assert
    assertTrue(returnedDevice.isEmpty());
  }

  /**
   * Test the containsOfIdentity method of the DeviceRepository class with a valid DeviceID.
   */
  @Test
  void shouldReturnTrue_WhenGivenValidDeviceID() {
    //Arrange
    Device Device = mock(Device.class);
    DeviceID DeviceID = mock(DeviceID.class);
    when(Device.getID()).thenReturn(DeviceID);
    DeviceRepository DeviceRepository = new DeviceRepository();
    DeviceRepository.save(Device);
    //Act
    boolean containsDevice = DeviceRepository.containsOfIdentity(DeviceID);
    //Assert
    assertTrue(containsDevice);
  }

  /**
   * Test the containsOfIdentity method of the DeviceRepository class with an invalid DeviceID.
   */
  @Test
  void shouldReturnFalse_WhenGivenInvalidDeviceID() {
    //Arrange
    DeviceRepository DeviceRepository = new DeviceRepository();

    Device Device = mock(Device.class);
    DeviceID DeviceID = mock(DeviceID.class);
    when(Device.getID()).thenReturn(DeviceID);
    DeviceRepository.save(Device);

    DeviceID nonExistentDeviceID = mock(DeviceID.class);
    //Act
    boolean containsDevice = DeviceRepository.containsOfIdentity(nonExistentDeviceID);
    //Assert
    assertFalse(containsDevice);
  }

  /**
   * Test the findByRoomId method of the DeviceRepository class with a valid RoomID.
   */
  @Test
  void shouldReturnDeviceList_WhenGivenValidRoomID() {
    //Arrange
    DeviceRepository deviceRepository = new DeviceRepository();

    Device device = mock(Device.class);
    DeviceID deviceID = mock(DeviceID.class);
    when(device.getID()).thenReturn(deviceID);

    Device device2 = mock(Device.class);
    DeviceID deviceID2 = mock(DeviceID.class);
    when(device2.getID()).thenReturn(deviceID2);

    Device device3 = mock(Device.class);
    DeviceID deviceID3 = mock(DeviceID.class);
    when(device3.getID()).thenReturn(deviceID3);

    RoomID roomID = mock(RoomID.class);
    when(device.getRoomID()).thenReturn(roomID);
    when(device2.getRoomID()).thenReturn(roomID);
    when(device3.getRoomID()).thenReturn(roomID);

    deviceRepository.save(device);
    deviceRepository.save(device2);
    deviceRepository.save(device3);

    List<Device> expectedDeviceList = List.of(device, device2, device3);

    //Act
    List<Device> returnedDeviceList = deviceRepository.findByRoomID(roomID);

    //Assert
    assertEquals(expectedDeviceList, returnedDeviceList);
  }


}