import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import restaurantFinder from '../views/restaurantFinder.vue'
import RestaurantDetails from '../views/RestaurantDetails'
import InviteeView from '../views/InviteeView.vue'
import Home from '../views/Home.vue'
import CreateInvite from '../views/CreateInvite.vue'
import Profile from '../views/Profile.vue'
import EditProfile from '../components/EditProfile.vue'
import InviteConfirmation from '../views/InviteConfirmation.vue'
import InviteDetailsPage from '../views/InviteDetails.vue'

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
   /* {
      path: '/',
      name: "home",
      component: Home,
      meta: {
        requiresAuth: true
      }
    },*/
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
      path: "/search/:id",
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
    },

    {
      path: "/",
      name: "restaurantFinder",
      component: restaurantFinder,
      meta: {
        requiresAuth: true
      }
    },

    {
      path: "/create",
      name: "createInvite",
      component: CreateInvite,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/profile",
      name: "profile",
      component: Profile,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/editprofile",
      name:"editProfile",
      component: EditProfile,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/confirmation",
      name:"inviteConfirmationPage",
      component: InviteConfirmation,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/invite/:id",
      name:"inviteDetailsPage",
      component: InviteDetailsPage,
      meta: {
        requiresAuth: true
      }
    },

  ]
});

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
