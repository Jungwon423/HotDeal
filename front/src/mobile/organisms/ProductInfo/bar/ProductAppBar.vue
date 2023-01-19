<template>
  <v-system-bar
    elevation="0"
    height="8"
    color="#F5F5F5"
  />
  <v-app-bar
    color="white"
    elevation="1"
    height="55"
  >
    <span class="pt-2"><v-btn
      rounded
      @click="$router.go(-1)"
    >
      <v-icon
        color="black"
        size="xx-large"
      > mdi-arrow-left-thin</v-icon>
    </v-btn></span>
    <template #append>
      <span class="pt-2">
        <v-btn icon="mdi-magnify">
          <v-icon color="black" />
        </v-btn>
        <v-btn icon="mdi-cart">
          <v-icon color="black" />
        </v-btn>
      </span>
      <v-spacer />
    </template>
  </v-app-bar>
</template>