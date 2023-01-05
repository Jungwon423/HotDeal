<template>
  <v-sheet class="d-flex pa-1 my-2">
    <div>함께 보면 좋은 상품</div>
  </v-sheet>

  <v-row dense>
    <v-col
      v-for="card in cards"
      :key="card.title"
      :cols="card.flex"
    >
      <v-card>
        <v-img
          :src="card.src"
          class="align-end"
          gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
          height="200px"
          cover
          append-icon="mdi-heart"
        >
          <v-btn
            size="small"
            color="white"
            variant="text"
            icon="mdi-heart"
          >
            <v-icon>
              {{ active ? 'mdi-heart' : 'mdi-heart-outline' }}
            </v-icon>
          </v-btn>
          <v-card-title
            class="text-white"
            v-text="card.title"
          />
        </v-img>
      </v-card>
    </v-col>
  </v-row>
  <carousel
    :autoplay="true"
    :nav="false"
    :dots="false"
    class="marginTop50"
  >
    <img src="https://placeimg.com/200/200/any?1">
    <img src="https://placeimg.com/200/200/any?2">
    <img src="https://placeimg.com/200/200/any?3">
    <img src="https://placeimg.com/200/200/any?4">
  </carousel>
</template>
  
  <script>
  import carousel from 'vue-owl-carousel2'
  export default {
    components :{carousel},
    data() {
    return {
        plugin:null,
        options:{
            autoplay:false,
            items:1,
            startPosition:2,
            autoplayTimeout:1000,
        },
        cards: [
        {
            title: 'Favorite road trips',
            src: 'https://cdn.vuetifyjs.com/images/cards/road.jpg',
            flex: 6,
        },
        {
            title: 'Best airlines',
            src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg',
            flex: 6,
        },
        ],
    }
  }
}
  </script>
  
  <style>
  
  </style>