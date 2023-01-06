<template>
  <v-app>
    <v-main>
      <TitleHeader />
      <CategorySlider />
      <MarketList />
      <ProductList />
      <!-- <CategoryList /> -->
    </v-main>
  </v-app>
</template>

<script>
import MarketList from '../organisms/MarketList.vue'
import TitleHeader from '@/components/organisms/TitleHeader.vue'
import CategorySlider from '@//components/organisms/CategorySlider.vue'
// import CategoryList from '@//components/organisms/CategoryList.vue';
import ProductList from '@//components/organisms/ProductList.vue'

export default {
  name: 'HomePage',

  components: {
    TitleHeader,
    MarketList,
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
