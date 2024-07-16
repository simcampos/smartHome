/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.mapper.actuator_vo_assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DecimalLimits;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.IntegerLimits;
import smarthome.domain.value_object.ModelPath;
import smarthome.utils.PathEncoder;
import smarthome.utils.entry_dto.actuator_entry_dto.ActuatorGenericDataDTOImp;
import smarthome.utils.entry_dto.actuator_entry_dto.ActuatorWithDecimalLimitsEntryDTOImp;
import smarthome.utils.entry_dto.actuator_entry_dto.ActuatorWithIntegerLimitsEntryDTOImp;
import smarthome.utils.entry_dto.actuator_entry_dto.IActuatorEntryDTO;

class ActuatorVOAssemblerImplTest {

  /**
   * Tests if the object is instantiated when the attributes are null.
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenUnsupportedActuatorDataDTO() {
    // Arrange
    ActuatorVOAssemblerImpl actuatorVOAssembler = new ActuatorVOAssemblerImpl();
    String message = "Unsupported actuator data DTO";
    IActuatorEntryDTO actuatorDataDTO = null;
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> actuatorVOAssembler.getActuatorParameters(actuatorDataDTO));
    // Assert
    assertEquals(message, exception.getMessage());
  }

  /**
   * Tests if the object is instantiated when the ActuatorDataDTO is with decimal limits attribute.
   */
  @Test
  void shouldReturnArrayOfObjectsWhenActuatorDataDTOIsActuatorWithDecimalLimitsDataDTO() {
    // Arrange
    String deviceID = "deviceID";
    String actuatorModelPath = "YWN0dWF0b3JNb2RlbFBhdGg=";
    String actuatorName = "actuatorName";
    String actuatorTypeID = "actuatorTypeID";
    double minLimit = 10.0;
    double maxLimit = 50.0;
    IActuatorEntryDTO actuatorDataDTO =
        new ActuatorWithDecimalLimitsEntryDTOImp(
            deviceID, actuatorModelPath, actuatorTypeID, actuatorName, minLimit, maxLimit);
    ActuatorVOAssemblerImpl actuatorVOAssembler = new ActuatorVOAssemblerImpl();

    DeviceID deviceID1 = new DeviceID(deviceID);
    String decodedModelPath = PathEncoder.decode(actuatorModelPath);
    ModelPath modelPath = new ModelPath(decodedModelPath);
    ActuatorName actuatorName1 = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeID1 = new ActuatorTypeID(actuatorTypeID);
    DecimalLimits decimalLimits =
        new DecimalLimits(minLimit, maxLimit);
    Object[] expected = {deviceID1, modelPath, actuatorTypeID1, actuatorName1, decimalLimits};

    // Act
    Object[] result = actuatorVOAssembler.getActuatorParameters(actuatorDataDTO);
    // Assert
    assertEquals(Arrays.stream(expected).toList(), Arrays.stream(result).toList());
  }

  /**
   * Tests if the object is instantiated when the ActuatorDataDTO is with generic data attribute.
   */
  @Test
  void shouldReturnArrayOfObjectsWhenActuatorDataDTOIsActuatorGenericDataDTOImp() {
    // Arrange
    String deviceID = "deviceID";
    String actuatorModelPath = "YWN0dWF0b3JNb2RlbFBhdGg=";
    String actuatorName = "actuatorName";
    String actuatorTypeID = "actuatorTypeID";
    IActuatorEntryDTO actuatorDataDTO =
        new ActuatorGenericDataDTOImp(deviceID, actuatorModelPath, actuatorTypeID, actuatorName);
    ActuatorVOAssemblerImpl actuatorVOAssembler = new ActuatorVOAssemblerImpl();

    DeviceID deviceID1 = new DeviceID(deviceID);
    String decodedModelPath = PathEncoder.decode(actuatorModelPath);
    ModelPath modelPath = new ModelPath(decodedModelPath);
    ActuatorName actuatorName1 = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeID1 = new ActuatorTypeID(actuatorTypeID);
    Object[] expected = {deviceID1, modelPath, actuatorTypeID1, actuatorName1};

    // Act
    Object[] result = actuatorVOAssembler.getActuatorParameters(actuatorDataDTO);
    // Assert
    assertEquals(Arrays.stream(expected).toList(), Arrays.stream(result).toList());
  }

  /**
   * Tests if the object is instantiated when the ActuatorDataDTO is with integer limits attribute.
   */
  @Test
  void shouldReturnArrayOfObjectsWhenActuatorDataDTOIsActuatorDataWithIntegerLimitsDTOImp() {
    // Arrange
    String deviceID = "deviceID";
    String actuatorModelPath = "YWN0dWF0b3JNb2RlbFBhdGg=";
    String actuatorName = "actuatorName";
    String actuatorTypeID = "actuatorTypeID";
    String minLimit = "1";
    String maxLimit = "10";
    IActuatorEntryDTO actuatorDataDTO = new ActuatorWithIntegerLimitsEntryDTOImp(deviceID, actuatorModelPath, actuatorTypeID, actuatorName, minLimit, maxLimit);
    ActuatorVOAssemblerImpl actuatorVOAssembler = new ActuatorVOAssemblerImpl();

    DeviceID deviceID1 = new DeviceID(deviceID);
    String decodedModelPath = PathEncoder.decode(actuatorModelPath);
    ModelPath modelPath = new ModelPath(decodedModelPath);
    ActuatorName actuatorName1 = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeID1 = new ActuatorTypeID(actuatorTypeID);
    IntegerLimits integerLimits = new IntegerLimits(Integer.parseInt(minLimit), Integer.parseInt(maxLimit));

    Object[] expected = {deviceID1, modelPath, actuatorTypeID1, actuatorName1, integerLimits};

    // Act
    Object[] result = actuatorVOAssembler.getActuatorParameters(actuatorDataDTO);

    // Assert
    assertEquals(Arrays.stream(expected).toList(), Arrays.stream(result).toList());
  }
}
