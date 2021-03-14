import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import Buefy from 'buefy'
import 'buefy/dist/buefy.css'




Vue.use(Buefy)
Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */

//sets currentToken and User  
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

//does this set the header? Would I put this when I make the calls?
if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {

    token: currentToken || '',
    user: currentUser || {},
    byLocationBoard: [],
    restaurantCard: {
      
    },
    searchLocation: '',
    searchDirections: '',
    radius: '',
    restaurants: [],
    thumbsUpList: [],
    invitees: []
  },
  mutations: {
    SET_RESTAURANT_LIST(state, restaurants){
      state.restaurants = restaurants;
    },
    SET_THUMBS_UP_LIST(state, thumbsUpList){
      state.thumbsUpList = thumbsUpList;
    },
    SET_INVITEES_LIST(state, invitees){
      state.invitees = invitees;
    },
    //sets the auth token
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },

    //sets the user
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },

    

    },

    

    //removes token and user from local storage, sets token and user to blank, 
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      localStorage.removeItem('currentUsername')
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },

    SET_SEARCH_LOCATION(state, location) {
      state.searchLocation = location;
      state.searchDirections = state.searchLocation.split(' ').join('+');
    },

    SET_SEARCH_RADIUS(state, radius) {
      state.radius = radius;

    }
  }
})
