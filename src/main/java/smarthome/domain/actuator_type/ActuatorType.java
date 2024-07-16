/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator_type;

import smarthome.ddd.IAggregateRoot;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.utils.Validator;

public class ActuatorType implements IAggregateRoot<ActuatorTypeID> {

  private final TypeDescription name;
  private final UnitID unitID;
  private ActuatorTypeID id;

  /**
   * Creates a new {@link ActuatorType} instance using the provided actuator type name and unit.
   *
   * @param name the actuator type name, must not be null
   * @param unit the unit of the actuator type, must not be null
   */
  ActuatorType(TypeDescription name, UnitID unit) {
    Validator.validateNotNull(name, "Type Description");
    Validator.validateNotNull(unit, "Unit ID");
    generateID(name);

    this.name = name;
    this.unitID = unit;
  }

  /**
   * Creates a new {@link ActuatorType} instance using the provided actuator type name, unit and
   * actuator type ID.
   *
   * @param name           the actuator type name, must not be null
   * @param unit           the unit of the actuator type, must not be null
   * @param actuatorTypeID the actuator type ID, must not be null
   */
  public ActuatorType(TypeDescription name, UnitID unit, ActuatorTypeID actuatorTypeID) {
    Validator.validateNotNull(name, "Type Description");
    Validator.validateNotNull(unit, "Unit ID");
    Validator.validateNotNull(actuatorTypeID, "Actuator Type ID");

    this.name = name;
    this.unitID = unit;
    this.id = actuatorTypeID;
  }

  private void generateID(TypeDescription name) {
    id = new ActuatorTypeID(name.toString());
  }


  /**
   * Gets the actuator type name.
   *
   * @return the actuator type name
   */
  @Override
  public ActuatorTypeID getID() {
    return id;
  }

  /**
   * Gets the actuator type name.
   *
   * @return the actuator type name
   */
  public TypeDescription getActuatorTypeName() {
    return name;
  }


  /**
   * Method to get unit
   *
   * @return unit
   */
  public UnitID getUnit() {
    return unitID;
  }

  /**
   * Method to compare two instances
   *
   * @param object the object to compare
   * @return true if the instances are equal, false otherwise
   */

  @Override
  public boolean equals(Object object) {
    if (object instanceof ActuatorType actuatorType) {
      return this.id.equals(actuatorType.id);
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
    return id.hashCode();
  }

  /**
   * Method to get string representation
   *
   * @return string representation
   */
  @Override
  public String toString() {
    return id + " " + name + " " + unitID;
  }
}
