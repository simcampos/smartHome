/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils.dto;

import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import smarthome.ddd.IDTO;

@EqualsAndHashCode
public class DeviceTypeDTO extends RepresentationModel<DeviceTypeDTO> implements IDTO {

  public final String description;

  /**
   * Constructs a new DeviceTypeDTO object.
   *
   * @param description The description of the device type.
   */
  public DeviceTypeDTO(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return description;
  }
}
