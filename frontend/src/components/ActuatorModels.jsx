import React, {useContext, useEffect} from 'react';
import AppContext from "../context/AppContext.jsx";
import {
    fetchActuatorModels,
    updateSelectedActuatorModelName,
    updateSelectedActuatorModelPath
} from "../context/Actions.jsx";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import InputLabel from "@mui/material/InputLabel";
import FormControl from "@mui/material/FormControl";
import {Box} from "@mui/material";



const ActuatorModels = () => {
    const {state, dispatch} = useContext(AppContext)
    const {actuatorModels} = state;
    const {loading, error, data} = actuatorModels;

    const {selectedActuatorTypeId} = state;
    const {selectedTypeOfActuator} = state;

    const {selectedActuatorModelName} = state;
    const {selectedActuatorModelPath} = state;

    useEffect(() => {
        fetchActuatorModels(dispatch, selectedActuatorTypeId);
    }, [dispatch, selectedActuatorTypeId, selectedTypeOfActuator]);

    const handleTypeChange = (actuatorModel) => {
        updateSelectedActuatorModelName(dispatch, actuatorModel);
        updateSelectedActuatorModelPath(dispatch, actuatorModel);
    }

    return (
        <Box sx={{ padding: 2 }}>
            <FormControl fullWidth variant="outlined" margin="normal">
                <InputLabel id="type-label">Select Actuator Models</InputLabel>
                <Select
                    labelId="type-label"
                    id="model"
                    value={selectedActuatorModelName.actuatorModelName}
                    onChange={(event) => handleTypeChange(data.find(item => item.actuatorModelName === event.target.value))}
                    label="Select Model"
                >
                    <MenuItem value="">
                        <em>None</em>
                    </MenuItem>
                    {data.map((actuatorModel) => (
                        <MenuItem key={actuatorModel.actuatorModelPath} value={actuatorModel.actuatorModelName}>
                            {actuatorModel.actuatorModelName}
                        </MenuItem>
                    ))}
                </Select>
            </FormControl>
        </Box>
    );
};

export default ActuatorModels;