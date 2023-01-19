<template>
  <div class="py-12 banner">
    <!-- 홍보 배너 -->
    <v-carousel
      :show-arrows="false"
      :hide-delimiters="true"
      height="auto"
    >
      <v-carousel-item
        src="@/assets/홈그림.png"
        cover
      />

      <v-carousel-item
        src="@/assets/carousel/cate2.png"
        cover
      />

      <v-carousel-item
        src="@/assets/carousel/cate3.png"
        cover
      />
    </v-carousel>
  </div>
</template>
<style scoped>
.banner{
  top:25%;
  position:relative;
}
</style>