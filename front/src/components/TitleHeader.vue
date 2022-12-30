<template>
    <v-app-bar
      color="white"
      elevation="0"
    >
      <v-avatar
        color="white"
        size="56"
      >
        <v-img 
              max-height="50"
              max-width="50"
              src="@/assets/kamua.png">
        </v-img></v-avatar>
      <div style="font-size: large; font-family: dream">&nbsp; Kagu</div>
      <v-spacer></v-spacer>
      <div class="absolute1">
      <v-text-field style="align=center"
        variant = "solo"
        success
        bg-color="white"
      ></v-text-field>
      </div>
      <v-img class="absolute2"
        max-height="20"
        max-width="20"
        src="@/assets/search.png">
      </v-img>
    </v-app-bar>

    <v-system-bar
      elevation="0"
      height="8"
      color="#F5F5F5"
    >
    </v-system-bar>
  
  <v-container
    align="left"
    class="border=5px"
  >
    <v-tabs
      v-model="tab"
      bg-color="transparent"
      color="#BCAAA4"
      grow
    >
      <v-tab 
        v-for="item in items"
        :key="item"
        :value="item"
        style = "font-size:12px; font-family:dream"
      >
        {{ item }}
      </v-tab>
    </v-tabs>
    <v-carousel hide-delimiters height="auto"> 
      <v-carousel-item
        src="@/assets/cate1.png"
        cover
      ></v-carousel-item>

      <v-carousel-item
        src="@/assets/cate2.png"
        cover
      ></v-carousel-item>

      <v-carousel-item
        src="@/assets/cate3.png"
        cover
      ></v-carousel-item>
    </v-carousel>
  </v-container>
</template>
  
  <script>
  export default {
    data () {
      return {
        tab: 'Appetizers',
        items: [
          '패션/뷰티', '가전', '자동차', '가구',
        ],
      }
    },
  }
  </script>

<style> 
#v-text-field.relative {
    position: relative;
    left: 30px;
    border: 3px solid #73AD21;
}
div.absolute1 {
    position: absolute;
    top: 5px;
    right: 50px;
    width: 150px;
    height: 0px;
}

v-img.absolute2 {
    position: absolute;
    top: 5px;
    right: 100px;
    width: 150px;
    height: 5px;
}

  </style>