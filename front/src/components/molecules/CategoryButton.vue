<template>
  <div
    class="d-flex flex-column align-center"
    @click="onClick"
  >
    <!--
      <v-avatar color="red">
      <span class="white--text text-">{{ categoryNameSub }}</span>
    </v-avatar>
  -->
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

  data() {
        return {
        category: [
        {
            title: '컴퓨터',
            src: require('@/assets/categoryImage/computer.png'),
            imageSize: '40px',
            color: '#D1C4E9',
        },
        {
            title: '가구',
            src: require('@/assets/categoryImage/furniture.png'),
            imageSize: '40px',
            color: '#FFCDD2',
        },
        {
            title: '패션',
            src: require('@/assets/categoryImage/fashion.png'),
            imageSize: '40px',
            color: '#F8BBD0',
        },
        ],
      }
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
