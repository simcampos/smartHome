/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator.switch_actuator;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import smarthome.ddd.IActuatorValue;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.utils.Validator;
import smarthome.utils.visitor_pattern.IActuatorVisitor;

public class SwitchActuator implements IActuator {

  private final DeviceID deviceID;
  private final ActuatorName actuatorName;
  private final ModelPath modelPath;
  private final ActuatorTypeID actuatorTypeID;
  @Getter(AccessLevel.NONE)
  private ActuatorID actuatorID;
  @Getter(AccessLevel.NONE)
  private SwitchActuatorValue value;

  /**
   * Instantiates a new Switch actuator.
   *
   * @param deviceID       the device id
   * @param modelPath      the model path
   * @param actuatorTypeID the actuator type id
   * @param actuatorName   the actuator name
   */

  public SwitchActuator(DeviceID deviceID, ModelPath modelPath, ActuatorTypeID actuatorTypeID,
      ActuatorName actuatorName) {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(actuatorName, "ActuatorName");
    validateActuatorTypeID(actuatorTypeID);

    generateActuatorID();
    this.actuatorTypeID = actuatorTypeID;
    this.actuatorName = actuatorName;
    this.modelPath = modelPath;
    this.deviceID = deviceID;
  }

  /**
   * Instantiates a new Switch actuator.
   *
   * @param deviceID       the device id
   * @param modelPath      the model path
   * @param actuatorTypeID the actuator type id
   * @param actuatorName   the actuator name
   * @param actuatorID     the actuator id
   */
  public SwitchActuator(DeviceID deviceID, ModelPath modelPath, ActuatorTypeID actuatorTypeID,
      ActuatorName actuatorName, ActuatorID actuatorID) {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(actuatorName, "ActuatorName");
    validateActuatorTypeID(actuatorTypeID);
    Validator.validateNotNull(actuatorID, "ActuatorID");

    this.actuatorTypeID = actuatorTypeID;
    this.actuatorName = actuatorName;
    this.modelPath = modelPath;
    this.deviceID = deviceID;
    this.actuatorID = actuatorID;
  }

  /**
   * generate actuator id
   */
  private void generateActuatorID() {
    this.actuatorID = new ActuatorID(UUID.randomUUID().toString());
  }


  /**
   * Validate actuator type id.
   *
   * @param actuatorTypeID the actuator type id
   */
  private void validateActuatorTypeID(ActuatorTypeID actuatorTypeID) {
    Validator.validateNotNull(actuatorTypeID, "ActuatorTypeID");

    if (!actuatorTypeID.getID().equals("Switch")) {
      throw new IllegalArgumentException("The value of 'actuatorTypeID' should be 'Switch'.");
    }
  }

  /**
   * Gets ID.
   *
   * @return the actuator id
   */
  @Override
  public ActuatorID getID() {
    return actuatorID;
  }

  /**
   * Gets value.
   *
   * @return the actuator name
   */

  @Override
  public ActuatorName getName() {
    return actuatorName;
  }

  /**
   * Gets value.
   *
   * @return the model path
   */

  @Override
  public ModelPath getModelPath() {
    return modelPath;
  }

  /**
   * Gets the actuator type id.
   *
   * @return the actuator type id
   */
  @Override
  public ActuatorTypeID getActuatorTypeID() {
    return actuatorTypeID;
  }

  /**
   * Gets the device id.
   *
   * @return the device id
   */
  @Override
  public DeviceID getDeviceID() {
    return deviceID;
  }

  /**
   * Sets the value of the actuator.
   *
   * @param value the value object of the actuator
   * @return the value object of this Actuator
   */
  @Override
  public SwitchActuatorValue setValue(IActuatorValue value) {
    if (value instanceof SwitchActuatorValue switchActuatorValue) {
      this.value = switchActuatorValue;
      return switchActuatorValue;
    }
    return null;
  }

  /**
   * Returns the Switch Actuator in a string format.
   *
   * @return the string format of the Switch Actuator
   */
  @Override
  public String toString() {
    return "SwitchActuator: DeviceID=" + deviceID + ", ActuatorName=" + actuatorName
        + ", ModelPath=" + modelPath
        + ", ActuatorTypeID=" + actuatorTypeID + ", ActuatorID=" + actuatorID + ", Value=" + value;
  }

  /**
   * Compares the Switch Actuator with another object.
   *
   * @param object is the object to be compared.
   * @return true if the object is equal to the Switch Actuator, false otherwise.
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof SwitchActuator switchActuator) {
      return switchActuator.deviceID.equals(this.deviceID);
    }
    return false;
  }

  /**
   * Returns the hash code of the Switch Actuator.
   *
   * @return the hash code of the Switch Actuator.
   */
  @Override
  public int hashCode() {
    return this.actuatorID.hashCode();
  }

  /**
   * Accepts the visitor.
   *
   * @param visitor is the visitor to be accepted.
   * @return the string format of the Switch Actuator.
   */
  @Override
  public String accept(IActuatorVisitor visitor) {
    visitor.visitorSwitchActuator(this);
    return this.toString();
  }
}
