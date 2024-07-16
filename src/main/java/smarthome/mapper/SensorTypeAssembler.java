/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import smarthome.ddd.IAssembler;
import smarthome.domain.sensor_type.SensorType;
import smarthome.utils.Validator;
import smarthome.utils.dto.SensorTypeDTO;

@Component
public class SensorTypeAssembler implements IAssembler<SensorType, SensorTypeDTO> {

  /**
   * Converts a {@link SensorType} domain entity to a {@link SensorTypeDTO} data transfer object.
   *
   * @param sensorType is the domain entity to be converted.
   * @return a {@link SensorTypeDTO} data transfer object.
   */
  public SensorTypeDTO domainToDTO(SensorType sensorType) {
    Validator.validateNotNull(sensorType, "Sensor Type");

    String sensorTypeID = sensorType.getID().toString();
    String sensorTypeDescription = sensorType.getDescription().toString();
    String unit = sensorType.getUnitID().toString();

    return new SensorTypeDTO(sensorTypeID, sensorTypeDescription, unit);
  }

  /**
   * Converts a list of {@link SensorType} domain entities to a list of {@link SensorTypeDTO} data
   * transfer objects.
   *
   * @param sensorTypes is the list of domain entities to be converted.
   * @return a list of {@link SensorTypeDTO} data transfer objects.
   */
  public List<SensorTypeDTO> domainToDTO(List<SensorType> sensorTypes) {
    if (sensorTypes == null) {
      throw new IllegalArgumentException("The list of sensor types cannot be null.");
    }

    return sensorTypes.stream().map(this::domainToDTO).toList();
  }

}
