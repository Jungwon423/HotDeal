<template>
  <div
    class="d-flex flex-column align-center"
    @click="onClick"
  >
    <v-img  
      class="bg-white"
      :aspect-ratio="1"
      :src="imagePath"
      :width="33"
    />
    <span
      style="font-size: smaller;
  font-family: 'dream'"
      v-text="categoryNameSub"
    />
  </div>
</template>

<script>

export default { 
  name: 'CategoryButton',

  props: {
    categoryName: {
      type: String,
      required: true,
    }
  },
  
  computed: {
    imagePath() {
      return require('../../assets/'+this.categoryName+'.png')
    },

    categoryNameSub() { //categoryNameSubstitution
      return this.$categoryMap.get(this.categoryName)
    },
  },

  methods: {
    onClick: function(evt) {
      this.$store.commit('GetProductListApi/SET_CURRENTCATEGORY', this.categoryName)
    }
  },
}
</script>