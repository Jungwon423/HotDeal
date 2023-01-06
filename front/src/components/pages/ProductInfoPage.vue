<template>
  <ProductAppBar />
  <ProductTitle />
  <RelatedProduct />
  <ProductTab />

  
  <v-sheet class="sticky d-flex grow-1">
    <v-btn
      color="#FF4081"
      class="buy"
    >
      구매하기
    </v-btn>
  </v-sheet>
  <PageFooter />
</template>

<script>
import ProductAppBar from '@/components/organisms/ProductAppBar.vue'
import ProductTitle from '@/components/organisms/ProductTitle.vue'
import ProductTab from '@/components/organisms/ProductTab.vue'
import PageFooter from '@/components/organisms/PageFooter.vue'
import RelatedProduct from '@/components/organisms/RelatedProduct.vue'

export default {
  components: {
    ProductAppBar,
    ProductTitle,
    RelatedProduct,
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
}
</script>

<style>
@import '@/assets/styles/InfoView.css';
.line{
  background-color:grey;
  height:1px;
}
</style>
