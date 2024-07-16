/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.unit;

import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;

public interface IUnitFactory {

  /**
   * Creates and returns a new {@link Unit} instance.
   *
   * @param unitDescription The description of the unit type, defining its nature or category
   *                        (e.g., temperature, pressure).
   * @param unitSymbol      The unit of unit (e.g., Celsius, Pascal) associated with this
   *                        unit type.
   * @return A new {@link Unit} instance configured with the specified description and unit.
   */
  Unit createUnit(UnitDescription unitDescription, UnitSymbol unitSymbol);


  /**
   * Creates and returns a new {@link Unit} instance.
   *
   * @param unitDescription The description of the unit type, defining its nature or category
   *                        (e.g., temperature, pressure).
   * @param unitSymbol      The unit of unit (e.g., Celsius, Pascal) associated with this
   *                        unit type.
   * @param unitID          The unique identifier for the unit type.
   * @return A new {@link Unit} instance configured with the specified description, unit, and ID.
   */
  Unit createUnit(UnitDescription unitDescription, UnitSymbol unitSymbol, UnitID unitID);
}
