/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.jpa.data_model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.persistence.data_model.SensorDataModel;

class SensorDataModelTest {

  @Test
  void shouldCreateSensorDataModel() {
    //Arrange
    String deviceID = "deviceID";
    String modelPath = "modelPath";
    String sensorTypeID = "Temperature";
    String sensorName = "TemperatureSensor";
    String sensorID = "Teste";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.getID()).thenReturn(deviceID);
    ModelPath modelPathDouble = mock(ModelPath.class);
    when(modelPathDouble.getID()).thenReturn(modelPath);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
    when(sensorTypeIDDouble.getID()).thenReturn(sensorTypeID);
    SensorName sensorNameDouble = mock(SensorName.class);
    when(sensorNameDouble.getSensorName()).thenReturn(sensorName);
    SensorID sensorIDDouble = mock(SensorID.class);
    when(sensorIDDouble.getID()).thenReturn(sensorID);
    ISensor sensorMock = mock(ISensor.class);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);
    when(sensorMock.getDeviceID()).thenReturn(deviceIDDouble);
    when(sensorMock.getModelPath()).thenReturn(modelPathDouble);
    when(sensorMock.getSensorTypeID()).thenReturn(sensorTypeIDDouble);
    when(sensorMock.getSensorName()).thenReturn(sensorNameDouble);

    String expected = "SensorDataModel{" +
        "sensorID='" + sensorID + '\'' +
        ", deviceID='" + deviceID + '\'' +
        ", modelPath='" + modelPath + '\'' +
        ", sensorTypeID='" + sensorTypeID + '\'' +
        ", sensorName='" + sensorName + '\'' +
        ", latitude='" + null + '\'' +
        ", longitude='" + null + '\'' +
        ", startDate='" + null + '\'' +
        ", endDate='" + null + '\'' +
        '}';
    //Act
    SensorDataModel sensorDataModel = new SensorDataModel(sensorMock);
    //Assert
    assertEquals(expected, sensorDataModel.toString());
  }

  @Test
  void shouldSetLatitude() {
    //Arrange
    String deviceID = "deviceID";
    String modelPath = "modelPath";
    String sensorTypeID = "Temperature";
    String sensorName = "TemperatureSensor";
    String sensorID = "Teste";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.getID()).thenReturn(deviceID);
    ModelPath modelPathDouble = mock(ModelPath.class);
    when(modelPathDouble.getID()).thenReturn(modelPath);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
    when(sensorTypeIDDouble.getID()).thenReturn(sensorTypeID);
    SensorName sensorNameDouble = mock(SensorName.class);
    when(sensorNameDouble.getSensorName()).thenReturn(sensorName);
    SensorID sensorIDDouble = mock(SensorID.class);
    when(sensorIDDouble.getID()).thenReturn(sensorID);
    ISensor sensorMock = mock(ISensor.class);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);
    when(sensorMock.getDeviceID()).thenReturn(deviceIDDouble);
    when(sensorMock.getModelPath()).thenReturn(modelPathDouble);
    when(sensorMock.getSensorTypeID()).thenReturn(sensorTypeIDDouble);
    when(sensorMock.getSensorName()).thenReturn(sensorNameDouble);
    SensorDataModel sensorDataModel = new SensorDataModel(sensorMock);
    String latitude = "10.0";
    //Act
    sensorDataModel.setLatitude(latitude);
    //Assert
    assertEquals(latitude, sensorDataModel.getLatitude());
  }

  @Test
  void shouldSetLongitude() {
    //Arrange
    String deviceID = "deviceID";
    String modelPath = "modelPath";
    String sensorTypeID = "Temperature";
    String sensorName = "TemperatureSensor";
    String sensorID = "Teste";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.getID()).thenReturn(deviceID);
    ModelPath modelPathDouble = mock(ModelPath.class);
    when(modelPathDouble.getID()).thenReturn(modelPath);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
    when(sensorTypeIDDouble.getID()).thenReturn(sensorTypeID);
    SensorName sensorNameDouble = mock(SensorName.class);
    when(sensorNameDouble.getSensorName()).thenReturn(sensorName);
    SensorID sensorIDDouble = mock(SensorID.class);
    when(sensorIDDouble.getID()).thenReturn(sensorID);
    ISensor sensorMock = mock(ISensor.class);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);
    when(sensorMock.getDeviceID()).thenReturn(deviceIDDouble);
    when(sensorMock.getModelPath()).thenReturn(modelPathDouble);
    when(sensorMock.getSensorTypeID()).thenReturn(sensorTypeIDDouble);
    when(sensorMock.getSensorName()).thenReturn(sensorNameDouble);
    SensorDataModel sensorDataModel = new SensorDataModel(sensorMock);
    String longitude = "20.0";
    //Act
    sensorDataModel.setLongitude(longitude);
    //Assert
    assertEquals(longitude, sensorDataModel.getLongitude());
  }

  @Test
  void shouldSetStartDate() {
    //Arrange
    String deviceID = "deviceID";
    String modelPath = "modelPath";
    String sensorTypeID = "Temperature";
    String sensorName = "TemperatureSensor";
    String sensorID = "Teste";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.getID()).thenReturn(deviceID);
    ModelPath modelPathDouble = mock(ModelPath.class);
    when(modelPathDouble.getID()).thenReturn(modelPath);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
    when(sensorTypeIDDouble.getID()).thenReturn(sensorTypeID);
    SensorName sensorNameDouble = mock(SensorName.class);
    when(sensorNameDouble.getSensorName()).thenReturn(sensorName);
    SensorID sensorIDDouble = mock(SensorID.class);
    when(sensorIDDouble.getID()).thenReturn(sensorID);
    ISensor sensorMock = mock(ISensor.class);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);
    when(sensorMock.getDeviceID()).thenReturn(deviceIDDouble);
    when(sensorMock.getModelPath()).thenReturn(modelPathDouble);
    when(sensorMock.getSensorTypeID()).thenReturn(sensorTypeIDDouble);
    when(sensorMock.getSensorName()).thenReturn(sensorNameDouble);
    SensorDataModel sensorDataModel = new SensorDataModel(sensorMock);
    String startDate = "2021-01-01";
    //Act
    sensorDataModel.setStartDate(startDate);
    //Assert
    assertEquals(startDate, sensorDataModel.getStartDate());
  }

  @Test
  void shouldSetEndData() {
    //Arrange
    String deviceID = "deviceID";
    String modelPath = "modelPath";
    String sensorTypeID = "Temperature";
    String sensorName = "TemperatureSensor";
    String sensorID = "Teste";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.getID()).thenReturn(deviceID);
    ModelPath modelPathDouble = mock(ModelPath.class);
    when(modelPathDouble.getID()).thenReturn(modelPath);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
    when(sensorTypeIDDouble.getID()).thenReturn(sensorTypeID);
    SensorName sensorNameDouble = mock(SensorName.class);
    when(sensorNameDouble.getSensorName()).thenReturn(sensorName);
    SensorID sensorIDDouble = mock(SensorID.class);
    when(sensorIDDouble.getID()).thenReturn(sensorID);
    ISensor sensorMock = mock(ISensor.class);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);
    when(sensorMock.getDeviceID()).thenReturn(deviceIDDouble);
    when(sensorMock.getModelPath()).thenReturn(modelPathDouble);
    when(sensorMock.getSensorTypeID()).thenReturn(sensorTypeIDDouble);
    when(sensorMock.getSensorName()).thenReturn(sensorNameDouble);
    SensorDataModel sensorDataModel = new SensorDataModel(sensorMock);
    String endDate = "2021-01-01";
    //Act
    sensorDataModel.setEndDate(endDate);
    //Assert
    assertEquals(endDate, sensorDataModel.getEndDate());
  }

  @Test
  void shouldReturnSensorID() {
    //Arrange
    String deviceID = "deviceID";
    String modelPath = "modelPath";
    String sensorTypeID = "Temperature";
    String sensorName = "TemperatureSensor";
    String sensorID = "Teste";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.getID()).thenReturn(deviceID);
    ModelPath modelPathDouble = mock(ModelPath.class);
    when(modelPathDouble.getID()).thenReturn(modelPath);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
    when(sensorTypeIDDouble.getID()).thenReturn(sensorTypeID);
    SensorName sensorNameDouble = mock(SensorName.class);
    when(sensorNameDouble.getSensorName()).thenReturn(sensorName);
    SensorID sensorIDDouble = mock(SensorID.class);
    when(sensorIDDouble.getID()).thenReturn(sensorID);
    ISensor sensorMock = mock(ISensor.class);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);
    when(sensorMock.getDeviceID()).thenReturn(deviceIDDouble);
    when(sensorMock.getModelPath()).thenReturn(modelPathDouble);
    when(sensorMock.getSensorTypeID()).thenReturn(sensorTypeIDDouble);
    when(sensorMock.getSensorName()).thenReturn(sensorNameDouble);
    SensorDataModel sensorDataModel = new SensorDataModel(sensorMock);
    //Act
    String result = sensorDataModel.getSensorID();
    //Assert
    assertEquals(sensorID, result);
  }

  @Test
  void shouldReturnDeviceID() {
    //Arrange
    String deviceID = "deviceID";
    String modelPath = "modelPath";
    String sensorTypeID = "Temperature";
    String sensorName = "TemperatureSensor";
    String sensorID = "Teste";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.getID()).thenReturn(deviceID);
    ModelPath modelPathDouble = mock(ModelPath.class);
    when(modelPathDouble.getID()).thenReturn(modelPath);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
    when(sensorTypeIDDouble.getID()).thenReturn(sensorTypeID);
    SensorName sensorNameDouble = mock(SensorName.class);
    when(sensorNameDouble.getSensorName()).thenReturn(sensorName);
    SensorID sensorIDDouble = mock(SensorID.class);
    when(sensorIDDouble.getID()).thenReturn(sensorID);
    ISensor sensorMock = mock(ISensor.class);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);
    when(sensorMock.getDeviceID()).thenReturn(deviceIDDouble);
    when(sensorMock.getModelPath()).thenReturn(modelPathDouble);
    when(sensorMock.getSensorTypeID()).thenReturn(sensorTypeIDDouble);
    when(sensorMock.getSensorName()).thenReturn(sensorNameDouble);
    SensorDataModel sensorDataModel = new SensorDataModel(sensorMock);
    //Act
    String result = sensorDataModel.getDeviceID();
    //Assert
    assertEquals(deviceID, result);
  }

  @Test
  void shouldReturnModelPath() {
    //Arrange
    String deviceID = "deviceID";
    String modelPath = "modelPath";
    String sensorTypeID = "Temperature";
    String sensorName = "TemperatureSensor";
    String sensorID = "Teste";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.getID()).thenReturn(deviceID);
    ModelPath modelPathDouble = mock(ModelPath.class);
    when(modelPathDouble.getID()).thenReturn(modelPath);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
    when(sensorTypeIDDouble.getID()).thenReturn(sensorTypeID);
    SensorName sensorNameDouble = mock(SensorName.class);
    when(sensorNameDouble.getSensorName()).thenReturn(sensorName);
    SensorID sensorIDDouble = mock(SensorID.class);
    when(sensorIDDouble.getID()).thenReturn(sensorID);
    ISensor sensorMock = mock(ISensor.class);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);
    when(sensorMock.getDeviceID()).thenReturn(deviceIDDouble);
    when(sensorMock.getModelPath()).thenReturn(modelPathDouble);
    when(sensorMock.getSensorTypeID()).thenReturn(sensorTypeIDDouble);
    when(sensorMock.getSensorName()).thenReturn(sensorNameDouble);
    SensorDataModel sensorDataModel = new SensorDataModel(sensorMock);
    //Act
    String result = sensorDataModel.getModelPath();
    //Assert
    assertEquals(modelPath, result);
  }

  @Test
  void shouldReturnSensorTypeID() {
    //Arrange
    String deviceID = "deviceID";
    String modelPath = "modelPath";
    String sensorTypeID = "Temperature";
    String sensorName = "TemperatureSensor";
    String sensorID = "Teste";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.getID()).thenReturn(deviceID);
    ModelPath modelPathDouble = mock(ModelPath.class);
    when(modelPathDouble.getID()).thenReturn(modelPath);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
    when(sensorTypeIDDouble.getID()).thenReturn(sensorTypeID);
    SensorName sensorNameDouble = mock(SensorName.class);
    when(sensorNameDouble.getSensorName()).thenReturn(sensorName);
    SensorID sensorIDDouble = mock(SensorID.class);
    when(sensorIDDouble.getID()).thenReturn(sensorID);
    ISensor sensorMock = mock(ISensor.class);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);
    when(sensorMock.getDeviceID()).thenReturn(deviceIDDouble);
    when(sensorMock.getModelPath()).thenReturn(modelPathDouble);
    when(sensorMock.getSensorTypeID()).thenReturn(sensorTypeIDDouble);
    when(sensorMock.getSensorName()).thenReturn(sensorNameDouble);
    SensorDataModel sensorDataModel = new SensorDataModel(sensorMock);
    //Act
    String result = sensorDataModel.getSensorTypeID();
    //Assert
    assertEquals(sensorTypeID, result);
  }

  @Test
  void shouldReturnSensorName() {
    //Arrange
    String deviceID = "deviceID";
    String modelPath = "modelPath";
    String sensorTypeID = "Temperature";
    String sensorName = "TemperatureSensor";
    String sensorID = "Teste";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.getID()).thenReturn(deviceID);
    ModelPath modelPathDouble = mock(ModelPath.class);
    when(modelPathDouble.getID()).thenReturn(modelPath);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
    when(sensorTypeIDDouble.getID()).thenReturn(sensorTypeID);
    SensorName sensorNameDouble = mock(SensorName.class);
    when(sensorNameDouble.getSensorName()).thenReturn(sensorName);
    SensorID sensorIDDouble = mock(SensorID.class);
    when(sensorIDDouble.getID()).thenReturn(sensorID);
    ISensor sensorMock = mock(ISensor.class);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);
    when(sensorMock.getDeviceID()).thenReturn(deviceIDDouble);
    when(sensorMock.getModelPath()).thenReturn(modelPathDouble);
    when(sensorMock.getSensorTypeID()).thenReturn(sensorTypeIDDouble);
    when(sensorMock.getSensorName()).thenReturn(sensorNameDouble);
    SensorDataModel sensorDataModel = new SensorDataModel(sensorMock);
    //Act
    String result = sensorDataModel.getSensorName();
    //Assert
    assertEquals(sensorName, result);
  }

  @Test
  void shouldReturnLatitudeAndLongitude() {
    //Arrange
    String deviceID = "deviceID";
    String modelPath = "modelPath";
    String sensorTypeID = "Temperature";
    String sensorName = "TemperatureSensor";
    String sensorID = "Teste";
    String latitude = "10.0";
    String longitude = "20.0";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.getID()).thenReturn(deviceID);
    ModelPath modelPathDouble = mock(ModelPath.class);
    when(modelPathDouble.getID()).thenReturn(modelPath);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);
    when(sensorTypeIDDouble.getID()).thenReturn(sensorTypeID);
    SensorName sensorNameDouble = mock(SensorName.class);
    when(sensorNameDouble.getSensorName()).thenReturn(sensorName);
    SensorID sensorIDDouble = mock(SensorID.class);
    when(sensorIDDouble.getID()).thenReturn(sensorID);
    ISensor sensorMock = mock(ISensor.class);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);

    when(sensorMock.getID()).thenReturn(sensorIDDouble);
    when(sensorMock.getDeviceID()).thenReturn(deviceIDDouble);
    when(sensorMock.getModelPath()).thenReturn(modelPathDouble);
    when(sensorMock.getSensorTypeID()).thenReturn(sensorTypeIDDouble);
    when(sensorMock.getSensorName()).thenReturn(sensorNameDouble);
    SensorDataModel sensorDataModel = new SensorDataModel(sensorMock);
    sensorDataModel.setLatitude(latitude);
    sensorDataModel.setLongitude(longitude);
    //Act
    String result = sensorDataModel.getLatitude();
    String result2 = sensorDataModel.getLongitude();
    //Assert
    assertEquals(latitude, result);
    assertEquals(longitude, result2);
  }
}