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

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.log.Log;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.LogID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitID;
import smarthome.utils.dto.LogDTO;

class LogAssemblerTest {

  /**
   * Test if the domainToDTO method returns a LogDTO object when the log is valid.
   */
  @Test
  void shouldReturnALogDTO_WhenGivenALog() {
    //Arrange
    String logID = "1";
    String deviceID = "1";
    String sensorID = "1";
    String sensorTypeID = "1";
    String reading = "1";
    String timestamp = "2021-10-10 10:10:10";
    String unitID = "1";

    Log log = mock(Log.class);

    when(log.getID()).thenReturn(mock(LogID.class));
    when(log.getID().toString()).thenReturn(logID);

    when(log.getDeviceID()).thenReturn(mock(DeviceID.class));
    when(log.getDeviceID().toString()).thenReturn(deviceID);

    when(log.getSensorID()).thenReturn(mock(SensorID.class));
    when(log.getSensorID().toString()).thenReturn(sensorID);

    when(log.getDescription()).thenReturn(mock(SensorTypeID.class));
    when(log.getDescription().toString()).thenReturn(sensorTypeID);

    when(log.getReadingValue()).thenReturn(mock(ReadingValue.class));
    when(log.getReadingValue().getValue()).thenReturn(reading);

    when(log.getTimeStamp()).thenReturn(mock(LocalDateTime.class));
    when(log.getTimeStamp().toString()).thenReturn(timestamp);

    when(log.getUnit()).thenReturn(mock(UnitID.class));
    when(log.getUnit().toString()).thenReturn(unitID);

    LogAssembler logAssembler = new LogAssembler();

    LogDTO expectedLogDTO = new LogDTO(logID, deviceID, sensorID, sensorTypeID, reading, timestamp, unitID);

    //Act
    LogDTO logDTO = logAssembler.domainToDTO(log);

    //Assert
    assertEquals(expectedLogDTO.toString(), logDTO.toString());
  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the log is null.
   */
  @Test
  void shouldThrowAnIllegalArgumentException_WhenGivenANullLog() {
    //Arrange
    Log log = null;
    LogAssembler logAssembler = new LogAssembler();

    String expectedMessage = "Log is required";

    //Act & Assert
    Exception exception  = assertThrows(IllegalArgumentException.class,
        () -> logAssembler.domainToDTO(log));

    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test if the domainToDTO method returns a list of LogDTO objects when the list of logs is
   * valid.
   */
  @Test
  void shouldReturnAListOfLogDTO_WhenGivenAListOfLogs() {
    //Arrange
    String logID = "1";
    String deviceID = "1";
    String sensorID = "1";
    String sensorTypeID = "1";
    String reading = "1";
    String timestamp = "2021-10-10 10:10:10";
    String unitID = "1";

    Log log = mock(Log.class);

    when(log.getID()).thenReturn(mock(LogID.class));
    when(log.getID().toString()).thenReturn(logID);

    when(log.getDeviceID()).thenReturn(mock(DeviceID.class));
    when(log.getDeviceID().toString()).thenReturn(deviceID);

    when(log.getSensorID()).thenReturn(mock(SensorID.class));
    when(log.getSensorID().toString()).thenReturn(sensorID);

    when(log.getDescription()).thenReturn(mock(SensorTypeID.class));
    when(log.getDescription().toString()).thenReturn(sensorTypeID);

    when(log.getReadingValue()).thenReturn(mock(ReadingValue.class));
    when(log.getReadingValue().toString()).thenReturn(reading);

    when(log.getTimeStamp()).thenReturn(mock(LocalDateTime.class));
    when(log.getTimeStamp().toString()).thenReturn(timestamp);

    when(log.getUnit()).thenReturn(mock(UnitID.class));
    when(log.getUnit().toString()).thenReturn(unitID);

    LogAssembler logAssembler = new LogAssembler();

    LogDTO LogDTO = new LogDTO(logID, deviceID, sensorID, sensorTypeID, reading, timestamp, unitID);

   int expectedSize = List.of(LogDTO).size();

    //Act
    List<LogDTO> logDTOList = logAssembler.domainToDTO(List.of(log));

    //Assert
    assertEquals(logDTOList.size(), expectedSize);
  }

  /**
   * Test when list of logs is null
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenGivenNullListOfLogs() {
    //Arrange
    List<Log> logs = null;
    LogAssembler logAssembler = new LogAssembler();

    String expectedMessage = "The list of Logs cannot be null.";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> logAssembler.domainToDTO(logs));

    String result = exception.getMessage();
    assertEquals(expectedMessage, result);
  }


}




