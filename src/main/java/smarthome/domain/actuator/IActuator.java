/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator;

import smarthome.ddd.IActuatorValue;
import smarthome.ddd.IAggregateRoot;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.utils.visitor_pattern.IActuatorVisitor;

public interface IActuator extends IAggregateRoot<ActuatorID> {


  /**
   * Gets the actuator ID.
   *
   * @return The actuator ID.
   */
  ActuatorID getID();

  /**
   * Gets the actuator name.
   *
   * @return The actuator name.
   */
  ActuatorName getName();

  /**
   * Gets the model path.
   *
   * @return The model path.
   */
  ModelPath getModelPath();

  /**
   * Gets the actuator type ID.
   *
   * @return The actuator type ID.
   */
  ActuatorTypeID getActuatorTypeID();

  /**
   * Gets the device ID.
   */
  DeviceID getDeviceID();

  /**
   * Returns the actuator attributes in a string format.
   *
   * @return The actuator attributes in a string format.
   */
  String toString();

  /**
   * Method to get the value object of the actuator.
   *
   * @return the value.
   */
  IActuatorValue setValue(IActuatorValue value);


  /**
   * Method to accept the visitor.
   *
   * @param visitor The visitor.
   */
  String accept(IActuatorVisitor visitor);
}
