import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import RestaurantFinder from '../views/restaurantFinder'
import RestaurantDetails from '../views/RestaurantDetails'
import InviteeView from '../views/InviteeView.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: "restaurantFinder",
      component: RestaurantFinder,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/:id",
      name: "details",
      component: RestaurantDetails,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/invite/:uniqueId/",
      name: "inviteeView",
      component: InviteeView,
      meta: {
        requiresAuth: false
      }
    }

    
  ]
})

router.beforeEach((to, from, next) => {

  // Determines if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
