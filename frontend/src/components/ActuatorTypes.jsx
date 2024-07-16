import React, {useContext, useEffect} from 'react';
import AppContext from "../context/AppContext.jsx";
import { fetchActuatorTypes, updateActuatorTypeId} from "../context/Actions.jsx";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import InputLabel from "@mui/material/InputLabel";
import FormControl from "@mui/material/FormControl";
import {Box} from "@mui/material";

const ActuatorTypes = () => {
    const {state, dispatch} = useContext(AppContext);
    const {actuatorTypes} = state;
    const {loading, error, data} = actuatorTypes;
    const {selectedActuatorTypeId} = state;

    const {selectedTypeOfActuator} = state;

    useEffect(() => {
        fetchActuatorTypes(dispatch);
    }, [dispatch, selectedTypeOfActuator]);

    const filterActuatorTypes = (selectedTypeOfActuator) => {
        if (selectedTypeOfActuator === 'decimalActuator'){
            return data.filter(actuatorType =>
            actuatorType.actuatorTypeID === 'SetDecimal'
            );
        } else if (selectedTypeOfActuator === 'integerActuator') {
            return data.filter(actuatorType =>
            actuatorType.actuatorTypeID === 'SetInteger'
            );
        } else {
            return data.filter(actuatorType =>
                actuatorType.actuatorTypeID !== 'SetDecimal' &&
                actuatorType.actuatorTypeID !== 'SetInteger'
            );
        }
    };

    const filteredActuatorTypes = filterActuatorTypes(selectedTypeOfActuator);

    const handleTypeChange = (event) => {
        updateActuatorTypeId(dispatch, event.target.value);
    }

    return (
        <Box sx={{ padding: 2 }}>
            <FormControl fullWidth variant="outlined" margin="normal">
                <InputLabel id="type-label">Select Actuator Type</InputLabel>
                <Select
                    labelId="type-label"
                    id="type"
                    value={selectedActuatorTypeId}
                    onChange={handleTypeChange}
                    label="Select Type"
                >
                    <MenuItem value="">
                        None
                    </MenuItem>
                    {filteredActuatorTypes.map((actuatorType) => (
                        <MenuItem key={actuatorType.actuatorTypeID} value={actuatorType.actuatorTypeID}>{actuatorType.actuatorTypeID}</MenuItem>
                    ))}
                </Select>
            </FormControl>
        </Box>
    );

};

export default ActuatorTypes;
