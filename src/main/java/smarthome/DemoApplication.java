/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import smarthome.ddd.IValueObject;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.device.Device;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.house.House;
import smarthome.domain.room.Room;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DecimalLimits;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.IntegerLimits;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomName;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorModelName;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TimeDelta;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.service.ActuatorTypeServiceImpl;
import smarthome.service.IActuatorModelService;
import smarthome.service.IActuatorService;
import smarthome.service.IActuatorTypeService;
import smarthome.service.IDeviceService;
import smarthome.service.IDeviceTypeService;
import smarthome.service.IHouseService;
import smarthome.service.ILogService;
import smarthome.service.IRoomService;
import smarthome.service.ISensorModelService;
import smarthome.service.ISensorService;
import smarthome.service.ISensorTypeService;
import smarthome.service.IUnitService;
import smarthome.utils.ValueSimulator;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
/*@Bean
@Profile({"frontendtest", "docker", "demo"})
  public CommandLineRunner demo (IHouseService houseService, IRoomService roomService, IDeviceService deviceService, IDeviceTypeService deviceTypeService,
        IUnitService unitService, ISensorTypeService sensorTypeService, ISensorModelService sensorModelService, IActuatorModelService actuatorModelService, IActuatorTypeService actuatorTypeService,
        ISensorService sensorService, IActuatorService actuatorService, ILogService logService) {
      return (args) -> {
        //House and rooms configuration
        if (houseService.getHouse().isEmpty()) {
          // Let's create a house
          Address address = new Address("Antonio Bernardino de Almeida", "431", "4249-015",
              "PT", new PostalCodeFactory());
          GPS gps = new GPS(41.1839, -8.6096);
          House house = houseService.addHouse(address, gps);

          // Let's add a few rooms to the first floor
          RoomFloor firstFloorRooms = new RoomFloor(1);
          Dimension bedroomDimensions = new Dimension(4, 3, 2);
          RoomName bedroomName = new RoomName("Bedroom");
          Room bedroom = roomService.addRoom(bedroomName, bedroomDimensions, firstFloorRooms);
          Dimension bathroomDimensions = new Dimension(3, 3, 2);
          RoomName bathRoomName = new RoomName("Bathroom");
          Room bathroom = roomService.addRoom(bathRoomName, bathroomDimensions, firstFloorRooms);

          // Let's add a few rooms to the ground floor
          RoomFloor groundFloorRooms = new RoomFloor(0);
          Dimension kitchenDimensions = new Dimension(4, 4, 2);
          RoomName kitchenName = new RoomName("Kitchen");
          Room kitchen = roomService.addRoom(kitchenName, kitchenDimensions, groundFloorRooms);
          RoomName gardenName = new RoomName("Garden");
          Dimension gardenDimensions = new Dimension(10, 10, 2);
          Room garden = roomService.addRoom(gardenName, gardenDimensions, groundFloorRooms);

          ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          //Types and models configuration

          // Let's add a few device types
          TypeDescription thermostatDesr = new TypeDescription("Thermostat");
          DeviceType thermostat = deviceTypeService.addDeviceType(thermostatDesr);
          TypeDescription powerSourceDesc = new TypeDescription("PowerSource");
          DeviceType powerSource = deviceTypeService.addDeviceType(powerSourceDesc);
          TypeDescription powerMeterDesc = new TypeDescription("PowerMeter");
          DeviceType powerMeter = deviceTypeService.addDeviceType(powerMeterDesc);
          TypeDescription blindRollerDesc = new TypeDescription("BlindRoller");
          DeviceType blindroller = deviceTypeService.addDeviceType(blindRollerDesc);
          TypeDescription sunsetAndSunriseDesc = new TypeDescription("SunsetAndSunrise");
          DeviceType sunsetAndSunrise = deviceTypeService.addDeviceType(sunsetAndSunriseDesc);
          TypeDescription demoDeviceForAllSensorAndActuators = new TypeDescription(
              "NasaSpaceStationDevice");
          DeviceType nasaSpaceStationTypeOfDevice = deviceTypeService.addDeviceType(
              demoDeviceForAllSensorAndActuators);


          // Let's add a few units
          UnitDescription celsius = new UnitDescription("Celsius");
          UnitSymbol symbol = new UnitSymbol("ºC");
          Unit celsiusUnit = unitService.addunitType(celsius, symbol);
          UnitDescription percent = new UnitDescription("Percent");
          UnitSymbol percentSymbol = new UnitSymbol("%");
          Unit percentUnit = unitService.addunitType(percent, percentSymbol);
          UnitDescription watt = new UnitDescription("Watt");
          UnitSymbol wattSymbol = new UnitSymbol("W");
          Unit wattUnit = unitService.addunitType(watt, wattSymbol);
          UnitDescription integer = new UnitDescription("Integer");
          UnitSymbol integerSymbol = new UnitSymbol("-");
          Unit integerUnit = unitService.addunitType(integer, integerSymbol);
          UnitDescription kmPerHour = new UnitDescription("KilometerPerHour");
          UnitSymbol kmPerHourSymbol = new UnitSymbol("km/h");
          Unit kmPerHourUnit = unitService.addunitType(kmPerHour, kmPerHourSymbol);
          UnitDescription wattBySquareMeter = new UnitDescription("WattBySquareMeter");
          UnitSymbol wattBySquareMeterSymbol = new UnitSymbol("Wm²");
          Unit wattBySquareMeterUnit = unitService.addunitType(wattBySquareMeter,
              wattBySquareMeterSymbol);
          UnitDescription wattHour = new UnitDescription("WattHour");
          UnitSymbol wattHourSymbol = new UnitSymbol("Wh");
          Unit wattHourUnit = unitService.addunitType(wattHour, wattHourSymbol);
          UnitDescription time = new UnitDescription("Time");
          UnitSymbol timeSymbol = new UnitSymbol("-");
          Unit timeUnit = unitService.addunitType(time, timeSymbol);
          UnitDescription decimal = new UnitDescription("Decimal");
          UnitSymbol decimalSymbol = new UnitSymbol("-");
          Unit decimalUnit = unitService.addunitType(decimal, decimalSymbol);
          UnitDescription status = new UnitDescription("Status");
          UnitSymbol statusSymbol = new UnitSymbol("-");
          Unit statusUnit = unitService.addunitType(status, statusSymbol);
          UnitDescription kilowattHours = new UnitDescription("KilowattHour");
          UnitSymbol kilowattHoursSymbol = new UnitSymbol("kWh");
          Unit kilowattHoursUnit = unitService.addunitType(kilowattHours, kilowattHoursSymbol);

          // Let's add a few sensor types
          TypeDescription temperatureDesc = new TypeDescription("Temperature");
          SensorType temperature = sensorTypeService.createSensorType(temperatureDesc,
              celsiusUnit.getID());
          TypeDescription humidityDesc = new TypeDescription("Humidity");
          SensorType humidity = sensorTypeService.createSensorType(humidityDesc,
              percentUnit.getID());
          TypeDescription switchDesc = new TypeDescription("Switch");
          SensorType switchType = sensorTypeService.createSensorType(switchDesc,
              statusUnit.getID());
          TypeDescription avgPowerDesc = new TypeDescription("AveragePowerConsumption");
          SensorType avgPower = sensorTypeService.createSensorType(avgPowerDesc,
              wattHourUnit.getID());
          TypeDescription dewPointDesc = new TypeDescription("DewPoint");
          SensorType dewPoint = sensorTypeService.createSensorType(dewPointDesc,
              celsiusUnit.getID());
          TypeDescription windDesc = new TypeDescription("Wind");
          SensorType wind = sensorTypeService.createSensorType(windDesc, kmPerHourUnit.getID());
          TypeDescription percentagePosDesc = new TypeDescription("PercentagePosition");
          SensorType percentagePos = sensorTypeService.createSensorType(percentagePosDesc,
              percentUnit.getID());
          TypeDescription sunriseTimeDesc = new TypeDescription("SunriseTime");
          SensorType sunriseTime = sensorTypeService.createSensorType(sunriseTimeDesc,
              timeUnit.getID());
          TypeDescription instantPowerDesc = new TypeDescription("InstantPowerConsumption");
          SensorType instantPower = sensorTypeService.createSensorType(instantPowerDesc,
              wattUnit.getID());
          TypeDescription solarIrradianceDesc = new TypeDescription("SolarIrradiance");
          SensorType solarIrradiance = sensorTypeService.createSensorType(solarIrradianceDesc,
              wattBySquareMeterUnit.getID());
          TypeDescription electricConsumptionDesc = new TypeDescription("ElectricConsumptionWh");
          SensorType electricConsumption = sensorTypeService.createSensorType(
              electricConsumptionDesc, wattHourUnit.getID());
          TypeDescription sunsetTimeDesc = new TypeDescription("SunsetTime");
          SensorType sunsetTime = sensorTypeService.createSensorType(sunsetTimeDesc,
              timeUnit.getID());

          // Let's add the supported sensor models
          SensorModelName temperatureSensorName = new SensorModelName("TemperatureSensor");
          ModelPath temperatureSensorPath = new ModelPath(
              "smarthome.domain.sensor.temperature_sensor.TemperatureSensor");
          SensorModel temperatureSensor = sensorModelService.createSensorModel(
              temperatureSensorName, temperatureSensorPath, temperature.getID());
          SensorModelName humiditySensorName = new SensorModelName("HumiditySensor");
          ModelPath humiditySensorPath = new ModelPath(
              "smarthome.domain.sensor.humidity_sensor.HumiditySensor");
          SensorModel humiditySensor = sensorModelService.createSensorModel(humiditySensorName,
              humiditySensorPath, humidity.getID());
          SensorModelName switchSensorName = new SensorModelName("SwitchSensor");
          ModelPath switchSensorPath = new ModelPath(
              "smarthome.domain.sensor.switch_sensor.SwitchSensor");
          SensorModel switchSensor = sensorModelService.createSensorModel(switchSensorName,
              switchSensorPath, switchType.getID());
          SensorModelName avgPowerSensorName = new SensorModelName("AveragePowerConsumptionSensor");
          ModelPath avgPowerSensorPath = new ModelPath(
              "smarthome.domain.sensor.average_power_consumption_sensor.AveragePowerConsumptionSensor");
          SensorModel avgPowerSensor = sensorModelService.createSensorModel(avgPowerSensorName,
              avgPowerSensorPath, avgPower.getID());
          SensorModelName dewPointSensorName = new SensorModelName("DewPointSensor");
          ModelPath dewPointSensorPath = new ModelPath(
              "smarthome.domain.sensor.dew_point_sensor.DewPointSensor");
          SensorModel dewPointSensor = sensorModelService.createSensorModel(dewPointSensorName,
              dewPointSensorPath, dewPoint.getID());
          SensorModelName windSensorName = new SensorModelName("WindSensor");
          ModelPath windSensorPath = new ModelPath(
              "smarthome.domain.sensor.wind_sensor.WindSensor");
          SensorModel windSensor = sensorModelService.createSensorModel(windSensorName,
              windSensorPath, wind.getID());
          SensorModelName percentagePosSensorName = new SensorModelName("PercentagePositionSensor");
          ModelPath percentagePosSensorPath = new ModelPath(
              "smarthome.domain.sensor.percentage_position_sensor.PercentagePositionSensor");
          SensorModel percentagePosSensor = sensorModelService.createSensorModel(
              percentagePosSensorName, percentagePosSensorPath, percentagePos.getID());
          SensorModelName sunriseTimeSensorName = new SensorModelName("SunriseTimeSensor");
          ModelPath sunriseTimeSensorPath = new ModelPath(
              "smarthome.domain.sensor.sunrise_time_sensor.SunriseTimeSensor");
          SensorModel sunriseTimeSensor = sensorModelService.createSensorModel(
              sunriseTimeSensorName, sunriseTimeSensorPath, sunriseTime.getID());
          SensorModelName instantPowerSensorName = new SensorModelName(
              "InstantPowerConsumptionSensor");
          ModelPath instantPowerSensorPath = new ModelPath(
              "smarthome.domain.sensor.instant_power_consumption_sensor.InstantPowerConsumptionSensor");
          SensorModel instantPowerSensor = sensorModelService.createSensorModel(
              instantPowerSensorName, instantPowerSensorPath, instantPower.getID());
          SensorModelName solarIrradianceSensorName = new SensorModelName("SolarIrradianceSensor");
          ModelPath solarIrradianceSensorPath = new ModelPath(
              "smarthome.domain.sensor.solar_irradiance_sensor.SolarIrradianceSensor");
          SensorModel solarIrradianceSensor = sensorModelService.createSensorModel(
              solarIrradianceSensorName, solarIrradianceSensorPath, solarIrradiance.getID());
          SensorModelName electricConsumptionSensorName = new SensorModelName(
              "ElectricConsumptionWhSensor");
          ModelPath electricConsumptionSensorPath = new ModelPath(
              "smarthome.domain.sensor.electric_consumption_wh_sensor.ElectricConsumptionWhSensor");
          SensorModel electricConsumptionSensor = sensorModelService.createSensorModel(
              electricConsumptionSensorName, electricConsumptionSensorPath,
              electricConsumption.getID());
          SensorModelName sunsetTimeSensorName = new SensorModelName("SunsetTimeSensor");
          ModelPath sunsetTimeSensorPath = new ModelPath(
              "smarthome.domain.sensor.sunset_time_sensor.SunsetTimeSensor");
          SensorModel sunsetTimeSensor = sensorModelService.createSensorModel(sunsetTimeSensorName,
              sunsetTimeSensorPath, sunsetTime.getID());

          // Lets add a few actuator types
          TypeDescription blindRollerActuatorDesc = new TypeDescription("BlindRoller");
          ActuatorType blindRollerActuator = actuatorTypeService.createActuatorType(
              blindRollerActuatorDesc, percentUnit.getID());
          actuatorTypeService.addActuatorType(blindRollerActuator);
          TypeDescription setIntegerDesc = new TypeDescription("SetInteger");
          ActuatorType setInteger = actuatorTypeService.createActuatorType(setIntegerDesc,
              integerUnit.getID());
          actuatorTypeService.addActuatorType(setInteger);
          TypeDescription setDecimalDesc = new TypeDescription("SetDecimal");
          ActuatorType setDecimal = actuatorTypeService.createActuatorType(setDecimalDesc,
              decimalUnit.getID());
          actuatorTypeService.addActuatorType(setDecimal);
          TypeDescription switchActuatorDesc = new TypeDescription("Switch");
          ActuatorType switchActuator = actuatorTypeService.createActuatorType(switchActuatorDesc,
              statusUnit.getID());
          actuatorTypeService.addActuatorType(switchActuator);

          // Let's add the supported actuator models
          ActuatorModelName blindRollerActuatorName = new ActuatorModelName("BlindRollerActuator");
          ModelPath blindRollerActuatorPath = new ModelPath(
              "smarthome.domain.actuator.blind_roller_actuator.BlindRollerActuator");
          ActuatorModel blindRollerActuatorModel = actuatorModelService.addActuatorModel(
              blindRollerActuatorPath, blindRollerActuatorName, blindRollerActuator.getID());
          ActuatorModelName setIntegerActuatorName = new ActuatorModelName("SetIntegerActuator");
          ModelPath setIntegerActuatorPath = new ModelPath(
              "smarthome.domain.actuator.set_integer_actuator.SetIntegerActuator");
          ActuatorModel setIntegerActuatorModel = actuatorModelService.addActuatorModel(
              setIntegerActuatorPath, setIntegerActuatorName, setInteger.getID());
          ActuatorModelName switchActuatorName = new ActuatorModelName("SwitchActuator");
          ModelPath switchActuatorPath = new ModelPath(
              "smarthome.domain.actuator.switch_actuator.SwitchActuator");
          ActuatorModel switchActuatorModel = actuatorModelService.addActuatorModel(
              switchActuatorPath, switchActuatorName, switchActuator.getID());
          ActuatorModelName setDecimalActuatorName = new ActuatorModelName("SetDecimalActuator");
          ModelPath setDecimalActuatorPath = new ModelPath(
              "smarthome.domain.actuator.set_decimal_actuator.SetDecimalActuator");
          ActuatorModel setDecimalActuatorModel = actuatorModelService.addActuatorModel(
              setDecimalActuatorPath, setDecimalActuatorName, setDecimal.getID());
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

          // Let's add a few devices
          Device bedroomThermostat = deviceService.addDevice(bedroom.getID(),
              new DeviceName("Bedroom Thermostat"), thermostat.getID());
          Device kitchenThermostat = deviceService.addDevice(kitchen.getID(),
              new DeviceName("Kitchen Thermostat"), thermostat.getID());
          Device gardenThermostat = deviceService.addDevice(garden.getID(),
              new DeviceName("Garden Thermostat"), thermostat.getID());
          Device gardenPowerSource = deviceService.addDevice(garden.getID(),
              new DeviceName("Garden Power Source"), powerSource.getID());
          Device gardenPowerMeter = deviceService.addDevice(garden.getID(),
              new DeviceName("Garden Power Meter"), powerMeter.getID());
          Device bedroomBlindRoller = deviceService.addDevice(bedroom.getID(),
              new DeviceName("BlindRoller"), blindroller.getID());
          Device sunsetAndSunriseDevice = deviceService.addDevice(garden.getID(),
              new DeviceName("Sunset And Sunrise"), sunsetAndSunrise.getID());
          Device nasaSpaceStation = deviceService.addDevice(garden.getID(),
              new DeviceName("Nasa Space Station Device"), nasaSpaceStationTypeOfDevice.getID());

          // Let's add a few sensors and actuatores to the devices
          SensorName bedroomThermostatTemperatureSensorName = new SensorName("Temperature Sensor");
          ISensor bedroomThermostatTemperatureSensor = sensorService.addSensor(bedroomThermostat.getID(), temperatureSensorPath, temperature.getID(), bedroomThermostatTemperatureSensorName); // This is a temperature sensor
          SensorName bedroomThermostatHumiditySensorName = new SensorName("Humidity Sensor");
          ISensor bedroomThermostatHumiditySensor = sensorService.addSensor(bedroomThermostat.getID(), humiditySensorPath, humidity.getID(), bedroomThermostatHumiditySensorName); // This is a humidity sensor
          SensorName kitchenThermostatTemperatureSensorName = new SensorName("Temperature Sensor");
          ISensor kitchenThermostatTemperatureSensor = sensorService.addSensor(kitchenThermostat.getID(), temperatureSensorPath, temperature.getID(), kitchenThermostatTemperatureSensorName); // This is a temperature sensor
          SensorName kitchenThermostatHumiditySensorName = new SensorName("Humidity Sensor");
          ISensor kitchenThermostatHumiditySensor = sensorService.addSensor(kitchenThermostat.getID(), humiditySensorPath, humidity.getID(), kitchenThermostatHumiditySensorName); // This is a humidity sensor
          SensorName gardenThermostatTemperatureSensorName = new SensorName("Temperature Sensor");
          ISensor gardenThermostatTemperatureSensor = sensorService.addSensor(gardenThermostat.getID(), temperatureSensorPath, temperature.getID(), gardenThermostatTemperatureSensorName); // This is a temperature sensor
          SensorName gardenThermostatHumiditySensorName = new SensorName("Humidity Sensor");
          ISensor gardenThermostatHumiditySensor = sensorService.addSensor(gardenThermostat.getID(), humiditySensorPath, humidity.getID(), gardenThermostatHumiditySensorName); // This is a humidity sensor

          SensorName gardenPowerMeterInstantPowerSensorName = new SensorName("Instant Power Sensor");
          ISensor gardenPowerMeterInstantPowerSensor = sensorService.addSensor(gardenPowerMeter.getID(), instantPowerSensorPath, instantPower.getID(), gardenPowerMeterInstantPowerSensorName); // This is an instant power sensor
          SensorName gardenPowerSourceInstantPowerSensorName = new SensorName("Instant Power Sensor");
          ISensor gardenPowerSourceInstantPowerSensor = sensorService.addSensor(gardenPowerSource.getID(), instantPowerSensorPath, instantPower.getID(), gardenPowerSourceInstantPowerSensorName); // This is an instant power sensor

          ISensor deviceSunsetTimeSensor = sensorService.addSensor(sunsetAndSunriseDevice.getID(), sunsetTimeSensorPath, sunsetTime.getID(), new SensorName("Sunset Time Sensor"), gps); // This is a sunset time sensor
          ISensor deviceSunriseTimeSensor = sensorService.addSensor(sunsetAndSunriseDevice.getID(), sunriseTimeSensorPath, sunriseTime.getID(), new SensorName("Sunrise Time Sensor"), gps); // This is a sunrise time sensor

          SensorName bedroomBlindRollerPercentagePositionSensorName = new SensorName("Percentage Position Sensor");
          ISensor bedroomBlindRollerPercentagePositionSensor = sensorService.addSensor(bedroomBlindRoller.getID(), percentagePosSensorPath, percentagePos.getID(), bedroomBlindRollerPercentagePositionSensorName); // This is a percentage position sensor
          ActuatorName bedroomBlindRollerActuatorName = new ActuatorName("Blind Roller Actuator");
          IActuator bedroomBlindRollerActuator = actuatorService.addActuator(bedroomBlindRoller.getID(), blindRollerActuatorPath, blindRollerActuator.getID(), bedroomBlindRollerActuatorName); // This is a blind roller actuator


          //Lets configure the nasa space station, in the demo to show that all sensors and actuatores can be added to the device
          SensorName nasaSpaceStationTemperatureSensorName = new SensorName("Temperature Sensor");
          ISensor nasaSpaceStationTemperatureSensor = sensorService.addSensor(nasaSpaceStation.getID(), temperatureSensorPath, temperature.getID(), nasaSpaceStationTemperatureSensorName); // This is a temperature sensor
          SensorName nasaSpaceStationHumiditySensorName = new SensorName("Humidity Sensor");
          ISensor nasaSpaceStationHumiditySensor = sensorService.addSensor(nasaSpaceStation.getID(), humiditySensorPath, humidity.getID(), nasaSpaceStationHumiditySensorName); // This is a humidity sensor
          SensorName nasaSpaceStationSwitchSensorName = new SensorName("Switch Sensor");
          ISensor nasaSpaceStationSwitchSensor = sensorService.addSensor(nasaSpaceStation.getID(), switchSensorPath, switchType.getID(), nasaSpaceStationSwitchSensorName); // This is a switch sensor
          SensorName nasaSpaceStationAvgPowerSensorName = new SensorName("Average Power Sensor");
          ISensor nasaSpaceStationAvgPowerSensor = sensorService.addSensor(nasaSpaceStation.getID(), avgPowerSensorPath, avgPower.getID(), nasaSpaceStationAvgPowerSensorName); // This is an average power sensor
          SensorName nasaSpaceStationDewPointSensorName = new SensorName("Dew Point Sensor");
          ISensor nasaSpaceStationDewPointSensor = sensorService.addSensor(nasaSpaceStation.getID(), dewPointSensorPath, dewPoint.getID(), nasaSpaceStationDewPointSensorName); // This is a dew point sensor
          SensorName nasaSpaceStationWindSensorName = new SensorName("Wind Sensor");
          ISensor nasaSpaceStationWindSensor = sensorService.addSensor(nasaSpaceStation.getID(), windSensorPath, wind.getID(), nasaSpaceStationWindSensorName); // This is a wind sensor
          SensorName nasaSpaceStationPercentagePosSensorName = new SensorName("Percentage Position Sensor");
          ISensor nasaSpaceStationPercentagePosSensor = sensorService.addSensor(nasaSpaceStation.getID(), percentagePosSensorPath, percentagePos.getID(), nasaSpaceStationPercentagePosSensorName); // This is a percentage position sensor
          SensorName nasaSpaceStationInstantPowerSensorName = new SensorName("Instant Power Sensor");
          ISensor nasaSpaceStationInstantPowerSensor = sensorService.addSensor(nasaSpaceStation.getID(), instantPowerSensorPath, instantPower.getID(), nasaSpaceStationInstantPowerSensorName); // This is an instant power sensor
          SensorName nasaSpaceStationSolarIrradianceSensorName = new SensorName("Solar Irradiance Sensor");
          ISensor nasaSpaceStationSolarIrradianceSensor = sensorService.addSensor(nasaSpaceStation.getID(), solarIrradianceSensorPath, solarIrradiance.getID(), nasaSpaceStationSolarIrradianceSensorName); // This is a solar irradiance sensor

          // Sensores with GPS
          SensorName nasaSpaceStationSunsetTimeSensorName = new SensorName("Sunset Time Sensor");
          ISensor nasaSpaceStationSunsetTimeSensor = sensorService.addSensor(nasaSpaceStation.getID(), sunsetTimeSensorPath, sunsetTime.getID(), nasaSpaceStationSunsetTimeSensorName, gps); // This is a sunset time sensor
          SensorName nasaSpaceStationSunriseTimeSensorName = new SensorName("Sunrise Time Sensor");
          ISensor nasaSpaceStationSunriseTimeSensor = sensorService.addSensor(nasaSpaceStation.getID(), sunriseTimeSensorPath, sunriseTime.getID(), nasaSpaceStationSunriseTimeSensorName, gps); // This is a sunrise time sensor
          // Sensors with timePeriod
          SensorName nasaSpaceStationElectricConsumptionSensorName = new SensorName("Electric Consumption Sensor");
          DatePeriod timePeriod = new DatePeriod(LocalDateTime.now().minusDays(1), LocalDateTime.now());
          ISensor nasaSpaceStationElectricConsumptionSensor = sensorService.addSensor(nasaSpaceStation.getID(), electricConsumptionSensorPath, electricConsumption.getID(), nasaSpaceStationElectricConsumptionSensorName, timePeriod); // This is an electric consumption sensor

          // Lets add a actuatores do the nasa space station
          ActuatorName nasaSpaceStationBlindRollerActuatorName = new ActuatorName("Blind Roller Actuator");
          IActuator nasaSpaceStationBlindRollerActuator = actuatorService.addActuator(nasaSpaceStation.getID(), blindRollerActuatorPath, blindRollerActuator.getID(), nasaSpaceStationBlindRollerActuatorName); // This is a blind roller actuator
          ActuatorName nasaSpaceStationSetIntegerActuatorName = new ActuatorName("Set Integer Actuator");
          IntegerLimits integerLimits = new IntegerLimits(0, 100);
          IActuator nasaSpaceStationSetIntegerActuator = actuatorService.addActuator(nasaSpaceStation.getID(), setIntegerActuatorPath, setInteger.getID(), nasaSpaceStationSetIntegerActuatorName, integerLimits); // This is a set integer actuator
          ActuatorName nasaSpaceStationSwitchActuatorName = new ActuatorName("Switch Actuator");
          IActuator nasaSpaceStationSwitchActuator = actuatorService.addActuator(nasaSpaceStation.getID(), switchActuatorPath, switchActuator.getID(), nasaSpaceStationSwitchActuatorName); // This is a switch actuator
          ActuatorName nasaSpaceStationSetDecimalActuatorName = new ActuatorName("Set Decimal Actuator");
          DecimalLimits decimalLimits = new DecimalLimits(0.5, 99.5);
          IActuator nasaSpaceStationSetDecimalActuator = actuatorService.addActuator(nasaSpaceStation.getID(), setDecimalActuatorPath, setDecimal.getID(), nasaSpaceStationSetDecimalActuatorName, decimalLimits); // This is a set decimal actuator

          /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          //Demo logs
          logGeneratorForDemoPurposes(logService, bedroomThermostat.getID(), bedroomThermostatTemperatureSensor.getID(), temperature.getID(), celsiusUnit.getID(), 5, 20.0, 25.0);
          logGeneratorForDemoPurposes(logService, kitchenThermostat.getID(), kitchenThermostatTemperatureSensor.getID(), temperature.getID(), celsiusUnit.getID(), 5, 20.0, 25.0);
          logGeneratorForDemoPurposes(logService, gardenThermostat.getID(), gardenThermostatTemperatureSensor.getID(), temperature.getID(), celsiusUnit.getID(), 5, 20.0, 25.0);

          logGeneratorForDemoPurposes(logService, kitchenThermostat.getID(), kitchenThermostatHumiditySensor.getID(), humidity.getID(), percentUnit.getID(), 5, 60.4, 65.7);
          logGeneratorForDemoPurposes(logService, bedroomThermostat.getID(), bedroomThermostatHumiditySensor.getID(), humidity.getID(), percentUnit.getID(), 5, 60.5, 65.8);
          logGeneratorForDemoPurposes(logService, gardenThermostat.getID(), gardenThermostatHumiditySensor.getID(), humidity.getID(), percentUnit.getID(), 5, 50.5, 55.2);


          logGeneratorForDemoPurposes(logService, gardenPowerMeter.getID(), gardenPowerMeterInstantPowerSensor.getID(), instantPower.getID(), wattUnit.getID(), 15, 0, 5000);
          logGeneratorForDemoPurposes(logService, gardenPowerSource.getID(), gardenPowerSourceInstantPowerSensor.getID(), instantPower.getID(), wattUnit.getID(), 15, 0, 100);

          //Add log to blind roller device
          logGeneratorForDemoPurposes(logService, bedroomBlindRoller.getID(), bedroomBlindRollerPercentagePositionSensor.getID(), percentagePos.getID(), percentUnit.getID(), 120, 0, 100);

          //Add log do SunsetSunrise device
          IValueObject  sunsetTimeSensorValue= deviceSunsetTimeSensor.getValue();
          IValueObject sunriseTimeSensorValue = deviceSunriseTimeSensor.getValue();
          logGeneratorForDemoPurposes(logService, sunsetAndSunriseDevice.getID(), deviceSunsetTimeSensor.getID(), sunsetTime.getID(), timeUnit.getID(), sunsetTimeSensorValue);
          logGeneratorForDemoPurposes(logService, sunsetAndSunriseDevice.getID(), deviceSunsetTimeSensor.getID(), sunsetTime.getID(), timeUnit.getID(), sunriseTimeSensorValue);

        }
      };
  }


  private void logGeneratorForDemoPurposes(ILogService logService, DeviceID deviceID, SensorID sensorID, SensorTypeID description, UnitID unit, int timeBetweenLogsInMinutes, int min, int max) {
    // This is a demo method to generate logs for the sensors
    // This method is not part of the domain, it is only for demo purposes
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime end = now.minusDays(1);

    for (LocalDateTime date = now; date.isAfter(end); date = date.minusMinutes(timeBetweenLogsInMinutes)) {
      int value = ValueSimulator.generateRandomValue(min, max);
      String reading = String.valueOf(value);
      ReadingValue readingValue = new ReadingValue(reading);
      logService.addLog(deviceID, sensorID, date, readingValue, description, unit);
    }
  }
  private void logGeneratorForDemoPurposes(ILogService logService, DeviceID deviceID, SensorID sensorID, SensorTypeID description, UnitID unit, int timeBetweenLogsInMinutes, double min, double max) {
    // This is a demo method to generate logs for the sensors
    // This method is not part of the domain, it is only for demo purposes
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime end = now.minusDays(1);

    for (LocalDateTime date = now; date.isAfter(end);
       date = date.minusMinutes(timeBetweenLogsInMinutes)) {
      double value = ValueSimulator.generateRandomValue(min, max);
      String reading = String.valueOf(Math.round(value * 100) / 100);
      ReadingValue readingValue = new ReadingValue(reading);
      logService.addLog(deviceID, sensorID, date, readingValue, description, unit);
    }
  }

  private void logGeneratorForDemoPurposes(ILogService logService, DeviceID deviceID, SensorID sensorID, SensorTypeID description, UnitID unit, IValueObject value){
    // This is a demo method to generate logs for the sensors
    // This method is not part of the domain, it is only for demo purposes
      String result = value.toString();
      logService.addLog(deviceID, sensorID, LocalDateTime.now(), new ReadingValue(result), description, unit);
  }*/

}
