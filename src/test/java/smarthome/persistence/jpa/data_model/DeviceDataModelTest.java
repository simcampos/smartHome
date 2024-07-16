/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.jpa.data_model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import smarthome.domain.device.Device;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;
import smarthome.persistence.data_model.DeviceDataModel;

class DeviceDataModelTest {

  /**
   * Test to check if the DeviceDataModel is instantiated
   */
  @Test
  void shouldInstantiateDeviceDataModel_WhenAsNoArguments() {
    // Act
    DeviceDataModel deviceModel = new DeviceDataModel();
    // Assert
    assertNotNull(deviceModel);
  }


  /**
   * Test constructor with null parameter.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDeviceIsNull() {
    //Arrange
    Device device = null;
    String expectedMessage = "Device is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new DeviceDataModel(device));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test constructor with valid parameter.
   */
  @Test
  void shouldCreateDeviceDataModel_WhenDeviceIsNotNull() {
    //Arrange
    String strRoomID = "1";
    String strDeviceName = "Light";
    String strDeviceTypeID = "1";
    boolean deviceStatus = true;
    String strDeviceID = "1";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    RoomID roomIDDouble = mock(RoomID.class);
    DeviceName deviceNameDouble = mock(DeviceName.class);
    DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);
    DeviceStatus deviceStatusDouble = mock(DeviceStatus.class);

    when(deviceIDDouble.getID()).thenReturn(strDeviceID);
    when(roomIDDouble.getID()).thenReturn(strRoomID);
    when(deviceNameDouble.getName()).thenReturn(strDeviceName);
    when(deviceTypeIDDouble.getID()).thenReturn(strDeviceTypeID);
    when(deviceStatusDouble.getStatus()).thenReturn(deviceStatus);

    Device deviceDouble = mock(Device.class);

    when(deviceDouble.getID()).thenReturn(deviceIDDouble);
    when(deviceDouble.getRoomID()).thenReturn(roomIDDouble);
    when(deviceDouble.getName()).thenReturn(deviceNameDouble);
    when(deviceDouble.getDeviceTypeID()).thenReturn(deviceTypeIDDouble);
    when(deviceDouble.getDeviceStatus()).thenReturn(deviceStatusDouble);

    //Act
    DeviceDataModel deviceDataModel = new DeviceDataModel(deviceDouble);

    //Assert
    assertNotNull(deviceDataModel);
  }

  /**
   * Test getDeviceID method.
   */
  @Test
  void shouldReturnDeviceID_WhenGetDeviceID() {
    //Arrange
    String strRoomID = "1";
    String strDeviceName = "Light";
    String strDeviceTypeID = "1";
    boolean deviceStatus = true;
    String strDeviceID = "1";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    RoomID roomIDDouble = mock(RoomID.class);
    DeviceName deviceNameDouble = mock(DeviceName.class);
    DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);
    DeviceStatus deviceStatusDouble = mock(DeviceStatus.class);

    when(deviceIDDouble.getID()).thenReturn(strDeviceID);
    when(roomIDDouble.getID()).thenReturn(strRoomID);
    when(deviceNameDouble.getName()).thenReturn(strDeviceName);
    when(deviceTypeIDDouble.getID()).thenReturn(strDeviceTypeID);
    when(deviceStatusDouble.getStatus()).thenReturn(deviceStatus);

    Device deviceDouble = mock(Device.class);

    when(deviceDouble.getID()).thenReturn(deviceIDDouble);
    when(deviceDouble.getRoomID()).thenReturn(roomIDDouble);
    when(deviceDouble.getName()).thenReturn(deviceNameDouble);
    when(deviceDouble.getDeviceTypeID()).thenReturn(deviceTypeIDDouble);
    when(deviceDouble.getDeviceStatus()).thenReturn(deviceStatusDouble);

    DeviceDataModel deviceDataModel = new DeviceDataModel(deviceDouble);

    //Act
    String result = deviceDataModel.getDeviceID();

    //Assert
    assertEquals(strDeviceID, result);
  }

  /**
   * Test getRoomID method.
   */
  @Test
  void shouldReturnRoomID_WhenGetRoomID() {
    //Arrange
    String strRoomID = "1";
    String strDeviceName = "Light";
    String strDeviceTypeID = "1";
    boolean deviceStatus = true;
    String strDeviceID = "1";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    RoomID roomIDDouble = mock(RoomID.class);
    DeviceName deviceNameDouble = mock(DeviceName.class);
    DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);
    DeviceStatus deviceStatusDouble = mock(DeviceStatus.class);

    when(deviceIDDouble.getID()).thenReturn(strDeviceID);
    when(roomIDDouble.getID()).thenReturn(strRoomID);
    when(deviceNameDouble.getName()).thenReturn(strDeviceName);
    when(deviceTypeIDDouble.getID()).thenReturn(strDeviceTypeID);
    when(deviceStatusDouble.getStatus()).thenReturn(deviceStatus);

    Device deviceDouble = mock(Device.class);

    when(deviceDouble.getID()).thenReturn(deviceIDDouble);
    when(deviceDouble.getRoomID()).thenReturn(roomIDDouble);
    when(deviceDouble.getName()).thenReturn(deviceNameDouble);
    when(deviceDouble.getDeviceTypeID()).thenReturn(deviceTypeIDDouble);
    when(deviceDouble.getDeviceStatus()).thenReturn(deviceStatusDouble);

    DeviceDataModel deviceDataModel = new DeviceDataModel(deviceDouble);

    //Act
    String result = deviceDataModel.getRoomID();

    //Assert
    assertEquals(strRoomID, result);
  }

  /**
   * Test getDeviceName method.
   */
  @Test
  void shouldReturnDeviceName_WhenGetDeviceName() {
    //Arrange
    String strRoomID = "1";
    String strDeviceName = "Light";
    String strDeviceTypeID = "1";
    boolean deviceStatus = true;
    String strDeviceID = "1";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    RoomID roomIDDouble = mock(RoomID.class);
    DeviceName deviceNameDouble = mock(DeviceName.class);
    DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);
    DeviceStatus deviceStatusDouble = mock(DeviceStatus.class);

    when(deviceIDDouble.getID()).thenReturn(strDeviceID);
    when(roomIDDouble.getID()).thenReturn(strRoomID);
    when(deviceNameDouble.getName()).thenReturn(strDeviceName);
    when(deviceTypeIDDouble.getID()).thenReturn(strDeviceTypeID);
    when(deviceStatusDouble.getStatus()).thenReturn(deviceStatus);

    Device deviceDouble = mock(Device.class);

    when(deviceDouble.getID()).thenReturn(deviceIDDouble);
    when(deviceDouble.getRoomID()).thenReturn(roomIDDouble);
    when(deviceDouble.getName()).thenReturn(deviceNameDouble);
    when(deviceDouble.getDeviceTypeID()).thenReturn(deviceTypeIDDouble);
    when(deviceDouble.getDeviceStatus()).thenReturn(deviceStatusDouble);

    DeviceDataModel deviceDataModel = new DeviceDataModel(deviceDouble);

    //Act
    String result = deviceDataModel.getDeviceName();

    //Assert
    assertEquals(strDeviceName, result);
  }

  /**
   * Test getDeviceTypeID method.
   */
  @Test
  void shouldReturnDeviceTypeID_WhenGetDeviceTypeID() {
    //Arrange
    String strRoomID = "1";
    String strDeviceName = "Light";
    String strDeviceTypeID = "1";
    boolean deviceStatus = true;
    String strDeviceID = "1";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    RoomID roomIDDouble = mock(RoomID.class);
    DeviceName deviceNameDouble = mock(DeviceName.class);
    DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);
    DeviceStatus deviceStatusDouble = mock(DeviceStatus.class);

    when(deviceIDDouble.getID()).thenReturn(strDeviceID);
    when(roomIDDouble.getID()).thenReturn(strRoomID);
    when(deviceNameDouble.getName()).thenReturn(strDeviceName);
    when(deviceTypeIDDouble.getID()).thenReturn(strDeviceTypeID);
    when(deviceStatusDouble.getStatus()).thenReturn(deviceStatus);

    Device deviceDouble = mock(Device.class);

    when(deviceDouble.getID()).thenReturn(deviceIDDouble);
    when(deviceDouble.getRoomID()).thenReturn(roomIDDouble);
    when(deviceDouble.getName()).thenReturn(deviceNameDouble);
    when(deviceDouble.getDeviceTypeID()).thenReturn(deviceTypeIDDouble);
    when(deviceDouble.getDeviceStatus()).thenReturn(deviceStatusDouble);

    DeviceDataModel deviceDataModel = new DeviceDataModel(deviceDouble);

    //Act
    String result = deviceDataModel.getDeviceTypeID();

    //Assert
    assertEquals(strDeviceTypeID, result);
  }

  /**
   * Test getDeviceStatus method.
   */
  @Test
  void shouldReturnDeviceStatus_WhenGetDeviceStatus() {
    //Arrange
    String strRoomID = "1";
    String strDeviceName = "Light";
    String strDeviceTypeID = "1";
    boolean deviceStatus = true;
    String strDeviceID = "1";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    RoomID roomIDDouble = mock(RoomID.class);
    DeviceName deviceNameDouble = mock(DeviceName.class);
    DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);
    DeviceStatus deviceStatusDouble = mock(DeviceStatus.class);

    when(deviceIDDouble.getID()).thenReturn(strDeviceID);
    when(roomIDDouble.getID()).thenReturn(strRoomID);
    when(deviceNameDouble.getName()).thenReturn(strDeviceName);
    when(deviceTypeIDDouble.getID()).thenReturn(strDeviceTypeID);
    when(deviceStatusDouble.getStatus()).thenReturn(deviceStatus);

    Device deviceDouble = mock(Device.class);

    when(deviceDouble.getID()).thenReturn(deviceIDDouble);
    when(deviceDouble.getRoomID()).thenReturn(roomIDDouble);
    when(deviceDouble.getName()).thenReturn(deviceNameDouble);
    when(deviceDouble.getDeviceTypeID()).thenReturn(deviceTypeIDDouble);
    when(deviceDouble.getDeviceStatus()).thenReturn(deviceStatusDouble);

    DeviceDataModel deviceDataModel = new DeviceDataModel(deviceDouble);

    //Act
    boolean result = deviceDataModel.getDeviceStatus();

    //Assert
    assertEquals(deviceStatus, result);
  }

  /**
   * Test to update the device data model from the domain.
   */
  @Test
  void shouldUpdateDeviceDataModel_WhenUpdateFromDomain() {
    //Arrange
    String strRoomID = "1";
    String strDeviceName = "Light";
    String strDeviceTypeID = "1";
    boolean deviceStatus = true;
    String strDeviceID = "1";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    RoomID roomIDDouble = mock(RoomID.class);
    DeviceName deviceNameDouble = mock(DeviceName.class);
    DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);
    DeviceStatus deviceStatusDouble = mock(DeviceStatus.class);

    when(deviceIDDouble.getID()).thenReturn(strDeviceID);
    when(roomIDDouble.getID()).thenReturn(strRoomID);
    when(deviceNameDouble.getName()).thenReturn(strDeviceName);
    when(deviceTypeIDDouble.getID()).thenReturn(strDeviceTypeID);
    when(deviceStatusDouble.getStatus()).thenReturn(deviceStatus);

    Device deviceDouble = mock(Device.class);

    when(deviceDouble.getID()).thenReturn(deviceIDDouble);
    when(deviceDouble.getRoomID()).thenReturn(roomIDDouble);
    when(deviceDouble.getName()).thenReturn(deviceNameDouble);
    when(deviceDouble.getDeviceTypeID()).thenReturn(deviceTypeIDDouble);
    when(deviceDouble.getDeviceStatus()).thenReturn(deviceStatusDouble);

    DeviceDataModel deviceDataModel = new DeviceDataModel(deviceDouble);

    //Act
    boolean result = deviceDataModel.updateFromDomain(deviceDouble);

    //Assert
    assertTrue(result);
  }


}

