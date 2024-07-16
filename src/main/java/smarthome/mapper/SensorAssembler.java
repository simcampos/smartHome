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
import smarthome.domain.sensor.ISensor;
import smarthome.utils.PathEncoder;
import smarthome.utils.Validator;
import smarthome.utils.dto.SensorDTO;

@Component
public class SensorAssembler implements IAssembler<ISensor, SensorDTO> {


  /**
   * Converts a domain entity to a DTO.
   *
   * @param sensor is the domain entity to be converted.
   * @return the DTO that was created.
   */
  @Override
  public SensorDTO domainToDTO(ISensor sensor) {
    Validator.validateNotNull(sensor, "Sensor");

    String deviceID = sensor.getDeviceID().toString();
    String modelPath = sensor.getModelPath().toString();
    modelPath = PathEncoder.encode(modelPath);
    String sensorTypeID = sensor.getSensorTypeID().toString();
    String sensorID = sensor.getID().toString();
    String sensorName = sensor.getSensorName().toString();

    return new SensorDTO(deviceID, modelPath, sensorID, sensorTypeID, sensorName);
  }

  /**
   * Converts a list of domain entities to a list of DTOs.
   *
   * @param sensors is the list of domain entities to be converted.
   * @return the list of DTOs that was created.
   */
  @Override
  public List<SensorDTO> domainToDTO(List<ISensor> sensors) {
    if (sensors == null) {
      throw new IllegalArgumentException("The list of sensors cannot be null.");
    }

    return sensors.stream().map(this::domainToDTO).toList();
  }
}
