import AppContext from "../context/AppContext.jsx";
import React, {useContext, useEffect, useState} from "react";
import SubmitButton from "../components/SubmitButton.jsx";
import FormDataContext from "../context/FormDataContext.jsx";
import TypesOfActuator from "../components/TypesOfActuator.jsx";
import ActuatorTypes from "../components/ActuatorTypes.jsx";
import ActuatorModels from "../components/ActuatorModels.jsx";
import ConfigureActuator from "../components/ConfigureActuator.jsx";
import {Button} from "@mui/material";
import {useNavigate} from "react-router-dom";
import {resetChanges} from "../context/Actions.jsx";

function AddActuatorPage() {
    const {state, dispatch} = useContext(AppContext);
    const {formState, formDispatch} = useContext(FormDataContext);
    const {actuatorName} = formState;
    const {selectedActuatorTypeId} = state;
    const {selectedTypeOfActuator} = state;
    const {selectedActuatorModelName} = state;

    const navigate = useNavigate();

    const handleGoBack = () => {
        resetChanges (dispatch)
        navigate(-1);
    };

    const handleBackToHome = () => {
        resetChanges (dispatch)
        navigate('/');
    };


    if (selectedTypeOfActuator === "") {
        return (
            <div>
                <h1>Let's add an Actuator</h1>
                <h3>Select a Type of Actuator</h3>
                <TypesOfActuator/>
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

    if (selectedTypeOfActuator !== "" && selectedActuatorTypeId === "") {
        return (
            <div>

                <h1>Let's add an Actuator</h1>
                <TypesOfActuator/>
                <h3> Select the Actuator Type </h3>
                <ActuatorTypes/>
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

    if (selectedTypeOfActuator !== "" && selectedActuatorTypeId !== "" && selectedActuatorModelName === "") {
        return (
            <div>
                <h1>Let's add an Actuator</h1>
                <TypesOfActuator/>
                <ActuatorTypes/>
                <h3>Select the Actuator Model</h3>
                <ActuatorModels/>
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

    if (selectedTypeOfActuator !== "" && selectedActuatorTypeId !== "" && selectedActuatorModelName !== "") {
        return (
            <div>

                <h1>Let's add an Actuator</h1>
                <TypesOfActuator/>
                <ActuatorTypes/>
                <ActuatorModels/>
                <h3>Finish the Configuration </h3>
                <ConfigureActuator/>
                {actuatorName !== "" && <SubmitButton/>}
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

export default AddActuatorPage;