<template>
  <v-app>
    <v-main>
      <h1>Deploy Test [{{ state.msg }}]</h1>
      <TitleHeader />
      <HelloWorld />
    </v-main>
  </v-app>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import TitleHeader from './components/TitleHeader.vue';
import axios from 'axios'
import {reactive} from 'vue'

export default {
  name: 'App',

  components: {
    HelloWorld,
    TitleHeader
  },

  setup(){
    axios.get('/api/category/test')
    .then(res=>{
      state.msg = res.data;
    })
    const state =reactive({
      msg: '',
    })
    return {state,}
  },

  data: () => ({
    //
  }),
}
</script>

<style>

</style>
