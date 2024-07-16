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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.persistence.data_model.UnitDataModel;

class UnitDataModelTest {

  /**
   * Test Instance of UnitDataModel when empty constructor is called
   */
  @Test
  void shouldInstantiateUnitDataModel_whenEmptyConstructorIsCalled() {
    // Arrange
    UnitDataModel unitDataModel = new UnitDataModel();

    // Act
    assertNotNull(unitDataModel);
  }

  /**
   * Test Instance of UnitDataModel when Unit is valid
   */
  @Test
  void shouldInstantiateUnitDataModel_whenUnitIsValid() {
    // Arrange
    Unit unit = mock(Unit.class);
    when(unit.getID()).thenReturn(mock(UnitID.class));
    when(unit.getUnitSymbol()).thenReturn(mock(UnitSymbol.class));
    when(unit.getUnitDescription()).thenReturn(mock(UnitDescription.class));

    // Act
    UnitDataModel unitDataModel = new UnitDataModel(unit);

    // Assert
    assertNotNull(unitDataModel);
  }

  /**
   * Test Instance of UnitDataModel when Unit is null
   */
  @Test
  void shouldThrowException_whenUnitIsNull() {
    // Arrange
    Unit unit = null;

    String expectedMessage = "Unit is required";

    // Act
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new UnitDataModel(unit));

    // Assert
    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test returns UnitID when getUnitID is called
   */
  @Test
  void shouldReturnUnitID_whenGetUnitIDIsCalled() {
    // Arrange
    Unit unit = mock(Unit.class);
    UnitID unitID = mock(UnitID.class);
    when(unitID.getID()).thenReturn("1L");
    when(unit.getID()).thenReturn(unitID);
    when(unit.getUnitDescription()).thenReturn(mock(UnitDescription.class));
    when(unit.getUnitSymbol()).thenReturn(mock(UnitSymbol.class));

    UnitDataModel unitDataModel = new UnitDataModel(unit);

    // Act
    String ActualUnitID = unitDataModel.getUnitID();

    // Assert
    assertEquals(unitID.getID(), ActualUnitID);
  }

  /**
   * Test returns UnitSymbol when getUnitSymbol is called
   */
  @Test
  void shouldReturnUnitSymbol_whenGetUnitSymbolIsCalled() {
    // Arrange
    Unit unit = mock(Unit.class);
    UnitSymbol unitSymbolDouble = mock(UnitSymbol.class);
    when(unitSymbolDouble.getUnit()).thenReturn("UnitSymbol");
    when(unit.getUnitSymbol()).thenReturn(unitSymbolDouble);
    when(unit.getUnitDescription()).thenReturn(mock(UnitDescription.class));
    when(unit.getID()).thenReturn(mock(UnitID.class));

    UnitDataModel unitDataModel = new UnitDataModel(unit);

    // Act
    String unitSymbol = unitDataModel.getUnitSymbol();

    // Assert
    assertEquals("UnitSymbol", unitSymbol);
  }

  /**
   * Test returns UnitDescription when getUnitDescription is called
   */
  @Test
  void shouldReturnUnitDescription_whenGetUnitDescriptionIsCalled() {
    // Arrange
    Unit unit = mock(Unit.class);
    UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
    when(unitDescriptionDouble.getDescription()).thenReturn("UnitDescription");
    when(unit.getUnitDescription()).thenReturn(unitDescriptionDouble);
    when(unit.getUnitSymbol()).thenReturn(mock(UnitSymbol.class));
    when(unit.getID()).thenReturn(mock(UnitID.class));

    UnitDataModel unitDataModel = new UnitDataModel(unit);

    // Act
    String unitDescription = unitDataModel.getUnitDescription();

    // Assert
    assertEquals("UnitDescription", unitDescription);
  }

}