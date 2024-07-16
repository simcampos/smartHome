/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.mem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.value_object.DeviceTypeID;


/**
 * Test class for DeviceTypeRepository.
 */
class DeviceTypeRepositoryTest {

  /**
   * Tests saving a device type when given a valid device type.
   */
  @Test
  void shouldSaveDeviceType_whenGivenValidDeviceType() {
    //Arrange
    DeviceType deviceType = mock(DeviceType.class);
    DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();

    //Act
    DeviceType savedDeviceType = deviceTypeRepository.save(deviceType);

    //Assert
    assertEquals(deviceType, savedDeviceType);
  }

  /**
   * Tests throwing IllegalArgumentException when given a null device type.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullDeviceType() {
    //Arrange
    DeviceType deviceType = null;
    DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
    String expectedMessage = "DeviceType is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> deviceTypeRepository.save(deviceType));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Tests throwing IllegalArgumentException when the device type already exists.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceTypeAlreadyExists() {
    //Arrange
    DeviceType deviceType = mock(DeviceType.class);
    DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
    deviceTypeRepository.save(deviceType);
    String expectedMessage = "DeviceType already exists.";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> deviceTypeRepository.save(deviceType));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Tests returning all device types when findAll is called.
   */
  @Test
  void shouldReturnAllDeviceTypes_whenFindAllIsCalled() {
    //Arrange
    DeviceType deviceType1 = mock(DeviceType.class);
    DeviceTypeID deviceTypeID1 = mock(DeviceTypeID.class);
    when(deviceType1.getID()).thenReturn(deviceTypeID1);

    DeviceType deviceType2 = mock(DeviceType.class);
    DeviceTypeID deviceTypeID2 = mock(DeviceTypeID.class);
    when(deviceType2.getID()).thenReturn(deviceTypeID2);

    DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();

    deviceTypeRepository.save(deviceType1);
    deviceTypeRepository.save(deviceType2);
    List<DeviceType> expectedList = List.of(deviceType1, deviceType2);

    //Act
    List<DeviceType> allDeviceTypes = deviceTypeRepository.findAll();

    //Assert
    assertEquals(expectedList, allDeviceTypes);
  }

  /**
   * Tests returning an empty list when findAll is called and no device types are saved.
   */
  @Test
  void shouldReturnEmptyList_whenFindAllIsCalledAndNoDeviceTypesSaved() {
    //Arrange
    DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();

    //Act
    List<DeviceType> allDeviceTypes = deviceTypeRepository.findAll();

    //Assert
    assertEquals(0, allDeviceTypes.size());
  }

  /**
   * Tests returning a device type when given a valid device type ID.
   */
  @Test
  void shouldReturnSensorType_whenGivenValidSensorTypeID() {
    //Arrange
    DeviceType deviceType = mock(DeviceType.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);
    when(deviceType.getID()).thenReturn(deviceTypeID);

    DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
    deviceTypeRepository.save(deviceType);

    //Act
    DeviceType foundDeviceType = deviceTypeRepository.ofIdentity(deviceTypeID).get();

    //Assert
    assertEquals(deviceType, foundDeviceType);
  }

  /**
   * Tests returning an empty Optional when given an invalid sensor type ID.
   */
  @Test
  void shouldReturnEmptyOptional_whenGivenInvalidSensorTypeID() {
    //Arrange
    DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();

    DeviceType deviceType = mock(DeviceType.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);
    when(deviceType.getID()).thenReturn(deviceTypeID);

    deviceTypeRepository.save(deviceType);

    DeviceTypeID invalidDeviceTypeID = mock(DeviceTypeID.class);

    //Act
    Optional<DeviceType> foundDeviceType = deviceTypeRepository.ofIdentity(invalidDeviceTypeID);

    //Assert
    assertEquals(Optional.empty(), foundDeviceType);
  }

  /**
   * Tests returning true when given a valid sensor type ID.
   */
  @Test
  void shouldReturnTrue_whenGivenValidSensorTypeID() {
    //Arrange
    DeviceType deviceType = mock(DeviceType.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);
    when(deviceType.getID()).thenReturn(deviceTypeID);

    DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
    deviceTypeRepository.save(deviceType);

    //Act
    boolean containsDeviceType = deviceTypeRepository.containsOfIdentity(deviceTypeID);

    //Assert
    assertTrue(containsDeviceType);
  }

  /**
   * Tests returning false when given an invalid sensor type ID.
   */
  @Test
  void shouldReturnFalse_whenGivenInvalidSensorTypeID() {
    //Arrange
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);
    DeviceTypeID invalidDeviceTypeID = mock(DeviceTypeID.class);
    DeviceType deviceType = mock(DeviceType.class);
    when(deviceType.getID()).thenReturn(deviceTypeID);

    DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
    deviceTypeRepository.save(deviceType);

    //Act
    boolean containsDeviceType = deviceTypeRepository.containsOfIdentity(invalidDeviceTypeID);

    //Assert
    assertFalse(containsDeviceType);
  }
}


