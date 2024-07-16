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
import smarthome.domain.repository.IUnitRepository;
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.UnitID;
import smarthome.utils.Validator;

public class UnitRepository implements IUnitRepository {

  private final Map<UnitID, Unit> DATA = new LinkedHashMap<>();

  /**
   * Save a unitType. If the unitType is null, throw an IllegalArgumentException. If
   * the unitType already exists, throw an IllegalArgumentException.
   *
   * @param entity is the domain entity to be saved.
   * @return the saved domain entity.
   */
  @Override
  public Unit save(Unit entity) {
    Validator.validateNotNull(entity, "unitType");

    if (containsOfIdentity(entity.getID())) {
      throw new IllegalArgumentException("unitType already exists.");
    } else {
      DATA.put(entity.getID(), entity);
    }
    return entity;
  }

  /**
   * Method to find all unitTypes.
   *
   * @return a list of all unitTypes.
   */
  @Override
  public List<Unit> findAll() {
    List<Unit> allUnits = DATA.values().stream().toList();
    return allUnits;
  }

  /**
   * Method to find a unitType by its unique identifier.
   *
   * @param objectID is the unique identifier of the domain entity.
   * @return the domain entity.
   */
  @Override
  public Optional<Unit> ofIdentity(UnitID objectID) {
    Optional<Unit> unitType = Optional.ofNullable(DATA.get(objectID));
    return unitType;
  }

  /**
   * Method to check if a unitType exists by its unique identifier.
   *
   * @param objectID is the unique identifier of the domain entity.
   * @return true if the domain entity exists, false otherwise.
   */
  @Override
  public boolean containsOfIdentity(UnitID objectID) {
    return DATA.containsKey(objectID);
  }
}
