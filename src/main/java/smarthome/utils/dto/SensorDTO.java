/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils.dto;

import smarthome.ddd.IDTO;

/**
 * Data Transfer Object (DTO) representing a sensor.
 */
public class SensorDTO implements IDTO {

  public final String deviceID;
  public final String modelPath;
  public final String sensorTypeID;
  public final String sensorID;
  public final String sensorName;

  /**
   * Constructor of SensorDTO.
   *
   * @param deviceID
   * @param modelPath
   * @param sensorID
   * @param sensorTypeID
   * @param sensorName
   */
  public SensorDTO(
      String deviceID, String modelPath, String sensorID, String sensorTypeID, String sensorName) {
    this.deviceID = deviceID;
    this.modelPath = modelPath;
    this.sensorTypeID = sensorTypeID;
    this.sensorID = sensorID;
    this.sensorName = sensorName;
  }

  @Override
  public String toString() {
    return deviceID + " " + modelPath + " " + sensorTypeID + " " + sensorID + " " + sensorName;
  }
}
