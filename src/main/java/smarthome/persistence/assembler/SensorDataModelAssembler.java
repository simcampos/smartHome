/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.assembler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.sensor.ISensorFactory;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.persistence.data_model.SensorDataModel;
import smarthome.utils.Validator;

@Component
public class SensorDataModelAssembler implements IDataModelAssembler<SensorDataModel, ISensor> {

  private final ISensorFactory sensorFactory;
  private final List<Object> parameters = new ArrayList<>();

  /**
   * Class constructor
   *
   * @param sensorFactory is the factory used to create Sensor instances.
   */
  public SensorDataModelAssembler(ISensorFactory sensorFactory) {
    Validator.validateNotNull(sensorFactory, "Sensor factory");
    this.sensorFactory = sensorFactory;
  }

  /**
   * Converts a SensorDataModel instance to a Sensor instance.
   *
   * @param sensorDataModel is the domain entity to be converted.
   * @return a Sensor instance.
   */
  public ISensor toDomain(SensorDataModel sensorDataModel) {
    Validator.validateNotNull(sensorDataModel, "Sensor data model");
    parameters.clear();
    getDeviceID(sensorDataModel);
    getModelPath(sensorDataModel);
    getSensorTypeID(sensorDataModel);
    getSensorName(sensorDataModel);
    if (Objects.equals(sensorDataModel.getSensorTypeID(), "SunriseTime")
        || Objects.equals(sensorDataModel.getSensorTypeID(), "SunsetTime")) {
      getGPS(sensorDataModel);
    }

    if (Objects.equals(sensorDataModel.getSensorTypeID(), "ElectricConsumptionWh")) {
      getDatePeriod(sensorDataModel);

    }
    getSensorID(sensorDataModel);
    return sensorFactory.create(parameters.toArray());
  }

  /**
   * Converts a list of SensorDataModel instances to a list of Sensor instances.
   *
   * @param sensorDataModels is the list of domain entities to be converted.
   * @return a list of Sensor instances.
   */
  public List<ISensor> toDomain(List<SensorDataModel> sensorDataModels) {
    List<ISensor> sensors = new ArrayList<>();
    for (SensorDataModel sensorDataModel : sensorDataModels) {
      ISensor sensor = toDomain(sensorDataModel);
      sensors.add(sensor);
    }
    return sensors;
  }

  /**
   * Adds a DeviceID instance to the parameters list.
   *
   * @param sensorDataModel is the domain entity to be converted.
   * @return true if the DeviceID instance was added to the parameters list, false otherwise.
   */
  private boolean getDeviceID(SensorDataModel sensorDataModel) {
    DeviceID deviceID = new DeviceID(sensorDataModel.getDeviceID());
    parameters.add(deviceID);
    return true;
  }

  /**
   * Adds a ModelPath instance to the parameters list.
   *
   * @param sensorDataModel is the domain entity to be converted.
   * @return true if the ModelPath instance was added to the parameters list, false otherwise.
   */
  private boolean getModelPath(SensorDataModel sensorDataModel) {
    ModelPath modelPath = new ModelPath(sensorDataModel.getModelPath());
    parameters.add(modelPath);
    return true;
  }

  /**
   * Adds a SensorTypeID instance to the parameters list.
   *
   * @param sensorDataModel is the domain entity to be converted.
   * @return true if the SensorTypeID instance was added to the parameters list, false otherwise.
   */
  private boolean getSensorTypeID(SensorDataModel sensorDataModel) {
    SensorTypeID sensorTypeID = new SensorTypeID(sensorDataModel.getSensorTypeID());
    parameters.add(sensorTypeID);
    return true;
  }

  /**
   * Adds a SensorName instance to the parameters list.
   *
   * @param sensorDataModel is the domain entity to be converted.
   * @return true if the SensorName instance was added to the parameters list, false otherwise.
   */
  private boolean getSensorName(SensorDataModel sensorDataModel) {
    SensorName sensorName = new SensorName(sensorDataModel.getSensorName());
    parameters.add(sensorName);
    return true;
  }

  /**
   * Adds a GPS instance to the parameters list.
   *
   * @param sensorDataModel is the domain entity to be converted.
   * @return true if the GPS instance was added to the parameters list, false otherwise.
   */
  private boolean getGPS(SensorDataModel sensorDataModel) {
    if (sensorDataModel.getLatitude() != null && sensorDataModel.getLongitude() != null) {
      double latitude = Double.parseDouble(sensorDataModel.getLatitude());
      double longitude = Double.parseDouble(sensorDataModel.getLongitude());
      GPS gps = new GPS(latitude, longitude);
      parameters.add(gps);
      return true;
    }
    return false;
  }

  /**
   * Adds a DatePeriod instance to the parameters list.
   *
   * @param sensorDataModel is the domain entity to be converted.
   * @return true if the DatePeriod instance was added to the parameters list, false otherwise.
   */
  private boolean getDatePeriod(SensorDataModel sensorDataModel) {
    if (sensorDataModel.getStartDate() != null && sensorDataModel.getEndDate() != null) {
      LocalDateTime startDate = LocalDateTime.parse(sensorDataModel.getStartDate());
      LocalDateTime endDate = LocalDateTime.parse(sensorDataModel.getEndDate());
      DatePeriod datePeriod = new DatePeriod(startDate, endDate);
      parameters.add(datePeriod);
      return true;
    }
    return false;
  }

  /**
   * Adds a SensorID instance to the parameters list.
   *
   * @param sensorDataModel is the domain entity to be converted.
   * @return true if the SensorID instance was added to the parameters list, false otherwise.
   */
  private boolean getSensorID(SensorDataModel sensorDataModel) {
    SensorID sensorID = new SensorID(sensorDataModel.getSensorID());
    parameters.add(sensorID);
    return true;
  }
}
