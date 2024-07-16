## US12

## 0. Description

As a Power User [or Administrator], I want to add an actuator to an existing device in
a room. The actuator must be of a model of an existing type of actuator.

## 1. Analysis
All actuators will have an ID, a device ID, a model, a name, an actuator type ID and a value.
Some actuators might have additional information.

### 1.1. Created/Affected Use Cases

* Creates: [Use Case 10](../../useCases/uc10_toAddActuator/uc10_readme.md) - To add an actuator to a device in a room

### 1.2. Acceptance Criteria

* AC1: The system must allow the Power User (or Administrator) to add an actuator to an existing device in a room.
* AC2: The system must not allow any other user to add an actuator to an existing device in a room.
* AC3: The system must allow for an actuator with a valid ID, device ID, model, name, actuator type ID and value to be added to the device.
* AC4: The system must not allow for an actuator with an invalid ID, device ID, model, name, actuator type ID or value to be added to the device.

### 1.3 Customer Specifications and Clarifications

N/A