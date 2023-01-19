<template>
  <CategoryHead />
  <CategoryMain />
</template>
  
<script>
import CategoryHead from '@/mobile/organisms/Category/CategoryHead.vue'
import CategoryMain from '@/mobile/organisms/Category/CategoryMain.vue'
  export default {
    name: 'CategoryButton',
    components : {
     CategoryHead,
     CategoryMain,
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