<template>
  <v-tabs
    v-model="tab"
    justify="center"
    bg-color="transparent"
    color="black"
    grow
  >
    <v-tab
      v-for="tab in info"
      :key="tab"
    >
      {{ tab }}
    </v-tab>
  </v-tabs>
  <v-img src="https://m.media-amazon.com/images/W/WEBP_402378-T2/images/S/aplus-media-library-service-media/baf925ef-8d72-4b5e-9f8e-45268757be6f.__CR0,0,1464,600_PT0_SX1464_V1___.jpg" />
  <v-img src="https://m.media-amazon.com/images/W/WEBP_402378-T2/images/S/aplus-media-library-service-media/78ec524a-3178-4a16-bd7c-94492654f62e.__CR0,0,1464,600_PT0_SX1464_V1___.jpg" />
  <v-img src="https://m.media-amazon.com/images/W/WEBP_402378-T2/images/S/aplus-media-library-service-media/0b1a01a0-d5c2-465a-941d-cd8e0e17b04c.__CR0,0,1464,600_PT0_SX1464_V1___.jpg" />

  <ProductDetail />
</template>

<script>
import ProductDetail from '@/components/organisms/ProductInfo/additional/ProductDetail.vue';

export default {
  components: {
    ProductDetail
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