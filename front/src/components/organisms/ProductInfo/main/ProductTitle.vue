<template>
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
        :src="marketImg"
      />
    </v-avatar>
    <div
      style="font-size: small"
      class="my-3"
    >
      {{ marketName }}
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
      :src="imgUrl"
    />
  </div>
  <v-sheet class="d-flex pa-2 grow-1">
    <div class="productName">
      {{ productName }}
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
  <span class="discount2">{{ discountRate }}% </span>
  <span> {{ addCommaPrice }}원 </span>
  <v-divider />
</template>

<script>
export default {
  name: 'ProductTitle',
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
    marketName: {
      type: String,
      requried: true,
    },
   },
   computed: {
      marketImg(){
        let url = require("@/assets/" + this.marketName + ".png")
        return url
      },
      addCommaPrice(){
        return Number(this.productPrice).toLocaleString('en')
      }
   }
  }
</script>