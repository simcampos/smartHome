/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UnitSymbolTest {

  /**
   * Test case: Instantiate UnitSymbol with null unit
   */
  @Test
  void shouldInstantiateUnitSymbol_WhenGivenValidParameters() {
    //Arrange
    String unit = "C";

    //Act
    UnitSymbol result = new UnitSymbol(unit);

    //Assert
    assertNotNull(result);
  }

  /**
   * Test case: Instantiate UnitSymbol with null unit
   */
  @Test
  void shouldThrowException_WhenUnitSymbolIsNull() {
    //Arrange
    String unit = null;

    String expectedMessage = "Invalid unit";
    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new UnitSymbol(unit);
    });

    String actualMessage = exception.getMessage();

    //Assert
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Test case: Instantiate UnitSymbol with empty unit
   */
  @Test
  void shouldThrowException_WhenUnitSymbolIsEmpty() {
    //Arrange
    String unit = "";

    String expectedMessage = "Invalid unit";
    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new UnitSymbol(unit);
    });

    String actualMessage = exception.getMessage();

    //Assert
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Test case: Instantiate UnitSymbol with unit length greater than 5
   */
  @Test
  void shouldThrowException_WhenUnitSymbolLengthIsGreaterThan5() {
    //Arrange
    String unit = "123456";

    String expectedMessage = "Invalid unit";
    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new UnitSymbol(unit);
    });

    String actualMessage = exception.getMessage();

    //Assert
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Test case: Instantiate UnitSymbol with unit length equal to 5
   */
  @Test
  void shouldInstantiateUnitSymbol_WhenUnitSymbolLengthIsEqualTo5() {
    //Arrange
    String unit = "12345";

    //Act
    UnitSymbol result = new UnitSymbol(unit);

    //Assert
    assertNotNull(result);
  }

  /**
   * Test equals method with reflection
   */
  @Test
  void shouldReturnTrue_WhenComparingUnitSymbolWithItself() {
    //Arrange
    String unit = "C";
    UnitSymbol unitSymbol = new UnitSymbol(unit);

    //Act
    boolean result = unitSymbol.equals(unitSymbol);

    //Assert
    assertTrue(result);
  }

  /**
   * Test case: Check if two UnitSymbol objects are equal
   */
  @Test
  void shouldReturnTrue_WhenTwoUnitSymbolObjectsAreEqual() {
    //Arrange
    String unit = "C";
    UnitSymbol unitSymbol1 = new UnitSymbol(unit);
    UnitSymbol unitSymbol2 = new UnitSymbol(unit);

    //Act
    boolean result = unitSymbol1.equals(unitSymbol2);

    //Assert
    assertTrue(result);
  }

  /**
   * Test case: Check if two UnitSymbol objects are not equal
   */
  @Test
  void shouldReturnFalse_WhenTwoUnitSymbolObjectsAreNotEqual() {
    //Arrange
    String unit1 = "C";
    String unit2 = "F";
    UnitSymbol unitSymbol1 = new UnitSymbol(unit1);
    UnitSymbol unitSymbol2 = new UnitSymbol(unit2);

    //Act
    boolean result = unitSymbol1.equals(unitSymbol2);

    //Assert
    assertFalse(result);
  }

  /**
   * Should return unit symbol when getUnit method is called.
   */
  @Test
  void shouldReturnUnit_WhenGetUnitMethodIsCalled() {
    //Arrange
    String unit = "C";
    UnitSymbol unitSymbol = new UnitSymbol(unit);

    //Act
    String result = unitSymbol.getUnit();

    //Assert
    assertEquals(unit, result);
  }

  /**
   * Should return unit symbol when toString method is called.
   */
  @Test
  void shouldReturnUnit_WhenToStringMethodIsCalled() {
    //Arrange
    String unit = "C";
    UnitSymbol unitSymbol = new UnitSymbol(unit);

    //Act
    String result = unitSymbol.toString();

    //Assert
    assertTrue(result.contains(unit));
  }

  /**
   * Test if the hashCode method returns the same hash code for two equal unit symbols.
   */
  @Test
  void shouldReturnSameHashCode_WhenTwoUnitSymbolsAreEqual() {
    //Arrange
    String unit = "C";
    UnitSymbol unitSymbol = new UnitSymbol(unit);

    int expected = unitSymbol.hashCode();

    //Act
    int hashCode = unitSymbol.hashCode();

    //Assert
    assertEquals(expected, hashCode);
  }

  /**
   * Test if the hashCode method returns not equals when one hashCode is zero.
   */
  @Test
  void shouldReturnDifferentHashCode_WhenOneUnitSymbolIsZero() {
    //Arrange
    String unit = "C";
    UnitSymbol unitSymbol = new UnitSymbol(unit);

    //Act
    int result = unitSymbol.hashCode();

    //Assert
    assertNotEquals(0, result);
  }

  /**
   * Test getSymbol method
   */
  @Test
  void shouldReturnUnitSymbol_WhenGetSymbolIsCalled() {
    //Arrange
    String unit = "C";
    UnitSymbol unitSymbol = new UnitSymbol(unit);

    //Act
    String result = unitSymbol.getSymbol();

    //Assert
    assertEquals(unit, result);
  }

  /**
   * Test if equals method returns false when the object is not an instance of UnitSymbol
   */
  @Test
  void shouldReturnFalse_WhenComparingUnitSymbolWithDifferentObject() {
    //Arrange
    String unit = "C";
    UnitSymbol unitSymbolObject = new UnitSymbol(unit);

    //Act
    boolean result = unitSymbolObject.equals(new Object());

    //Assert
    assertFalse(result);
  }
}