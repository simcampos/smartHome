/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DeviceNameTest {

  /**
   * Tests the DeviceName constructor with a valid device name.
   */
  @Test
  void shouldGetValidObject_whenUsingValidDeviceName() {
    // Arrange
    String validDeviceName = "Living Room 2";

    // Act
    DeviceName deviceName = new DeviceName(validDeviceName);

    // Assert
    assertNotNull(deviceName);

  }

  /**
   * Tests the DeviceName constructor with a null device name.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceNameNull() {
    // Arrange
    String nullDeviceName = null;

    String expectedMessage = "The device name cannot be null, blank, or empty.";

    // Act
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new DeviceName(nullDeviceName)
    );

    // Assert
    String exceptionMessage = exception.getMessage();

    assertTrue(exceptionMessage.contains(expectedMessage));
  }

  /**
   * Tests the DeviceName constructor with a blank device name.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceNameBlank() {
    // Arrange
    String blankDeviceName = " ";
    String expectedMessage = "The device name cannot be null, blank, or empty.";

    // Act
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new DeviceName(blankDeviceName)
    );

    // Assert
    String exceptionMessage = exception.getMessage();

    assertTrue(exceptionMessage.contains(expectedMessage));
  }

  /**
   * Tests the DeviceName constructor with an empty device name.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceNameEmpty() {
    // Arrange
    String emptyDeviceName = "";
    String expectedMessage = "The device name cannot be null, blank, or empty.";

    // Act
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new DeviceName(emptyDeviceName)
    );

    // Assert
    String exceptionMessage = exception.getMessage();

    assertTrue(exceptionMessage.contains(expectedMessage));
  }

  /**
   * Should throw illegal argument exception with a device name containing special characters.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceNameContainsSpecialCharacters() {
    // Arrange
    String specialCharactersDeviceName = "Living Room 2@ # $ % ^ & * ( ) _ + = - { } [ ] | \\ / ? > < , . ; : '";
    String expectedMessage = "The device name can only contain letters and numbers.";

    // Act
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new DeviceName(specialCharactersDeviceName)
    );

    // Assert
    String exceptionMessage = exception.getMessage();

    assertTrue(exceptionMessage.contains(expectedMessage));
  }

  /**
   * Test if the equals method returns true when comparing two equal device names.
   */
  @Test
  void shouldReturnTrue_whenComparingTwoEqualDeviceNames() {
    // Arrange
    String deviceName1 = "Living Room 2";
    String deviceName2 = "Living Room 2";

    // Act
    DeviceName deviceNameObject1 = new DeviceName(deviceName1);
    DeviceName deviceNameObject2 = new DeviceName(deviceName2);

    // Assert
    assertEquals(deviceNameObject1, deviceNameObject2);
  }

  /**
   * Test if the equals method returns false when comparing two different device names.
   */
  @Test
  void shouldReturnFalse_whenComparingTwoDifferentDeviceNames() {
    // Arrange
    String deviceName1 = "Living Room 2";
    String deviceName2 = "Living Room 3";

    // Act
    DeviceName deviceNameObject1 = new DeviceName(deviceName1);
    DeviceName deviceNameObject2 = new DeviceName(deviceName2);

    // Assert
    assertNotEquals(deviceNameObject1, deviceNameObject2);
  }

  /**
   * Test if the equals method returns true when comparing the same device name.
   */
  @Test
  void shouldReturnTrue_whenComparingTheSameDeviceName() {
    // Arrange
    String deviceName = "Living Room 2";
    DeviceName deviceNameObject = new DeviceName(deviceName);
    // Act
    boolean result = deviceNameObject.equals(deviceNameObject);

    // Assert
    assertTrue(result);
  }

  /**
   * Test if the deviceName object returns correct string representation.
   */
  @Test
  void shouldReturnDeviceNameInString() {
    // Arrange
    String deviceName = "Living Room";

    String expected = "Device name: Living Room";

    // Act
    DeviceName deviceNameObject = new DeviceName(deviceName);

    // Assert
    assertEquals(expected, deviceNameObject.toString());
  }

  /**
   * Test if the getName method returns the correct device name.
   */
  @Test
  void shouldReturnDeviceName() {
    //Arrange
    String deviceName = "Living Room";

    DeviceName deviceNameObject = new DeviceName(deviceName);

    String expected = "Living Room";

    //Act
    String result = deviceNameObject.getName();

    //Assert
    assertEquals(expected, result);
  }

  /**
   * Test if the hashCode method returns the same hash code for two equal device names.
   */
  @Test
  void shouldReturnHashCode_whenCallingHashCode() {
    // Arrange
    String deviceName = "Living Room 2";
    DeviceName deviceNameVO = new DeviceName(deviceName);
    int expectedHashCode = deviceName.hashCode();

    // Act
    int result = deviceNameVO.hashCode();

    // Assert
    assertEquals(expectedHashCode, result);
  }

  /**
   * Tests if equals method returns false when the object is not an instance of DeviceName
   */
  @Test
  void shouldReturnFalseWhenComparingDeviceNameWithDifferentObject() {
    // Arrange
    String deviceName = "Living Room 2";
    DeviceName deviceNameObject = new DeviceName(deviceName);

    // Act
    boolean result = deviceNameObject.equals(new Object());

    // Assert
    assertFalse(result);
  }
}