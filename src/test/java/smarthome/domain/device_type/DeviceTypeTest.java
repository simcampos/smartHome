/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.device_type;

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
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.TypeDescription;

class DeviceTypeTest {

  /**
   * Should create an instance of DeviceType when the constructor attributes are valid.
   */
  @Test
  void shouldCreateInstanceOfDeviceType_whenConstructorAttributesAreValid() {
    // Arrange
    TypeDescription deviceTypeDescription = mock(TypeDescription.class);

    try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("123");
        })) {
      // Act
      DeviceType deviceType = new DeviceType(deviceTypeDescription);

      // Assert
      assertNotNull(deviceType);

    }
  }

  /**
   * Should create an instance of DeviceType when the constructor attributes are valid including
   * DeviceTypeID
   */
  @Test
  void shouldCreateInstanceOfDeviceType_whenConstructorAttributesAreValidIncludingDeviceTypeID() {
    // Arrange
    TypeDescription deviceTypeDescription = mock(TypeDescription.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

    try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("123");
        })) {
      // Act
      DeviceType deviceType = new DeviceType(deviceTypeID, deviceTypeDescription);

      // Assert
      assertNotNull(deviceType);

    }
  }

  /**
   * Should throw an exception when the device type description is null.
   */
  @Test
  void shouldThrowException_whenDeviceTypeDescriptionIsNull() {
    // Arrange
    TypeDescription deviceTypeDescription = null;

    String expectedMessage = "DeviceTypeDescription is required";

    try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("123");
        })) {
      //Act + Assert

      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
        new DeviceType(deviceTypeDescription);
      });

      assertEquals(expectedMessage, exception.getMessage());

    }
  }

  /**
   * Should return the device type ID.
   */
  @Test
  void shouldReturnDeviceTypeID_whenGetIdIsCalled() {
    // Arrange
    TypeDescription deviceTypeDescription = mock(TypeDescription.class);

    String expectedId = "123";

    try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("123");
        })) {
      DeviceType deviceType = new DeviceType(deviceTypeDescription);

      // Act
      DeviceTypeID result = deviceType.getID();

      // Assert
      assertEquals(expectedId, result.getID());
    }
  }

  /**
   * Should return true when the instances are the same object.
   */
  @Test
  void shouldReturnTrue_whenInstancesAreSameObject() {
    // Arrange
    TypeDescription deviceTypeDescription = mock(TypeDescription.class);

    try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("123");
        })) {
      DeviceType deviceType = new DeviceType(deviceTypeDescription);

      // Act
      boolean result = deviceType.equals(deviceType);

      // Assert
      assertTrue(result);
    }
  }

  /**
   * Should return true when the instances are equal.
   */
  @Test
  void shouldReturnFalse_whenInstancesAreNotEqual() {
    // Arrange
    TypeDescription deviceTypeDescription1 = mock(TypeDescription.class);
    TypeDescription deviceTypeDescription2 = mock(TypeDescription.class);

    try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("123");
        })) {
      DeviceType deviceType1 = new DeviceType(deviceTypeDescription1);
      DeviceType deviceType2 = new DeviceType(deviceTypeDescription2);

      // Act
      boolean result = deviceType1.equals(deviceType2);

      // Assert
      assertFalse(result);
    }
  }

  /**
   * Should return false when the object is not an instance of DeviceType.
   */
  @Test
  void shouldReturnFalse_WhenObjectIsNotTheInstanceOfDeviceType() {
    // Arrange
    TypeDescription deviceTypeDescription = mock(TypeDescription.class);

    try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("123");
        })) {
      DeviceType deviceType = new DeviceType(deviceTypeDescription);

      // Act
      boolean result = deviceType.equals(new Object());

      // Assert
      assertFalse(result);
    }
  }

  /**
   * Should return the device type description.
   */
  @Test
  void shouldReturnDeviceTypeDescription_whenGetDescriptionIsCalled() {
    // Arrange
    TypeDescription deviceTypeDescription = mock(TypeDescription.class);

    try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("123");
        })) {
      DeviceType deviceType = new DeviceType(deviceTypeDescription);

      // Act
      TypeDescription result = deviceType.getDescription();

      // Assert
      assertEquals(deviceTypeDescription, result);
    }
  }

  /**
   * Should return the device type ID hash code.
   */
  @Test
  void shouldReturnDeviceTypeHashCode_whenHashCodeIsCalled() {
    // Arrange
    TypeDescription deviceTypeDescription = mock(TypeDescription.class);

    try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("123");
        })) {
      DeviceType deviceType = new DeviceType(deviceTypeDescription);

      int expected = deviceType.getID().hashCode();

      // Act
      int result = deviceType.hashCode();

      // Assert
      assertEquals(expected, result);
    }
  }

  /**
   * Should return the device type string representation.
   */
  @Test
  void shouldReturnDeviceTypeStringRepresentation_whenToStringIsCalled() {
    // Arrange
    TypeDescription deviceTypeDescription = mock(TypeDescription.class);

    try (MockedConstruction<DeviceTypeID> deviceTypeIdMocked = mockConstruction(DeviceTypeID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("123");
        })) {
      DeviceType deviceType = new DeviceType(deviceTypeDescription);

      String expected =
          "Device Type:  Device Description= " + deviceTypeDescription.getDescription() + " ID= "
              + deviceType.getID().getID();

      // Act
      String result = deviceType.toString();

      // Assert
      assertEquals(expected, result);
    }
  }


}
