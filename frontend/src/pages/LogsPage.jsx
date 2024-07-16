import React, { useContext, useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { TextField, Button, Typography } from '@mui/material';
import AppContext from "../context/AppContext.jsx";

function LogsPage() {
    const { deviceId } = useParams();
    const { state } = useContext(AppContext);
    const { currentDevice } = state;
    const { deviceName } = currentDevice;
    const [startPeriod, setStartPeriod] = useState('');
    const [endPeriod, setEndPeriod] = useState('');

    const navigate = useNavigate();

    // Function to format date and time to 'YYYY-MM-DDTHH:MM' format
    const formatDate = (date) => {
        const pad = (num) => (num < 10 ? '0' + num : num);
        return `${date.getFullYear()}-${pad(date.getMonth() + 1)}-${pad(date.getDate())}T${pad(date.getHours())}:${pad(date.getMinutes())}`;
    };

    useEffect(() => {
        const currentDate = new Date();
        setEndPeriod(formatDate(currentDate));
        const startDate = new Date(currentDate.getTime() - (7 * 24 * 60 * 60 * 1000));
        setStartPeriod(formatDate(startDate));
    }, []);

    const handleSubmit = (event) => {
        event.preventDefault();
        navigate(`/logs/${deviceId}/results?start=${startPeriod}&end=${endPeriod}`);
    };

    const handleGoBack = () => {
        navigate(-1);
    }

    const handleGoBackToMain = () => {
        navigate('/');
    }

    return (
        <div className="logs-page">
            <h1>Please choose the time period: {deviceName}</h1>
            <form onSubmit={handleSubmit}>
                <TextField
                    label="Start Time"
                    type="datetime-local"
                    value={startPeriod}
                    onChange={(e) => setStartPeriod(e.target.value)}
                    InputLabelProps={{ shrink: true }}
                    required
                />
                <TextField
                    label="End Time"
                    type="datetime-local"
                    value={endPeriod}
                    onChange={(e) => setEndPeriod(e.target.value)}
                    InputLabelProps={{ shrink: true }}
                    required
                />
                <Button type="submit" variant="contained" color="primary">Fetch Logs</Button>
                <Button
                    variant="contained"
                    color="secondary"
                    fullWidth
                    onClick={handleGoBack}
                    style={{marginTop: '20px'}}
                >
                    Back
                </Button>

                <Button
                    variant="contained"
                    color="secondary"
                    fullWidth
                    onClick={handleGoBackToMain}
                    style={{marginTop: '20px'}}
                >
                    Back to Home
                </Button>
            </form>
        </div>
    );
}

export default LogsPage;
