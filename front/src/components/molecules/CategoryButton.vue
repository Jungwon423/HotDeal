<template>
  <div class="d-flex flex-column align-center" @click="onClick">
    <!-- <v-avatar color="red">
      <span class="white--text text-">{{ categoryNameSub }}</span>
    </v-avatar> -->
    <span
      style="font-size: 12px; font-family: 'dream'"
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
    },
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
