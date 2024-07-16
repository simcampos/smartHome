import * as React from 'react';
import {createTheme, styled, ThemeProvider} from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import MuiAppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Link from '@mui/material/Link';
import Rooms from '../components/Rooms.jsx';
import OutsideTemperatureWidgetV2 from "../components/OutsideTemperatureWidget.jsx";
import SunriseSunsetWidgetV2 from "../components/SunriseSunsetWidget.jsx";

function Copyright(props) {
    return (
        <Typography variant="body2" color="text.secondary" align="center" {...props}>
            {'Copyright © '}
            <Link color="inherit">
                Os Chouriças
            </Link>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

const drawerWidth = 240;

const AppBar = styled(MuiAppBar, {
    shouldForwardProp: (prop) => prop !== 'open',
})(({theme, open}) => ({
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(['width', 'margin'], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
    })
}));


const defaultTheme = createTheme();

export default function Dashboard() {
    return (
        <ThemeProvider theme={defaultTheme}>
            <Box sx={{display: 'flex'}}>
                <CssBaseline/>
                <AppBar position="absolute">
                    <Toolbar
                        sx={{
                            pr: '24px',  // keep right padding when drawer closed
                            backgroundColor: 'black'
                        }}
                    >
                        <Typography
                            component="h1"
                            variant="h6"
                            color="inherit"
                            noWrap
                            sx={{flexGrow: 1}}
                        >
                            Smart Home is finally here!
                        </Typography>
                    </Toolbar>
                </AppBar>

                <Box
                    component="main"
                    sx={{
                        backgroundColor: (theme) =>
                            theme.palette.mode === 'light'
                                ? theme.palette.grey[100]
                                : theme.palette.grey[900],
                        flexGrow: 1,
                        height: '100vh',
                        overflow: 'auto',
                    }}
                >
                    <Toolbar/>
                    <Container maxWidth="lg" sx={{mt: 4, mb: 4}}>
                        <Grid container spacing={3}>
                            {/* List Of Rooms */}
                            <Grid item xs={12}>
                                <Paper sx={{p: 2, display: 'flex', flexDirection: 'column'}}>
                                    <Rooms/>
                                </Paper>
                            </Grid>

                            {/* OutsideTemperatureWidget */}
                            <Grid item xs={12} md={6} lg={6}>
                                <Paper
                                    sx={{
                                        p: 2,
                                        display: 'flex',
                                        flexDirection: 'column',
                                        height: 240,
                                        alignItems: 'center',
                                        justifyContent: 'center'
                                    }}
                                >
                                    <OutsideTemperatureWidgetV2/>
                                </Paper>
                            </Grid>
                            {/* Recent Deposits */}
                            <Grid item xs={12} md={6} lg={6}>
                                <Paper
                                    sx={{
                                        p: 2,
                                        display: 'flex',
                                        flexDirection: 'column',
                                        height: 240,
                                        alignItems: 'center',
                                        justifyContent: 'center'
                                    }}
                                >
                                    <SunriseSunsetWidgetV2/>
                                </Paper>
                            </Grid>

                        </Grid>
                        <Copyright sx={{pt: 4}}/>
                    </Container>
                </Box>
            </Box>
        </ThemeProvider>
    );
}