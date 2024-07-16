/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils.visitor_pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import smarthome.domain.sensor.average_power_consumption_sensor.AveragePowerConsumptionSensor;
import smarthome.domain.sensor.dew_point_sensor.DewPointSensor;
import smarthome.domain.sensor.electric_consumption_wh_sensor.ElectricConsumptionWhSensor;
import smarthome.domain.sensor.humidity_sensor.HumiditySensor;
import smarthome.domain.sensor.instant_power_consumption_sensor.InstantPowerConsumptionSensor;
import smarthome.domain.sensor.percentage_position_sensor.PercentagePositionSensor;
import smarthome.domain.sensor.solar_irradiance_sensor.SolarIrradianceSensor;
import smarthome.domain.sensor.sunrise_time_sensor.SunriseTimeSensor;
import smarthome.domain.sensor.sunset_time_sensor.SunsetTimeSensor;
import smarthome.domain.sensor.switch_sensor.SwitchSensor;
import smarthome.domain.sensor.temperature_sensor.TemperatureSensor;
import smarthome.domain.sensor.wind_sensor.WindSensor;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.persistence.data_model.SensorDataModel;

class SensorVisitorForDataModelImplTest {

  @Test
  void shouldGetSensorDataModel() {
    // Arrange
    SensorDataModel sensorDataModel = new SensorDataModel();
    SensorVisitorForDataModelImpl sensorVisitorForDataModel = new SensorVisitorForDataModelImpl(
        sensorDataModel);

    // Act
    SensorDataModel result = sensorVisitorForDataModel.getSensorDataModel();

    // Assert
    assertEquals(sensorDataModel, result);
  }

  @Test
  void shouldFillSensorDataModelWithTemperatureSensorData() {
    // Arrange
    SensorDataModel sensorDataModel = new SensorDataModel();
    SensorVisitorForDataModelImpl sensorVisitorForDataModel = new SensorVisitorForDataModelImpl(
        sensorDataModel);
    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.getID()).thenReturn("1");
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.getID()).thenReturn("modelPath");
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Temperature");
    SensorName sensorName = mock(SensorName.class);
    when(sensorName.getSensorName()).thenReturn("sensorName");
    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1");

    TemperatureSensor temperatureSensor = mock(TemperatureSensor.class);
    when(temperatureSensor.getID()).thenReturn(new SensorID("1"));
    when(temperatureSensor.getDeviceID()).thenReturn(deviceID);
    when(temperatureSensor.getModelPath()).thenReturn(modelPath);
    when(temperatureSensor.getSensorTypeID()).thenReturn(sensorTypeID);
    when(temperatureSensor.getSensorName()).thenReturn(sensorName);

    String expected = "SensorDataModel{" +
        "sensorID='" + sensorID.getID() + '\'' +
        ", deviceID='" + deviceID.getID() + '\'' +
        ", modelPath='" + modelPath.getID() + '\'' +
        ", sensorTypeID='" + sensorTypeID.getID() + '\'' +
        ", sensorName='" + sensorName.getSensorName() + '\'' +
        ", latitude='" + null + '\'' +
        ", longitude='" + null + '\'' +
        ", startDate='" + null + '\'' +
        ", endDate='" + null + '\'' +
        '}';

    // Act
    String result = sensorVisitorForDataModel.visitTemperatureSensor(temperatureSensor);

    // Assert
    assertEquals(sensorDataModel.toString(), result);
    assertEquals(expected, result);
  }

  /**
   * Test with the same logic but for humidity sensor Using mock as the exemple above
   */
  @Test
  void shouldFillSensorDataModelWithHumiditySensorData() {
    // Arrange
    SensorDataModel sensorDataModel = new SensorDataModel();
    SensorVisitorForDataModelImpl sensorVisitorForDataModel = new SensorVisitorForDataModelImpl(
        sensorDataModel);
    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.getID()).thenReturn("1");
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.getID()).thenReturn("modelPath");
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Humidity");
    SensorName sensorName = mock(SensorName.class);
    when(sensorName.getSensorName()).thenReturn("sensorName");
    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1");

    HumiditySensor humiditySensor = mock(HumiditySensor.class);
    when(humiditySensor.getID()).thenReturn(new SensorID("1"));
    when(humiditySensor.getDeviceID()).thenReturn(deviceID);
    when(humiditySensor.getModelPath()).thenReturn(modelPath);
    when(humiditySensor.getSensorTypeID()).thenReturn(sensorTypeID);
    when(humiditySensor.getSensorName()).thenReturn(sensorName);

    String expected = "SensorDataModel{" +
        "sensorID='" + sensorID.getID() + '\'' +
        ", deviceID='" + deviceID.getID() + '\'' +
        ", modelPath='" + modelPath.getID() + '\'' +
        ", sensorTypeID='" + sensorTypeID.getID() + '\'' +
        ", sensorName='" + sensorName.getSensorName() + '\'' +
        ", latitude='" + null + '\'' +
        ", longitude='" + null + '\'' +
        ", startDate='" + null + '\'' +
        ", endDate='" + null + '\'' +
        '}';
    // Act
    String result = sensorVisitorForDataModel.visitHumiditySensor(humiditySensor);

    // Assert
    assertEquals(sensorDataModel.toString(), result);
    assertEquals(expected, result);
  }

  /**
   * Test with the same logic but for SunsetTimeSensor Sunset Time sensor has a GPS object
   */
  @Test
  void shouldFillSensorDataModelWithSunsetTimeSensorData() {
    // Arrange
    SensorDataModel sensorDataModel = new SensorDataModel();
    SensorVisitorForDataModelImpl sensorVisitorForDataModel = new SensorVisitorForDataModelImpl(
        sensorDataModel);
    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.getID()).thenReturn("1");
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.getID()).thenReturn("modelPath");
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");
    SensorName sensorName = mock(SensorName.class);
    when(sensorName.getSensorName()).thenReturn("sensorName");
    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1");

    GPS gps = mock(GPS.class);
    when(gps.getLatitude()).thenReturn(1.0);
    when(gps.getLongitude()).thenReturn(1.0);

    SunsetTimeSensor sunsetTimeSensor = mock(SunsetTimeSensor.class);
    when(sunsetTimeSensor.getID()).thenReturn(sensorID);
    when(sunsetTimeSensor.getDeviceID()).thenReturn(deviceID);
    when(sunsetTimeSensor.getModelPath()).thenReturn(modelPath);
    when(sunsetTimeSensor.getSensorTypeID()).thenReturn(sensorTypeID);
    when(sunsetTimeSensor.getSensorName()).thenReturn(sensorName);
    when(sunsetTimeSensor.getGPS()).thenReturn(gps);

    String expected = "SensorDataModel{" +
        "sensorID='" + sensorID.getID() + '\'' +
        ", deviceID='" + deviceID.getID() + '\'' +
        ", modelPath='" + modelPath.getID() + '\'' +
        ", sensorTypeID='" + sensorTypeID.getID() + '\'' +
        ", sensorName='" + sensorName.getSensorName() + '\'' +
        ", latitude='" + gps.getLatitude() + '\'' +
        ", longitude='" + gps.getLongitude() + '\'' +
        ", startDate='" + null + '\'' +
        ", endDate='" + null + '\'' +
        '}';
    // Act
    String result = sensorVisitorForDataModel.visitSunsetTimeSensor(sunsetTimeSensor);

    // Assert
    assertEquals(sensorDataModel.toString(), result);
    assertEquals(expected, result);
  }

  /**
   * Test with the same logic but for WindSensor
   */
  @Test
  void shouldFillSensorDataModelWithWindSensorData() {
    // Arrange
    SensorDataModel sensorDataModel = new SensorDataModel();
    SensorVisitorForDataModelImpl sensorVisitorForDataModel = new SensorVisitorForDataModelImpl(
        sensorDataModel);
    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.getID()).thenReturn("1");
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.getID()).thenReturn("modelPath");
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Wind");
    SensorName sensorName = mock(SensorName.class);
    when(sensorName.getSensorName()).thenReturn("sensorName");
    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1");

    WindSensor windSensor = mock(WindSensor.class);
    when(windSensor.getID()).thenReturn(sensorID);
    when(windSensor.getDeviceID()).thenReturn(deviceID);
    when(windSensor.getModelPath()).thenReturn(modelPath);
    when(windSensor.getSensorTypeID()).thenReturn(sensorTypeID);
    when(windSensor.getSensorName()).thenReturn(sensorName);

    String expected = "SensorDataModel{" +
        "sensorID='" + sensorID.getID() + '\'' +
        ", deviceID='" + deviceID.getID() + '\'' +
        ", modelPath='" + modelPath.getID() + '\'' +
        ", sensorTypeID='" + sensorTypeID.getID() + '\'' +
        ", sensorName='" + sensorName.getSensorName() + '\'' +
        ", latitude='" + null + '\'' +
        ", longitude='" + null + '\'' +
        ", startDate='" + null + '\'' +
        ", endDate='" + null + '\'' +
        '}';
    // Act
    String result = sensorVisitorForDataModel.visitWindSensor(windSensor);

    // Assert
    assertEquals(sensorDataModel.toString(), result);
    assertEquals(expected, result);
  }

  /**
   * Test with the same logic but for SwitchSensor
   */
  @Test
  void shouldFillSensorDataModelWithSwitchSensorData() {
    // Arrange
    SensorDataModel sensorDataModel = new SensorDataModel();
    SensorVisitorForDataModelImpl sensorVisitorForDataModel = new SensorVisitorForDataModelImpl(
        sensorDataModel);
    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.getID()).thenReturn("1");
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.getID()).thenReturn("modelPath");
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Switch");
    SensorName sensorName = mock(SensorName.class);
    when(sensorName.getSensorName()).thenReturn("sensorName");
    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1");

    SwitchSensor switchSensor = mock(SwitchSensor.class);
    when(switchSensor.getID()).thenReturn(sensorID);
    when(switchSensor.getDeviceID()).thenReturn(deviceID);
    when(switchSensor.getModelPath()).thenReturn(modelPath);
    when(switchSensor.getSensorTypeID()).thenReturn(sensorTypeID);
    when(switchSensor.getSensorName()).thenReturn(sensorName);

    String expected = "SensorDataModel{" +
        "sensorID='" + sensorID.getID() + '\'' +
        ", deviceID='" + deviceID.getID() + '\'' +
        ", modelPath='" + modelPath.getID() + '\'' +
        ", sensorTypeID='" + sensorTypeID.getID() + '\'' +
        ", sensorName='" + sensorName.getSensorName() + '\'' +
        ", latitude='" + null + '\'' +
        ", longitude='" + null + '\'' +
        ", startDate='" + null + '\'' +
        ", endDate='" + null + '\'' +
        '}';
    // Act
    String result = sensorVisitorForDataModel.visitSwitchSensor(switchSensor);

    // Assert
    assertEquals(sensorDataModel.toString(), result);
    assertEquals(expected, result);
  }

  /**
   * Test with the same logic but for SunriseTimeSensor Sunrise Time sensor has a GPS object
   */
  @Test
  void shouldFillSensorDataModelWithSunriseTimeSensorData() {
    // Arrange
    SensorDataModel sensorDataModel = new SensorDataModel();
    SensorVisitorForDataModelImpl sensorVisitorForDataModel = new SensorVisitorForDataModelImpl(
        sensorDataModel);
    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.getID()).thenReturn("1");
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.getID()).thenReturn("modelPath");
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunriseTime");
    SensorName sensorName = mock(SensorName.class);
    when(sensorName.getSensorName()).thenReturn("sensorName");
    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1");

    GPS gps = mock(GPS.class);
    when(gps.getLatitude()).thenReturn(1.0);
    when(gps.getLongitude()).thenReturn(1.0);

    SunriseTimeSensor sunriseTimeSensor = mock(SunriseTimeSensor.class);
    when(sunriseTimeSensor.getID()).thenReturn(sensorID);
    when(sunriseTimeSensor.getDeviceID()).thenReturn(deviceID);
    when(sunriseTimeSensor.getModelPath()).thenReturn(modelPath);
    when(sunriseTimeSensor.getSensorTypeID()).thenReturn(sensorTypeID);
    when(sunriseTimeSensor.getSensorName()).thenReturn(sensorName);
    when(sunriseTimeSensor.getGPS()).thenReturn(gps);

    String expected = "SensorDataModel{" +
        "sensorID='" + sensorID.getID() + '\'' +
        ", deviceID='" + deviceID.getID() + '\'' +
        ", modelPath='" + modelPath.getID() + '\'' +
        ", sensorTypeID='" + sensorTypeID.getID() + '\'' +
        ", sensorName='" + sensorName.getSensorName() + '\'' +
        ", latitude='" + gps.getLatitude() + '\'' +
        ", longitude='" + gps.getLongitude() + '\'' +
        ", startDate='" + null + '\'' +
        ", endDate='" + null + '\'' +
        '}';
    // Act
    String result = sensorVisitorForDataModel.visitSunriseTimeSensor(sunriseTimeSensor);

    // Assert
    assertEquals(sensorDataModel.toString(), result);
    assertEquals(expected, result);
  }

  /**
   * Test with the same logic but for Solar Irradiances Sensor
   */
  @Test
  void shouldFillSensorDataModelWithSolarIrradiancesSensorData() {
    // Arrange
    SensorDataModel sensorDataModel = new SensorDataModel();
    SensorVisitorForDataModelImpl sensorVisitorForDataModel = new SensorVisitorForDataModelImpl(
        sensorDataModel);
    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.getID()).thenReturn("1");
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.getID()).thenReturn("modelPath");
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiances");
    SensorName sensorName = mock(SensorName.class);
    when(sensorName.getSensorName()).thenReturn("sensorName");
    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1");

    SolarIrradianceSensor solarIrradiancesSensor = mock(SolarIrradianceSensor.class);
    when(solarIrradiancesSensor.getID()).thenReturn(sensorID);
    when(solarIrradiancesSensor.getDeviceID()).thenReturn(deviceID);
    when(solarIrradiancesSensor.getModelPath()).thenReturn(modelPath);
    when(solarIrradiancesSensor.getSensorTypeID()).thenReturn(sensorTypeID);
    when(solarIrradiancesSensor.getSensorName()).thenReturn(sensorName);

    String expected = "SensorDataModel{" +
        "sensorID='" + sensorID.getID() + '\'' +
        ", deviceID='" + deviceID.getID() + '\'' +
        ", modelPath='" + modelPath.getID() + '\'' +
        ", sensorTypeID='" + sensorTypeID.getID() + '\'' +
        ", sensorName='" + sensorName.getSensorName() + '\'' +
        ", latitude='" + null + '\'' +
        ", longitude='" + null + '\'' +
        ", startDate='" + null + '\'' +
        ", endDate='" + null + '\'' +
        '}';
    // Act
    String result = sensorVisitorForDataModel.visitSolarIrradianceSensor(solarIrradiancesSensor);

    // Assert
    assertEquals(sensorDataModel.toString(), result);
    assertEquals(expected, result);
  }

  /**
   * Test with the same logic but for PercentageSensor
   */
  @Test
  void shouldFillSensorDataModelWithPercentageSensorData() {
    // Arrange
    SensorDataModel sensorDataModel = new SensorDataModel();
    SensorVisitorForDataModelImpl sensorVisitorForDataModel = new SensorVisitorForDataModelImpl(
        sensorDataModel);
    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.getID()).thenReturn("1");
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.getID()).thenReturn("modelPath");
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Percentage");
    SensorName sensorName = mock(SensorName.class);
    when(sensorName.getSensorName()).thenReturn("sensorName");
    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1");

    PercentagePositionSensor percentageSensor = mock(PercentagePositionSensor.class);
    when(percentageSensor.getID()).thenReturn(sensorID);
    when(percentageSensor.getDeviceID()).thenReturn(deviceID);
    when(percentageSensor.getModelPath()).thenReturn(modelPath);
    when(percentageSensor.getSensorTypeID()).thenReturn(sensorTypeID);
    when(percentageSensor.getSensorName()).thenReturn(sensorName);

    String expected = "SensorDataModel{" +
        "sensorID='" + sensorID.getID() + '\'' +
        ", deviceID='" + deviceID.getID() + '\'' +
        ", modelPath='" + modelPath.getID() + '\'' +
        ", sensorTypeID='" + sensorTypeID.getID() + '\'' +
        ", sensorName='" + sensorName.getSensorName() + '\'' +
        ", latitude='" + null + '\'' +
        ", longitude='" + null + '\'' +
        ", startDate='" + null + '\'' +
        ", endDate='" + null + '\'' +
        '}';
    // Act
    String result = sensorVisitorForDataModel.visitPercentageSensor(percentageSensor);

    // Assert
    assertEquals(sensorDataModel.toString(), result);
    assertEquals(expected, result);
  }

  /**
   * Test with the same logic but for InstantPowerSensor
   */
  @Test
  void shouldFillSensorDataModelWithInstantPowerSensorData() {
    // Arrange
    SensorDataModel sensorDataModel = new SensorDataModel();
    SensorVisitorForDataModelImpl sensorVisitorForDataModel = new SensorVisitorForDataModelImpl(
        sensorDataModel);
    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.getID()).thenReturn("1");
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.getID()).thenReturn("modelPath");
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("InstantPower");
    SensorName sensorName = mock(SensorName.class);
    when(sensorName.getSensorName()).thenReturn("sensorName");
    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1");

    InstantPowerConsumptionSensor instantPowerSensor = mock(InstantPowerConsumptionSensor.class);
    when(instantPowerSensor.getID()).thenReturn(sensorID);
    when(instantPowerSensor.getDeviceID()).thenReturn(deviceID);
    when(instantPowerSensor.getModelPath()).thenReturn(modelPath);
    when(instantPowerSensor.getSensorTypeID()).thenReturn(sensorTypeID);
    when(instantPowerSensor.getSensorName()).thenReturn(sensorName);

    String expected = "SensorDataModel{" +
        "sensorID='" + sensorID.getID() + '\'' +
        ", deviceID='" + deviceID.getID() + '\'' +
        ", modelPath='" + modelPath.getID() + '\'' +
        ", sensorTypeID='" + sensorTypeID.getID() + '\'' +
        ", sensorName='" + sensorName.getSensorName() + '\'' +
        ", latitude='" + null + '\'' +
        ", longitude='" + null + '\'' +
        ", startDate='" + null + '\'' +
        ", endDate='" + null + '\'' +
        '}';
    // Act
    String result = sensorVisitorForDataModel.visitInstantPowerSensor(instantPowerSensor);

    // Assert
    assertEquals(sensorDataModel.toString(), result);
    assertEquals(expected, result);
  }

  /**
   * Test with the same logic but for DewPointSensor
   */
  @Test
  void shouldFillSensorDataModelWithDewPointSensorData() {
    // Arrange
    SensorDataModel sensorDataModel = new SensorDataModel();
    SensorVisitorForDataModelImpl sensorVisitorForDataModel = new SensorVisitorForDataModelImpl(
        sensorDataModel);
    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.getID()).thenReturn("1");
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.getID()).thenReturn("modelPath");
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("DewPoint");
    SensorName sensorName = mock(SensorName.class);
    when(sensorName.getSensorName()).thenReturn("sensorName");
    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1");

    DewPointSensor dewPointSensor = mock(DewPointSensor.class);
    when(dewPointSensor.getID()).thenReturn(sensorID);
    when(dewPointSensor.getDeviceID()).thenReturn(deviceID);
    when(dewPointSensor.getModelPath()).thenReturn(modelPath);
    when(dewPointSensor.getSensorTypeID()).thenReturn(sensorTypeID);
    when(dewPointSensor.getSensorName()).thenReturn(sensorName);

    String expected = "SensorDataModel{" +
        "sensorID='" + sensorID.getID() + '\'' +
        ", deviceID='" + deviceID.getID() + '\'' +
        ", modelPath='" + modelPath.getID() + '\'' +
        ", sensorTypeID='" + sensorTypeID.getID() + '\'' +
        ", sensorName='" + sensorName.getSensorName() + '\'' +
        ", latitude='" + null + '\'' +
        ", longitude='" + null + '\'' +
        ", startDate='" + null + '\'' +
        ", endDate='" + null + '\'' +
        '}';
    // Act
    String result = sensorVisitorForDataModel.visitDewPointSensor(dewPointSensor);

    // Assert
    assertEquals(sensorDataModel.toString(), result);
    assertEquals(expected, result);
  }

  /**
   * Test with the same logic but for AveragePowerConsumptionSensor
   */
  @Test
  void shouldFillSensorDataModelWithAveragePowerConsumptionSensorData() {
    // Arrange
    SensorDataModel sensorDataModel = new SensorDataModel();
    SensorVisitorForDataModelImpl sensorVisitorForDataModel = new SensorVisitorForDataModelImpl(
        sensorDataModel);
    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.getID()).thenReturn("1");
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.getID()).thenReturn("modelPath");
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("AveragePowerConsumption");
    SensorName sensorName = mock(SensorName.class);
    when(sensorName.getSensorName()).thenReturn("sensorName");
    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1");

    AveragePowerConsumptionSensor averagePowerConsumptionSensor = mock(
        AveragePowerConsumptionSensor.class);
    when(averagePowerConsumptionSensor.getID()).thenReturn(sensorID);
    when(averagePowerConsumptionSensor.getDeviceID()).thenReturn(deviceID);
    when(averagePowerConsumptionSensor.getModelPath()).thenReturn(modelPath);
    when(averagePowerConsumptionSensor.getSensorTypeID()).thenReturn(sensorTypeID);
    when(averagePowerConsumptionSensor.getSensorName()).thenReturn(sensorName);

    String expected = "SensorDataModel{" +
        "sensorID='" + sensorID.getID() + '\'' +
        ", deviceID='" + deviceID.getID() + '\'' +
        ", modelPath='" + modelPath.getID() + '\'' +
        ", sensorTypeID='" + sensorTypeID.getID() + '\'' +
        ", sensorName='" + sensorName.getSensorName() + '\'' +
        ", latitude='" + null + '\'' +
        ", longitude='" + null + '\'' +
        ", startDate='" + null + '\'' +
        ", endDate='" + null + '\'' +
        '}';
    // Act
    String result = sensorVisitorForDataModel.visitAveragePowerConsumptionSensor(
        averagePowerConsumptionSensor);

    // Assert
    assertEquals(sensorDataModel.toString(), result);
    assertEquals(expected, result);
  }

  /**
   * Test with the same logic but for ElectricConsumptionWhSensor
   */
  @Test
  void shouldFillSensorDataModelWithElectricConsumptionWhSensorData() {
    // Arrange
    SensorDataModel sensorDataModel = new SensorDataModel();
    SensorVisitorForDataModelImpl sensorVisitorForDataModel = new SensorVisitorForDataModelImpl(
        sensorDataModel);
    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.getID()).thenReturn("1");
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.getID()).thenReturn("modelPath");
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("ElectricConsumptionWh");
    SensorName sensorName = mock(SensorName.class);
    when(sensorName.getSensorName()).thenReturn("sensorName");
    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1");

    DatePeriod datePeriod = mock(DatePeriod.class);
    when(datePeriod.getStartDate()).thenReturn(LocalDateTime.now());
    when(datePeriod.getEndDate()).thenReturn(LocalDateTime.now().plusDays(2));

    ElectricConsumptionWhSensor electricConsumptionWhSensor = mock(
        ElectricConsumptionWhSensor.class);
    when(electricConsumptionWhSensor.getID()).thenReturn(sensorID);
    when(electricConsumptionWhSensor.getDeviceID()).thenReturn(deviceID);
    when(electricConsumptionWhSensor.getModelPath()).thenReturn(modelPath);
    when(electricConsumptionWhSensor.getSensorTypeID()).thenReturn(sensorTypeID);
    when(electricConsumptionWhSensor.getSensorName()).thenReturn(sensorName);
    when(electricConsumptionWhSensor.getDatePeriod()).thenReturn(datePeriod);

    String expected = "SensorDataModel{" +
        "sensorID='" + sensorID.getID() + '\'' +
        ", deviceID='" + deviceID.getID() + '\'' +
        ", modelPath='" + modelPath.getID() + '\'' +
        ", sensorTypeID='" + sensorTypeID.getID() + '\'' +
        ", sensorName='" + sensorName.getSensorName() + '\'' +
        ", latitude='" + null + '\'' +
        ", longitude='" + null + '\'' +
        ", startDate='" + datePeriod.getStartDate() + '\'' +
        ", endDate='" + datePeriod.getEndDate() + '\'' +
        '}';
    // Act
    String result = sensorVisitorForDataModel.visitElectricConsumptionWhSensor(
        electricConsumptionWhSensor);

    // Assert
    assertEquals(sensorDataModel.toString(), result);
    assertEquals(expected, result);
  }


}