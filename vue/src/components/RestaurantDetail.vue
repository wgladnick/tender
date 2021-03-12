<template>
  <main>
    <div class="main-div" v-if="!isLoading">
      <div class="left-panel">
        <img class="yelp-image" v-bind:src="restaurant.image_url" />
      </div>
      <div class="right-panel">
          <div class="open-sign"> 
            <p v-for="hours in restaurant.hours" v-bind:key="hours.hours_type">
          <b-button
            v-bind:type="{
              'is-danger': !hours.is_open_now,
              'is-success': hours.is_open_now,
            }"
          >
           
            <span v-if="!hours.is_open_now">Currently Closed</span>
            <span v-else>Currently Open</span>
          </b-button>
        </p>
        </div>
                <p> <span class="bold-class">Price: <span class="has-text-success" >{{ restaurant.price }} </span> </span>
        <span class="detail-cats"
          v-for="category in this.categories"
          v-bind:key="category"
          > {{ category }}
        </span>
        <h1 class="title">{{ restaurant.name }}</h1>

     
        <div class="stars">
          <star-rating
            class="stars"
            :rating="restaurant.rating"
            :read-only="true"
            :increment="0.01"
            :show-rating="false"
            :star-size="25"
          />
          <a :href="restaurant.url" target="_blank"> View reviews on <img height="12px" width="12px" src="../assets/yelp.png">Yelp</a>
        </div>

        <div>
          
          
    
          </a>
        </div>
        <div>
          <span class="location-number"
            v-for="ln in restaurant.location.display_address"
            v-bind:key="ln.display_address"
          >
            {{ ln }}
          </span>
          <br>
        </div>
        
        <div>
          <span class="location-number">{{ restaurant.display_phone }} </span>
        </div>

      <div class="transactions">
          <b-button size="is-small">
            <i
              class="fas fa-check has-text-success"
              v-if="transactionTypes.includes('pickup')"
            ></i>
            <i
              class="fas fa-times has-text-danger"
              v-if="!transactionTypes.includes('pickup')"
            ></i>
            Takeout</b-button
          >
          <b-button size="is-small">
            <i
              class="fas fa-check has-text-success"
              v-if="transactionTypes.includes('delivery')"
            ></i>
            <i
              class="fas fa-times has-text-danger"
              v-if="!transactionTypes.includes('delivery')"
            ></i>
            Delivery</b-button
          >
          <b-button size="is-small">
            <i
              class="fas fa-check has-text-success"
              v-if="transactionTypes.includes('restaurant_reservation')"
            ></i>
            <i
              class="fas fa-times has-text-danger"
              v-if="!transactionTypes.includes('restaurant_reservation')"
            ></i>
            Reservations</b-button
          >
        </div>
 <div class="call-to-action">
            <a :href="`tel:${restaurant.phone}`">
              <b-button type="is-primary" rounded size="is-small">
                <i class="fas fa-phone-alt"></i>
                Call to order</b-button
              >
            </a>
            <a
            :href="`https://www.google.com/maps/dir/${
              this.$store.state.searchDirections
            }/${restaurant.location.display_address
              .toString()
              .split(' ')
              .join('+')}/`"
            target="_blank"
          >
              <b-button rounded size="is-small">
              <i class="fas fa-route"></i>
              Get Directions</b-button>
              </a>
          </div>
        <br />
      
        <br />
      </div>
      <div class="far-right-panel">
        <p>Hours:</p>
        <p v-for="hours in restaurant.hours" v-bind:key="hours.hours_type">
          <span  v-for="open in hours.open" v-bind:key="open.day">
            <span v-if="open.day === 6"
              >Sunday: {{ open.start }} - {{ open.end }}<br
            /></span>
            <span v-if="open.day === 0"
              >Monday: {{ open.start }} - {{ open.end }}<br
            /></span>
            <span v-if="open.day === 1"
              >Tuesday: {{ open.start }} - {{ open.end }}<br
            /></span>
            <span v-if="open.day === 2"
              >Wednesday: {{ open.start }} - {{ open.end }}<br
            /></span>
            <span v-if="open.day === 3"
              >Thursday: {{ open.start }} - {{ open.end }}<br
            /></span>
            <span v-if="open.day === 4"
              >Friday: {{ open.start }} - {{ open.end }}<br
            /></span>
            <span v-if="open.day === 5"
              >Saturday: {{ open.start }} - {{ open.end }}<br
            /></span>
          </span>
        </p>
      </div>
    </div>
  </main>
</template>

<script>
import RestaurantService from "../services/RestaurantService";
import StarRating from "vue-star-rating";

export default {
  name: "restaurant-detail",
  components: {
    StarRating,
  },
  data() {
    return {
      restaurant: {},
      isLoading: true,
      directionsAddress: [],
      transactionTypes: "",
      categories: [],
      category: ''
    };
  },
  created() {
    RestaurantService.getTheRestaurant(this.$route.params.id).then(
      (response) => {
        this.restaurant = response.data;
        this.isLoading = false;

        for (let i = 0; i < this.restaurant.transactions.length; i++) {
          this.transactionTypes +=
            this.restaurant.transactions[i].transactions + " ";
        }
        for (let j = 0; j < this.restaurant.categories.length; j++) {
          this.category = this.restaurant.categories[j].title;
          if (j < this.restaurant.categories.length - 1) {
            this.categories.push(this.category + " | ");
          } else {
            this.categories.push(this.category);
          }
        }
      }
    );
  },
};
</script>

<style scoped>
.location-number{
  font-weight:500;
  font-size:1.5em;
}
.main-div {
  display: flex;
  flex-direction: row;
  width: 100%;
  justify-content: center;
  align-items: center;
}
.yelp-image {
  width: 500px;
  height: 500px;
  object-fit: cover;
}
.left-panel {
  padding: 20px;
  margin-right: 25px;
}
.right-panel {
  padding: 20px;
  padding-top:4.5em;
  
}
.button.is-rounded {
  border-radius: 290486px;
  padding-left: calc(1em + 0.25em);
  margin-right: 10px;
  padding-right: calc(1em + 0.25em);
      padding: 20px;
    width: 12em;
    font-size: 1em;

}
.button.is-danger{
background-color:#e41942
}


.transactions > button.is-small {
  border: none;
  margin-right: 10px;
  margin-left: 10px;
  padding: 10px;
  font-size:.9em;
}

.transactions {  
margin-bottom:-3em;
margin-top:3em;
margin-left:-.5em;

}
.call-to-action{
  margin-top:5em;
  margin-bottom:-2em;
}
.far-right-panel{
  margin-left:7em;
}

.far-right-panel span,p{
  font-weight:bold;
}
.open-sign{
  margin-top:-3em;
  padding-bottom:3em;
}

.stars{
  width:max-content;
}

.detail-cats{
  font-weight:500;
  color:rgb(153, 152, 152)
}
.title{
  font-weight:bold;
  }

  .time{
    padding-bottom:1em;
  }

</style>