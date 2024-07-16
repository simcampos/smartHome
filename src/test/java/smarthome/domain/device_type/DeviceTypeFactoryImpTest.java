/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.device_type;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.TypeDescription;


class DeviceTypeFactoryImpTest {

  /**
   * Should create device type when create device type is called with valid parameters.
   */
  @Test
  void shouldCreateDeviceType_WhenCreateDeviceTypeIsCalledWithValidParameters() {
    // Arrange
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    when(typeDescriptionDouble.getDescription()).thenReturn("Test");
    DeviceTypeFactoryImpl factory = new DeviceTypeFactoryImpl();

    // Act
    DeviceType deviceType = factory.createDeviceType(typeDescriptionDouble);

    // Assert
    assertNotNull(deviceType);
  }

  /**
   * Should create device type when create device type is called with valid parameters including
   * ID.
   */
  @Test
  void shouldCreateDeviceType_WhenCreateDeviceTypeIsCalledWithValidParametersIncludingID() {
    // Arrange
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);
    DeviceTypeFactoryImpl factory = new DeviceTypeFactoryImpl();

    // Act
    DeviceType deviceType = factory.createDeviceType(deviceTypeIDDouble, typeDescriptionDouble);

    // Assert
    assertNotNull(deviceType);
  }
}
