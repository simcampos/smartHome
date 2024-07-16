import {configureAndFetchTemperature, fetchTemperatureFromWS} from '../services/ServiceWS.jsx';

export const FETCH_TEMPERATURE_STARTED = 'FETCH_TEMPERATURE_STARTED';
export const FETCH_TEMPERATURE_SUCCESS = 'FETCH_TEMPERATURE_SUCCESS';
export const FETCH_TEMPERATURE_FAILURE = 'FETCH_TEMPERATURE_FAILURE';

export function fetchTemperature(dispatch) {
    const action = {
        type: FETCH_TEMPERATURE_STARTED
    }
    dispatch(action);
    const success = (res) => {
        const action = fetchTemperatureSuccess(res);
        dispatch(action);
    };
    const failure = (err) => {
        const action = fetchTemperatureFailure(err.message);
        dispatch(action);
    };

    fetchTemperatureFromWS(success, failure);
}

function fetchTemperatureSuccess(temperature) {
    return {
        type: FETCH_TEMPERATURE_SUCCESS,
        payload: {
            data: temperature
        }
    }
}

function fetchTemperatureFailure(message) {
    return {
        type: FETCH_TEMPERATURE_FAILURE,
        payload: {
            error: message
        }
    }
}

export function initializeTemperatureFetching(dispatch) {
    const {clearTimers} = configureAndFetchTemperature(dispatch, fetchTemperature);

    // Return the cleanup function
    return clearTimers;
}