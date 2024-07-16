/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor_type;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;

class ImpSensorTypeFactoryTest {

  /**
   * Test to ensure that a SensorType can be created successfully
   */
  @Test
  void shouldCreateSensorType_whenAttributesAreValid() {
    // Arrange
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    UnitID unitDouble = mock(UnitID.class);

    SensorTypeFactoryImpl factory = new SensorTypeFactoryImpl();

    // Act
    SensorType createdSensorType = factory.createSensorType(typeDescriptionDouble, unitDouble);

    // Assert
    assertNotNull(createdSensorType);
  }

  /**
   * Test to ensure that a SensorType can be created successfully with a SensorTypeID
   */
  @Test
  void shouldCreateSensorType_whenAttributesAreValidWithSensorTypeID() {
    // Arrange
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    UnitID unitDouble = mock(UnitID.class);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

    SensorTypeFactoryImpl factory = new SensorTypeFactoryImpl();

    // Act
    SensorType createdSensorType = factory.createSensorType(sensorTypeIDDouble,
        typeDescriptionDouble, unitDouble);

    // Assert
    assertNotNull(createdSensorType);
  }
}
