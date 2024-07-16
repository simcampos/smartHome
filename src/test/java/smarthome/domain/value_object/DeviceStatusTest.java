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

class DeviceStatusTest {

  /**
   * Tests the DeviceStatus constructor with a true device status.
   */
  @Test
  void shouldGetValidObject_whenUsingDeviceStatusAsTrue() {
    // Arrange
    boolean validDeviceStatus = true;

    // Act
    DeviceStatus deviceStatus = new DeviceStatus(validDeviceStatus);

    // Assert
    assertNotNull(deviceStatus);
  }

  /**
   * Tests the DeviceStatus constructor with a false device status.
   */
  @Test
  void shouldGetValidObject_whenUsingDeviceStatusAsFalse() {
    // Arrange
    boolean validDeviceStatus = false;

    // Act
    DeviceStatus result = new DeviceStatus(validDeviceStatus);

    // Assert
    assertNotNull(result);
  }

  /**
   * Tests the equals method with the same entry
   */
  @Test
  void shouldReturnTrue_WhenStatusIsTheSameAsTrue() {
    // Arrange
    boolean status = true;
    DeviceStatus deviceStatus = new DeviceStatus(status);
    // Act
    boolean result = deviceStatus.equals(deviceStatus);
    // Assert
    assertTrue(result);
  }

  /**
   * Tests the equals method with two different DeviceStatus objects with same value
   */
  @Test
  void shouldReturnTrue_WhenStatusIsTheSame() {
    // Arrange
    boolean status = true;
    DeviceStatus deviceStatus = new DeviceStatus(status);
    DeviceStatus deviceStatus2 = new DeviceStatus(status);
    // Act
    boolean result = deviceStatus.equals(deviceStatus2);
    // Assert
    assertTrue(result);
  }

  /**
   * Tests the equals method with a different entry
   */
  @Test
  void shouldReturnFalse_WhenStatusIsDifferent() {
    // Arrange
    boolean status = true;
    DeviceStatus deviceStatus = new DeviceStatus(status);
    DeviceStatus deviceStatus2 = new DeviceStatus(false);
    // Act
    boolean result = deviceStatus.equals(deviceStatus2);
    // Assert
    assertFalse(result);
  }

  /**
   * Tests toString method when status is true
   */
  @Test
  void shouldReturnON_whenStatusIsTrue() {
    // Arrange
    boolean status = true;
    DeviceStatus deviceStatus = new DeviceStatus(status);
    // Act
    String result = deviceStatus.toString();
    // Assert
    assertEquals("ON", result);
  }

  /**
   * Tests toString method when status is false
   */
  @Test
  void shouldReturnOFF_whenStatusIsFalse() {
    // Arrange
    boolean status = false;
    DeviceStatus deviceStatus = new DeviceStatus(status);
    // Act
    String result = deviceStatus.toString();
    // Assert
    assertEquals("OFF", result);
  }

  /**
   * Tests the hashCode method
   */
  @Test
  void shouldReturnHashCode_whenUsingHashCodeMethod() {
    // Arrange
    boolean status = true;
    DeviceStatus deviceStatus = new DeviceStatus(status);
    // Act
    int result = deviceStatus.hashCode();
    // Assert
    assertEquals(Boolean.hashCode(status), result);
  }

  /**
   * Tests the getter method when status is true
   */
  @Test
  void shouldReturnTrue_whenUsingGetterMethod() {
    // Arrange
    boolean status = true;
    DeviceStatus deviceStatus = new DeviceStatus(status);
    // Act
    boolean result = deviceStatus.getStatus();
    // Assert
    assertEquals(status, result);
  }

  /**
   * Tests the getter method when status is false
   */
  @Test
  void shouldReturnFalse_whenUsingGetterMethod() {
    // Arrange
    boolean status = false;
    DeviceStatus deviceStatus = new DeviceStatus(status);
    // Act
    boolean result = deviceStatus.getStatus();
    // Assert
    assertEquals(status, result);
  }

  /**
   * Tests if equals method returns false when the object is different
   */
  @Test
  void shouldReturnFalse_whenObjectIsDifferent() {
    // Arrange
    boolean status = true;
    DeviceStatus deviceStatus = new DeviceStatus(status);

    // Act
    boolean result = deviceStatus.equals(new Object());

    // Assert
    assertFalse(result);
  }
}