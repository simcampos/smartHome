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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DeviceTypeIDTest {

  /**
   * Tests the correct instantiation of a DeviceTypeID.
   */
  @Test
  void shouldGetValidObject_whenUsingValidStringInConstructor() {
    // Arrange
    String deviceTypeIDDescription = "Fridge";
    // Act
    DeviceTypeID deviceTypeID = new DeviceTypeID(deviceTypeIDDescription);
    // Assert
    assertNotNull(deviceTypeID);
  }

  /**
   * Tests if the exception is thrown with a null deviceTypeID.
   */
  @Test
  void shouldThrowException_whenDeviceTypeIdIsNull() {
    // Arrange
    String deviceTypeIDDescription = null;
    String expectedMessage = "'deviceTypeID' must be a non-empty string.";

    // Act + Assert
    Exception exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class, () ->
            new DeviceTypeID(deviceTypeIDDescription)
    );

    // Assert
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests if the exception is thrown with a blank deviceTypeID.
   */
  @Test
  void shouldThrowException_whenDeviceTypeIdIsBlank() {
    // Arrange
    String deviceTypeIDDescription = " ";
    String expectedMessage = "'deviceTypeID' must be a non-empty string.";

    // Act + Assert
    Exception exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class, () ->
            new DeviceTypeID(deviceTypeIDDescription)
    );

    // Assert
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests if the device type ID is retured correctly.
   */
  @Test
  void shouldReturnDeviceTypeID() {
    // Arrange
    String deviceTypeIDDescription = "Fridge";
    DeviceTypeID deviceTypeID = new DeviceTypeID(deviceTypeIDDescription);

    String expected = "Fridge";

    // Act
    String result = deviceTypeID.getID();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Tests if the euqals method returns true when the device type ID is compared to itself.
   */
  @Test
  void shouldReturnTrue_WhenDeviceTypeIDisEqualToItself() {
    // Arrange
    String deviceTypeIDDescription = "Fridge";
    DeviceTypeID deviceTypeID = new DeviceTypeID(deviceTypeIDDescription);

    // Act
    boolean result = deviceTypeID.equals(deviceTypeID);

    // Assert
    assertTrue(result);
  }

  /**
   * Tests if the equals method returns true when the device type ID is compared to another device
   * type ID with the same ID.
   */
  @Test
  void shouldReturnTrue_WhenDeviceTypeIDIsEqualToOtherDeviceTypeID() {
    // Arrange
    String deviceTypeIDDescription = "Fridge";
    DeviceTypeID deviceTypeID1 = new DeviceTypeID(deviceTypeIDDescription);
    DeviceTypeID deviceTypeID2 = new DeviceTypeID(deviceTypeIDDescription);

    // Act
    boolean result = deviceTypeID1.equals(deviceTypeID2);

    // Assert
    assertTrue(result);
  }

  @Test
  void shouldReturnFalse_WhenDeviceTypeIDIsNotEqualToOtherDeviceTypeID() {
    // Arrange
    String deviceTypeIDDescription1 = "Fridge";
    String deviceTypeIDDescription2 = "Oven";
    DeviceTypeID deviceTypeID1 = new DeviceTypeID(deviceTypeIDDescription1);
    DeviceTypeID deviceTypeID2 = new DeviceTypeID(deviceTypeIDDescription2);

    // Act
    boolean result = deviceTypeID1.equals(deviceTypeID2);

    // Assert
    assertFalse(result);
  }

  /**
   * Tests equals method with a different object.
   */
  @Test
  void shouldReturnFalse_WhenObjectIsDifferent() {
    // Arrange
    String deviceTypeIDDescription = "Fridge";
    DeviceTypeID deviceTypeID = new DeviceTypeID(deviceTypeIDDescription);

    // Act
    boolean result = deviceTypeID.equals(new Object());

    // Assert
    assertFalse(result);
  }

  /**
   * Tests if the toString method returns the device type ID string.
   */

  @Test
  void shouldReturnHashCode() {
    // Arrange
    String deviceTypeIDDescription = "Fridge";

    DeviceTypeID deviceTypeID = new DeviceTypeID(deviceTypeIDDescription);

    int expected = deviceTypeIDDescription.hashCode();

    // Act
    int result = deviceTypeID.hashCode();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Tests if the toString method returns the device type ID string.
   */
  @Test
  void shouldReturnDeviceTypeIDString() {
    // Arrange
    String deviceTypeIDDescription = "Fridge";

    DeviceTypeID deviceTypeID = new DeviceTypeID(deviceTypeIDDescription);

    String expected = "Fridge";

    // Act
    String result = deviceTypeID.toString();

    // Assert
    assertEquals(expected, result);
  }
}
