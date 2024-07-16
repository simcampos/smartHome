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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.value_object.ActuatorModelID;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;
import smarthome.persistence.data_model.ActuatorModelDataModel;

class ActuatorModelDataModelTest {

  /**
   * Test to ensure that an ActuatorModelDataModel can be instantiated successfully
   */
  @Test
  void shouldInstantiateActuatorModelDataModelWithDefaultConstructor() {
    // Act
    ActuatorModelDataModel actuatorModelDataModel = new ActuatorModelDataModel();
    // Assert
    assertNotNull(actuatorModelDataModel);
  }

  /**
   * Test to ensure that an ActuatorModelDataModel can be instantiated successfully when given an
   * ActuatorModel
   */
  @Test
  void shouldInstantiateActuatorModelDataModel() {
    // Arrange
    ActuatorModelID actuatorModelID = mock(ActuatorModelID.class);
    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

    ActuatorModel actuatorModel = mock(ActuatorModel.class);

    when(actuatorModel.getName()).thenReturn(actuatorModelName);
    when(actuatorModel.getID()).thenReturn(modelPath);
    when(actuatorModel.getActuatorTypeID()).thenReturn(actuatorTypeID);
    // Act
    ActuatorModelDataModel actuatorModelDataModel = new ActuatorModelDataModel(actuatorModel);
    // Assert
    assertNotNull(actuatorModelDataModel);
  }

  /**
   * Test to ensure that an IllegalArgumentException is thrown when an ActuatorModelDataModel is
   * instantiated with a null ActuatorModel
   */
  @Test
  void shouldThrowExceptionWhenGivenNullActuatorModel() {
    // Arrange
    ActuatorModel actuatorModel = null;
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> new ActuatorModelDataModel(actuatorModel));
    // Assert
    assertEquals("Actuator Model is required", exception.getMessage());
  }

  /**
   * Test to return the actuator model ID
   */
  @Test
  void shouldReturnActuatorModelIDWhenGetActuatorModelID() {
    // Arrange
    String strActuatorModelName = "actuatorModelName";
    String strModelPath = "modelPath";
    String strActuatorTypeID = "actuatorTypeID";

    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorModel actuatorModelDouble = mock(ActuatorModel.class);

    when(actuatorModelName.getActuatorModelName()).thenReturn(strActuatorModelName);
    when(modelPath.getID()).thenReturn(strModelPath);
    when(actuatorTypeID.getID()).thenReturn(strActuatorTypeID);

    when(actuatorModelDouble.getName()).thenReturn(actuatorModelName);
    when(actuatorModelDouble.getID()).thenReturn(modelPath);
    when(actuatorModelDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);

    ActuatorModelDataModel actuatorModelDataModel = new ActuatorModelDataModel(actuatorModelDouble);
    // Act
    String result = actuatorModelDataModel.getModelPath();
    // Assert
    assertEquals(strModelPath, result);
  }

  /**
   * Test to return the actuator model name
   */
  @Test
  void shouldReturnActuatorModelNameWhenGetActuatorModelName() {
    // Arrange
    String strActuatorModelName = "actuatorModelName";
    String strModelPath = "modelPath";
    String strActuatorTypeID = "actuatorTypeID";

    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorModel actuatorModelDouble = mock(ActuatorModel.class);

    when(actuatorModelName.getActuatorModelName()).thenReturn(strActuatorModelName);
    when(modelPath.getID()).thenReturn(strModelPath);
    when(actuatorTypeID.getID()).thenReturn(strActuatorTypeID);

    when(actuatorModelDouble.getName()).thenReturn(actuatorModelName);
    when(actuatorModelDouble.getID()).thenReturn(modelPath);
    when(actuatorModelDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);

    ActuatorModelDataModel actuatorModelDataModel = new ActuatorModelDataModel(actuatorModelDouble);
    // Act
    String result = actuatorModelDataModel.getActuatorModelName();
    // Assert
    assertEquals(strActuatorModelName, result);
  }

  /**
   * Test to return the actuator type ID
   */
  @Test
  void shouldReturnActuatorTypeIDWhenGetActuatorTypeID() {
    // Arrange
    String strActuatorModelName = "actuatorModelName";
    String strModelPath = "modelPath";
    String strActuatorTypeID = "actuatorTypeID";

    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorModel actuatorModelDouble = mock(ActuatorModel.class);

    when(actuatorModelName.getActuatorModelName()).thenReturn(strActuatorModelName);
    when(modelPath.getID()).thenReturn(strModelPath);
    when(actuatorTypeID.getID()).thenReturn(strActuatorTypeID);

    when(actuatorModelDouble.getName()).thenReturn(actuatorModelName);
    when(actuatorModelDouble.getID()).thenReturn(modelPath);
    when(actuatorModelDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);

    ActuatorModelDataModel actuatorModelDataModel = new ActuatorModelDataModel(actuatorModelDouble);
    // Act
    String result = actuatorModelDataModel.getActuatorTypeID();
    // Assert
    assertEquals(strActuatorTypeID, result);
  }

  /**
   * Test to return the model path
   */
  @Test
  void shouldReturnActuatorModelPathWhenGetActuatorModelPath() {
    // Arrange
    String strActuatorModelName = "actuatorModelName";
    String strModelPath = "modelPath";
    String strActuatorTypeID = "actuatorTypeID";

    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorModel actuatorModelDouble = mock(ActuatorModel.class);

    when(actuatorModelName.getActuatorModelName()).thenReturn(strActuatorModelName);
    when(modelPath.getID()).thenReturn(strModelPath);
    when(actuatorTypeID.getID()).thenReturn(strActuatorTypeID);

    when(actuatorModelDouble.getName()).thenReturn(actuatorModelName);
    when(actuatorModelDouble.getID()).thenReturn(modelPath);
    when(actuatorModelDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);

    ActuatorModelDataModel actuatorModelDataModel = new ActuatorModelDataModel(actuatorModelDouble);
    // Act
    String result = actuatorModelDataModel.getModelPath();
    // Assert
    assertEquals(strModelPath, result);
  }
}
