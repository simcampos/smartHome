/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils.dto;

import org.springframework.hateoas.RepresentationModel;
import smarthome.ddd.IDTO;

/**
 * Data Transfer Object (DTO) representing an actuator type.
 */
public class ActuatorTypeDTO extends RepresentationModel<ActuatorTypeDTO> implements IDTO {

  /**
   * Description of the actuator type.
   */
  public final String actuatorTypeID;
  public final String actuatorTypeDescription;
  public final String unit;

  /**
   * Constructs a new ActuatorTypeDTO object.
   *
   * @param actuatorTypeID The description of the actuator type.
   */
  public ActuatorTypeDTO(String actuatorTypeID, String actuatorTypeDescription, String unit) {
    this.actuatorTypeID = actuatorTypeID;
    this.actuatorTypeDescription = actuatorTypeDescription;
    this.unit = unit;
  }

  /**
   * Returns a string representation of the ActuatorTypeDTO object.
   *
   * @return A string representation of the ActuatorTypeDTO object.
   */
  @Override
  public String toString() {
    return actuatorTypeID + " " + actuatorTypeDescription + " " + unit;
  }
}

