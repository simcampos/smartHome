/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils.dto;

import smarthome.ddd.IDTO;

public class UnitDTO implements IDTO {

  public final String unitID;
  public final String description;
  public final String unitSymbol;

  /**
   * Constructor for the unitDTO class.
   *
   * @param unitID      is the ID of the unit.
   * @param description is the description of the unit.
   */
  public UnitDTO(String unitID, String description, String unitSymbol) {

    this.unitID = unitID;
    this.description = description;
    this.unitSymbol = unitSymbol;
  }

  @Override
  public String toString() {
    return unitID + " " + description + " " + unitSymbol;
  }
}
