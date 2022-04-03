import React, { useState } from 'react'
import { Grid,Paper, Avatar, TextField, Button, Typography,Link } from '@material-ui/core'
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Axios from 'axios'
const Login=()=>{
    const [userName, setuserNameReg] = useState("");
    const [userPassword, setuserPassword] = useState("");
    const paperStyle={padding :20,height:'70vh',width:280, margin:"20px auto"}
    const avatarStyle={backgroundColor:'#ff7779'}
    const btnstyle={margin:'8px 0'}

    const register = () => {
        Axios.post("http://192.168.137.1:8086/api/auth/login",{
        email: userName,
        password : userPassword,
    }).then((response) => {
        console.log(response)
    })}
    return(
        <Grid>
            <Paper elevation={10} style={paperStyle}>
                <Grid align='center'>
                     <Avatar style={avatarStyle}><LockOutlinedIcon/></Avatar>
                    <h2>Sign In</h2>
                </Grid>
                <TextField label='Username' placeholder='Enter username' onChange = {(e)=> {setuserNameReg(e.target.value)}} fullWidth required/>
                <TextField label='Password' placeholder='Enter password' onChange = {(e)=> {setuserPassword(e.target.value)}} type='password' fullWidth required/>
                <FormControlLabel
                    control={
                    <Checkbox
                        name="checkedB"
                        color="primary"
                    />
                    }
                    label="Remember me"
                 />
                <Button type='submit' color='primary' variant="contained" onClick ={register} style={btnstyle} fullWidth>Sign in</Button>
                <Typography >
                <Link href="#" >
                        Forgot password ?
                </Link>
                </Typography>
                <Typography > Do you have an account ?
                     <Link href="./Signup" >
                         Sign Up
                     </Link>
                </Typography>
            </Paper>
        </Grid>
    )
}

export default Login