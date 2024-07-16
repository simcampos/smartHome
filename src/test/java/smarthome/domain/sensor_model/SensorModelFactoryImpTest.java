/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor_model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorModelName;
import smarthome.domain.value_object.SensorTypeID;

/**
 * Tests for the {@link SensorModelFactoryImpl} class, ensuring that sensor models are created
 * correctly.
 */

class SensorModelFactoryImpTest {

  /**
   * Test to ensure that a sensor model can be created successfully when given valid parameters.
   */
  @Test
  void shouldCreateSensorModelWhenGivenValidSensorModelNameAndModelPath() {
    // Arrange
    SensorModelName sensorModelName = mock(SensorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();
    // Act

    SensorModel sensorModel =
        sensorModelFactory.createSensorModel(sensorModelName, modelPath, sensorTypeID);
    // Assert
    assertNotNull(sensorModel);
  }
}
