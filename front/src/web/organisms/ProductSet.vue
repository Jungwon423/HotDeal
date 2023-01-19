<template>
  <v-row>
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
    }
}
</script>