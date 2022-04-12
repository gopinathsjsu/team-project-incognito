import Axios from "axios";
import React, { useEffect, useState } from "react";

function PurchaseHistory() {
  const [purchasedProducts, setPurchasedProducts] = useState([]);
  // const [purchasedProducts, setPurchasedProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [currentPage, setCurrentPage] = useState(1);
  const [itemsPerPage, setItemsPerPage] = useState(5);

    useEffect(() => {
      getBookingHistory();
    }, []);

    const getBookingHistory = () => {
        console.log("hello")
    }

  let renderPurchases = null;

  if (purchasedProducts.length === 0) {
    renderPurchases = () => {
      return <div>No Purchases till now...</div>;
    };
  } else {
    renderPurchases = purchasedProducts.map((pro) => {
      return (
        <div className="home_cards mb-4">
          <div className="home_card card">
            <div
              className="purchase_item_header"
              style={{ backgroundColor: "rgb(243, 234, 223)" }}
            >
              <p className="purchase_item_price">Item Price ${pro.itemPrice}</p>
              <p style={{ width: "70%" }} className="purchase_item_price">
                Order Id #{pro._id}
              </p>
            </div>

            <hr style={{ marginTop: "-2px" }}></hr>
            <div className="item">
              <img src={pro.itemImage} className="card-img-left" alt="..." />

              <div style={{ marginLeft: "10px" }} className="item-details">
                <h5 className="card-title">{pro.itemName}</h5>

                <p className="card-text">{pro.itemDescription}</p>
                <p className="card-text">Quantity: {pro.qty}</p>

                {pro.giftMessage !== "" ? (
                  <p className="card-text">Gift Message: {pro.giftMessage}</p>
                ) : (
                  <p className="card-text"></p>
                )}

                {/* <button className="btn-sm btn-dark">Edit</button> */}
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