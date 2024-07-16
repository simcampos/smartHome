/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.assembler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import smarthome.domain.log.ILogFactory;
import smarthome.domain.log.Log;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.LogID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitID;
import smarthome.persistence.data_model.LogDataModel;
import smarthome.utils.Validator;

@Component
public class LogDataModelAssembler implements IDataModelAssembler<LogDataModel, Log> {

  private final ILogFactory logFactory;

  /**
   * Class constructor
   *
   * @param logFactory is the factory used to create Log instances.
   */
  public LogDataModelAssembler(ILogFactory logFactory) {
    Validator.validateNotNull(logFactory, "Log Factory");
    this.logFactory = logFactory;
  }

  /**
   * Converts a LogDataModel instance to a Log instance.
   *
   * @param logDataModel is the domain entity to be converted.
   * @return a Log instance.
   */
  @Override
  public Log toDomain(LogDataModel logDataModel) {
    Validator.validateNotNull(logDataModel, "Log Data Model");

    LogID logID = new LogID(logDataModel.getLogID());
    DeviceID deviceID = new DeviceID(logDataModel.getDeviceID());
    SensorID sensorID = new SensorID(logDataModel.getSensorID());
    LocalDateTime timeStamp = logDataModel.getTimestamp();
    ReadingValue readingValue = new ReadingValue(logDataModel.getReadingValue());
    SensorTypeID description = new SensorTypeID(logDataModel.getDescription());
    UnitID unit = new UnitID(logDataModel.getUnit());

    Log log =
        logFactory.createLog(
            logID, deviceID, sensorID, timeStamp, readingValue, description, unit);

    return log;
  }

  /**
   * Converts a list of LogDataModel instances to a list of Log instances.
   *
   * @param logDataModels is the list of domain entities to be converted.
   * @return a list of Log instances.
   */
  @Override
  public List<Log> toDomain(List<LogDataModel> logDataModels) {
    List<Log> logs = new ArrayList<>();

    for (LogDataModel logDataModel : logDataModels) {
      Log log = toDomain(logDataModel);
      logs.add(log);
    }

    return logs;
  }
}
