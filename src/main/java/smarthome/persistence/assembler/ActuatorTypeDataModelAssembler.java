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
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.actuator_type.IActuatorTypeFactory;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.persistence.data_model.ActuatorTypeDataModel;
import smarthome.utils.Validator;

@Component
public class ActuatorTypeDataModelAssembler implements
    IDataModelAssembler<ActuatorTypeDataModel, ActuatorType> {

  private final IActuatorTypeFactory actuatorTypeFactory;

  /**
   * Class constructor.
   *
   * @param actuatorTypeFactory is the factory used to create ActuatorType instances.
   */
  public ActuatorTypeDataModelAssembler(IActuatorTypeFactory actuatorTypeFactory) {
    Validator.validateNotNull(actuatorTypeFactory, "Actuator Type Factory");
    this.actuatorTypeFactory = actuatorTypeFactory;
  }


  /**
   * Method to convert a domain entity into a DataModel.
   *
   * @param domainEntity is the domain entity to be converted.
   * @return the DataModel.
   */
  @Override
  public ActuatorType toDomain(ActuatorTypeDataModel domainEntity) {
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(domainEntity.getActuatorTypeID());
    TypeDescription actuatorTypeName = new TypeDescription(domainEntity.getActuatorTypeName());
    UnitID unitID = new UnitID(domainEntity.getUnitID());

    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(actuatorTypeName, unitID,
        actuatorTypeID);

    return actuatorType;
  }

  /**
   * Method to convert a list of domain entities into a list of DataModels.
   *
   * @param domainEntities is the list of domain entities to be converted.
   * @return the list of DataModels.
   */
  @Override
  public List<ActuatorType> toDomain(List<ActuatorTypeDataModel> domainEntities) {
    List<ActuatorType> actuatorTypes = new ArrayList<>();

    for (ActuatorTypeDataModel actuatorTypeDataModel : domainEntities) {
      ActuatorType actuatorType = toDomain(actuatorTypeDataModel);
      actuatorTypes.add(actuatorType);
    }
    return actuatorTypes;
  }
}

