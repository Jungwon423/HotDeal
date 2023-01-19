<template>
  <v-row
    class="px-6 text-center"
    justify="center"
  >
    <v-col
      v-for="cate in category"
      :key="cate.title"
    >
      <v-avatar
        :color="cate.color"
        size="70"
        class="rounded-xl"
      >
        <v-img
          :src="cate.src"
          :width="cate.imageSize"
          :height="cate.imageSize"
        />
      </v-avatar>
      <div
        class="imgText"
        v-text="cate.title"
      />
    </v-col>
  </v-row>
  
  <v-row
    class="px-6 text-center"
    justify="center"
  > 
    <v-col
      v-for="cate in category2"
      :key="cate.title"
    >
      <v-avatar
        :color="cate.color"
        size="70"
        class="rounded-xl"
      >
        <v-img
          :src="cate.src"
          :width="cate.imageSize"
          :height="cate.imageSize"
        />
      </v-avatar>
      <div
        class="imgText"
        v-text="cate.title"
      />
    </v-col>
  </v-row>
  <v-row
    class="px-6 text-center"
    justify="center"
  >
    <v-col
      v-for="cate in category3"
      :key="cate.title"
    >
      <v-avatar
        :color="cate.color"
        size="70"
        class="rounded-xl"
      >
        <v-img
          :src="cate.src"
          :width="cate.imageSize"
          :height="cate.imageSize"
        />
      </v-avatar>
      <div
        class="imgText"
        v-text="cate.title"
      />
    </v-col>
  </v-row>
  <v-row
    class="px-6 text-center"
    justify="center"
  >
    <v-col
      v-for="cate in category4"
      :key="cate.title"
    >
      <v-avatar
        :color="cate.color"
        size="70"
        class="rounded-xl"
      >
        <v-img
          :src="cate.src"
          :width="cate.imageSize"
          :height="cate.imageSize"
        />
      </v-avatar>
      <div
        class="imgText"
        v-text="cate.title"
      />
    </v-col>
  </v-row>
</template>
<script>
export default {
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
        category2: [
        {
            title: '건강',
            src: require('@/assets/categoryImage/health.png'),
            imageSize: '40px',
            color: '#C8E6C9',
        },
        {
            title: '자동차',
            src: require('@/assets/categoryImage/car.png'),
            imageSize: '30px',
            color: '#BDBDBD',
        },
        {
            title: '가전',
            src: require('@/assets/categoryImage/electronics.png'),
            imageSize: '40px',
            color: '#ECEFF1',
        },
        ],
        category3: [
        {
            title: '화장품',
            src: require('@/assets/categoryImage/cosmetics.png'),
            imageSize: '40px',
            color: '#FFCCBC',
        },
        {
            title: '공구용품',
            src: require('@/assets/categoryImage/tool.png'),
            imageSize: '40px',
            color: '#BCAAA4',
        },
        {
            title: '도서/음반',
            src: require('@/assets/categoryImage/book.png'),
            imageSize: '40px',
            color: '#B2DFDB',
        },
        ],
        category4:[
        {
            title: '사무용품',
            src: require('@/assets/categoryImage/office.png'),
            imageSize: '40px',
            color: '#B2EBF2',
        },
        {
            title: '스포츠/레저',
            src: require('@/assets/categoryImage/leisure.png'),
            imageSize: '40px',
            color: '#FFECB3',
        },
        {
            title: '주방용품',
            src: require('@/assets/categoryImage/kitchen.png'),
            imageSize: '40px',
            color: '#F0F4C3',
        }
      ]
      }
    },
}
</script>
<style>
@import '@/assets/styles/CategoryMain.css';
</style>

