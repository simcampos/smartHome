/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.unit;

import org.springframework.stereotype.Component;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;

/**
 * Factory implementation for creating {@link Unit} instances.
 */
@Component
public class UnitFactoryImpl implements IUnitFactory {

  /**
   * Creates and returns a new {@link Unit} instance with the provided unit value,
   * unit type, and unit time.
   */

  @Override
  public Unit createUnit(UnitDescription unitDescription, UnitSymbol unitSymbol)
      throws IllegalArgumentException {
    return new Unit(unitDescription, unitSymbol);
  }

  /**
   * Creates and returns a new {@link Unit} instance with the provided unit value,
   * unit type, unit time, and unit ID.
   */
  @Override
  public Unit createUnit(UnitDescription unitDescription, UnitSymbol unitSymbol, UnitID unitID)
      throws IllegalArgumentException {
    return new Unit(unitDescription, unitSymbol, unitID);
  }
}
