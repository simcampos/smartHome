/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator.set_integer_actuator;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import smarthome.ddd.IActuatorValue;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.IntegerLimits;
import smarthome.domain.value_object.ModelPath;
import smarthome.utils.Validator;
import smarthome.utils.visitor_pattern.IActuatorVisitor;

public class SetIntegerActuator implements IActuator {

  private final ActuatorName actuatorName;
  private final ModelPath modelPath;
  private final ActuatorTypeID actuatorTypeID;
  private final DeviceID deviceID;
  private final IntegerLimits limits;
  @Getter(AccessLevel.NONE)
  private ActuatorID actuatorID;
  @Getter(AccessLevel.NONE)
  private SetIntegerValue value;

  /**
   * Constructor for SetIntegerActuator
   *
   * @param deviceID       is the ID of the device associated with the actuator
   * @param modelPath      is the path of the model associated with the actuator
   * @param actuatorTypeID is the ID of the actuator type
   * @param actuatorName   is the name of the actuator
   */
  public SetIntegerActuator(DeviceID deviceID, ModelPath modelPath, ActuatorTypeID actuatorTypeID,
      ActuatorName actuatorName, IntegerLimits limits) {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(actuatorName, "ActuatorName");
    validateActuatorTypeID(actuatorTypeID);
    Validator.validateNotNull(limits, "SetIntegerActuatorLimits");

    this.actuatorTypeID = actuatorTypeID;
    this.actuatorName = actuatorName;
    this.modelPath = modelPath;
    this.deviceID = deviceID;
    this.limits = limits;
    generateActuatorID();
  }

  /**
   * Constructor for SetIntegerActuator
   *
   * @param deviceID       is the ID of the device associated with the actuator
   * @param modelPath      is the path of the model associated with the actuator
   * @param actuatorTypeID is the ID of the actuator type
   * @param actuatorName   is the name of the actuator
   * @param actuatorID     is the ID of the actuator
   */
  public SetIntegerActuator(DeviceID deviceID, ModelPath modelPath, ActuatorTypeID actuatorTypeID,
      ActuatorName actuatorName, IntegerLimits limits, ActuatorID actuatorID) {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(actuatorName, "ActuatorName");
    validateActuatorTypeID(actuatorTypeID);
    Validator.validateNotNull(limits, "SetIntegerActuatorLimits");
    Validator.validateNotNull(actuatorID, "ActuatorID");

    this.actuatorTypeID = actuatorTypeID;
    this.actuatorName = actuatorName;
    this.modelPath = modelPath;
    this.deviceID = deviceID;
    this.limits = limits;
    this.actuatorID = actuatorID;

  }

  /**
   * Generates actuatorID
   */

  private void generateActuatorID() {
    this.actuatorID = new ActuatorID(UUID.randomUUID().toString());
  }


  /**
   * Validates the actuatorTypeID
   *
   * @param actuatorTypeID is the ID of the actuator type
   */
  private void validateActuatorTypeID(ActuatorTypeID actuatorTypeID) {
    Validator.validateNotNull(actuatorTypeID, "ActuatorTypeID");

    if (!actuatorTypeID.getID().equals("SetInteger")) {
      throw new IllegalArgumentException("ActuatorTypeID must be SetInteger");
    }
  }


  /**
   * Getter for actuatorID
   *
   * @return actuatorID
   */
  @Override
  public ActuatorID getID() {
    return this.actuatorID;
  }

  /**
   * Getter for actuatorName
   *
   * @return actuatorName
   */
  @Override
  public ActuatorName getName() {
    return this.actuatorName;
  }

  /**
   * Getter for modelPath
   *
   * @return modelPath
   */
  @Override
  public ModelPath getModelPath() {
    return this.modelPath;
  }

  /**
   * Getter for actuatorTypeID
   *
   * @return actuatorTypeID
   */
  @Override
  public ActuatorTypeID getActuatorTypeID() {
    return this.actuatorTypeID;
  }

  /**
   * Getter for deviceID
   *
   * @return deviceID
   */
  @Override
  public DeviceID getDeviceID() {
    return this.deviceID;
  }

  /**
   * Getter for limits
   *
   * @return limits
   */
  public IntegerLimits getLimits() {
    return this.limits;
  }

  /**
   * Sets the value within the range
   *
   * @return SetIntegerValue
   */
  @Override
  public SetIntegerValue setValue(IActuatorValue value) {
    if (value == null) {
      throw new IllegalArgumentException("Value cannot be null");
    }

    int nValue = Integer.parseInt(value.toString());

    if (nValue < limits.getLowerLimit()) {
      throw new IllegalArgumentException("Value cannot be less than the lower limit.");
    } else if (nValue > limits.getUpperLimit()) {
      throw new IllegalArgumentException("Value cannot be greater than the upper limit.");
    } else if (value instanceof SetIntegerValue) {
      this.value = (SetIntegerValue) value;
      return (SetIntegerValue) value;
    }

    return null;
  }

  /**
   * Accepts the visitor
   *
   * @param visitor The visitor.
   */
  @Override
  public String accept(IActuatorVisitor visitor) {
    visitor.visitorSetIntegerActuator(this);
    return this.toString();
  }

  /**
   * Method to compare two instances
   *
   * @param object The object to compare with.
   * @return true if the objects are equal, false otherwise.
   */

  @Override
  public boolean equals(Object object) {
    if (object instanceof SetIntegerActuator setIntegerActuator) {
      return this.actuatorID.equals(setIntegerActuator.actuatorID);
    }
    return false;
  }

  /**
   * Method to get hash code
   *
   * @return hash code
   */
  @Override
  public int hashCode() {
    return actuatorID.hashCode();
  }

  /**
   * Method to get string representation
   *
   * @return string representation
   */
  @Override
  public String toString() {
    return "SetIntegerActuator:" + "ActuatorID:" + actuatorID + ", ActuatorName:" + actuatorName
        + ", ModelPath:" + modelPath + ", ActuatorTypeID:" + actuatorTypeID + ", DeviceID="
        + deviceID + ", Value:" + value + ", Limits:" + limits;
  }
}
