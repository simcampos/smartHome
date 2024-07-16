import React from 'react';
import SensorsList from '../components/SensorsList';
import {useNavigate} from "react-router-dom";
import {Button} from "@mui/material";

function SensorsPage() {

    const navigate = useNavigate();

    const handleGoBack = () => {
        navigate(-1);
    };

    const handleBackToHome = () => {
        navigate('/');
    };

    return (
        <div>
            <h1>Let's see the sensors from the device</h1>
            <SensorsList />
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
                onClick={handleBackToHome}
                style={{marginTop: '20px'}}
            >
                Back to Home
            </Button>
        </div>
    );
}

export default SensorsPage;