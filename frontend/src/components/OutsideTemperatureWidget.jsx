import React, {useEffect, useContext, useState} from "react";
import { Typography} from "@mui/material";
import Box from "@mui/material/Box";
import CircularProgress from "@mui/material/CircularProgress";
import AppContext from "../context/AppContext.jsx";
import {initializeTemperatureFetching} from "../context/TemperatureActions.jsx";

function OutsideTemperatureWidget() {
    const {state, dispatch} = useContext(AppContext);
    const {temperature} = state;
    const {loading, error, data, lastUpdated} = temperature;
    const [nextUpdate, setNextUpdate] = useState(""); // Estado para armazenar a próxima atualização
    const [validLastUpdated, setValidLastUpdated] = useState(""); // Estado para armazenar o lastUpdated válido

    useEffect(() => {
        const clearTimers = initializeTemperatureFetching(dispatch);

        // Cleanup function to clear the timeout and interval
        return () => {
            clearTimers();
        };
    }, [dispatch]);

    // Função para calcular e definir a próxima atualização com base no horário atual
    const calculateNextUpdate = () => {
        const now = new Date();
        const minutes = now.getMinutes();
        let nextUpdateMinutes = Math.ceil((minutes + 1) / 15) * 15; // Próximo horário de atualização (15, 30, 45 ou 00)
        if (nextUpdateMinutes === 60) {
            nextUpdateMinutes = 0;
            now.setHours(now.getHours() + 1);
        }
        const nextUpdateFormattedMinutes = nextUpdateMinutes.toString().padStart(2, "0"); // Formatando para "00" se for 60
        const nextUpdateHour = now.getHours(); // Obtendo a hora atual
        const nextUpdateFormattedHour = nextUpdateHour.toString().padStart(2, "0"); // Adicionando um zero à esquerda se for menor que 10
        setNextUpdate(`${nextUpdateFormattedHour}:${nextUpdateFormattedMinutes}`); // Formatando a próxima atualização como "HH:MM"
    };

    // Função para arredondar o horário para o último intervalo de 15 minutos
    const getRoundedLastUpdated = () => {
        const now = new Date();
        const minutes = now.getMinutes();
        const roundedMinutes = Math.floor(minutes / 15) * 15;
        const formattedMinutes = roundedMinutes.toString().padStart(2, "0");
        const hours = now.getHours();
        const formattedHours = hours.toString().padStart(2, "0");
        return `${formattedHours}:${formattedMinutes}`;
    };

    // Atualizar a próxima atualização e lastUpdated sempre que a temperatura for atualizada
    useEffect(() => {
        calculateNextUpdate();
        setValidLastUpdated(getRoundedLastUpdated());
    }, [data]);

    const renderContent = () => {
        if (loading === true && error === null) {
            return <CircularProgress/>;
        } else if (error !== null) {
            // Truncate the error message to fit within the box
            const truncatedError = error ? (error.length > 50 ? error.substring(0, 50) + "..." : error) : "Please check your VPN DEI connection and try again";
            return (
                <Typography variant="h5" style={{textAlign: "center"}}>
                    Error: {truncatedError}
                </Typography>
            );
        } else if (data) {
            return (
                <div style={{textAlign: "center", justifyContent: "center", alignItems:"center"}}>
                    <Typography variant="h4" component="h2" marginBottom="40px">
                        Outside temperature: {data}
                    </Typography>
                    <Typography variant="h6" color="textSecondary">
                        Last updated: {validLastUpdated}
                    </Typography>
                    <Typography variant="h6" color="textSecondary">
                        Next update at: {nextUpdate}
                    </Typography>
                </div>
            );
        } else {
            return null; // Render nothing if data is not available
        }
    };

    return (
        <Box>
            {renderContent()}
        </Box>
    );
}

export default OutsideTemperatureWidget;
