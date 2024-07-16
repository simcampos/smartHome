import {
    ADD_DATE_SENSOR_TO_DEVICE_FAILURE,
    ADD_DATE_SENSOR_TO_DEVICE_STARTED,
    ADD_DATE_SENSOR_TO_DEVICE_SUCCESS,
    ADD_DECIMAL_ACTUATOR_TO_DEVICE_FAILURE,
    ADD_DECIMAL_ACTUATOR_TO_DEVICE_STARTED,
    ADD_DECIMAL_ACTUATOR_TO_DEVICE_SUCCESS,
    ADD_DEVICE_FAILURE,
    ADD_DEVICE_STARTED,
    ADD_DEVICE_SUCCESS,
    ADD_GENERIC_ACTUATOR_TO_DEVICE_FAILURE,
    ADD_GENERIC_ACTUATOR_TO_DEVICE_STARTED,
    ADD_GENERIC_ACTUATOR_TO_DEVICE_SUCCESS,
    ADD_GENERIC_SENSOR_TO_DEVICE_FAILURE,
    ADD_GENERIC_SENSOR_TO_DEVICE_STARTED,
    ADD_GENERIC_SENSOR_TO_DEVICE_SUCCESS,
    ADD_GPS_SENSOR_TO_DEVICE_FAILURE,
    ADD_GPS_SENSOR_TO_DEVICE_STARTED,
    ADD_GPS_SENSOR_TO_DEVICE_SUCCESS,
    ADD_INTEGER_ACTUATOR_TO_DEVICE_FAILURE,
    ADD_INTEGER_ACTUATOR_TO_DEVICE_STARTED,
    ADD_INTEGER_ACTUATOR_TO_DEVICE_SUCCESS,
    DEACTIVATE_DEVICE_FAILURE,
    DEACTIVATE_DEVICE_STARTED,
    DEACTIVATE_DEVICE_SUCCESS,
    FETCH_ACTUATOR_MODELS_FAILURE,
    FETCH_ACTUATOR_MODELS_STARTED,
    FETCH_ACTUATOR_MODELS_SUCCESS,
    FETCH_ACTUATOR_TYPES_FAILURE,
    FETCH_ACTUATOR_TYPES_STARTED,
    FETCH_ACTUATOR_TYPES_SUCCESS,
    FETCH_ACTUATORS_FAILURE,
    FETCH_ACTUATORS_STARTED,
    FETCH_ACTUATORS_SUCCESS,
    FETCH_CURRENT_POSITION_FAILURE,
    FETCH_CURRENT_POSITION_STARTED,
    FETCH_CURRENT_POSITION_SUCCESS,
    FETCH_DEVICE_TYPES_FAILURE,
    FETCH_DEVICE_TYPES_STARTED,
    FETCH_DEVICE_TYPES_SUCCESS,
    FETCH_DEVICES_FAILURE,
    FETCH_DEVICES_STARTED,
    FETCH_DEVICES_SUCCESS,
    FETCH_LOGS_FAILURE,
    FETCH_LOGS_STARTED,
    FETCH_LOGS_SUCCESS,
    FETCH_ROOM_BY_ID_FAILURE,
    FETCH_ROOM_BY_ID_STARTED,
    FETCH_ROOM_BY_ID_SUCCESS,
    FETCH_ROOMS_FAILURE,
    FETCH_ROOMS_STARTED,
    FETCH_ROOMS_SUCCESS,
    FETCH_SENSOR_MODELS_FAILURE,
    FETCH_SENSOR_MODELS_STARTED,
    FETCH_SENSOR_MODELS_SUCCESS,
    FETCH_SENSOR_TYPES_FAILURE,
    FETCH_SENSOR_TYPES_STARTED,
    FETCH_SENSOR_TYPES_SUCCESS, FETCH_SENSORS_FAILURE, FETCH_SENSORS_STARTED, FETCH_SENSORS_SUCCESS,
    SAVE_CURRENT_DEVICE,
    SET_BLIND_ROLLER_VALUE_FAILURE,
    SET_BLIND_ROLLER_VALUE_STARTED,
    SET_BLIND_ROLLER_VALUE_SUCCESS,
    SET_CURRENT_ROOM,
    UPDATE_DEVICE_FAILURE,
    UPDATE_DEVICE_STARTED,
    UPDATE_DEVICE_SUCCESS,
    UPDATE_END_DATE_DATA,
    UPDATE_GENERIC_ACTUATOR_DATA,
    UPDATE_GENERIC_SENSOR_DATA,
    UPDATE_LATITUDE_DATA,
    UPDATE_LONGITUDE_DATA,
    UPDATE_MAX_LIMIT_DATA,
    UPDATE_MIN_LIMIT_DATA,
    UPDATE_SELECTED_ACTUATOR_MODEL_NAME,
    UPDATE_SELECTED_ACTUATOR_MODEL_PATH,
    UPDATE_SELECTED_ACTUATOR_TYPE_ID,
    UPDATE_SELECTED_SENSOR_MODEL,
    UPDATE_SELECTED_SENSOR_MODEL_NAME,
    UPDATE_SELECTED_SENSOR_MODEL_PATH,
    UPDATE_SELECTED_SENSOR_TYPE_ID,
    UPDATE_SELECTED_TYPE_OF_ACTUATOR,
    UPDATE_SELECTED_TYPE_OF_SENSOR,
    UPDATE_START_DATE_DATA
} from "./Actions.jsx";
import {
    FETCH_TEMPERATURE_FAILURE,
    FETCH_TEMPERATURE_STARTED,
    FETCH_TEMPERATURE_SUCCESS
} from "./TemperatureActions.jsx";

function reducer(state = initialState, action) {
    switch (action.type) {
        case FETCH_ROOMS_STARTED:
            return {
                ...state,
                rooms: {
                    loading: true,
                    error: null,
                    data: []
                }
            };
        case FETCH_ROOMS_SUCCESS:
            return {
                ...state,
                rooms: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            };
        case FETCH_ROOMS_FAILURE:
            return {
                ...state,
                rooms: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            };
        case SET_CURRENT_ROOM:
            return {
                ...state,
                currentRoom: {
                    roomId: action.payload.roomId,
                    roomName: action.payload.roomName
                }
            };
        case FETCH_ROOM_BY_ID_STARTED:
            return {
                ...state,
                room: {
                    loading: true,
                    error: null,
                    data: null
                }
            };
        case FETCH_ROOM_BY_ID_SUCCESS:
            return {
                ...state,
                room: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            };
        case FETCH_ROOM_BY_ID_FAILURE:
            return {
                ...state,
                room: {
                    loading: false,
                    error: action.payload.error,
                    data: null
                }
            };
        case FETCH_DEVICES_STARTED:
            return {
                ...state,
                devices: {
                    loading: true,
                    error: null,
                    data: []
                }
            };
        case FETCH_DEVICES_SUCCESS:
            return {
                ...state,
                devices: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            };
        case FETCH_DEVICES_FAILURE:
            return {
                ...state,
                devices: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            };
        case FETCH_TEMPERATURE_STARTED:
            return {
                ...state,
                temperature: {
                    loading: true,
                    error: null,
                    data: null,
                    lastUpdated: null
                }
            };
        case FETCH_TEMPERATURE_SUCCESS:
            return {
                ...state,
                temperature: {
                    loading: false,
                    error: null,
                    data: action.payload.data,
                    lastUpdated: action.payload.lastUpdated
                }
            };
        case FETCH_TEMPERATURE_FAILURE:
            return {
                ...state,
                temperature: {
                    loading: false,
                    error: action.payload.error,
                    data: null,
                    lastUpdated: null
                }
            };

        case ADD_DEVICE_STARTED:
            return {
                ...state,
                addingDevice: true,
                addDeviceError: null
            };
        case ADD_DEVICE_SUCCESS:
            return {
                ...state,
                addingDevice: false,
                addDeviceError: null,
                devices: {
                    ...state.devices,
                    data: [...state.devices.data, action.payload.device]
                }
            };
        case ADD_DEVICE_FAILURE:
            return {
                ...state,
                addingDevice: false,
                addDeviceError: action.payload.error
            };

        case FETCH_LOGS_STARTED:
            return {
                ...state,
                logs: {
                    loading: true,
                    error: null,
                    data: []
                }
            };
        case FETCH_LOGS_SUCCESS:
            return {
                ...state,
                logs: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            };
        case FETCH_LOGS_FAILURE:
            return {
                ...state,
                logs: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            };
        case UPDATE_DEVICE_STARTED:
            return {
                ...state,
                updateDevice: {
                    loading: true,
                    error: null,
                    data: null
                }
            };
        case UPDATE_DEVICE_SUCCESS:
            return {
                ...state,
                updateDevice: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            };
        case UPDATE_DEVICE_FAILURE:
            return {
                ...state,
                updateDevice: {
                    loading: false,
                    error: action.payload.error,
                    data: null
                }
            };


        case FETCH_CURRENT_POSITION_STARTED:
            return {
                ...state,
                position: {
                    ...state.position,
                    loading: true,
                    error: null
                }
            };
        case FETCH_CURRENT_POSITION_SUCCESS:
            return {
                ...state,
                position: {
                    ...state.position,
                    loading: false,
                    data: action.payload.data,
                    error: null
                }
            };
        case FETCH_CURRENT_POSITION_FAILURE:
            return {
                ...state,
                position: {
                    ...state.position,
                    loading: false,
                    data: null,
                    error: action.payload.error
                }
            };
        case SET_BLIND_ROLLER_VALUE_STARTED:
            return {
                ...state,
                setBlindRollerStatus: {
                    loading: true,
                    error: null,
                    message: null
                }
            };
        case SET_BLIND_ROLLER_VALUE_SUCCESS:
            return {
                ...state,
                setBlindRollerStatus: {
                    loading: false,
                    error: null,
                    message: action.payload.message
                }
            };
        case SET_BLIND_ROLLER_VALUE_FAILURE:
            return {
                ...state,
                setBlindRollerStatus: {
                    loading: false,
                    error: action.payload.error,
                    message: null
                }
            };
        case FETCH_DEVICE_TYPES_STARTED:
            return {
                ...state,
                deviceTypes: {
                    loading: true,
                    error: null,
                    data: []
                }
            };
        case FETCH_DEVICE_TYPES_SUCCESS:
            return {
                ...state,
                deviceTypes: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            };
        case FETCH_DEVICE_TYPES_FAILURE:
            return {
                ...state,
                deviceTypes: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            };


        case SAVE_CURRENT_DEVICE:
            return {
                ...state,
                currentDevice: {
                    deviceId: action.payload.deviceId
                }
            };

// ------------------------------ Sensor ------------------------------//
        case FETCH_SENSORS_STARTED:
            return {
                ...state,
                sensors: {
                    loading: true,
                    error: null,
                    data: []
                }
            };
        case FETCH_SENSORS_SUCCESS:
            return {
                ...state,
                sensors: {
                    loading: false,
                    error: null,
                    data: action.payload.sensors
                }
            };
        case FETCH_SENSORS_FAILURE:
            return {
                ...state,
                sensors: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            };

// ------------------------------ Types of Sensor ------------------------------//
        case UPDATE_SELECTED_TYPE_OF_SENSOR:
            return {
                ...state,
                selectedTypeOfSensor: action.payload.selectedTypeOfSensor
            };
// ------------------------------ Sensor Models ------------------------------//

        case FETCH_SENSOR_MODELS_STARTED:
            return {
                ...state,
                sensorModels: {
                    loading: true,
                    error: null,
                    data: []
                }
            };
        case FETCH_SENSOR_MODELS_SUCCESS:
            return {
                ...state,
                sensorModels: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            };
        case FETCH_SENSOR_MODELS_FAILURE:
            return {
                ...state,
                sensorModels: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            };

        case UPDATE_SELECTED_SENSOR_MODEL:
            return {
                ...state,
                selectedSensorModel: action.payload.selectedSensorModel
            };

        case UPDATE_SELECTED_SENSOR_MODEL_PATH:
            return {
                ...state,
                selectedSensorModelPath: action.payload.selectedSensorModelPath
            };

        case UPDATE_SELECTED_SENSOR_MODEL_NAME:
            return {
                ...state,
                selectedSensorModelName: action.payload.selectedSensorModelName
            };

        // ------------------------------ Sensor Types ------------------------------//

        case FETCH_SENSOR_TYPES_STARTED:
            return {
                ...state,
                sensorTypes: {
                    loading: true,
                    error: null,
                    data: []
                }
            };
        case FETCH_SENSOR_TYPES_SUCCESS:
            return {
                ...state,
                sensorTypes: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            };
        case FETCH_SENSOR_TYPES_FAILURE:
            return {
                ...state,
                sensorTypes: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            };


        case UPDATE_SELECTED_SENSOR_TYPE_ID:
            return {
                ...state,
                selectedSensorTypeId: action.payload.selectedSensorType
            };

        // ------------------------------ Add Sensor ------------------------------//
        case UPDATE_GENERIC_SENSOR_DATA:
            return {
                ...state,
                sensorName: action.payload.sensorName,
            };

        case UPDATE_LATITUDE_DATA:
            return {
                ...state,
                latitude: action.payload.latitude,
            };

        case UPDATE_LONGITUDE_DATA:
            return {
                ...state,
                longitude: action.payload.longitude,
            };

        case UPDATE_START_DATE_DATA:
            return {
                ...state,
                startDate: action.payload.startDate,
            };

        case UPDATE_END_DATE_DATA:
            return {
                ...state,
                endDate: action.payload.endDate,
            };

        case ADD_GENERIC_SENSOR_TO_DEVICE_STARTED:
            return {
                ...state,
                addingSensor: {
                    status: true,
                    error: null,
                }
            };

        case ADD_GENERIC_SENSOR_TO_DEVICE_SUCCESS:
            return {
                ...state,
                addingSensor: {
                    status: false,
                    error: null,
                }
            };

        case ADD_GENERIC_SENSOR_TO_DEVICE_FAILURE:
            return {
                ...state,
                addingSensor: {
                    status: false,
                    error: action.payload.error,
                }
            };

        case ADD_GPS_SENSOR_TO_DEVICE_STARTED:
            return {
                ...state,
                addingSensor: {
                    status: true,
                    error: null,
                }
            };

        case ADD_GPS_SENSOR_TO_DEVICE_SUCCESS:
            return {
                ...state,
                addingSensor: {
                    status: false,
                    error: null,
                }
            };

        case ADD_GPS_SENSOR_TO_DEVICE_FAILURE:
            return {
                ...state,
                addingSensor: {
                    status: false,
                    error: action.payload.error,
                }
            };

        case ADD_DATE_SENSOR_TO_DEVICE_STARTED:
            return {
                ...state,
                addingSensor: {
                    status: true,
                    error: null,
                }
            };

        case ADD_DATE_SENSOR_TO_DEVICE_SUCCESS:
            return {
                ...state,
                addingSensor: {
                    status: false,
                    error: null,
                }
            };

        case ADD_DATE_SENSOR_TO_DEVICE_FAILURE:
            return {
                ...state,
                addingSensor: {
                    status: false,
                    error: action.payload.error,
                }
            };

        // ------------------------------ Actuators ------------------------------//
// ------------------------------ Fetch Actuator ------------------------------//
        case FETCH_ACTUATORS_STARTED:
            return {
                ...state,
                actuators: {
                    loading: true,
                    error: null,
                    data: []
                }
            };
        case FETCH_ACTUATORS_SUCCESS:
            return {
                ...state,
                actuators: {
                    loading: false,
                    error: null,
                    data: action.payload.actuators
                }
            };
        case FETCH_ACTUATORS_FAILURE:
            return {
                ...state,
                actuators: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            };

// ------------------------------ Types of Actuator ------------------------------//
        case UPDATE_SELECTED_TYPE_OF_ACTUATOR:
            return {
                ...state,
                selectedTypeOfActuator: action.payload.selectedTypeOfActuator
            };

        // ------------------------------ Actuators Model ------------------------------//
        case FETCH_ACTUATOR_MODELS_STARTED:
            return {
                ...state,
                actuatorModels: {
                    loading: true,
                    error: null,
                    data: []
                }
            };

        case FETCH_ACTUATOR_MODELS_SUCCESS:
            return {
                ...state,
                actuatorModels: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            };

        case FETCH_ACTUATOR_MODELS_FAILURE:
            return {
                ...state,
                actuatorModels: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            };

        case  UPDATE_SELECTED_ACTUATOR_MODEL_PATH:
            return {
                ...state,
                selectedActuatorModelPath: action.payload.selectedActuatorModelPath
            };

        case UPDATE_SELECTED_ACTUATOR_MODEL_NAME:
            return {
                ...state,
                selectedActuatorModelName: action.payload.selectedActuatorModelName
            };

        // ------------------------------ Actuators Types ------------------------------//

        case FETCH_ACTUATOR_TYPES_STARTED:
            return {
                ...state,
                actuatorTypes: {
                    loading: true,
                    error: null,
                    data: []
                }
            };

        case FETCH_ACTUATOR_TYPES_SUCCESS:
            return {
                ...state,
                actuatorTypes: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            };

        case FETCH_ACTUATOR_TYPES_FAILURE:
            return {
                ...state,
                actuatorTypes: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            };

        case UPDATE_SELECTED_ACTUATOR_TYPE_ID:
            return {
                ...state,
                selectedActuatorTypeId: action.payload.selectedActuatorType
            };

        // ------------------------------ Add Actuator ------------------------------//
        case UPDATE_GENERIC_ACTUATOR_DATA:
            return {
                ...state,
                actuatorName: action.payload.actuatorName
            };

        case UPDATE_MIN_LIMIT_DATA:
            return {
                ...state,
                minLimit: action.payload.minLimit
            };

        case UPDATE_MAX_LIMIT_DATA:
            return {
                ...state,
                maxLimit: action.payload.maxLimit
            };

        case ADD_GENERIC_ACTUATOR_TO_DEVICE_STARTED:
            return {
                ...state,
                addingActuator: {
                    status: true,
                    error: null,
                }
            };

        case ADD_GENERIC_ACTUATOR_TO_DEVICE_SUCCESS:
            return {
                ...state,
                addingActuator: {
                    status: false,
                    error: null,
                }
            };

        case ADD_GENERIC_ACTUATOR_TO_DEVICE_FAILURE:
            return {
                ...state,
                addingActuator: {
                    status: false,
                    error: action.payload.error,
                }
            };

        case ADD_DECIMAL_ACTUATOR_TO_DEVICE_STARTED:
            return {
                ...state,
                addingActuator: {
                    status: true,
                    error: null,
                }
            };

        case ADD_DECIMAL_ACTUATOR_TO_DEVICE_SUCCESS:
            return {
                ...state,
                addingActuator: {
                    status: false,
                    error: null,
                }
            };

        case ADD_DECIMAL_ACTUATOR_TO_DEVICE_FAILURE:
            return {
                ...state,
                addingActuator: {
                    status: false,
                    error: action.payload.error,
                }
            };

        case ADD_INTEGER_ACTUATOR_TO_DEVICE_STARTED:
            return {
                ...state,
                addingActuator: {
                    status: true,
                    error: null,
                }
            };

        case ADD_INTEGER_ACTUATOR_TO_DEVICE_SUCCESS:
            return {
                ...state,
                addingActuator: {
                    status: false,
                    error: null,
                }
            };

        case ADD_INTEGER_ACTUATOR_TO_DEVICE_FAILURE:
            return {
                ...state,
                addingActuator: {
                    status: false,
                    error: action.payload.error,
                }
            };

        case DEACTIVATE_DEVICE_STARTED:
            return {
                ...state,
                deactivatingDevice: true,
                deactivateDeviceError: null
            };

        case DEACTIVATE_DEVICE_SUCCESS:
            return {
                ...state,
                deactivatingDevice: false,
                deactivateDeviceError: null
            };

        case DEACTIVATE_DEVICE_FAILURE:
            return {
                ...state,
                deactivatingDevice: false,
                deactivateDeviceError: action.payload.error
            };

        case 'RESET_CHANGES':
            return {
                ...state,
                selectedTypeOfActuator: "",
                selectedTypeOfSensor: "",
                selectedSensorTypeId: "",
                selectedSensorModelName: "",
                sensorName: "",
                selectedActuatorTypeId: "",
                selectedActuatorModelName: "",
                latitude: '',
                longitude: '',
                startDate: null,
                endDate: null,
                actuatorName: '',
                minLimit: '',
                maxLimit: '',

            };


        default:
            return state;
    }
}

export default reducer;
