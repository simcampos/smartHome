/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils.visitor_pattern;

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

public interface ISensorVisitor {

  String visitTemperatureSensor(TemperatureSensor temperatureSensor);

  String visitHumiditySensor(HumiditySensor humiditySensor);

  String visitSunsetTimeSensor(SunsetTimeSensor sunsetTimeSensor);

  String visitWindSensor(WindSensor windSensor);

  String visitSwitchSensor(SwitchSensor switchSensor);

  String visitSunriseTimeSensor(SunriseTimeSensor sunriseTimeSensor);

  String visitSolarIrradianceSensor(SolarIrradianceSensor solarIrradianceSensor);

  String visitPercentageSensor(PercentagePositionSensor percentagePositionSensor);

  String visitInstantPowerSensor(InstantPowerConsumptionSensor instantPowerConsumptionSensor);

  String visitDewPointSensor(DewPointSensor dewPointSensor);

  String visitAveragePowerConsumptionSensor(
      AveragePowerConsumptionSensor averagePowerConsumptionSensor);

  String visitElectricConsumptionWhSensor(ElectricConsumptionWhSensor electricConsumptionWhSensor);

}
