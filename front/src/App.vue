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

  components: {
  },
}
</script>

<style>
@font-face {
  font-family:'dream';
  src: url('./assets/fonts/SCDream6.ttf') format('truetype');
  font-weight: 700;
}

</style>
