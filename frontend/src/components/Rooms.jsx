import * as React from 'react';
import Title from './Title.jsx';
import Accordion from "@mui/material/Accordion";
import AccordionSummary from "@mui/material/AccordionSummary";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore.js";
import Typography from "@mui/material/Typography";
import AccordionDetails from "@mui/material/AccordionDetails";
import AccordionActions from "@mui/material/AccordionActions";
import Button from "@mui/material/Button";
import {useContext, useEffect} from "react";
import AppContext from "../context/AppContext.jsx";
import {useNavigate} from "react-router-dom";
import {fetchRooms, setCurrentRoom} from "../context/Actions.jsx";

function preventDefault(event) {
    event.preventDefault();
}

export default function Rooms() {
    const { state, dispatch } = useContext(AppContext);
    const { rooms } = state;
    const { loading, error, data } = rooms;
    const navigate = useNavigate();

    useEffect(() => {
        fetchRooms(dispatch);
    }, [dispatch]);

    const handleAddDeviceClick = (roomId) => {
        navigate(`/rooms/${roomId}`);
    };

    const handleOpenDevicesClick = (roomId, roomName) => {
        setCurrentRoom(dispatch, roomId, roomName);
        navigate(`/rooms/${roomId}/devices`);
    };

    if (loading) {
        return <h1>Loading ....</h1>;
    }

    if (error) {
        return <h1>Error: {error}</h1>;
    }

    if (!Array.isArray(data) || data.length === 0) {
        return <h1>No data ....</h1>;
    }

    return (
        <React.Fragment>
            <Title sx={{ textAlign: 'center' , marginTop: '20px', fontSize: '2.5em', color: 'black'}}>Rooms</Title>
            <div style={{
                maxHeight: '400px', // Set the desired height
                overflowY: 'auto', // Enable vertical scrolling
                padding: '10px', // Optional: Add padding for better spacing
                marginTop: '10px'
            }}>
                {data.map((room) => (
                    <Accordion key={room.roomId} sx={{
                        border: '1px solid lightgrey',
                        backgroundColor: 'white',
                        fontFamily: 'Roboto, sans-serif',
                        marginBottom: 2
                    }}>
                        <AccordionSummary
                            expandIcon={<ExpandMoreIcon />}
                            aria-controls={`panel${room.roomId}-content`}
                            id={`panel${room.roomId}-header`}
                        >
                            <Typography sx={{ fontFamily: 'Roboto, sans-serif' }}>
                                {room.roomName}
                            </Typography>
                        </AccordionSummary>
                        <AccordionDetails>
                            <Typography sx={{ fontFamily: 'Roboto, sans-serif' }}>
                                Floor: {room.floor}
                            </Typography>
                        </AccordionDetails>
                        <AccordionActions>
                            <Button
                                size="small"
                                color="primary"
                                onClick={() => handleOpenDevicesClick(room.roomId, room.roomName)}>
                                Open Devices
                            </Button>
                            <Button
                                size="small"
                                color="primary"
                                onClick={() => handleAddDeviceClick(room.roomId)}>
                                Add Device
                            </Button>
                        </AccordionActions>
                    </Accordion>
                ))}
            </div>
        </React.Fragment>
    );
}
