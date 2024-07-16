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
import org.springframework.stereotype.Component;
import smarthome.domain.actuator.IActuator;
import smarthome.utils.Validator;

@Component
@Entity
@Table(name = "Actuator")
public class ActuatorDataModel {

  @Id
  private String actuatorID;
  private String deviceID;
  private String modelPath;
  private String actuatorTypeID;
  private String actuatorName;
  private String integerLowerBond;
  private String integerUpperBond;
  private String decimalLowerBond;
  private String decimalUpperBond;
  @Version
  private long version;


  public ActuatorDataModel() {
  }

  /**
   * Class constructor
   */
  public ActuatorDataModel(IActuator actuator) {
    Validator.validateNotNull(actuator, "Actuator");
    setGenericActuatorParameters(actuator);

  }

  /**
   * Set the generic actuator parameters to the data model
   *
   * @param actuator
   */
  //Setters
  public void setGenericActuatorParameters(IActuator actuator) {
    resetActuator();
    this.actuatorID = actuator.getID().getID();
    this.deviceID = actuator.getDeviceID().getID();
    this.modelPath = actuator.getModelPath().getID();
    this.actuatorTypeID = actuator.getActuatorTypeID().getID();
    this.actuatorName = actuator.getName().getName();
  }

  /**
   * Method to return the integer lower bond
   */
  public String getIntegerLowerBond() {
    return this.integerLowerBond;
  }

  /**
   * Set the integer lower bond from actuators that have this specification
   *
   * @param integerLowerBond
   */

  public void setIntegerLowerBond(int integerLowerBond) {
    this.integerLowerBond = String.valueOf(integerLowerBond);
  }

  /**
   * Method to return the integer upper bond
   */
  public String getIntegerUpperBond() {
    return this.integerUpperBond;
  }

  /**
   * Set the integer upper bond from actuators that have this specification
   *
   * @param integerUpperBond
   */
  public void setIntegerUpperBond(int integerUpperBond) {
    this.integerUpperBond = String.valueOf(integerUpperBond);
  }
  // This section is for getter methods

  /**
   * Method to return the decimal lower bond
   */
  public String getDecimalLowerBond() {
    return this.decimalLowerBond;
  }

  /**
   * set de lower decimal bond from actuators that have this specification
   *
   * @param decimalLowerBond
   */
  public void setDecimalLowerBond(double decimalLowerBond) {
    this.decimalLowerBond = String.valueOf(decimalLowerBond);
  }

  /**
   * Method to return the decimal upper bond
   */
  public String getDecimalUpperBond() {
    return this.decimalUpperBond;
  }

  /**
   * Set the upper decimal bond from actuators that have this specification
   *
   * @param decimalUpperBond
   */
  public void setDecimalUpperBond(double decimalUpperBond) {
    this.decimalUpperBond = String.valueOf(decimalUpperBond);
  }

  /**
   * Method to return the actuator ID.
   *
   * @return the actuator ID
   */
  public String getActuatorID() {
    return this.actuatorID;
  }

  /**
   * Method to return the device ID.
   *
   * @return the device ID
   */
  public String getDeviceID() {
    return this.deviceID;
  }

  /**
   * Method to return the model path.
   *
   * @return the model path
   */
  public String getModelPath() {
    return this.modelPath;
  }

  /**
   * Method to return the actuator type ID.
   *
   * @return the actuator type ID
   */
  public String getActuatorTypeID() {
    return this.actuatorTypeID;
  }

  /**
   * Method to return the actuator name.
   *
   * @return the actuator name
   */
  public String getActuatorName() {
    return this.actuatorName;
  }

  private void resetActuator(){
    this.actuatorID = null;
    this.deviceID = null;
    this.modelPath = null;
    this.actuatorTypeID = null;
    this.actuatorName = null;
    this.integerLowerBond = null;
    this.integerUpperBond = null;
    this.decimalLowerBond = null;
    this.decimalUpperBond = null;
  }

  @Override
  public String toString() {
    return "ActuatorDataModel{" +
        "actuatorID='" + actuatorID + '\'' +
        ", deviceID='" + deviceID + '\'' +
        ", modelPath='" + modelPath + '\'' +
        ", actuatorTypeID='" + actuatorTypeID + '\'' +
        ", actuatorName='" + actuatorName + '\'' +
        ", integerLowerBond='" + integerLowerBond + '\'' +
        ", integerUpperBond='" + integerUpperBond + '\'' +
        ", decimalLowerBond='" + decimalLowerBond + '\'' +
        ", decimalUpperBond='" + decimalUpperBond + '\'' +
        '}';
  }

}
