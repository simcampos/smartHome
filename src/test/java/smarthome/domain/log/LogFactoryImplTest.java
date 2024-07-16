/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.log;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.LogID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitID;

class LogFactoryImplTest {

  /**
   * Test to ensure that a Log can be created successfully when createLog is called with valid
   * parameters.
   */
  @Test
  void shouldCreateLog_WhenCreateLogIsCalledWithValidParameters() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    SensorID sensorID = mock(SensorID.class);
    LocalDateTime timeStamp = mock(LocalDateTime.class);
    ReadingValue readingValue = mock(ReadingValue.class);
    SensorTypeID description = mock(SensorTypeID.class);
    UnitID unit = mock(UnitID.class);

    LogFactoryImpl factory = new LogFactoryImpl();

    //Act
    Log result = factory.createLog(deviceID, sensorID, timeStamp, readingValue, description, unit);

    //Assert
    assertNotNull(result);
  }

  /**
   * Test to ensure that a Log can be created successfully when createLog is called with ID.
   */
  @Test
  void shouldCreateLog_WhenCreateLogIsCalledWithID() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    SensorID sensorID = mock(SensorID.class);
    LocalDateTime timeStamp = mock(LocalDateTime.class);
    ReadingValue readingValue = mock(ReadingValue.class);
    LogID logID = mock(LogID.class);
    SensorTypeID description = mock(SensorTypeID.class);
    UnitID unit = mock(UnitID.class);

    LogFactoryImpl factory = new LogFactoryImpl();

    //Act
    Log result = factory.createLog(logID, deviceID, sensorID, timeStamp, readingValue, description,
        unit);

    //Assert
    assertNotNull(result);
  }
}
