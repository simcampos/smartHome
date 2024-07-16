/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.device.Device;
import smarthome.domain.device.DeviceFactoryImpl;
import smarthome.domain.device.IDeviceFactory;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;
import smarthome.persistence.data_model.DeviceDataModel;

class DeviceDataModelAssemblerTest {

  /**
   * Test to verify if the DeviceDataModelConverter is instantiated correctly
   */
  @Test
  void shouldInstantiateDeviceDataModelConverter_whenDeviceFactoryIsValid() {
    //Arrange
    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);

    //Act
    DeviceDataModelAssembler deviceDataModelAssembler = new DeviceDataModelAssembler(deviceFactory);

    //Assert
    assertNotNull(deviceDataModelAssembler);
  }

  /**
   * Test to verify if the DeviceDataModelConverter throws an IllegalArgumentException when given a
   * null device factory
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceFactoryIsNull() {
    //Arrange
    IDeviceFactory deviceFactory = null;

    String expectedMessage = "Device Factory is required";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new DeviceDataModelAssembler(deviceFactory));
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test to verify if the DeviceDataModelConverter converts a DeviceDataModel to a Device domain
   * object
   */
  @Test
  void shouldConvertDeviceDataModelToDomain_whenDeviceDataModelIsValid() {
    //Arrange
    String deviceID = "1";
    String roomID = "123";
    String deviceName = "Light";
    boolean deviceStatus = true;
    String deviceTypeID = "1";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    RoomID roomIDDouble = mock(RoomID.class);
    DeviceName deviceNameDouble = mock(DeviceName.class);
    DeviceStatus deviceStatusDouble = mock(DeviceStatus.class);
    DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);

    DeviceDataModel deviceDataModelDouble = mock(DeviceDataModel.class);

    when(deviceDataModelDouble.getDeviceID()).thenReturn(deviceID);
    when(deviceDataModelDouble.getRoomID()).thenReturn(roomID);
    when(deviceDataModelDouble.getDeviceName()).thenReturn(deviceName);
    when(deviceDataModelDouble.getDeviceStatus()).thenReturn(deviceStatus);
    when(deviceDataModelDouble.getDeviceTypeID()).thenReturn(deviceTypeID);

    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    DeviceDataModelAssembler deviceDataModelAssembler = new DeviceDataModelAssembler(deviceFactory);

    Device expected = deviceFactory.createDevice(deviceIDDouble, roomIDDouble, deviceNameDouble,
        deviceStatusDouble, deviceTypeIDDouble);

    //Act
    Device result = deviceDataModelAssembler.toDomain(deviceDataModelDouble);

    //Assert
    assertEquals(expected, result);
  }

  /**
   * Test to verify if the DeviceDataModelConverter throws an IllegalArgumentException when given a
   * null DeviceDataModel
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceDataModelIsNull() {
    //Arrange
    DeviceDataModel deviceDataModel = null;

    IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
    DeviceDataModelAssembler deviceDataModelAssembler = new DeviceDataModelAssembler(deviceFactory);

    String expectedMessage = "Device Data Model is required";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> deviceDataModelAssembler.toDomain(deviceDataModel));
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test to verify if the DeviceDataModelConverter converts a list of DeviceDataModel to a list of
   * Device domain objects
   */
  @Test
  void shouldConvertListOfDeviceDataModelToDomain_whenDeviceDataModelListIsValid() {
    //Arrange
    String deviceID = "1";
    String roomID = "123";
    String deviceName = "Light";
    boolean deviceStatus = true;
    String deviceTypeID = "1";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.getID()).thenReturn(deviceID);

    RoomID roomIDDouble = mock(RoomID.class);
    when(roomIDDouble.toString()).thenReturn(roomID);

    DeviceName deviceNameDouble = mock(DeviceName.class);
    when(deviceNameDouble.getName()).thenReturn(deviceName);

    DeviceStatus deviceStatusDouble = mock(DeviceStatus.class);
    when(deviceStatusDouble.getStatus()).thenReturn(deviceStatus);

    DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);
    when(deviceTypeIDDouble.getID()).thenReturn(deviceTypeID);

    DeviceDataModel deviceDataModelDouble = mock(DeviceDataModel.class);

    when(deviceDataModelDouble.getDeviceID()).thenReturn(deviceID);
    when(deviceDataModelDouble.getRoomID()).thenReturn(roomID);
    when(deviceDataModelDouble.getDeviceName()).thenReturn(deviceName);
    when(deviceDataModelDouble.getDeviceStatus()).thenReturn(deviceStatus);
    when(deviceDataModelDouble.getDeviceTypeID()).thenReturn(deviceTypeID);

    DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);

    DeviceDataModelAssembler deviceDataModelAssembler = new DeviceDataModelAssembler(deviceFactory);

    List<DeviceDataModel> deviceDataModelList = new ArrayList<>();
    deviceDataModelList.add(deviceDataModelDouble);

    //Expected
    Device expected = mock(Device.class);

    when(deviceFactory.createDevice(any(DeviceID.class), any(RoomID.class), any(DeviceName.class),
        any(DeviceStatus.class), any(DeviceTypeID.class))).thenReturn(expected);

    List<Device> expectedList = List.of(expected);

    //Act
    List<Device> result = deviceDataModelAssembler.toDomain(List.of(deviceDataModelDouble));

    //Assert
    assertEquals(expectedList, result);
  }


}
