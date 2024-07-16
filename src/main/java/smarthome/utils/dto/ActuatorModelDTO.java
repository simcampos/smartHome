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
 * ActuatorModelDTO is a DTO class that represents the actuator model.
 */
public class ActuatorModelDTO implements IDTO {

  /**
   * Attributes of class ActuatorModelDTO.
   */
  public String actuatorModelPath;

  /**
   * Attributes of class ActuatorModelDTO.
   */
  public String actuatorModelName;



  /**
   * Constructor of class ActuatorModelDTO.
   */
  public ActuatorModelDTO(String actuatorModelPath, String actuatorModelName) {
    this.actuatorModelPath = actuatorModelPath;
    this.actuatorModelName = actuatorModelName;
  }

  @Override
  public String toString() {
    return actuatorModelPath + " " + actuatorModelName;
  }
}
