<template>
  <BannerBox />
  <CategoryButton2 />
  <v-card-text
    class="mx-3 pa-0 mt-4"
    style="font-family: dream"
  >
    {{ dealText }}
  </v-card-text>
  <ProductContainer
    v-for="product in productList"
    :key="product.id"
    :product-name="product.name"
    :product-price="product.price"
    :discount-rate="product.discountRate"
    :img-url="product.imageUrl"
    :link="product.link"
    :market-name="product.marketName"
  />
</template>

<script>
import BannerBox from '@/components/organisms/Home/BannerBox.vue'
import CategoryButton2 from '@/components/molecules/CategoryButton2.vue'
import ProductContainer from '@/components/molecules/ProductContainer.vue'

export default {
  components: { 
    BannerBox,
    ProductContainer,
    CategoryButton2
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

    dealText: function() {
      // TODO
      return this.currentMarket+ '-' +this.currentCategory
    }


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
    
    await this.$store.dispatch('GetProductListApi/FETCH_EXCHANGERATE_API')
  },
}
</script>
<style scoped>
a {
  text-decoration: none;
}

.test2{
  width: auto;
  height: 80px;
  background-image: url("@/assets/ticket.png");
  background-position: center;
  background-size: 100%;
}

</style>