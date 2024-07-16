/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.device;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;

class DeviceTest {

  /**
   * Instantiates a new Device object with valid parameters.
   */
  @Test
  void shouldInstantiateANewDevice_WhenIDDoesntExist() {

    //Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = mock(DeviceName.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

    try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
      //Act
      Device device = new Device(roomID, deviceName, deviceTypeID);

      //Assert
      assertNotNull(device);
    }
  }

  /**
   * Instantiates a new Device object with valid parameters if ID already exists.
   */
  @Test
  void shouldInstantiateANewDevice_whenIDExists() {
    //Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = mock(DeviceName.class);
    DeviceStatus deviceStatus = mock(DeviceStatus.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);
    DeviceID deviceID = mock(DeviceID.class);

    //Act
    Device device = new Device(deviceID, roomID, deviceName, deviceStatus, deviceTypeID);

    //Assert
    assertNotNull(device);
  }


  /**
   * Throws an IllegalArgumentException when the constructor is called with a null RoomID.
   */
  @Test
  void shouldThrowIllegalArgumentException_When1stConstructorIsCalledWithNullRoomID() {

    //Arrange
    RoomID roomID = null;
    DeviceName deviceName = mock(DeviceName.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

    try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
      String expectedMessage = "RoomID is required";

      //Act & Assert
      Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new Device(roomID, deviceName, deviceTypeID);
      });

      String actualMessage = exception.getMessage();

      //Assert
      assertEquals(expectedMessage, actualMessage);
    }
  }

  /**
   * Throws an IllegalArgumentException when the constructor is called with a null RoomID.
   */
  @Test
  void shouldThrowIllegalArgumentException_When2ndConstructorIsCalledWithNullRoomID() {

    //Arrange
    RoomID roomID = null;
    DeviceName deviceName = mock(DeviceName.class);
    DeviceStatus deviceStatus = mock(DeviceStatus.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);
    DeviceID deviceID = mock(DeviceID.class);

    try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
      String expectedMessage = "RoomID is required";

      //Act & Assert
      Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new Device(deviceID, roomID, deviceName, deviceStatus, deviceTypeID);
      });

      String actualMessage = exception.getMessage();

      //Assert
      assertEquals(expectedMessage, actualMessage);
    }
  }

  /**
   * Throws an IllegalArgumentException when the constructor is called with a null DeviceName.
   */
  @Test
  void shouldThrowIllegalArgumentException_When1stConstructorIsCalledWithNullDeviceName() {

    //Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = null;
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

    try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
      String expectedMessage = "DeviceName is required";

      //Act & Assert
      Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new Device(roomID, deviceName, deviceTypeID);
      });

      String actualMessage = exception.getMessage();

      //Assert
      assertEquals(expectedMessage, actualMessage);
    }
  }

  /**
   * Throws an IllegalArgumentException when the constructor is called with a null DeviceName.
   */
  @Test
  void shouldThrowIllegalArgumentException_When2ndConstructorIsCalledWithNullDeviceName() {

    //Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = null;
    DeviceStatus deviceStatus = mock(DeviceStatus.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);
    DeviceID deviceID = mock(DeviceID.class);

    try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
      String expectedMessage = "DeviceName is required";

      //Act & Assert
      Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new Device(deviceID, roomID, deviceName, deviceStatus, deviceTypeID);
      });

      String actualMessage = exception.getMessage();

      //Assert
      assertEquals(expectedMessage, actualMessage);
    }
  }


  /**
   * Throws an IllegalArgumentException when the constructor is called with a null DeviceStatus.
   */
  @Test
  void shouldThrowIllegalArgumentException_When2ndConstructorIsCalledWithNullDeviceStatus() {

    //Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = mock(DeviceName.class);
    DeviceStatus deviceStatus = null;
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);
    DeviceID deviceID = mock(DeviceID.class);

    String expectedMessage = "DeviceStatus is required";

    //Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new Device(deviceID, roomID, deviceName, deviceStatus, deviceTypeID);
    });

    String actualMessage = exception.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Throws an IllegalArgumentException when the constructor is called with a null DeviceTypeID.
   */
  @Test
  void shouldThrowIllegalArgumentException_When1stConstructorIsCalledWithNullDeviceTypeID() {

    //Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = mock(DeviceName.class);
    DeviceTypeID deviceTypeID = null;

    String expectedMessage = "DeviceTypeID is required";

    //Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new Device(roomID, deviceName, deviceTypeID);
    });

    String actualMessage = exception.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Throws an IllegalArgumentException when the constructor is called with a null DeviceTypeID.
   */
  @Test
  void shouldThrowIllegalArgumentException_When2ndConstructorIsCalledWithNullDeviceTypeID() {

    //Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = mock(DeviceName.class);
    DeviceStatus deviceStatus = mock(DeviceStatus.class);
    DeviceTypeID deviceTypeID = null;
    DeviceID deviceID = mock(DeviceID.class);

    String expectedMessage = "DeviceTypeID is required";

    //Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new Device(deviceID, roomID, deviceName, deviceStatus, deviceTypeID);
    });

    String actualMessage = exception.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Throws an IllegalArgumentException when the constructor is called with a null DeviceID.
   */
  @Test
  void shouldThrowIllegalArgumentException_When2ndConstructorIsCalledWithNullDeviceID() {

    //Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = mock(DeviceName.class);
    DeviceStatus deviceStatus = mock(DeviceStatus.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);
    DeviceID deviceID = null;

    String expectedMessage = "DeviceID is required";

    //Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new Device(deviceID, roomID, deviceName, deviceStatus, deviceTypeID);
    });

    String actualMessage = exception.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should return the RoomID of the Device object.
   */
  @Test
  void shouldReturnRoomID() {
    //Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = mock(DeviceName.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

    try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
      Device device = new Device(roomID, deviceName, deviceTypeID);
      //Act
      RoomID result = device.getRoomID();

      //Assert
      assertEquals(result, roomID);
    }
  }

  /**
   * Should return the DeviceID of the Device object.
   */
  @Test
  void shouldReturnDeviceID() {
    //Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = mock(DeviceName.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

    String expectedDeviceID = "123";

    try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn(expectedDeviceID);
        })) {
      Device device = new Device(roomID, deviceName, deviceTypeID);

      //Act
      DeviceID result = device.getID();

      //Assert
      assertEquals(expectedDeviceID, result.getID());
    }
  }

  /**
   * Should return the DeviceName of the Device object.
   */
  @Test
  void shouldReturnDeviceName() {
    //Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = mock(DeviceName.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

    try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
      Device device = new Device(roomID, deviceName, deviceTypeID);
      //Act
      DeviceName result = device.getName();
      //Assert
      assertEquals(result, deviceName);
    }
  }


  /**
   * Should return true when equals method is called with the same object.
   */
  @Test
  void shouldReturnTrueWhenGivenSameObject() {
    //Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = mock(DeviceName.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

    try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
      Device device = new Device(roomID, deviceName, deviceTypeID);
      //Act
      boolean result = device.equals(device);
      //Assert
      assertTrue(result);

    }
  }


  /**
   * Should return false when comparing two objects with different ID.
   */
  @Test
  void shouldReturnFalseWhenComparingTwoObjectsWithDifferentID() {
    //Arrange
    RoomID roomID = mock(RoomID.class);
    RoomID roomID2 = mock(RoomID.class);
    DeviceName deviceName = mock(DeviceName.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

    try (MockedConstruction<DeviceID> mocked = mockConstruction(DeviceID.class)) {

      Device device = new Device(roomID, deviceName, deviceTypeID);
      Device device2 = new Device(roomID2, deviceName, deviceTypeID);

      //Act
      boolean result = device.equals(device2);

      //Assert
      assertFalse(result);
    }
  }

  @Test
  void shouldReturnFalseWhenComparingObjectWithNull() {
    //Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = mock(DeviceName.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

    try (MockedConstruction<DeviceID> mocked = mockConstruction(DeviceID.class)) {

      Device device = new Device(roomID, deviceName, deviceTypeID);

      //Act
      boolean result = device.equals(null);

      //Assert
      assertFalse(result);
    }
  }

  /**
   * Should return device as a string when toString is called.
   */
  @Test
  void shouldReturnExpectedStringWhenToStringIsCalled() {
    //Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = mock(DeviceName.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

    try (MockedConstruction<DeviceID> mocked = mockConstruction(DeviceID.class)) {
      Device device = new Device(roomID, deviceName, deviceTypeID);

      String expectedString =
          "Device:" + "roomID=" + roomID + ", deviceID=" + device.getID() + ", deviceName="
              + deviceName + ", deviceStatus=" + device.getDeviceStatus();

      //Act
      String result = device.toString();

      //Assert
      assertEquals(expectedString, result);
    }
  }

  @Test
  void shouldReturnDeactivatedDeviceStatus_WhenDeactivateDeviceIsCalled() {
    //Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = mock(DeviceName.class);
    DeviceStatus deviceStatus = mock(DeviceStatus.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);
    DeviceID deviceID = mock(DeviceID.class);

    try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
      Device device = new Device(deviceID,roomID, deviceName, deviceStatus, deviceTypeID);

      String expected = "OFF";

      when(deviceStatus.toString()).thenReturn(expected);

      //Act
      DeviceStatus result = device.deactivateDevice();

      //Assert
      assertEquals(expected, result.toString());


    }
  }

  /**
   * Should return the DeviceTypeID of the Device object.
   */
  @Test
  void shouldReturnDeviceTypeID() {
    //Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = mock(DeviceName.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

    try (MockedConstruction<DeviceID> mocked = mockConstruction(DeviceID.class)) {
      Device device = new Device(roomID, deviceName, deviceTypeID);
      //Act
      DeviceTypeID result = device.getDeviceTypeID();
      //Assert
      assertEquals(result, deviceTypeID);
    }
  }

  @Test
  void shouldReturnDeviceHashCode_whenHashCodeIsCalled() {
    //Arrange
    RoomID roomID = mock(RoomID.class);
    DeviceName deviceName = mock(DeviceName.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

    try (MockedConstruction<DeviceID> mocked = mockConstruction(DeviceID.class)) {
      Device device = new Device(roomID, deviceName, deviceTypeID);
      int expected = device.getID().hashCode();

      //Act
      int result = device.hashCode();

      //Assert
      assertEquals(expected, result);
    }
  }
}
