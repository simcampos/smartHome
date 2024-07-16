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
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import smarthome.domain.device_type.IDeviceTypeFactory;

class DeviceTypeDataModelAssemblerTest {

  /**
   * Test to check if the constructor throws an exception when the device type factory is null
   */
  @Test
  void shouldThrowException_whenDeviceTypeFactoryIsNull() {
    // Arrange
    IDeviceTypeFactory deviceTypeFactory = null;

    String expected = "Device Type Factory is required";

    // Act
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new DeviceTypeDataModelAssembler(deviceTypeFactory));

    // Assert
    String actual = exception.getMessage();

    assertEquals(expected, actual);
  }

  /**
   * Test to check if the constructor instantiates the DeviceTypeDataModelAssembler when the device
   * type factory is valid
   */
  @Test
  void shouldInstantiateDeviceTypeDataModelAssembler_whenDeviceTypeFactoryIsValid() {
    // Arrange
    IDeviceTypeFactory deviceTypeFactory = mock(IDeviceTypeFactory.class);

    // Act
    DeviceTypeDataModelAssembler deviceTypeDataModelAssembler =
        new DeviceTypeDataModelAssembler(deviceTypeFactory);

    // Assert
    assertNotNull(deviceTypeDataModelAssembler);
  }
}
