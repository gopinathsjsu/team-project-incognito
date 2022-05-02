import React, { useState } from 'react'
import { Grid, Paper, Avatar, Typography, TextField, Button } from '@material-ui/core'
import AddCircleOutlineOutlinedIcon from '@material-ui/icons/AddCircleOutlineOutlined';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Axios from 'axios';
import AuthService from '../User_auth';
import validator from 'validator';
import { useHistory } from 'react-router-dom'; 
import { Header } from '..';

const EmployeeSignup = () => {
    const [userName, setuserNameReg] = useState("");
    const [userPassword, setuserPassword] = useState("");
    const [userGender, setGender] = useState("");
    const [userPhoneno, setPhoneNumber] = useState("");
    const [email, setEmail] = useState("");
    const [succesful, setSuccesfull] = useState(false);
    const history = useHistory();

    const paperStyle = { padding: '30px 20px', width: 300, margin: "20px auto" }
    const headerStyle = { margin: 0 }
    const avatarStyle = { backgroundColor: '#ff7779' }
    const marginTop = { marginTop: 5 }
    

    const register = (event) => {
        event.preventDefault();
        if( userName == "" || userPassword == ""){
            window.alert("Username or Password Missing")
            return
        }
        if(!validator.isEmail(email)){
            window.alert("Please enter valid Email")
            return
        }
        AuthService.employeeregister(userName, email, userPassword, userPhoneno).then(
            history.push('/employeelogin') 
        ).catch((error) => {
            // Error
            if (error.response) {
                window.alert(error.response)
            } else if (error.request) {
                window.alert(error.request)
                console.log(error.request);
            } else {
                // Something happened in setting up the request that triggered an Error
                window.alert(error.message)
                console.log('Error', error.message);
            }
            console.log(error.config);
        });
    }

    return (
        <div>
            <Header/>
        <Grid>
            <Paper elevation={20} style={paperStyle}>
                <Grid align='center'>
                    <Avatar style={avatarStyle}>
                        <AddCircleOutlineOutlinedIcon />
                    </Avatar>
                    <h2 style={headerStyle}>Employee Sign Up</h2>
                    <Typography variant='caption' gutterBottom>Please fill this form to create an account !</Typography>
                </Grid>
                <form>
                    <TextField fullWidth label='Name' onChange = {(e)=> {setuserNameReg(e.target.value)}} placeholder="Enter your name" />
                    <TextField fullWidth label='Email' onChange = {(e)=> {setEmail(e.target.value)}} placeholder="Enter your email" />
                    <FormControl component="fieldset" style={marginTop}>
                        <FormLabel component="legend">Gender</FormLabel>
                        <RadioGroup aria-label="gender" name="gender" onChange = {(e)=> {setGender(e.target.value)}} style={{ display: 'initial' }}>
                            <FormControlLabel value="female" control={<Radio />} label="Female" />
                            <FormControlLabel value="male" control={<Radio />} label="Male" />
                        </RadioGroup>
                    </FormControl>
                    <TextField fullWidth label='Phone Number' onChange = {(e)=> {setPhoneNumber(e.target.value)}} placeholder="Enter your phone number" />
                    <TextField fullWidth label='Password' onChange = {(e)=> {setuserPassword(e.target.value)}} placeholder="Enter your password"/>
                    <TextField fullWidth label='Confirm Password' placeholder="Confirm your password"/>
                    <FormControlLabel
                        control={<Checkbox name="checkedA" />}
                        label="I accept the terms and conditions."
                    />
                    <Button type='submit' variant='contained' onClick ={register} color='primary'>Sign up</Button>
                </form>
            </Paper>
        </Grid>
        </div>
    )
}

export default EmployeeSignup;