/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.mem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorTypeID;

class SensorModelRepositoryTest {

  @Test
  void shouldAddSensorModelToRepositoryWhenGivenValidSensorModel() {
    //Arrange
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModel sensorModel = mock(SensorModel.class);
    //Act
    SensorModel actualSensorModel = sensorModelRepository.save(sensorModel);
    //Assert
    assertEquals(sensorModel, actualSensorModel);
  }

  @Test
  void shouldThrowExceptionWhenGivenNullSensorModel() {
    //Arrange
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModel sensorModel = null;
    String expectedMessage = "Sensor Model is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> sensorModelRepository.save(sensorModel));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenSensorModelAlreadyExists() {
    //Arrange
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModel sensorModel = mock(SensorModel.class);
    sensorModelRepository.save(sensorModel);
    String expectedMessage = "SensorModel already exists.";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> sensorModelRepository.save(sensorModel));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldReturnListSensorModelsWhenGetAllSensorModelsIsCalled() {
    //Arrange
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModel sensorModel = mock(SensorModel.class);
    SensorModel secondSensorModel = mock(SensorModel.class);

    ModelPath modelPath = mock(ModelPath.class);
    when(sensorModel.getModelPath()).thenReturn(modelPath);

    sensorModelRepository.save(sensorModel);
    sensorModelRepository.save(secondSensorModel);

    List<SensorModel> expectedSensorModels = List.of(sensorModel, secondSensorModel);
    //Act
    List<SensorModel> sensorModels = sensorModelRepository.findAll();
    //Assert
    assertEquals(expectedSensorModels, sensorModels);
  }

  @Test
  void shouldReturnEmptyListWhenNoSensorModelsAreAdded() {
    //Arrange
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    //Act
    List<SensorModel> sensorModels = sensorModelRepository.findAll();
    //Assert
    assertTrue(sensorModels.isEmpty());
  }

  @Test
  void shouldReturnSensorModelWhenGivenValidSensorModelID() {
    //Arrange
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModel sensorModel = mock(SensorModel.class);
    ModelPath modelPath = mock(ModelPath.class);
    when(sensorModel.getModelPath()).thenReturn(modelPath);

    sensorModelRepository.save(sensorModel);
    //Act
    SensorModel actualSensorModel = sensorModelRepository.ofIdentity(modelPath).get();
    //Assert
    assertEquals(sensorModel, actualSensorModel);
  }

  @Test
  void shouldReturnOptionalEmptyWhenGivenInvalidSensorModelID() {
    //Arrange
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModel sensorModel = mock(SensorModel.class);
    ModelPath sensorModelID = mock(ModelPath.class);
    ModelPath invalidSensorModelID = mock(ModelPath.class);
    when(sensorModel.getID()).thenReturn(sensorModelID);

    sensorModelRepository.save(sensorModel);
    //Act
    boolean result = sensorModelRepository.ofIdentity(invalidSensorModelID).isEmpty();
    //Assert
    assertTrue(result);
  }

  @Test
  void shouldReturnFalseWhenGivenInvalidSensorModelID() {
    //Arrange
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModel sensorModel = mock(SensorModel.class);
    ModelPath sensorModelID = mock(ModelPath.class);
    ModelPath invalidSensorModelID = mock(ModelPath.class);
    when(sensorModel.getID()).thenReturn(sensorModelID);

    sensorModelRepository.save(sensorModel);
    //Act
    boolean result = sensorModelRepository.containsOfIdentity(invalidSensorModelID);
    //Assert
    assertFalse(result);
  }

  @Test
  void shouldReturnSensorTypeID_WhenGivenValidSensorTypeID() {
    //Arrange
    SensorModelRepository sensorModelRepository = new SensorModelRepository();

    SensorModel sensorModel = mock(SensorModel.class);
    ModelPath modelPath = mock(ModelPath.class);
    when(sensorModel.getModelPath()).thenReturn(modelPath);
    when(sensorModel.getSensorTypeID()).thenReturn(mock(SensorTypeID.class));

    sensorModelRepository.save(sensorModel);
    //Act
    SensorModel actualSensorModel = sensorModelRepository.findBySensorTypeId(
        sensorModel.getSensorTypeID()).get(0);
    //Assert
    assertEquals(sensorModel, actualSensorModel);
  }
}