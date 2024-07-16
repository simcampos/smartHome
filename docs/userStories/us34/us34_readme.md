## US34

## 0. Description

As a Room Owner [or Power User, or Administrator], 
I want to get the maximum instantaneous temperature difference between a device in the room and the outside, 
in a given period.

## 1. Analysis
A device with temperature functionality will be selected, as well as a time frame.
The system will then calculate the maximum temperature difference between the device and the outside temperature
in the selected time frame.

### 1.1. Created/Affected Use Cases

* Creates: Use Case 12 - To get Devices By Room And Temperature Functionality [UC12](../../useCases/uc12_toGetDevicesByRoomAndTemperatureFunctionality/uc12_readme.md)
* Creates: Use Case 13 - To get the maximum instantaneous temperature difference between a device and the outside, in a given period. [UC13](../../useCases/uc13_toGetMaxInstTempDifBetweenDeviceAndOutside/uc13_readme.md)

### 1.2. Acceptance Criteria

* AC1: The system must allow the Room Owner, the Power User, and the Administrator only to select a device with temperature functionality.
* AC2: The system must allow the Room Owner, the Power User, and the Administrator only to select a time frame.
* AC3: The system must calculate the maximum temperature difference between the device and the outside temperature in the selected time frame.
* AC4: The system must display the maximum temperature difference between the device and the outside temperature in the selected time frame.

### 1.3 Customer Specifications and Clarifications
* 11/04/2024 - "Relativamente à US34, no conjunto de dados de teste não se pode assumir que os dados de diferentes sensores foram obtidos no mesmo instante.
A probabilidade de isso acontecer na realidade é praticamente nula."