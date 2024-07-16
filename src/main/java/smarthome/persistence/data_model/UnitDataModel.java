/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.data_model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import smarthome.domain.unit.Unit;
import smarthome.utils.Validator;


@Entity
@Table(name = "unit")
public class UnitDataModel {

  @Id
  private String unitID;
  private String unitSymbol;
  private String unitDescription;

  @Version
  private long version;

  /**
   * Default constructor.
   */
  public UnitDataModel() {

  }

  /**
   * Constructs a UnitDataModel object from a Unit object.
   *
   * @param unit The Unit object to construct from.
   */
  public UnitDataModel(Unit unit) {
    Validator.validateNotNull(unit, "Unit");
    this.unitID = unit.getID().getID();
    this.unitSymbol = unit.getUnitSymbol().getUnit();
    this.unitDescription = unit.getUnitDescription().getDescription();
  }

  /**
   * Gets the unit ID.
   *
   * @return The unit ID.
   */
  public String getUnitID() {
    return this.unitID;
  }

  /**
   * Gets the unit symbol.
   *
   * @return The unit symbol.
   */
  public String getUnitSymbol() {
    return this.unitSymbol;
  }

  /**
   * Gets the unit description.
   *
   * @return The unit description.
   */
  public String getUnitDescription() {
    return this.unitDescription;
  }
}


