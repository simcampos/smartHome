/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.actuator_type.ActuatorTypeFactoryImpl;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.persistence.mem.ActuatorTypeRepository;
import smarthome.persistence.mem.UnitRepository;

class ActuatorTypeServiceImplTest {

  /**
   * Tests the instantiation of ActuatorTypeService with valid parameters.
   */
  @Test
  void shouldInstantiateActuatorTypeService_WhenParametersAreValid() {
    // Arrange
    ActuatorTypeRepository actuatorTypeRepository = mock(ActuatorTypeRepository.class);
    ActuatorTypeFactoryImpl actuatorTypeFactory = mock(ActuatorTypeFactoryImpl.class);
    UnitRepository unitRepository = mock(UnitRepository.class);

    // Act
    ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
        new ActuatorTypeServiceImpl(actuatorTypeRepository, actuatorTypeFactory, unitRepository);

    // Assert
    assertNotNull(ActuatorTypeServiceImpl);
  }

  /**
   * Test that the ActuatorTypeService can throw an IllegalArgumentException when the ActuatorType
   * repository is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenActuatorTypeRepositoryIsNull() {
    // Arrange
    ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
    UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

    String expectedMessage = "Actuator type repository is required";

    // Act
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new ActuatorTypeServiceImpl(null, actuatorTypeFactoryDouble, unitRepositoryDouble));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test that the ActuatorTypeService can throw an IllegalArgumentException when the ActuatorType
   * factory is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenActuatorTypeFactoryIsNull() {
    // Arrange
    ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
    UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

    String expectedMessage = "Actuator type factory is required";

    // Act
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new ActuatorTypeServiceImpl(actuatorTypeRepositoryDouble, null,
            unitRepositoryDouble));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test that the ActuatorTypeService can throw an IllegalArgumentException when the Unit
   * repository is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenUnitRepositoryIsNull() {
    // Arrange
    ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
    ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);

    String expectedMessage = "Unit repository is required";
    // Act
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new ActuatorTypeServiceImpl(actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble,
            null));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }


  /**
   * Test that the ActuatorTypeService can return the ActuatorType when the ActuatorType is created
   * and saved to the repository.
   */
  @Test
  void shouldReturnTheActuatorType_whenActuatorTypeIsCreatedAndSavedToRepository() {
    // Arrange
    ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
    TypeDescription actuatorTypeName = mock(TypeDescription.class);
    UnitID unitID = mock(UnitID.class);

    ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
    when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
        .thenReturn(actuatorTypeDouble);

    ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
    UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

    ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
        new ActuatorTypeServiceImpl(
            actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);
    when(unitRepositoryDouble.containsOfIdentity(unitID)).thenReturn(true);

    // Act
    ActuatorType actuatorType = ActuatorTypeServiceImpl.createActuatorType(actuatorTypeName,
        unitID);

    // Assert
    assertEquals(actuatorType, actuatorTypeDouble);
  }

  /**
   * Test that the ActuatorTypeService can throw an IllegalArgumentException when the ActuatorType
   * name is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenActuatorTypeNameIsNull() {
    // Arrange
    TypeDescription actuatorTypeName = null;
    UnitID unitID = mock(UnitID.class);

    ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
    ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
    UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

    ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
        new ActuatorTypeServiceImpl(
            actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);

    // Act + Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> ActuatorTypeServiceImpl.createActuatorType(actuatorTypeName, unitID));
  }

  /**
   * Test that the ActuatorTypeService can throw an IllegalArgumentException when the ActuatorType
   * already exists.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenActuatorTypeAlreadyExists() {
    // Arrange
    ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
    TypeDescription actuatorTypeName = mock(TypeDescription.class);
    UnitID unitID = mock(UnitID.class);

    ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
    when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
        .thenReturn(actuatorTypeDouble);

    ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
    when(actuatorTypeRepositoryDouble.existsOfName(actuatorTypeName)).thenReturn(true);

    UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

    ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
        new ActuatorTypeServiceImpl(
            actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);

    // Act + Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> ActuatorTypeServiceImpl.createActuatorType(actuatorTypeName, unitID));
  }

  /**
   * Test save method
   */
  @Test
  void shouldReturnTheActuatorType_whenActuatorTypeIsSavedToRepository() {
    // Arrange
    ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
    TypeDescription actuatorTypeName = mock(TypeDescription.class);
    UnitID unitID = mock(UnitID.class);

    ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
    when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
        .thenReturn(actuatorTypeDouble);

    ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
    UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

    ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
        new ActuatorTypeServiceImpl(
            actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);
    when(actuatorTypeRepositoryDouble.save(actuatorTypeDouble)).thenReturn(actuatorTypeDouble);

    // Act
    ActuatorType actuatorType = ActuatorTypeServiceImpl.addActuatorType(actuatorTypeDouble);

    // Assert
    assertEquals(actuatorTypeDouble, actuatorType);
  }

  /**
   * Test find all actuator types
   */
  @Test
  void shouldReturnAllActuatorTypes_whenFindAllActuatorTypes() {
    // Arrange
    ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
    TypeDescription actuatorTypeName = mock(TypeDescription.class);
    UnitID unitID = mock(UnitID.class);

    ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
    when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
        .thenReturn(actuatorTypeDouble);

    ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
    UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

    ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
        new ActuatorTypeServiceImpl(
            actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);
    when(actuatorTypeRepositoryDouble.findAll()).thenReturn(List.of(actuatorTypeDouble));

    // Act
    List<ActuatorType> actuatorTypes = ActuatorTypeServiceImpl.getAllActuatorTypes();

    // Assert
    assertEquals(List.of(actuatorTypeDouble), actuatorTypes);
  }

  /**
   * Test find actuator by typeId
   */
  @Test
  void shouldReturnActuatorType_whenFindActuatorTypeByTypeId() {
    // Arrange
    ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
    TypeDescription actuatorTypeName = mock(TypeDescription.class);
    UnitID unitID = mock(UnitID.class);

    ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
    when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
        .thenReturn(actuatorTypeDouble);

    ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
    UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("1");

    ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
        new ActuatorTypeServiceImpl(
            actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);
    when(actuatorTypeRepositoryDouble.ofIdentity(actuatorTypeID)).thenReturn(
        Optional.of(actuatorTypeDouble));

    // Act
    Optional<ActuatorType> actuatorType = ActuatorTypeServiceImpl.getActuatorTypeByID(
        actuatorTypeID);

    // Assert
    assertEquals(Optional.of(actuatorTypeDouble), actuatorType);
  }

  /**
   * Test that the ActuatorTypeService can throw an IllegalArgumentException when the ActuatorType
   */
  @Test
  void shouldThrowException_whenActuatorTypeIDIsNull() {
    // Arrange
    ActuatorType actuatorTypeDouble = mock(ActuatorType.class);
    TypeDescription actuatorTypeName = mock(TypeDescription.class);
    UnitID unitID = mock(UnitID.class);

    ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
    when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
        .thenReturn(actuatorTypeDouble);

    ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
    UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

    ActuatorTypeID actuatorTypeID = null;

    String expectedMessage = "Please enter a valid actuator type ID.";

    ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
        new ActuatorTypeServiceImpl(
            actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);

    // Act + Assert
    Exception exception = assertThrows(
        IllegalArgumentException.class,
        () -> ActuatorTypeServiceImpl.getActuatorTypeByID(actuatorTypeID));

    String actualMessage = exception.getMessage();

    // Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Tests if IllegalArgumentException is thrown when the ActuatorType is null.
   */
  @Test
  void shouldThrowException_whenActuatorTypeIsNull() {
    // Arrange
    ActuatorType actuatorTypeDouble = null;
    TypeDescription actuatorTypeName = mock(TypeDescription.class);
    UnitID unitID = mock(UnitID.class);

    ActuatorTypeFactoryImpl actuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
    when(actuatorTypeFactoryDouble.createActuatorType(actuatorTypeName, unitID))
        .thenReturn(actuatorTypeDouble);

    ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
    UnitRepository unitRepositoryDouble = mock(UnitRepository.class);

    String expectedMessage = "Please enter a valid actuator type.";

    ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
        new ActuatorTypeServiceImpl(
            actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, unitRepositoryDouble);

    // Act + Assert
    Exception exception = assertThrows(
        IllegalArgumentException.class,
        () -> ActuatorTypeServiceImpl.addActuatorType(actuatorTypeDouble));

    String actualMessage = exception.getMessage();

    // Assert
    assertEquals(expectedMessage, actualMessage);
  }
}
