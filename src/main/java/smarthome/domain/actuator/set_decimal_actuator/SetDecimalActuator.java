/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator.set_decimal_actuator;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import smarthome.ddd.IActuatorValue;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DecimalLimits;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.utils.Validator;
import smarthome.utils.visitor_pattern.IActuatorVisitor;

/**
 * Represents a decimal actuator used to set decimal values within specified limits.
 */
public class SetDecimalActuator implements IActuator {

  private final ActuatorName actuatorName;
  private final ModelPath modelPath;
  private final ActuatorTypeID actuatorTypeID;
  private final DeviceID deviceID;
  private final DecimalLimits limits;
  @Getter(AccessLevel.NONE)
  private ActuatorID actuatorID;
  @Getter(AccessLevel.NONE)
  private SetDecimalValue value;

  /**
   * Constructs a SetDecimalActuator object with the provided parameters.
   *
   * @param deviceID       The ID of the device associated with the actuator.
   * @param modelPath      The path of the model associated with the actuator.
   * @param actuatorTypeID The ID of the actuator type.
   * @param actuatorName   The name of the actuator.
   * @param limits         The limits within which the actuator can set values.
   */
  public SetDecimalActuator(
      DeviceID deviceID,
      ModelPath modelPath,
      ActuatorTypeID actuatorTypeID,
      ActuatorName actuatorName,
      DecimalLimits limits) {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(actuatorName, "ActuatorName");
    validateActuatorTypeID(actuatorTypeID);
    Validator.validateNotNull(limits, "SetDecimalActuatorLimits");

    generateActuatorID();
    this.actuatorName = actuatorName;
    this.modelPath = modelPath;
    this.deviceID = deviceID;
    this.actuatorTypeID = actuatorTypeID;
    this.limits = limits;
  }

  /**
   * Constructs a SetDecimalActuator object with the provided parameters.
   *
   * @param deviceID       The ID of the device associated with the actuator.
   * @param modelPath      The path of the model associated with the actuator.
   * @param actuatorTypeID The ID of the actuator type.
   * @param actuatorName   The name of the actuator.
   * @param limits         The limits within which the actuator can set values.
   * @param actuatorID     The ID of the actuator.
   */
  public SetDecimalActuator(
      DeviceID deviceID, ModelPath modelPath, ActuatorTypeID actuatorTypeID,
      ActuatorName actuatorName, DecimalLimits limits, ActuatorID actuatorID) {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(actuatorName, "ActuatorName");
    validateActuatorTypeID(actuatorTypeID);
    Validator.validateNotNull(limits, "SetDecimalActuatorLimits");
    Validator.validateNotNull(actuatorID, "ActuatorID");

    this.actuatorName = actuatorName;
    this.modelPath = modelPath;
    this.deviceID = deviceID;
    this.actuatorTypeID = actuatorTypeID;
    this.limits = limits;
    this.actuatorID = actuatorID;
  }

  /**
   * Generates a new actuator ID.
   */
  private void generateActuatorID() {
    this.actuatorID = new ActuatorID(UUID.randomUUID().toString());
  }

  /**
   * Validates the provided actuator type ID.
   *
   * @param actuatorTypeID The ID of the actuator type.
   */
  private void validateActuatorTypeID(ActuatorTypeID actuatorTypeID) {
    Validator.validateNotNull(actuatorTypeID, "ActuatorTypeID");

    if (!actuatorTypeID.getID().equals("SetDecimal")) {
      throw new IllegalArgumentException("The value of 'actuatorTypeID' should be 'SetDecimal'.");
    }
  }

  /**
   * Gets the ID of the actuator.
   *
   * @return The ActuatorID object representing the ID of the actuator.
   */
  public ActuatorID getID() {
    return actuatorID;
  }

  /**
   * Gets the name of the actuator.
   *
   * @return The ActuatorName object representing the name of the actuator.
   */
  public ActuatorName getName() {
    return actuatorName;
  }

  /**
   * Gets the model path associated with the actuator.
   *
   * @return The ModelPath object representing the model path associated with the actuator.
   */
  public ModelPath getModelPath() {
    return modelPath;
  }

  /**
   * Gets the ID of the actuator type.
   *
   * @return The ActuatorTypeID object representing the ID of the actuator type.
   */
  public ActuatorTypeID getActuatorTypeID() {
    return actuatorTypeID;
  }

  /**
   * Gets the ID of the device associated with the actuator.
   *
   * @return The DeviceID object representing the ID of the device associated with the actuator.
   */
  public DeviceID getDeviceID() {
    return deviceID;
  }

  /**
   * Gets the limits within which the actuator can set values.
   *
   * @return The SetDecimalActuatorLimits object representing the limits of the actuator.
   */
  public DecimalLimits getLimits() {
    return limits;
  }

  /**
   * Sets the value of the actuator within the specified limits.
   *
   * @param value The value to be set for the actuator.
   * @return The SetDecimalValue object representing the set value of the actuator.
   * @throws IllegalArgumentException if the provided value is null or outside the specified
   *                                  limits.
   */
  public SetDecimalValue setValue(IActuatorValue value) {
    if (value == null) {
      throw new IllegalArgumentException("Value cannot be null");
    }
    double nValue = Double.parseDouble(value.toString());

    if (nValue < limits.getLowerLimit()) {
      throw new IllegalArgumentException("Value cannot be less than the lower limit.");
    } else if (nValue > limits.getUpperLimit()) {
      throw new IllegalArgumentException("Value cannot be greater than the upper limit.");
    } else if (value instanceof SetDecimalValue) {
      this.value = (SetDecimalValue) value;
      return this.value;
    }
    return null;
  }


  /**
   * Accepts the visitor.
   *
   * @param visitor is the visitor to be accepted.
   * @return the string format of the {@link SetDecimalActuator}
   */

  @Override
  public String accept(IActuatorVisitor visitor) {
    visitor.visitorSetDecimalActuator(this);
    return this.toString();
  }

  /**
   * Method to compare two instances
   *
   * @param object The object to compare with.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof SetDecimalActuator setDecimalActuator) {
      return actuatorID.equals(setDecimalActuator.actuatorID);
    }
    return false;
  }

  /**
   * Overrides the hashCode method to return the hash code of the actuator ID.
   */
  @Override
  public int hashCode() {
    return actuatorID.hashCode();
  }

  /**
   * Returns a string representation of the actuator.
   *
   * @return A string representation of the actuator.
   */
  @Override
  public String toString() {
    return "SetDecimalActuator:" + "ActuatorID: "
        + actuatorID
        + ", ActuatorName: "
        + actuatorName
        + ", ModelPath: "
        + modelPath
        + ", ActuatorTypeID: "
        + actuatorTypeID
        + ", DeviceID: "
        + deviceID
        + ", Limits: "
        + limits;
  }
}
