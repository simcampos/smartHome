import React, {useContext} from "react";
import AppContext from "../context/AppContext.jsx";
import {updateSelectedTypeOfActuator} from "../context/Actions.jsx";
import Box from "@mui/material/Box";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";

const TypesOfActuator = () => {
    const {state, dispatch} = useContext(AppContext);
    const {typesOfActuator} = state;
    const {selectedTypeOfActuator} = state;

    const actuatorTypeMapping = {
        "integerActuator": "Integer Actuator",
        "genericActuator": "Generic Actuator",
        "decimalActuator": "Decimal Actuator"
    };

    const handleTypeChange = (event) => {
        updateSelectedTypeOfActuator(dispatch, event.target.value);
    };

    return (
        <Box sx={{ padding: 2 }}>
            <FormControl fullWidth variant="outlined" margin="normal">
                <InputLabel id="type-label">Select Type</InputLabel>
                <Select
                    labelId="type-label"
                    id="type"
                    value={selectedTypeOfActuator}
                    onChange={handleTypeChange}
                    label="Select Type"
                >
                    <MenuItem value="">
                        <em>None</em>
                    </MenuItem>
                    {typesOfActuator.map((type) => (
                        <MenuItem key={type} value={type}>
                            {actuatorTypeMapping[type]}
                        </MenuItem>
                    ))}
                </Select>
            </FormControl>
        </Box>
    );
};

export default TypesOfActuator;