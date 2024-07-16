## US07

## 0. Description

As a Power User [or Administrator], I want to add a sensor to an existing device in
a room. The sensor must be of a model of an existing type of sensor.

## 1. Analysis
All sensors will have an ID, a device ID, a model, a name, a sensor type ID and a value.
Some sensors might have additional information.

### 1.1. Created/Affected Use Cases

* Creates: Use Case 7 - To add sensor to a device in a room

### 1.2. Acceptance Criteria

* AC1: The system must allow the Power User (or Administrator) to add a sensor to an existing device in a room.
* AC2: The system must not allow any other user to add a sensor to an existing device in a room.
* AC3: The system must allow for a sensor with a valid ID, device ID, model, name, sensor type ID and value to be added to the device.
* AC4: The system must not allow for a sensor with an invalid ID, device ID, model, name, sensor type ID or value to be added to the device.

### 1.3 Customer Specifications and Clarifications

N/A