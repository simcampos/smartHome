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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.unit.Unit;
import smarthome.domain.unit.UnitFactoryImpl;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.persistence.data_model.UnitDataModel;

class UnitDataModelAssemblerTest {

  /**
   * Test Instantiation of UnitDataModelAssembler when UnitFactory is valid
   */
  @Test
  void shouldInstantiateUnitDataModelAssembler_whenUnitFactoryIsValid() {

    // Arrange
    UnitFactoryImpl unitFactory = mock(UnitFactoryImpl.class);

    // Act
    UnitDataModelAssembler unitDataModelAssembler = new UnitDataModelAssembler(unitFactory);

    // Assert
    assertNotNull(unitDataModelAssembler);
  }

  /**
   * Test Instantiation of UnitDataModelAssembler when UnitFactory is null
   */
  @Test
  void shouldThrowIllegalArgumentException_whenUnitFactoryIsNull() {

    // Arrange
    UnitFactoryImpl unitFactory = null;
    String expectedMessage = "UnitFactory cannot be null.";

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new UnitDataModelAssembler(unitFactory));

    // Assert
    String actualMessage = exception.getMessage();
    assertNotNull(expectedMessage, actualMessage);
  }

  /**
   * Test toDomain method of UnitDataModelAssembler when given valid UnitDataModel
   */
  @Test
  void shouldReturnUnit_whenGivenValidUnitDataModel() {

    // Arrange
    UnitFactoryImpl unitFactoryDouble = mock(UnitFactoryImpl.class);
    UnitDataModelAssembler unitDataModelAssembler = new UnitDataModelAssembler(unitFactoryDouble);
    UnitDataModel unitDataModelDouble = mock(UnitDataModel.class);
    when(unitDataModelDouble.getUnitID()).thenReturn("1L");
    when(unitDataModelDouble.getUnitSymbol()).thenReturn("m");
    when(unitDataModelDouble.getUnitDescription()).thenReturn("meter");

    UnitID unitIDDouble = mock(UnitID.class);
    when(unitIDDouble.getID()).thenReturn("1L");

    UnitSymbol unitSymbolDouble = mock(UnitSymbol.class);
    when(unitSymbolDouble.getSymbol()).thenReturn("m");

    UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
    when(unitDescriptionDouble.getDescription()).thenReturn("meter");

    Unit expected = unitFactoryDouble.createUnit(unitDescriptionDouble, unitSymbolDouble);

    // Act
    Unit unit = unitDataModelAssembler.toDomain(unitDataModelDouble);

    // Assert
    assertEquals(expected, unit);
  }

  /**
   * Test toDomain method of UnitDataModelAssembler when given null UnitDataModel
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullUnitDataModel() {

    // Arrange
    UnitFactoryImpl unitFactoryDouble = mock(UnitFactoryImpl.class);
    UnitDataModelAssembler unitDataModelAssembler = new UnitDataModelAssembler(unitFactoryDouble);
    UnitDataModel unitDataModelDouble = null;
    String expectedMessage = "Unit Data Model is required";

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> unitDataModelAssembler.toDomain(unitDataModelDouble));

    // Assert
    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test toDomain method of UnitDataModelAssembler when given list of UnitDataModels
   */
  @Test
  void shouldReturnListOfUnits_whenGivenListOfUnitDataModels() {

    // Arrange
    UnitFactoryImpl unitFactoryDouble = mock(UnitFactoryImpl.class);
    when(
        unitFactoryDouble.createUnit(any(UnitDescription.class), any(UnitSymbol.class))).thenReturn(
        mock(Unit.class));

    UnitDataModelAssembler unitDataModelAssembler = new UnitDataModelAssembler(unitFactoryDouble);

    UnitID unitIDDouble = mock(UnitID.class);
    when(unitIDDouble.getID()).thenReturn("1L");

    UnitSymbol unitSymbolDouble = mock(UnitSymbol.class);
    when(unitSymbolDouble.getSymbol()).thenReturn("m");

    UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
    when(unitDescriptionDouble.getDescription()).thenReturn("meter");

    UnitDataModel unitDataModelDouble = mock(UnitDataModel.class);
    when(unitDataModelDouble.getUnitID()).thenReturn("1L");
    when(unitDataModelDouble.getUnitSymbol()).thenReturn("m");
    when(unitDataModelDouble.getUnitDescription()).thenReturn("meter");

    List<UnitDataModel> unitDataModels = List.of(unitDataModelDouble);

    Unit expected = unitFactoryDouble.createUnit(unitDescriptionDouble, unitSymbolDouble,
        unitIDDouble);

    // Act
    List<Unit> units = unitDataModelAssembler.toDomain(unitDataModels);

    // Assert
    assertEquals(expected, units.get(0));
  }

}