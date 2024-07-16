## US43

## 0. Description

As a Room Owner [or Power User, or Administrator], I want to get a list of all devices in a room, so that I can configure them.

## 1. Analysis

Allows a Room Owner to see a list of all devices in a room using the Web App. This use case involves fetching the list of
rooms from the backend, and then fetch all the devices and displaying them in the Web App. 

The User should be able to select a device in order to configure it.

### 1.1 Acceptance Criteria

* AC01: The system must allow a User to see a list of all rooms in the house.
* AC02: The system must allow a User to see the name of each room in the list.
* AC03: The system must allow the User to see a list of devices in a room.
* AC04: The system must allow the User to see the name of each device in the list.
* AC05: The system must allow the User to select a device in order to configure it.

### 1.2 Customer Specifications and Clarifications

* The user interface must be intuitive and responsive.
* The user should be able to see the list of rooms in the house.
* The user should be able to see the list of devices in a room.
* Error handling should be implemented to inform the user if the room list fails to load.