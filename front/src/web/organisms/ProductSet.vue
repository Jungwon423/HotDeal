<template>
  <v-row
    class="px-6 text-center"
    justify="center"
  >
    <v-col>
      <div class="best-text">
        BEST SELLER
      </div>
    </v-col>
  </v-row> 
  <v-row justify="center">
    <v-col cols="8">
      <v-tabs
        v-model="tab"
        bg-color="transparent"
        color="#64B5F6"
        grow
      >
        <v-tab
          v-for="marketname in marketNames"
          :key="marketname"
          :value="marketname"
        >
          {{ marketname }}
        </v-tab>
      </v-tabs>
    </v-col>
  </v-row>
  <v-row
    justify="center"
    class="px-12"
  >
    <v-col>
      <product-container />
    </v-col>
    <v-col>
      <product-container />
    </v-col>
    <v-col>
      <product-container />
    </v-col>
    <v-col>
      <product-container />
    </v-col>
  </v-row>
</template>

<script>
import ProductContainer from '@/web/molecules/ProductContainer.vue'
export default {
    name: 'ProductSet',
    components: {
        ProductContainer
    },
    data() {
    return {
      tab: 'Market',
      marketNames: ['all', 'Amazon', 'AliExpress', 'eBay'],
    }
  },
}
</script>
<style scoped>
.best-text {
  position: relative;
  font-family: Poppins;
  font-size: 30px;
  font-weight: 800;
}
.set-group{
  position: relative;
  left : 5%;
}
</style>