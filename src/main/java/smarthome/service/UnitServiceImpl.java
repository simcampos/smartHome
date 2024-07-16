/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import smarthome.ddd.IRepository;
import smarthome.domain.unit.IUnitFactory;
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.utils.Validator;

@Service
public class UnitServiceImpl implements IUnitService {

  private final IRepository<UnitID, Unit> unitRepository;
  private final IUnitFactory unitFactory;

  /**
   * Constructor for unitTypeService.
   *
   * @param unitRepository The repository for the unit type.
   * @param unitFactory    The factory for the unit type.
   */
  public UnitServiceImpl(IRepository<UnitID, Unit> unitRepository, IUnitFactory unitFactory) {
    Validator.validateNotNull(unitRepository, "unitType repository");
    Validator.validateNotNull(unitFactory, "unitType factory");

    this.unitRepository = unitRepository;
    this.unitFactory = unitFactory;
  }


  /**
   * Creates a new unitType and saves it in the repository.
   *
   * @param description The description of the unit type.
   * @param unit        The unit of the unit type.
   * @return The created and saved unitType object.
   */
  @Override
  public Unit addunitType(UnitDescription description, UnitSymbol unit) {
    validateDescription(description);
    validateUnit(unit);

    Unit unitUnit = unitFactory.createUnit(description, unit);
    return unitRepository.save(unitUnit);
  }

  /**
   * Validates that the description is not null or empty.
   *
   * @param description The description to validate.
   * @throws IllegalArgumentException if the description is null or empty.
   */
  private void validateDescription(UnitDescription description) {
    if (description == null || description.getDescription().trim().isEmpty()) {
      throw new IllegalArgumentException("unit type description cannot be null or empty.");
    }
  }

  /**
   * Validates that the unit is not null or empty.
   *
   * @param unit The unit to validate.
   * @throws IllegalArgumentException if the unit is null or empty.
   */
  private void validateUnit(UnitSymbol unit) {
    if (unit == null || unit.getUnit().trim().isEmpty()) {
      throw new IllegalArgumentException("unit type unit cannot be null or empty.");
    }
  }

  /**
   * Finds a unitType by its ID.
   *
   * @param unitID The unique identifier of the unitType.
   * @return An Optional containing the found unitType, or an empty Optional if not found.
   */
  @Override
  public Optional<Unit> getunitTypeById(UnitID unitID) {
    if (unitID == null) {
      throw new IllegalArgumentException("Please enter a valid sensor type ID.");
    }
    return unitRepository.ofIdentity(unitID);
  }

  /**
   * Returns a list of all unitTypes.
   *
   * @return A List containing all unitTypes.
   */
  @Override
  public List<Unit> getAllunitTypes() {
    return unitRepository.findAll();
  }

}
