/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.electric_consumption_wh_sensor;

import java.util.Objects;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.Validator;
import smarthome.utils.visitor_pattern.ISensorVisitor;

/**
 * Represents a sensor that measures electric consumption in watt-hours.
 */
@Getter
public class ElectricConsumptionWhSensor implements ISensor {

  /**
   * Constructs a new ElectricConsumptionWhSensor with the given parameters.
   *
   * @param deviceID the ID of the device
   * @param modelPath the model path of the sensor
   * @param sensorTypeID the type ID of the sensor
   * @param sensorName the name of the sensor
   * @param datePeriod the period during which the sensor measures consumption
   */
  private final DeviceID deviceID;
  private final ModelPath modelPath;
  private final SensorTypeID sensorTypeID;
  @Getter(AccessLevel.NONE)
  private ElectricConsumptionWhValue electricConsumptionWhValue;
  private final SensorName sensorName;
  @Getter(AccessLevel.NONE)
  private SensorID sensorID;
  private final DatePeriod datePeriod;

  /**
   * Constructs a new ElectricConsumptionWhSensor with the given parameters.
   *
   * @param deviceID     the ID of the device
   * @param modelPath    the model path of the sensor
   * @param sensorTypeID the type ID of the sensor
   * @param sensorName   the name of the sensor
   * @param datePeriod   the period during which the sensor measures consumption
   */

  public ElectricConsumptionWhSensor(DeviceID deviceID, ModelPath modelPath,
      SensorTypeID sensorTypeID, SensorName sensorName, DatePeriod datePeriod) {
    Validator.validateNotNull(deviceID, "DeviceID");
    this.deviceID = deviceID;
    validateSensorTypeID(sensorTypeID);
    this.sensorTypeID = sensorTypeID;
    Validator.validateNotNull(modelPath, "ModelPath");
    this.modelPath = modelPath;
    Validator.validateNotNull(sensorName, "SensorName");
    this.sensorName = sensorName;
    Validator.validateNotNull(datePeriod, "DatePeriod");
    this.datePeriod = datePeriod;

    generateElectricConsumptionWhID();
  }

  public ElectricConsumptionWhSensor(DeviceID deviceID, ModelPath modelPath,
      SensorTypeID sensorTypeID, SensorName sensorName, DatePeriod datePeriod, SensorID sensorID) {
    Validator.validateNotNull(deviceID, "DeviceID");
    this.deviceID = deviceID;
    validateSensorTypeID(sensorTypeID);
    this.sensorTypeID = sensorTypeID;
    Validator.validateNotNull(modelPath, "ModelPath");
    this.modelPath = modelPath;
    Validator.validateNotNull(sensorName, "SensorName");
    this.sensorName = sensorName;
    Validator.validateNotNull(datePeriod, "DatePeriod");
    this.datePeriod = datePeriod;
    Validator.validateNotNull(sensorID, "SensorID");
    this.sensorID = sensorID;
  }

  /**
   * Generates a new ElectricConsumptionWhSensor ID.
   */

  private void generateElectricConsumptionWhID() {
    this.sensorID = new SensorID(UUID.randomUUID().toString());
  }

  /**
   * Validates the given parameters.
   *
   * @param sensorTypeID the type ID of the sensor
   */
  private void validateSensorTypeID(SensorTypeID sensorTypeID) {
    Validator.validateNotNull(sensorTypeID, "SensorTypeID");

    if (!Objects.equals("ElectricConsumptionWh", sensorTypeID.getID())) {
      throw new IllegalArgumentException("SensorTypeID must be of type 'ElectricConsumptionWh'");
    }
  }


  /**
   * Returns the ID of the sensor.
   *
   * @return the ID of the sensor
   */
  @Override
  public SensorID getID() {
    return sensorID;
  }

  /**
   * Returns the period during which the sensor measures consumption.
   *
   * @return the period during which the sensor measures consumption
   */

  public DatePeriod getDatePeriod() {
    return datePeriod;
  }

  /**
   * Returns the value of the sensor.
   */
  @Override
  public ElectricConsumptionWhValue getValue() {
    int consumptionInWh = getConsumptionInWhForGivenPeriod();
    electricConsumptionWhValue = new ElectricConsumptionWhValue(consumptionInWh);
    return electricConsumptionWhValue;
  }

  /**
   * Returns the consumption in watt-hours for the given period.
   *
   * @return the consumption in watt-hours for the given period
   */
  private int getConsumptionInWhForGivenPeriod() {
    return datePeriod.getDurationInMinutes() * 5;
  }

  /**
   * Method to compare two instances
   *
   * @param object the object to compare
   * @return true if the instances are equal, false otherwise
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof ElectricConsumptionWhSensor electricConsumptionWhSensor) {
      return this.sensorID.equals(electricConsumptionWhSensor.sensorID);
    }
    return false;
  }

  /**
   * Method to get hash code
   *
   * @return the hash code
   */
  @Override
  public int hashCode() {
    return sensorID.hashCode();
  }

  /**
   * Returns the period during which the sensor measures consumption.
   *
   * @return the period during which the sensor measures consumption
   */
  @Override
  public String toString() {
    return deviceID + " " + modelPath + " " + sensorTypeID + " " + electricConsumptionWhValue + " "
        + sensorName
        + " " + sensorID + " " + datePeriod;
  }

  /**
   * Accept method for the visitor pattern.
   */
  public String accept(ISensorVisitor visitor) {
    visitor.visitElectricConsumptionWhSensor(this);
    return this.toString();
  }

}
