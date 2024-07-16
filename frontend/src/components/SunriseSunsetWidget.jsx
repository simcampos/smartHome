import React, {useState, useEffect} from "react";
import {Typography} from "@mui/material";
import Box from "@mui/material/Box";


function SunriseSunsetWidget() {
    const [sunrise, setSunrise] = useState("");
    const [sunset, setSunset] = useState("");

    useEffect(() => {
        fetch("https://api.sunrise-sunset.org/json?lat=41.1784964&lng=-8.6089491")
            .then((response) => response.json())
            .then((data) => {
                setSunrise(data.results.sunrise);
                setSunset(data.results.sunset);
            });
    }, []);

    return (
        <Box display={"flex"} flexDirection={"column"} gap={"30px"} alignItems={"center"} justifyContent={"center"}>
            <Typography variant="h4" component="h3" >
                Sunrise: {sunrise}
            </Typography>
            <Typography variant="h4" component="h5">
                Sunset: {sunset}
            </Typography>
        </Box>
    )
}

export default SunriseSunsetWidget;