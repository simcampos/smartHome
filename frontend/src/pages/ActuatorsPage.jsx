import React from 'react';
import ActuatorsList from '../components/ActuatorsList';
import {useNavigate} from "react-router-dom";
import {Button} from "@mui/material";

function ActuatorsPage() {

    const navigate = useNavigate();

    const handleGoBack = () => {
        navigate(-1);
    };

    const handleBackToHome = () => {
        navigate('/');
    };

    return (
        <div>
            <h1>Let's see the actuators from the device</h1>
            <ActuatorsList />
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

export default ActuatorsPage;