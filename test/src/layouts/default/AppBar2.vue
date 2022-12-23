<template>
    <v-app>
      <v-system-bar>
        <v-img 
            width="27.7px"
            height="13px"
            src="@/assets/Mobile Signal.png">
        </v-img>
        <v-img 
            width="27.7px"
            height="13px"
            src="@/assets/Wifi.png">
        </v-img>
        <v-img 
            width="27.7px"
            height="13px"
            src="@/assets/Fill.png">
        </v-img>
      </v-system-bar>
        
      <v-app-bar app color="white" dark>
        <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
        <v-toolbar-title class="ml-5 font-weight-black" >Global Hot Deal</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn text @click="onUploadFile">
          <v-icon class="px-2" large >mdi-upload</v-icon>
        </v-btn>
        <v-btn text @click="dialog = !dialog">
          <v-icon class="px-2" large>mdi-history</v-icon>
        </v-btn>
      </v-app-bar>
      <v-navigation-drawer v-model="drawer" app>
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title class="text-h6">
              Global Hot Deal
            </v-list-item-title>
            <!-- <v-list-item-subtitle>
              subtext
            </v-list-item-subtitle> -->
          </v-list-item-content>
        </v-list-item>
        <v-divider></v-divider>
        <v-list
          dense
          nav
        >
          <v-list-item
            v-for="item in items"
            :key="item.title"
            link
            :to = "item.to"
          >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>
      <v-main>
          <router-view/>
      </v-main>
    </v-app>
  </template>
  
  <script>
  export default {
    name: 'App',
    data: () => ({
      drawer : false,
      dialog: false,
      model: 1,
      files: null,
      items: [
        { title: 'INFO', icon: 'mdi-view-dashboard', to:'/info'},
        { title: 'RELAY', icon: 'mdi-image', to:"/relay" },
        { title: 'ALARM', icon: 'mdi-help-box', to:"/alarm" },
        { title: 'SETTING', icon: 'mdi-help-box', to:'/setting' },
        { title: 'EVENT', icon: 'mdi-image', to: '/event'},
        { title: 'MEASURE', icon: 'mdi-help-box', to: '/measure'},
        { title: 'Main', icon: 'mdi-help-box', to: '/'},
        { title: 'upload', icon: 'mdi-help-box', to: '/upload'},
      ],
      models: [
        {icon: 'mdi-file-document', file: 'ETU-25-A'},
        {icon: 'mdi-file-document', file: 'ETU-25-D'},
        {icon: 'mdi-file-document', file: 'ETU-25-GE'},
        {icon: 'mdi-file-document', file: 'ETU-25-N'},
        {icon: 'mdi-file-document', file: 'ETU-63-GD'},
      ],
    }),
    methods:{
      fetchData: function() {
        const datum = this;
        http.get("/")
          .then(function(res) {
            console.log(res.data);
            datum.models = res.data;
          })
          .catch(function(error) {
            console.log(error);
          });
        }
      }
    }
  </script>