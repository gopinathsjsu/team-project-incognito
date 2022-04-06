import axios from "axios";
const API_URL = "http://localhost:8080/api/auth/";
class AuthService {
  login(username, password) {
    return axios
      .post(API_URL + "signin", {
        username,
        password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        return response.data;
      });
  }
  logout() {
    localStorage.removeItem("user");
  }
  register(username, email, password) {
    return axios.post(API_URL + "signup", {
      //username,
      email,
      password
    });
  }
  hotel(startdate, enddate, location){

  }
  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }
  validUser(){
      var x = JSON.parse(localStorage.getItem('user'));
      if(x == null){
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