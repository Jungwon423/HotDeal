<template>
  <HomeHeader />
  <AdvertisingBox />
  <CategoryMain />
  <product-container />
  <product-set />
</template>
    
  <script>
  import CategoryMain from '@/web/organisms/CategoryButtons.vue'
  import AdvertisingBox from '@/web/organisms/AdvertisingBox.vue'
  import HomeHeader from '@/web/organisms/HomeHeader.vue'
  import ProductContainer from '@/web/molecules/ProductContainer.vue'
  import ProductSet from '../organisms/ProductSet.vue'
  export default {
    name: 'CategoryButtons',
    components : {
      HomeHeader,
      CategoryMain,
      AdvertisingBox,
      ProductContainer,
      ProductSet
    },
      
    computed: {
      categoryNameSub() {
        //categoryNameSubstitution
        return this.$categoryMap.get(this.categoryName)
      },
    },
    
    methods: {
      onClick: function (evt) {
        this.$store.commit(
          'GetProductListApi/SET_CURRENTCATEGORY',
          this.categoryName
        )
      },
    },
  }
  </script>