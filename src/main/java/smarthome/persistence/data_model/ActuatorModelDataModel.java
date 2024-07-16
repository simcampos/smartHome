/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.data_model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.utils.Validator;

@Entity
@Table(name = "ACTUATOR_MODEL")
public class ActuatorModelDataModel {

  @Id
  private String modelPath;
  private String actuatorModelName;
  private String actuatorTypeID;
  @Version
  private long version;

  /**
   * Class constructor
   */
  public ActuatorModelDataModel() {
  }

  public ActuatorModelDataModel(ActuatorModel actuatorModel) {
    Validator.validateNotNull(actuatorModel, "Actuator Model");
    this.actuatorModelName = actuatorModel.getName().getActuatorModelName();
    this.modelPath = actuatorModel.getID().getID();
    this.actuatorTypeID = actuatorModel.getActuatorTypeID().getID();
  }

  /**
   * Method to return the model path.
   *
   * @return modelPath
   */
  public String getModelPath() {
    return this.modelPath;
  }

  /**
   * Method to return the actuator model name.
   *
   * @return actuatorModelName
   */
  public String getActuatorModelName() {
    return this.actuatorModelName;
  }



  /**
   * Method to return the actuator type ID.
   *
   * @return actuatorTypeID
   */
  public String getActuatorTypeID() {
    return this.actuatorTypeID;
  }
}
