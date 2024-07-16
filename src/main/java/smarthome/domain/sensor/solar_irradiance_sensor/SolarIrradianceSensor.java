/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.solar_irradiance_sensor;

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
import smarthome.utils.visitor_pattern.ISensorVisitor;

@Getter
public class SolarIrradianceSensor implements ISensor {

  @Getter(AccessLevel.NONE)
  private SensorID sensorID;
  private final SensorName sensorName;
  private final ModelPath modelPath;
  private final SensorTypeID sensorTypeID;
  private final DeviceID deviceID;
  @Getter(AccessLevel.NONE)
  private SolarIrradianceValue value;

  /**
   * Constructor for SolarIrradianceSensor
   *
   * @param deviceID     is the deviceID
   * @param modelPath    is the model path
   * @param sensorTypeID is the sensor type ID
   * @param sensorName   is the sensor name
   */
  public SolarIrradianceSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID,
      SensorName sensorName) {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(sensorName, "SensorName");
    validateSensorTypeID(sensorTypeID);
    generateSensorID();
    this.deviceID = deviceID;
    this.modelPath = modelPath;
    this.sensorTypeID = sensorTypeID;
    this.sensorName = sensorName;
  }

  /**
   * Constructor for SolarIrradianceSensor with SensorID
   *
   * @param deviceID     is the deviceID
   * @param modelPath    is the model path
   * @param sensorTypeID is the sensor type ID
   * @param sensorName   is the sensor name
   * @param sensorID     is the sensor id
   */
  public SolarIrradianceSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID,
      SensorName sensorName, SensorID sensorID) {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(sensorName, "SensorName");
    validateSensorTypeID(sensorTypeID);
    Validator.validateNotNull(sensorID, "SensorID");
    this.sensorID = sensorID;
    this.deviceID = deviceID;
    this.modelPath = modelPath;
    this.sensorTypeID = sensorTypeID;
    this.sensorName = sensorName;
  }

  /**
   * Generates sensorID
   */
  private void generateSensorID() {
    this.sensorID = new SensorID(UUID.randomUUID().toString());
  }


  /**
   * Validates the sensorTypeID
   *
   * @param sensorTypeID is the sensor type id
   */
  private void validateSensorTypeID(SensorTypeID sensorTypeID) {
    Validator.validateNotNull(sensorTypeID, "SensorTypeID");

    if (!Objects.equals(sensorTypeID.getID(), "SolarIrradiance")) {
      throw new IllegalArgumentException("SensorTypeID must be SolarIrradiance");
    }
  }


  /**
   * Getter for sensorID
   *
   * @return sensor id
   */
  @Override
  public SensorID getID() {
    return this.sensorID;
  }



  /**
   * Getter for sensor value
   *
   * @return sensor value
   */
  @Override
  public SolarIrradianceValue getValue() {
    this.value = new SolarIrradianceValue(4500);
    return this.value;
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof SolarIrradianceSensor sensor) {
      return this.sensorID.equals(sensor.sensorID);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.sensorID.hashCode();
  }

  /**
   * Accept method for visitor pattern
   */
  public String accept(ISensorVisitor visitor) {
    visitor.visitSolarIrradianceSensor(this);
    return this.toString();
  }
}
