import React, { useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@mui/material';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import FormDataContext from "../context/FormDataContext.jsx";
import AppContext from "../context/AppContext.jsx";
import {
    addDateSensorToDevice, addDecimalActuatorToDevice,
    addGenericActuatorToDevice,
    addGenericSensorToDevice,
    addGPSSensorToDevice, addIntegerActuatorToDevice
} from "../context/Actions.jsx";

const SubmitButton = () => {
    const { formState, formDispatch } = useContext(FormDataContext);
    const { latitude, longitude, sensorName, startDate, endDate, actuatorName, minLimit, maxLimit } = formState;

    const { state } = useContext(AppContext);
    const { selectedTypeOfSensor, selectedSensorTypeId, currentDevice, selectedSensorModelPath, selectedTypeOfActuator, selectedActuatorTypeId, selectedActuatorModelPath } = state;
    const { deviceId } = currentDevice;

    const [open, setOpen] = useState(false);
    const [dialogMessage, setDialogMessage] = useState('');

    const navigate = useNavigate();

    const handleGoToLanding = () => {
        navigate('/');
    }

    const handleSuccess = (message) => {
        setDialogMessage(message);
        setOpen(true);
    };

    const handleFailure = (errorMessage) => {
        toast.error(<div>
            <strong>Failed to add sensor:</strong><br />
            {errorMessage}
        </div>, {
            position: "top-right",
            autoClose: 5000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
        });
    };

    const handleSubmit = () => {
        const successCallback = (data) => {
            handleSuccess("Sensor or actuator has been successfully added to the device. Details: " + JSON.stringify(data));
        };

        const failureCallback = (errorMessage) => {
            handleFailure(errorMessage);
        };

        if (selectedTypeOfSensor === 'dateSensor') {
            addDateSensorToDevice(formDispatch, selectedTypeOfSensor, deviceId, selectedSensorModelPath, selectedSensorTypeId, sensorName, startDate, endDate, successCallback, failureCallback);
        }
        if (selectedTypeOfSensor === 'gpsSensor') {
            addGPSSensorToDevice(formDispatch, selectedTypeOfSensor, deviceId, selectedSensorModelPath, selectedSensorTypeId, sensorName, latitude, longitude, successCallback, failureCallback);
        }
        if (selectedTypeOfSensor === 'genericSensor') {
            addGenericSensorToDevice(formDispatch, selectedTypeOfSensor, deviceId, selectedSensorModelPath, selectedSensorTypeId, sensorName, successCallback, failureCallback);
        }
        if (selectedTypeOfActuator === 'decimalActuator') {
            addDecimalActuatorToDevice(formDispatch, selectedTypeOfActuator, deviceId, selectedActuatorModelPath, selectedActuatorTypeId, actuatorName, minLimit, maxLimit, successCallback, failureCallback);
        }
        if (selectedTypeOfActuator === 'integerActuator') {
            addIntegerActuatorToDevice(formDispatch, selectedTypeOfActuator, deviceId, selectedActuatorModelPath, selectedActuatorTypeId, actuatorName, minLimit, maxLimit, successCallback, failureCallback);
        }
        if (selectedTypeOfActuator === 'genericActuator') {
            addGenericActuatorToDevice(formDispatch, selectedTypeOfActuator, deviceId, selectedActuatorModelPath, selectedActuatorTypeId, actuatorName, successCallback, failureCallback);
        }
    };

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <div>
            <Button variant="contained" color="success" onClick={handleSubmit}>
                Submit
            </Button>
            <Dialog
                open={open}
                onClose={handleClose}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
                fullWidth={true}
                maxWidth="xl" // You can set this to 'sm', 'md', 'lg', or 'xl' based on your needs
            >
                <DialogTitle id="alert-dialog-title">{"Success"}</DialogTitle>
                <DialogContent>
                    <DialogContentText id="alert-dialog-description" style={{ whiteSpace: 'pre-wrap' }}>
                        {dialogMessage}
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleGoToLanding} color="primary">
                        Go to Landing Page
                    </Button>
                    <Button onClick={handleClose} color="primary" autoFocus>
                        OK
                    </Button>
                </DialogActions>
            </Dialog>
            <ToastContainer />
        </div>
    );
};

export default SubmitButton;
