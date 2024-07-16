/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.percentage_position_sensor;

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
import smarthome.utils.visitor_pattern.ISensorVisitor;

/**
 * Represents a percentage position sensor. This sensor measures the percentage position of an
 * object.
 */
@Getter
public class PercentagePositionSensor implements ISensor {

  private final ModelPath modelPath;
  private final SensorName sensorName;
  @Getter(AccessLevel.NONE)
  private SensorID sensorID;
  private final SensorTypeID sensorTypeID;
  @Getter(AccessLevel.NONE)
  private PercentagePositionSensorValue percentagePositionSensorValue;
  private final DeviceID deviceID;

  /**
   * Constructs a PercentagePositionSensor with the given parameters.
   *
   * @param deviceID     The ID of the device.
   * @param modelPath    The model path of the sensor.
   * @param sensorTypeID The type ID of the sensor.
   * @param sensorName   The name of the sensor.
   * @throws IllegalArgumentException if any of the parameters are null.
   */
  public PercentagePositionSensor(
      DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName) {
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(sensorName, "SensorName");
    validateSensorTypeID(sensorTypeID);
    Validator.validateNotNull(deviceID, "DeviceID");
    generatePercentageID();
    this.modelPath = modelPath;
    this.sensorName = sensorName;
    this.sensorTypeID = sensorTypeID;
    this.deviceID = deviceID;
  }

  /**
   * Constructs a PercentagePositionSensor with the given parameters.
   *
   * @param deviceID     The ID of the device.
   * @param modelPath    The model path of the sensor.
   * @param sensorTypeID The type ID of the sensor.
   * @param sensorName   The name of the sensor.
   * @param sensorID     The ID of the sensor.
   * @throws IllegalArgumentException if any of the parameters are null.
   */
  public PercentagePositionSensor(
      DeviceID deviceID,
      ModelPath modelPath,
      SensorTypeID sensorTypeID,
      SensorName sensorName,
      SensorID sensorID) {
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(sensorName, "SensorName");
    validateSensorTypeID(sensorTypeID);
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(sensorID, "SensorID");

    this.modelPath = modelPath;
    this.sensorName = sensorName;
    this.sensorTypeID = sensorTypeID;
    this.deviceID = deviceID;
    this.sensorID = sensorID;
  }

  /**
   * Generates a unique ID for the sensor.
   */
  private void generatePercentageID() {
    this.sensorID = new SensorID(UUID.randomUUID().toString());
  }

  /**
   * Validates the sensor type ID.
   *
   * @param sensorTypeID The sensor type ID to validate.
   * @throws IllegalArgumentException if the sensor type ID is null.
   */
  private void validateSensorTypeID(SensorTypeID sensorTypeID) {
    Validator.validateNotNull(sensorTypeID, "SensorTypeID");

    if (!sensorTypeID.getID().equals("PercentagePosition")) {
      throw new IllegalArgumentException("SensorTypeID must be 'PercentagePosition'");
    }
  }

  /**
   * Gets the ID of the sensor.
   *
   * @return The ID of the sensor.
   */
  @Override
  public SensorID getID() {
    return sensorID;
  }

  /**
   * Gets the value of the sensor.
   *
   * @return The value of the sensor.
   */
  @Override
  public PercentagePositionSensorValue getValue() {
    int randomInt = 14;

    this.percentagePositionSensorValue = new PercentagePositionSensorValue(randomInt);

    return this.percentagePositionSensorValue;
  }

  /**
   * Method to compare this object with another object for equality.
   *
   * @param o The object to be compared.
   * @return true if the objects are equal, otherwise false.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof PercentagePositionSensor percentagePositionSensorObject) {
      return sensorID.equals(percentagePositionSensorObject.sensorID);
    }
    return false;
  }

  /**
   * Returns a hash code value for this object.
   *
   * @return The hash code value for this object.
   */
  @Override
  public int hashCode() {
    return sensorID.hashCode();
  }

  /**
   * Returns a string representation of this object.
   *
   * @return The string representation of this object.
   */
  @Override
  public String toString() {
    return "PercentagePositionSensor: DeviceID= "
        + deviceID.getID()
        + " ModelPath= "
        + modelPath.getID()
        + " SensorTypeID= "
        + sensorTypeID.getID()
        + " SensorName= "
        + sensorName.getSensorName()
        + " SensorID= "
        + sensorID.getID();
  }

  /**
   * Accepts a visitor
   *
   * @param visitor The visitor
   */
  public String accept(ISensorVisitor visitor) {
    visitor.visitPercentageSensor(this);
    return this.toString();
  }
}
