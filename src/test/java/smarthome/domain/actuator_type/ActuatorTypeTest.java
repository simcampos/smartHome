/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator_type;

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
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;

class ActuatorTypeTest {

  /**
   * Test of the first constructor of class ActuatorType, when arguments are valid.
   */

  @Test
  void shouldCreateActuatorTypeInTheFirstConstructor_whenAttributesAreValid() {
    // Arrange
    TypeDescription actuatorName = mock(TypeDescription.class);
    when(actuatorName.toString()).thenReturn("ValidName");
    UnitID unitID = mock(UnitID.class);

    // Act
    ActuatorType actuatorType = new ActuatorType(actuatorName, unitID);

    // Assert
    assertNotNull(actuatorType);
  }

  /**
   * Test of the second constructor of class ActuatorType, when arguments are valid.
   */
  @Test
  void shouldCreateActuatorTypeInTheSecondConstructor_whenAttributesAreValid() {
    // Arrange
    TypeDescription actuatorName = mock(TypeDescription.class);
    when(actuatorName.toString()).thenReturn("ValidName");
    UnitID unitID = mock(UnitID.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.toString()).thenReturn("1");

    // Act
    ActuatorType actuatorType = new ActuatorType(actuatorName, unitID, actuatorTypeID);

    // Assert
    assertNotNull(actuatorType);
  }


  /**
   * Test of constructor of class ActuatorType, when typeDescription is null.
   */

  @Test
  void shouldThrowIllegalArgumentException_whenTypeDescriptionIsNull() {
    // Arrange
    TypeDescription typeDescriptionDouble = null;
    UnitID unitID = mock(UnitID.class);

    String expectedMessage = "Type Description is required";

    try (MockedConstruction<ActuatorTypeID> actuatorTypeIdDouble = mockConstruction(
        ActuatorTypeID.class, (mock, context) -> {
          when(mock.toString()).thenReturn("1");
        })) {
      // Act
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
          () -> new ActuatorType(typeDescriptionDouble, unitID));

      // Assert
      assertEquals(expectedMessage, exception.getMessage());
    }
  }

  /**
   * Test of method getID of class ActuatorType.
   */

  @Test
  void shouldReturnActuatorTypeID_whenGetIDisCalled() {
    // Arrange
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    UnitID unitID = mock(UnitID.class);
    try (MockedConstruction<ActuatorTypeID> actuatorTypeIdDouble = mockConstruction(
        ActuatorTypeID.class, (mock, context) -> {
          when(mock.toString()).thenReturn("1");
        })) {
      ActuatorType actuatorType = new ActuatorType(typeDescriptionDouble, unitID);
      // Act
      ActuatorTypeID result = actuatorType.getID();

      // Assert
      assertEquals("1", result.toString());
    }
  }

  /**
   * Test of method equals of class ActuatorType.
   */
  @Test
  void shouldReturnTrue_whenInstanceIsComparedToItself() {
    // Arrange
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    UnitID unitID = mock(UnitID.class);

    ActuatorType actuatorType = new ActuatorType(typeDescriptionDouble, unitID);

    // Act
    boolean result = actuatorType.equals(actuatorType);
    // Assert
    assertTrue(result);
  }

  /**
   * Test of method equals of class ActuatorType, when the instances are not equal.
   */
  @Test
  void shouldReturnFalse_whenInstancesAreNotEqual() {
    // Arrange
    TypeDescription typeDescriptionDouble1 = mock(TypeDescription.class);
    when(typeDescriptionDouble1.toString()).thenReturn("Name1");
    TypeDescription typeDescriptionDouble2 = mock(TypeDescription.class);
    when(typeDescriptionDouble2.toString()).thenReturn("Name2");
    UnitID unitID1 = mock(UnitID.class);
    UnitID unitID2 = mock(UnitID.class);

    ActuatorType actuatorType1 = new ActuatorType(typeDescriptionDouble1, unitID1);
    ActuatorType actuatorType2 = new ActuatorType(typeDescriptionDouble2, unitID2);

    // Act
    boolean result = actuatorType1.equals(actuatorType2);

    // Assert
    assertFalse(result);
  }

  /**
   * Test of method equals of class ActuatorType, when the instance is compared to an object of a
   * different class.
   */
  @Test
  void shouldReturnFalse_whenComparedWithDifferentClass() {
    // Arrange
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    UnitID unitID = mock(UnitID.class);

    ActuatorType actuatorType = new ActuatorType(typeDescriptionDouble, unitID);

    // Act
    boolean isEqual = actuatorType.equals(new Object());
    // Assert
    assertFalse(isEqual, "ActuatorType should not be equal to an object of a different class");
  }

  @Test
  void shouldReturnString_whenToStringIsCalled() {
    // Arrange
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    UnitID unitID = mock(UnitID.class);
    String expected = "1 " + typeDescriptionDouble + " " + unitID;

    try (MockedConstruction<ActuatorTypeID> actuatorTypeIdDouble = mockConstruction(
        ActuatorTypeID.class, (mock, context) -> {
          when(mock.toString()).thenReturn("1");
        })) {
      ActuatorType actuatorType = new ActuatorType(typeDescriptionDouble, unitID);

      // Act
      String result = actuatorType.toString();

      // Assert
      assertEquals(expected, result);
    }
  }

  /**
   * Test of method getActuatorTypeName of class ActuatorType.
   */
  @Test
  void shouldReturnActuatorTypeName_whenGetActuatorTypeNameIsCalled() {
    // Arrange
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    UnitID unitID = mock(UnitID.class);
    String actuatorTypeID = "1";

    try (MockedConstruction<ActuatorTypeID> actuatorTypeIdDouble = mockConstruction(
        ActuatorTypeID.class, (mock, context) -> {
          when(mock.toString()).thenReturn(actuatorTypeID);
        })) {
      ActuatorType actuatorType = new ActuatorType(typeDescriptionDouble, unitID);

      // Act
      TypeDescription result = actuatorType.getActuatorTypeName();

      // Assert
      assertEquals(typeDescriptionDouble, result);
    }
  }

  /**
   * Test of method hashcode of class ActuatorType.
   */
  @Test
  void shouldReturnConsistentHashCodeBasedOnID() {
    // Arrange
    TypeDescription typeDescription = mock(TypeDescription.class);
    UnitID unitID = mock(UnitID.class);

    try (MockedConstruction<ActuatorTypeID> actuatorTypeIdDouble = mockConstruction(
        ActuatorTypeID.class, (mock, context) -> {
          when(mock.toString()).thenReturn("1");
        })) {
      ActuatorType actuatorType = new ActuatorType(typeDescription, unitID);

      int expected = actuatorType.getID().hashCode();

      // Act
      int result = actuatorType.hashCode();

      // Assert
      assertEquals(expected, result);
    }
  }

  @Test
  void shouldThrowIllegalArgumentException_whenActuatorTypeIDIsNull() {
    // Arrange
    TypeDescription typeDescription = mock(TypeDescription.class);
    UnitID unitID = mock(UnitID.class);
    ActuatorTypeID actuatorTypeID = null;

    String expectedMessage = "Actuator Type ID is required";

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ActuatorType(typeDescription, unitID, actuatorTypeID));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }
}