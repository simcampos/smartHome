export function fetchTemperatureFromWS(success, failure) {
    const controller = new AbortController();
    const timeoutId = setTimeout(() => controller.abort(), 7000); // 7 seconds timeout

    const now = new Date();
    const hour = now.getHours();
    const groupNumber = 1;
    const latitude = 41.1784964;
    const longitude = -8.6089491;

    fetch(`http://10.9.24.170:8080/InstantaneousTemperature?groupNumber=${groupNumber}&latitude=${latitude}&longitude=${longitude}&hour=${hour}`, {signal: controller.signal})
        .then((response) => {
            clearTimeout(timeoutId); // Clear the timeout on success
            return response.json();
        })
        .then((data) => {
            if (data && data.measurement !== "NaN" && data.unit === "C") {
                success(`${data.measurement}ºC`);
            } else {
                throw new Error(data.info || "Invalid API response structure");
            }
        })
        .catch((err) => {
            clearTimeout(timeoutId); // Clear the timeout on failure
            if (err.name === 'AbortError') {
                failure("Please check your VPN DEI connection and try again");
            } else {
                console.error("Error fetching temperature:", err);
                failure("Error fetching temperature");
            }
        });
}

export function configureAndFetchTemperature(dispatch, fetchTemperature) {
    let timeoutId;
    let intervalId;

    const startFetching = () => {
        fetchTemperature(dispatch);

        const now = new Date();
        const minutes = now.getMinutes();
        const seconds = now.getSeconds();
        const milliseconds = now.getMilliseconds();

        // Calculate delay until the next 15-minute mark
        const minutesToNextQuarterHour = 15 - (minutes % 15);
        let delay =
            minutesToNextQuarterHour * 60 * 1000 -
            seconds * 1000 -
            milliseconds;

        console.log("Current time:", now);
        console.log("Delay until next 15-minute mark:", delay);

        // Set timeout to start fetching at the next 15-minute mark
        timeoutId = setTimeout(() => {
            fetchTemperature(dispatch);

            // Set interval to fetch every 15 minutes
            intervalId = setInterval(() => {
                console.log("Fetching temperature at:", new Date());
                fetchTemperature(dispatch);
            }, 15 * 60 * 1000);
        }, delay);
    };

    startFetching();

    return {
        clearTimers: () => {
            if (timeoutId) clearTimeout(timeoutId);
            if (intervalId) clearInterval(intervalId);
        }
    };
}
