<template>
  <v-card-text style="font-family:dream">
    오늘의 핫딜
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
import ProductContainer from '../molecules/ProductContainer.vue';

export default {
    components: { ProductContainer }, 
    
    computed: {
      productList: function() {
        return this.$store.state.GetProductListApi.productList
      },

      currentCategory: function() {
        return this.$store.state.GetProductListApi.currentCategory
      }
    },

    watch: {
      currentCategory(val) {
        this.$store.dispatch('GetProductListApi/FETCH_PRODUCTLIST_API')
      }
    },

    async created() {
      await this.$store.dispatch('GetProductListApi/FETCH_PRODUCTLIST_API')
    }
    
}
</script>