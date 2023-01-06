<template>
  <BannerBox />
  <div id="app">
    <router-link to="category">
      Go to Category Page
    </router-link>
    <router-view />
  </div>

  <v-card-text
    class="mx-3 pa-0 mt-4"
    style="font-family: dream"
  >
    오늘의 핫딜 Top 5!
  </v-card-text>
  <ProductContainer
    v-for="product in productList"
    :key="product.id"
    :product-name="product.name"
    :product-price="product.price"
    :discount-rate="product.discountRate"
    :img-url="product.imageUrl"
    :link="product.link"
  />
</template>

<script>
import BannerBox from './BannerBox.vue'
import ProductContainer from '../molecules/ProductContainer.vue'

export default {
  components: { 
    BannerBox,
    ProductContainer
  },

  computed: {
    productList: function () {
      return this.$store.state.GetProductListApi.productList
    },

    currentCategory: function () {
      return this.$store.state.GetProductListApi.currentCategory
    },

    currentMarket: function () {
      return this.$store.state.GetProductListApi.currentMarket
    },


  },

  watch: {
    currentCategory(val) {
      this.$store.dispatch('GetProductListApi/FETCH_PRODUCTLIST_API')
    },
    currentMarket(val) {
      this.$store.dispatch('GetProductListApi/FETCH_PRODUCTLIST_API')
    },
  },

  async created() {
    await this.$store.dispatch('GetProductListApi/FETCH_PRODUCTLIST_API')
  },
  methods: {
    gopage() {
      this.$router.push('/info')
    },
  },
}
</script>
<style scoped>
a {
  text-decoration: none;
}

</style>