/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils.visitor_pattern;

import smarthome.domain.actuator.blind_roller_actuator.BlindRollerActuator;
import smarthome.domain.actuator.set_decimal_actuator.SetDecimalActuator;
import smarthome.domain.actuator.set_integer_actuator.SetIntegerActuator;
import smarthome.domain.actuator.switch_actuator.SwitchActuator;

public interface IActuatorVisitor {

  String visitorSetIntegerActuator(SetIntegerActuator setIntegerActuator);

  String visitorSetDecimalActuator(SetDecimalActuator setDecimalActuator);

  String visitorSwitchActuator(SwitchActuator setBooleanActuator);

  String visitorBlindRollerActuator(BlindRollerActuator blindRollerActuator);


}
