import './App.css';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Header, Footer, Login, SearchPage, Home, Signup, AuthService} from './component';
import SignInOutContainer from './containers/SignInOutContainer';
import React, { useEffect, useState } from 'react'

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
          <Route path="/">
            <Home />
          </Route>
        </Switch>
        <Footer />
      </ Router>
    </div>
  );
}

export default App;
