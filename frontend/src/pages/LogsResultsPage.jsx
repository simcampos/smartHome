import React, { useContext, useEffect } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { CircularProgress, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button } from '@mui/material';
import AppContext from "../context/AppContext.jsx";
import { fetchLogsByDeviceId } from '../context/Actions.jsx';

function useQuery() {
    return new URLSearchParams(useLocation().search);
}

function LogsResultsPage() {
    const { deviceId } = useParams();
    const { state, dispatch } = useContext(AppContext);
    const query = useQuery();
    const startPeriod = query.get('start');
    const endPeriod = query.get('end');
    const navigate = useNavigate();

    useEffect(() => {
        if (deviceId && startPeriod && endPeriod) {
            fetchLogsByDeviceId(dispatch, deviceId, startPeriod, endPeriod);
        }
    }, [dispatch, deviceId, startPeriod, endPeriod]);

    const { logs } = state;
    const { loading, error, data } = logs;

    const handleGoBackToDevices = () => {
        navigate(-1);
    };

    const handleGoBackToMain = () => {
        navigate('/');
    };

    return (
        <div className="logs-results-page">
            <h1>Logs for Device</h1>
            <Button
                variant="contained"
                color="secondary"
                fullWidth
                onClick={handleGoBackToDevices}
                style={{ marginTop: '20px' }}
            >
                Back
            </Button>
            <Button
                variant="contained"
                color="secondary"
                fullWidth
                onClick={handleGoBackToMain}
                style={{ marginTop: '20px' }}
            >
                Back to Home
            </Button>

            {loading ? (
                <CircularProgress style={{ marginTop: '20px' }} />
            ) : error ? (
                <Typography color="error">{error}</Typography>
            ) : (
                <TableContainer component={Paper} style={{ marginTop: '20px' }}>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>Name</TableCell>
                                <TableCell>Reading</TableCell>
                                <TableCell>Unit</TableCell>
                                <TableCell>Date</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {data && data.length > 0 ? (
                                data.map((log) => (
                                    <TableRow key={log.id}>
                                        <TableCell>{log.sensorTypeID}</TableCell>
                                        <TableCell>{log.reading}</TableCell>
                                        <TableCell>{log.unitID}</TableCell>
                                        <TableCell>{log.timestamp}</TableCell>
                                    </TableRow>
                                ))
                            ) : (
                                <TableRow>
                                    <TableCell colSpan={4}>No readings yet</TableCell>
                                </TableRow>
                            )}
                        </TableBody>
                    </Table>
                </TableContainer>
            )}
        </div>
    );
}

export default LogsResultsPage;
