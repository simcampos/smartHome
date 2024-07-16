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
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.utils.Validator;

@Entity
@Table(name = "Actuator_Type")
public class ActuatorTypeDataModel {

  @Id
  private String actuatorTypeID;
  private String actuatorTypeName;
  private String unitID;
  @Version
  private long version;

  /**
   * Default constructor.
   */
  public ActuatorTypeDataModel() {
  }

  /**
   * Constructor that initializes the ActuatorTypeDataModel with the given ActuatorType.
   *
   * @param actuatorType is the ActuatorType to be used to initialize the ActuatorTypeDataModel.
   */
  public ActuatorTypeDataModel(ActuatorType actuatorType) {
    Validator.validateNotNull(actuatorType, "Actuator Type");
    this.actuatorTypeID = actuatorType.getID().getID();
    this.actuatorTypeName = actuatorType.getActuatorTypeName().toString();
    this.unitID = actuatorType.getUnit().getID();
  }


  /**
   * Getter for the actuator type ID.
   *
   * @return the actuator type ID.
   */
  public String getActuatorTypeID() {
    return this.actuatorTypeID;
  }

  /**
   * Getter for the actuator type name.
   *
   * @return the actuator type name.
   */
  public String getActuatorTypeName() {
    return this.actuatorTypeName;
  }

  /**
   * Getter for the unit ID.
   *
   * @return the unit ID.
   */
  public String getUnitID() {
    return this.unitID;
  }
}

