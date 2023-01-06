<template>
  <div
    class="d-flex flex-column align-center h-25"
    @click="onClick"
  >
    <span
      style="font-size: 12px; font-family: 'dream'"
      v-text="marketName"
    />
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
        'GetProductListApi/SET_PRODUCTLISTBYMARKET',
        this.marketName
      )
    },
  },
}
</script>
