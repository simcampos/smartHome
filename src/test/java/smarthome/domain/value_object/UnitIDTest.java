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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UnitIDTest {

  /**
   * test construct
   */
  @Test
  void shouldGetValidObject_whenUsingValidStringInConstructor() {
    // Arrange
    String strUnitID = "unit1";
    // Act
    UnitID unitID = new UnitID(strUnitID);
    // Assert
    assertNotNull(unitID);
  }

  /**
   * test construct if null
   */
  @Test
  void shouldThrowException_whenunitIDIsNull() {
    // Arrange
    String unitID = null;

    String expectedMessage = "The value of 'UnitID' should not null, blank, or empty.";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new UnitID(unitID));

    String actualMessage = exception.getMessage();

    //assert
    assertEquals(expectedMessage, actualMessage);

  }

  /**
   * test construct if blank
   */
  @Test
  void shouldThrowException_whenunitIDIsBlank() {
    // Arrange
    String unitID = " ";

    String expectedMessage = "The value of 'UnitID' should not null, blank, or empty.";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new UnitID(unitID));

    String actualMessage = exception.getMessage();

    //assert
    assertEquals(expectedMessage, actualMessage);

  }

  /**
   * test construct if empty
   */
  @Test
  void shouldThrowException_whenunitIDIsEmpty() {
    // Arrange
    String unitID = "";

    String expectedMessage = "The value of 'UnitID' should not null, blank, or empty.";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new UnitID(unitID));

    String actualMessage = exception.getMessage();

    //assert
    assertEquals(expectedMessage, actualMessage);

  }

  /**
   * test equals
   */
  @Test
  void shouldReturnTrue_whenComparingTwoEqualunitIDs() {
    // Arrange
    String unitID = "unit1";
    UnitID unitID1 = new UnitID(unitID);
    UnitID unitID2 = new UnitID(unitID);

    // Act
    boolean result = unitID1.equals(unitID2);
    // Assert
    assertTrue(result);
  }

  /**
   * test equals if same object
   */
  @Test
  void shouldReturnTrue_whenComparingunitIDToItself() {
    // Arrange
    String unitID = "unit1";
    UnitID unitID1 = new UnitID(unitID);
    // Act
    boolean result = unitID1.equals(unitID1);
    // Assert
    assertTrue(result);
  }

  /**
   * test equals if different object
   */
  @Test
  void shouldReturnFalse_whenComparingunitIDToDifferentObject() {
    // Arrange
    String unitID = "unit1";
    String strUnitID2 = "unit2";

    UnitID unitID1 = new UnitID(unitID);
    UnitID unitID2 = new UnitID(strUnitID2);

    // Act
    boolean result = unitID1.equals(unitID2);
    // Assert
    assertFalse(result);
  }

  @Test
  void shouldReturnFalse_whenComparingUnitIDToNull() {
    // Arrange
    String unitID = "unit1";
    UnitID unitID1 = new UnitID(unitID);
    // Act
    boolean result = unitID1.equals(null);
    // Assert
    assertFalse(result);
  }

  /**
   * test get id
   */
  @Test
  void shouldReturnunitID_whenGetIDIsCalled() {
    // Arrange
    String unitID = "unit1";
    UnitID unitID1 = new UnitID(unitID);

    // Act
    String result = unitID1.getID();
    // Assert
    assertEquals(unitID, result);
  }

  /**
   * test hash code
   */
  @Test
  void shouldReturnHashCode_whenHashCodeIsCalled() {
    // Arrange
    String unitID = "unit1";
    UnitID unitID1 = new UnitID(unitID);
    // Act
    int result = unitID1.hashCode();
    // Assert
    assertEquals(result, unitID.hashCode());
  }


}
