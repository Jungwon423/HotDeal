<template>
  <v-container class="pa-0">
    <v-tabs
      v-model="tab"
      bg-color="transparent"
      color="#BCAAA4"
      grow
    >
      <v-tab
        v-for="categoryName in categoryNames"
        :key="categoryName"
        :value="categoryName"
        style="font-size: 12px; font-family: dream"
      >
        <CategoryButton :category-name="categoryName" />
      </v-tab>
    </v-tabs>
    <!-- 위에 있는 탭 -->
  </v-container>
</template>

<script>
import CategoryButton from '@/components/molecules/CategoryButton.vue'
export default {
  components: {
    CategoryButton,
  },

  data() {
    return {
      tab: 'Category',
      categoryNames: [
        'all',
        'fashion_beauty',
        'furniture_living',
        'homeAppliances_computers',
        'travel_car',
      ],
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
