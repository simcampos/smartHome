import React, { useContext } from 'react';
import AppContext from "../context/AppContext.jsx";
import DeviceList from '../components/DeviceList';
import {useNavigate} from "react-router-dom";
import {Button} from "@mui/material";

function DevicesInRoomPage() {
    const { state } = useContext(AppContext);
    const { currentRoom } = state;
    const roomName = currentRoom.roomName;

    const navigate = useNavigate();

    const handleGoBack = () => {
        navigate(-1);

    }

    return (
        <div className={"devices-in-room-page"}>

            <h1>Devices in {roomName}</h1>
            <DeviceList/>
            <Button
                variant="contained"
                color="secondary"
                fullWidth
                onClick={handleGoBack}
                style={{marginTop: '20px'}}
            >
                Back to Home
            </Button>
        </div>
    );
}

export default DevicesInRoomPage;