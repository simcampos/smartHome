import AppContext from "../context/AppContext.jsx";
import React, {useContext, useState} from "react";
import {TextField, Button} from "@mui/material";
import Box from "@mui/material/Box";
import {DemoContainer} from '@mui/x-date-pickers/internals/demo';
import {AdapterDayjs} from '@mui/x-date-pickers/AdapterDayjs';
import {LocalizationProvider} from '@mui/x-date-pickers/LocalizationProvider';
import {DateTimePicker} from '@mui/x-date-pickers/DateTimePicker';
import FormDataContext from "../context/FormDataContext.jsx";
import dayjs from 'dayjs';
import {
    updateEndDateData,
    updateGenericSensorData,
    updateLatitudeData,
    updateLongitudeData, updateStartDateData
} from "../context/Actions.jsx";


const ConfigureSensor = () => {
    const {state} = useContext(AppContext);
    const {selectedTypeOfSensor, selectedSensorTypeId, currentDevice, selectedSensorModelPath} = state;
    const {deviceId} = currentDevice;

    const {formState, formDispatch} = useContext(FormDataContext);
    const {latitude, longitude, sensorName, startDate, endDate} = formState;
    const [errors, setErrors] = useState({});

    const validateFields = () => {
        const newErrors = {};
        if (sensorName === "") newErrors.sensorName = "Sensor Name is required";
        if (selectedTypeOfSensor === 'gpsSensor') {
            if (latitude === "") newErrors.latitude = "Latitude is required";
            if (longitude === "") newErrors.longitude = "Longitude is required";
        }
        if (selectedTypeOfSensor === 'dateSensor') {
            if (!startDate ) newErrors.startDate = "Start Date is required";
            if (!endDate ) newErrors.endDate = "End Date is required";
        }
        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };


    const handleInputChange = (e) => {
        validateFields();
        updateGenericSensorData(formDispatch, e.target.value)
    };


    const handleLatitudeChange = (e) => {
        const value = e.target.value;
        if (/^-?\d*\.?\d*$/.test(value)) {
            updateLatitudeData(formDispatch, value)
        }

    }

    const handleLongitudeChange = (e) => {
        const value = e.target.value;
        if (/^-?\d*\.?\d*$/.test(value)) {
            updateLongitudeData(formDispatch, value);
        }
    };

    const handleStartDateChange = (date) => {
        if (date && date.isValid()) {
            updateStartDateData(formDispatch, date);
        }
    }

    const handleEndDateChange = (date) => {
        if (date && date.isValid()) {
            updateEndDateData(formDispatch, date);
        }
    }


    const filterSensorsConfiguration = (selectedTypeOfSensor) => {
        if (selectedTypeOfSensor === 'gpsSensor') {
            return (
                <Box
                    component="form"
                    sx={{'& > :not(style)': {m: 1, width: '25ch'}}}
                    noValidate
                    autoComplete="off"
                >
                    <TextField
                        id="latitude"
                        label="Latitude"
                        variant="outlined"
                        value={latitude}
                        onChange={handleLatitudeChange}
                        required
                        error={!!errors.latitude}
                        helperText={errors.latitude}
                    />
                    <TextField
                        id="longitude"
                        label="Longitude"
                        variant="outlined"
                        value={longitude}
                        onChange={handleLongitudeChange}
                        required
                        error={!!errors.longitude}
                        helperText={errors.longitude}
                    />
                    <TextField
                        id="sensorName"
                        label="Sensor Name"
                        variant="outlined"
                        value={sensorName}
                        onChange={handleInputChange}
                        required
                        error={!!errors.sensorName}
                        helperText={errors.sensorName}
                    />
                </Box>
            );
        } else if (selectedTypeOfSensor === 'dateSensor') {
            return (
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DemoContainer components={['DateTimePicker']}>
                        <DateTimePicker
                            label="Start Date"
                            value={startDate ? dayjs(startDate) : null}
                            onChange={handleStartDateChange}
                            required
                            error={!!errors.startDate}
                            helperText={errors.startDate}
                        />
                        <DateTimePicker
                            label="End Date"
                            value={endDate ? dayjs(endDate) : null}
                            onChange={handleEndDateChange}
                            required
                            error={!!errors.endDate}
                            helperText={errors.endDate}
                        />
                    </DemoContainer>
                    <Box
                        component="form"
                        sx={{'& > :not(style)': {m: 1, width: '25ch'}}}
                        noValidate
                        autoComplete="off"
                    >
                        <TextField
                            id="sensorName"
                            label="Sensor Name"
                            variant="outlined"
                            value={sensorName}
                            onChange={handleInputChange}
                            required
                            error={!!errors.sensorName}
                            helperText={errors.sensorName}
                        />
                    </Box>
                </LocalizationProvider>
            );
        } else {
            return (
                <Box
                    component="form"
                    sx={{'& > :not(style)': {m: 1, width: '25ch'}}}
                    noValidate
                    autoComplete="off"
                >
                    <TextField
                        id="sensorName"
                        label="Sensor Name"
                        variant="outlined"
                        value={sensorName}
                        onChange={handleInputChange}
                        required
                        error={!!errors.sensorName}
                        helperText={errors.sensorName}
                    />
                </Box>
            );
        }
    };

    return (
        <div>
            {filterSensorsConfiguration(selectedTypeOfSensor)}
        </div>
    );
};

export default ConfigureSensor;
