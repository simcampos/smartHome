/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.utils.dto.DeviceTypeDTO;


class DeviceTypeAssemblerTest {

  /**
   * Test if the domainToDTO method returns a DeviceTypeDTO object when the device type is valid.
   */
  @Test
  void shouldReturnDeviceTypeAssemblerDTO_WhenDeviceTypeIsValid() {
    // Arrange
    String deviceTypeDescription = "Switch Device Description";
    DeviceType deviceType = mock(DeviceType.class);
    when(deviceType.getID()).thenReturn(mock(DeviceTypeID.class));
    when(deviceType.getDescription()).thenReturn(mock(TypeDescription.class));
    when(deviceType.getDescription().toString()).thenReturn(deviceTypeDescription);

    DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();
    // Act
    DeviceTypeDTO deviceTypeDTO = deviceTypeAssembler.domainToDTO(deviceType);
    // Assert
    assertEquals(deviceTypeDescription, deviceTypeDTO.toString());
  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the DeviceType is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDeviceTypeIsNull() {
    // Arrange
    DeviceType deviceType = null;
    DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();

    String expectedMessage = "DeviceType is required";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      deviceTypeAssembler.domainToDTO(deviceType);
    });
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the list of DeviceTypes
   * is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDeviceTypeListIsNull() {
    // Arrange
    List<DeviceType> deviceTypes = null;
    DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();

    String expectedMessage = "The list of DeviceTypes cannot be null.";

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      deviceTypeAssembler.domainToDTO(deviceTypes);
    });
    assertEquals(expectedMessage, exception.getMessage());

  }

  /**
   * Test if the domainToDTO method returns a list of DeviceTypeDTO objects when the list of device
   * types is valid.
   */
  @Test
  void shouldReturnDeviceTypeAssemblerDTO_WhenDeviceTypeListIsValid() {
    // Arrange
    String deviceTypeID = "Switch Device";
    String deviceTypeDescription = "Switch Device Description";

    DeviceType deviceType = mock(DeviceType.class);
    when(deviceType.getID()).thenReturn(mock(DeviceTypeID.class));
    when(deviceType.getID().toString()).thenReturn(deviceTypeID);
    when(deviceType.getDescription()).thenReturn(mock(TypeDescription.class));
    when(deviceType.getDescription().toString()).thenReturn(deviceTypeDescription);

    DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();
    List<DeviceType> deviceTypes = List.of(deviceType);
    DeviceTypeDTO expectedDeviceType = new DeviceTypeDTO(deviceTypeDescription);
    List<DeviceTypeDTO> expected = List.of(expectedDeviceType);

    // Act
    List<DeviceTypeDTO> deviceTypeDTOS = deviceTypeAssembler.domainToDTO(deviceTypes);

    // Assert
    assertEquals(expected.toString(), deviceTypeDTOS.toString());
  }

  /**
   * Should return empty list of DeviceTypeDTO when the list of device types is empty.
   */
  @Test
  void shouldReturnEmptyList_WhenDeviceTypeListIsEmpty() {
    // Arrange
    List<DeviceType> deviceTypes = List.of();
    DeviceTypeAssembler deviceTypeAssembler = new DeviceTypeAssembler();

    // Act
    List<DeviceTypeDTO> deviceTypeDTOS = deviceTypeAssembler.domainToDTO(deviceTypes);

    // Assert
    assertEquals(0, deviceTypeDTOS.size());
  }
}
