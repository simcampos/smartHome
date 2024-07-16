/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.sensor.ISensorFactory;
import smarthome.domain.sensor.SensorFactoryImpl;
import smarthome.domain.sensor.electric_consumption_wh_sensor.ElectricConsumptionWhSensor;
import smarthome.domain.sensor.sunrise_time_sensor.SunriseTimeSensor;
import smarthome.domain.sensor.temperature_sensor.TemperatureSensor;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.persistence.data_model.SensorDataModel;

class SensorDataModelAssemblerTest {

  @Test
  void shouldInstantiateGenericSensorWhenGivenValidParameters() {
    String deviceIDValue = "some-device-id";
    String modelPathValue = "smarthome.domain.sensor.temperature_sensor.TemperatureSensor";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Temperature";
    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    ISensorFactory sensorFactory = new SensorFactoryImpl();

    TemperatureSensor sensor = (TemperatureSensor) sensorFactory.create(deviceID, modelPath,
        sensorTypeID, sensorName);

    SensorDataModel sensorDataModel = new SensorDataModel(sensor);

    SensorDataModelAssembler sensorDataModelAssembler = new SensorDataModelAssembler(sensorFactory);

    // Act
    ISensor sensor2 = sensorDataModelAssembler.toDomain(sensorDataModel);
    // Assert
    assertEquals(sensor, sensor2);

  }

  @Test
  void shouldInstantiateSensorWithGPS() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "smarthome.domain.sensor.sunrise_time_sensor.SunriseTimeSensor";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "SunriseTime";
    double GPSLatitude = 90.0;
    double GPSLongitude = 180.0;

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    GPS gps = new GPS(GPSLatitude, GPSLongitude);
    ISensorFactory sensorFactory = new SensorFactoryImpl();

    SunriseTimeSensor result = (SunriseTimeSensor) sensorFactory.create(deviceID, modelPath,
        sensorTypeID, sensorName, gps);
    SensorDataModel sensorDataModel = new SensorDataModel(result);
    sensorDataModel.setLatitude(String.valueOf(GPSLatitude));
    sensorDataModel.setLongitude(String.valueOf(GPSLongitude));
    SensorDataModelAssembler sensorDataModelAssembler = new SensorDataModelAssembler(sensorFactory);

    // Act
    ISensor sensor = sensorDataModelAssembler.toDomain(sensorDataModel);

    // Assert
    assertEquals(result, sensor);
  }

  @Test
  void shouldInstantiateSensorWithDatePeriod() {
    //Arrange
    ModelPath modelPath = new ModelPath(
        "smarthome.domain.sensor.electric_consumption_wh_sensor.ElectricConsumptionWhSensor");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
    SensorName sensorName = new SensorName("sensorName");
    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    ISensorFactory sensorFactory = new SensorFactoryImpl();
    ElectricConsumptionWhSensor electricConsumptionWhSensor = (ElectricConsumptionWhSensor) sensorFactory.create(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);

    SensorDataModel sensorDataModel = new SensorDataModel(electricConsumptionWhSensor);
    sensorDataModel.setStartDate(startDate.toString());
    sensorDataModel.setEndDate(endDate.toString());


    SensorDataModelAssembler sensorDataModelAssembler = new SensorDataModelAssembler(sensorFactory);

    //Act
    ISensor sensor = sensorDataModelAssembler.toDomain(sensorDataModel);

    //Assert
    assertEquals(electricConsumptionWhSensor, sensor);
  }

  @Test
  void shouldReturnListOfSensorsOfDifferentSubClasses() {
    //Arrange
    //Sensor Temp
    String deviceIDTemp = "some-device-id";
    String modelPathTemp = "smarthome.domain.sensor.temperature_sensor.TemperatureSensor";
    String sensorNameTemp = "sensorName";
    String sensorTypeIDTemp = "Temperature";
    DeviceID deviceID = new DeviceID(deviceIDTemp);
    ModelPath modelPath = new ModelPath(modelPathTemp);
    SensorName sensorName = new SensorName(sensorNameTemp);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDTemp);

    ISensorFactory sensorFactory = new SensorFactoryImpl();

    TemperatureSensor temperatureSensor = (TemperatureSensor) sensorFactory.create(deviceID,
        modelPath, sensorTypeID, sensorName);
    SensorDataModel tempatureSensorDataModel = new SensorDataModel(temperatureSensor);

    //Sensor Sunrise
    String deviceIDSunr = "deviceID";
    String modelPathSunr = "smarthome.domain.sensor.sunrise_time_sensor.SunriseTimeSensor";
    String sensorNameSunr = "sensorName";
    String sensorTypeIDSunr = "SunriseTime";
    double GPSLatitude = 90.0;
    double GPSLongitude = 180.0;

    DeviceID sunrDeviceID = new DeviceID(deviceIDSunr);
    ModelPath sunrModelPath = new ModelPath(modelPathSunr);
    SensorName sunrSensorName = new SensorName(sensorNameSunr);
    SensorTypeID sunrSensorTypeID = new SensorTypeID(sensorTypeIDSunr);
    GPS gps = new GPS(GPSLatitude, GPSLongitude);

    SunriseTimeSensor sunriseTimeSensor = (SunriseTimeSensor) sensorFactory.create(sunrDeviceID,
        sunrModelPath, sunrSensorTypeID, sunrSensorName, gps);
    SensorDataModel sunriseSensorDataModel = new SensorDataModel(sunriseTimeSensor);
    sunriseSensorDataModel.setLatitude(String.valueOf(GPSLatitude));
    sunriseSensorDataModel.setLongitude(String.valueOf(GPSLongitude));

    List<SensorDataModel> sensorDataModels = List.of(tempatureSensorDataModel,
        sunriseSensorDataModel);
    List<ISensor> expected = List.of(temperatureSensor, sunriseTimeSensor);

    SensorDataModelAssembler sensorDataModelAssembler = new SensorDataModelAssembler(sensorFactory);
    //Act

    List<ISensor> result = sensorDataModelAssembler.toDomain(sensorDataModels);

    //Assert
    assertEquals(expected, result);
  }
}