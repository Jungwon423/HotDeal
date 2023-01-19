<template>
  <ProductAppBar />
  <ProductTitle 
    :key="$route.params.productId"
    :product-name="$route.query.productName"
    :product-price="$route.query.productPrice"
    :discount-rate="$route.query.discountRate"
    :img-url="$route.query.imgUrl"
    :market-name="$route.query.marketName"
  />
  <ProductTab />
  <!-- <RelatedProduct /> -->
  
  <v-sheet
    class="sticky d-flex grow-1"
  >
    <v-btn
      class="buy"
      :href="$route.query.link"
    >
      구매하기
    </v-btn>
  </v-sheet>
  <PageFooter />
</template>

<script>
import ProductTitle from '@/mobile/organisms/ProductInfo/main/ProductTitle.vue'
import ProductAppBar from '@/mobile/organisms/ProductInfo/bar/ProductAppBar.vue'
import ProductTab from '@/mobile/organisms/ProductInfo/additional/ProductTab.vue'
import PageFooter from '@/mobile/organisms/ProductInfo/footer/PageFooter.vue'
import RelatedProduct from '@/mobile/organisms/ProductInfo/RelatedProduct.vue'

export default {
  components: {
    ProductAppBar,
    ProductTitle,
    //RelatedProduct,
    ProductTab,
    PageFooter,
  },
  data() {
    return {
      info: ["제품사진","상세정보","문의"],
      links: [
        'Home',
        'About Us',
        'Services',
        'Contact Us',
      ],
    }
  },
  // created(){
  //   console.log("hi")
  //   console.log(this.$route.query.marketName)
  //   console.log(this.$route.params.productId)
  //   console.log(this.$route.query.productName)
  //   console.log(this.$route.query.productPrice)
  //   console.log(this.$route.query.link)
  // },
}
</script>

<style>
@import '@/assets/styles/ProductInfoPage.css';
.line{
  background-color:grey;
  height:1px;
}
</style>
