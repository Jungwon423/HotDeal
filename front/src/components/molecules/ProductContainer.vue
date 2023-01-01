<template>
  <v-container class="pa-5">
    <v-row>
      <v-col
        :cols="5"
        class="ma-1 pa-1"
      >
        <a :href="link">
          <v-img 
            style="display: block"
            class="bg-white rounded-sm"
            max-height="100"
            :src="imgUrl"
          />
        </a>
      </v-col>
      <v-col
        :cols="6"
        class="ma-0 pa-0"
      >
        <h5> {{ productName }}</h5>
        <p />
        <h5>&nbsp; ${{ productPrice }} {{ discountRate }}%</h5>
        <p />
        <h5>~133,145₩</h5>
      </v-col>
    </v-row>
  </v-container>
  <v-divider class="mx-5" />
</template>

<script>
export default {
  name: 'ProductContainer',
  props: {
    productName: {
      type: String,
      required: true,
    },
    productPrice: {
      type: Number,
      required: true
    },
    discountRate: {
      type: Number,
      required: true
    },
    imgUrl: {
      type: String,
      required: true,
    },
    link: {
      type: String,
      required: true,
    },
  },
}
</script>