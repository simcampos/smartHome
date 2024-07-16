/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator_model;

import smarthome.ddd.IAggregateRoot;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;
import smarthome.utils.Validator;

public class ActuatorModel implements IAggregateRoot<ModelPath> {


  private final ModelPath modelPath;
  private final ActuatorModelName name;
  private final ActuatorTypeID actuatorTypeID;

  /**
   * ActuatorModel constructor
   *
   * @param modelPath The path to the model
   * @param name      The name of the actuator model
   */
  ActuatorModel(
      ModelPath modelPath, ActuatorModelName name, ActuatorTypeID actuatorTypeID) {
    Validator.validateNotNull(name, "ActuatorModelName");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(actuatorTypeID, "ActuatorTypeID");
    this.modelPath = modelPath;
    this.name = name;
    this.actuatorTypeID = actuatorTypeID;
  }


  /**
   * Equals method for actuator model
   *
   * @param object Object
   * @return boolean
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    ActuatorModel actuatorModel = (ActuatorModel) object;
    return modelPath.equals(actuatorModel.modelPath);
  }

  /**
   * Hash code method
   */
  @Override
  public int hashCode() {
    return modelPath.hashCode();
  }

  /**
   * Get actuator model ID
   *
   * @return ActuatorModelID
   */
  @Override
  public ModelPath getID() {
    return modelPath;
  }

  /**
   * Get actuator model name
   *
   * @return ActuatorModelName
   */
  public ActuatorModelName getName() {
    return name;
  }

  /**
   * method to get sensor type id
   */
  public ActuatorTypeID getActuatorTypeID() {
    return actuatorTypeID;
  }

  /**
   * To string method for actuator model
   *
   * @return String
   */
  @Override
  public String toString() {
    return modelPath + " " + name + " " + actuatorTypeID;
  }
}
