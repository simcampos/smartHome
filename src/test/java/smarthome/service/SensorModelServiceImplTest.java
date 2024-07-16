/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smarthome.domain.repository.ISensorModelRepository;
import smarthome.domain.sensor_model.ISensorModelFactory;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorModelName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.persistence.mem.SensorModelRepository;

class SensorModelServiceImplTest {

  /**
   * Test case for the constructor of the SensorModelService class.
   */
  @Test
  void shouldInstantiateSensorModelServiceWhenGivenValidParameters() {
    //Arrange
    ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
    ISensorModelRepository sensorModelRepository = mock(ISensorModelRepository.class);
    //Act
    SensorModelServiceImpl sensorModelServiceImpl = new SensorModelServiceImpl(
        sensorModelRepository, sensorModelFactory);
    //Assert
    assertNotNull(sensorModelServiceImpl);
  }

  /**
   * Should throw an exception when the sensor model factory is null.
   */
  @Test
  void shouldThrowExceptionWhenSensorModelFactoryIsNull() {
    //Arrange
    ISensorModelFactory sensorModelFactory = null;
    ISensorModelRepository sensorModelRepository = mock(ISensorModelRepository.class);

    String expectedMessage = "Sensor model factory is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorModelServiceImpl(sensorModelRepository, sensorModelFactory));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should throw an exception when the sensor model repository is null.
   */
  @Test
  void shouldThrowExceptionWhenSensorModelRepositoryIsNull() {
    //Arrange
    ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
    ISensorModelRepository sensorModelRepository = null;

    String expectedMessage = "Sensor model repository is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorModelServiceImpl(sensorModelRepository, sensorModelFactory));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test case for the getAllSensorModels method.
   */
  @Test
  void shouldGetListOfSensorModel_WhenGetSensorModelsCalled() {
    //Arrange
    SensorModel sensorModel = mock(SensorModel.class);
    ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
    ISensorModelRepository sensorModelRepository = mock(ISensorModelRepository.class);
    when(sensorModelRepository.findAll()).thenReturn(List.of(sensorModel));
    try (MockedConstruction<ModelPath> modelPathMockedConstruction = mockConstruction(
        ModelPath.class, (mock, context) -> {
        });
        MockedConstruction<SensorModelName> sensorModelMockedConstruction = mockConstruction(
            SensorModelName.class, (mock, context) -> {
            })) {

      // Act
      SensorModelServiceImpl sensorModelServiceImpl = new SensorModelServiceImpl(
          sensorModelRepository, sensorModelFactory);
      List<SensorModel> sensorModels = sensorModelServiceImpl.getAllSensorModels();

      // Assert
      assertEquals(sensorModels, List.of(sensorModel));
    }
  }

  /**
   * Test case for the getSensorModel method.
   */
  @Test
  void shouldGetSensorModel_WhenGetSensorModelCalled() {
    //Arrange
    SensorModel sensorModel = mock(SensorModel.class);
    ModelPath sensorModelId = mock(ModelPath.class);
    when(sensorModel.getID()).thenReturn(sensorModelId);

    ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
    ISensorModelRepository sensorModelRepository = mock(ISensorModelRepository.class);
    when(sensorModelRepository.ofIdentity(sensorModelId)).thenReturn(Optional.of(sensorModel));
    try (MockedConstruction<ModelPath> modelPathMockedConstruction = mockConstruction(
        ModelPath.class, (mock, context) -> {
        });
        MockedConstruction<SensorModelName> sensorModelMockedConstruction = mockConstruction(
            SensorModelName.class, (mock, context) -> {
            })) {

      // Act
      SensorModelServiceImpl sensorModelServiceImpl = new SensorModelServiceImpl(
          sensorModelRepository, sensorModelFactory);

      Optional<SensorModel> actualSensor = sensorModelServiceImpl.getSensorModel(sensorModelId);

      // Assert
      assertEquals(sensorModel, actualSensor.get());
    }
  }

  /**
   * Test case for the createSensorModel method.
   */
  @Test
  void shouldCreateSensorModel_WhenGivenValidParameters() {
    //Arrange
    SensorModelName sensorModelName = mock(SensorModelName.class);
    ModelPath sensorPath = mock(ModelPath.class);
    SensorModel sensorModel = mock(SensorModel.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
    when(
        sensorModelFactory.createSensorModel(sensorModelName, sensorPath, sensorTypeID)).thenReturn(
        sensorModel);
    SensorModelServiceImpl sensorModelServiceImpl = new SensorModelServiceImpl(
        mock(SensorModelRepository.class), sensorModelFactory);
    //Act
    SensorModel actualSensorModel = sensorModelServiceImpl.createSensorModel(sensorModelName,
        sensorPath, sensorTypeID);
    //Assert
    assertEquals(sensorModel, actualSensorModel);
  }

  /**
   * Should activate the sensor model repository when the createSensorModel method is called.
   */
  @Test
  void shouldActivateSensorModelRepository_WhenCreateSensorModelCalled() {
    //Arrange
    SensorModelName sensorModelName = mock(SensorModelName.class);
    ModelPath sensorPath = mock(ModelPath.class);
    SensorModel sensorModel = mock(SensorModel.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
    ISensorModelRepository sensorModelRepository = mock(ISensorModelRepository.class);
    when(
        sensorModelFactory.createSensorModel(sensorModelName, sensorPath, sensorTypeID)).thenReturn(
        sensorModel);

    SensorModelServiceImpl sensorModelServiceImpl = new SensorModelServiceImpl(
        sensorModelRepository, sensorModelFactory);
    //Act
    sensorModelServiceImpl.createSensorModel(sensorModelName, sensorPath, sensorTypeID);
    //Assert
    verify(sensorModelRepository, times(1)).save(sensorModel);
  }


  /**
   * Test case for the getSensorModelsBySensorTypeId method.
   */
  @Test
  void shouldReturnSensorModelsBySensorTypeId() {
    // Arrange
    SensorModel sensorModel1 = mock(SensorModel.class);
    SensorModel sensorModel2 = mock(SensorModel.class);
    List<SensorModel> expectedSensorModels = Arrays.asList(sensorModel1, sensorModel2);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    ISensorModelRepository sensorModelRepository = mock(ISensorModelRepository.class);
    when(sensorModelRepository.findBySensorTypeId(sensorTypeID)).thenReturn(expectedSensorModels);

    ISensorModelFactory sensorModelFactory = mock(ISensorModelFactory.class);
    SensorModelServiceImpl sensorModelServiceImpl = new SensorModelServiceImpl(
        sensorModelRepository, sensorModelFactory);

    // Act
    List<SensorModel> actualSensorModels = sensorModelServiceImpl.getSensorModelsBySensorTypeId(
        sensorTypeID);

    // Assert
    assertEquals(expectedSensorModels, actualSensorModels);
  }


}

