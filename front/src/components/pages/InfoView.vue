<template>
  <v-system-bar
    elevation="0"
    height="8"
    color="#F5F5F5"
  />
  <v-app-bar
    color="white"
    elevation="1"
    height="55"
  >
    <span class="pt-2"><v-btn rounded>
      <v-icon
        color="black"
        size="xx-large"
      > mdi-arrow-left-thin</v-icon>
    </v-btn></span>
    <template #append>
      <span class="pt-2">
        <v-btn icon="mdi-magnify">
          <v-icon color="black" />
        </v-btn>
        <v-btn icon="mdi-cart">
          <v-icon color="black" />
        </v-btn>
      </span>
      <v-spacer />
    </template>
  </v-app-bar>

  <v-container class="pa-0">
    <v-sheet
      class="d-flex mx-0"
      color="white"
    >
      <v-avatar
        class="mx-4"
        color="white"
        size="40"
      >
        <v-img
          max-height="40"
          max-width="40"
          src="@/assets/amazon.jpg"
        />
      </v-avatar>
      <div
        style="font-size: small"
        class="my-3"
      >
        Amazon
      </div>
      <div class="num">
        6005
        <v-btn
          flat
          color="transparent"
          icon="mdi-heart"
        >
          <v-icon
            size="small"
            color="#757575"
          />
        </v-btn>
      </div>
    </v-sheet>
    <div class="box">
      <v-img
        width="100%"
        height="100%"
        src="@/assets/example.png"
      />
    </div>
    <v-sheet class="d-flex pa-2 grow-1">
      <div class="productName">
        Acer Nitro 5 AN515-55-53E5 Acer Nitro 5 AN515-55-53E5
      </div>
    </v-sheet>
    <v-sheet>
      <v-rating
        class="px-1 mx-1"
        value="3.5"
        color="#FFB300"
        empty-icon="mdi-star-outline"
        full-icon="mdi-star"
        half-icon="mdi-star-half"
        density="default"
        half-increments
        readonly
        size="14"
      />
      <span class="review"> 리뷰 30개 </span>
    </v-sheet>
    <span class="discount2">30% </span>
    <span> 60000원 </span>
    <v-divider />
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
  </v-container>
  <footer fixed>
    <v-sheet class="footer">
      <v-btn
        color="#FF4081"
        class="buy"
      >
        구매하기
      </v-btn>
    </v-sheet>
  </footer>
</template>

<script>
export default {
  data() {
    return {
      tab: 'Category',
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
      categoryNames: ['패션', '컴퓨터', '가구', '여행'],
    }
  },
}
</script>

<style scoped>
.box {
  object-fit: contain;
  padding: 0%;
}
.footer {
  bottom: 10%;
  position: sticky;
  height: auto;
}
.buy {
  position: fixed;
  width: 300px;
  position: absolute;
  left: 6%;
  border-radius: 50%;
  text-align: center;
  background-color: #ff4081;
  color: white;
}
.productName {
  font-size: medium;
  font-family: 'dream';
}
.discount2 {
  padding: 6px;
  font-size: large;
  font-family: 'dream';
  color: #ff4081;
}
.review {
  font-weight: bolder;
  font-family: 'dream';
  font-size: smaller;
  text-decoration-line: underline;
  color: #cfd8dc;
}
div {
  font-family: 'dream';
}
.num {
  position: relative;
  left: 40%;
  font-size: small;
  color: #757575;
}
</style>
