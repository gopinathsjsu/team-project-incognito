import React, { useState } from 'react';
import './SearchResult.css';
import FavoriteBorderIcon from "@material-ui/icons/FavoriteBorder";
import StarIcon from "@material-ui/icons/Star";
import { Button } from '@material-ui/core';
import { DateRangePicker } from 'react-date-range';
import Details from './Details';

function SearchResult({
    img,
    location,
    title,
    description,
    star,
    price,
    total,
}
) {
    const [showSearch, setShowSearch] = useState(false);
    return (
        <div className='searchResult'>
            <img src={img} alt="" />
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
                        <h2></h2>
                        <p></p>
                        <Button onClick={() => setShowSearch(!showSearch)} variant='outlined'>Book</Button>
                    </div>
                    {showSearch && <Details/>}
                </div>
            </div>
        </div>
    )
}

export default SearchResult