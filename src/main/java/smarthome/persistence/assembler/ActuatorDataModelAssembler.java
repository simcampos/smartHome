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
import java.util.Objects;
import org.springframework.stereotype.Component;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.actuator.IActuatorFactory;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DecimalLimits;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.IntegerLimits;
import smarthome.domain.value_object.ModelPath;
import smarthome.persistence.data_model.ActuatorDataModel;
import smarthome.utils.Validator;

@Component
public class ActuatorDataModelAssembler implements
    IDataModelAssembler<ActuatorDataModel, IActuator> {

  private final List<Object> parameters = new ArrayList<>();
  private final IActuatorFactory actuatorFactory;

  public ActuatorDataModelAssembler(IActuatorFactory actuatorFactory) {
    Validator.validateNotNull(actuatorFactory, "Actuator factory");
    this.actuatorFactory = actuatorFactory;
  }

  @Override
  public IActuator toDomain(ActuatorDataModel domainEntity) {
    Validator.validateNotNull(domainEntity, "Actuator data model");
    parameters.clear();
    getDeviceID(domainEntity);
    getModelPath(domainEntity);
    getActuatorTypeID(domainEntity);
    getActuatorName(domainEntity);
    if (Objects.equals(domainEntity.getActuatorTypeID(), "SetInteger")){
      getIntegerLimits(domainEntity);
    }

    if (Objects.equals(domainEntity.getActuatorTypeID(), "SetDecimal")){
      getDecimalLimits(domainEntity);
    }
    getActuatorID(domainEntity);

    return actuatorFactory.create(parameters.toArray());
  }

  /**
   * Method to convert a list of ActuatorDataModel to a list of IActuator.
   *
   * @param domainEntities is the list of data models to be converted.
   * @return the list of domain entities.
   */
  @Override
  public List<IActuator> toDomain(List<ActuatorDataModel> domainEntities) {
    return List.of(domainEntities.stream().map(this::toDomain).toArray(IActuator[]::new));
  }

  private boolean getDeviceID(ActuatorDataModel actuatorDataModel) {
    DeviceID deviceID = new DeviceID(actuatorDataModel.getDeviceID());
    parameters.add(deviceID);
    return true;
  }

  private boolean getModelPath(ActuatorDataModel actuatorDataModel) {
    ModelPath modelPath = new ModelPath(actuatorDataModel.getModelPath());
    parameters.add(modelPath);
    return true;
  }

  private boolean getActuatorTypeID(ActuatorDataModel actuatorDataModel) {
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorDataModel.getActuatorTypeID());
    parameters.add(actuatorTypeID);
    return true;
  }

  private boolean getActuatorName(ActuatorDataModel actuatorDataModel) {
    ActuatorName actuatorName = new ActuatorName(actuatorDataModel.getActuatorName());
    parameters.add(actuatorName);
    return true;
  }

  private boolean getIntegerLimits(ActuatorDataModel actuatorDataModel) {
    if (actuatorDataModel.getIntegerLowerBond() != null
        || actuatorDataModel.getIntegerUpperBond() != null) {
      int integerLowerBond = Integer.parseInt(actuatorDataModel.getIntegerLowerBond());
      int integerUpperBond = Integer.parseInt(actuatorDataModel.getIntegerUpperBond());
      IntegerLimits integerLimits = new IntegerLimits(integerLowerBond, integerUpperBond);
      parameters.add(integerLimits);
      return true;
    }
    return false;
  }

  private boolean getDecimalLimits(ActuatorDataModel actuatorDataModel) {
    if (actuatorDataModel.getDecimalLowerBond() != null
        || actuatorDataModel.getDecimalUpperBond() != null) {
      double decimalLowerBond = Double.parseDouble(actuatorDataModel.getDecimalLowerBond());
      double decimalUpperBond = Double.parseDouble(actuatorDataModel.getDecimalUpperBond());
      DecimalLimits decimalLimits = new DecimalLimits(decimalLowerBond, decimalUpperBond);
      parameters.add(decimalLimits);
      return true;
    }
    return false;
  }

  private boolean getActuatorID(ActuatorDataModel actuatorDataModel) {
    ActuatorID actuatorID = new ActuatorID(actuatorDataModel.getActuatorID());
    parameters.add(actuatorID);
    return true;
  }
}
