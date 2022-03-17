import React from 'react'
import "./Titlebar.css"

function Titlebar() {
  return (
    <div className='wrapper'>
        <img className='logo'
              src={ "/Images/Logo.png"}
              alt="home">
        </img>
        <div className='nav_title'>
            <h1>Hotel Booking</h1>
        </div>

        <div className='nav_icons'>
            <button className='login_button'><b>Login</b></button>
        </div>
    </div>
  )
}

export default Titlebar