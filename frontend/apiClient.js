import axios from 'axios';

// Function to add a house
async function addHouse() {
    const houseData = {
        latitude: 34.0522,
        longitude: -118.2437,
        street: '123 Smart St',
        doorNumber: '101',
        countryCode: 'US',
        postalCode: '90210'
    };

    try {
        const response = await axios.post('http://localhost:8080/houses', houseData);
        console.log('House added:', response.data);
    } catch (error) {
        console.error('Error adding house:', error.response.data);
    }
}

async function addRooms() {
    const roomData = {
        name: 'Bedroom',
        floor: 1,
        width: 2,
        length: 4,
        height: 3,
    };
    const gardenData = {
        name: 'Garden',
        floor: 1,
        width: 3,
        length: 5,
        height: 3,
    };
    const livingRoomData = {
        name: 'Living Room',
        floor: 1,
        width: 3,
        length: 5,
        height: 5,
    }
    try {
        const responseOne = await axios.post('http://localhost:8080/rooms', roomData);
        console.log('Room added:', responseOne.data);
        const bedroomId = responseOne.data.roomId;
        const responseTwo = await axios.post('http://localhost:8080/rooms', gardenData);
        console.log('Room added:', responseTwo.data);
        const gardenId = responseTwo.data.roomId;
        const responseThree = await axios.post('http://localhost:8080/rooms', livingRoomData);
        console.log('Room added:', responseThree.data);
        return {bedroomId, gardenId};
    } catch (error) {
        console.error('Error adding room:', error.response.data);
    }
}

async function addDevices(gardenId, bedroomId) {
    const devices = [
        {type: "PowerMeter", name: "EDP PowerMeter", roomId: gardenId},
        {type: "PowerSource", name: "Solar Panel", roomId: gardenId},
        {type: "TemperatureGarden", name: "Temperature Sensor", roomId: gardenId},
        {type: "TemperatureBedroom", name: "Temperature Sensor", roomId: bedroomId},
        {type: "BlindRoller", name: "BlindRoller", roomId: bedroomId},
        {type: "LightSensor", name: "Light Intensity", roomId: bedroomId},
        {type: "GeneralDevice", name: "House Device", roomId: bedroomId}
    ];

    const deviceIds = {};
    try {
        for (const device of devices) {
            const response = await axios.post('http://localhost:8080/devices', {
                deviceTypeDescription: device.type,
                deviceName: device.name,
                roomID: device.roomId
            });
            deviceIds[`${device.type.toLowerCase()}DeviceId`] = response.data.deviceID;
            console.log(`Device added: ${device.name} with ID ${response.data.deviceID}`);
        }
        console.log('All devices added:', deviceIds);
        return deviceIds;
    } catch (error) {
        console.error('Error adding devices:', error.response ? error.response.data : error.message);
        return null; // Return null or an empty object to indicate failure
    }
}

async function fetchAndStoreSensorModels() {
    const sensorTypes = [
        {type: 'InstantPowerConsumption', variable: 'instantPowerSensor'},
        {type: 'Temperature', variable: 'temperatureSensor'},
        {type: 'AveragePowerConsumption', variable: 'averagePowerConsumptionSensor'},
        {type: 'DewPoint', variable: 'dewPointSensor'},
        {type: 'Humidity', variable: 'humiditySensor'},
        {type: 'SolarIrradiance', variable: 'solarIrradianceSensor'},
        {type: 'Switch', variable: 'switchSensor'},
        {type: 'Wind', variable: 'windSensor'},
        {type: 'SunriseTime', variable: 'sunriseTimeSensor'},
        {type: 'SunsetTime', variable: 'sunsetTimeSensor'},
        {type: 'ElectricConsumptionWh', variable: 'electricConsumptionWh'},
        {type: 'PercentagePosition', variable: 'percentagePosition'}
    ];

    const globalSettings = {};

    try {
        // Iterate through each sensor type and make a GET request
        for (const {type, variable} of sensorTypes) {
            const response = await axios.get(`http://localhost:8080/sensor-models?sensorTypeID=${type}`, {
                headers: {'Content-Type': 'application/json'}
            });
            // Store the model path in a global settings object
            globalSettings[variable] = response.data._embedded.sensorModelDTOList[0].modelPath;
            console.log(`${variable} set to ${globalSettings[variable]}`);
        }
    } catch (error) {
        console.error('Error fetching sensor models:', error.response ? error.response.data : error.message);
    }

    return globalSettings;
}

async function addSensors(deviceIds, globalSettings) {
    const sensorRequests = [
/*        {
            type: 'genericSensor',
            deviceID: deviceIds.powermeterDeviceId,
            sensorModelPath: globalSettings.instantPowerSensor,
            sensorName: 'GA100K',
            sensorTypeID: 'InstantPowerConsumption'
        },
        {
            type: 'genericSensor',
            deviceID: deviceIds.powersourceDeviceId,
            sensorModelPath: globalSettings.instantPowerSensor,
            sensorName: 'GA100K',
            sensorTypeID: 'InstantPowerConsumption'
        },
        {
            type: 'genericSensor',
            deviceID: deviceIds.temperaturegardenDeviceId,
            sensorModelPath: globalSettings.temperatureSensor,
            sensorName: 'TX200K',
            sensorTypeID: 'Temperature'
        },
        {
            type: 'gpsSensor',
            deviceID: deviceIds.temperaturebedroomDeviceId,
            sensorModelPath: globalSettings.sunsetTimeSensor,
            sensorName: 'GPS300K',
            sensorTypeID: 'SunsetTime',
            latitude: 48.8566,
            longitude: 2.3522
        },
        {
            type: 'genericSensor',
            deviceID: deviceIds.temperaturebedroomDeviceId,
            sensorModelPath: globalSettings.temperatureSensor,
            sensorName: "TX200K",
            sensorTypeID: "Temperature"
        },
        {
            type: 'genericSensor',
            deviceID: deviceIds.generaldeviceDeviceId,
            sensorModelPath: globalSettings.averagePowerConsumptionSensor,
            sensorName: "RD320C",
            sensorTypeID: "AveragePowerConsumption"
        },

 */
        {
            type: 'genericSensor',
            deviceID: deviceIds.blindrollerDeviceId,
            sensorModelPath: globalSettings.percentagePosition,
            sensorName: "PS100K",
            sensorTypeID: "PercentagePosition"
        }
    ];

    try {
        const responses = await Promise.all(
            sensorRequests.map(sensorData =>
                axios.post('http://localhost:8080/sensors', sensorData, {
                    headers: {'Content-Type': 'application/json'}
                })
            )
        );

        // Log the results or handle them as needed
        responses.forEach((response, index) => {
            console.log(`Sensor ${sensorRequests[index].sensorName} added successfully:`, response.data);
        });

    } catch (error) {
        console.error('Error adding sensors:', error.response ? error.response.data : error.message);
    }
}

// Start by adding a house
addHouse().then(() => {
    // Once the house is added, add rooms
    addRooms().then(({gardenId, bedroomId}) => {
        // After rooms, add devices using the IDs from rooms
        addDevices(gardenId, bedroomId).then(deviceIds => {
            // Fetch and store sensor models
            fetchAndStoreSensorModels().then(globalSettings => {
                // Finally, add sensors using device IDs and sensor model paths
                addSensors(deviceIds, globalSettings).then(() => {
                    console.log('All operations completed successfully');
                }).catch(error => {
                    console.error('Failed to add sensors:', error);
                });
            }).catch(error => {
                console.error('Failed to fetch and store sensor models:', error);
            });
        }).catch(error => {
            console.error('Failed to add devices:', error);
        });
    }).catch(error => {
        console.error('Failed to add rooms:', error);
    });
}).catch(error => {
    console.error('Failed to add house:', error);
});
