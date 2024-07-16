import React, {useState} from 'react';
import {Box, Button, FormControl, InputLabel, MenuItem, Select, TextField, Typography} from '@mui/material';
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import {useNavigate} from "react-router-dom";

const DeviceForm = ({room, deviceTypes, onSubmit}) => {
    const [deviceName, setDeviceName] = useState('');
    const [deviceType, setDeviceType] = useState('');
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!deviceName || !deviceType) {
            toast.error('Please fill in all fields.');
            return;
        }

        const device = {name: deviceName, type: deviceType};
        onSubmit(device);
    };

    const handleBackToHome = () => {
        navigate('/');
    };

    return (
        <Box
            display="flex"
            flexDirection="column"
            alignItems="center"
            justifyContent="center"
            minHeight="100vh"
        >
            <Typography variant="h4" gutterBottom>
                Add New Device to {room.data?.name || 'Room'}
            </Typography>
            <form onSubmit={handleSubmit} style={{width: '100%'}}>
                <TextField
                    label="Device Name"
                    fullWidth
                    margin="normal"
                    value={deviceName}
                    onChange={(e) => setDeviceName(e.target.value)}
                />
                <FormControl fullWidth margin="normal">
                    <InputLabel>Device Type</InputLabel>
                    <Select
                        value={deviceType}
                        onChange={(e) => setDeviceType(e.target.value)}
                    >
                        <MenuItem value="" disabled>
                            Select a device type
                        </MenuItem>
                        {deviceTypes.data.map((type, index) => (
                            <MenuItem key={index} value={type.description}>
                                {type.description}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <Button type="submit" variant="contained" color="primary" fullWidth>
                    Add Device
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
            </form>
        </Box>
    );
};

export default DeviceForm;
