import {
    addDateSensor,
    addDecimalActuator,
    addDeviceToRoom as addDeviceToRoomService,
    addGenericActuator,
    addGenericSensor,
    addGPSSensor,
    addIntegerActuator, deactivateDevice,
    fetchActuatorModelsByActuatorTypeIdFromServer,
    fetchActuatorsByDeviceId,
    fetchActuatorTypesFromServer,
    fetchCurrentPositionValue,
    fetchDevicesByRoomIdFromServer,
    fetchDeviceTypesFromServer,
    fetchLogsByDeviceIdFromServer,
    fetchRoomByIdFromServer,
    fetchRoomsFromServer,
    fetchSensorModelsBySensorTypeIdFromServer, fetchSensorsByDeviceId,
    fetchSensorTypesFromServer,
    setBlindRollerValue,
    updateCurrentDeviceFromServer
} from "../services/Service.jsx";

// Actions
export const FETCH_ROOMS_STARTED = 'FETCH_ROOMS_STARTED';
export const FETCH_ROOMS_SUCCESS = 'FETCH_ROOMS_SUCCESS';
export const FETCH_ROOMS_FAILURE = 'FETCH_ROOMS_FAILURE';
export const ADD_DEVICE_STARTED = 'ADD_DEVICE_STARTED';
export const ADD_DEVICE_SUCCESS = 'ADD_DEVICE_SUCCESS';
export const ADD_DEVICE_FAILURE = 'ADD_DEVICE_FAILURE';
export const FETCH_ACTUATOR_MODELS_STARTED = 'FETCH_ACTUATOR_MODELS_STARTED';
export const FETCH_ACTUATOR_MODELS_SUCCESS = 'FETCH_ACTUATOR_MODELS_SUCCESS';
export const FETCH_ACTUATOR_MODELS_FAILURE = 'FETCH_ACTUATOR_MODELS_FAILURE';
export const FETCH_ROOM_BY_ID_STARTED = 'FETCH_ROOM_BY_ID_STARTED';
export const FETCH_ROOM_BY_ID_SUCCESS = 'FETCH_ROOM_BY_ID_SUCCESS';
export const FETCH_ROOM_BY_ID_FAILURE = 'FETCH_ROOM_BY_ID_FAILURE';
export const FETCH_DEVICES_STARTED = 'FETCH_DEVICES_STARTED';
export const FETCH_DEVICES_SUCCESS = 'FETCH_DEVICES_SUCCESS';
export const FETCH_DEVICES_FAILURE = 'FETCH_DEVICES_FAILURE';
export const FETCH_SENSOR_TYPES_STARTED = 'FETCH_SENSOR_TYPES_STARTED';
export const FETCH_SENSOR_TYPES_SUCCESS = 'FETCH_SENSOR_TYPES_SUCCESS';
export const FETCH_SENSOR_TYPES_FAILURE = 'FETCH_SENSOR_TYPES_FAILURE';
export const FETCH_SENSOR_MODELS_STARTED = 'FETCH_SENSOR_MODELS_STARTED';
export const FETCH_SENSOR_MODELS_SUCCESS = 'FETCH_SENSOR_MODELS_SUCCESS';
export const FETCH_SENSOR_MODELS_FAILURE = 'FETCH_SENSOR_MODELS_FAILURE';
export const FETCH_LOGS_STARTED = 'FETCH_LOGS_STARTED';
export const FETCH_LOGS_SUCCESS = 'FETCH_LOGS_SUCCESS';
export const FETCH_LOGS_FAILURE = 'FETCH_LOGS_FAILURE';
export const UPDATE_DEVICE_STARTED = 'UPDATE_DEVICE_STARTED';
export const UPDATE_DEVICE_SUCCESS = 'UPDATE_DEVICE_SUCCESS';
export const UPDATE_DEVICE_FAILURE = 'UPDATE_DEVICE_FAILURE';
export const SET_CURRENT_ROOM = 'SET_CURRENT_ROOM';
export const UPDATE_SELECTED_SENSOR_TYPE_ID = 'UPDATE_SELECTED_SENSOR_TYPE_ID';
export const UPDATE_SELECTED_ACTUATOR_TYPE_ID = 'UPDATE_SELECTED_ACTUATOR_TYPE_ID';

export const UPDATE_SELECTED_SENSOR_MODEL = 'UPDATE_SELECTED_SENSOR_MODEL';
export const UPDATE_SELECTED_TYPE_OF_SENSOR = 'UPDATE_SELECTED_TYPE_OF_SENSOR';
export const FETCH_CURRENT_POSITION_STARTED = 'FETCH_CURRENT_POSITION_STARTED';
export const FETCH_CURRENT_POSITION_SUCCESS = 'FETCH_CURRENT_POSITION_SUCCESS';
export const FETCH_CURRENT_POSITION_FAILURE = 'FETCH_CURRENT_POSITION_FAILURE';
export const SET_BLIND_ROLLER_VALUE_STARTED = 'SET_BLIND_ROLLER_VALUE_STARTED';
export const SET_BLIND_ROLLER_VALUE_SUCCESS = 'SET_BLIND_ROLLER_VALUE_SUCCESS';
export const SET_BLIND_ROLLER_VALUE_FAILURE = 'SET_BLIND_ROLLER_VALUE_FAILURE';
export const FETCH_DEVICE_TYPES_STARTED = 'FETCH_DEVICE_TYPES_STARTED';
export const FETCH_DEVICE_TYPES_SUCCESS = 'FETCH_DEVICE_TYPES_SUCCESS';
export const FETCH_DEVICE_TYPES_FAILURE = 'FETCH_DEVICE_TYPES_FAILURE';
export const FETCH_ACTUATORS_STARTED = 'FETCH_ACTUATORS_STARTED';
export const FETCH_ACTUATORS_SUCCESS = 'FETCH_ACTUATORS_SUCCESS';
export const FETCH_ACTUATORS_FAILURE = 'FETCH_ACTUATORS_FAILURE';
export const FETCH_SENSORS_STARTED = 'FETCH_SENSORS_STARTED';
export const FETCH_SENSORS_SUCCESS = 'FETCH_SENSORS_SUCCESS';
export const FETCH_SENSORS_FAILURE = 'FETCH_SENSORS_FAILURE';
export const FETCH_ACTUATOR_TYPES_STARTED = 'FETCH_ACTUATOR_TYPES_STARTED';
export const FETCH_ACTUATOR_TYPES_SUCCESS = 'FETCH_ACTUATOR_TYPES_SUCCESS';
export const FETCH_ACTUATOR_TYPES_FAILURE = 'FETCH_ACTUATOR_TYPES_FAILURE';
export const DEACTIVATE_DEVICE_STARTED = 'DEACTIVATE_DEVICE_STARTED';
export const DEACTIVATE_DEVICE_SUCCESS = 'DEACTIVATE_DEVICE_SUCCESS';
export const DEACTIVATE_DEVICE_FAILURE = 'DEACTIVATE_DEVICE_FAILURE';

export const resetChanges = (dispatch) => {
    dispatch({
        type: 'RESET_CHANGES',
    });
};

export function fetchRooms(dispatch) {
    dispatch({type: FETCH_ROOMS_STARTED});

    const success = (res) => {
        // Ensure we handle the case where _embedded might not be defined
        const roomList = res._embedded ? res._embedded.roomDTOList : res;
        dispatch({type: FETCH_ROOMS_SUCCESS, payload: {data: roomList}});
    };

    const failure = (err) => {
        dispatch({type: FETCH_ROOMS_FAILURE, payload: {error: err}});
    };

    fetchRoomsFromServer(success, failure);
}


export function setCurrentRoom(dispatch, roomId, roomName) {
    const action = {
        type: SET_CURRENT_ROOM,
        payload: {roomId, roomName}
    };
    dispatch(action);
}

export function fetchRoomById(dispatch, id) {
    dispatch({type: FETCH_ROOM_BY_ID_STARTED});

    const success = (res) => dispatch(fetchRoomByIdSuccess(res));
    const failure = (err) => dispatch(fetchRoomByIdFailure(err.message));

    fetchRoomByIdFromServer(success, failure, id);
}

function fetchRoomByIdSuccess(room) {
    return {
        type: FETCH_ROOM_BY_ID_SUCCESS,
        payload: {data: room}
    };
}

function fetchRoomByIdFailure(message) {
    return {
        type: FETCH_ROOM_BY_ID_FAILURE,
        payload: {error: message}
    };
}

export function fetchDevicesByRoomId(dispatch, id) {
    dispatch({type: FETCH_DEVICES_STARTED});

    const success = (res) => dispatch(fetchDevicesByRoomIdSuccess(res));
    const failure = (err) => dispatch(fetchDevicesByRoomIdFailure(err.message));

    fetchDevicesByRoomIdFromServer(success, failure, id);
}

function fetchDevicesByRoomIdSuccess(devices) {
    return {
        type: FETCH_DEVICES_SUCCESS,
        payload: {data: devices}
    };
}

function fetchDevicesByRoomIdFailure(message) {
    return {
        type: FETCH_DEVICES_FAILURE,
        payload: {error: message}
    };
}

export function addDeviceToRoom(dispatch, roomId, device) {
    dispatch({type: ADD_DEVICE_STARTED});

    addDeviceToRoomService(
        roomId,
        device,
        (data) => dispatch({type: ADD_DEVICE_SUCCESS, payload: {device: data}}),
        (err) => dispatch({type: ADD_DEVICE_FAILURE, payload: {error: err.message}})
    );
}


export function fetchLogsByDeviceId(dispatch, deviceId, timeStart, timeEnd) {
    dispatch({type: FETCH_LOGS_STARTED});

    const success = (res) => dispatch(fetchLogsSuccess(res));
    const failure = (err) => dispatch(fetchLogsFailure(err.message));

    fetchLogsByDeviceIdFromServer(success, failure, deviceId, timeStart, timeEnd);
}

function fetchLogsSuccess(logs) {
    return {
        type: FETCH_LOGS_SUCCESS,
        payload: {data: logs}
    };
}

function fetchLogsFailure(message) {
    return {
        type: FETCH_LOGS_FAILURE,
        payload: {error: message}
    };
}

export function updateCurrentDevice(dispatch, deviceId, deviceName) {
    dispatch({type: UPDATE_DEVICE_STARTED});

    const success = (res) => dispatch(updateCurrentDeviceSuccess(res));
    const failure = (err) => dispatch(updateCurrentDeviceFailure(err.message));

    updateCurrentDeviceFromServer(success, failure, deviceId, deviceName);
}

function updateCurrentDeviceSuccess(device) {
    return {
        type: UPDATE_DEVICE_SUCCESS,
        payload: {data: device}
    };
}

function updateCurrentDeviceFailure(message) {
    return {
        type: UPDATE_DEVICE_FAILURE,
        payload: {error: message}
    };
}

export function fetchCurrentPosition(dispatch, deviceID) {
    dispatch({type: FETCH_CURRENT_POSITION_STARTED});

    const success = (position) => dispatch({type: FETCH_CURRENT_POSITION_SUCCESS, payload: {data: position}});
    const failure = (error) => dispatch({type: FETCH_CURRENT_POSITION_FAILURE, payload: {error: error.message}});

    fetchCurrentPositionValue(success, failure, deviceID);
}

export function setBlindRoller(dispatch, deviceID, actuatorID, value) {
    dispatch({type: SET_BLIND_ROLLER_VALUE_STARTED});

    const success = (data) => dispatch({
        type: SET_BLIND_ROLLER_VALUE_SUCCESS,
        payload: {data, message: 'Blind roller value set successfully'}
    });
    const failure = (error) => dispatch({type: SET_BLIND_ROLLER_VALUE_FAILURE, payload: {error: error.message}});

    setBlindRollerValue(success, failure, deviceID, actuatorID, value);
}

export function fetchDeviceTypes(dispatch) {
    dispatch({type: FETCH_DEVICE_TYPES_STARTED});

    const success = (res) => dispatch({type: FETCH_DEVICE_TYPES_SUCCESS, payload: {data: res}});
    const failure = (err) => dispatch({type: FETCH_DEVICE_TYPES_FAILURE, payload: {error: err.message}});

    fetchDeviceTypesFromServer(success, failure);
}

// ------------------------------ Sensors ------------------------------//
// ------------------------------ Add Sensors ------------------------------//


export const ADD_GENERIC_SENSOR_TO_DEVICE_STARTED = 'ADD_GENERIC_SENSOR_TO_DEVICE_STARTED';
export const ADD_GENERIC_SENSOR_TO_DEVICE_SUCCESS = 'ADD_GENERIC_SENSOR_TO_DEVICE_SUCCESS';
export const ADD_GENERIC_SENSOR_TO_DEVICE_FAILURE = 'ADD_GENERIC_SENSOR_TO_DEVICE_FAILURE';

export function addGenericSensorToDevice(dispatch, selectedTypeOfSensor, deviceId, selectedSensorModelPath, selectedSensorTypeId, sensorName) {
    const action = {
        type: ADD_GENERIC_SENSOR_TO_DEVICE_STARTED,
        payload: {
            data: {
                selectedSensorTypeId, selectedSensorModelPath, selectedTypeOfSensor,
                deviceId, sensorName
            }
        }
    }
    dispatch(action);

    const success = () => {
        const action = addGenericSensorSuccess()
        dispatch(action)
    };

    const failure = (error) => {
        const action = addGenericSensorFailure(error)
        dispatch(action)
    };

    addGenericSensor(selectedTypeOfSensor, deviceId, selectedSensorModelPath, selectedSensorTypeId, sensorName, success, failure)
}

function addGenericSensorSuccess() {
    return {
        type: ADD_GENERIC_SENSOR_TO_DEVICE_SUCCESS,
        payload: "Generic sensor added successfully"
    }
}

function addGenericSensorFailure(err) {
    return {
        type: ADD_GENERIC_SENSOR_TO_DEVICE_FAILURE,
        payload: {
            error: err.message
        }
    }
}


export const ADD_GPS_SENSOR_TO_DEVICE_STARTED = 'ADD_GPS_SENSOR_TO_DEVICE_STARTED';
export const ADD_GPS_SENSOR_TO_DEVICE_SUCCESS = 'ADD_GPS_SENSOR_TO_DEVICE_SUCCESS';
export const ADD_GPS_SENSOR_TO_DEVICE_FAILURE = 'ADD_GPS_SENSOR_TO_DEVICE_FAILURE';

export function addGPSSensorToDevice(dispatch, selectedTypeOfSensor, deviceId, selectedSensorModelPath, selectedSensorTypeId, sensorName, latitude, longitude) {

    const action = {
        type: ADD_GPS_SENSOR_TO_DEVICE_STARTED,
        payload: {
            data: {
                selectedSensorTypeId, selectedSensorModelPath, selectedTypeOfSensor,
                deviceId, sensorName, latitude, longitude
            }
        }
    }
    dispatch(action);

    const success = () => {
        const action = addGPSSensorSuccess()
        dispatch(action)
    };

    const failure = (error) => {
        const action = addGPSSensorFailure(error)
        dispatch(action)
    };

    addGPSSensor(selectedTypeOfSensor, deviceId, selectedSensorModelPath, selectedSensorTypeId, sensorName, latitude, longitude, success, failure)
}

function addGPSSensorSuccess() {
    return {
        type: ADD_GPS_SENSOR_TO_DEVICE_SUCCESS,
        payload: "GPS sensor added successfully"
    }
}

function addGPSSensorFailure(err) {
    return {
        type: ADD_GPS_SENSOR_TO_DEVICE_FAILURE,
        payload: {
            error: err.message
        }
    }
}

export const ADD_DATE_SENSOR_TO_DEVICE_STARTED = 'ADD_DATE_SENSOR_TO_DEVICE_STARTED';
export const ADD_DATE_SENSOR_TO_DEVICE_SUCCESS = 'ADD_DATE_SENSOR_TO_DEVICE_SUCCESS';
export const ADD_DATE_SENSOR_TO_DEVICE_FAILURE = 'ADD_DATE_SENSOR_TO_DEVICE_FAILURE';

export function addDateSensorToDevice(dispatch, selectedTypeOfSensor, deviceId, selectedSensorModelPath, selectedSensorTypeId, sensorName, startDate, endDate) {

    const action = {
        type: ADD_DATE_SENSOR_TO_DEVICE_STARTED,
        payload: {
            data: {
                selectedSensorTypeId, selectedSensorModelPath, selectedTypeOfSensor,
                deviceId, sensorName, startDate, endDate
            }
        }
    }
    dispatch(action);

    const success = () => {
        const action = addDateSensorSuccess()
        dispatch(action)
    };

    const failure = (error) => {
        const action = addDateSensorFailure(error)
        dispatch(action)
    };

    addDateSensor(selectedTypeOfSensor, deviceId, selectedSensorModelPath, selectedSensorTypeId, sensorName, startDate, endDate, success, failure)
}

function addDateSensorSuccess() {
    return {
        type: ADD_DATE_SENSOR_TO_DEVICE_SUCCESS,
        payload: "Date sensor added successfully"
    }
}

function addDateSensorFailure(err) {
    return {
        type: ADD_DATE_SENSOR_TO_DEVICE_FAILURE,
        payload: {
            error: err.message
        }
    }
}


export const UPDATE_GENERIC_SENSOR_DATA = 'UPDATE_GENERIC_SENSOR_DATA';

export function updateGenericSensorData(formDispatch, sensorName) {
    const action = {
        type: UPDATE_GENERIC_SENSOR_DATA,
        payload: {
            sensorName: sensorName
        }
    }
    formDispatch(action);
}

export const UPDATE_LATITUDE_DATA = 'UPDATE_LATITUDE_DATA';

export function updateLatitudeData(formDispatch, latitude) {
    const action = {
        type: UPDATE_LATITUDE_DATA,
        payload: {
            latitude: latitude
        }
    }
    formDispatch(action);
}

export const UPDATE_LONGITUDE_DATA = 'UPDATE_LONGITUDE_DATA';

export function updateLongitudeData(formDispatch, longitude) {
    const action = {
        type: UPDATE_LONGITUDE_DATA,
        payload: {
            longitude: longitude
        }
    }
    formDispatch(action);
}

export const UPDATE_START_DATE_DATA = 'UPDATE_START_DATE_DATA';

export function updateStartDateData(formDispatch, startDate) {
    const action = {
        type: UPDATE_START_DATE_DATA,
        payload: {
            startDate: startDate
        }
    }
    formDispatch(action);
}


export const UPDATE_END_DATE_DATA = 'UPDATE_END_DATE_DATA';

export function updateEndDateData(formDispatch, endDate) {
    const action = {
        type: UPDATE_END_DATE_DATA,
        payload: {
            endDate: endDate
        }
    }
    formDispatch(action);
}

// ------------------------------ Types Of Sensor  ------------------------------//
export function updateSelectedTypeOfSensor(dispatch, selectedTypeOfSensor) {
    const action = {
        type: UPDATE_SELECTED_TYPE_OF_SENSOR,
        payload: {selectedTypeOfSensor}
    };
    dispatch(action);
}

// ------------------------------ Sensor Types  ------------------------------//
export function fetchSensorTypes(dispatch) {
    dispatch({type: FETCH_SENSOR_TYPES_STARTED});

    const success = (res) => dispatch(fetchSensorTypesSuccess(res));
    const failure = (err) => dispatch(fetchSensorTypesFailure(err.message));

    fetchSensorTypesFromServer(success, failure);
}

function fetchSensorTypesSuccess(sensorTypes) {
    return {
        type: FETCH_SENSOR_TYPES_SUCCESS,
        payload: {data: sensorTypes}
    };
}

function fetchSensorTypesFailure(message) {
    return {
        type: FETCH_SENSOR_TYPES_FAILURE,
        payload: {error: message}
    };
}

export function updateSensorTypeId(dispatch, selectedSensorType) {
    const action = {
        type: UPDATE_SELECTED_SENSOR_TYPE_ID,
        payload: {selectedSensorType}
    };
    dispatch(action);
}

// ------------------------------ Sensor Models  ------------------------------//
export function fetchSensorModels(dispatch, sensorTypeId) {
    dispatch({type: FETCH_SENSOR_MODELS_STARTED});

    const success = (res) => dispatch(fetchSensorModelsSuccess(res));
    const failure = (err) => dispatch(fetchSensorModelsFailure(err.message));

    fetchSensorModelsBySensorTypeIdFromServer(success, failure, sensorTypeId);
}

function fetchSensorModelsSuccess(sensorModels) {
    return {
        type: FETCH_SENSOR_MODELS_SUCCESS,
        payload: {data: sensorModels}
    };
}

function fetchSensorModelsFailure(message) {
    return {
        type: FETCH_SENSOR_MODELS_FAILURE,
        payload: {error: message}
    };
}

export function updateSelectedSensorModel(dispatch, selectedSensorModel) {
    const action = {
        type: UPDATE_SELECTED_SENSOR_MODEL,
        payload: {selectedSensorModel}
    };
    dispatch(action);
}


export const UPDATE_SELECTED_SENSOR_MODEL_NAME = 'UPDATE_SELECTED_SENSOR_MODEL_NAME';

export function updateSelectedSensorModelName(dispatch, selectedSensorModel) {
    const action = {
        type: UPDATE_SELECTED_SENSOR_MODEL_NAME,
        payload: {
            selectedSensorModelName: selectedSensorModel.sensorModelName
        }
    }
    dispatch(action);
}


export const UPDATE_SELECTED_SENSOR_MODEL_PATH = 'UPDATE_SELECTED_SENSOR_MODEL_PATH';

export function updateSelectedSensorModelPath(dispatch, selectedSensorModel) {
    const action = {
        type: UPDATE_SELECTED_SENSOR_MODEL_PATH,
        payload: {
            selectedSensorModelPath: selectedSensorModel.modelPath
        }
    }
    dispatch(action);
}


// ------------------------------ Actuators ------------------------------//
export function fetchActuators(dispatch, deviceId) {
    dispatch({type: FETCH_ACTUATORS_STARTED});

    const success = (data) => dispatch({type: FETCH_ACTUATORS_SUCCESS, payload: {actuators: data}});
    const failure = (error) => dispatch({type: FETCH_ACTUATORS_FAILURE, payload: {error: error}});

    fetchActuatorsByDeviceId(success, failure, deviceId);
}

export function fetchSensors(dispatch, deviceId) {
    dispatch({type: FETCH_SENSORS_STARTED});

    const success = (data) => dispatch({type: FETCH_SENSORS_SUCCESS, payload: {sensors: data}});
    const failure = (error) => dispatch({type: FETCH_SENSORS_FAILURE, payload: {error: error}});

    fetchSensorsByDeviceId(success, failure, deviceId);

}

// ------------------------------ Type Of Actuator ------------------------------//
export const UPDATE_SELECTED_TYPE_OF_ACTUATOR = 'UPDATE_SELECTED_TYPE_OF_ACTUATOR';

export function updateSelectedTypeOfActuator(dispatch, selectedTypeOfActuator) {
    const action = {
        type: UPDATE_SELECTED_TYPE_OF_ACTUATOR,
        payload: {
            selectedTypeOfActuator: selectedTypeOfActuator
        }
    }
    dispatch(action);
}

// ------------------------------ Actuator Types ------------------------------//

export function fetchActuatorTypes(dispatch) {
    dispatch({type: FETCH_ACTUATOR_TYPES_STARTED});

    const success = (res) => dispatch(fetchActuatorTypesSuccess(res));
    const failure = (err) => dispatch(fetchActuatorTypesFailure(err.message));

    fetchActuatorTypesFromServer(success, failure);
}

function fetchActuatorTypesSuccess(actuatorTypes) {
    return {
        type: FETCH_ACTUATOR_TYPES_SUCCESS,
        payload: {data: actuatorTypes}
    };
}

function fetchActuatorTypesFailure(message) {
    return {
        type: FETCH_ACTUATOR_TYPES_FAILURE,
        payload: {error: message}
    };

}


export function updateActuatorTypeId(dispatch, selectedActuatorType) {
    const action = {
        type: UPDATE_SELECTED_ACTUATOR_TYPE_ID,
        payload: {selectedActuatorType}
    };
    dispatch(action);
}

// ------------------------------ Actuator Models ------------------------------//

export function fetchActuatorModels(dispatch, actuatorTypeId) {
    dispatch({type: FETCH_ACTUATOR_MODELS_STARTED});

    const success = (res) => dispatch(fetchActuatorModelsSuccess(res));
    const failure = (err) => dispatch(fetchActuatorModelsFailure(err.message));

    fetchActuatorModelsByActuatorTypeIdFromServer(success, failure, actuatorTypeId);
}

function fetchActuatorModelsSuccess(actuatorModels) {
    return {
        type: FETCH_ACTUATOR_MODELS_SUCCESS,
        payload: {data: actuatorModels}
    };
}

function fetchActuatorModelsFailure(message) {
    return {
        type: FETCH_ACTUATOR_MODELS_FAILURE,
        payload: {error: message}
    };
}


export const UPDATE_SELECTED_ACTUATOR_MODEL_NAME = 'UPDATE_SELECTED_ACTUATOR_MODEL_NAME';

export function updateSelectedActuatorModelName(dispatch, actuatorModel) {
    const action = {
        type: UPDATE_SELECTED_ACTUATOR_MODEL_NAME,
        payload: {
            selectedActuatorModelName: actuatorModel.actuatorModelName
        }
    }
    dispatch(action);
}

export const UPDATE_SELECTED_ACTUATOR_MODEL_PATH = 'UPDATE_SELECTED_ACTUATOR_MODEL_PATH';

export function updateSelectedActuatorModelPath(dispatch, actuatorModel) {
    const action = {
        type: UPDATE_SELECTED_ACTUATOR_MODEL_PATH,
        payload: {
            selectedActuatorModelPath: actuatorModel.actuatorModelPath
        }
    }
    dispatch(action);

}

// ------------------------------ Add actuators ------------------------------//
export const UPDATE_GENERIC_ACTUATOR_DATA = 'UPDATE_GENERIC_ACTUATOR_DATA';

export function updateGenericActuatorData(formDispatch, actuatorName) {
    const action = {
        type: UPDATE_GENERIC_ACTUATOR_DATA,
        payload: {
            actuatorName: actuatorName
        }
    }
    formDispatch(action);

}

export const UPDATE_MIN_LIMIT_DATA = 'UPDATE_MIN_LIMIT_DATA';

export function updateMinLimitData(formDispatch, minLimit) {
    const action = {
        type: UPDATE_MIN_LIMIT_DATA,
        payload: {
            minLimit: minLimit
        }
    }
    formDispatch(action);
}

export const UPDATE_MAX_LIMIT_DATA = 'UPDATE_MAX_LIMIT_DATA';

export function updateMaxLimitData(formDispatch, maxLimit) {
    const action = {
        type: UPDATE_MAX_LIMIT_DATA,
        payload: {
            maxLimit: maxLimit
        }
    }
    formDispatch(action);
}


export const ADD_GENERIC_ACTUATOR_TO_DEVICE_STARTED = 'ADD_GENERIC_ACTUATOR_TO_DEVICE_STARTED';
export const ADD_GENERIC_ACTUATOR_TO_DEVICE_SUCCESS = 'ADD_GENERIC_ACTUATOR_TO_DEVICE_SUCCESS';
export const ADD_GENERIC_ACTUATOR_TO_DEVICE_FAILURE = 'ADD_GENERIC_ACTUATOR_TO_DEVICE_FAILURE';

export function addGenericActuatorToDevice(dispatch, selectedTypeOfActuator, deviceId, selectedActuatorModelPath, selectedActuatorTypeId, actuatorName) {
    const action = {
        type: ADD_GENERIC_ACTUATOR_TO_DEVICE_STARTED,
        payload: {
            data: {selectedTypeOfActuator, deviceId, selectedActuatorModelPath, selectedActuatorTypeId, actuatorName}
        }
    }
    dispatch(action);

    const success = () => {
        const action = addGenericActuatorSuccess()
        dispatch(action)
    };

    const failure = (error) => {
        const action = addGenericActuatorFailure(error)
        dispatch(action)
    };
    addGenericActuator(selectedTypeOfActuator, deviceId, selectedActuatorModelPath, selectedActuatorTypeId, actuatorName, success, failure)
}

function addGenericActuatorSuccess() {
    return {
        type: ADD_GENERIC_ACTUATOR_TO_DEVICE_SUCCESS,
        payload: "Generic actuator added successfully"
    }
}

function addGenericActuatorFailure(err) {
    return {
        type: ADD_GENERIC_ACTUATOR_TO_DEVICE_FAILURE,
        payload: {
            error: err.message
        }
    }
}

export const ADD_DECIMAL_ACTUATOR_TO_DEVICE_STARTED = 'ADD_DECIMAL_ACTUATOR_TO_DEVICE_STARTED';
export const ADD_DECIMAL_ACTUATOR_TO_DEVICE_SUCCESS = 'ADD_DECIMAL_ACTUATOR_TO_DEVICE_SUCCESS';
export const ADD_DECIMAL_ACTUATOR_TO_DEVICE_FAILURE = 'ADD_DECIMAL_ACTUATOR_TO_DEVICE_FAILURE';

export function addDecimalActuatorToDevice(dispatch, selectedTypeOfActuator, deviceId, selectedActuatorModelPath, selectedActuatorTypeId, actuatorName, minLimit, maxLimit) {
    const action = {
        type: ADD_DECIMAL_ACTUATOR_TO_DEVICE_STARTED,
        payload: {
            data: {
                selectedTypeOfActuator,
                deviceId,
                selectedActuatorModelPath,
                selectedActuatorTypeId,
                actuatorName,
                minLimit,
                maxLimit
            }
        }
    }
    dispatch(action)

    const success = () => {
        const action = addDecimalActuatorSuccess()
        dispatch(action)
    };

    const failure = (error) => {
        const action = addDecimalActuatorFailure(error)
        dispatch(action)
    };

    addDecimalActuator(selectedTypeOfActuator, deviceId, selectedActuatorModelPath, selectedActuatorTypeId, actuatorName, minLimit, maxLimit, success, failure)
}

function addDecimalActuatorSuccess() {
    return {
        type: ADD_DECIMAL_ACTUATOR_TO_DEVICE_SUCCESS,
        payload: "Decimal actuator added successfully"
    }
}

function addDecimalActuatorFailure(err) {
    return {
        type: ADD_DECIMAL_ACTUATOR_TO_DEVICE_FAILURE,
        payload: {
            error: err.message
        }

    }
}


export const ADD_INTEGER_ACTUATOR_TO_DEVICE_STARTED = 'ADD_INTEGER_ACTUATOR_TO_DEVICE_STARTED';
export const ADD_INTEGER_ACTUATOR_TO_DEVICE_SUCCESS = 'ADD_INTEGER_ACTUATOR_TO_DEVICE_SUCCESS';
export const ADD_INTEGER_ACTUATOR_TO_DEVICE_FAILURE = 'ADD_INTEGER_ACTUATOR_TO_DEVICE_FAILURE';

export function addIntegerActuatorToDevice(dispatch, selectedTypeOfActuator, deviceId, selectedActuatorModelPath, selectedActuatorTypeId, actuatorName, minLimit, maxLimit) {
    const action = {
        type: ADD_INTEGER_ACTUATOR_TO_DEVICE_STARTED,
        payload: {
            data: {
                selectedTypeOfActuator,
                deviceId,
                selectedActuatorModelPath,
                selectedActuatorTypeId,
                actuatorName,
                minLimit,
                maxLimit
            }
        }
    }
    dispatch(action)

    const success = () => {
        const action = addIntegerActuatorSuccess()
        dispatch(action)
    };

    const failure = (error) => {
        const action = addIntegerActuatorFailure(error)
        dispatch(action)
    };

    addIntegerActuator(selectedTypeOfActuator, deviceId, selectedActuatorModelPath, selectedActuatorTypeId, actuatorName, minLimit, maxLimit, success, failure)
}

function addIntegerActuatorSuccess() {
    return {
        type: ADD_INTEGER_ACTUATOR_TO_DEVICE_SUCCESS,
        payload: "Integer actuator added successfully"
    }
}

function addIntegerActuatorFailure(err) {
    return {
        type: ADD_INTEGER_ACTUATOR_TO_DEVICE_FAILURE,
        payload: {
            error: err.message
        }

    }
}


export function deactivateDeviceFromServer(dispatch, deviceId) {
    dispatch({ type: DEACTIVATE_DEVICE_STARTED});

    const success = data => dispatch ({
        type: DEACTIVATE_DEVICE_SUCCESS,
        payload: {data, message: 'Device deactivated successfully'}
    });
    const failure = error => dispatch({
        type: DEACTIVATE_DEVICE_FAILURE, payload: {error: error.message}});

    deactivateDevice(success, failure, deviceId);
}

export const SAVE_CURRENT_DEVICE = 'SAVE_CURRENT_DEVICE';

export function saveCurrentDevice(dispatch, deviceId) {
    const action = {
        type: SAVE_CURRENT_DEVICE,
        payload: {
            deviceId: deviceId
        }
    }
    dispatch(action);



}