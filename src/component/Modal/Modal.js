import React, { useState } from 'react'
import './Modal.css';
import { useHistory } from 'react-router-dom';
import { Button } from '@material-ui/core';
import AuthService from '../User_auth';

function Modal(price, id) {
  const history = useHistory();

  const Confirm = () =>{
      history.push('/')
  }

  const Cancel = () => {
    AuthService.getCancelBooking(id).then(
      () => { 
       history.push('/') 
          
       //window.location.href = "/";
       // return <Redirect to ="/"/>
      }).catch((error) => {
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
    <div className="modal">
          <div className="overlay"></div>
          <div className="modal-content">
            <h2>Thank you for you booking</h2>
            <h2>Your total cost is { price.price } </h2>
            <Button className="btn_modal" onClick = {Confirm}>
              Book New
            </Button>
            <span>
            <Button className="right_btn" onClick = {Cancel}>
              Cancel
            </Button>
            </span>
          </div>
        </div>
  )
}

export default Modal
