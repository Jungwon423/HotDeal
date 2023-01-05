<template>
  <v-row
    class="text-center"
    justify="center"
  >
    <v-col
      cols="12"
      md="3"
    >
      <div class="today">
        <div
          class="pa-10 secondary text-no-wrap rounded-pill grow-1"
        >
          <span class="category">전체 카테고리</span>
        </div>
      </div>
    </v-col>
  </v-row>
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
      <span
        style="font-size: 12px; font-family: 'dream'"
        v-text="cate.title"
      />
    </v-col>
  </v-row>
  
  <v-row
    class="mx-3 pa-6 text-center"
    justify="center"
  >
    <v-avatar
      color="#424242"
      size="70"
      class="rounded-xl"
    >
      <span style="color:aliceblue">ALL</span>
    </v-avatar>
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
      <span
        style="font-size: 12px; font-family: 'dream'"
        text-align="center"
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
      <span
        style="font-size: 12px; font-family: 'dream'"
        v-text="cate.title"
      />
    </v-col>
  </v-row>
</template>
  
  <script>
  export default {
    name: 'CategoryButton',
    data() {
        return {
        tab: 'Market',
        img1:[
            {img2:require('@/assets/categoryImage/computer.png'),}
        ],
        img: require('@/assets/categoryImage/computer.png'),
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
        {
            title: '건강',
            src: require('@/assets/categoryImage/health.png'),
            imageSize: '40px',
            color: '#C8E6C9',
        },
        ],
        category2: [
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
        {
            title: '패션',
            src: require('@/assets/categoryImage/cosmetics.png'),
            imageSize: '40px',
            color: '#FFCCBC',
        },
        ],
        category3: [
        {
            title: '공구용품',
            src: require('@/assets/categoryImage/tool.png'),
            imageSize: '40px',
            color: '#D1C4E9',
        },
        {
            title: '도서/음반',
            src: require('@/assets/categoryImage/book.png'),
            imageSize: '40px',
            color: '#FFCDD2',
        },
        {
            title: '사무용품',
            src: require('@/assets/categoryImage/office.png'),
            imageSize: '40px',
            color: '#F8BBD0',
        },
        {
            title: '스포츠/레저',
            src: require('@/assets/categoryImage/leisure.png'),
            imageSize: '40px',
            color: '#C8E6C9',
        },
        {
            title: '주방용품',
            src: require('@/assets/categoryImage/kitchen.png'),
            imageSize: '40px',
            color: '#C8E6C9',
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
  <style>
  .subName{
    position:relative;
    bottom:1%;
  }
  .category{
  font-family: "dream";
  font-size: large;
  font-weight: bold;
  color : black;
  text-align: center;
}
  </style>