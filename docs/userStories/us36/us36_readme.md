## US36

## 0. Description

As a Power User [or Administrator], I want to get the peak power consumption of
the house, in a given period. The house has a device acting as the grid power
meter and may have several devices metering power sources (e.g. solar panel
array).

## 1. Analysis

The system will calculate the peak power consumption of the house in a given period.

### 1.1. Created/Affected Use Cases

* Creates: Use Case 14 - To Get the Maximum Power Consumption over a
  Period [UC14](../../useCases/uc14_toGetMaxPowerConsumptionOverAPeriod/uc14_readme.md)

### 1.2. Acceptance Criteria

* AC1: The system must allow the Power User and the Administrator only to select a time frame.
* AC2: The system must calculate the peak power consumption of the house in the selected time frame.
* AC3: The system must display the peak power consumption of the house in the selected time frame.

### 1.3 Customer Specifications and Clarifications

* 30/04/2024 - "O consumo da casa será o valor da rede mais a soma das várias fontes. Um valor negativo do "grid power
  meter" significa que está a ser injectada energia na rede. Note-se, mais uma vez, que não faz sentido somar valores
  muito distantes no tempo. Terá de ser considerado um intervalo de validade.
* 30/04/2024 - "Poderá ser o referido na US24 - Power consumption in a given instant (W)?"
* Parece-me uma escolha ajustada. Estamos a falar de potência instantânea..."
* 02/05/2024 - "Há um grid power meter à entrada e vários devices associados a produtores de energia. Portanto, há
  vários power source independentes espalhados pela casa."
* 02/05/2024 - "Quanto ao intervalo de tempo em que as medições podem ser consideradas como simultâneas, deve ser
  parametrizável. Por exemplo, em algumas condições 1 minuto pode ser exigido, noutros casos podem ser 15 minutos. O
  utilizador ou o caso de uso devem definir o valor. É a melhor solução."
* 08/05/2024 - "Quanto ao intervalo de tempo em que as medições podem ser consideradas como simultâneas, deve ser
  parametrizável. Por exemplo, em algumas condições 1 minuto pode ser exigido, noutros casos podem ser 15 minutos. O
  utilizador ou o caso de uso devem definir o valor. É a melhor solução."