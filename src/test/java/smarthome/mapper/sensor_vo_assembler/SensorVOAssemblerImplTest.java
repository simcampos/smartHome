/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.mapper.sensor_vo_assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.PathEncoder;
import smarthome.utils.entry_dto.sensor_entry_dto.ISensorEntryDTO;
import smarthome.utils.entry_dto.sensor_entry_dto.SensorGenericEntryDTOImp;
import smarthome.utils.entry_dto.sensor_entry_dto.SensorWithDateEntryDTOImp;
import smarthome.utils.entry_dto.sensor_entry_dto.SensorWithGPSEntryDTOImp;

class SensorVOAssemblerImplTest {

  /**
   * Tests if the object is instantiated when the attributes are null.
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenUnsupportedSensorDataDTO() {
    // Arrange
    SensorVOAssemblerImpl sensorVOAssembler = new SensorVOAssemblerImpl();
    String message = "Unsupported sensor data DTO";
    ISensorEntryDTO sensorDataDTO = null;
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> sensorVOAssembler.getSensorParameters(sensorDataDTO));
    // Assert
    assertEquals(message, exception.getMessage());
  }

  /**
   * Tests if the object is instantiated when the SensorDataDTO is with gps attribute.
   */
  @Test
  void shouldReturnArrayOfObjectsWhenSensorDataDTOIsSensorWithGPSDataDTO() {
    // Arrange
    String deviceID = "deviceID";
    String sensorModelPath = "sensorModelPath";
    String encodedSensorModelPath = PathEncoder.encode(sensorModelPath);
    String sensorName = "sensorName";
    String sensorTypeID = "sensorTypeID";
    String latitude = "8.5";
    String longitude = "0.7";
    ISensorEntryDTO sensorDataDTO =
        new SensorWithGPSEntryDTOImp(
            deviceID, encodedSensorModelPath, sensorTypeID,  sensorName, latitude, longitude);
    SensorVOAssemblerImpl sensorVOAssembler = new SensorVOAssemblerImpl();

    DeviceID deviceID1 = new DeviceID(deviceID);
    ModelPath modelPath = new ModelPath(sensorModelPath);
    SensorName sensorName1 = new SensorName(sensorName);
    SensorTypeID sensorTypeID1 = new SensorTypeID(sensorTypeID);
    GPS gps = new GPS(Double.parseDouble(latitude), Double.parseDouble(longitude));
    Object[] expected = {deviceID1, modelPath, sensorTypeID1, sensorName1, gps};

    // Act
    Object[] result = sensorVOAssembler.getSensorParameters(sensorDataDTO);
    // Assert
    assertEquals(Arrays.stream(expected).toList(), Arrays.stream(result).toList());
  }

  /**
   * Tests if the object is instantiated when the SensorDataDTO is generic.
   */
  @Test
  void shouldReturnArrayOfObjectsWhenSensorDataDTOIsSensorGenericDataDTOImp() {
    // Arrange
    String deviceID = "deviceID";
    String sensorModelPath = "sensorModelPath";
    String encodedSensorModelPath = PathEncoder.encode(sensorModelPath);
    String sensorName = "sensorName";
    String sensorTypeID = "sensorTypeID";
    ISensorEntryDTO sensorDataDTO =
        new SensorGenericEntryDTOImp(deviceID, encodedSensorModelPath, sensorTypeID, sensorName);
    SensorVOAssemblerImpl sensorVOAssembler = new SensorVOAssemblerImpl();

    DeviceID deviceID1 = new DeviceID(deviceID);
    ModelPath modelPath = new ModelPath(sensorModelPath);
    SensorName sensorName1 = new SensorName(sensorName);
    SensorTypeID sensorTypeID1 = new SensorTypeID(sensorTypeID);
    Object[] expected = {deviceID1, modelPath, sensorTypeID1, sensorName1};

    // Act
    Object[] result = sensorVOAssembler.getSensorParameters(sensorDataDTO);
    // Assert
    assertEquals(Arrays.stream(expected).toList(), Arrays.stream(result).toList());
  }

  /**
   * Tests if the object is instantiated when the SensorDataDTO is with date attribute.
   */
  @Test
  void shouldReturnArrayOfObjectsWhenSensorDataDTOIsSensorDataWithDateDTOImp() {
    // Arrange
    String deviceID = "deviceID";
    String sensorModelPath = "sensorModelPath";
    String encodedSensorModelPath = PathEncoder.encode(sensorModelPath);
    String sensorName = "sensorName";
    String sensorTypeID = "sensorTypeID";
    String startDate = "2021-08-01T00:00:00";
    String endDate = "2021-08-02T00:00:00";
    ISensorEntryDTO sensorDataDTO =
        new SensorWithDateEntryDTOImp(
            deviceID, encodedSensorModelPath, sensorTypeID, sensorName, startDate, endDate);
    SensorVOAssemblerImpl sensorVOAssembler = new SensorVOAssemblerImpl();

    DeviceID deviceID1 = new DeviceID(deviceID);
    ModelPath modelPath = new ModelPath(sensorModelPath);
    SensorName sensorName1 = new SensorName(sensorName);
    SensorTypeID sensorTypeID1 = new SensorTypeID(sensorTypeID);
    LocalDateTime startDate1 = LocalDateTime.parse(startDate);
    LocalDateTime endDate1 = LocalDateTime.parse(endDate);
    DatePeriod date = new DatePeriod(startDate1, endDate1);
    Object[] expected = {deviceID1, modelPath, sensorTypeID1, sensorName1, date};

    // Act
    Object[] result = sensorVOAssembler.getSensorParameters(sensorDataDTO);
    // Assert
    assertEquals(Arrays.stream(expected).toList(), Arrays.stream(result).toList());
  }
}
