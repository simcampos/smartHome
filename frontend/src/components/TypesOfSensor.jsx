import React, {useContext, useEffect} from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import AppContext from "../context/AppContext.jsx";
import {updateSelectedTypeOfSensor} from "../context/Actions.jsx";

const TypesOfSensor = () => {
    const {state, dispatch} = useContext(AppContext);
    const {typesOfSensor} = state;
    const {selectedTypeOfSensor} = state;

    const sensorTypeMapping = {
        "gpsSensor": "GPS Sensor",
        "dateSensor": "Date Sensor",
        "genericSensor": "Generic Sensor"
    };

    const handleTypeChange = (event) => {
        updateSelectedTypeOfSensor(dispatch, event.target.value);
    };

    return (
        <Box sx={{ padding: 2 }}>
            <FormControl fullWidth variant="outlined" margin="normal">
                <InputLabel id="type-label">Select Type of Sensor</InputLabel>
                <Select
                    labelId="type-label"
                    id="type"
                    value={selectedTypeOfSensor}
                    onChange={handleTypeChange}
                    label="Select Type"
                >
                    <MenuItem value="">
                        <em>None</em>
                    </MenuItem>
                    {typesOfSensor.map((type) => (
                        <MenuItem key={type} value={type}>
                            {sensorTypeMapping[type]}
                        </MenuItem>
                    ))}
                </Select>
            </FormControl>
        </Box>
    );
};

export default TypesOfSensor;