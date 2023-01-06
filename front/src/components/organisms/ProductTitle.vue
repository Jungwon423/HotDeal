<template>
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
</template>