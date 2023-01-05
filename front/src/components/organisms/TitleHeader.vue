<template>
  <v-app-bar
    color="white"
    elevation="0"
  >
    <v-avatar
      color="white"
      size="56"
    >
      <v-img
        max-height="50"
        max-width="50"
        src="@/assets/kamua.png"
      />
    </v-avatar>

    <div style="font-size: large; font-family: dream">
      &nbsp; Kagu
    </div>
    <div
      class="
        d-flex
        align-center
        flex-grow-1
        rounded"
    >
      <v-text-field
        class="flex-grow-1 text-align-center pa-9"
        bg-color="#ECEFF1"
        density="comfortable"
        hide-details="auto"
        append-inner-icon="mdi-magnify"
        @click="goSearchPage()"
      />
    </div>
  </v-app-bar>

  <v-system-bar
    elevation="0"
    height="1"
    color="#F5F5F5"
  />
</template>

<script>
export default {
  methods: {
    goSearchPage() {
      this.$router.push('/search')
    },
  },
}
</script>

$router.push()
<style>
#v-text-field.relative {
  position: relative;
  left: 30px;
  border: 3px solid #73ad21;
}
.search-box{
  position: relative;
  left: 10%;
  background-color :#ECEFF1;
  height : 37px;
  width : 250px;
}
</style>
