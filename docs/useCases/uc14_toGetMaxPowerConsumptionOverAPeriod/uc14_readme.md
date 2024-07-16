# UC14

## 0. Description

To get the peak power consumption of the house, in a given period

## 1. Analysis
The system will calculate the max power consumption over a given period, and provide the result.

### 1.1. Use Case Description
_To get the peak power consumption of the house, in a given period_

    Use Case Name: To get the peak power consumption of the house, in a given period

    Actor: Room Owner [or Power User, or Administrator]

    Goal: To provide to the user the max power consumption value of the house, ina given period.

    Preconditions:
    The user must select the power source meter device of the house.

    

    Basic Flow:
    1. The user selects a the power source meter.
    2. The user selects a time frame.
    3. The system provides the user the max power comsumption value from the given time period.

    Alternative Flows:
    1. The user selects the wrong device.
    2. The user selects a time frame that does not exist.
    3. The user selects a time frame that is not valid.


### 1.2. Dependency on other use cases
This use case depends on UC09.

### 1.3. Relevant domain aggregate model
![Device](../../ooa/4.agreggateModels/Device_v1.svg)
![Repository](../../ooa/4.agreggateModels/Log_v1.svg)

### 1.4. Customer Specifications and Clarifications

- See US36 documentation for more details.

### 1.5. System Sequence Diagram
![UC14-SSD](artifacts/uc14_SD_v1.svg)

### 1.6 Applied Patterns
- All classes have only one and well-defined responsibility.
- A controller receives and coordinates system operations connecting the UI layer to the App's logic layer.
- The flow of this process is entirely made following this principle: for a particular responsibility, it is determined the information needed to fulfill it and where that information is stored.
- Services that represent a concept outside the problem's domain, but they have set of responsibilities designed to achieve low coupling, high cohesion and the potential for reuse.
- Dependencies between classes are at their lowest point possible. The use of Services classes reduced the dependency level between them.
- Due to low coupling, the responsibilities of each class are highly focused, therefore cohesion's high.