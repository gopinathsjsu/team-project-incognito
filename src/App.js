import "./App.css";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Header, Footer, Login, SearchPage, Home, Signup } from "./component";
import SignInOutContainer from "./containers/SignInOutContainer";
import HotelRoomOverview from "./component/RoomDetails/HotelRoomOverview";
import Payment from "./component/Payments/payment";
import PurchaseHistory from "./component/PurchaseHistory/PurchaseHistory";
import EmployeePage from "./component/EmployeePage/EmployeePage"


function App() {
  const [valid, setvalidState] = useState("false");
  useEffect(() => {
    const user = AuthService.getCurrentUser();
    console.log(user)
    if(user == null){
      setvalidState(true)
    }
  }, [])

  return (
    <div className="app">
      <Router>
        <Header />
        <Switch> 
            <Route path="/search">
              <SearchPage />
            </Route>
          <Route path="/login">
            <SignInOutContainer />
          </Route>
          <Route path="/signin">
            <Login />
          </Route>
          <Route path="/signup">
            <Signup />
          </Route>
          {/* <Route path="/payment">
            <Payment />
          </Route> */}
          <Route path="/purchaseHistory">
            <PurchaseHistory />
          </Route>
          <Route path="/roomDetails" >
            <HotelRoomOverview/>
          </Route>
          <Route path="/payment">
            <Payment />
          </Route>
          <Route path="/employeePage">
            <EmployeePage />
          </Route>
          
          <Route path="/">
            <Home />
          </Route>

        </Switch>
        {/* <Footer /> */}
      </Router>
    </div>
  );
}

export default App;