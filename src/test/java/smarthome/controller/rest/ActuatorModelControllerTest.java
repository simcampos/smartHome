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
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.actuator_model.IActuatorModelFactory;
import smarthome.domain.repository.IActuatorModelRepository;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;

@SpringBootTest
@AutoConfigureMockMvc
class ActuatorModelControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private IActuatorModelFactory actuatorModelFactory;

  @MockBean private IActuatorModelRepository actuatorModelRepository;

  /**
   * Test for the ActuatorModelController class.
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnActuatorModelDTO_whenActuatorModelExists() throws Exception {
    // Arrange

    String actuatorModelPath = "path";
    String actuatorName = "Thermostat";
    String actuatorTypeID = "1";

    ModelPath modelPath = new ModelPath(actuatorModelPath);
    ActuatorTypeID typeID = new ActuatorTypeID(actuatorTypeID);
    ActuatorModelName actuatorName1 = new ActuatorModelName(actuatorName);

    ActuatorModel actuatorModel =
        actuatorModelFactory.createActuatorModel(modelPath, actuatorName1, typeID);

    //when(actuatorModelRepository.findBy_actuatorTypeID(typeID)).thenReturn(List.of(actuatorModel));

    // Act & Assert
    mockMvc
        .perform(get("/actuator-models?actuatorTypeID=" + actuatorTypeID).accept(
            MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(
            jsonPath("$._embedded.actuatorModelDTOList[0].actuatorModelName").value("Thermostat"))
        .andExpect(jsonPath("$._links.self").exists());
  }

  /**
   * Test for the ActuatorModelController class.
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnNotFound_WhenNoActuatorModelExists() throws Exception {
    // Arrange
    String actuatorTypeID = "1";
    ActuatorTypeID typeID = new ActuatorTypeID(actuatorTypeID);

    when(actuatorModelRepository.findBy_actuatorTypeID(typeID)).thenReturn(List.of());
    // Act & Assert
    mockMvc
        .perform(
            get("/actuator-models/{actuatorTypeID}", actuatorTypeID)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}
