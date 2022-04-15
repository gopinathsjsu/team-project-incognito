import React, { useState } from 'react';
import './SearchResult.css';
import './Details.css';
import FavoriteBorderIcon from "@material-ui/icons/FavoriteBorder";
import StarIcon from "@material-ui/icons/Star";
import { Button } from '@material-ui/core';
import { DateRangePicker } from 'react-date-range';
import RoomServiceIcon from '@mui/icons-material/RoomService';
import BedroomParentIcon from '@mui/icons-material/BedroomParent';
import AuthService from '../User_auth';
import { useHistory } from 'react-router-dom';

function Details() {
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());
    const [breakfast, setCheckedBF] = React.useState(false);
    const [fit, setCheckedFit] = React.useState(false);
    const [pool, setCheckedPool] = React.useState(false);
    const [park, setCheckedParking] = React.useState(false);
    const [meals, setCheckedMeals] = React.useState(false);
    const [room, setRoomType] = React.useState(false);
    const history = useHistory();

    const setCheckedOne = () => {
      setCheckedBF(!breakfast);
    };

    const setCheckedTwo = () => {
      setCheckedFit(!fit);
    };

    const setCheckedThree = () => {
      setCheckedPool(!pool);
    };

    const setCheckedFour = () => {
      setCheckedParking(!park);
    };

    const setCheckedFive = () => {
      setCheckedMeals(!meals);
    };
  
    const roomType = (event) => {
      console.log(event.target.value)
      setRoomType(event.target.value)
    }
    
    const selectionRange = {
        startDate: startDate,
        endDate: endDate,
        key: "selection",
      };
      function handleSelect(ranges) {
        setStartDate(ranges.selection.startDate);
        setEndDate(ranges.selection.endDate);
    }

    const book = () => {
      console.log(Date())
      var g1 = new Date();
      if(startDate.getTime() < g1.getTime()){
          window.alert("Kindly select the later dates")
          return
      }
      AuthService.getBookingConfirmation(room, startDate, endDate, breakfast, fit, pool, park, meals).then(
          () => { history.push('/') 
          //window.location.reload(false);
          },
          error => {
              const resMessage =
              (error.response &&
              error.response.data &&
              error.response.data.message) ||
              error.message ||
              error.toString();
          }
      )
  }

  return (
    <div>
        <DateRangePicker ranges={[selectionRange]} onChange={handleSelect} />
        <h3>Amenities <RoomServiceIcon /></h3>
        <input type="checkbox" checked={breakfast} onChange={setCheckedOne}/> Daily Continental Breakfast
        <br></br>
        <input type="checkbox" checked={fit} onChange={setCheckedTwo}/> Access to fitness room
        <br></br>
        <input type="checkbox" checked={pool} onChange={setCheckedThree}/> Access to Swimming Pool/Jacuzzi
        <br></br>
        <input type="checkbox" checked={park} onChange={setCheckedFour}/> Daily Parking
        <br></br>
        <input type="checkbox" checked={meals} onChange={setCheckedFive}/> All meals included (Breakfast, Lunch, Dinner)
        <br></br>
        <br></br>
        <br></br>
        <h3>Room Type <BedroomParentIcon /></h3>
        <div onChange = {roomType}>
          <input type="radio" value="Single" name="room"/> Single Room
          <br></br>
          <input type="radio" value="Double" name="room"/> Double Room
          <br></br>
          <input type="radio" value="Suite" name="room"/> Suite
        </div>
        <br></br>
        <Button variant='outlined' onClick={book} >Confirm</Button>
    </div>
  )
}

export default Details
