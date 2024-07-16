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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.actuator_type.IActuatorTypeFactory;
import smarthome.domain.repository.IActuatorTypeRepository;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.utils.LoadDefaultConfiguration;


@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class ActuatorTypeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private IActuatorTypeFactory actuatorTypeFactory;

  @MockBean
  private IActuatorTypeRepository actuatorTypeRepository;

  @MockBean
  private LoadDefaultConfiguration loadConfig;

  /**
   * Tests successful retrieval of all actuator types.
   *
   * @throws Exception if the MVC request building or execution fails
   */
  @Test
  void shouldReturnAllActuatorTypes_WhenRequested() throws Exception {
    // Arrange
    TypeDescription typeDescription = new TypeDescription("Blind Roller");
    UnitID unitID = new UnitID("Percent");

    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(typeDescription, unitID);

    when(actuatorTypeRepository.findAll()).thenReturn(List.of(actuatorType));
    // Act & Assert
    mockMvc.perform(get("/actuator-types")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.actuatorTypeDTOList[0].actuatorTypeID").value(
            actuatorType.getID().getID()))
        .andExpect(jsonPath("$._links.self.href").exists());
  }

  /**
   * Tests the scenario where no actuator types are found.
   *
   * @throws Exception if the MVC request building or execution fails
   */
  @Test
  void shouldReturnEmptyList_WhenNoActuatorTypesAvailable() throws Exception {
    // Arrange
    when(actuatorTypeRepository.findAll()).thenReturn(List.of());
    // Act & Assert
    mockMvc.perform(get("/actuator-types")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.actuatorTypeDTOList").doesNotExist())
        .andExpect(jsonPath("$._links.self.href").exists());
  }
}
