/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor_type;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;

class SensorTypeAggregateTest {

  /**
   * Test of constructor of class SensorType, when arguments are valid.
   */
  @Test
  void shouldCreateSensorType_whenAttributesAreValid() {
    // Arrange
    String typeDescription = "DewPointSensor";
    String unitID = "Celsius";

    TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
    UnitID unitDouble = new UnitID(unitID);

    // Act
    SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

    // Assert
    assertNotNull(sensorType);
  }


  /**
   * Test of getID method, of class SensorType.
   */
  @Test
  void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
    // Arrange
    String typeDescription = "DewPointSensor";
    String unitID = "Celsius";

    TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
    UnitID unitDouble = new UnitID(unitID);

    SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

    // Act
    SensorTypeID result = sensorType.getID();

    // Assert
    assertTrue(sensorType.toString().contains(result.toString()));
  }


  /**
   * Test of getName method, of class SensorType.
   */
  @Test
  void shouldReturnSensorTypeName_whenGetSensorTypeNameIsCalled() {
    // Arrange
    String typeDescription = "DewPointSensor";
    String unitID = "Celsius";

    TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
    UnitID unitDouble = new UnitID(unitID);

    SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

    // Act
    TypeDescription sensorTypeName = sensorType.getDescription();

    // Assert
    assertEquals(sensorTypeName, typeDescriptionDouble);

  }

  /**
   * Test of getUnit method, of class SensorType.
   */
  @Test
  void shouldReturnUnit_whenGetUnitIsCalled() {
    // Arrange
    String typeDescription = "DewPointSensor";
    String unitID = "Celsius";

    TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
    UnitID unitDouble = new UnitID(unitID);

    SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

    // Act
    UnitID unit = sensorType.getUnitID();

    // Assert
    assertEquals(unit, unitDouble);

  }


  /**
   * Test of equals method, of class SensorType, when comparing sensorType with itself.
   */
  @Test
  void shouldGetTrue_whenSensorTypeIsEqualToItself() {
    // Arrange
    String typeDescription = "DewPointSensor";
    String unitID = "Celsius";

    TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
    UnitID unitDouble = new UnitID(unitID);

    String sensorTypeID = "1";

    SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

    // Act
    boolean result = sensorType.equals(sensorType);

    // Assert
    assertTrue(result);
  }


  /**
   * Test of equals method, of class SensorType, when comparing sensorType with another object with
   * same ID.
   */
  @Test
  void shouldGetTrue_whenSensorTypeIsEqualToAnotherSensorTypeWithSameID() {
    // Arrange
    String typeDescription = "DewPointSensor";
    String unitID = "Celsius";

    TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
    UnitID unitDouble = new UnitID(unitID);

    TypeDescription typeDescriptionDouble2 = new TypeDescription(typeDescription);
    UnitID unitDouble2 = new UnitID(unitID);

    String sensorTypeID = "1";

    SensorType sensorType1 = new SensorType(typeDescriptionDouble, unitDouble);
    SensorType sensorType2 = new SensorType(typeDescriptionDouble2, unitDouble2);

    // Act
    boolean result = sensorType1.equals(sensorType2);

    // Assert
    assertTrue(result);
  }


  /**
   * Test of equals method, of class SensorType, when comparing sensorType with another object with
   * different ID.
   */
  @Test
  void shouldGetFalse_whenSensorTypeIsComparedToAnotherSensorTypeWithDifferentID()
      throws NoSuchFieldException, IllegalAccessException {
    // Arrange
    String typeDescription = "DewPointSensor";
    String typeDescription2 = "TemperatureSensor";
    String unitID = "Celsius";

    TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
    UnitID unitDouble = new UnitID(unitID);

    TypeDescription typeDescriptionDouble2 = new TypeDescription(typeDescription2);
    UnitID unitDouble2 = new UnitID(unitID);

    SensorType sensorType1 = new SensorType(typeDescriptionDouble, unitDouble);
    SensorType sensorType2 = new SensorType(typeDescriptionDouble2, unitDouble2);

    // Act
    boolean result = sensorType1.equals(sensorType2);

    // Assert
    assertFalse(result);

  }

  /**
   * Test of equals method, of class SensorType, when comparing sensorType with null object.
   */
  @Test
  void shouldReturnFalse_WhenComparingWithNullObject() {
    //Arrange
    String typeDescription = "DewPointSensor";
    String unitID = "Celsius";

    TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
    UnitID unitDouble = new UnitID(unitID);

    SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

    //Act
    boolean result = sensorType.equals(null);

    //Assert
    assertFalse(result);
  }

  /**
   * Should return the ID of the sensor type.
   */
  @Test
  void shouldReturnID_whenGetIDIsCalled() {
    // Arrange
    String typeDescription = "DewPointSensor";
    String unitID = "Celsius";

    TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
    UnitID unitDouble = new UnitID(unitID);

    String sensorTypeIDExpected = "DewPointSensor";

    SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

    // Act
    SensorTypeID result = sensorType.getID();

    // Assert
    assertEquals(sensorTypeIDExpected, result.toString());


  }

  /**
   * Test of toString method, of class SensorType.
   */
  @Test
  void shouldGetStringWithAttributes_whenToStringIsCalled() {
    // Arrange
    String typeDescription = "DewPointSensor";
    String unitID = "Celsius";

    TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
    UnitID unitDouble = new UnitID(unitID);

    SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

    String expected = "SensorType:" +
        "id=" + sensorType.getID() +
        ", name=" + sensorType.getDescription() +
        ", unit=" + sensorType.getUnitID();

    // Act
    String result = sensorType.toString();

    // Assert
    assertEquals(expected, result);
  }


  /**
   * Test of hashCode method, of class SensorType.
   */
  @Test
  void shouldReturnHashCode_whenHashCodeIsCalled() {
    // Arrange
    String typeDescription = "DewPointSensor";
    String unitID = "Celsius";

    TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
    UnitID unitDouble = new UnitID(unitID);

    SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

    int expected = sensorType.getID().hashCode();

    // Act
    int result = sensorType.hashCode();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Test of constructor of class SensorType, when arguments are valid.
   */
  @Test
  void shouldInstantiateSensorType_WhenParametersAreValid() {
    //Arrange
    String strTypeDescription = "DewPoint";
    String strUnitID = "Celsius";
    String strSensorTypeID = "123";

    TypeDescription typeDescription = new TypeDescription(strTypeDescription);
    UnitID unitID = new UnitID(strUnitID);
    SensorTypeID sensorTypeID = new SensorTypeID(strSensorTypeID);

    //Act
    SensorType sensor = new SensorType(sensorTypeID, typeDescription, unitID);

    //Assert
    assertNotNull(sensor);
  }

  /**
   * Test of constructor of class SensorType, when type description is null.
   */
  @Test
  void shouldThrowException_WhenTypeDescriptionIsNull() {
    //Arrange
    String strUnitID = "Celsius";
    String strSensorTypeID = "123";

    TypeDescription typeDescription = null;
    UnitID unitID = new UnitID(strUnitID);
    SensorTypeID sensorTypeID = new SensorTypeID(strSensorTypeID);

    String expectedMessage = "Name is required";

    //Act
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new SensorType(sensorTypeID, typeDescription, unitID);
    });

    //Assert
    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test of constructor of class SensorType, when unit is null.
   */
  @Test
  void shouldThrowException_WhenUnitIDIsNull() {
    //Arrange
    String strTypeDescription = "DewPoint";
    String strSensorTypeID = "123";

    TypeDescription typeDescription = new TypeDescription(strTypeDescription);
    UnitID unitID = null;
    SensorTypeID sensorTypeID = new SensorTypeID(strSensorTypeID);

    String expectedMessage = "Unit is required";

    //Act
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new SensorType(sensorTypeID, typeDescription, unitID);
    });

    //Assert
    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test of constructor of class SensorType, when sensor type ID is null.
   */
  @Test
  void shouldThrowException_WhenSensorTypeIDIsNull() {
    //Arrange
    String strTypeDescription = "DewPoint";
    String strUnitID = "Celsius";

    TypeDescription typeDescription = new TypeDescription(strTypeDescription);
    UnitID unitID = new UnitID(strUnitID);
    SensorTypeID sensorTypeID = null;

    String expectedMessage = "SensorTypeID is required";

    //Act
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new SensorType(sensorTypeID, typeDescription, unitID);
    });

    //Assert
    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

}
