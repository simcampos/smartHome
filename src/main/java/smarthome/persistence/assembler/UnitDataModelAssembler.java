/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.assembler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import smarthome.domain.unit.IUnitFactory;
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.persistence.data_model.UnitDataModel;
import smarthome.utils.Validator;

@Component
public class UnitDataModelAssembler implements IDataModelAssembler<UnitDataModel, Unit> {

  private final IUnitFactory unitFactory;

  /**
   * Class constructor
   *
   * @param unitFactory is the factory used to create Unit instances.
   */
  public UnitDataModelAssembler(IUnitFactory unitFactory) {
    Validator.validateNotNull(unitFactory, "Unit Factory");
    this.unitFactory = unitFactory;
  }


  /**
   * Converts a UnitDataModel instance to a Unit instance.
   *
   * @param unitDataModel is the domain entity to be converted.
   * @return a Unit instance.
   */
  @Override
  public Unit toDomain(UnitDataModel unitDataModel) {
    Validator.validateNotNull(unitDataModel, "Unit Data Model");

    UnitID unitID = new UnitID(unitDataModel.getUnitID());
    UnitSymbol unitSymbol = new UnitSymbol(unitDataModel.getUnitSymbol());
    UnitDescription unitDescription = new UnitDescription(unitDataModel.getUnitDescription());

    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol, unitID);

    return unit;
  }

  /**
   * Converts a list of UnitDataModel instances to a list of Unit instances.
   *
   * @param unitDataModels is the list of domain entities to be converted.
   * @return a list of Unit instances.
   */
  @Override
  public List<Unit> toDomain(List<UnitDataModel> unitDataModels) {
    List<Unit> units = new ArrayList<>();

    for (UnitDataModel unitDataModel : unitDataModels) {
      Unit unit = toDomain(unitDataModel);
      units.add(unit);
    }
    return units;
  }

}
