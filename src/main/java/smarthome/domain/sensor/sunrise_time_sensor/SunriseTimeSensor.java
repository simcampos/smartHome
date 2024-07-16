/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.sunrise_time_sensor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import org.shredzone.commons.suncalc.SunTimes;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.Validator;
import smarthome.utils.visitor_pattern.ISensorVisitor;

@Getter
public class SunriseTimeSensor implements ISensor {

  @Getter(AccessLevel.NONE)
  private SunriseTimeSensorValue sunriseTimeSensorValue;
  private final SensorTypeID sensorTypeID;
  @Getter(AccessLevel.NONE)
  private SensorID sensorID;
  private final SensorName sensorName;
  private final DeviceID deviceID;
  private final ModelPath modelPath;
  private final GPS gps;

  /**
   * Creates a new SunriseTimeSensor with a given catalogue.
   */
  public SunriseTimeSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID,
      SensorName sensorName, GPS gps) {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(sensorName, "SensorName");
    validateSensorTypeID(sensorTypeID);
    Validator.validateNotNull(gps, "GPS");

    generateSensorID();
    this.deviceID = deviceID;
    this.sensorTypeID = sensorTypeID;
    this.modelPath = modelPath;
    this.sensorName = sensorName;
    this.gps = gps;
  }

  /**
   * Creates a new SunriseTimeSensor with a given catalogue.
   */
  public SunriseTimeSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID,
      SensorName sensorName, GPS gps, SensorID sensorID) {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(sensorName, "SensorName");
    validateSensorTypeID(sensorTypeID);
    Validator.validateNotNull(gps, "GPS");
    Validator.validateNotNull(sensorID, "SensorID");

    this.deviceID = deviceID;
    this.sensorTypeID = sensorTypeID;
    this.modelPath = modelPath;
    this.sensorName = sensorName;
    this.gps = gps;
    this.sensorID = sensorID;
  }


  private void validateSensorTypeID(SensorTypeID sensorTypeID) {
    Validator.validateNotNull(sensorTypeID, "SensorTypeID");

    if (!sensorTypeID.getID().equals("SunriseTime")) {
      throw new IllegalArgumentException("SensorTypeID must be 'SunriseTime'.");
    }
  }


  private void generateSensorID() {
    sensorID = new SensorID(java.util.UUID.randomUUID().toString());
  }


  /**
   * Gets the Sunrise Time of the GPS location for a given date.
   *
   * @param date the date to be used.
   * @return the Sunrise Time of the GPS location for a given date.
   */
  private LocalTime getSunriseTime(LocalDate date) {
    SunTimes time = SunTimes.compute().on(date).at(gps.getLatitude(), gps.getLongitude()).execute();
    LocalTime sunrise = Objects.requireNonNull(time.getRise()).toLocalTime();
    return sunrise;
  }

  @Override
  public SensorID getID() {
    return sensorID;
  }

  /**
   * Gets the value of the SunriseTimeSensor for the current day.
   *
   * @return the value of the SunriseTimeSensor.
   */
  @Override
  public SunriseTimeSensorValue getValue() {
    LocalTime sunrise = getSunriseTime(LocalDate.now());
    this.sunriseTimeSensorValue = new SunriseTimeSensorValue(sunrise);
    return this.sunriseTimeSensorValue;
  }

  /**
   * Gets the GPS configuration of the sensor.
   */
  public GPS getGPS() {
    return gps;
  }

  /**
   * Gets the value of the SunriseTimeSensor for a given date.
   *
   * @param date the date to be used.
   * @return the value of the SunriseTimeSensor for a given date.
   */
  public SunriseTimeSensorValue getValue(LocalDate date) {
    LocalTime sunrise = getSunriseTime(date);
    this.sunriseTimeSensorValue = new SunriseTimeSensorValue(sunrise);
    return this.sunriseTimeSensorValue;
  }

  /**
   * Equals method for SunriseTimeSensor.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof SunriseTimeSensor sunriseTimeObject) {
      return sensorID.equals(sunriseTimeObject.sensorID);
    }
    return false;
  }

  /**
   * HashCode method for SunriseTimeSensor.
   */
  @Override
  public int hashCode() {
    return sensorID.hashCode();
  }

  /**
   * ToString method for SunriseTimeSensor.
   */
  @Override
  public String toString() {
    return "SunriseTimeSensor:" +
        ", sensorID=" + sensorID +
        ", sensorName=" + sensorName +
        ", modelPath=" + modelPath +
        ", sensorTypeID=" + sensorTypeID +
        ", deviceID=" + deviceID +
        ", gps=" + gps;
  }

  public String accept(ISensorVisitor visitor) {
    visitor.visitSunriseTimeSensor(this);
    return this.toString();
  }
}
