import React, {useState} from 'react';
import {Link} from 'react-router-dom';
import './SearchResult.css';
import FavoriteBorderIcon from "@material-ui/icons/FavoriteBorder";
import StarIcon from "@material-ui/icons/Star";
import HotelRoomOverview from '../RoomDetails/HotelRoomOverview';
// import {withRouter} from 'react-router';
 
// import { useNavigate } from 'react-router';
// import { Navigate } from 'react-router';

function SearchResult({
    img,
    location,
    title,
    description,
    star,
    price,
    total,
}) 
{
    const stateData = {
        img:img,
        location:location,
        title:title,
        description:description,
        star:star,
        price:price,
        total:total
    }
    
// {   const [openHotelOverviewPage, SetOpenHotelOverviewPage] = useState(false);
    // const navigate = useNavigate();
    const handleOpenImage = () => {
        // SetOpenHotelOverviewPage(true);
        console.log("Hello, iM OPENING Hotel overview page");
       window.location.pathname ="/roomdetails"
    };

    return (
        <div className='searchResult'>
            <Link to={{pathname: '/roomdetails/', state:{data:stateData}}}>
                <img style={{width:"550px", height:"300px"}}src ={img}/>
            </Link>
            {/* <img onClick={handleOpenImage} src={img} alt="" /> */}
            <FavoriteBorderIcon className="searchResult__heart" />

            <div className='searchResult__info'>
                <div className="searchResult__infoTop">
                    <p>{location}</p>
                    <h3>{title}</h3>
                    <p>____</p>
                    <p>{description}</p>
                </div>

                <div className="searchResult__infoBottom">
                    <div className="searchResult__stars">
                        <StarIcon className="searchResult__star" />
                        <p>
                            <strong>{star}</strong>
                        </p>
                    </div>
                    <div className='searchResults__price'>
                        <h2>{price}</h2>
                        <p>{total}</p>
                    </div>

                    {/* <Link to={{pathname: '/roomdetails/'}}
                    state={stateName:'mounica'}> go to the hotel </Link> */}
                    {/* <Link to={{pathname: '/roomdetails/', state:{data:stateData}}}>Go back to Hotel</Link> */}

                </div>
            </div>
        </div>
    )
}

export default SearchResult