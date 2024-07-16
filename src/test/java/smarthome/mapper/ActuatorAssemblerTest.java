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
import smarthome.domain.actuator.IActuator;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.utils.PathEncoder;
import smarthome.utils.dto.ActuatorDTO;

class ActuatorAssemblerTest {

  /**
   * Should convert Actuator to ActuatorDTO when Actuator is valid.
   */
  @Test
  void shouldConvertActuatorToActuatorDTO_whenActuatorIsValid() {
    // Arrange
    String actuatorID = "1";
    ActuatorID actuatorIdDouble = mock(ActuatorID.class);
    when(actuatorIdDouble.getID()).thenReturn(actuatorID);

    String actuatorTypeID = "EREWSD";
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    when(actuatorTypeIDDouble.getID()).thenReturn(actuatorTypeID);

    String actuatorName = "César";
    ActuatorName actuatorNameDouble = mock(ActuatorName.class);
    when(actuatorNameDouble.getName()).thenReturn(actuatorName);

    String actuatorModelPath = "actuatorModelPath"; // Original path
    ModelPath modelPathDouble = mock(ModelPath.class);
    when(modelPathDouble.toString()).thenReturn(actuatorModelPath);

    String deviceID = "1098234";
    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.getID()).thenReturn(deviceID);

    IActuator actuatorDouble = mock(IActuator.class);
    when(actuatorDouble.getID()).thenReturn(actuatorIdDouble);
    when(actuatorDouble.getActuatorTypeID()).thenReturn(actuatorTypeIDDouble);
    when(actuatorDouble.getName()).thenReturn(actuatorNameDouble);
    when(actuatorDouble.getModelPath()).thenReturn(modelPathDouble);
    when(actuatorDouble.getDeviceID()).thenReturn(deviceIDDouble);

    ActuatorAssembler actuatorAssembler = new ActuatorAssembler();
    String encodedModelPath = PathEncoder.encode(actuatorModelPath);
    String expected = actuatorID + " " + actuatorTypeID + " " + actuatorName + " " + encodedModelPath + " " + deviceID;

    // Act
    ActuatorDTO actuatorDTO = actuatorAssembler.domainToDTO(actuatorDouble);

    // Assert
    assertEquals(expected, actuatorDTO.toString());
  }

  /**
   * Should throw IllegalArgumentException when Actuator is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenActuatorIsNull() {
    //Arrange
    IActuator actuatorDouble = null;
    ActuatorAssembler actuatorAssembler = new ActuatorAssembler();

    String expectedMessage = "Actuator is required";

    //Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> actuatorAssembler.domainToDTO(actuatorDouble));
    assertEquals(expectedMessage, exception.getMessage());

  }

  /**
   * Should convert a list of Actuator to a list of ActuatorDTO when the list of Actuator is valid.
   */
  @Test
  void shouldConvertListOfActuatorToListOfActuatorDTO_whenListOfActuatorIsValid() {
    // Arrange
    String actuatorID1 = "1";
    ActuatorID actuatorIdDouble1 = mock(ActuatorID.class);
    when(actuatorIdDouble1.getID()).thenReturn(actuatorID1);

    String actuatorID2 = "2";
    ActuatorID actuatorIdDouble2 = mock(ActuatorID.class);
    when(actuatorIdDouble2.getID()).thenReturn(actuatorID2);

    String actuatorTypeID = "EREWSD";
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    when(actuatorTypeIDDouble.getID()).thenReturn(actuatorTypeID);

    String actuatorName1 = "César";
    ActuatorName actuatorNameDouble = mock(ActuatorName.class);
    when(actuatorNameDouble.getName()).thenReturn(actuatorName1);

    String actuatorName2 = "Aquece";
    ActuatorName actuatorNameDouble2 = mock(ActuatorName.class);
    when(actuatorNameDouble2.getName()).thenReturn(actuatorName2);

    String actuatorModelPath = "path";
    ModelPath modelPathDouble = mock(ModelPath.class);
    when(modelPathDouble.toString()).thenReturn(actuatorModelPath);

    String deviceID = "1098234";
    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.getID()).thenReturn(deviceID);

    IActuator actuatorDouble1 = mock(IActuator.class);
    when(actuatorDouble1.getID()).thenReturn(actuatorIdDouble1);
    when(actuatorDouble1.getActuatorTypeID()).thenReturn(actuatorTypeIDDouble);
    when(actuatorDouble1.getName()).thenReturn(actuatorNameDouble);
    when(actuatorDouble1.getModelPath()).thenReturn(modelPathDouble);
    when(actuatorDouble1.getDeviceID()).thenReturn(deviceIDDouble);

    IActuator actuatorDouble2 = mock(IActuator.class);
    when(actuatorDouble2.getID()).thenReturn(actuatorIdDouble2);
    when(actuatorDouble2.getActuatorTypeID()).thenReturn(actuatorTypeIDDouble);
    when(actuatorDouble2.getName()).thenReturn(actuatorNameDouble2);
    when(actuatorDouble2.getModelPath()).thenReturn(modelPathDouble);
    when(actuatorDouble2.getDeviceID()).thenReturn(deviceIDDouble);

    List<IActuator> actuators = List.of(actuatorDouble1, actuatorDouble2);

    ActuatorAssembler actuatorAssembler = new ActuatorAssembler();
    String encodedModelPath = PathEncoder.encode(actuatorModelPath);
    ActuatorDTO actuatorDTO1 = new ActuatorDTO(actuatorID1, actuatorTypeID, actuatorName1, encodedModelPath, deviceID);
    ActuatorDTO actuatorDTO2 = new ActuatorDTO(actuatorID2, actuatorTypeID, actuatorName2, encodedModelPath, deviceID);
    List<ActuatorDTO> expected = List.of(actuatorDTO1, actuatorDTO2);

    // Act
    List<ActuatorDTO> actuatorsDTO = actuatorAssembler.domainToDTO(actuators);

    // Assert
    assertEquals(expected.toString(), actuatorsDTO.toString());
  }


  /**
   * Should throw IllegalArgumentException when the list of Actuator is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenListOfActuatorIsNull() {
    //Arrange
    List<IActuator> actuators = null;
    ActuatorAssembler actuatorAssembler = new ActuatorAssembler();

    String expectedMessage = "The list of Actuators cannot be null.";

    //Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> actuatorAssembler.domainToDTO(actuators));
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should return empty list of ActuatorDTO when the list of Actuator is empty.
   */
  @Test
  void shouldReturnEmptyListOfActuatorDTO_whenListOfActuatorIsEmpty()  {
    //Arrange
    List<IActuator> actuators = List.of();
    ActuatorAssembler actuatorAssembler = new ActuatorAssembler();
    List<ActuatorDTO> expected = List.of();

    //Act
    List<ActuatorDTO> actuatorsDTO = actuatorAssembler.domainToDTO(actuators);

    //Assert
    assertEquals(expected, actuatorsDTO);
  }
}
