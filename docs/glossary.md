## Glossary

In this file we will describe important terms and its definition.

|     Domain      |           Sensors           |   Actuators    |
|:---------------:|:---------------------------:|:--------------:|
|   [Actuator]    | [Average Power Consumption] | [Blind Roller] |
| [Actuator Type] |         [Dew Point]         | [Set Decimal]  |
|    [Address]    |   [Electric Consumption]    | [Set Integer]  |
|    [Device]     |      [Humidity Sensor]      |    [Switch]    |
|      [GPS]      |     [Solar Irradiance]      |                |
|     [House]     |          [Sunrise]          |                |
|   [Location]    |          [Sunset]           |                |
|     [Room]      |          [Switch]           |                |
|    [Sensor]     |        [Temperature]        |                |
|  [Sensor Type]  | [Wind Speed and Direction]  |                |
|     [Unit]      | [Instant Power Consumption] |                |
|     [Value]     |    [Percentage Position]    |                |
|  [Dimensions]   |          [Switch]           |                |
|      [Log]      |                             |                |

## Domain Terms

### Actuator

[actuator]: #actuator
_An actuator is a physical component within a smart home system that directly causes an action or change in the
environment. Actuators receive instructions from the smart home software and translate those commands into physical
actions._

### Actuator Type

[actuator type]: #actuator-type
_Refers to the specific kind of [actuator] within the system. It characterizes the functionality and behavior of an
actuator, dictating what actions it can perform or what changes it can make in the environment. Different actuator types
can include switches, motors, pumps, and more, each with their unique operations._

### Address

[address]: #address
_It is the address of the house. It has a street, a postal code, a city and a country._

### Device

[device]: #device
_The device is a physical unit that will be installed in the [house]. It has a name, a status, a list of [sensor]
and [actuator], and belongs to a [room]._

### Dimensions

[dimensions]: #dimensions
_The dimensions of the [room]. It has a width, a length and a height._

### GPS

[gps]: #gps
_The GPS coordinates of the [house]. It has a latitude and a longitude._

### House

[house]: #house
_The house is the place where the system will be installed. It will have a name, a location, and a list of rooms._

### Location

[location]: #location
_The location of the [house]. It is composed of an [address] and [GPS] coordinates._

### Room

[room]: #room
_The room is a division of the [house]. It has a name, a floor, [dimensions] and a list of devices._

### Sensor

[sensor]: #sensor
_A device or functionality that detects or measures physical properties of the environment and converts them into
electronic signals. It will have a type that will identify the function and corresponding [unit]._

### Sensor Type

[sensor type]: #sensor-type
_Refers to the specific kind of [sensor] within the system. It has a description that will identify the specific
function._

### Unit

[unit]: #unit
_Is the unit of unit of the physical quantity that each [sensor] will measure. It has a description._

### Value

[value]: #value
_The value is the unit itself of the physical quantity that each [sensor] will measure. It has a quantity and
a [unit]._

## Supported Sensors

### Average Power Consumption

[average power consumption]: #average-power-consumption
_The average power consumption is the average of the power consumption in a period of time. It will have a value and a
unit._

### Dew Point

[dew point]: #dew-point
_The dew point is the temperature at which the water vapor in a sample of air at constant barometric pressure condenses
into liquid water at the same rate at which it evaporates. It will have a value and a unit._

### Electric Consumption

[electric consumption]: #electric-consumption
_The electric consumption is the electric consumption in a period of time, which will be read in hourly intervals. It
will have an integer value._

### Humidity Sensor

[humidity sensor]: #humidity-sensor
_The humidity sensor is a sensor that measures the humidity in the air. It will have a value and a unit._

### Instant Power Consumption

[instant power consumption]: #instant-power-consumption
_The power consumption at a given moment. It will have a value and a unit._

### Percentage Position

[percentage position]: #percentage-position
_This sensor provides the current position of a device in a percentage format. It will have a value and a unit._

### Solar Irradiance

[solar irradiance]: #solar-irradiance
_The solar irradiance is the power per unit of surface received from the sun in the form of electromagnetic radiation,
in the wavelength range of the measuring instrument. It will have a value and a unit._

### Sunrise

[sunrise]: #sunrise
_The sunrise is the time when the upper limb of the sun appears on the horizon in the morning, in a given location. It
will have a time value and a precision to the minute._

### Sunset

[sunset]: #sunset
_The sunset is the time when the upper part of the sun disappears below the horizon in a given location. It will have a
time value and a precision to the minute._

### Switch

[switch]: #switch
_A sensor that will read the position of a device behaving like a switch, with an "on" and "off" value. It will have a
value and a unit._

### Temperature

[temperature]: #temperature
_The temperature is a unit of the warmth or coldness of an object or substance with reference to some standard
value. It will have a value and a unit._

### Wind Speed and Direction

[wind speed and direction]: #wind-speed-and-direction
_The speed and direction of the wind. It will have a value and a unit._

## Supported Actuators

### Blind Roller

[blind roller]: #blind-roller
_The blind roller is a device that will control the blind roller. It will have a value and a unit._

### Set Decimal

[set decimal]: #set-decimal
_The set decimal actuator is a device that will set a decimal value within some limits. It will have a value, limits and
a unit._

### Set Integer

[set integer]: #set-integer
_The set integer actuator is a device that will set an integer value within some limits. It will have a value, limits
and a unit._

### Switch

[switch]: #switch
_The switch is a device that will toggle between an 'on' and 'off' state. It will have a value and a unit._

### Log

[log]: #log
_The log stores the units (readings) of the sensors within a given device. Each log entry is identified by their
logID, and it also identifies the device and sensor by their respective IDs_