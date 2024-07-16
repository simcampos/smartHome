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
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorModelName;
import smarthome.utils.PathEncoder;
import smarthome.utils.dto.SensorModelDTO;

class SensorModelAssemblerTest {

  /**
   * Test that the method domainToDTO converts a SensorModel to a SensorModelDTO.
   */
  @Test
  void shouldConvertSensorModelToSensorModelDTO_whenSensorModelIsValid() {

    // Arrange
    String sensorModelID = "1";
    ModelPath sensorModelIDDouble = mock(ModelPath.class);
    when(sensorModelIDDouble.toString()).thenReturn(sensorModelID);

    String sensorModelName = "Temperature";
    SensorModelName sensorModelNameDouble = mock(SensorModelName.class);
    when(sensorModelNameDouble.toString()).thenReturn(sensorModelName);

    String sensorModelPath = PathEncoder.encode(sensorModelID);
    ModelPath sensorModelPathDouble = mock(ModelPath.class);
    when(sensorModelPathDouble.toString()).thenReturn(sensorModelPath);

    SensorModel sensorModelDouble = mock(SensorModel.class);
    when(sensorModelDouble.getID()).thenReturn(sensorModelIDDouble);
    when(sensorModelDouble.getName()).thenReturn(sensorModelNameDouble);
    when(sensorModelDouble.getModelPath()).thenReturn(sensorModelPathDouble);

    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    String expected = sensorModelName + " " + sensorModelPath;

    // Act
    SensorModelDTO sensorModelDTO = sensorModelAssembler.domainToDTO(sensorModelDouble);

    // Assert
    assertEquals(expected, sensorModelDTO.toString());
  }

  /**
   * Test that the method domainToDTO throws an IllegalArgumentException when the SensorModel is
   * null.
   */
  @Test
  void shouldThrowException_whenSensorModelIsNull() {

    // Arrange
    SensorModel sensorModel = null;
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();

    String expected = "Sensor Model is required";

    // Act and Assert
    Exception exception = assertThrows(
        IllegalArgumentException.class, () -> sensorModelAssembler.domainToDTO(sensorModel));
    assertEquals(expected, exception.getMessage());
  }

  /**
   * Test that the method domainToDTO converts a list of SensorModel to a list of SensorModelDTO.
   */
  @Test
  void shouldConvertSensorModelListToSensorModelDTOList_whenSensorModelListIsValid()
     {

    // Arrange
       // sensorModel1
       String sensorModelID = "1";
       ModelPath sensorModelIDDouble = mock(ModelPath.class);
       when(sensorModelIDDouble.toString()).thenReturn(sensorModelID);

       String sensorModelName = "Temperature";
       SensorModelName sensorModelNameDouble = mock(SensorModelName.class);
       when(sensorModelNameDouble.toString()).thenReturn(sensorModelName);

       String sensorModelPath = PathEncoder.encode(sensorModelID);
       ModelPath sensorModelPathDouble = mock(ModelPath.class);
       when(sensorModelPathDouble.toString()).thenReturn(sensorModelPath);

       SensorModel sensorModelDouble = mock(SensorModel.class);
       when(sensorModelDouble.getID()).thenReturn(sensorModelIDDouble);
       when(sensorModelDouble.getName()).thenReturn(sensorModelNameDouble);
       when(sensorModelDouble.getModelPath()).thenReturn(sensorModelPathDouble);

       SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();

       // sensorModel2
       String sensorModelID2 = "2";
       ModelPath sensorModelIDDouble2 = mock(ModelPath.class);
       when(sensorModelIDDouble2.toString()).thenReturn(sensorModelID2);

       String sensorModelName2 = "Humidity";
       SensorModelName sensorModelNameDouble2 = mock(SensorModelName.class);
       when(sensorModelNameDouble2.toString()).thenReturn(sensorModelName2);

       String sensorModelPath2 = PathEncoder.encode(sensorModelID2);
       ModelPath sensorModelPathDouble2 = mock(ModelPath.class);
       when(sensorModelPathDouble2.toString()).thenReturn(sensorModelPath2);

       SensorModel sensorModelDouble2 = mock(SensorModel.class);
       when(sensorModelDouble2.getID()).thenReturn(sensorModelIDDouble2);
       when(sensorModelDouble2.getName()).thenReturn(sensorModelNameDouble2);
       when(sensorModelDouble2.getModelPath()).thenReturn(sensorModelPathDouble2);

       List<SensorModel> sensorModels = List.of(sensorModelDouble, sensorModelDouble2);

    // Act
    List<SensorModelDTO> sensorModelsDTO = sensorModelAssembler.domainToDTO(sensorModels);

    // Assert
       assertEquals(2, sensorModelsDTO.size());
       assertEquals(sensorModelName + " " + sensorModelPath,
           sensorModelsDTO.get(0).toString());
       assertEquals(sensorModelName2 + " " + sensorModelPath2,
           sensorModelsDTO.get(1).toString());
  }

  /**
   * Test that the method domainToDTO throws an IllegalArgumentException when the list of
   * SensorModel is null
   */
  @Test
  void shouldThrowException_whenSensorModelListIsNull() {

    // Arrange
    List<SensorModel> sensorModels = null;
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    String expected = "The list of Sensor Models cannot be null.";

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      sensorModelAssembler.domainToDTO(sensorModels);
    });
    String result = exception.getMessage();
    assertEquals(expected, result);
  }

  /**
   * Should return empty list of SensorModelDTO when the list of SensorModel is empty
   */
  @Test
  void shouldReturnEmptyList_whenSensorModelListIsEmpty(){

    // Arrange
    List<SensorModel> sensorModels = List.of();
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();

    // Act
    List<SensorModelDTO> sensorModelsDTO = sensorModelAssembler.domainToDTO(sensorModels);

    // Assert
    assertEquals(0, sensorModelsDTO.size());
  }
}
