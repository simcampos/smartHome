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
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.device_type.DeviceTypeFactoryImpl;
import smarthome.domain.device_type.IDeviceTypeFactory;
import smarthome.domain.repository.IDeviceTypeRepository;
import smarthome.domain.value_object.TypeDescription;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class DeviceTypeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private IDeviceTypeRepository deviceTypeRepository;


  /**
   * Tests successful retrieval of all device types.
   */
  @Test
  void shouldReturnAllDeviceTypes_WhenRequested() throws Exception {
    // Arrange
    IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
    TypeDescription deviceTypeDescription = new TypeDescription("Test");
    DeviceType deviceType = deviceTypeFactory.createDeviceType(deviceTypeDescription);
    when(deviceTypeRepository.findAll()).thenReturn(List.of(deviceType));

    // Act & Assert
    mockMvc.perform(get("/device-types")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.self.href").exists());
  }

  /**
   * Tests the scenario where no device types are found.
   */
  @Test
  void shouldReturnNoDeviceTypes_WhenNoneExist() throws Exception {
    // Arrange
    when(deviceTypeRepository.findAll()).thenReturn(List.of());

    // Act & Assert
    mockMvc.perform(get("/device-types")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded").doesNotExist())
        .andExpect(jsonPath("$._links.self.href").exists());
  }
}