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
 * Data Transfer Object (DTO) representing information about an actuator.
 */
public class ActuatorDTO implements IDTO {

  /**
   * The unique identifier of the actuator.
   */
  public final String id;

  /**
   * The type of the actuator.
   */
  public final String actuatorTypeID;

  /**
   * The name of the actuator.
   */
  public final String actuatorName;

  /**
   * The description of the actuator.
   */
  public final String modelPath;

  public final String deviceID;

  public String actuatorValue;

  /**
   * Constructs a new ActuatorDTO object with the specified actuator details.
   *
   * @param actuatorID     The unique identifier of the actuator.
   * @param actuatorTypeID The type of the actuator.
   * @param actuatorName   The name of the actuator.
   * @param modelPath      The description of the actuator.
   */
  public ActuatorDTO(
      String actuatorID,
      String actuatorTypeID,
      String actuatorName,
      String modelPath,
      String deviceID) {
    this.id = actuatorID;
    this.actuatorTypeID = actuatorTypeID;
    this.actuatorName = actuatorName;
    this.modelPath = modelPath;
    this.deviceID = deviceID;
    this.actuatorValue = null;
  }

  public ActuatorDTO(
      String actuatorID,
      String actuatorTypeID,
      String actuatorName,
      String modelPath,
      String deviceID, String actuatorValue) {
    this.id = actuatorID;
    this.actuatorTypeID = actuatorTypeID;
    this.actuatorName = actuatorName;
    this.modelPath = modelPath;
    this.deviceID = deviceID;
    this.actuatorValue = actuatorValue;
  }

  @Override
  public String toString() {
    return id + " " + actuatorTypeID + " " + actuatorName + " " + modelPath + " " + deviceID;
  }
}
