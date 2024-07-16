/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator_type;

import org.springframework.stereotype.Component;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;

@Component
public class ActuatorTypeFactoryImpl implements IActuatorTypeFactory {

  /**
   * Creates and returns a new ActuatorType instance with the provided description.
   *
   * @param name the description of the actuatorType
   * @return a newly created ActuatorType instance
   */
  @Override
  public ActuatorType createActuatorType(TypeDescription name, UnitID unitID) {
    return new ActuatorType(name, unitID);
  }

  /**
   * @param name
   * @param unitID
   * @param actuatorTypeID
   * @return
   */
  @Override
  public ActuatorType createActuatorType(TypeDescription name, UnitID unitID,
      ActuatorTypeID actuatorTypeID) {
    return new ActuatorType(name, unitID, actuatorTypeID);
  }
}
