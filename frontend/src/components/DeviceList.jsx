import React, {useEffect, useContext, useState} from "react";
import Accordion from '@mui/material/Accordion';
import AccordionActions from '@mui/material/AccordionActions';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import {useNavigate} from 'react-router-dom';
import AppContext, {BlindRollerContext} from "../context/AppContext.jsx";
import {
    deactivateDeviceFromServer,
    fetchActuators,
    fetchDevicesByRoomId, fetchSensors,
    saveCurrentDevice,
    updateCurrentDevice
} from "../context/Actions.jsx";
import BlindRollerPosition from "./BlindRollerPosition.jsx";
import BlindRollerControl from "./BlindRollerControl.jsx";
import ActuatorsList from "./ActuatorsList.jsx";

function DeviceList() {
    const {state, dispatch} = useContext(AppContext);
    const {currentRoom, devices, currentDevice, actuators} = state;
    const {deviceId} = currentDevice;
    const {roomId} = currentRoom;
    const {loading, error, data} = devices;
    const navigate = useNavigate();
    const [blindRollerValue, setBlindRollerValue] = useState(null);
    const [deactivatedDevices, setDeactivatedDevices] = useState({});
    const [loadingDevices, setLoadingDevices] = useState({});

    console.log(roomId)

    useEffect(() => {
        fetchDevicesByRoomId(dispatch, roomId);
    }, [dispatch, roomId]);

    const fetchActuatorsForDevice = (deviceId) => {
        fetchActuators(dispatch, deviceId);
    };

    const handleFetchActuatorsForDevice = (deviceId) => {
        saveCurrentDevice(dispatch, deviceId);
        navigate(`/devices/${deviceId}/actuators`)
    }

    const handleFetchSensorsForDevice = (deviceId) => {
        saveCurrentDevice(dispatch, deviceId);
        navigate(`/devices/${deviceId}/sensors`)
    }

    const handleAddSensorToDeviceOnClick = (deviceId) => {
        saveCurrentDevice(dispatch, deviceId);
        navigate(`/devices/${deviceId}/add-sensor`);
    };

    const handleAddActuatorToDeviceOnClick = (deviceId) => {
        saveCurrentDevice(dispatch, deviceId);
        navigate(`/devices/${deviceId}/add-actuator`);
    };

    const handleViewLogsOnClick = (deviceId, deviceName) => {
        updateCurrentDevice(dispatch, deviceId, deviceName);
        navigate(`/logs/${deviceId}`);
    };

    const handleToggle = (deviceId) => {
        setLoadingDevices(prevState => ({...prevState, [deviceId]: true}));
        deactivateDeviceFromServer(dispatch, deviceId)
            .then(() => {
                setDeactivatedDevices(prevState => ({...prevState, [deviceId]: true}));
                setLoadingDevices(prevState => ({...prevState, [deviceId]: false}));
            });
    };

    if (loading) {
        return <h1>Loading ....</h1>;
    } else if (error) {
        return <h1>Error ....</h1>;
    } else if (data.length > 0) {
        return (
            <div>
                {data.map((device) => {
                    return (
                        <Accordion key={device.deviceID}
                                   onChange={() => fetchActuatorsForDevice(device.deviceID)}
                        >
                            <AccordionSummary
                                expandIcon={<ExpandMoreIcon/>}
                                aria-controls={`panel${device.deviceID}-content`}
                                id={`panel${device.deviceID}-header`}
                            >
                                <Typography>{device.deviceName}</Typography>
                            </AccordionSummary>
                            <AccordionDetails>
                                <Typography component="div">
                                    <div style={{marginBottom: '16px'}}>
                                        Here are some details about {device.deviceName}.
                                        {loadingDevices[device.deviceID] ? (
                                            <p>The device is deactivated </p>
                                        ) : deactivatedDevices[device.deviceID] ? (
                                            <p>The device is deactivated.</p>
                                        ) : (
                                            <p>Here is the device status: {device.deviceStatus}.</p>
                                        )}
                                    </div>
                                    {device.deviceName === 'BlindRoller' && (
                                        <BlindRollerContext.Provider value={{blindRollerValue, setBlindRollerValue}}>
                                            <div style={{marginBottom: '16px'}}>
                                                <BlindRollerPosition deviceId={device.deviceID}/>
                                            </div>
                                            <div>
                                                <BlindRollerControl deviceId={device.deviceID}/>
                                            </div>
                                        </BlindRollerContext.Provider>
                                    )}
                                </Typography>
                            </AccordionDetails>
                            {!deactivatedDevices[device.deviceID] && (
                                <AccordionActions>
                                    <Button size="small" color="primary"
                                            onClick={() => handleViewLogsOnClick(device.deviceID, device.deviceName)}>
                                        View Logs
                                    </Button>
                                    <Button size="small" color="primary"
                                            onClick={() => handleAddSensorToDeviceOnClick(device.deviceID)}>
                                        Add Sensor
                                    </Button>
                                    <Button size="small" color="primary"
                                            onClick={() => handleAddActuatorToDeviceOnClick(device.deviceID)}>
                                        Add Actuator
                                    </Button>
                                    <Button size="small" color="primary"
                                            onClick={() => handleToggle(device.deviceID)}>
                                        Deactivate
                                    </Button>
                                    <Button size="small" color="primary"
                                            onClick={() => handleFetchActuatorsForDevice(device.deviceID)}>
                                        See Actuators
                                    </Button>
                                    <Button size="small" color="primary"
                                            onClick={() => handleFetchSensorsForDevice(device.deviceID)}>
                                        See Sensors
                                    </Button>
                                </AccordionActions>
                            )}
                        </Accordion>
                    );
                })}
            </div>
        );
    }
    return null;
}

export default DeviceList;