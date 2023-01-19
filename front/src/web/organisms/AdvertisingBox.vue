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
</template>
<style scoped>
.banner{
  padding:0;
  position:relative;
  top:-7%;
  background-color: beige;
}
</style>