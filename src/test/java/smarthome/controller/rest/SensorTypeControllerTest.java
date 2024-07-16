/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.domain.repository.ISensorTypeRepository;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.sensor_type.SensorTypeFactoryImpl;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.persistence.spring_data.unit.UnitSpringDataRepository;
import smarthome.utils.LoadDefaultConfiguration;
import smarthome.utils.entry_dto.SensorTypeEntryDTO;


@SpringBootTest
@AutoConfigureMockMvc
class SensorTypeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private ISensorTypeRepository sensorTypeRepository;

  @Autowired
  private SensorTypeFactoryImpl sensorTypeFactoryImpl;

  @MockBean
  private UnitSpringDataRepository unitRepository;

  @MockBean
  private LoadDefaultConfiguration loadDefaultConfiguration;

  /**
   * Verify that a SensorType is correctly created
   */
  @Test
  void shouldReturnSensorTypeDTO_whenSensorTypeIsCreated() throws Exception {
    // Arrange
    String sensorTypeDescription = "Temperature";
    String unitID = "Celsius";
    SensorTypeEntryDTO sensorTypeDataDTO = new SensorTypeEntryDTO(sensorTypeDescription, unitID);

    TypeDescription typeDescription = new TypeDescription(sensorTypeDescription);
    UnitID unitID2 = new UnitID(unitID);


    SensorType sensorType = sensorTypeFactoryImpl.createSensorType(typeDescription, unitID2);
    when(unitRepository.containsOfIdentity(sensorType.getUnitID())).thenReturn(true);
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    // Act & Assert
    mockMvc.perform(post("/sensor-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorTypeDataDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.description").value(sensorTypeDescription))
        .andExpect(jsonPath("$.unitID").value(unitID))
        .andExpect(jsonPath("$._links.self").exists());
  }

  /**
   * Verify that a SensorType is not created when the description is null
   */
  @Test
  void shouldReturnBadRequest_whenSensorTypeDescriptionIsNull() throws Exception {
    // Arrange
    String unitID = "Celsius";
    SensorTypeEntryDTO sensorTypeDataDTO = new SensorTypeEntryDTO(null, unitID);

    // Act & Assert
    mockMvc.perform(post("/sensor-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorTypeDataDTO)))
        .andExpect(status().isBadRequest());
  }


  /**
   * Verify that a SensorType is not created when the unitID is null
   */
  @Test
  void shouldReturnBadRequest_whenUnitIDIsNull() throws Exception {
    // Arrange
    String sensorTypeDescription = "Temperature";
    SensorTypeEntryDTO sensorTypeDataDTO = new SensorTypeEntryDTO(sensorTypeDescription, null);

    // Act & Assert
    mockMvc.perform(post("/sensor-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorTypeDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * This test case verifies that the SensorTypeController returns a 404 Not Found status when no
   * actuator types are available.
   */
  @Test
  void shouldReturnNotFound_whenNoSensorTypesAvailable() throws Exception {
    when(sensorTypeRepository.findAll()).thenReturn(Collections.emptyList());

    mockMvc.perform(get("/sensor-types")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  /**
   * This test case verifies that the SensorTypeController returns a list of sensor types when
   * available.
   */
  @Test
  void shouldReturnSensorTypes_whenFound() throws Exception {
    // Arrange
    String sensorTypeDescription = "Temperature";
    String unitID = "Celsius";

    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorType sensorType = sensorTypeFactory.createSensorType(new TypeDescription(sensorTypeDescription), new UnitID(unitID));

    when(sensorTypeRepository.findAll()).thenReturn(List.of(sensorType));

    mockMvc.perform(get("/sensor-types")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.sensorTypeDTOList[*].description").value(sensorTypeDescription))
        .andExpect(jsonPath("$._embedded.sensorTypeDTOList.[*].unitID").value(unitID))
        .andExpect(jsonPath("$._embedded.sensorTypeDTOList[*]._links.['sensor-models']").exists())
        .andExpect(jsonPath("$._embedded.sensorTypeDTOList[*]._links.['self']").exists());
  }

  /**
   * This test case verifies that the SensorTypeController returns a bad request status when an
   * invalid sensor type is added.
   */
  @Test
  void shouldReturnBadRequest_whenInvalidSensorTypeAdded() throws Exception {
    // Arrange
    SensorTypeEntryDTO sensorTypeDataDTO = new SensorTypeEntryDTO("", "");
    // Act & Assert
    mockMvc.perform(post("/sensor-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorTypeDataDTO)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void shouldReturnSensorTypeByID_whenFound() throws Exception {
    // Arrange
    String sensorTypeDescription = "Temperature";
    String unitID = "Celsius";
    UnitID unitIDVO = new UnitID(unitID);

    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorType sensorType = sensorTypeFactory.createSensorType(
        new TypeDescription(sensorTypeDescription), unitIDVO);

    when(unitRepository.containsOfIdentity(sensorType.getUnitID())).thenReturn(true);

    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    mockMvc.perform(get("/sensor-types/" + sensorType.getID())
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.description").value(sensorTypeDescription))
        .andExpect(jsonPath("$.unitID").value(unitID))
        .andExpect(jsonPath("$._links.['sensor-models']").exists())
        .andExpect(jsonPath("$._links.['self']").exists());
  }



}

