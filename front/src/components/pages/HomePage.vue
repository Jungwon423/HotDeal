<template>
  <v-app>
    <v-main>
      <TitleHeader />
      <CategorySlider />
      <AdvertisingBox />
      <MarketList />
      <ProductList />
      <!-- <CategoryList /> -->
    </v-main>
  </v-app>
</template>

<script>
import TitleHeader from '@/components/organisms/Home/title/TitleHeader.vue'
import MarketList from '@/components/organisms/Home/MarketList.vue'
import AdvertisingBox from '@/components/organisms/Home/AdvertisingBox.vue'
import CategorySlider from '@/components/organisms/Home/category/CategorySlider.vue'
// import CategoryList from '@//components/organisms/CategoryList.vue';
import ProductList from '@/components/organisms/Home/ProductList.vue'

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

<style>
@font-face {
  font-family: 'dream';
  src: url('@/assets/fonts/SCDream6.ttf') format('truetype');
  font-weight: 700;
}
</style>
