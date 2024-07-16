/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.device_type.IDeviceTypeFactory;
import smarthome.domain.repository.IDeviceTypeRepository;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.TypeDescription;


class DeviceTypeServiceImplTest {

  /**
   * Test for the DeviceTypeService constructor.
   */
  @Test
  void shouldInstantiateDeviceTypeService_whenGivenValidParameters() {
    //Arrange
    IDeviceTypeRepository deviceTypeRepository = mock(IDeviceTypeRepository.class);
    IDeviceTypeFactory deviceTypeFactory = mock(IDeviceTypeFactory.class);

    //Act
    DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository,
        deviceTypeFactory);

    //Assert
    assertNotNull(deviceTypeServiceImpl);
  }

  /**
   * Test for the DeviceTypeService constructor when the DeviceTypeRepository is null.
   */

  @Test
  void shouldThrowException_whenDeviceTypeRepositoryIsNull() {
    //Arrange
    IDeviceTypeRepository deviceTypeRepository = null;
    IDeviceTypeFactory deviceTypeFactory = mock(IDeviceTypeFactory.class);
    String expectedMessage = "Device type repository is required";

    //Act
    IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
      new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);
    });

    //Assert
    assertEquals(thrown.getMessage(), expectedMessage);
  }

  /**
   * Test for the DeviceTypeService constructor when the DeviceTypeFactory is null.
   */
  @Test
  void shouldThrowException_whenDeviceTypeFactoryIsNull() {
    //Arrange
    IDeviceTypeRepository deviceTypeRepository = mock(IDeviceTypeRepository.class);
    IDeviceTypeFactory deviceTypeFactory = null;
    String expectedMessage = "Device type factory is required";

    //Act
    IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
      new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);
    });

    //Assert
    assertEquals(thrown.getMessage(), expectedMessage);
  }

  /**
   * Test for the addDeviceType method when the device type is created and saved to the repository.
   */
  @Test
  void shouldReturnTheDeviceType_whenDeviceTypeIsCreatedAndSavedToRepository() {
    //Arrange
    TypeDescription typeDescription = mock(TypeDescription.class);
    DeviceType deviceType = mock(DeviceType.class);

    IDeviceTypeFactory deviceTypeFactory = mock(IDeviceTypeFactory.class);
    when(deviceTypeFactory.createDeviceType(typeDescription)).thenReturn(deviceType);

    IDeviceTypeRepository deviceTypeRepository = mock(IDeviceTypeRepository.class);
    when(deviceTypeRepository.save(deviceType)).thenReturn(deviceType);

    DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository,
        deviceTypeFactory);

    //Act
    DeviceType resultDeviceType = deviceTypeServiceImpl.addDeviceType(typeDescription);

    //Assert
    assertEquals(deviceType, resultDeviceType);
  }

  /**
   * Test for the addDeviceType method when the device type is null.
   */
  @Test
  void shouldThrowException_whenDeviceTypeIsNull() {
    //Arrange
    IDeviceTypeRepository deviceTypeRepository = mock(IDeviceTypeRepository.class);
    IDeviceTypeFactory deviceTypeFactory = mock(IDeviceTypeFactory.class);
    DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository,
        deviceTypeFactory);
    TypeDescription typeDescription = null;
    String expectedMessage = "Device type is required";

    //Act
    IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
      deviceTypeServiceImpl.addDeviceType(typeDescription);
    });

    //Assert
    assertEquals(thrown.getMessage(), expectedMessage);
  }

  /**
   * Test for the getDeviceTypeByID method when a device typeID exists in the repository.
   */
  @Test
  void shouldFindDeviceTypeByID_whenDeviceTypeExistInRepository() {
    //Arrange
    DeviceType deviceType = mock(DeviceType.class);
    DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);
    IDeviceTypeRepository deviceTypeRepository = mock(IDeviceTypeRepository.class);
    when(deviceTypeRepository.ofIdentity(deviceTypeID)).thenReturn(Optional.of(deviceType));

    IDeviceTypeFactory deviceTypeFactory = mock(IDeviceTypeFactory.class);
    DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository,
        deviceTypeFactory);

    //Act
    DeviceType resultDeviceType = deviceTypeServiceImpl.getDeviceTypeByID(deviceTypeID).get();

    //Assert
    assertEquals(deviceType, resultDeviceType);
  }

  /**
   * Test for the getDeviceTypeByID method when a device type ID does not exist in the repository.
   */
  @Test
  void shouldThrowException_WhenFindingDeviceTypeByNullID() {
    //Arrange
    IDeviceTypeRepository deviceTypeRepository = mock(IDeviceTypeRepository.class);
    IDeviceTypeFactory deviceTypeFactory = mock(IDeviceTypeFactory.class);
    DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository,
        deviceTypeFactory);
    DeviceTypeID deviceTypeID = null;
    String expectedMessage = "Please enter a valid device type ID.";

    //Act
    IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
      deviceTypeServiceImpl.getDeviceTypeByID(deviceTypeID);
    });

    //Assert
    assertEquals(thrown.getMessage(), expectedMessage);
  }

  /**
   * Test for the findAll method when there are device types in the repository.
   */
  @Test
  void shouldReturnAllDeviceTypes_whenFindingAllDeviceTypes() {
    //Arrange
    IDeviceTypeRepository deviceTypeRepository = mock(IDeviceTypeRepository.class);
    IDeviceTypeFactory deviceTypeFactory = mock(IDeviceTypeFactory.class);
    DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository,
        deviceTypeFactory);

    List<DeviceType> deviceTypes = deviceTypeRepository.findAll();

    //Act
    List<DeviceType> resultList = deviceTypeServiceImpl.getAllDeviceTypes();

    //Assert
    assertEquals(deviceTypes, resultList);
  }


  /**
   * Test for the findAll method when there are no device types in the repository.
   */
  @Test
  void shouldReturnEmptyList_WhenNoDeviceTypesExist() {
    //Arrange
    IDeviceTypeRepository deviceTypeRepository = mock(IDeviceTypeRepository.class);
    IDeviceTypeFactory deviceTypeFactory = mock(IDeviceTypeFactory.class);
    DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository,
        deviceTypeFactory);

    DeviceType deviceType = mock(DeviceType.class);
    List<DeviceType> deviceTypes = List.of(deviceType);
    when(deviceTypeRepository.findAll()).thenReturn(deviceTypes);

    //Act
    List<DeviceType> resultList = deviceTypeServiceImpl.getAllDeviceTypes();

    //Assert
    assertFalse(resultList.isEmpty());
  }

}
