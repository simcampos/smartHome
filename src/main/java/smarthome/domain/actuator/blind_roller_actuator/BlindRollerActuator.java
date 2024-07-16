/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator.blind_roller_actuator;

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

/**
 * Represents a Blind Roller Actuator in the Smart Home Domain. This actuator is responsible for
 * controlling blind roller devices.
 */
public class BlindRollerActuator implements IActuator {

  private final DeviceID deviceID;
  private final ModelPath modelPath;
  private final ActuatorTypeID actuatorTypeID;
  private final ActuatorName actuatorName;
  @Getter(AccessLevel.NONE)
  private ActuatorID actuatorID;
  @Getter(AccessLevel.NONE)
  private BlindRollerValue value;

  /**
   * Constructs a new BlindRollerActuator with the specified parameters.
   *
   * @param deviceID       The ID of the device associated with this actuator.
   * @param actuatorTypeID The type ID of the actuator.
   * @param actuatorName   The name of the actuator.
   * @param modelPath      The model path of the actuator.
   */
  public BlindRollerActuator(
      DeviceID deviceID,
      ModelPath modelPath,
      ActuatorTypeID actuatorTypeID,
      ActuatorName actuatorName) {
    Validator.validateNotNull(deviceID, "DeviceID");
    validateActuatorTypeID(actuatorTypeID);
    Validator.validateNotNull(actuatorName, "ActuatorName");
    Validator.validateNotNull(modelPath, "ModelPath");

    generateActuatorID();
    this.actuatorTypeID = actuatorTypeID;
    this.actuatorName = actuatorName;
    this.modelPath = modelPath;
    this.deviceID = deviceID;
  }

  /**
   * Constructs a new BlindRollerActuator with the specified parameters.
   *
   * @param deviceID       The ID of the device associated with this actuator.
   * @param modelPath      The model path of the actuator.
   * @param actuatorTypeID The type ID of the actuator.
   * @param actuatorName   The name of the actuator.
   * @param actuatorID     The ID of the actuator.
   */
  public BlindRollerActuator(
      DeviceID deviceID, ModelPath modelPath, ActuatorTypeID actuatorTypeID,
      ActuatorName actuatorName, ActuatorID actuatorID) {
    Validator.validateNotNull(deviceID, "DeviceID");
    validateActuatorTypeID(actuatorTypeID);
    Validator.validateNotNull(actuatorName, "ActuatorName");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(actuatorID, "ActuatorID");

    this.deviceID = deviceID;
    this.actuatorID = actuatorID;
    this.actuatorTypeID = actuatorTypeID;
    this.actuatorName = actuatorName;
    this.modelPath = modelPath;
  }

  /**
   * Validates and sets the actuator type ID.
   *
   * @param actuatorTypeID The actuator type ID to be validated.
   * @throws IllegalArgumentException If the actuatorTypeID is null.
   */
  private void validateActuatorTypeID(ActuatorTypeID actuatorTypeID) {
    Validator.validateNotNull(actuatorTypeID, "ActuatorTypeID");
    if (!actuatorTypeID.getID().equals("BlindRoller")) {
      throw new IllegalArgumentException("The value of 'actuatorTypeID' should be 'BlindRoller'.");
    }
  }

  /**
   * Generates a unique actuator ID.
   */
  private void generateActuatorID() {
    this.actuatorID = new ActuatorID(UUID.randomUUID().toString());
  }

  /**
   * Returns the actuator ID.
   *
   * @return The actuator ID.
   */
  @Override
  public ActuatorID getID() {
    return actuatorID;
  }

  /**
   * Returns the actuator name.
   *
   * @return The actuator name.
   */
  @Override
  public ActuatorName getName() {
    return actuatorName;
  }

  /**
   * Returns the model path of the actuator.
   *
   * @return The model path.
   */
  @Override
  public ModelPath getModelPath() {
    return modelPath;
  }

  /**
   * Returns the actuator type ID.
   *
   * @return The actuator type ID.
   */
  @Override
  public ActuatorTypeID getActuatorTypeID() {
    return actuatorTypeID;
  }

  /**
   * Returns the device ID associated with this actuator.
   *
   * @return The device ID.
   */
  @Override
  public DeviceID getDeviceID() {
    return deviceID;
  }

  /**
   * Sets the value of the actuator if the value object is of the correct type.
   *
   * @param value The value to set.
   * @return The set value if successful, null otherwise.
   */
  @Override
  public BlindRollerValue setValue(IActuatorValue value) {
   if (value instanceof BlindRollerValue blindRollerValue) {
      this.value = blindRollerValue;
      return blindRollerValue;
    }
    return null;
  }

  /**
   * Method to compare two instances
   *
   * @param object The object to compare with.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof BlindRollerActuator actuator) {
      return actuatorID.equals(actuator.actuatorID);
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
    return "BlindRollerActuator:" + "actuatorID=" + actuatorID
        + ", deviceID=" + deviceID
        + ", modelPath=" + modelPath
        + ", actuatorTypeID=" + actuatorTypeID
        + ", actuatorName=" + actuatorName;
  }

  /**
   * Accepts the visitor.
   *
   * @param visitor The visitor to be accepted.
   * @return The string format of the {@link BlindRollerActuator}
   */
  @Override
  public String accept(IActuatorVisitor visitor) {
    visitor.visitorBlindRollerActuator(this);
    return this.toString();
  }
}
