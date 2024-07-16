import React, {useContext, useEffect} from 'react';
import {useParams} from 'react-router-dom';
import {Alert, CircularProgress, Container} from '@mui/material';
import {ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import {addDeviceToRoom, fetchDeviceTypes, fetchRoomById} from '../context/Actions.jsx';
import AppContext from '../context/AppContext.jsx';
import DeviceForm from '../components/DeviceForm.jsx';

function AddDeviceToRoomPage() {
    const {roomId} = useParams();
    const {state, dispatch} = useContext(AppContext);

    useEffect(() => {
        fetchRoomById(dispatch, roomId);
        fetchDeviceTypes(dispatch);
    }, [roomId, dispatch]);

    const handleAddDevice = async (device) => {
        await addDeviceToRoom(dispatch, roomId, device);
    };

    const {room, deviceTypes} = state;

    if (room.loading || deviceTypes.loading) {
        return <CircularProgress/>;
    }

    if (room.error) {
        return <Alert severity="error">Error loading room: {room.error}</Alert>;
    }

    if (deviceTypes.error) {
        return <Alert severity="error">Error loading device types: {deviceTypes.error}</Alert>;
    }

    return (
        <Container maxWidth="sm">
            <ToastContainer/>
            <DeviceForm
                room={room}
                deviceTypes={deviceTypes}
                onSubmit={handleAddDevice}
            />
        </Container>
    );
}

export default AddDeviceToRoomPage;
