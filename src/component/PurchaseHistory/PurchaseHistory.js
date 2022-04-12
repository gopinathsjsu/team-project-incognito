import Axios from "axios";
import React, { useEffect, useState } from "react";

function PurchaseHistory() {
  const [bookingHistory, setBookingHistory] = useState([]);
  // const [purchasedProducts, setPurchasedProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [currentPage, setCurrentPage] = useState(1);
  const [itemsPerPage, setItemsPerPage] = useState(5);

    useEffect(() => {
      getBookingHistory();
    }, []);

    const editBooking = () => {
        console.log("Edit booking axios");
    }

    const cancelBooking = () => {
        console.log("Cancel booking");
    }
 
    const getBookingHistory = () => {
        const history = [
            {
                id: "1",
                hotelName:"Raddisson",
                hotelLocation:"Santa Cruz",
                hotelImage:"https://images.pexels.com/photos/338504/pexels-photo-338504.jpeg",
                hotelDescription:"Santa Cruz Hotels",
                hotelPrice: "1000"
            },  {
                id: "2",
                hotelName:"Triago",
                hotelLocation:"Santa Cruz",
                hotelImage:"https://images.pexels.com/photos/338504/pexels-photo-338504.jpeg",
                hotelDescription:"San Francisco Hotels",
                hotelPrice: "1000"
            }
        ]
        setBookingHistory(history);
    }

  let renderPurchases = null;
  if (bookingHistory.length === 0) {
    renderPurchases = () => {
      return <div>No Purchases till now...</div>;
    };
  } else {
    renderPurchases = bookingHistory.map((pro) => {
      return (
        <div className="home_cards mb-4">
          <div className="home_card card">
            <div
              className="purchase_item_header"
              style={{ backgroundColor: "rgb(243, 234, 223)" }}
            >
              <p className="purchase_item_price">Room Price ${pro.hotelPrice}</p>
              <p style={{ width: "70%" }} className="purchase_item_price">
                Booking Id #{pro.id}
              </p>
            </div>

            <hr style={{ marginTop: "-2px" }}></hr>
            <div className="item">
              <img src={pro.hotelImage} className="card-img-left" alt="..." />

              <div style={{ marginLeft: "10px" }} className="item-details">
                <h5 className="card-title">{pro.hotelName}</h5>

                <p className="card-text">{pro.hotelDescription}</p>


                <button onClick={editBooking} className="btn-sm btn-dark">Edit</button>
                <button onClick={cancelBooking} className="btn-sm btn-dark">Cancel</button>

              </div>
            </div>
          </div>
        </div>
      );
    });
  }

  return (
    <div>
      <div>
        <h1 style={{ marginLeft: "30px", color: "#ff7779" }}>Stay History</h1>
        <div className="profile_favourites">
          <div className="container-fluid mx-1">
            <div className="row mt-5 mx-1">
              <div className="col-md-9">
                <div className="row"> {renderPurchases} </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default PurchaseHistory;