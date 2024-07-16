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
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.actuator_model.IActuatorModelFactory;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;
import smarthome.persistence.data_model.ActuatorModelDataModel;

class ActuatorModelDataModelAssemblerTest {

  /**
   * Test to check if the ActuatorModelDataModelAssembler is instantiated when the
   * ActuatorModelFactory is valid
   */
  @Test
  void shouldThrowException_whenActuatorModelFactoryIsNull() {
    // Arrange
    IActuatorModelFactory actuatorModelFactory = null;

    String expected = "Actuator Model Factory is required";

    // Act
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new ActuatorModelDataModelAssembler(actuatorModelFactory));

    // Assert
    String actual = exception.getMessage();

    assertEquals(expected, actual);
  }

  /**
   * Test to check if the ActuatorModelDataModelAssembler is instantiated when the
   * ActuatorModelFactory is valid
   */
  @Test
  void shouldInstantiateActuatorModelDataModelAssembler_whenActuatorModelFactoryIsValid() {
    // Arrange
    IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);

    // Act
    ActuatorModelDataModelAssembler actuatorModelDataModelAssembler = new ActuatorModelDataModelAssembler(
        actuatorModelFactory);

    // Assert
    assertNotNull(actuatorModelDataModelAssembler);
  }

  /**
   * Test to check if the ActuatorModelDataModelAssembler is instantiated when the
   * ActuatorModelFactory is valid
   */
  @Test
  void shouldConvertActuatorModelDataModelToDomain_WhenActuatorModelDataModelIsValid() {
    //Arrange
    ModelPath modelPathDouble = mock(ModelPath.class);
    ActuatorModelName actuatorModelNameDouble = mock(ActuatorModelName.class);
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

    ActuatorModelDataModel actuatorModelDataModelDouble = mock(ActuatorModelDataModel.class);
    when(actuatorModelDataModelDouble.getModelPath()).thenReturn("modelPath");
    when(actuatorModelDataModelDouble.getActuatorModelName()).thenReturn("actuatorModelName");
    when(actuatorModelDataModelDouble.getActuatorTypeID()).thenReturn("actuatorTypeID");

    IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);

    ActuatorModelDataModelAssembler actuatorModelDataModelAssembler = new ActuatorModelDataModelAssembler(
        actuatorModelFactory);

    ActuatorModel expected = actuatorModelFactory.createActuatorModel(modelPathDouble,
        actuatorModelNameDouble,
        actuatorTypeIDDouble);

    //Act
    ActuatorModel result = actuatorModelDataModelAssembler.toDomain(actuatorModelDataModelDouble);

    //Assert
    assertEquals(expected, result);
  }

  /**
   * Test to check if the ActuatorModelDataModelAssembler throws an exception when the
   * ActuatorModelDataModel is null
   */
  @Test
  void shouldThrowException_WhenActuatorDataModelisNull() {
    //Arrange
    ActuatorModelDataModel actuatorModelDataModel = null;

    IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
    ActuatorModelDataModelAssembler actuatorModelDataModelAssembler = new ActuatorModelDataModelAssembler(
        actuatorModelFactory);

    String expected = "Actuator Model Data Model is required";

    //Act
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> actuatorModelDataModelAssembler.toDomain(actuatorModelDataModel));

    //Assert
    String actual = exception.getMessage();
    assertEquals(expected, actual);
  }

  @Test
  void shouldConvertActuatorModelDataModelListToDomainList_WhenActuatorModelDataModelListIsValid() {
    //Arrange
    //ActuatorModel 1
    ModelPath modelPathDouble = mock(ModelPath.class);
    ActuatorModelName actuatorModelNameDouble = mock(ActuatorModelName.class);
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

    ActuatorModelDataModel actuatorModelDataModelDouble = mock(ActuatorModelDataModel.class);
    when(actuatorModelDataModelDouble.getModelPath()).thenReturn("modelPath");
    when(actuatorModelDataModelDouble.getActuatorModelName()).thenReturn("actuatorModelName");
    when(actuatorModelDataModelDouble.getActuatorTypeID()).thenReturn("actuatorTypeID");

    //ActuatorModel 2
    ModelPath modelPathDouble2 = mock(ModelPath.class);
    ActuatorModelName actuatorModelNameDouble2 = mock(ActuatorModelName.class);
    ActuatorTypeID actuatorTypeIDDouble2 = mock(ActuatorTypeID.class);

    ActuatorModelDataModel actuatorModelDataModelDouble2 = mock(ActuatorModelDataModel.class);
    when(actuatorModelDataModelDouble2.getModelPath()).thenReturn("modelPath2");
    when(actuatorModelDataModelDouble2.getActuatorModelName()).thenReturn("actuatorModelName2");
    when(actuatorModelDataModelDouble2.getActuatorTypeID()).thenReturn("actuatorTypeID2");

    IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);

    ActuatorModelDataModelAssembler actuatorModelDataModelAssembler = new ActuatorModelDataModelAssembler(
        actuatorModelFactory);

    List<ActuatorModelDataModel> actuatorModelDataModelList = new ArrayList<>();
    actuatorModelDataModelList.add(actuatorModelDataModelDouble);
    actuatorModelDataModelList.add(actuatorModelDataModelDouble2);

    //Expected ActuatorModels
    ActuatorModel expected1 = mock(ActuatorModel.class);
    ActuatorModel expected2 = mock(ActuatorModel.class);

    when(
        actuatorModelFactory.createActuatorModel(any(ModelPath.class), any(ActuatorModelName.class),
            any(ActuatorTypeID.class))).thenReturn(expected1, expected2);

    List<ActuatorModel> expectedList = List.of(expected1, expected2);

    //Act
    List<ActuatorModel> resultList = actuatorModelDataModelAssembler.toDomain(
        actuatorModelDataModelList);

    //Assert
    assertEquals(expectedList, resultList);
  }

}
