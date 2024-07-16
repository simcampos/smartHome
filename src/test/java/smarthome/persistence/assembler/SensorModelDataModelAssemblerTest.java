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

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.sensor_model.ISensorModelFactory;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorModelName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.persistence.data_model.SensorModelDataModel;

class SensorModelDataModelAssemblerTest {

  /**
   * Test to check if the SensorModelDataModelAssembler is instantiated when the SensorModelFactory
   * is valid
   */
  @Test
  void shouldThrowException_whenSensorModelFactoryIsNull() {
    // Arrange
    ISensorModelFactory sensorModelFactory = null;

    String expected = "Sensor Model Factory is required";

    // Act
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorModelDataModelAssembler(sensorModelFactory));

    // Assert
    String actual = exception.getMessage();

    assertEquals(expected, actual);
  }

  /**
   * Test to check if the SensorModelDataModelAssembler is instantiated when the SensorModelFactory
   * is valid
   */
  @Test
  void shouldInstantiateSensorModelDataModelAssembler_whenSensorModelFactoryIsValid() {
    // Arrange
    ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);

    // Act
    SensorModelDataModelAssembler sensorModelDataModelAssembler = new SensorModelDataModelAssembler(
        sensorModelFactory);

    // Assert
    assertNotNull(sensorModelDataModelAssembler);
  }

  /**
   * Test to check if the SensorModelDataModelAssembler is instantiated when the SensorModelFactory
   * is valid
   */
  @Test
  void shouldConvertSensorModelDataModelToDomain_WhenSensorModelDataModelIsValid() {
    //Arrange
    ModelPath modelPathDouble = mock(ModelPath.class);
    SensorModelName sensorModelNameDouble = mock(SensorModelName.class);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

    SensorModelDataModel sensorModelDataModelDouble = mock(SensorModelDataModel.class);
    when(sensorModelDataModelDouble.getModelPath()).thenReturn("modelPath");
    when(sensorModelDataModelDouble.getSensorModelName()).thenReturn("sensorModelName");
    when(sensorModelDataModelDouble.getSensorTypeID()).thenReturn("sensorTypeID");

    ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);

    SensorModelDataModelAssembler sensorModelDataModelAssembler = new SensorModelDataModelAssembler(
        sensorModelFactory);

    SensorModel expected = sensorModelFactory.createSensorModel(sensorModelNameDouble,
        modelPathDouble, sensorTypeIDDouble);

    //Act
    SensorModel result = sensorModelDataModelAssembler.toDomain(sensorModelDataModelDouble);

    //Assert
    assertEquals(expected, result);
  }

  /**
   * Test to check if the SensorModelDataModelAssembler throws an exception when the
   * SensorModelDataModel is null
   */
  @Test
  void shouldThrowException_WhenSensorDataModelisNull() {
    //Arrange
    SensorModelDataModel sensorModelDataModel = null;

    ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
    SensorModelDataModelAssembler sensorModelDataModelAssembler = new SensorModelDataModelAssembler(
        sensorModelFactory);

    String expected = "Sensor Model Data Model is required";

    //Act
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> sensorModelDataModelAssembler.toDomain(sensorModelDataModel));

    //Assert
    String actual = exception.getMessage();
    assertEquals(expected, actual);
  }

  @Test
  void shouldConvertSensorModelDataModelListToDomainList_WhenSensorModelDataModelListIsValid() {
    //Arrange
    //SensorModel 1
    ModelPath modelPathDouble = mock(ModelPath.class);
    SensorModelName sensorModelNameDouble = mock(SensorModelName.class);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

    SensorModelDataModel sensorModelDataModelDouble = mock(SensorModelDataModel.class);
    when(sensorModelDataModelDouble.getModelPath()).thenReturn("modelPath");
    when(sensorModelDataModelDouble.getSensorModelName()).thenReturn("sensorModelName");
    when(sensorModelDataModelDouble.getSensorTypeID()).thenReturn("sensorTypeID");

    //SensorModel 2
    ModelPath modelPathDouble2 = mock(ModelPath.class);
    SensorModelName sensorModelNameDouble2 = mock(SensorModelName.class);
    SensorTypeID sensorTypeIDDouble2 = mock(SensorTypeID.class);

    SensorModelDataModel sensorModelDataModelDouble2 = mock(SensorModelDataModel.class);
    when(sensorModelDataModelDouble2.getModelPath()).thenReturn("modelPath2");
    when(sensorModelDataModelDouble2.getSensorModelName()).thenReturn("sensorModelName2");
    when(sensorModelDataModelDouble2.getSensorTypeID()).thenReturn("sensorTypeID2");

    ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);

    SensorModelDataModelAssembler sensorModelDataModelAssembler = new SensorModelDataModelAssembler(
        sensorModelFactory);

    List<SensorModelDataModel> sensorModelDataModelList = new ArrayList<>();
    sensorModelDataModelList.add(sensorModelDataModelDouble);
    sensorModelDataModelList.add(sensorModelDataModelDouble2);

    //Expected SensorModels
    SensorModel expected1 = mock(SensorModel.class);
    SensorModel expected2 = mock(SensorModel.class);

    when(sensorModelFactory.createSensorModel(any(SensorModelName.class), any(ModelPath.class),
        any(SensorTypeID.class))).thenReturn(expected1, expected2);

    List<SensorModel> expectedList = List.of(expected1, expected2);

    //Act
    List<SensorModel> resultList = sensorModelDataModelAssembler.toDomain(sensorModelDataModelList);

    //Assert
    assertEquals(expectedList, resultList);
  }

}

