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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.TypeDescription;

class DeviceTypeAggregateTest {

  /**
   * Should create an instance of {@link DeviceType} when the constructor attributes are valid.
   */
  @Test
  void shouldCreateInstanceOfDeviceType_whenConstructorAttributesAreValid() {
    // Arrange
    String description = "Device Type Description";
    TypeDescription deviceTypeDescription = new TypeDescription(description);

    // Act
    DeviceType deviceType = new DeviceType(deviceTypeDescription);

    // Assert
    assertNotNull(deviceType);
  }

  /**
   * Should create an instance of {@link DeviceType} when the constructor attributes are valid
   * including DeviceTypeID
   */
  @Test
  void shouldCreateInstanceOfDeviceType_whenConstructorAttributesAreValidIncludingDeviceTypeID() {
    // Arrange
    String description = "Device Type Description";
    TypeDescription deviceTypeDescription = new TypeDescription(description);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

    // Act
    DeviceType deviceType = new DeviceType(deviceTypeID, deviceTypeDescription);

    // Assert
    assertNotNull(deviceType);
  }

  /**
   * Should return the device type ID.
   */
  @Test
  void shouldReturnDeviceTypeID_whenGetIdIsCalled() {
    // Arrange
    String description = "Device Type Description";
    TypeDescription deviceTypeDescription = new TypeDescription(description);

    DeviceType deviceType = new DeviceType(deviceTypeDescription);
    // Act
    DeviceTypeID result = deviceType.getID();

    // Assert
    assertNotNull(result);
  }

  /**
   * Should return true when the instances are the same object.
   */
  @Test
  void shouldReturnTrue_whenInstancesAreSameObject() {
    // Arrange
    String description = "Device Type Description";
    TypeDescription deviceTypeDescription = new TypeDescription(description);

    DeviceType deviceType = new DeviceType(deviceTypeDescription);

    // Act
    boolean result = deviceType.equals(deviceType);

    // Assert
    assertTrue(result);
  }

  /**
   * Should return false when the objects are not the same.
   */
  @Test
  void shouldReturnFalse_whenObjectIsNotTheSame() {
    // Arrange
    String description = "Device Type Description";
    String description2 = "DeviceType";
    TypeDescription deviceTypeDescription = new TypeDescription(description);
    TypeDescription deviceTypeDescription1 = new TypeDescription(description2);

    DeviceType deviceType = new DeviceType(deviceTypeDescription);
    DeviceType deviceType1 = new DeviceType(deviceTypeDescription1);

    // Act
    boolean result = deviceType.equals(deviceType1);

    // Assert
    assertFalse(result);
  }

  /**
   * Should return false when the object is not an instance of DeviceType.
   */
  @Test
  void shouldReturnFalse_whenObjectIsNotInstanceOfDeviceType() {
    // Arrange
    String description = "Device Type Description";
    TypeDescription deviceTypeDescription = new TypeDescription(description);

    DeviceType deviceType = new DeviceType(deviceTypeDescription);

    // Act
    boolean result = deviceType.equals(new Object());

    // Assert
    assertFalse(result);
  }

  /**
   * Test case to verify the behavior of getDescription method in DeviceType class.
   */
  @Test
  void shouldReturnDeviceTypeDescription_whenGetDescriptionIsCalled() {
    // Arrange
    String description = "Device Type Description";
    TypeDescription deviceTypeDescription = new TypeDescription(description);
    DeviceType deviceType = new DeviceType(deviceTypeDescription);

    // Act
    TypeDescription result = deviceType.getDescription();

    // Assert
    assertEquals(description, result.toString());
  }

  /**
   * Test case to verify the behavior of hashCode method in DeviceType class.
   */
  @Test
  void shouldReturnHashCode_whenHashCodeIsCalled() {
    // Arrange
    String description = "Device Type Description";
    TypeDescription deviceTypeDescription = new TypeDescription(description);

    DeviceType deviceType = new DeviceType(deviceTypeDescription);

    DeviceTypeID deviceTypeID = deviceType.getID();

    int expected = deviceTypeID.hashCode();

    // Act
    int result = deviceType.hashCode();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Test case to verify the behavior of toString method in DeviceType class.
   */
  @Test
  void shouldReturnStringRepresentation_whenToStringIsCalled() {
    // Arrange
    String description = "Device Type Description";
    TypeDescription deviceTypeDescription = new TypeDescription(description);

    DeviceType deviceType = new DeviceType(deviceTypeDescription);

    String expected =
        "Device Type:  Device Description= " + description + " ID= " + deviceType.getID().getID();

    // Act
    String result = deviceType.toString();

    // Assert
    assertEquals(expected, result);
  }
}
