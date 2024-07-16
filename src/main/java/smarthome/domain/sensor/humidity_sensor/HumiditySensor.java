/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.humidity_sensor;

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

/**
 * Represents a humidity sensor.
 */
@Getter
public class HumiditySensor implements ISensor {

  private final SensorTypeID sensorTypeID;
  private final DeviceID deviceID;
  private final ModelPath modelPath;
  private final SensorName sensorName;
  @Getter(AccessLevel.NONE)
  private SensorID sensorID;
  @Getter(AccessLevel.NONE)
  private HumiditySensorValue humiditySensorValue;

  /**
   * Constructs a new HumiditySensor.
   *
   * @param deviceID     The ID of the device to which the sensor belongs.
   * @param modelPath    The path of the model associated with the sensor.
   * @param sensorTypeID The type ID of the sensor.
   * @param sensorName   The name of the sensor.
   */
  public HumiditySensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID,
      SensorName sensorName) {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(sensorName, "SensorName");
    validateSensorTypeID(sensorTypeID);
    generateHumidityID();

    this.deviceID = deviceID;
    this.sensorTypeID = sensorTypeID;
    this.modelPath = modelPath;
    this.sensorName = sensorName;
  }

  /**
   * Constructor with sensorID.
   *
   * @param deviceID     The ID of the device to which the sensor belongs.
   * @param modelPath    The path of the model associated with the sensor.
   * @param sensorTypeID The type ID of the sensor.
   * @param sensorName   The name of the sensor.
   * @param sensorID     is the sensor id
   */
  public HumiditySensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID,
      SensorName sensorName, SensorID sensorID) {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(sensorName, "SensorName");
    validateSensorTypeID(sensorTypeID);
    Validator.validateNotNull(sensorID, "SensorID");

    this.deviceID = deviceID;
    this.sensorTypeID = sensorTypeID;
    this.modelPath = modelPath;
    this.sensorName = sensorName;
    this.sensorID = sensorID;
  }

  /**
   * Generates a random SensorID for the humidity sensor.
   */
  private void generateHumidityID() {
    this.sensorID = new SensorID(UUID.randomUUID().toString());
  }

  /**
   * Validates the sensor type ID.
   *
   * @param sensorTypeID The sensor type ID to validate.
   * @throws IllegalArgumentException if the sensor type ID is null or not of type 'Humidity'.
   */
  private void validateSensorTypeID(SensorTypeID sensorTypeID) {
    Validator.validateNotNull(sensorTypeID, "SensorTypeID");

    if (!Objects.equals(sensorTypeID.getID(), "Humidity")) {
      throw new IllegalArgumentException("SensorTypeID must be of type 'Humidity'");
    }
  }


  /**
   * Returns the sensor ID.
   *
   * @return The sensor ID.
   */
  @Override
  public SensorID getID() {
    return sensorID;
  }
  /**
   * Returns the humidity sensor value.
   *
   * @return The humidity sensor value.
   */
  @Override
  public HumiditySensorValue getValue() {
    int humidityReadingReading = ValueSimulator.generateRandomValue(0, 100);
    humiditySensorValue = new HumiditySensorValue(humidityReadingReading);
    return humiditySensorValue;
  }

  /**
   * Checks if this humidity sensor is equal to another object.
   *
   * @param o The object to compare.
   * @return True if the objects are equal, otherwise false.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof HumiditySensor humiditySensor) {
      return this.sensorID.equals(humiditySensor.getID());
    }
    return false;
  }

  /**
   * Returns the hash code of this humidity sensor.
   *
   * @return The hash code.
   */
  @Override
  public int hashCode() {
    return this.sensorID.hashCode();
  }

  /**
   * Should accept a visitor.
   */
  public String accept(ISensorVisitor visitor) {
    visitor.visitHumiditySensor(this);
    return this.toString();
  }

  @Override
  public String toString() {
    return "HumiditySensor{" +
        "_modelPath=" + modelPath +
        ", _sensorName=" + sensorName +
        ", _sensorID=" + sensorID +
        ", _sensorTypeID=" + sensorTypeID +
        ", _deviceID=" + deviceID +
        '}';
  }
}
