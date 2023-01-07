<template>
  <v-row
    class="px-6 text-center"
    justify="center"
  >
    <v-col @click="gopage">
      <v-avatar
        color="#424242"
        size="60"
        class="rounded-xl"
      >
        <span style="color:aliceblue">ALL</span>
      </v-avatar>
      <v-div class="imgText">
        전체
      </v-div>
    </v-col>
    <v-col
      v-for="cate in category"
      :key="cate.title"
    >
      <v-avatar
        :color="cate.color"
        size="60"
        class="rounded-xl"
      >
        <v-img
          :src="cate.src"
          :width="cate.imageSize"
          :height="cate.imageSize"
        />
      </v-avatar>
      <v-div
        class="imgText"
        v-text="cate.title"
      />
    </v-col>
  </v-row>
</template>
<script>
export default {
    name: 'CategoryButton2',
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
            title: '가구/생활',
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
        ]
    }
    },
    methods: {
        gopage() {
            this.$router.push('/category')
        },
    },
}
</script>