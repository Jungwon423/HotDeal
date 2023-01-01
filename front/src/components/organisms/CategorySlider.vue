<template>
  <v-container
    align="left"
    class="border=5px"
  >
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
    <v-carousel
      hide-delimiters
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
        items: [
          '패션/뷰티', '가전', '자동차', '가구',
        ],
      }
    },
}
</script>