<template>
  <v-app>
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'App',
}
</script>

<style>
#app{
  width: 1000px;
  height: 80vw;
  margin-left: auto;
  margin-right: auto;
}
</style>
