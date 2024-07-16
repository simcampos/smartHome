/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils.visitor_pattern;

import org.springframework.stereotype.Component;
import smarthome.domain.sensor.average_power_consumption_sensor.AveragePowerConsumptionSensor;
import smarthome.domain.sensor.dew_point_sensor.DewPointSensor;
import smarthome.domain.sensor.electric_consumption_wh_sensor.ElectricConsumptionWhSensor;
import smarthome.domain.sensor.humidity_sensor.HumiditySensor;
import smarthome.domain.sensor.instant_power_consumption_sensor.InstantPowerConsumptionSensor;
import smarthome.domain.sensor.percentage_position_sensor.PercentagePositionSensor;
import smarthome.domain.sensor.solar_irradiance_sensor.SolarIrradianceSensor;
import smarthome.domain.sensor.sunrise_time_sensor.SunriseTimeSensor;
import smarthome.domain.sensor.sunset_time_sensor.SunsetTimeSensor;
import smarthome.domain.sensor.switch_sensor.SwitchSensor;
import smarthome.domain.sensor.temperature_sensor.TemperatureSensor;
import smarthome.domain.sensor.wind_sensor.WindSensor;
import smarthome.persistence.data_model.SensorDataModel;


@Component
public class SensorVisitorForDataModelImpl implements ISensorVisitorForDataModel {

  private final SensorDataModel sensorDataModel;

  public SensorVisitorForDataModelImpl(SensorDataModel sensorDataModel) {
    this.sensorDataModel = sensorDataModel;
  }


  @Override
  public SensorDataModel getSensorDataModel() {
    return sensorDataModel;
  }

  @Override
  public String visitTemperatureSensor(TemperatureSensor temperatureSensor) {
    sensorDataModel.setGenericSensor(temperatureSensor);
    return sensorDataModel.toString();
  }

  @Override
  public String visitHumiditySensor(HumiditySensor humiditySensor) {
    sensorDataModel.setGenericSensor(humiditySensor);

    return sensorDataModel.toString();
  }

  @Override
  public String visitSunsetTimeSensor(SunsetTimeSensor sunsetTimeSensor) {
    sensorDataModel.setGenericSensor(sunsetTimeSensor);
    String latitude = String.valueOf(sunsetTimeSensor.getGPS().getLatitude());
    String longitude = String.valueOf(sunsetTimeSensor.getGPS().getLongitude());
    sensorDataModel.setLatitude(latitude);
    sensorDataModel.setLongitude(longitude);

    return sensorDataModel.toString();
  }

  @Override
  public String visitWindSensor(WindSensor windSensor) {
    sensorDataModel.setGenericSensor(windSensor);

    return sensorDataModel.toString();
  }

  @Override
  public String visitSwitchSensor(SwitchSensor switchSensor) {
    sensorDataModel.setGenericSensor(switchSensor);

    return sensorDataModel.toString();
  }

  @Override
  public String visitSunriseTimeSensor(SunriseTimeSensor sunriseTimeSensor) {
    sensorDataModel.setGenericSensor(sunriseTimeSensor);
    String latitude = String.valueOf(sunriseTimeSensor.getGPS().getLatitude());
    String longitude = String.valueOf(sunriseTimeSensor.getGPS().getLongitude());
    sensorDataModel.setLatitude(latitude);
    sensorDataModel.setLongitude(longitude);

    return sensorDataModel.toString();
  }

  @Override
  public String visitSolarIrradianceSensor(SolarIrradianceSensor solarIrradianceSensor) {
    sensorDataModel.setGenericSensor(solarIrradianceSensor);

    return sensorDataModel.toString();
  }

  @Override
  public String visitPercentageSensor(PercentagePositionSensor percentagePositionSensor) {
    sensorDataModel.setGenericSensor(percentagePositionSensor);

    return sensorDataModel.toString();
  }

  @Override
  public String visitInstantPowerSensor(
      InstantPowerConsumptionSensor instantPowerConsumptionSensor) {
    sensorDataModel.setGenericSensor(instantPowerConsumptionSensor);

    return sensorDataModel.toString();
  }

  @Override
  public String visitDewPointSensor(DewPointSensor dewPointSensor) {
    sensorDataModel.setGenericSensor(dewPointSensor);

    return sensorDataModel.toString();
  }

  @Override
  public String visitAveragePowerConsumptionSensor(
      AveragePowerConsumptionSensor averagePowerConsumptionSensor) {
    sensorDataModel.setGenericSensor(averagePowerConsumptionSensor);

    return sensorDataModel.toString();
  }

  @Override
  public String visitElectricConsumptionWhSensor(
      ElectricConsumptionWhSensor electricConsumptionWhSensor) {
    sensorDataModel.setGenericSensor(electricConsumptionWhSensor);
    String startDate = String.valueOf(electricConsumptionWhSensor.getDatePeriod().getStartDate());
    String endDate = String.valueOf(electricConsumptionWhSensor.getDatePeriod().getEndDate());
    sensorDataModel.setStartDate(startDate);
    sensorDataModel.setEndDate(endDate);

    return sensorDataModel.toString();
  }
}
