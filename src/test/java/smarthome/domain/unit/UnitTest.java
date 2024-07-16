/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;

class UnitTest {

  /**
   * Validates construction with valid arguments.
   */
  @Test
  void shouldReturnValidUnit_WhenGivenValidParameters() {
    //Arrange
    UnitSymbol unitDouble = mock(UnitSymbol.class);
    UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

    //Act
    Unit result = new Unit(unitDescriptionDouble, unitDouble);

    //Assert
    assertNotNull(result);
  }


  /**
   * Expects IllegalArgumentException for null unit unit.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenGivenNullUnitSymbol() {
    //Arrange
    UnitSymbol unitDouble = null;
    UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

    String expectedMessage = "UnitSymbol is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new Unit(unitDescriptionDouble, unitDouble));

    //Assert
    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Expects IllegalArgumentException for null unit description.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenGivenNullUnitDescription() {
    //Arrange
    UnitSymbol unitDouble = mock(UnitSymbol.class);
    UnitDescription unitDescriptionDouble = null;

    String expectedMessage = "UnitDescription is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new Unit(unitDescriptionDouble, unitDouble));

    //Assert
    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }


  /**
   * Validates construction with valid arguments.
   */
  @Test
  void shouldReturnValidUnit_WhenGivenValidParametersWithID() {
    //Arrange
    UnitSymbol unitDouble = mock(UnitSymbol.class);
    UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
    UnitID unitIDDouble = mock(UnitID.class);

    //Act
    Unit result = new Unit(unitDescriptionDouble, unitDouble, unitIDDouble);

    //Assert
    assertNotNull(result);
  }

  /**
   * Expects IllegalArgumentException for null unit unit.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenGivenNullUnitSymbolWithID() {
    //Arrange
    UnitSymbol unitDouble = null;
    UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
    UnitID unitIDDouble = mock(UnitID.class);

    String expectedMessage = "UnitSymbol is required";

    //Act
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new Unit(unitDescriptionDouble, unitDouble, unitIDDouble));
    String actualMessage = exception.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Expects IllegalArgumentException for null unit description.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenGivenNullUnitDescriptionWithID() {
    //Arrange
    UnitSymbol unitSymbol = mock(UnitSymbol.class);
    UnitDescription unitDescription = null;
    UnitID unitIDDouble = mock(UnitID.class);

    String expectedMessage = "UnitDescription is required";

    //Act
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new Unit(unitDescription, unitSymbol, unitIDDouble));
    String actualMessage = exception.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Expects IllegalArgumentException for null unitID.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenGivenNullUnitID() {
    //Arrange
    UnitSymbol unitDouble = mock(UnitSymbol.class);
    UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
    UnitID unitIDDouble = null;

    String expectedMessage = "UnitID is required";

    //Act
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new Unit(unitDescriptionDouble, unitDouble, unitIDDouble));
    String actualMessage = exception.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Tests equality on the same object instance.
   */
  @Test
  void shouldReturnTrue_WhenGivenSameObject() {
    //Arrange
    UnitSymbol unitDouble = mock(UnitSymbol.class);
    UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

    try (MockedConstruction<UnitID> mockedUnitID = mockConstruction(UnitID.class)) {
      Unit unit = new Unit(unitDescriptionDouble, unitDouble);

      //Act
      boolean result = unit.equals(unit);

      //Assert
      assertTrue(result);
    }
  }

  /**
   * Tests inequality on objects with different IDs.
   */
  @Test
  void shouldReturnFalse_WhenComparingTwoObjectsWithDifferentID() {
    //Arrange
    UnitSymbol unitDouble = mock(UnitSymbol.class);
    UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
    UnitDescription anotherUnitDescriptionDouble = mock(UnitDescription.class);

    try (MockedConstruction<UnitID> mockedUnitID = mockConstruction(UnitID.class)) {
      Unit unit = new Unit(unitDescriptionDouble, unitDouble);
      Unit unit2 = new Unit(anotherUnitDescriptionDouble, unitDouble);

      //Act
      boolean result = unit.equals(unit2);

      //Assert
      assertFalse(result);
    }
  }

  /**
   * Tests inequality with null.
   */
  @Test
  void shouldReturnFalse_WhenComparingObjectWithNull() {
    //Arrange
    UnitSymbol unitDouble = mock(UnitSymbol.class);
    UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

    try (MockedConstruction<UnitID> mockedUnitID = mockConstruction(UnitID.class)) {
      Unit unit = new Unit(unitDescriptionDouble, unitDouble);

      //Act
      boolean result = unit.equals(null);

      //Assert
      assertFalse(result);
    }
  }

  /**
   * Tests getting Unit description.
   */
  @Test
  void shouldReturnUnitDescription_whenGetUnitDescriptionIsCalled() {
    //Arrange
    String description = "Celsius";

    UnitSymbol unitDouble = mock(UnitSymbol.class);

    UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
    when(unitDescriptionDouble.toString()).thenReturn(description);

    try (MockedConstruction<UnitID> mockedUnitID = mockConstruction(UnitID.class)) {
      Unit unit = new Unit(unitDescriptionDouble, unitDouble);

      //Act
      UnitDescription result = unit.getUnitDescription();

      //Assert
      assertEquals(unitDescriptionDouble, result);
    }
  }

  /**
   * Tests getting ID.
   */
  @Test
  void shouldReturnUnitID_whenGetIDisCalled() {
    //Arrange
    UnitSymbol unitDouble = mock(UnitSymbol.class);
    UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

    try (MockedConstruction<UnitID> mockedUnitID = mockConstruction(UnitID.class)) {
      Unit unit = new Unit(unitDescriptionDouble, unitDouble);

      //Act
      UnitID result = unit.getID();

      //Assert
      assertTrue(unit.toString().contains(result.toString()));
    }
  }

  /**
   * Tests toString method.
   */
  @Test
  void shouldReturnString_whenToStringIsCalled() {
    //Arrange
    String unitSymbol = "C";
    String description = "Celsius";

    UnitSymbol unitDouble = mock(UnitSymbol.class);
    when(unitDouble.toString()).thenReturn(unitSymbol);

    UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
    when(unitDescriptionDouble.toString()).thenReturn(description);

    try (MockedConstruction<UnitID> mockedUnitID = mockConstruction(UnitID.class)) {
      Unit unit = new Unit(unitDescriptionDouble, unitDouble);

      UnitID unitIDDouble = mockedUnitID.constructed().get(0);

      String expected = "Unit:" +
          "unitSymbol=" + unitDouble +
          ", unitDescription=" + unitDescriptionDouble +
          ", unitID=" + unitIDDouble;

      //Act
      String result = unit.toString();

      //Assert
      assertEquals(expected, result);
    }
  }

  /**
   * Tests getting unit symbol.
   */
  @Test
  void shouldReturnUnitSymbol_whenGetUnitSymbolIsCalled() {
    //Arrange
    String unitSymbol = "C";

    UnitSymbol unitDouble = mock(UnitSymbol.class);
    when(unitDouble.toString()).thenReturn(unitSymbol);

    UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

    try (MockedConstruction<UnitID> mockedUnitID = mockConstruction(UnitID.class)) {
      Unit unit = new Unit(unitDescriptionDouble, unitDouble);

      //Act
      UnitSymbol result = unit.getUnitSymbol();

      //Assert
      assertEquals(unitDouble, result);
    }
  }

  /**
   * Tests returning hash code.
   */
  @Test
  void shouldReturnHashCode_whenGetHashCodeIsCalled() {
    //Arrange
    UnitSymbol unitDouble = mock(UnitSymbol.class);
    UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

    try (MockedConstruction<UnitID> mockedUnitID = mockConstruction(UnitID.class)) {
      Unit unit = new Unit(unitDescriptionDouble, unitDouble);
      int expected = unit.getID().hashCode();

      //Act
      int result = unit.hashCode();

      //Assert
      assertEquals(expected, result);
    }
  }
}