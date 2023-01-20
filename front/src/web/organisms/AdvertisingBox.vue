<template>
  <div class="banner">
    <!-- 홍보 배너 -->
    <v-carousel
      :show-arrows="false"
      :hide-delimiters="true"
      height="500"
    >
      <v-carousel-item
        src="@/assets/홈그림.png"
        max-height="500px"
        cover
      />

      <v-carousel-item
        src="@/assets/carousel/cate2.png"
        max-height="500px"
        cover
      />

      <v-carousel-item
        src="@/assets/carousel/cate3.png"
        cover
        max-height="500px"
      />
    </v-carousel>
  </div>
  <div class="innerText1">
    Super Flash Sale
  </div>
</template>
<style scoped>
.banner{
  padding:0;
  position:relative;
  top:-3%;
  background-color: beige;
}
.innerText1{
  color:white;
  font-size: 50px;
  font-weight: 500;
  line-height: 36px;
  position:absolute;
  top:500px;
  left:300px;
}
</style>