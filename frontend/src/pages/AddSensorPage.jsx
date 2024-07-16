import TypesOfSensor from "../components/TypesOfSensor.jsx";
import SensorTypes from "../components/SensorTypes.jsx";
import SensorModels from "../components/SensorModels.jsx";
import AppContext from "../context/AppContext.jsx";
import React, {useContext, useEffect, useState} from "react";
import ConfigureSensor from "../components/ConfigureSensor.jsx";
import SubmitButton from "../components/SubmitButton.jsx";
import FormDataContext from "../context/FormDataContext.jsx";
import {useNavigate} from "react-router-dom";
import {Button} from "@mui/material";
import {resetChanges} from "../context/Actions.jsx";

function AddSensorPage() {
    const {state, dispatch} = useContext(AppContext);
    const {formState, formDispatch} = useContext(FormDataContext);
    const {sensorName} = formState;
    const {selectedSensorTypeId} = state;
    const {selectedTypeOfSensor} = state;
    const {selectedSensorModelName} = state;
    const navigate = useNavigate();

    const handleGoBack = () => {
        resetChanges (dispatch)
        navigate(-1);
    };

    const handleBackToHome = () => {
        resetChanges (dispatch)
        navigate('/');
    };



    if (selectedTypeOfSensor === "") {
        return (
            <div>

                <h1>Let's add a Sensor</h1>
                <h3>Select a Type of Sensor</h3>
                <TypesOfSensor/>
                <Button
                    variant="contained"
                    color="secondary"
                    fullWidth
                    onClick={handleGoBack}
                    style={{marginTop: '20px'}}
                >
                    Back
                </Button>
                <Button
                    variant="contained"
                    color="secondary"
                    fullWidth
                    onClick={handleBackToHome}
                    style={{marginTop: '20px'}}
                >
                    Back to Home
                </Button>
            </div>
        );
    }

    if (selectedTypeOfSensor !== "" && selectedSensorTypeId === "") {
        return (
            <div>

                <h1>Let's add a Sensor</h1>
                <TypesOfSensor/>
                <h3> Select the Sensor Type </h3>
                <SensorTypes/>
                <Button
                    variant="contained"
                    color="secondary"
                    fullWidth
                    onClick={handleGoBack}
                    style={{marginTop: '20px'}}
                >
                    Back
                </Button>
                <Button
                    variant="contained"
                    color="secondary"
                    fullWidth
                    onClick={handleBackToHome}
                    style={{marginTop: '20px'}}
                >
                    Back to Home
                </Button>
            </div>
        );
    }

    if (selectedTypeOfSensor !== "" && selectedSensorTypeId !== "" && selectedSensorModelName === "") {
        return (
            <div>

                <h1>Let's add a Sensor</h1>
                <TypesOfSensor/>
                <SensorTypes/>
                <h3>Select the Sensor Model</h3>
                <SensorModels/>
                <Button
                    variant="contained"
                    color="secondary"
                    fullWidth
                    onClick={handleGoBack}
                    style={{marginTop: '20px'}}
                >
                    Back
                </Button>
                <Button
                    variant="contained"
                    color="secondary"
                    fullWidth
                    onClick={handleBackToHome}
                    style={{marginTop: '20px'}}
                >
                    Back to Home
                </Button>

            </div>
        );
    }

    if (selectedTypeOfSensor !== "" && selectedSensorTypeId !== "" && selectedSensorModelName !== "") {
        return (
            <div>
                <h1>Let's add a Sensor</h1>
                <TypesOfSensor/>
                <SensorTypes/>
                <SensorModels/>
                <h3>Finish the Configuration </h3>
                <ConfigureSensor/>
                {sensorName !== "" && <SubmitButton/>}
                <Button
                    variant="contained"
                    color="secondary"
                    fullWidth
                    onClick={handleGoBack}
                    style={{marginTop: '20px'}}
                >
                    Back
                </Button>
                <Button
                    variant="contained"
                    color="secondary"
                    fullWidth
                    onClick={handleBackToHome}
                    style={{marginTop: '20px'}}
                >
                    Back to Home
                </Button>
            </div>
        );
    }


}

export default AddSensorPage;