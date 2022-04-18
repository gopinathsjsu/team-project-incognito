import axios from "axios";
const API_URL = "api/auth/";
const API_book = "hotel-booking/";
const API_rewards = ""

//axios.defaults.proxy.host = "http://localhost"
//axios.defaults.proxy.port = "8086"

class AuthService {
  login(email, password) {
    //return Promise.resolve(localStorage.setItem("user", "Shreyansh"))
    console.log("Getting booking")
    return axios
      .post(API_URL + "signin", {
        email,
        password
      })
      .then(response => {
        if (response) {
          console.log(response.data.username)
          localStorage.setItem("user", JSON.stringify(response.data.username));
          localStorage.setItem("token", JSON.stringify(response.data.token));
          
        }
        return response.data;
      });
  }
  logout() {
    return Promise.resolve(localStorage.removeItem('user'));
  }

  register( username, email, password, phoneNumber) {
    console.log(username)
    return axios.post(API_URL + "signup", {
      username,
      email,
      password,
      phoneNumber,
    }).then(response => {
      console.log(response);
    }).catch(e=> {
      console.log(e);
    });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));
  }

  getBalanceUser(){
    //return Promise.resolve(JSON.stringify({"balance":1000}));
    let x = localStorage.getItem('user')
    let email = x.username;
    return axios
      .post(API_rewards + "rewards/"+email)
       .then(response => {
         if (response.data.accessToken) {
           localStorage.setItem("balance", JSON.stringify(response.data));
         }
         return response.data;
       });
  }

  getBookingDetails(){
    let x = localStorage.getItem('user')
    let email = x;
    email = email.replace(/\"/g,'')
    let token = localStorage.getItem('token');
    token = token.replace(/\"/g,'')
    console.log(token)
    let yourConfig = {
      headers: {
          'Content-Type' : 'application/json',
         'Authorization': "Bearer " + token,
      }
   }
    return axios
        .get(API_book+"getBooking/"+ email, yourConfig)
          .then(response => {
                  console.log(response)
                   return response;
          })
  }

  getBookingConfirmation(roomType, fromDate, toDate, daily_continental_breakfast, access_to_fitness_room, access_to_swimming_Pool_Jacuzzi, daily_parking, all_meals_included, numberOfRooms, number_of_children, number_of_adults){
    let x = localStorage.getItem('user')
    let email = x;
    email = email.replace(/\"/g,'')
    let token = localStorage.getItem('token');
    token = token.replace(/\"/g,'')
    let emailID = email
    console.log(fromDate)
    var date = new Date(fromDate.getTime());
    date.setHours(0, 0, 0, 0);
    console.log(date)
    let yourConfig = {
      headers: {
          'Content-Type' : 'application/json',
         'Authorization': "Bearer " + token,
      },
        emailID,
        roomType,
        fromDate,
        toDate,
        numberOfRooms,
        number_of_children,
        number_of_adults,
        "amenities": {
        daily_continental_breakfast,
        access_to_fitness_room,
        access_to_swimming_Pool_Jacuzzi,
        daily_parking,
        all_meals_included
      }
   }
    console.log("Welcome")
    return axios
       .post(API_book + "createBooking", {
        emailID,
        roomType,
        fromDate,
        toDate,
        numberOfRooms,
        number_of_children,
        number_of_adults,
        "amenities": {
        daily_continental_breakfast,
        access_to_fitness_room,
        access_to_swimming_Pool_Jacuzzi,
        daily_parking,
        all_meals_included
      }
       }, yourConfig
        )
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        return response.data;
      });
  }

  getUserUpdate(roomType, fromDate, toDate, daily_continental_breakfast, access_to_fitness_room, access_to_swimming_Pool_Jacuzzi, daily_parking, all_meals_included, numberOfRooms, number_of_children, number_of_adults, bookid)
  {
    let x = localStorage.getItem('user')
    let email = x;
    email = email.replace(/\"/g,'')
    let token = localStorage.getItem('token');
    token = token.replace(/\"/g,'')
    let emailID = email
    console.log(fromDate)
    var date = new Date(fromDate.getTime());
    date.setHours(0, 0, 0, 0);
    let yourConfig = {
      headers: {
          'Content-Type' : 'application/json',
         'Authorization': "Bearer " + token,
      },
        emailID,
        roomType,
        fromDate,
        toDate,
        numberOfRooms,
        number_of_children,
        number_of_adults,
        "amenities": {
        daily_continental_breakfast,
        access_to_fitness_room,
        access_to_swimming_Pool_Jacuzzi,
        daily_parking,
        all_meals_included
      }
   }
    return axios
       .put(API_book + "updateBooking/"+ bookid, {
        emailID,
        roomType,
        fromDate,
        toDate,
        numberOfRooms,
        number_of_children,
        number_of_adults,
        "amenities": {
        daily_continental_breakfast,
        access_to_fitness_room,
        access_to_swimming_Pool_Jacuzzi,
        daily_parking,
        all_meals_included
      }
       },yourConfig
       )
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        return response.data;
      });
  }

  validUser(){
      //var x = JSON.parse(localStorage.getItem('user'));
      var x = JSON.stringify(localStorage.getItem('user'))
      var y = localStorage.getItem('user');
      console.log("Valid User")
      console.log(y)
      if(y == null){
          return false
      }
      else{
          return true
      }
  }
}
export default new AuthService();

export function authHeader() {
    const user = JSON.parse(localStorage.getItem('user'));
    if (user && user.accessToken) {
      return { Authorization: 'Bearer ' + user.accessToken };
    } else {
      return {};
    }
  }