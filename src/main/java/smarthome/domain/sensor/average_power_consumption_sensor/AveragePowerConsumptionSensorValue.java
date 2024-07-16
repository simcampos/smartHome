/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.average_power_consumption_sensor;


import smarthome.ddd.IValueObject;

public class AveragePowerConsumptionSensorValue implements IValueObject {

  public double dValue;

  /**
   * Creates a new PowerConsumptionSensorValue with a given value.
   *
   * @param dValue the value to be set.
   */

  public AveragePowerConsumptionSensorValue(double dValue) {
    validateValue(dValue);
  }

  /**
   * Gets the value of the PowerConsumptionSensorValue.
   *
   * @return the value of the PowerConsumptionSensorValue.
   */
  public double getValue() {
    return this.dValue;
  }

  /**
   * Gets the value of the PowerConsumptionSensorValue.
   *
   * @return the value of the PowerConsumptionSensorValue.
   */

  private void validateValue(double dValue) {
    if (dValue < 0) {
      throw new IllegalArgumentException("Value must be positive");
    }
    this.dValue = dValue;
  }

  public String toString() {
    return this.dValue + "";
  }

  /**
   * Overrides the equals method to compare two PowerConsumptionSensorValue objects.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue) {
      double epsilon = 0.001;
      double a = Math.abs(dValue - averagePowerConsumptionSensorValue.dValue);
      return Math.min(a, epsilon) == a;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Double.hashCode(dValue);
  }
}
