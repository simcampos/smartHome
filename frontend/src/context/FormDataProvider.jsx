import React, {useReducer, useState} from 'react';
import FormDataContext from './FormDataContext';
import reducer from "./Reducer.jsx";
import PropTypes from "prop-types";
import {Provider} from "./FormDataContext.jsx";


const initialState = {

    latitude: '',
    longitude: '',
    sensorName: '',
    startDate: null,
    endDate: null,
    actuatorName: '',
    minLimit: '',
    maxLimit: '',
};


const FormDataProvider = (props) => {
    const [formState, formDispatch] = useReducer(reducer, initialState);
    return (
        <Provider value={{
            formState,
            formDispatch
        }}>
            {props.children}
        </Provider>
    );
};

FormDataProvider.propTypes = {
    children: PropTypes.node,
};

export default FormDataProvider;