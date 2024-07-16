/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.PathEncoder;
import smarthome.utils.dto.SensorDTO;

class SensorAssemblerTest {

  /**
   * Test that the method domainToDTO converts a Sensor to a SensorDTO.
   */
  @Test
  void shouldConvertSensorToSensorDTO_WhenSensorIsValid() {
    // Arrange
    String deviceID = "123";
    String modelPath = "sensorModelPath";
    String expectedModelPath = PathEncoder.encode(modelPath);
    String sensorTypeID = "321";
    String sensorID = "432";
    String sensorName = "dewPoint";

    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.toString()).thenReturn(deviceID);

    ModelPath modelPathDouble = mock(ModelPath.class);
    when(modelPathDouble.toString()).thenReturn(modelPath);

    SensorTypeID sensorTypeDouble = mock(SensorTypeID.class);
    when(sensorTypeDouble.toString()).thenReturn(sensorTypeID);

    SensorID sensorIDDouble = mock(SensorID.class);
    when(sensorIDDouble.toString()).thenReturn(sensorID);

    SensorName sensorNameDouble = mock(SensorName.class);
    when(sensorNameDouble.toString()).thenReturn(sensorName);

    ISensor sensorDouble = mock(ISensor.class);
    when(sensorDouble.getDeviceID()).thenReturn(deviceIDDouble);
    when(sensorDouble.getModelPath()).thenReturn(modelPathDouble);
    when(sensorDouble.getSensorTypeID()).thenReturn(sensorTypeDouble);
    when(sensorDouble.getID()).thenReturn(sensorIDDouble);
    when(sensorDouble.getSensorName()).thenReturn(sensorNameDouble);

    SensorAssembler sensorAssembler = new SensorAssembler();
    String expected =
        deviceID + " " + expectedModelPath + " " + sensorTypeID + " " + sensorID + " " + sensorName;

    // Act
    SensorDTO sensorDTO = sensorAssembler.domainToDTO(sensorDouble);

    // Assert
    assertEquals(expected, sensorDTO.toString());
  }

  /**
   * Test that the method domainToDTO throws an exception when the Sensor is null.
   */
  @Test
  void shouldThrowException_WhenSensorIsNull() {
    // Arrange
    ISensor sensor = null;

    SensorAssembler sensorAssembler = new SensorAssembler();

    String expected = "Sensor is required";

    // Act & Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              sensorAssembler.domainToDTO(sensor);
            });

    String result = exception.getMessage();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Test that the method domainToDTO throws an exception when the list of Sensors is null.
   */
  @Test
  void shouldConvertSensorListToListOfSensorsDTOList(){
    // Arrange
    String deviceID = "123";
    String modelPath = "sensorModelPath";
    String expectedModelPath = PathEncoder.encode(modelPath);
    String sensorTypeID = "321";
    String sensorID = "432";
    String sensorName = "dewPoint";

    String deviceID2 = "12";
    String modelPath2 = "sensorModelPath2";
    String expectedModelPath2 = PathEncoder.encode(modelPath2);
    String sensorTypeID2 = "321";
    String sensorID2 = "43212";
    String sensorName2 = "dewPoint2";

    // sensor1
    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.toString()).thenReturn(deviceID);

    ModelPath modelPathDouble = mock(ModelPath.class);
    when(modelPathDouble.toString()).thenReturn(modelPath);

    SensorTypeID sensorTypeDouble = mock(SensorTypeID.class);
    when(sensorTypeDouble.toString()).thenReturn(sensorTypeID);

    SensorID sensorIDDouble = mock(SensorID.class);
    when(sensorIDDouble.toString()).thenReturn(sensorID);

    SensorName sensorNameDouble = mock(SensorName.class);
    when(sensorNameDouble.toString()).thenReturn(sensorName);

    ISensor sensorDouble = mock(ISensor.class);
    when(sensorDouble.getDeviceID()).thenReturn(deviceIDDouble);
    when(sensorDouble.getModelPath()).thenReturn(modelPathDouble);
    when(sensorDouble.getSensorTypeID()).thenReturn(sensorTypeDouble);
    when(sensorDouble.getID()).thenReturn(sensorIDDouble);
    when(sensorDouble.getSensorName()).thenReturn(sensorNameDouble);

    // sensor2
    DeviceID deviceIDDouble2 = mock(DeviceID.class);
    when(deviceIDDouble2.toString()).thenReturn(deviceID2);

    ModelPath modelPathDouble2 = mock(ModelPath.class);
    when(modelPathDouble2.toString()).thenReturn(modelPath2);

    SensorTypeID sensorTypeDouble2 = mock(SensorTypeID.class);
    when(sensorTypeDouble2.toString()).thenReturn(sensorTypeID2);

    SensorID sensorIDDouble2 = mock(SensorID.class);
    when(sensorIDDouble2.toString()).thenReturn(sensorID2);

    SensorName sensorNameDouble2 = mock(SensorName.class);
    when(sensorNameDouble2.toString()).thenReturn(sensorName2);

    ISensor sensorDouble2 = mock(ISensor.class);
    when(sensorDouble2.getDeviceID()).thenReturn(deviceIDDouble2);
    when(sensorDouble2.getModelPath()).thenReturn(modelPathDouble2);
    when(sensorDouble2.getSensorTypeID()).thenReturn(sensorTypeDouble2);
    when(sensorDouble2.getID()).thenReturn(sensorIDDouble2);
    when(sensorDouble2.getSensorName()).thenReturn(sensorNameDouble2);

    SensorAssembler sensorAssembler = new SensorAssembler();

    List<ISensor> sensors = List.of(sensorDouble, sensorDouble2);

    SensorDTO sensorDTO1 = new SensorDTO(deviceID, expectedModelPath, sensorID, sensorTypeID,
        sensorName);
    SensorDTO sensorDTO2 = new SensorDTO(deviceID2, expectedModelPath2, sensorID2, sensorTypeID2,
        sensorName2);
    List<SensorDTO> expected = List.of(sensorDTO1, sensorDTO2);

    // Act
    List<SensorDTO> sensorsDTO = sensorAssembler.domainToDTO(sensors);

    // Assert
    assertEquals(expected.toString(), sensorsDTO.toString());
  }

  /**
   * Test that the method domainToDTO throws an exception when the list of Sensors is null.
   */
  @Test
  void shouldThrowException_WhenListOfSensorsIsNull() {
    // Arrange
    List<ISensor> sensors = null;

    SensorAssembler sensorAssembler = new SensorAssembler();

    // Act and Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              sensorAssembler.domainToDTO(sensors);
            });

    String expected = "The list of sensors cannot be null.";

    String result = exception.getMessage();

    // Assert
    assertEquals(expected, result);
  }
}
