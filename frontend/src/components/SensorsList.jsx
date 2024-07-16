import * as React from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import {useContext, useEffect} from "react";
import AppContext from "../context/AppContext.jsx";
import { fetchSensors} from "../context/Actions.jsx";

const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
    },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
    '&:nth-of-type(odd)': {
        backgroundColor: theme.palette.action.hover,
    },
    // hide last border
    '&:last-child td, &:last-child th': {
        border: 0,
    },
}));

function SensorsList() {
    const {state, dispatch} = useContext(AppContext);
    const {currentDevice, sensors} = state;
    const {deviceId} = currentDevice;
    const {loading, error, data} = sensors;

    const fetchSensorsForDevice = (deviceId) => {
        fetchSensors(dispatch, deviceId);
    }

    useEffect(() => {
        fetchSensorsForDevice(deviceId);
    }, [deviceId]);

    if (loading) {
        return <h1>Loading ....</h1>;
    } else if (error) {
        return <h1>Error ....</h1>;
    } else if (data.length > 0) {
        return (
            <TableContainer component={Paper}>
                <Table sx={{ minWidth: 700 }} aria-label="customized table">
                    <TableHead>
                        <TableRow>
                            <StyledTableCell>Sensor ID</StyledTableCell>
                            <StyledTableCell>Sensor Name</StyledTableCell>
                            {/* Add more headers as needed */}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {data.map((sensor) => (
                            <StyledTableRow key={sensor.sensorID}>
                                <StyledTableCell component="th" scope="row">
                                    {sensor.sensorTypeID}
                                </StyledTableCell>
                                <StyledTableCell>{sensor.sensorName}</StyledTableCell>
                                {/* Add more cells as needed */}
                            </StyledTableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        );
    }
    return <h3>No Sensors Found</h3>;
}

export default SensorsList;