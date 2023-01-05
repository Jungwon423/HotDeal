<template>
  <div class="d-flex flex-column align-center" @click="onClick">
    <span style="font-size: 12px; font-family: 'dream'" v-text="marketName" />
  </div>
</template>

<script>
export default {
  name: 'MarketButton',

  props: {
    marketName: {
      type: String,
      required: true,
    },
  },
  methods: {
    onClick: function (evt) {
      this.$store.commit(
        'GetProductListApi/SET_PRODUCTLISTBYCMARKET',
        this.marketName
      )
    },
  },
}
</script>
