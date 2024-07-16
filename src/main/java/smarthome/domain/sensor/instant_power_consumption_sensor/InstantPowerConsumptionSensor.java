/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.instant_power_consumption_sensor;

import java.util.Objects;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.Validator;
import smarthome.utils.ValueSimulator;
import smarthome.utils.visitor_pattern.ISensorVisitor;

@Getter
public class InstantPowerConsumptionSensor implements ISensor {

  private final ModelPath modelPath;
  private final SensorName sensorName;
  @Getter(AccessLevel.NONE)
  private SensorID sensorID;
  private final SensorTypeID sensorTypeID;
  @Getter(AccessLevel.NONE)
  private InstantPowerConsumptionValue instantPowerConsumptionValue;
  private final DeviceID deviceID;

  /**
   * Constructor of the class.
   *
   * @param deviceID     The device ID.
   * @param modelPath    The model path.
   * @param sensorName   The sensor name.
   * @param sensorTypeID The sensor type ID.
   */
  public InstantPowerConsumptionSensor(
      DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName) {
    Validator.validateNotNull(modelPath, "ModelPath");
    this.modelPath = modelPath;
    Validator.validateNotNull(sensorName, "SensorName");
    this.sensorName = sensorName;
    validateSensorTypeID(sensorTypeID);
    this.sensorTypeID = sensorTypeID;
    Validator.validateNotNull(deviceID, "DeviceID");
    this.deviceID = deviceID;

    generateInstantPowerConsumptionID();
  }

  /**
   * Constructor of the class, including the sensor ID.
   *
   * @param deviceID     is the device id
   * @param modelPath    is the model path
   * @param sensorTypeID is the sensor type id
   * @param sensorName   is the sensor name
   * @param sensorID     is the sensor id
   */
  public InstantPowerConsumptionSensor(
      DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName,
      SensorID sensorID) {

    Validator.validateNotNull(modelPath, "ModelPath");
    this.modelPath = modelPath;
    Validator.validateNotNull(sensorName, "SensorName");
    this.sensorName = sensorName;
    validateSensorTypeID(sensorTypeID);
    this.sensorTypeID = sensorTypeID;
    Validator.validateNotNull(deviceID, "DeviceID");
    this.deviceID = deviceID;
    Validator.validateNotNull(sensorID, "SensorID");
    this.sensorID = sensorID;
  }

  /**
   * Generates a new InstantPowerConsumptionID.
   */
  private void generateInstantPowerConsumptionID() {
    this.sensorID = new SensorID(UUID.randomUUID().toString());
  }


  /**
   * Validates the sensor type ID.
   *
   * @param sensorTypeID The sensor type ID.
   */
  private void validateSensorTypeID(SensorTypeID sensorTypeID) {
    Validator.validateNotNull(sensorTypeID, "SensorTypeID");

    if (!Objects.equals(sensorTypeID.getID(), "InstantPowerConsumption")) {
      throw new IllegalArgumentException("SensorTypeID must be of type 'InstantPowerConsumption'");
    }
  }


  /**
   * Gets the sensor ID.
   *
   * @return The sensor ID.
   */
  @Override
  public SensorID getID() {
    return this.sensorID;
  }

  /**
   * Returns the value of the sensor.
   */
  @Override
  public InstantPowerConsumptionValue getValue() {
    double instantPowerConsumptionValue = ValueSimulator.generateRandomValue(0.0, 100);
    this.instantPowerConsumptionValue =
        new InstantPowerConsumptionValue(instantPowerConsumptionValue);

    return this.instantPowerConsumptionValue;
  }

  /**
   * Compares this instance with another instance.
   *
   * @param o is the object to be compared.
   * @return true if the instances are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof InstantPowerConsumptionSensor sensor) {
      return this.sensorID.equals(sensor.getID());
    }
    return false;
  }

  /**
   * Generates a hash code for the sensor.
   *
   * @return The hash code.
   */
  @Override
  public int hashCode() {
    return this.sensorID.hashCode();
  }

  /**
   * toString method
   */
  @Override
  public String toString() {
    return "InstantPowerConsumptionSensor: " +
        "modelPath=" + modelPath +
        ", sensorName=" + sensorName +
        ", sensorID=" + sensorID +
        ", sensorTypeID=" + sensorTypeID +
        ", deviceID=" + deviceID;
  }

  /**
   * Accepts a visitor.
   */
  public String accept(ISensorVisitor visitor) {
    visitor.visitInstantPowerSensor(this);
    return this.toString();
  }
}




