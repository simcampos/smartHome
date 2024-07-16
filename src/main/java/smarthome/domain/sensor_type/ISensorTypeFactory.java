/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor_type;

import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;

/**
 * Interface defining a factory for creating sensorType instances. Provides a method to create a
 * sensorType with specific description and unit.
 */
public interface ISensorTypeFactory {

  /**
   * Creates and returns a new sensorType instance with the provided description and unit.
   *
   * @param name the description of the sensorType
   * @param unit the unit of the sensorType
   * @return a newly created SensorType instance
   */
  SensorType createSensorType(TypeDescription name, UnitID unit);

  /**
   * Create a sensorType with the following parameters:
   *
   * @param sensorTypeID is the sensorTypeID
   * @param name         is the sensor type name.
   * @param unitID       is the unit.
   * @return a sensorType.
   */
  SensorType createSensorType(SensorTypeID sensorTypeID, TypeDescription name, UnitID unitID);
}
