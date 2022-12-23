<template>
    <v-app id="inspire">
      <v-app-bar
        class="px-3"
        color="white"
        flat
        density="compact"
      >
        <v-spacer></v-spacer>
  
        <v-tabs
          centered
          color="grey-darken-2"
        >
          <v-tab
            v-for="link in links"
            :key="link"
          >
            {{ link }}
          </v-tab>
        </v-tabs>
        <v-spacer></v-spacer>
      </v-app-bar>
      <v-app-bar
        class="px-3"
        color="white"
        flat
        density="compact"
      >
        <v-spacer></v-spacer>
  
        <v-tabs
          centered
          color="grey-darken-2"
        >
          <v-tab
            v-for="category in categories"
            :key="category"
          >
            {{ category }}
          </v-tab>
        </v-tabs>
        <v-spacer></v-spacer>
        </v-app-bar>
        <v-app-bar>
            <v-btn
                class="mx-2"
                fab
                dark
                color="teal"
            >
            <v-icon dark>
                mdi-format-list-bulleted-square
            </v-icon>
            </v-btn>
            <v-btn stacked prepend-icon="mdi-vuetify" >
                패션/뷰티
            </v-btn>
            <v-btn v-for="category in categories" :key="category"
                elevation="2"
                fab
                raised
                Tile
            >{{category}}</v-btn>
        </v-app-bar>
  
      <v-main class="bg-grey-lighten-3">
        <v-container>
          <v-row>
            <v-col
              cols="12"
              sm="2"
            >
              <v-sheet
                rounded="lg"
                min-height="268"
              >
                <!--  -->
              </v-sheet>
            </v-col>
  
            <v-col
              cols="12"
              sm="8"
            >
              <v-sheet
                min-height="70vh"
                rounded="lg"
              >
                <!--  -->
              </v-sheet>
            </v-col>
  
            <v-col
              cols="12"
              sm="2"
            >
              <v-sheet
                rounded="lg"
                min-height="268"
              >
                <!--  -->
              </v-sheet>
            </v-col>
          </v-row>
        </v-container>
      </v-main>
    </v-app>
  </template>
  
  <script>
    export default {
      data: () => ({
        links: [
          'Today',
          'Best',
          'Brand'
        ],
        categories: [
            '패션/뷰티',
            '가구/생활',
            '가전/컴퓨터',
            '여행/자동차'
        ]
      }),
    }
  </script>