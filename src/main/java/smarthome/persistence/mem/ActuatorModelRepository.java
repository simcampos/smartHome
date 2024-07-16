/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.mem;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.repository.IActuatorModelRepository;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;
import smarthome.utils.Validator;

public class ActuatorModelRepository implements IActuatorModelRepository {

  private final Map<ModelPath, ActuatorModel> DATA = new LinkedHashMap<>();

  /**
   * Save an ActuatorModel. If the ActuatorModel is null, throw an IllegalArgumentException.
   *
   * @param actuatorModel is the domain entity to be saved.
   * @return the saved ActuatorModel.
   */
  @Override
  public ActuatorModel save(ActuatorModel actuatorModel) {
    Validator.validateNotNull(actuatorModel, "ActuatorModel");

    if (containsOfIdentity(actuatorModel.getID())) {
      throw new IllegalArgumentException("ActuatorModel already exists.");
    } else {
      DATA.put(actuatorModel.getID(), actuatorModel);
    }
    return actuatorModel;
  }

  /**
   * Find all ActuatorModels.
   *
   * @return a list of all ActuatorModels.
   */
  @Override
  public List<ActuatorModel> findAll() {

    return List.copyOf(DATA.values().stream().toList());
  }

  /**
   * Find an ActuatorModel by its identity.
   *
   * @param actuatorModelID is the unique identifier of the domain entity.
   * @return the ActuatorModel if found, otherwise Optional.empty().
   */
  @Override
  public Optional<ActuatorModel> ofIdentity(ModelPath actuatorModelID) {

    return Optional.ofNullable(DATA.get(actuatorModelID));
  }


  /**
   * Checks if an ActuatorModel exists by its identity.
   *
   * @param actuatorModelID is the unique identifier of the domain entity.
   * @return true if the ActuatorModel exists, otherwise false.
   */
  public boolean containsOfIdentity(ModelPath actuatorModelID) {
    return DATA.containsKey(actuatorModelID);
  }

  /**
   * Find all ActuatorModels by their ActuatorTypeID.
   */
  @Override
  public List<ActuatorModel> findBy_actuatorTypeID(ActuatorTypeID actuatorTypeID) {
    return DATA.values().stream()
        .filter(actuatorModel -> actuatorModel.getActuatorTypeID().equals(actuatorTypeID)).toList();
  }
}