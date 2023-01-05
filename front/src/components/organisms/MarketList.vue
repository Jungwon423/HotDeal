<template>
  <v-container class="pa-0">
    <v-card-text class="mx-1" style="font-family: dream">
      사이트 별 Top 5를 확인해보세요!
    </v-card-text>
    <v-tabs v-model="tab" bg-color="transparent" color="#BCAAA4" grow>
      <v-tab
        v-for="marketname in marketNames"
        :key="marketname"
        :value="marketname"
      >
        <MarketButton :market-name="marketname" />
      </v-tab>
    </v-tabs>
  </v-container>
</template>

<script>
import MarketButton from '../molecules/MarketButton.vue'

export default {
  components: {
    MarketButton,
  },

  data() {
    return {
      tab: 'Market',
      marketNames: ['All', 'Amazon', 'AliExpress', 'eBay'],
    }
  },

  computed: {
    categoryNameSub() {
      //categoryNameSubstitution
      return this.categoryNames.map((categoryName) =>
        this.$categoryMap.get(categoryName)
      )
    },
  },
}
</script>
