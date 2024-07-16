/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ModelPath;
import smarthome.utils.PathEncoder;
import smarthome.utils.dto.ActuatorModelDTO;

class ActuatorModelAssemblerTest {

  /**
   * Tests the conversion of an actuator model to an actuator model DTO, when the actuator model is
   * valid.
   */
  @Test
  void shouldConvertActuatorModelToActuatorModelDTO_whenActuatorModelIsValid() {
    // Arrange
    String actuatorModelID = "path";
    ModelPath actuatorModelIDDouble = mock(ModelPath.class);
    when(actuatorModelIDDouble.toString()).thenReturn(actuatorModelID);

    String actuatorModelName = "Light";
    ActuatorModelName actuatorModelNameDouble = mock(ActuatorModelName.class);
    when(actuatorModelNameDouble.getActuatorModelName()).thenReturn(actuatorModelName);

    ActuatorModel actuatorModelDouble = mock(ActuatorModel.class);
    when(actuatorModelDouble.getID()).thenReturn(actuatorModelIDDouble);
    when(actuatorModelDouble.getName()).thenReturn(actuatorModelNameDouble);

    ActuatorModelAssembler actuatorModelAssembler = new ActuatorModelAssembler();
    String expectedPath = PathEncoder.encode(actuatorModelID);
    String expectedName = actuatorModelName;

    // Act
    ActuatorModelDTO actuatorModelDTO = actuatorModelAssembler.domainToDTO(actuatorModelDouble);

    // Assert
    assertEquals(expectedPath, actuatorModelDTO.actuatorModelPath);
    assertEquals(expectedName, actuatorModelDTO.actuatorModelName);
  }



  /**
   * Tests the conversion of an actuator model to an actuator model DTO, when the actuator model is
   * null.
   */
  @Test
  void shouldThrowException_whenActuatorModelIsNull() {
    // Arrange
    ActuatorModel actuatorModel = null;
    ActuatorModelAssembler actuatorModelAssembler = new ActuatorModelAssembler();
    String expectedMessage = "Actuator Model is required";
    // Act & Assert

    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> actuatorModelAssembler.domainToDTO(actuatorModel));
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Tests the conversion of a list of actuator models to a list of actuator model DTOs, when the
   * list of actuator models is valid.
   */
  @Test
  void shouldConvertListOfActuatorModelsToListOfActuatorModelDTO_whenActuatorModelListIsValid() {
    // Arrange
    /* ActuatorModel 1 */
    String actuatorModelID1 = "path1";
    ModelPath actuatorModelIDDouble1 = mock(ModelPath.class);
    when(actuatorModelIDDouble1.toString()).thenReturn(actuatorModelID1);

    String actuatorModelName1 = "Light";
    ActuatorModelName actuatorModelNameDouble1 = mock(ActuatorModelName.class);
    when(actuatorModelNameDouble1.getActuatorModelName()).thenReturn(actuatorModelName1);

    ActuatorModel actuatorModelDouble1 = mock(ActuatorModel.class);
    when(actuatorModelDouble1.getID()).thenReturn(actuatorModelIDDouble1);
    when(actuatorModelDouble1.getName()).thenReturn(actuatorModelNameDouble1);

    /* ActuatorModel 2 */
    String actuatorModelID2 = "path2";
    ModelPath actuatorModelIDDouble2 = mock(ModelPath.class);
    when(actuatorModelIDDouble2.toString()).thenReturn(actuatorModelID2);

    String actuatorModelName2 = "Fan";
    ActuatorModelName actuatorModelNameDouble2 = mock(ActuatorModelName.class);
    when(actuatorModelNameDouble2.getActuatorModelName()).thenReturn(actuatorModelName2);

    ActuatorModel actuatorModelDouble2 = mock(ActuatorModel.class);
    when(actuatorModelDouble2.getID()).thenReturn(actuatorModelIDDouble2);
    when(actuatorModelDouble2.getName()).thenReturn(actuatorModelNameDouble2);

    List<ActuatorModel> actuatorModels = List.of(actuatorModelDouble1, actuatorModelDouble2);

    ActuatorModelAssembler actuatorModelAssembler = new ActuatorModelAssembler();
    String expectedPath1 = PathEncoder.encode(actuatorModelID1);
    String expectedPath2 = PathEncoder.encode(actuatorModelID2);
    String expectedName1 = actuatorModelName1;
    String expectedName2 = actuatorModelName2;

    // Act
    List<ActuatorModelDTO> actuatorModelsDTO = actuatorModelAssembler.domainToDTO(actuatorModels);

    // Assert
    assertEquals(expectedPath1, actuatorModelsDTO.get(0).actuatorModelPath);
    assertEquals(expectedName1, actuatorModelsDTO.get(0).actuatorModelName);
    assertEquals(expectedPath2, actuatorModelsDTO.get(1).actuatorModelPath);
    assertEquals(expectedName2, actuatorModelsDTO.get(1).actuatorModelName);
  }


  /**
   * Tests the conversion of a list of actuator models to a list of actuator model DTOs when the
   * list is null.
   */
  @Test
  void shouldThrowException_whenActuatorModelListIsNull() {
    // Arrange
    List<ActuatorModel> actuatorModels = null;
    ActuatorModelAssembler actuatorModelAssembler = new ActuatorModelAssembler();

    String expectedMessage = "The list of Actuator Models cannot be null.";
    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> actuatorModelAssembler.domainToDTO(actuatorModels));
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should return empty list when the list of actuator models is empty.
   */
  @Test
  void shouldReturnEmptyList_whenActuatorModelListIsEmpty()  {
    // Arrange
    List<ActuatorModel> actuatorModels = List.of();
    ActuatorModelAssembler actuatorModelAssembler = new ActuatorModelAssembler();
    List<ActuatorModelDTO> expected = List.of();
    // Act
    List<ActuatorModelDTO> actuatorModelsDTO = actuatorModelAssembler.domainToDTO(actuatorModels);
    // Assert
    assertEquals(expected.toString(), actuatorModelsDTO.toString());
  }
}
