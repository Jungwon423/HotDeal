<template>
  <v-container
    class="pa-0"
  >
    <!-- 위에 있는 탭 -->
    <v-tabs
      v-model="tab"
      bg-color="transparent"
      color="#BCAAA4"
      grow
    >
      <v-tab 
        v-for="item in items"
        :key="item"
        :value="item"
        style="font-size:12px; font-family:dream"
      >
        {{ item }}
      </v-tab>
    </v-tabs>

    <!-- 홍보 배너 -->
    <v-carousel
      :show-arrows="false"
      hide-delimiters="true"
      height="auto"
    > 
      <v-carousel-item
        src="@/assets/cate1.png"
        cover
      />

      <v-carousel-item
        src="@/assets/cate2.png"
        cover
      />

      <v-carousel-item
        src="@/assets/cate3.png"
        cover
      />
    </v-carousel>
  </v-container>
</template>

<script>
export default {
    data () {
      return {
        tab: 'Appetizers',
        categoryNames: ['fashion_beauty', 'furniture_living', 'homeAppliances_computers', 'travel_car']
      }
    },
}
</script>