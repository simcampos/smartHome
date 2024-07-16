/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.spring_data.unit;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import smarthome.domain.repository.IUnitRepository;
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.UnitID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.UnitDataModel;
import smarthome.utils.Validator;

@Repository
public class UnitSpringDataRepository implements IUnitRepository {

  IUnitSpringDataRepository repository;

  IDataModelAssembler<UnitDataModel, Unit> assembler;

  /**
   * Constructs a new repository instance with the specified repository and data model assembler.
   *
   * @param repository the repository to use
   * @param assembler  the converter to transform data models to domain models and vice versa
   */
  public UnitSpringDataRepository(IUnitSpringDataRepository repository,
      IDataModelAssembler<UnitDataModel, Unit> assembler) {
    Validator.validateNotNull(repository, "Unit repository");
    this.repository = repository;

    Validator.validateNotNull(assembler, "Unit data model assembler");
    this.assembler = assembler;
  }

  /**
   * Saves the specified unit entity to the database.
   *
   * @param entity the unit entity to save
   * @return the saved unit entity
   * @throws IllegalArgumentException if the entity is null
   */
  @Override
  public Unit save(Unit entity) {
    Validator.validateNotNull(entity, "Unit");

    UnitDataModel dataModel = new UnitDataModel(entity);

    repository.save(dataModel);

    return entity;
  }

  /**
   * Finds all unit entities in the database.
   *
   * @return a list of all unit entities
   */
  @Override
  public List<Unit> findAll() {
    List<UnitDataModel> unitDataModels = repository.findAll();

    return assembler.toDomain(unitDataModels);
  }

  /**
   * Finds the unit entity with the specified identity.
   *
   * @param id the identity of the unit entity to find
   * @return an optional containing the unit entity if it exists, otherwise an empty optional
   * @throws IllegalArgumentException if the identity is null
   */
  @Override
  public Optional<Unit> ofIdentity(UnitID id) {
    Optional<UnitDataModel> unitDataModel = repository.findById(id.getID());

    if (unitDataModel.isPresent()) {
      Unit domain = assembler.toDomain(unitDataModel.get());
      return Optional.of(domain);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Checks if a unit entity with the specified identity exists in the database.
   *
   * @param id the identity of the unit entity to check
   * @return true if the unit entity exists, false otherwise
   */
  @Override
  public boolean containsOfIdentity(UnitID id) {
    return repository.existsById(id.getID());
  }

}
