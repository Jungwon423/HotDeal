<template>
  <v-system-bar
    elevation="0"
    height="8"
    color="#F5F5F5"
  />
  <v-app-bar
    color="white"
    elevation="1"
    height="55"
  >
    <span class="pt-2"><v-btn rounded>
      <v-icon
        color="black"
        size="xx-large"
      > mdi-arrow-left-thin</v-icon>
    </v-btn></span>
    <template #append>
      <span class="pt-2">
        <v-btn icon="mdi-magnify">
          <v-icon color="black" />
        </v-btn>
        <v-btn icon="mdi-cart">
          <v-icon color="black" />
        </v-btn>
      </span>
      <v-spacer />
    </template>
  </v-app-bar>

  <v-container class="pa-0">
    <v-sheet
      class="d-flex mx-0"
      color="white"
    >
      <v-avatar
        class="mx-4"
        color="white"
        size="40"
      >
        <v-img
          max-height="40"
          max-width="40"
          src="@/assets/amazon.jpg"
        />
      </v-avatar>
      <div
        style="font-size: small"
        class="my-3"
      >
        Amazon
      </div>
      <div class="num">
        6005
        <v-btn
          flat
          color="transparent"
          icon="mdi-heart"
        >
          <v-icon
            size="small"
            color="#757575"
          />
        </v-btn>
      </div>
    </v-sheet>
    <div class="box">
      <v-img
        width="100%"
        height="100%"
        src="@/assets/example.png"
      />
    </div>
    <v-sheet class="d-flex pa-2 grow-1">
      <div class="productName">
        Acer Nitro 5 AN515-55-53E5 Acer Nitro 5 AN515-55-53E5
      </div>
    </v-sheet>
    <v-sheet>
      <v-rating
        class="px-1 mx-1"
        value="3.5"
        color="#FFB300"
        empty-icon="mdi-star-outline"
        full-icon="mdi-star"
        half-icon="mdi-star-half"
        density="default"
        half-increments
        readonly
        size="14"
      />
      <span class="review"> 리뷰 30개 </span>
    </v-sheet>
    <span class="discount2">30% </span>
    <span> 60000원 </span>
    <v-divider />
    <RelatedProduct />

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
  </v-container>
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
import PageFooter from '@/components/organisms/PageFooter.vue'
import ProductDetail from '@/components/organisms/ProductDetail.vue'
import RelatedProduct from '@/components/organisms/RelatedProduct.vue'

export default {
  components: {
    // CategoryList,
    RelatedProduct,
    ProductDetail,
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
