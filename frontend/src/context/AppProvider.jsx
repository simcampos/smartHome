import React, {useReducer} from 'react';
import PropTypes from "prop-types";
import {Provider} from './AppContext.jsx';
import reducer from './Reducer.jsx';

const initialState = {
    rooms: {
        loading: true,
        error: null,
        data: [],
    },
    room: { // Ensure room is defined in the initial state
        loading: false,
        error: null,
        data: null,
    },
    currentRoom: {
        roomId: null,
        roomName: null,
    },
    devices: {
        loading: false,
        error: null,
        data: [],
    },

    currentDevice: {
        deviceId: null,
        deviceName: null,
        deviceStatus: null,
    },

    temperature: {
        loading: true,
        error: null,
        data: null,
    },
    logs: {
        loading: true,
        error: null,
        data: [],
    },
    timeStart: '',
    timeEnd: '',
    typesOfSensor: ['gpsSensor', 'dateSensor', 'genericSensor'],
    selectedTypeOfSensor: "",
    sensorTypes: {
        loading: true,
        error: null,
        data: [],
    },
    selectedSensorTypeId: "",
    sensorModels: {
        loading: true,
        error: null,
        data: [],
    },

    position: {
        loading: true,
        error: null,
        data: null,
    },
    setBlindRollerStatus: {
        loading: false,
        error: null,
        message: null,
    },
    deviceTypes: {
        loading: true,
        error: null,
        data: [],
    },
    actuators: {
        loading: false,
        error: null,
        data: [],
    },

    sensors: {
        loading: false,
        error: null,
        data: [],
    },

    selectedSensorModelName: "",

    selectedSensorModelPath: "",
    addingSensor: {
        status: 'idle',
        error: null,
    },

    typesOfActuator : ['genericActuator', 'integerActuator', 'decimalActuator'],
    selectedTypeOfActuator: "",

    actuatorTypes: {
        loading: true,
        error: null,
        data: [],
    },

    selectedActuatorTypeId: "",

    actuatorModels: {
        loading: true,
        error: null,
        data: [],
    },

    selectedActuatorModelName: "",
    selectedActuatorModelPath: "",

    addingActuator: {
        status: 'idle',
        error: null,
    }
};


const AppProvider = (props) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <Provider value={{state, dispatch}}>
            {props.children}
        </Provider>
    );
};

AppProvider.propTypes = {
    children: PropTypes.node,
};

export default AppProvider;
