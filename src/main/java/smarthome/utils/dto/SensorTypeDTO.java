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
 * Data Transfer Object (DTO) representing a sensor type.
 */
public class SensorTypeDTO extends RepresentationModel<SensorTypeDTO>  implements IDTO {

  public final String sensorTypeID;
  public final String description;
  public final String unitID;

  /**
   * Constructs a new SensorTypeDTO object.
   *
   * @param description The description of the sensor type.
   * @param unitID                  The unit of unit for the sensor type.
   */
  public SensorTypeDTO(String sensorTypeID, String description, String unitID) {
    this.sensorTypeID = sensorTypeID;
    this.description = description;
    this.unitID = unitID;
  }

  /**
   * Returns a string representation of the SensorTypeDTO object.
   *
   * @return A string representation of the SensorTypeDTO object.
   */
  @Override
  public String toString() {
    return sensorTypeID + " " + description + " " + unitID;
  }
}
