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
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.utils.PathEncoder;
import smarthome.utils.Validator;
import smarthome.utils.dto.ActuatorModelDTO;

@Component
public class ActuatorModelAssembler implements IAssembler<ActuatorModel, ActuatorModelDTO> {

  /**
   * Converts an ActuatorModel domain entity to an ActuatorModelDTO data transfer object.
   *
   * @param domainEntity is the domain entity to be converted.
   * @return The ActuatorModelDTO data transfer object.
   */
  @Override
  public ActuatorModelDTO domainToDTO(ActuatorModel domainEntity) {
    Validator.validateNotNull(domainEntity, "Actuator Model");

    String actuatorModelName = domainEntity.getName().getActuatorModelName();
    String actuatorModelPath = domainEntity.getID().toString();
    actuatorModelPath = PathEncoder.encode(actuatorModelPath);

    return new ActuatorModelDTO(actuatorModelPath, actuatorModelName);
  }

  /**
   * Converts a list of ActuatorModel domain entities to a list of ActuatorModelDTO data transfer
   * objects.
   *
   * @param domainEntities is the list of domain entities to be converted.
   * @return The list of ActuatorModelDTO data transfer objects.
   */
  @Override
  public List<ActuatorModelDTO> domainToDTO(List<ActuatorModel> domainEntities) {
    if (domainEntities == null) {
      throw new IllegalArgumentException("The list of Actuator Models cannot be null.");
    }

    return domainEntities.stream().map(this::domainToDTO)
        .toList();
  }
}
