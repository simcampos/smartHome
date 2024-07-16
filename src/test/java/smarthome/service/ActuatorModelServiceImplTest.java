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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.actuator_model.IActuatorModelFactory;
import smarthome.domain.repository.IActuatorModelRepository;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;
import smarthome.persistence.mem.ActuatorModelRepository;

class ActuatorModelServiceImplTest {

  /**
   * Verifies service instantiation with valid parameters.
   */
  @Test
  void shouldInstantiateActuatorModelService_WhenGivenValidParameters() {
    //Arrange
    IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
    IActuatorModelRepository actuatorModelRepository = mock(IActuatorModelRepository.class);

    //Act
    ActuatorModelServiceImpl result = new ActuatorModelServiceImpl(actuatorModelRepository,
        actuatorModelFactory);

    //Assert
    assertNotNull(result);
  }

  /**
   * Ensures an exception is thrown if ActuatorModelFactory is null.
   */
  @Test
  void shouldThrowException_WhenActuatorModelFactoryIsNull() {
    //Arrange
    IActuatorModelFactory actuatorModelFactory = null;
    IActuatorModelRepository actuatorModelRepository = mock(IActuatorModelRepository.class);

    String expectedMessage = "Actuator model factory is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ActuatorModelServiceImpl(actuatorModelRepository, actuatorModelFactory));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Ensures an exception is thrown if ActuatorModelRepository is null.
   */
  @Test
  void shouldThrowException_WhenActuatorModelRepositoryIsNull() {
    //Arrange
    IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
    IActuatorModelRepository actuatorModelRepository = null;

    String expectedMessage = "Actuator model repository is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ActuatorModelServiceImpl(actuatorModelRepository, actuatorModelFactory));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Tests retrieval of all actuator models.
   */
  @Test
  void shouldGetListOfActuatorModel_WhenGetActuatorModelsCalled() {
    //Arrange
    ActuatorModel actuatorModel = mock(ActuatorModel.class);
    IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
    IActuatorModelRepository actuatorModelRepository = mock(IActuatorModelRepository.class);

    when(actuatorModelRepository.findAll()).thenReturn(List.of(actuatorModel));

    try (MockedConstruction<ModelPath> modelPathMockedConstruction = mockConstruction(
        ModelPath.class, (mock, context) -> {
        });
        MockedConstruction<ActuatorModelName> actuatorModelMockedConstruction = mockConstruction(
            ActuatorModelName.class, (mock, context) -> {
            })) {

      // Act
      ActuatorModelServiceImpl actuatorModelServiceImpl = new ActuatorModelServiceImpl(
          actuatorModelRepository, actuatorModelFactory);
      List<ActuatorModel> actuatorModels = actuatorModelServiceImpl.getAllActuatorModels();

      // Assert
      assertEquals(actuatorModels, List.of(actuatorModel));
    }
  }

  /**
   * Tests retrieval of a specific actuator model by its ID.
   */
  @Test
  void shouldGetActuatorModel_WhenGetActuatorModelCalled() {
    //Arrange
    ActuatorModel actuatorModel = mock(ActuatorModel.class);
    IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
    IActuatorModelRepository actuatorModelRepository = mock(IActuatorModelRepository.class);

    when(actuatorModelRepository.ofIdentity(any())).thenReturn(Optional.of(actuatorModel));

    try (MockedConstruction<ModelPath> modelPathMockedConstruction = mockConstruction(
        ModelPath.class, (mock, context) -> {
        });
        MockedConstruction<ActuatorModelName> actuatorModelMockedConstruction = mockConstruction(
            ActuatorModelName.class, (mock, context) -> {
            })) {

      // Act
      ActuatorModelServiceImpl actuatorModelServiceImpl = new ActuatorModelServiceImpl(
          actuatorModelRepository, actuatorModelFactory);
      Optional<ActuatorModel> actuatorModelOptional = actuatorModelServiceImpl.getActuatorModel(
          mock(ModelPath.class));

      // Assert
      assertEquals(actuatorModelOptional.get(), actuatorModel);
    }
  }

  /**
   * Tests retrieval of actuator models by their type ID.
   */
  @Test
  void shouldReturnActuatorModelsByTypeID_WhenParametersAreValid() {
    //Arrange
    ActuatorModel actuatorModel = mock(ActuatorModel.class);
    IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
    IActuatorModelRepository actuatorModelRepository = mock(IActuatorModelRepository.class);

    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

    when(actuatorModelRepository.findBy_actuatorTypeID(actuatorTypeID)).thenReturn(
        List.of(actuatorModel));

    ActuatorModelServiceImpl actuatorModelServiceImpl = new ActuatorModelServiceImpl(
        actuatorModelRepository, actuatorModelFactory);

    int expected = 1;

    //Act
    List<ActuatorModel> actuatorModels = actuatorModelServiceImpl.getActuatorModelsByActuatorTypeId(
        actuatorTypeID);

    int result = actuatorModels.size();

    //Assert
    assertEquals(expected, result);
  }

  /**
   * Tests adding an actuator model.
   */
  @Test
  void shouldAddActuatorModel_WhenParametersAreValid() {
    //Arrange
    ActuatorModel actuatorModel = mock(ActuatorModel.class);
    IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
    IActuatorModelRepository actuatorModelRepository = mock(IActuatorModelRepository.class);

    ModelPath modelPath = mock(ModelPath.class);
    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

    when(actuatorModelFactory.createActuatorModel(modelPath, actuatorModelName, actuatorTypeID))
        .thenReturn(actuatorModel);

    when(actuatorModelRepository.save(actuatorModel)).thenReturn(actuatorModel);

    ActuatorModelServiceImpl actuatorModelServiceImpl = new ActuatorModelServiceImpl(
        actuatorModelRepository, actuatorModelFactory);

    //Act
    ActuatorModel result = actuatorModelServiceImpl.addActuatorModel(modelPath, actuatorModelName,
        actuatorTypeID);

    //Assert
    assertEquals(actuatorModel, result);
  }

}
