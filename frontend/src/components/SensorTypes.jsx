import React, {useContext, useEffect} from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import AppContext from "../context/AppContext.jsx";
import {fetchSensorTypes, updateSensorTypeId} from "../context/Actions.jsx";


const SensorTypes = () => {
    const {state, dispatch} = useContext(AppContext);
    const {sensorTypes} = state;
    const {loading, error, data } = sensorTypes;
    const {selectedSensorTypeId} = state;

    const {selectedTypeOfSensor} = state;

    useEffect(() => {
        fetchSensorTypes(dispatch);

    }, [dispatch, selectedTypeOfSensor]);

    const filterSensorTypes = (selectedTypeOfSensor) => {
        if (selectedTypeOfSensor === 'gpsSensor') {
            return data.filter(sensorType =>
                sensorType.sensorTypeID === 'SunriseTime' || sensorType.sensorTypeID === 'SunsetTime'
            );
        } else if (selectedTypeOfSensor === 'dateSensor') {
            return data.filter(sensorType =>
                sensorType.sensorTypeID === 'ElectricConsumptionWh'
            );
        } else {
            return data.filter(sensorType =>
                sensorType.sensorTypeID !== 'SunriseTime' &&
                sensorType.sensorTypeID !== 'SunsetTime' &&
                sensorType.sensorTypeID !== 'ElectricConsumptionWh'
            );
        }
    };

    const filteredSensorTypes = filterSensorTypes(selectedTypeOfSensor);

    const handleTypeChange = (event) => {
        updateSensorTypeId(dispatch, event.target.value);
    }


    return (
        <Box sx={{ padding: 2 }}>
            <FormControl fullWidth variant="outlined" margin="normal">
                <InputLabel id="type-label">Select Sensor Type</InputLabel>
                <Select
                    labelId="type-label"
                    id="type"
                    value={selectedSensorTypeId}
                    onChange={handleTypeChange}
                    label="Select Type"
                >
                    <MenuItem value="">
                        <em>None</em>
                    </MenuItem>
                    {filteredSensorTypes.map((sensorType) => (
                        <MenuItem key={sensorType.sensorTypeID} value={sensorType.sensorTypeID}>
                            {sensorType.sensorTypeID}
                        </MenuItem>
                    ))}
                </Select>
            </FormControl>
        </Box>
    );
};

export default SensorTypes;