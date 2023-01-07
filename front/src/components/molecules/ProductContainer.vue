<template>
  <router-link
    :to="{ name: 'productInfo', params: { productId: productName } }"
  >
    <v-container class="pl-5">
      <v-card
        class="mx-auto"
        max-width="450"
        max-height="370"
      >
        <div class="image-container pa-2">
          <v-img
            height="250"
            :src="imgUrl"
          />
        </div>
        <v-divider class="mx-4" />

        <span class="discount">{{ discountRate }}% </span>
        <span class="price"> {{ discountedPrice }}원 </span>
        <span class="original"> {{ originalPrice }}원 </span>
        <v-card-title>{{ productName }}</v-card-title>

        <v-card-text>
          <v-row
            align="center"
            class="mx-0"
          >
            <v-rating
              value="3.5"
              color="#FFB300"
              empty-icon="mdi-star-outline"
              full-icon="mdi-star"
              half-icon="mdi-star-half"
              density="comfortable"
              half-increments
              readonly
              size="14"
            />

            <div
              class="rating"
              style="font-weight: bolder"
            >
              3.5 (6504)
            </div>
          </v-row>

          <div class="my-4">
            Amazon
          </div>
        </v-card-text>
      </v-card>
    </v-container>
  </router-link>
</template>

<script>
export default {
  name: 'ProductContainer',
  props: {
    productName: {
      type: String,
      required: true,
    },
    productPrice: {
      type: Number,
      required: true,
    },
    discountRate: {
      type: Number,
      required: true,
    },
    imgUrl: {
      type: String,
      required: true,
    },
    link: {
      type: String,
      required: true,
    },
  },
  computed: {
    discountedPrice: function() {
      return Math.floor(this.$store.state.GetProductListApi.dollar*this.productPrice)
    },
    originalPrice: function() {
      return Math.floor(this.$store.state.GetProductListApi.dollar*this.productPrice/(100-this.discountRate)*100)
    },

    dollar: function() {
      return this.$store.state.GetProductListApi.dollar
    }
  }
}
</script>

<style>
.image-container{
    height:300;
    display: flex;
    flex-direction: column;
    justify-content: center;
    background-color: white;
}
@import '@/assets/styles/ProductContainer.css';
</style>
