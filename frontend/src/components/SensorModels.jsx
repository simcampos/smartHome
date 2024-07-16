import React, {useContext, useEffect} from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import AppContext from "../context/AppContext.jsx";
import {fetchSensorModels, updateSelectedSensorModelName, updateSelectedSensorModelPath} from "../context/Actions.jsx";


const SensorModels = () => {
    const {state, dispatch} = useContext(AppContext);
    const {sensorModels} = state;
    const {loading, error, data } = sensorModels;

    const {selectedSensorTypeId} = state;
    const {selectedTypeOfSensor} = state;

    const {selectedSensorModelName} = state;
    const {selectedSensorModelPath} = state;


    useEffect(() => {
        fetchSensorModels(dispatch, selectedSensorTypeId);
    }, [dispatch, selectedSensorTypeId, selectedTypeOfSensor]);

    const handleTypeChange = (sensorModel) => {
        updateSelectedSensorModelName(dispatch, sensorModel);
        updateSelectedSensorModelPath(dispatch, sensorModel)
    }


    return (
        <Box sx={{ padding: 2 }}>
            <FormControl fullWidth variant="outlined" margin="normal">
                <InputLabel id="type-label">Select Sensor Models</InputLabel>
                <Select
                    labelId="type-label"
                    id="model"
                    value={selectedSensorModelName.sensorModelName}
                    onChange={(event) => handleTypeChange(data.find(item => item.sensorModelName === event.target.value))}
                    label="Select Model"
                >
                    <MenuItem value="">
                        <em>None</em>
                    </MenuItem>
                    {data.map((sensorModel) => (
                        <MenuItem key={sensorModel.modelPath} value={sensorModel.sensorModelName}>
                            {sensorModel.sensorModelName}
                        </MenuItem>
                    ))}
                </Select>
            </FormControl>
        </Box>
    );
};

export default SensorModels;