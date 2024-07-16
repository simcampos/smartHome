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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.persistence.data_model.SensorTypeDataModel;

class SensorTypeDataModelTest {

  @Test
  void shouldInstantiateSensorTypeDataModel() {
    //Arrange
    String strSensorTypeID = "123";
    String strTypeDescription = "DewPoint";
    String strUnitID = "Celsius";

    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    UnitID unitIDDouble = mock(UnitID.class);
    SensorType sensorTypeDouble = mock(SensorType.class);

    when(sensorTypeIDDouble.getID()).thenReturn(strSensorTypeID);
    when(typeDescriptionDouble.getID()).thenReturn(strTypeDescription);
    when(unitIDDouble.getID()).thenReturn(strUnitID);

    when(sensorTypeDouble.getID()).thenReturn(sensorTypeIDDouble);
    when(sensorTypeDouble.getDescription()).thenReturn(typeDescriptionDouble);
    when(sensorTypeDouble.getUnitID()).thenReturn(unitIDDouble);

    //Act
    SensorTypeDataModel sensorTypeDataModel = new SensorTypeDataModel(sensorTypeDouble);

    //Assert
    assertNotNull(sensorTypeDataModel);
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenSensorTypeIsNull() {
    //Arrange
    SensorType sensorTypeDouble = null;

    String expectedMessage = "Sensor Type is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorTypeDataModel(sensorTypeDouble));

    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));

  }

  @Test
  void shouldReturnSensorTypeID_WhenGetSensorTypeID() {
    //Arrange
    String strSensorTypeID = "123";
    String strTypeDescription = "DewPoint";
    String strUnitID = "Celsius";

    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    UnitID unitIDDouble = mock(UnitID.class);
    SensorType sensorTypeDouble = mock(SensorType.class);

    when(sensorTypeIDDouble.getID()).thenReturn(strSensorTypeID);
    when(typeDescriptionDouble.getID()).thenReturn(strTypeDescription);
    when(unitIDDouble.getID()).thenReturn(strUnitID);

    when(sensorTypeDouble.getID()).thenReturn(sensorTypeIDDouble);
    when(sensorTypeDouble.getDescription()).thenReturn(typeDescriptionDouble);
    when(sensorTypeDouble.getUnitID()).thenReturn(unitIDDouble);

    SensorTypeDataModel sensorTypeDataModel = new SensorTypeDataModel(sensorTypeDouble);

    //Act
    String result = sensorTypeDataModel.getSensorTypeID();

    //Assert
    assertEquals(strSensorTypeID, result);

  }

  @Test
  void shouldReturnTypeDescription_WhenGetTypeDescription() {
    //Arrange
    String strSensorTypeID = "123";
    String strTypeDescription = "DewPoint";
    String strUnitID = "Celsius";

    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    UnitID unitIDDouble = mock(UnitID.class);
    SensorType sensorTypeDouble = mock(SensorType.class);

    when(sensorTypeIDDouble.getID()).thenReturn(strSensorTypeID);
    when(typeDescriptionDouble.getID()).thenReturn(strTypeDescription);
    when(unitIDDouble.getID()).thenReturn(strUnitID);

    when(sensorTypeDouble.getID()).thenReturn(sensorTypeIDDouble);
    when(sensorTypeDouble.getDescription()).thenReturn(typeDescriptionDouble);
    when(sensorTypeDouble.getUnitID()).thenReturn(unitIDDouble);

    SensorTypeDataModel sensorTypeDataModel = new SensorTypeDataModel(sensorTypeDouble);

    //Act
    String result = sensorTypeDataModel.getTypeDescription();

    //Assert
    assertEquals(strTypeDescription, result);
  }

  @Test
  void shouldReturnUnitID_WhenGetUnitID() {
    //Arrange
    String strSensorTypeID = "123";
    String strTypeDescription = "DewPoint";
    String strUnitID = "Celsius";

    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    UnitID unitIDDouble = mock(UnitID.class);
    SensorType sensorTypeDouble = mock(SensorType.class);

    when(sensorTypeIDDouble.getID()).thenReturn(strSensorTypeID);
    when(typeDescriptionDouble.getID()).thenReturn(strTypeDescription);
    when(unitIDDouble.getID()).thenReturn(strUnitID);

    when(sensorTypeDouble.getID()).thenReturn(sensorTypeIDDouble);
    when(sensorTypeDouble.getDescription()).thenReturn(typeDescriptionDouble);
    when(sensorTypeDouble.getUnitID()).thenReturn(unitIDDouble);

    SensorTypeDataModel sensorTypeDataModel = new SensorTypeDataModel(sensorTypeDouble);

    //Act
    String result = sensorTypeDataModel.getUnitID();

    //Assert
    assertEquals(strUnitID, result);
  }

  @Test
  void shouldInstantiateSensorTypeDataModelWithDefaultConstructor() {
    //Act
    SensorTypeDataModel sensorTypeDataModel = new SensorTypeDataModel();

    //Act
    assertNotNull(sensorTypeDataModel);
  }
}