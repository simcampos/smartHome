/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

/**
 * This class represents the data transfer object for the Sensor data for a sensor with date info
 * to be received from the client.
 */

package smarthome.utils.entry_dto.sensor_entry_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor(force = true)
public class SensorWithDateEntryDTOImp implements ISensorEntryDTO {

  @NotBlank(message = "DeviceID cannot be empty")
  public final String deviceID;

  @NotBlank(message = "SensorModelPath cannot be empty")
  public final String sensorModelPath;

  @NotBlank(message = "SensorTypeID cannot be empty")
  public final String sensorTypeID;

  @NotBlank(message = "SensorName cannot be empty")
  public final String sensorName;

  @NotBlank(message = "Start Date cannot be empty")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  public final String startDate;

  @NotBlank(message = "End Date cannot be empty")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  public final String endDate;
}
