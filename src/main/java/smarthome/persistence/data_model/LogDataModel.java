/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.data_model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.LocalDateTime;
import smarthome.domain.log.Log;

@Entity
@Table(name = "Logs")
public class LogDataModel {

  @Id
  private String logID;
  private String deviceID;
  private String sensorID;
  private LocalDateTime timestamp;
  private String readingValue;
  private String description;
  private String unit;
  @Version
  private long version;


  /**
   * Empty class constructor
   */
  public LogDataModel() {
  }

  /**
   * Class constructor
   */
  public LogDataModel(Log log) {
    this.logID = log.getID().getID();
    this.deviceID = log.getDeviceID().getID();
    this.sensorID = log.getSensorID().getID();
    this.timestamp = log.getTimeStamp();
    this.readingValue = log.getReadingValue().getValue();
    this.description = log.getDescription().getID();
    this.unit = log.getUnit().getID();
  }

  /**
   * Method to return the log ID.
   */
  public String getLogID() {
    return this.logID;
  }

  /**
   * Method to return the device ID.
   */
  public String getDeviceID() {
    return this.deviceID;
  }

  /**
   * Method to return the sensor ID.
   */
  public String getSensorID() {
    return this.sensorID;
  }

  /**
   * Method to return the timestamp.
   */
  public LocalDateTime getTimestamp() {
    return this.timestamp;
  }

  /**
   * Method to return the reading value.
   */
  public String getReadingValue() {
    return this.readingValue;
  }

  /**
   * Method to return the description.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Method to return the unit.
   */
  public String getUnit() {
    return this.unit;
  }
}
