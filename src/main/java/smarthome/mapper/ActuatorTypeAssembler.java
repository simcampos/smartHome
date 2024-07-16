/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import smarthome.ddd.IAssembler;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.utils.Validator;
import smarthome.utils.dto.ActuatorTypeDTO;

@Component
public class ActuatorTypeAssembler implements IAssembler<ActuatorType, ActuatorTypeDTO> {


  /**
   * Converts an ActuatorType domain entity to an ActuatorTypeDTO data transfer object.
   *
   * @param actuatorType is the domain entity to be converted.
   * @return The ActuatorTypeDTO data transfer object.
   */
  @Override
  public ActuatorTypeDTO domainToDTO(ActuatorType actuatorType) {
    Validator.validateNotNull(actuatorType, "Actuator Type");

    String actuatorTypeID = actuatorType.getID().getID();
    String actuatorTypeDescription = actuatorType.getActuatorTypeName().toString();
    String unit = actuatorType.getUnit().toString();

    return new ActuatorTypeDTO(actuatorTypeID, actuatorTypeDescription,
        unit);
  }


  /**
   * Converts a list of ActuatorType domain entities to a list of ActuatorTypeDTO data transfer
   * objects.
   *
   * @param actuatorTypes is the list of domain entities to be converted.
   * @return The list of ActuatorTypeDTO data transfer objects.
   */
  @Override
  public List<ActuatorTypeDTO> domainToDTO(List<ActuatorType> actuatorTypes) {
    if (actuatorTypes == null) {
      throw new IllegalArgumentException("The list of ActuatorTypes cannot be null.");
    }
    return actuatorTypes.stream().map(this::domainToDTO).toList();
  }
}
