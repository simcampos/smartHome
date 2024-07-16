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
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.repository.IActuatorTypeRepository;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.utils.Validator;


public class ActuatorTypeRepository implements IActuatorTypeRepository {

  private final Map<ActuatorTypeID, ActuatorType> DATA = new LinkedHashMap<>();

  /**
   * Save an ActuatorType. If the ActuatorType is null, throw an IllegalArgumentException.
   *
   * @param actuatorType is the domain entity to be saved.
   * @return the saved ActuatorType.
   */
  @Override
  public ActuatorType save(ActuatorType actuatorType) {
    Validator.validateNotNull(actuatorType, "ActuatorType");

    if (containsOfIdentity(actuatorType.getID())) {
      throw new IllegalArgumentException("ActuatorType already exists.");
    } else {
      DATA.put(actuatorType.getID(), actuatorType);
    }
    return actuatorType;
  }

  /**
   * Find all ActuatorTypes.
   *
   * @return a list of all ActuatorTypes.
   */
  @Override
  public List<ActuatorType> findAll() {
    List<ActuatorType> allActuatorTypes = DATA.values().stream().toList();
    return allActuatorTypes;
  }

  /**
   * Find an ActuatorType by its identity.
   *
   * @param actuatorTypeID is the unique identifier of the domain entity.
   * @return the ActuatorType if found, otherwise Optional.empty().
   */
  @Override
  public Optional<ActuatorType> ofIdentity(ActuatorTypeID actuatorTypeID) {
    Optional<ActuatorType> actuatorType = Optional.ofNullable(DATA.get(actuatorTypeID));

    return actuatorType;
  }

  /**
   * Checks if an ActuatorType exists by its name.
   *
   * @param actuatorTypeName is the name of the ActuatorType.
   * @return true if the ActuatorType exists, otherwise false.
   */
  public boolean existsOfName(TypeDescription actuatorTypeName) {
    return DATA.values().stream()
        .anyMatch(actuatorType -> actuatorType.getActuatorTypeName().equals(actuatorTypeName));
  }

  /**
   * Checks if the repository contains an ActuatorType with the given identity.
   *
   * @param actuatorTypeID is the unique identifier of the domain entity.
   * @return true if the repository contains the ActuatorType, otherwise false.
   */
  @Override
  public boolean containsOfIdentity(ActuatorTypeID actuatorTypeID) {
    return DATA.containsKey(actuatorTypeID);
  }
}