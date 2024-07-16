## US40

## 0. Description

As a Power User, I want to configure a device in a room using the Web App.

## 1. Analysis

Allows a Power User to configure a device in an existing room in the house using the Web App. 

This use case involves fetching available types of sensor, sensor types, sensor models from the backend and updating the given device with the defined sensor. The same process occurs for the process of adding an actuator. 

In addition, the Web App allows the Power User to deactivate a device in a room.

### 1.1 Acceptance Criteria

* AC01: The system must allow a Power User to see a list of all rooms.
* AC02: The system must allow a Power User to select a room.
* AC03: The system must allow a Power User to select a device in the chosen room.
* AC04: The system must allow a Power User to select a type of sensor/actuator, sensor/actuator type, sensor/actuator model.
* AC05: The system must allow a Power User to define a sensor/actuator name.
* AC06: The system must allow a Power User to define a start date and end date for the Electric Consumption Wh.
* AC07: The system must allow a Power User to define a longitude and latitude for the sunset and sunrise time sensors.
* AC08: The system must allow a Power user to define a minimum and maximum decimal limit for the Set Decimal Actuator.
* AC09: The system must allow a Power user to define a minimum and maximum integer limit for the Set Integer Actuator.
* AC10: The system must allow a Power User to deactivate a device in a room.

### 1.2 Customer Specifications and Clarifications

* The user interface must be intuitive and responsive.
* Error handling should be implemented to inform the user if the device configuration fails.
* The user should be able to see the updated device after configuring it.