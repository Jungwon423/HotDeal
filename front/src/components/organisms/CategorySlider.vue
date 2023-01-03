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
        v-for="(categoryName, i) in categoryNames"
        :key="categoryName"
        :value="categoryName"
        style="font-size:12px; font-family:dream"
      >
        {{ categoryNameSub[i] }}
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
        tab: 'Category',
        categoryNames: ['fashion_beauty', 'furniture_living', 'homeAppliances_computers', 'travel_car']
      }
    },

    computed: {
      categoryNameSub() { //categoryNameSubstitution
        return this.categoryNames.map(categoryName => this.$categoryMap.get(categoryName))
      },
  },
}
</script>