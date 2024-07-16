/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator_type;

import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;

/**
 * Interface defining a factory for creating ActuatoryType instances. Provides a method to create a
 * actuatorType with specific description.
 */
public interface IActuatorTypeFactory {

  /**
   * Creates and returns a new  instance with the provided description.
   *
   * @param name   the description of the actuatorType
   * @param unitID the unitID of the actuatorType
   * @return a newly created ActuatorType instance
   */
  ActuatorType createActuatorType(TypeDescription name, UnitID unitID);

  /**
   * Creates and returns a new  instance with the provided description.
   *
   * @param name           the description of the actuatorType
   * @param unitID         the unitID of the actuatorType
   * @param actuatorTypeID the actuatorTypeID of the actuatorType
   * @return a newly created ActuatorType instance
   */
  ActuatorType createActuatorType(TypeDescription name, UnitID unitID,
      ActuatorTypeID actuatorTypeID);
}

