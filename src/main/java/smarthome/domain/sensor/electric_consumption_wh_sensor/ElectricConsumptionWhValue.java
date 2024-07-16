/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.electric_consumption_wh_sensor;

import smarthome.ddd.IValueObject;

/**
 * Represents the value of electric consumption in watt-hours.
 */
public class ElectricConsumptionWhValue implements IValueObject {

  /**
   * Constructs a new ElectricConsumptionWhValue with the given parameters.
   */
  private int value;

  /**
   * Constructs a new ElectricConsumptionWhValue with the given parameters.
   *
   * @param value the value of electric consumption in watt-hours
   */

  public ElectricConsumptionWhValue(int value) {
    setValue(value);
  }

  /**
   * Returns the value of electric consumption in watt-hours.
   */

  private void setValue(int value) {
    if (value < 0) {
      throw new IllegalArgumentException("Consumption cannot be negative.");
    } else {
      this.value = value;
    }
  }

  /**
   * Returns string representation of the value of electric consumption in watt-hours.
   */
  @Override
  public String toString() {
    return "ElectricConsumptionWh{" + +value +
        '}';
  }

  /**
   * Returns true if the given object is equal to this object.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof ElectricConsumptionWhValue objectElectricConsumptionWhValue) {
      return value == objectElectricConsumptionWhValue.value;
    }
    return false;
  }

  /**
   * Returns hashcode
   */
  @Override
  public int hashCode() {
    return Integer.hashCode(value);
  }
}
