<template>
  <v-row
    class="text-center"
    justify="center"
  >
    <v-col
      cols="12"
      md="3"
    >
      <div class="today">
        <div
          class="pa-4 secondary text-no-wrap rounded-pill grow-1"
        >
          <span class="hot">Today's Hot</span>
        </div>
      </div>
    </v-col>
  </v-row>
  <div id="app">
      <router-link to="apple">apple</router-link>
      <router-link to="google">google</router-link>
      <router-view></router-view>
    </div>

  <v-card-text class="mx-3 pa-0 mt-4" style="font-family: dream">
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
import ProductContainer from '../molecules/ProductContainer.vue'

export default {
  components: { ProductContainer },

  computed: {
    productList: function () {
      return this.$store.state.GetProductListApi.productList
    },

    currentCategory: function () {
      return this.$store.state.GetProductListApi.currentCategory
    },
  },

  watch: {
    currentCategory(val) {
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
.today{
  width: auto;
  height: 80px;
  background-image: url("@/assets/todayshot.png");
}
.hot{
  font-family: "dream";
  font-size: large;
  font-weight: bold;
  color : white;
  text-align: center;
}
</style>