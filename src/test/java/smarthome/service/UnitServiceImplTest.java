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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import smarthome.domain.repository.IUnitRepository;
import smarthome.domain.unit.IUnitFactory;
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;

class UnitServiceImplTest {

  /**
   * Test that the constructor of unitTypeService is instantiated correctly.
   */
  @Test
  void shouldInstantiateunitTypeService_WhenConstructorInvoked() {
    // Arrange
    IUnitRepository unitRepositoryDouble = mock(IUnitRepository.class);
    IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

    // Act
    UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepositoryDouble, unitFactoryDouble);

    // Assert
    assertNotNull(unitServiceImpl);
  }

  /**
   * Test that the unitTypeService can throw an IllegalArgumentException when the
   * unitType repository is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenunitTypeRepositoryIsNull() {
    // Arrange
    IUnitRepository unitRepositoryDouble = null;
    IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

    String expectedMessage = "unitType repository is required";

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new UnitServiceImpl(unitRepositoryDouble, unitFactoryDouble));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test that the unitTypeService can throw an IllegalArgumentException when the
   * unitType factory is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenunitTypeFactoryIsNull() {
    // Arrange
    IUnitRepository unitRepositoryDouble = mock(IUnitRepository.class);
    IUnitFactory unitFactoryDouble = null;

    String expectedMessage = "unitType factory is required";

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new UnitServiceImpl(unitRepositoryDouble, unitFactoryDouble));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test that the unitTypeService can create and save a unitType.
   */
  @Test
  void shouldCreateAndSaveunitType_WhenParameterAreValid() {
    // Arrange
    IUnitRepository unitRepositoryDouble = mock(IUnitRepository.class);
    IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

    UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepositoryDouble, unitFactoryDouble);

    UnitDescription description = mock(UnitDescription.class);
    UnitSymbol unit = mock(UnitSymbol.class);
    Unit unitDouble = mock(Unit.class);

    when(description.getDescription()).thenReturn("Temperature");
    when(unit.getUnit()).thenReturn("Celsius");

    when(unitFactoryDouble.createUnit(description, unit)).thenReturn(unitDouble);
    when(unitRepositoryDouble.save(unitDouble)).thenReturn(unitDouble);

    // Act
    Unit unitUnit = unitServiceImpl.addunitType(description, unit);

    // Assert
    assertEquals(unitDouble, unitUnit);
  }

  /**
   * Test that the unitTypeService can throw an IllegalArgumentException when the
   * unitType description is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenunitTypeDescriptionIsNull() {
    // Arrange
    IUnitRepository unitRepositoryDouble = mock(IUnitRepository.class);
    IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

    UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepositoryDouble, unitFactoryDouble);

    UnitDescription description = null;
    UnitSymbol unit = mock(UnitSymbol.class);

    String expectedMessage = "unit type description cannot be null or empty.";

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> unitServiceImpl.addunitType(description, unit));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test that the unitTypeService can throw an IllegalArgumentException when the
   * unitType description is empty.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenunitTypeDescriptionIsEmpty() {
    // Arrange
    IUnitRepository unitRepositoryDouble = mock(IUnitRepository.class);
    IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

    UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepositoryDouble, unitFactoryDouble);

    UnitDescription description = mock(UnitDescription.class);
    UnitSymbol unit = mock(UnitSymbol.class);

    when(description.getDescription()).thenReturn("");

    String expectedMessage = "unit type description cannot be null or empty.";

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> unitServiceImpl.addunitType(description, unit));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test that the unitTypeService can throw an IllegalArgumentException when the
   * unitType description is blank.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenunitTypeDescriptionIsBlank() {
    // Arrange
    IUnitRepository unitRepositoryDouble = mock(IUnitRepository.class);
    IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

    UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepositoryDouble, unitFactoryDouble);

    UnitDescription description = mock(UnitDescription.class);
    UnitSymbol unit = mock(UnitSymbol.class);

    when(description.getDescription()).thenReturn(" ");

    String expectedMessage = "unit type description cannot be null or empty.";

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> unitServiceImpl.addunitType(description, unit));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test that the unitTypeService can throw an IllegalArgumentException when the
   * unitType unit is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenunitTypeUnitIsNull() {
    // Arrange
    IUnitRepository unitRepositoryDouble = mock(IUnitRepository.class);
    IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

    UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepositoryDouble, unitFactoryDouble);

    UnitDescription description = mock(UnitDescription.class);
    when(description.getDescription()).thenReturn("Temperature");
    UnitSymbol unit = null;

    String expectedMessage = "unit type unit cannot be null or empty.";

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> unitServiceImpl.addunitType(description, unit));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test that the unitTypeService can throw an IllegalArgumentException when the
   * unitType unit is empty.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenunitTypeUnitIsEmpty() {
    // Arrange
    IUnitRepository unitRepositoryDouble = mock(IUnitRepository.class);
    IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

    UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepositoryDouble, unitFactoryDouble);

    UnitDescription description = mock(UnitDescription.class);
    when(description.getDescription()).thenReturn("Temperature");
    UnitSymbol unit = mock(UnitSymbol.class);

    when(unit.getUnit()).thenReturn("");

    String expectedMessage = "unit type unit cannot be null or empty.";

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> unitServiceImpl.addunitType(description, unit));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test findunitTypeById method of unitTypeService.
   */
  @Test
  void shouldReturnunitType_WhenFindByIdInvoked() {
    // Arrange
    IUnitRepository unitRepositoryDouble = mock(IUnitRepository.class);
    IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

    UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepositoryDouble, unitFactoryDouble);

    UnitID unitID = mock(UnitID.class);
    when(unitID.toString()).thenReturn("1");
    Unit unitDouble = mock(Unit.class);

    // Wrap the unitTypeDouble in an Optional
    when(unitRepositoryDouble.ofIdentity(unitID)).thenReturn(Optional.of(unitDouble));

    // Act
    Optional<Unit> result = unitServiceImpl.getunitTypeById(unitID);

    // Assert
    assertTrue(result.isPresent()); // Ensure the result is present
    assertEquals(unitDouble, result.get()); // Compare the actual unitType object
  }

  /**
   * Test findunitTypeById method of unitTypeService, when the unitID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenFindByIdWithNullIDInvoked() {
    // Arrange
    IUnitRepository unitRepositoryDouble = mock(IUnitRepository.class);
    IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

    UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepositoryDouble, unitFactoryDouble);

    String expectedMessage = "Please enter a valid sensor type ID.";
    UnitID unitID = null;

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> unitServiceImpl.getunitTypeById(unitID));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }


  /**
   * Test that the unitTypeService can return all unitTypes in the repository. Aims to
   * ensure that the interaction between the service and the repository occurs as expected,ensuring
   * that the data are effectively fetched from the repository. This test is a good example of a
   * test that ensures the correct interaction between the service and the repository.
   */
  @Test
  void shouldReturnAllunitTypes_WhenFindAllunitTypesInvoked() {
    // Arrange
    IUnitRepository unitRepositoryDouble = mock(IUnitRepository.class);
    IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

    UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepositoryDouble, unitFactoryDouble);

    // Act
    List<Unit> result = unitServiceImpl.getAllunitTypes();

    // Assert
    assertEquals(result, unitRepositoryDouble.findAll());
  }

  /**
   * Test that the unitTypeService can return a non-empty list when unit types are
   * available. Focuses on ensuring that the service correctly handles the data received from the
   * repository, especially in scenarios where data exists to be returned. This test is a good
   * example of a test that ensures the correct handling of data by the service.
   */
  @Test
  void shouldNotReturnEmptyList_WhenFindAllunitTypesIsCalledWithAvailableTypes() {
    // Arrange
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    IUnitFactory unitFactory = mock(IUnitFactory.class);
    UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);
    Unit unit = mock(Unit.class);
    List<Unit> availableTypes = Collections.singletonList(unit);

    when(unitRepository.findAll()).thenReturn(availableTypes);

    // Act
    List<Unit> result = unitServiceImpl.getAllunitTypes();

    // Assert
    assertFalse(result.isEmpty());
  }

}