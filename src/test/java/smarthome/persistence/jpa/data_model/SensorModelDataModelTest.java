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
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorModelName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.persistence.data_model.SensorModelDataModel;


class SensorModelDataModelTest {

  /**
   * Test to check if the SensorModelDataModel is instantiated correctly
   */
  @Test
  void shouldInstantiateSensorModelDataModel() {
    //Arrange
    String strModelPath = "modelPath";
    String strSensorModelName = "sensorName";
    String strSensorTypeID = "sensorTypeID";

    ModelPath modelPathDouble = mock(ModelPath.class);
    SensorModelName sensorNameDouble = mock(SensorModelName.class);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

    SensorModel sensorModelDouble = mock(SensorModel.class);

    when(modelPathDouble.getID()).thenReturn(strModelPath);
    when(sensorNameDouble.getSensorModelName()).thenReturn(strSensorModelName);
    when(sensorTypeIDDouble.getID()).thenReturn(strSensorTypeID);

    when(sensorModelDouble.getModelPath()).thenReturn(modelPathDouble);
    when(sensorModelDouble.getName()).thenReturn(sensorNameDouble);
    when(sensorModelDouble.getSensorTypeID()).thenReturn(sensorTypeIDDouble);

    when(sensorModelDouble.getModelPath()).thenReturn(modelPathDouble);
    when(sensorModelDouble.getName()).thenReturn(sensorNameDouble);
    when(sensorModelDouble.getSensorTypeID()).thenReturn(sensorTypeIDDouble);

    //Act
    SensorModelDataModel sensorModelDataModel = new SensorModelDataModel();

    //Assert
    assertNotNull(sensorModelDataModel);
  }

  /**
   * Test to check if IllegalArgumentException is thrown when SensorModel is null
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenSensorModelIsNull() {
    //Arrange
    SensorModel sensorModelDouble = null;

    String expectedMessage = "Sensor Model is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorModelDataModel(sensorModelDouble));

    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));

  }

  /**
   * Test to check if the SensorModelDataModel is instantiated correctly
   */
  @Test
  void shouldReturnModelPath_WhenGetModelPath() {
    //Arrange
    String strModelPath = "modelPath";
    String strSensorModelName = "sensorName";
    String strSensorTypeID = "sensorTypeID";

    ModelPath modelPathDouble = mock(ModelPath.class);
    SensorModelName sensorNameDouble = mock(SensorModelName.class);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

    SensorModel sensorModelDouble = mock(SensorModel.class);

    when(modelPathDouble.getID()).thenReturn(strModelPath);
    when(sensorNameDouble.getSensorModelName()).thenReturn(strSensorModelName);
    when(sensorTypeIDDouble.getID()).thenReturn(strSensorTypeID);

    when(sensorModelDouble.getModelPath()).thenReturn(modelPathDouble);
    when(sensorModelDouble.getName()).thenReturn(sensorNameDouble);
    when(sensorModelDouble.getSensorTypeID()).thenReturn(sensorTypeIDDouble);

    SensorModelDataModel sensorModelDataModel = new SensorModelDataModel(sensorModelDouble);

    //Act
    String result = sensorModelDataModel.getModelPath();

    //Assert
    assertEquals(strModelPath, result);
  }

  /**
   * Test to check if the SensorModelDataModel is instantiated correctly
   */
  @Test
  void shouldReturnSensorModelName_WhenGetSensorModelName() {
    //Arrange
    String strModelPath = "modelPath";
    String strSensorModelName = "sensorName";
    String strSensorTypeID = "sensorTypeID";

    ModelPath modelPathDouble = mock(ModelPath.class);
    SensorModelName sensorNameDouble = mock(SensorModelName.class);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

    SensorModel sensorModelDouble = mock(SensorModel.class);

    when(modelPathDouble.getID()).thenReturn(strModelPath);
    when(sensorNameDouble.getSensorModelName()).thenReturn(strSensorModelName);
    when(sensorTypeIDDouble.getID()).thenReturn(strSensorTypeID);

    when(sensorModelDouble.getModelPath()).thenReturn(modelPathDouble);
    when(sensorModelDouble.getName()).thenReturn(sensorNameDouble);
    when(sensorModelDouble.getSensorTypeID()).thenReturn(sensorTypeIDDouble);

    SensorModelDataModel sensorModelDataModel = new SensorModelDataModel(sensorModelDouble);

    //Act
    String result = sensorModelDataModel.getSensorModelName();

    //Assert
    assertEquals(strSensorModelName, result);
  }

  /**
   * Test to check if the SensorModelDataModel is instantiated correctly
   */
  @Test
  void shouldReturnSensorTypeID_WhenGetSensorTypeID() {
    //Arrange
    String strModelPath = "modelPath";
    String strSensorModelName = "sensorName";
    String strSensorTypeID = "sensorTypeID";

    ModelPath modelPathDouble = mock(ModelPath.class);
    SensorModelName sensorNameDouble = mock(SensorModelName.class);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

    SensorModel sensorModelDouble = mock(SensorModel.class);

    when(modelPathDouble.getID()).thenReturn(strModelPath);
    when(sensorNameDouble.getSensorModelName()).thenReturn(strSensorModelName);
    when(sensorTypeIDDouble.getID()).thenReturn(strSensorTypeID);

    when(sensorModelDouble.getModelPath()).thenReturn(modelPathDouble);
    when(sensorModelDouble.getName()).thenReturn(sensorNameDouble);
    when(sensorModelDouble.getSensorTypeID()).thenReturn(sensorTypeIDDouble);

    SensorModelDataModel sensorModelDataModel = new SensorModelDataModel(sensorModelDouble);

    //Act
    String result = sensorModelDataModel.getSensorTypeID();

    //Assert
    assertEquals(strSensorTypeID, result);
  }

}
