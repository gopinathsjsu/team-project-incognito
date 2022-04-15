import axios from "axios";
const API_URL = "http://localhost:8080/api/auth/";
const API_book = "http://localhost:8080/hotel-booking/"
class AuthService {
  login(username, password) {
    return Promise.resolve(localStorage.setItem("user", "Shreyansh"))
    // return axios
    //   .post(API_URL + "signin", {
    //     username,
    //     password
    //   })
    //   .then(response => {
    //     if (response.data.accessToken) {
    //       localStorage.setItem("user", JSON.stringify(response.data));
    //     }
    //     return response.data;
    //   });
  }
  logout() {
    return Promise.resolve(localStorage.removeItem('user'));
  }

  register(username, email, password) {
    return axios.post(API_URL + "signup", {
      //username,
      email,
      password
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
      .post(API_book + "rewards/"+email)
       .then(response => {
         if (response.data.accessToken) {
           localStorage.setItem("balance", JSON.stringify(response.data));
         }
         return response.data;
       });
  }

  getBookingConfirmation(roomType, fromDate, toDate, daily_continental_breakfast, access_to_fitness_room, access_to_swimming_Pool_Jacuzzi, daily_parking, all_meals_included){
    let x = localStorage.getItem('user')
    let email = x.username;
    return axios
       .post(API_URL + "confirmbooking", {
        //"emailID": "ravi@gmail123.com",
        email,
        //"phoneNumber": "+1 9876543211",
        //"roomType": "family",
        roomType,
        //"fromDate": "2022-11-21",
        fromDate,
        //"toDate": "2022-01-29",
        toDate,
        amenities: {
        daily_continental_breakfast,
        access_to_fitness_room,
        access_to_swimming_Pool_Jacuzzi,
        daily_parking,
        all_meals_included
      }
      })
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