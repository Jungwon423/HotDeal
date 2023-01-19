<template>
  <HomeHeader />
  <AdvertisingBox />
  <CategoryMain />
</template>
    
  <script>
  import CategoryMain from '@/web/organisms/CategoryButtons.vue'
    import AdvertisingBox from '@/web/organisms/AdvertisingBox.vue'
    import HomeHeader from '@/web/organisms/HomeHeader.vue'
    export default {
      name: 'CategoryButtons',
      components : {
        HomeHeader,
       CategoryMain,
        AdvertisingBox,
      },
      data() {
          return {}
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