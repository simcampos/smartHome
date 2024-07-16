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

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.utils.dto.ActuatorTypeDTO;

class ActuatorTypeAssemblerTest {

  /**
   * Test if the domainToDTO method returns an ActuatorTypeDTO object when the description type is
   * valid.
   */
  @Test
  void shouldReturnActuatorTypeAssemblerDTO_WhenDescriptionTypeIsValid() {
    // Arrange
    String actuatorTypeID = "SwitchActuator"; // Assuming this is the ID, not the description
    String typeDescription = "Switch Actuator";
    String unit = "Switch";

    ActuatorType actuatorType = mock(ActuatorType.class);
    ActuatorTypeID actuatorTypeIDMock = mock(ActuatorTypeID.class); // Mock the ActuatorTypeID
    TypeDescription typeDescriptionMock = mock(TypeDescription.class); // Mock the TypeDescription
    UnitID unitIDMock = mock(UnitID.class); // Mock the UnitID

    when(actuatorType.getID()).thenReturn(actuatorTypeIDMock);
    when(actuatorType.getActuatorTypeName()).thenReturn(typeDescriptionMock);
    when(actuatorType.getUnit()).thenReturn(unitIDMock);
    when(actuatorTypeIDMock.toString())
        .thenReturn(actuatorTypeID); // Keep this if you use toString() elsewhere
    when(actuatorTypeIDMock.getID()).thenReturn(actuatorTypeID); // Correct stubbing for getId()
    when(typeDescriptionMock.toString()).thenReturn(typeDescription);
    when(unitIDMock.toString()).thenReturn(unit);

    ActuatorTypeAssembler actuatorTypeAssembler = new ActuatorTypeAssembler();

    String expected = actuatorTypeID + " " + typeDescription + " " + unit;
    // Act
    ActuatorTypeDTO actuatorTypeDTO = actuatorTypeAssembler.domainToDTO(actuatorType);

    // Assert
    assertEquals(expected, actuatorTypeDTO.toString());
  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the ActuatorType is
   * null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenActuatorTypeIsNull() {
    // Arrange
    ActuatorType actuatorType = null;
    ActuatorTypeAssembler actuatorTypeAssembler = new ActuatorTypeAssembler();

    String expected = "Actuator Type is required";

    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> actuatorTypeAssembler.domainToDTO(actuatorType));

    // Assert
    String result = exception.getMessage();
    assertEquals(expected, result);
  }

  /**
   * Test if the domainToDTO method returns a list of ActuatorTypeDTO objects when the description
   * type is valid.
   */
  @Test
  void shouldReturnActuatorTypeAssemblerDTOList_WhenDescriptionTypeIsValid()
       {
    // Arrange
    String actuatorTypeID1 = "BlindActuator";
    String typeDescription1 = "Blind Actuator";
    String unit1 = "Switch";
    String actuatorTypeID2 = "SwitchActuator";
    String typeDescription2 = "Switch Actuator";
    String unit2 = "Switch";

    // Mocking ActuatorType objects
    ActuatorType actuatorType1 = mock(ActuatorType.class);
    ActuatorType actuatorType2 = mock(ActuatorType.class);

    // Setting up mocks for actuatorType1
    ActuatorTypeID actuatorTypeID1Mock = mock(ActuatorTypeID.class);
    TypeDescription typeDescription1Mock = mock(TypeDescription.class);
    UnitID unit1Mock = mock(UnitID.class);
    when(actuatorType1.getID()).thenReturn(actuatorTypeID1Mock);
    when(actuatorType1.getActuatorTypeName()).thenReturn(typeDescription1Mock);
    when(actuatorType1.getUnit()).thenReturn(unit1Mock);
    when(actuatorTypeID1Mock.toString()).thenReturn(actuatorTypeID1);
    when(typeDescription1Mock.toString()).thenReturn(typeDescription1);
    when(unit1Mock.toString()).thenReturn(unit1);
    when(actuatorTypeID1Mock.getID()).thenReturn(actuatorTypeID1);

    // Setting up mocks for actuatorType2
    ActuatorTypeID actuatorTypeID2Mock = mock(ActuatorTypeID.class);
    TypeDescription typeDescription2Mock = mock(TypeDescription.class);
    UnitID unit2Mock = mock(UnitID.class);
    when(actuatorType2.getID()).thenReturn(actuatorTypeID2Mock);
    when(actuatorType2.getActuatorTypeName()).thenReturn(typeDescription2Mock);
    when(actuatorType2.getUnit()).thenReturn(unit2Mock);
    when(actuatorTypeID2Mock.toString()).thenReturn(actuatorTypeID2);
    when(typeDescription2Mock.toString()).thenReturn(typeDescription2);
    when(unit2Mock.toString()).thenReturn(unit2);
    when(actuatorTypeID2Mock.getID()).thenReturn(actuatorTypeID2);

    List<ActuatorType> actuatorTypeList = Arrays.asList(actuatorType1, actuatorType2);

    ActuatorTypeAssembler actuatorTypeAssembler = new ActuatorTypeAssembler();

    // Expected DTOs
    ActuatorTypeDTO expectedDTO1 = new ActuatorTypeDTO(actuatorTypeID1, typeDescription1, unit1);
    ActuatorTypeDTO expectedDTO2 = new ActuatorTypeDTO(actuatorTypeID2, typeDescription2, unit2);
    List<ActuatorTypeDTO> expectedDTOList = List.of(expectedDTO1, expectedDTO2);

    // Act
    List<ActuatorTypeDTO> resultDTOList = actuatorTypeAssembler.domainToDTO(actuatorTypeList);

    // Assert
    assertEquals(expectedDTOList.toString(), resultDTOList.toString());
  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the list of
   * ActuatorTypes is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenActuatorTypeListIsNull() {
    // Arrange
    List<ActuatorType> actuatorTypeList = null;
    ActuatorTypeAssembler actuatorTypeAssembler = new ActuatorTypeAssembler();

    String expected = "The list of ActuatorTypes cannot be null.";

    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> actuatorTypeAssembler.domainToDTO(actuatorTypeList));

    // Assert
    String result = exception.getMessage();
    assertEquals(expected, result);
  }
}
