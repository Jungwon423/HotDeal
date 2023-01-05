<template>
  <v-row
    class="text-center"
    justify="center"
  >
    <v-col
      cols="12"
      md="3"
    >
      <div class="today">
        <div
          class="pa-4 secondary text-no-wrap rounded-pill grow-1"
        >
          <span class="hot">Today's Hot</span>
        </div>
      </div>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: 'BannerBox',
}
</script>

<style scoped>


.today{
  width: auto;
  height: 80px;
  background-image: url("@/assets/todayshot.png");
}
.hot{
  font-family: "dream";
  font-size: large;
  font-weight: bold;
  color : white;
  text-align: center;
}
</style>