/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.cli;

import java.time.LocalDateTime;
import java.util.List;
import smarthome.domain.log.Log;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TimeDelta;
import smarthome.service.ILogService;
import smarthome.utils.Validator;

public class GetMaxInstTempDiffBetweenDeviceAndOutsideController {

  private final ILogService logService;

  /**
   * Constructor for GetMaxInstTempDiffBetweenDeviceAndOutsideController.
   *
   * @param logService The log service.
   */
  public GetMaxInstTempDiffBetweenDeviceAndOutsideController(ILogService logService) {
    Validator.validateNotNull(logService, "Log Service");
    this.logService = logService;
  }

  /**
   * Get the maximum instantaneous temperature difference between a device and the outside.
   *
   * @param outsideDeviceIDStr is the outside device.
   * @param insideDeviceIDStr  is the inside device.
   * @param initialTime      is the initial time.
   * @param finalTime        is the final time.
   * @return the maximum instantaneous temperature difference.
   */
  public int getMaxInstTempDiffBetweenDeviceAndOutside(String outsideDeviceIDStr,
      String insideDeviceIDStr, LocalDateTime initialTime, LocalDateTime finalTime, int timeDelta) throws Exception{
    DatePeriod datePeriod = new DatePeriod(initialTime, finalTime);
    DeviceID insideDeviceID = new DeviceID(insideDeviceIDStr);
    DeviceID outsideDeviceID = new DeviceID(outsideDeviceIDStr);
    SensorTypeID sensorTypeID = new SensorTypeID("Temperature");

    /* Get readings for the inside and outside devices */
    List<Log> insideReadings = logService.getDeviceReadingsBySensorTypeAndTimePeriod(insideDeviceID,
        sensorTypeID, datePeriod);
    List<Log> outsideReadings = logService.getDeviceReadingsBySensorTypeAndTimePeriod(
        outsideDeviceID, sensorTypeID, datePeriod);
    TimeDelta timeDeltaObj = new TimeDelta(timeDelta);
    /* Get the maximum temperature difference */
    return logService.getMaxDifferenceBetweenReadingsThatAreWithinTimeDelta(insideReadings,
        outsideReadings, timeDeltaObj);
  }
}
