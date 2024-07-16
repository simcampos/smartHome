import AppContext from "../context/AppContext.jsx";
import React, {useContext, useState} from "react";
import {TextField, Button} from "@mui/material";
import Box from "@mui/material/Box";

import FormDataContext from "../context/FormDataContext.jsx";
import {
    updateMaxLimitData, updateGenericActuatorData, updateMinLimitData, resetChanges,
} from "../context/Actions.jsx";


const ConfigureActuator = () => {
    const {state, dispatch} = useContext(AppContext);
    const {selectedTypeOfActuator, selectedActuatorTypeId, currentDevice, selectedActuatorModelPath} = state;
    const {deviceId} = currentDevice;

    const {formState, formDispatch} = useContext(FormDataContext);
    const {minLimit, maxLimit, actuatorName} = formState;
    const [errors, setErrors] = useState({});

    const validateFields = () => {
        const newErrors = {};
        if (actuatorName === "") newErrors.actuatorName = "Actuator Name is required";
        if (selectedTypeOfActuator === 'decimalActuator') {
            if (minLimit === "") newErrors.minLimit = "Minimum Limit is required";
            if (maxLimit === "") newErrors.maxLimit = "Maximum Limit is required";
        }
        if (selectedTypeOfActuator === 'integerActuator') {
            if (minLimit === "") newErrors.minLimit = "Minimum Limit is required";
            if (maxLimit === "") newErrors.maxLimit = "Maximum Limit is required";
        }
        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };


    const handleInputChange = (e) => {
        validateFields();
        updateGenericActuatorData(formDispatch, e.target.value)
    };


    const handleMinLimitChangeInteger = (e) => {
        const value = e.target.value;
        if (/^-?\d*$/.test(value)) { // This regex will only match integer numbers
            updateMinLimitData(formDispatch, value)
        }
    };

    const handleMaxLimitChangeInteger = (e) => {
        const value = e.target.value;
        if (/^-?\d*$/.test(value)) { // This regex will only match integer numbers
            updateMaxLimitData(formDispatch, value);
        }
    };

    const handleMinLimitChangeDecimal = (e) => {
        const value = e.target.value;
        if (/^-?\d*\.?\d*$/.test(value)) { // This regex will match both integer and decimal numbers
            updateMinLimitData(formDispatch, value)
        }
    };

    const handleMaxLimitChangeDecimal = (e) => {
        const value = e.target.value;
        if (/^-?\d*\.?\d*$/.test(value)) { // This regex will match both integer and decimal numbers
            updateMaxLimitData(formDispatch, value)
        }
    };


    const filterActuatorsConfiguration = (selectedTypeOfActuator) => {
        if (selectedTypeOfActuator === 'decimalActuator') {
            return (
                <Box
                    component="form"
                    sx={{'& > :not(style)': {m: 1, width: '25ch'}}}
                    noValidate
                    autoComplete="off"
                >
                    <TextField
                        id="minLimit"
                        label="Minimum Limit"
                        variant="outlined"
                        value={minLimit}
                        onChange={handleMinLimitChangeDecimal}
                        required
                        error={!!errors.minLimit}
                        helperText={errors.minLimit}
                    />
                    <TextField
                        id="maxLimit"
                        label="Maximum Limit"
                        variant="outlined"
                        value={maxLimit}
                        onChange={handleMaxLimitChangeDecimal}
                        required
                        error={!!errors.maxLimit}
                        helperText={errors.maxLimit}
                    />
                    <TextField
                        id="actuatorName"
                        label="Actuator Name"
                        variant="outlined"
                        value={actuatorName}
                        onChange={handleInputChange}
                        required
                        error={!!errors.actuatorName}
                        helperText={errors.actuatorName}
                    />
                </Box>
            );
        } else if (selectedTypeOfActuator === 'integerActuator') {
            return (
                <Box
                    component="form"
                    sx={{'& > :not(style)': {m: 1, width: '25ch'}}}
                    noValidate
                    autoComplete="off"
                >
                    <TextField
                        id="minLimit"
                        label="Minimum Limit"
                        variant="outlined"
                        value={minLimit}
                        onChange={handleMinLimitChangeInteger}
                        required
                        error={!!errors.minLimit}
                        helperText={errors.minLimit}
                    />
                    <TextField
                        id="maxLimit"
                        label="Maximum Limit"
                        variant="outlined"
                        value={maxLimit}
                        onChange={handleMaxLimitChangeInteger}
                        required
                        error={!!errors.maxLimit}
                        helperText={errors.maxLimit}
                    />
                    <TextField
                        id="actuatorName"
                        label="Actuator Name"
                        variant="outlined"
                        value={actuatorName}
                        onChange={handleInputChange}
                        required
                        error={!!errors.actuatorName}
                        helperText={errors.actuatorName}
                    />
                </Box>
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
                        id="actuatorName"
                        label="Actuator Name"
                        variant="outlined"
                        value={actuatorName}
                        onChange={handleInputChange}
                        required
                        error={!!errors.actuatorName}
                        helperText={errors.actuatorName}
                    />
                </Box>
            );
        }
    };

    return (
        <div>
            {filterActuatorsConfiguration(selectedTypeOfActuator)}
        </div>
    );
};

export default ConfigureActuator;
