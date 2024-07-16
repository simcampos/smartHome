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
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.UnitID;

class UnitRepositoryTest {

  /**
   * Test of save method when given valid unitType.
   */
  @Test
  void shouldSaveSensorType_whenGivenValidunitType() {
    //Arrange
    Unit Unit = mock(Unit.class);

    UnitRepository unitRepository = new UnitRepository();

    //Act
    Unit savedUnit = unitRepository.save(Unit);

    //Assert
    assertEquals(Unit, savedUnit);
  }

  /**
   * Test of save method when given null unitType.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullunitType() {
    //Arrange
    Unit unit = null;
    UnitRepository unitRepository = new UnitRepository();
    String expectedMessage = "unitType is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> unitRepository.save(unit));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test of save method when unitType already exists.
   */
  @Test
  void shouldThrowException_whenunitTypeAlreadyExists() {
    //Arrange
    Unit unit = mock(Unit.class);

    UnitRepository unitRepository = new UnitRepository();

    unitRepository.save(unit);
    String expectedMessage = "unitType already exists.";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> unitRepository.save(unit));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test of findAll method when there are unitTypes saved.
   */
  @Test
  void shouldReturnAllunitTypes_whenFindAllIsCalled() {
    //Arrange
    Unit unit1 = mock(Unit.class);
    UnitID unitID1 = mock(UnitID.class);
    when(unit1.getID()).thenReturn(unitID1);

    Unit unit2 = mock(Unit.class);
    UnitID unitID2 = mock(UnitID.class);
    when(unit2.getID()).thenReturn(unitID2);

    UnitRepository unitRepository = new UnitRepository();

    unitRepository.save(unit1);
    unitRepository.save(unit2);
    List<Unit> expectedList = List.of(unit1, unit2);

    //Act
    List<Unit> allUnits = unitRepository.findAll();

    //Assert
    assertEquals(expectedList, allUnits);
  }

  /**
   * Test of findAll method when there are no unitTypes saved.
   */
  @Test
  void shouldReturnEmptyList_whenNounitTypesAreSaved() {
    //Arrange
    UnitRepository unitRepository = new UnitRepository();

    //Act
    List<Unit> allUnits = unitRepository.findAll();

    //Assert
    assertTrue(allUnits.isEmpty());
  }

  /**
   * Test of ofIdentity method when given valid ID.
   */
  @Test
  void shouldReturnunitType_whenGivenValidID() {
    //Arrange
    Unit unit = mock(Unit.class);
    UnitID unitTypeID = mock(UnitID.class);
    when(unit.getID()).thenReturn(unitTypeID);

    UnitRepository unitRepository = new UnitRepository();
    unitRepository.save(unit);

    //Act
    Unit returnedUnit = unitRepository.ofIdentity(unitTypeID).get();

    //Assert
    assertEquals(unit, returnedUnit);
  }

  /**
   * Test of ofIdentity method when given invalid ID.
   */
  @Test
  void shouldReturnOptionalEmpty_whenGivenInvalidID() {
    //Arrange
    UnitRepository unitRepository = new UnitRepository();

    Unit unit = mock(Unit.class);
    UnitID unitTypeID = mock(UnitID.class);
    when(unit.getID()).thenReturn(unitTypeID);

    unitRepository.save(unit);

    UnitID nonExistentID = mock(UnitID.class);

    //Act
    Optional<Unit> returnedunitType = unitRepository.ofIdentity(nonExistentID);

    //Assert
    assertTrue(returnedunitType.isEmpty());
  }

  /**
   * Test of containsOfIdentity method when given valid ID.
   */
  @Test
  void shouldReturnTrue_whenGivenValidID() {
    //Arrange
    Unit unit = mock(Unit.class);
    UnitID unitTypeID = mock(UnitID.class);
    when(unit.getID()).thenReturn(unitTypeID);

    UnitRepository unitRepository = new UnitRepository();
    unitRepository.save(unit);

    //Act
    boolean containsunitType = unitRepository.containsOfIdentity(unitTypeID);

    //Assert
    assertTrue(containsunitType);
  }

  /**
   * Test of containsOfIdentity method when given invalid ID.
   */
  @Test
  void shouldReturnFalse_whenGivenInvalidID() {
    //Arrange
    UnitRepository unitRepository = new UnitRepository();

    Unit unit = mock(Unit.class);
    UnitID unitTypeID = mock(UnitID.class);
    when(unit.getID()).thenReturn(unitTypeID);

    unitRepository.save(unit);

    UnitID nonExistentID = mock(UnitID.class);

    //Act
    boolean containsunitType = unitRepository.containsOfIdentity(nonExistentID);

    //Assert
    assertFalse(containsunitType);
  }

}
