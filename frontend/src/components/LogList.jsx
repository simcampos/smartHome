import React, { useEffect, useContext, useState } from 'react';
import { useParams } from 'react-router-dom';
import { fetchLogsByDeviceId } from '../context/Actions';
import AppContext from '../context/AppContext';
import { CircularProgress, Typography, List, ListItem, ListItemText, TextField, Button } from '@mui/material';

function DeviceLogs() {
    const { state, dispatch } = useContext(AppContext);
    const { logs } = state;
    const { loading, error, data } = logs;
    const { deviceId } = useParams();

    const [timeStart, setTimeStart] = useState('');
    const [timeEnd, setTimeEnd] = useState('');

    useEffect(() => {
        if (timeStart && timeEnd) {
            fetchLogsByDeviceId(dispatch, deviceId, timeStart, timeEnd);
        }
    }, [dispatch, deviceId, timeStart, timeEnd]);

    const handleSubmit = (event) => {
        event.preventDefault();
        if (timeStart && timeEnd) {
            fetchLogsByDeviceId(dispatch, deviceId, timeStart, timeEnd);
        }
    };

    if (loading) {
        return <CircularProgress />;
    }

    if (error) {
        return <Typography color="error">{error}</Typography>;
    }

    return (
        <div>
            <Typography variant="h5">Device Logs</Typography>
            <form onSubmit={handleSubmit}>
                <TextField
                    label="Start Time"
                    type="datetime-local"
                    value={timeStart}
                    onChange={(e) => setTimeStart(e.target.value)}
                    InputLabelProps={{ shrink: true }}
                    required
                />
                <TextField
                    label="End Time"
                    type="datetime-local"
                    value={timeEnd}
                    onChange={(e) => setTimeEnd(e.target.value)}
                    InputLabelProps={{ shrink: true }}
                    required
                />
                <Button type="submit" variant="contained" color="primary">Fetch Logs</Button>
            </form>
            <List>
                {data.map((log) => (
                    <ListItem key={log.id}>
                        <ListItemText
                            primary={`${log.type}: ${log.value}`}
                            secondary={`Date: ${log.timestamp}`}
                        />
                    </ListItem>
                ))}
            </List>
        </div>
    );
}

export default DeviceLogs;
