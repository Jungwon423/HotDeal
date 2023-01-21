<template>
  <HomeHeader />
  <AdvertisingBox />
  <CategoryMain />
  <!--<product-container />-->
  <product-set />
  <box />
  <exhibition-set />
</template>
    
  <script>
  import CategoryMain from '@/web/organisms/CategoryButtons.vue'
  import AdvertisingBox from '@/web/organisms/AdvertisingBox.vue'
  import HomeHeader from '@/web/organisms/HomeHeader.vue'
  import ProductContainer from '@/web/molecules/ProductContainer.vue'
  import ProductSet from '@/web/organisms/ProductSet.vue'
  import box from '@/web/molecules/SideBox.vue'
  import ExhibitionSet from '@/web/organisms/ExhibitionSet.vue'
  export default {
    name: 'CategoryButtons',
    components : {
      HomeHeader,
      CategoryMain,
      AdvertisingBox,
      ProductSet,
      box,
      ExhibitionSet
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
  <style>
  
  </style>