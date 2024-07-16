/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.wind_sensor;

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
public class WindSensor implements ISensor {

  private final ModelPath modelPath;
  private final SensorName sensorName;
  @Getter(AccessLevel.NONE)
  private SensorID sensorID;
  private final SensorTypeID sensorTypeID;
  @Getter(AccessLevel.NONE)
  private WindSensorValue windSensorValue;
  private final DeviceID deviceID;

  public WindSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID,
      SensorName sensorName) {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(sensorName, "SensorName");
    validateSensorTypeID(sensorTypeID);

    generateWindID();

    this.modelPath = modelPath;
    this.sensorName = sensorName;
    this.sensorTypeID = sensorTypeID;
    this.deviceID = deviceID;
  }

  /**
   * Constructor for WindSensor.
   *
   * @param deviceID     The device ID.
   * @param modelPath    The model path.
   * @param sensorTypeID The sensor type ID.
   * @param sensorName   The sensor name.
   * @param sensorID     The sensor ID.
   */
  public WindSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID,
      SensorName sensorName, SensorID sensorID) {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(sensorName, "SensorName");
    validateSensorTypeID(sensorTypeID);
    Validator.validateNotNull(sensorID, "SensorID");

    this.modelPath = modelPath;
    this.sensorName = sensorName;
    this.sensorTypeID = sensorTypeID;
    this.deviceID = deviceID;
    this.sensorID = sensorID;
  }

  /**
   * Generates a new sensor id.
   */
  private void generateWindID() {
    this.sensorID = new SensorID(UUID.randomUUID().toString());
  }

  /**
   * Validates the sensor type ID.
   *
   * @param sensorTypeID The sensor type ID.
   */
  private void validateSensorTypeID(SensorTypeID sensorTypeID) {
    Validator.validateNotNull(sensorTypeID, "SensorTypeID");

    if (!Objects.equals(sensorTypeID.getID(), "Wind")) {
      throw new IllegalArgumentException("SensorTypeID must be 'Wind'");

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
   * Method to get the value object of the sensor.
   *
   * @return the value.
   */
  @Override
  public WindSensorValue getValue() {
    int speed = ValueSimulator.generateRandomValue(0, 408);
    double direction = ValueSimulator.generateRandomValue(0.0, 2 * Math.PI);
    this.windSensorValue = new WindSensorValue(speed, direction);
    return windSensorValue;
  }

  /**
   * Checks if the sensor is equal to another sensor.
   *
   * @param o The sensor to compare.
   * @return True if the sensors are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof WindSensor windSensor) {
      return sensorID.equals(windSensor.sensorID);
    }
    return false;
  }

  /**
   * Method to get the hash code of the sensor.
   *
   * @return the hash code.
   */
  @Override
  public int hashCode() {
    return sensorID.hashCode();
  }

  /**
   * Method to get the string representation of the sensor.
   *
   * @return the string representation.
   */
  @Override
  public String toString() {
    return "WindSensor: DeviceID= " + deviceID.getID() + " ModelPath= " + modelPath.getID()
        + " SensorTypeID= " + sensorTypeID.getID() + " SensorName= " + sensorName.getSensorName()
        + " SensorID= " + sensorID.getID();
  }

  /**
   * Accepts the visitor.
   *
   * @param visitor The visitor.
   */
  public String accept(ISensorVisitor visitor) {
    visitor.visitWindSensor(this);
    return this.toString();
  }
}
