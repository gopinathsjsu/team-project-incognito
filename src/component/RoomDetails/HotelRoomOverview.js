import React, { useState } from 'react'
import { useParams } from 'react-router';
import "./HotelRoomOverview.css";
import {useLocation, withRouter} from 'react-router-dom';


const HotelRoomOverview = (props) => {
  const currentLocation = useLocation()
  const {img, location, title, description, star, price, total} = currentLocation.state.data
  console.log(currentLocation.state.data,"successful")
  
  return (
    <div className='roomoverview'>
      <div className='display-image'>
        {/* HotelRoomOverview  */}
        <br></br>
      
        <img src = {img}/>
        {/* <p> {location}</p> */}
        
      </div>
      <br></br>
      <br></br>

      <div>
        <h1> {title} </h1>
        <br></br>
        <h5> {location} </h5>
        <br></br>
        <br></br>
      </div>

      <div className='Amenities'>
        <h1> Amenities</h1> <br></br>
        <input type="checkbox"/>
        <label> Daily Continental Breakfast </label> <br></br>
        <input type="checkbox"/>
        <label> Access to fitness room </label> <br></br>
        <input type="checkbox"/>
        <label> Access to Swimming Pool/Jacuzzi </label> <br></br>
        <input type="checkbox"/>
        <label> Daily Parking </label> <br></br>
        <input type="checkbox"/>
        <label> All meals included (Breakfast, Lunch, Dinner) </label> <br></br>
        </div>

    </div>
  )
}

export default withRouter(HotelRoomOverview)