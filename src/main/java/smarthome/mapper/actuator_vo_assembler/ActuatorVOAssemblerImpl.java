/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.mapper.actuator_vo_assembler;

import org.springframework.stereotype.Component;
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

@Component
public class ActuatorVOAssemblerImpl implements IActuatorVOAssembler {

  /**
   * Returns an array of objects that are needed to create an actuator. The generic objects.
   *
   * @param actuatorDataDTO The actuator data DTO.
   * @return An array of objects that are needed to create an actuator.
   */
  private static Object[] getActuatorParameters(ActuatorGenericDataDTOImp actuatorDataDTO) {
    DeviceID deviceID = new DeviceID(actuatorDataDTO.deviceID);
    String decodedModelPath = PathEncoder.decode(actuatorDataDTO.actuatorModelPath);
    ModelPath modelPath = new ModelPath(decodedModelPath);
    ActuatorName actuatorName = new ActuatorName(actuatorDataDTO.actuatorName);
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorDataDTO.actuatorTypeID);
    return new Object[]{deviceID, modelPath, actuatorTypeID, actuatorName};
  }

  /**
   * Returns an array of objects that are needed to create an actuator. The generic objects plus the
   * decimal limits.
   *
   * @param actuatorDataDTO The actuator data DTO.
   * @return An array of objects that are needed to create an actuator.
   */
  private static Object[] getActuatorParameters(
      ActuatorWithDecimalLimitsEntryDTOImp actuatorDataDTO) {
    DeviceID deviceID = new DeviceID(actuatorDataDTO.deviceID);
    String decodedModelPath = PathEncoder.decode(actuatorDataDTO.actuatorModelPath);
    ModelPath modelPath = new ModelPath(decodedModelPath);
    ActuatorName actuatorName = new ActuatorName(actuatorDataDTO.actuatorName);
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorDataDTO.actuatorTypeID);
    double minLimit = actuatorDataDTO.minLimit;
    double maxLimit = actuatorDataDTO.maxLimit;
    DecimalLimits limits = new DecimalLimits(minLimit, maxLimit);
    return new Object[]{deviceID, modelPath, actuatorTypeID, actuatorName, limits};
  }

  /**
   * Returns an array of objects that are needed to create an actuator. The generic objects plus the
   * integer limits.
   *
   * @param actuatorDataDTO The actuator data DTO.
   * @return An array of objects that are needed to create an actuator.
   */
  private static Object[] getActuatorParameters(
      ActuatorWithIntegerLimitsEntryDTOImp actuatorDataDTO) {
    DeviceID deviceID = new DeviceID(actuatorDataDTO.deviceID);
    String decodedModelPath = PathEncoder.decode(actuatorDataDTO.actuatorModelPath);
    ModelPath modelPath = new ModelPath(decodedModelPath);
    ActuatorName actuatorName = new ActuatorName(actuatorDataDTO.actuatorName);
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorDataDTO.actuatorTypeID);
    int minLimit = Integer.parseInt(actuatorDataDTO.minLimit);
    int maxLimit = Integer.parseInt(actuatorDataDTO.maxLimit);
    IntegerLimits limits = new IntegerLimits(minLimit, maxLimit);
    return new Object[]{deviceID, modelPath, actuatorTypeID, actuatorName, limits};
  }

  /**
   * Returns an array of objects that are needed to create an actuator.
   *
   * @param actuatorDataDTO The actuator data DTO.
   * @return An array of objects that are needed to create an actuator.
   */
  @Override
  public Object[] getActuatorParameters(IActuatorEntryDTO actuatorDataDTO) {
    if (actuatorDataDTO instanceof ActuatorGenericDataDTOImp actuatorDataGenericDTOImp) {
      return getActuatorParameters(actuatorDataGenericDTOImp);
    } else if (actuatorDataDTO
        instanceof ActuatorWithDecimalLimitsEntryDTOImp actuatorDataWithDecimalLimitsDTOImp) {
      return getActuatorParameters(actuatorDataWithDecimalLimitsDTOImp);
    } else if (actuatorDataDTO
        instanceof ActuatorWithIntegerLimitsEntryDTOImp actuatorDataWithIntegerLimitsDTOImp) {
      return getActuatorParameters(actuatorDataWithIntegerLimitsDTOImp);
    } else {
      throw new IllegalArgumentException("Unsupported actuator data DTO");
    }
  }
}
