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

    <div class="d-flex align-center flex-grow-1 ">
      <v-text-field
        class="flex-grow-1 text-align-center px-8"
        variant="outlined"
        bg-color="white"
        density="comfortable"
        hide-details="auto"
        append-inner-icon="mdi-magnify"
      />
      <!-- </v-responsive> -->
    </div>
  </v-app-bar>

  <v-system-bar
    elevation="0"
    height="8"
    color="#F5F5F5"
  />
</template>
  
<script>
  export default {
    
  }
</script>

<style> 
#v-text-field.relative {
    position: relative;
    left: 30px;
    border: 3px solid #73AD21;
}
</style>