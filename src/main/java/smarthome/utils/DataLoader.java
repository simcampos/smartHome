/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils;

import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import smarthome.domain.log.ILogFactory;
import smarthome.domain.log.Log;
import smarthome.domain.repository.ILogRepository;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitID;



public class DataLoader implements CommandLineRunner {


  private final ILogRepository logRepository;
  private final ILogFactory logFactory;

  public DataLoader(ILogRepository logRepository, ILogFactory logFactory) {
    this.logRepository = logRepository;
    this.logFactory = logFactory;
  }

  @Override
  public void run(String... args) throws Exception {
    // Create log entries
    DeviceID deviceID = new DeviceID("123");
    SensorID sensorID = new SensorID("123");
    LocalDateTime localDateTime = LocalDateTime.of(2023, 5, 10, 14, 0);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("Temperature");
    UnitID unit = new UnitID("C");
    Log log1 = logFactory.createLog(deviceID, sensorID, localDateTime, value, description, unit);
    logRepository.save(log1);
  }
}
