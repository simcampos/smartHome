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
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.persistence.data_model.DeviceTypeDataModel;

class DeviceTypeDataModelTest {

  /**
   * Test to check if the DeviceTypeDataModel is instantiated
   */
  @Test
  void shouldInstantiateDeviceTypeDataModelNoArguments() {
    // Act
    DeviceTypeDataModel thisModel = new DeviceTypeDataModel();
    // Assert
    assertNotNull(thisModel);
  }

  /**
   * Test to check if the DeviceTypeDataModel is instantiated
   */
  @Test
  void shouldInstantiateDeviceTypeDataModel() {
    // Arrange
    String strDeviceTypeID = "123";
    String strDeviceTypeDescription = "Light";

    DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    DeviceType deviceTypeDouble = mock(DeviceType.class);

    when(deviceTypeIDDouble.getID()).thenReturn(strDeviceTypeID);
    when(typeDescriptionDouble.getDescription()).thenReturn(strDeviceTypeDescription);

    when(deviceTypeDouble.getID()).thenReturn(deviceTypeIDDouble);
    when(deviceTypeDouble.getDescription()).thenReturn(typeDescriptionDouble);

    // Act
    DeviceTypeDataModel deviceTypeDataModel = new DeviceTypeDataModel(deviceTypeDouble);

    // Assert
    assertNotNull(deviceTypeDataModel);
  }

  /**
   * Test to check if IllegalArgumentException is thrown when DeviceType is null
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenDeviceTypeIsNull() {
    // Arrange
    DeviceType deviceTypeDouble = null;

    String expectedMessage = "Device Type is required";

    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class, () -> new DeviceTypeDataModel(deviceTypeDouble));

    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Test to check if the DeviceTypeID is returned when getDeviceTypeID is called
   */
  @Test
  void shouldReturnDeviceTypeID_WhenGetDeviceTypeID() {
    // Arrange
    String strDeviceTypeID = "123";
    String strDeviceTypeDescription = "Light";

    DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    DeviceType deviceTypeDouble = mock(DeviceType.class);

    when(deviceTypeIDDouble.getID()).thenReturn(strDeviceTypeID);
    when(typeDescriptionDouble.getDescription()).thenReturn(strDeviceTypeDescription);

    when(deviceTypeDouble.getID()).thenReturn(deviceTypeIDDouble);
    when(deviceTypeDouble.getDescription()).thenReturn(typeDescriptionDouble);

    DeviceTypeDataModel deviceTypeDataModel = new DeviceTypeDataModel(deviceTypeDouble);

    // Act
    String actualDeviceTypeID = deviceTypeDataModel.getDeviceTypeID();

    // Assert
    assertEquals(strDeviceTypeID, actualDeviceTypeID);
  }

  /**
   * Test to check if the DeviceTypeDescription is returned when getDeviceTypeDescription is called
   */
  @Test
  void shouldReturnDeviceTypeDescription_WhenGetDeviceTypeDescription() {
    // Arrange
    String strDeviceTypeID = "123";
    String strDeviceTypeDescription = "Light";

    DeviceTypeID deviceTypeIDDouble = mock(DeviceTypeID.class);
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    DeviceType deviceTypeDouble = mock(DeviceType.class);

    when(deviceTypeIDDouble.getID()).thenReturn(strDeviceTypeID);
    when(typeDescriptionDouble.getDescription()).thenReturn(strDeviceTypeDescription);

    when(deviceTypeDouble.getID()).thenReturn(deviceTypeIDDouble);
    when(deviceTypeDouble.getDescription()).thenReturn(typeDescriptionDouble);

    DeviceTypeDataModel deviceTypeDataModel = new DeviceTypeDataModel(deviceTypeDouble);

    // Act
    String actualDeviceTypeDescription = deviceTypeDataModel.getDeviceTypeDescription();

    // Assert
    assertEquals(strDeviceTypeDescription, actualDeviceTypeDescription);
  }
}
