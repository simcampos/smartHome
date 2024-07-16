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
import {fetchActuators} from "../context/Actions.jsx";

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

function ActuatorsList() {
    const {state, dispatch} = useContext(AppContext);
    const {currentDevice, actuators} = state;
    const {deviceId} = currentDevice;
    const {loading, error, data} = actuators;

    const fetchActuatorsForDevice = (deviceId) => {
        fetchActuators(dispatch, deviceId);
    }

    useEffect(() => {
        fetchActuatorsForDevice(deviceId);
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
                            <StyledTableCell>Actuator ID</StyledTableCell>
                            <StyledTableCell>Actuator Name</StyledTableCell>
                            {/* Add more headers as needed */}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {data.map((actuator) => (
                            <StyledTableRow key={actuator.id}>
                                <StyledTableCell component="th" scope="row">
                                    {actuator.actuatorTypeID}
                                </StyledTableCell>
                                <StyledTableCell>{actuator.actuatorName}</StyledTableCell>
                                {/* Add more cells as needed */}
                            </StyledTableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        );
    }
    return <h3>No Actuators Found</h3>;
}

export default ActuatorsList;