## US37

## 0. Description

As a Room Owner [or Power User, or Administrator], I want to close a blind roller that is in a room.

## 1. Analysis
The system will close a blind roller that is in a room, with a given value set by the user.

### 1.1. Created/Affected Use Cases

* Affected: Use Case 03 - To Get a List of Rooms [UC03](/docs/useCases/uc03_toGetListOfRooms)
* Affected: Use Case 06 - To Get List of All Devices In a Room [UC06](/docs/useCases/uc06_toGetListOfAllDevicesInRoom)
* Creates: Use Case 15 - To Get Actuators By Device ID [UC15](/docs/useCases/uc15_toGetActuatorsByDeviceID)
* Creates: Use case 16 - To Close a Blind Roller [UC16](/docs/useCases/uc16_toCloseBlindRoller)

### 1.2 Acceptance Criteria

* AC01: The system must allow a Room Owner, Power User, or Administrator to select a room.
* AC02: The system must provide a list of all devices in the selected room.
* AC03: The system must allow the user to select a blind roller from the list of devices.
* AC04: The system must allow the user to set a value for the blind roller position.
* AC05: The system must close the blind roller to the set position when the user confirms the action.
* AC06: The system must provide feedback to the user that the blind roller has been successfully closed to the set position.

### 1.3 Customer Specifications and Clarifications 

* 02/05/2024 - "O blind roller pode apenas ser fechado ou a US deve contemplar também a sua abertura? O fecho pode ser tanto total como parcial?"
* Só fechar. Mas pode ser parcial.
* 02/05/2024 - "O atuador recebe o valor (%) da posição escolhida em que o blind roller deve ficar, ou o delta entre a posição atual e a escolhida?"
* Pode ser a posição final. 0% é fechado.
* 02/05/2024 - "Devemos considerar que o Blind Roller é um Device que possui em simultâneo:
  Um type of actuator that open/closes a blind roller referido na US14;
  Um type of sensor para current value/position in a scale (%) referido na US18?"
* O device que representa o blind roller poderá ter esse atuador e esse sensor.
* 02/05/2024 - "Sobre o Device que representar o Blind Roller ter então um atuador e um sensor, pode confirmar se é correto afirmar:
- o Actuator apenas promove uma ação no Hardware, mas não tem leituras (por não ser um sensor).
- para o utilizador saber se o Device que representar o Blind Roller está em posição fechado, tem de obter dados do Sensor."
* "Eu diria que atua sobre o device, mas a forma como interage com o device depende da implementação. Nos requisitos explicam-se bem os conceitos."
