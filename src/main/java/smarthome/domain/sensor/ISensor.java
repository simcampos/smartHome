/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor;

import org.springframework.ui.Model;
import smarthome.ddd.IAggregateRoot;
import smarthome.ddd.IValueObject;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.visitor_pattern.ISensorVisitor;

public interface ISensor extends IAggregateRoot<SensorID> {

  DeviceID getDeviceID();

  ModelPath getModelPath();

  SensorName getSensorName();

  SensorTypeID getSensorTypeID();

  /**
   * Returns the sensor attributes in a string format.
   *
   * @return The sensor attributes in a string format.
   */
  String toString();

  /**
   * Method to get the value object of the sensor.
   *
   * @return the value.
   */
  IValueObject getValue();

  /**
   * Accepts the visitor.
   *
   * @param visitor The visitor.
   */
  String accept(ISensorVisitor visitor);
}
