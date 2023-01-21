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
  width: 980px;
  height: 80vw;
  margin-left: auto;
  margin-right: auto;
}
@font-face {
  font-family: 'dream';
  src: url('./assets/fonts/SCDream6.ttf') format('truetype');
  font-weight: 700;
}
</style>
