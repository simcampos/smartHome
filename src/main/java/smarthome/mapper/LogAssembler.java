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
import smarthome.domain.log.Log;
import smarthome.utils.Validator;
import smarthome.utils.dto.LogDTO;

@Component
public class LogAssembler implements IAssembler<Log, LogDTO> {

  /**
   * Converts a Log object to a LogDTO object.
   *
   * @param domainEntity is the domain entity to be converted.
   * @return the LogDTO object.
   */
  @Override
  public LogDTO domainToDTO(Log domainEntity) {
    Validator.validateNotNull(domainEntity, "Log");

    String logID = domainEntity.getID().toString();
    String deviceID = domainEntity.getDeviceID().toString();
    String sensorID = domainEntity.getSensorID().toString();
    String sensorTypeID = domainEntity.getDescription().toString();
    String reading = domainEntity.getReadingValue().getValue();
    String timestamp = domainEntity.getTimeStamp().toString();
    String unitID = domainEntity.getUnit().toString();

    return new LogDTO(logID, deviceID, sensorID, sensorTypeID, reading, timestamp, unitID);
  }

  /**
   * Converts a list of Log objects to a list of LogDTO objects.
   *
   * @param domainEntities is the list of domain entities to be converted.
   * @return the list of LogDTO objects.
   */

  @Override
  public List<LogDTO> domainToDTO(List<Log> domainEntities) {
    if (domainEntities == null) {
      throw new IllegalArgumentException("The list of Logs cannot be null.");
    }
    return domainEntities.stream().map(this::domainToDTO).toList();
  }
}
