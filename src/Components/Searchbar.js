import React from 'react'
import "./Searchbar.css"

function Searchbar() {
  return (
    <div className='search'> 
        <input className="searchbar" type="text" placeholder='Search for a city or hotel'/>
        <button className='searchicon'> 
            <i class="search_icon material-icons md-36" id='searchicon'>search</i>
        </button>
        
    </div>
  )
}

export default Searchbar