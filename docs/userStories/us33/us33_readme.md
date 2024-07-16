## US33

## 0. Description

As a Room Owner [or Power User, or Administrator],
I want to get a list of all measurement of a device in a room, in a
given period.

## 1. Analysis

The list of measurements (as well as a single one) will be nominated as a `Log`.
A device will be selected, as well as a time frame.
The system will then return a list of all logs recorded by the selected device during the specified period.

### 1.1. Created/Affected Use Cases

* Creates: [Use Case 11](../../useCases/uc11_toGetLogFromDevice/uc11_readme.md) - To get a log of all logs of a
  device in a room, in a given period.

### 1.2. Acceptance Criteria

* AC1: The system must return all logs recorded by the selected device during the specified period.
* AC2: For each log the system must display the name, reading value, unit and date.
* AC3: The system should validate that the start date is earlier than the end date.
* AC4: If no logs are available for the given period, the system should return an empty list.

### 1.3 Customer Specifications and Clarifications

N/A