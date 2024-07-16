/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils.dto;

import smarthome.ddd.IDTO;

public class LogDTO implements IDTO {

  public String logID;
  public String deviceID;
  public String sensorID;
  public String sensorTypeID;
  public String reading;
  public String timestamp;
  public String unitID;

  /**
   * Constructs a new LogDTO object with the specified log details.
   */
  public LogDTO(String logID, String deviceID, String sensorID, String sensorTypeID, String reading,
      String timestamp, String unitID) {
    this.logID = logID;
    this.deviceID = deviceID;
    this.sensorID = sensorID;
    this.sensorTypeID = sensorTypeID;
    this.reading = reading;
    this.timestamp = timestamp;
    this.unitID = unitID;
  }

  /**
   * toString method for LogDTO.
   */
  @Override
  public String toString() {
    return logID + " " + deviceID + " " + sensorID + " " + sensorTypeID + " " + reading + " "
        + timestamp + " " + unitID;
  }

}
