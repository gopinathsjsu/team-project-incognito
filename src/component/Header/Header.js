import React from 'react';
import './Header.css'
import SearchIcon from "@material-ui/icons/Search";
import LanguageIcon from "@material-ui/icons/Language";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import { Avatar } from '@material-ui/core';
import { Link } from "react-router-dom";
import { Button } from '@material-ui/core'
function Header() {
  return (
    <div class='header'>
        <Link to='/'>
        <img
            className='header__icon'
            src="https://i.pinimg.com/originals/3c/bf/be/3cbfbe148597341fa56f2f87ade90956.png"
            alt=""
        />
        </Link>
        <Link to='./Login' className="header__right">
            <Button variant='outlined'>Login</Button>
        </Link>        
    </div>
  )
}

export default Header
