<template>
  <v-row
    class="ma-5 text-center"
    justify="end"
  >
    <v-icon
      color="black"
      size="xx-large"
      @click="$router.go(-1)"
    >
      mdi-close
    </v-icon>
  </v-row>
  <v-row
    class="text-center"
    justify="center"
  >
    <v-col
      cols="12"
      md="3"
    >
      <div>
        <span class="category">전체 카테고리</span>
      </div>
    </v-col>
  </v-row>
</template>