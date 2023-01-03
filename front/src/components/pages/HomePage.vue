<template>
  <v-app>
    <v-main>
      <TitleHeader />
      <CategorySlider />
      <CategoryList />
      <ProductList />
    </v-main>
  </v-app>
</template>
  
  <script>
  import TitleHeader from '@/components/organisms/TitleHeader.vue';
  import CategorySlider from '@//components/organisms/CategorySlider.vue';
  import CategoryList from '@//components/organisms/CategoryList.vue';
  import ProductList from '@//components/organisms/ProductList.vue';
  
  export default {
    name: 'HomePage',
  
    components: {
      TitleHeader,
      CategorySlider,
      CategoryList,
      ProductList
    },
  }
  </script>
  
  <style>
  @font-face {
    font-family:'dream';
    src: url('@/assets/fonts/SCDream6.ttf') format('truetype');
    font-weight: 700;
  }
  
  </style>