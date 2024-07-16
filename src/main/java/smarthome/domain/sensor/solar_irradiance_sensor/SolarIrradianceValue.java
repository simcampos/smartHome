/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.solar_irradiance_sensor;

import smarthome.ddd.IValueObject;

public class SolarIrradianceValue implements IValueObject {

  private final int value;

  /**
   * Constructor for SolarIrradianceValue
   *
   * @param value is the value of the solar irradiance sensor
   */
  public SolarIrradianceValue(int value) {
    this.value = value;
  }

  /**
   * Returns the value of the solar irradiance sensor as a string.
   *
   * @return The value of the solar irradiance sensor as a string.
   */
  @Override
  public String toString() {
    return value + "";
  }
}
