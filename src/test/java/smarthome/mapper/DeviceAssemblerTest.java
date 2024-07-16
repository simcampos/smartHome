/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.device.Device;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.RoomID;
import smarthome.utils.dto.DeviceDTO;

class DeviceAssemblerTest {

  /**
   * Test if the domainToDTO method returns a DeviceDTO object when the device is valid.
   */
  @Test
  void shouldReturnADeviceDTO_WhenGivenADevice() {
    //Arrange
    String deviceID = "1";
    String roomID = "1";
    String deviceName = "Test Device";
    String deviceStatus = "Off";

    Device device = mock(Device.class);

    when(device.getID()).thenReturn(mock(DeviceID.class));
    when(device.getID().toString()).thenReturn(deviceID);

    when(device.getRoomID()).thenReturn(mock(RoomID.class));
    when(device.getRoomID().toString()).thenReturn(roomID);

    when(device.getName()).thenReturn(mock(DeviceName.class));
    when(device.getName().getName()).thenReturn(deviceName);

    when(device.getDeviceStatus()).thenReturn(mock(DeviceStatus.class));
    when(device.getDeviceStatus().toString()).thenReturn(deviceStatus);

    DeviceAssembler deviceAssembler = new DeviceAssembler();

    DeviceDTO expectedDevice = new DeviceDTO(deviceID, roomID, deviceName, deviceStatus);

    //Act
    DeviceDTO deviceDTO = deviceAssembler.domainToDTO(device);

    //Assert
    assertEquals(expectedDevice.toString(), deviceDTO.toString());
  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the device is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenGivenNullDevice() {
    //Arrange
    Device device = null;
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    String expectedMessage = "Device is required";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> deviceAssembler.domainToDTO(device));

    //Assert
    String result = exception.getMessage();
    assertEquals(expectedMessage, result);
  }

  /**
   * Test if the domainToDTO method returns a list of DeviceDTO objects when the list of devices is
   * valid.
   */
  @Test
  void shouldReturnListOfDeviceDTO_WhenGivenListOfDevices()  {
    //Arrange
    String deviceID = "1";
    String roomID = "1";
    String deviceName = "Test Device";
    String deviceStatus = "Off";

    Device device = mock(Device.class);
    when(device.getID()).thenReturn(mock(DeviceID.class));
    when(device.getID().toString()).thenReturn(deviceID);

    when(device.getRoomID()).thenReturn(mock(RoomID.class));
    when(device.getRoomID().toString()).thenReturn(roomID);

    when(device.getName()).thenReturn(mock(DeviceName.class));
    when(device.getName().getName()).thenReturn(deviceName);

    when(device.getDeviceStatus()).thenReturn(mock(DeviceStatus.class));
    when(device.getDeviceStatus().toString()).thenReturn(deviceStatus);

    List<Device> devices = List.of(device);

    DeviceAssembler deviceAssembler = new DeviceAssembler();

    DeviceDTO expectedDevice = new DeviceDTO(deviceID, roomID, deviceName, deviceStatus);
    List<DeviceDTO> expected = List.of(expectedDevice);

    //Act
    List<DeviceDTO> deviceDTOs = deviceAssembler.domainToDTO(devices);

    //Assert
    assertEquals(expected.toString(), deviceDTOs.toString());
  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the list of devices is
   * null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenGivenNullDeviceList() {
    //Arrange
    DeviceAssembler deviceAssembler = new DeviceAssembler();
    List<Device> devices = null;

    String expectedMessage = "The list of Devices cannot be null.";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> deviceAssembler.domainToDTO(devices));

    //Assert
    String result = exception.getMessage();
    assertEquals(expectedMessage, result);
  }

  /**
   * should return empty list of DeviceDTO when the list of devices is empty
   */
  @Test
  void shouldReturnEmptyListOfDeviceDTO_WhenGivenEmptyListOfDevices()  {
    //Arrange
    List<Device> devices = List.of();
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    //Act
    List<DeviceDTO> deviceDTOs = deviceAssembler.domainToDTO(devices);

    //Assert
    assertEquals(0, deviceDTOs.size());
  }
}