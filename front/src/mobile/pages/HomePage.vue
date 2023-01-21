<template>
  <v-app>
    <v-main>
      <TitleHeader />
      <MarketList />
      <AdvertisingBox />
      <CategorySlider />
      <ProductList />
      <!-- <CategoryList /> -->
    </v-main>
  </v-app>
</template>

<script>
import TitleHeader from '@/mobile/organisms/Home/title/TitleHeader.vue'
import MarketList from '@/mobile/organisms/Home/MarketList.vue'
import AdvertisingBox from '@/mobile/organisms/Home/AdvertisingBox.vue'
import CategorySlider from '@/mobile/organisms/Home/category/CategorySlider.vue'
// import CategoryList from '@/mobile/organisms/CategoryList.vue';
import ProductList from '@/mobile/organisms/Home/ProductList.vue'

export default {
  name: 'HomePage',

  components: {
    TitleHeader,
    MarketList,
    AdvertisingBox,
    CategorySlider,
    // CategoryList,
    ProductList,
  },
}
</script>

<style scoped>
@font-face {
  font-family: 'dream';
  src: url('@/assets/fonts/SCDream6.ttf') format('truetype');
  font-weight: 700;
}
</style>
