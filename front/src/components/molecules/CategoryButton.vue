<template>
  <div class="text-center">
    <v-card @click="onClick">
      <v-img  
        class="bg-white"
        :aspect-ratio="1"
        :src="imagePath"
      />
      <h4 v-text="categoryNameSub" />
    </v-card>
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