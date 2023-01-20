<template>
  <div class="sidebar-sidebar">
    <ul>
      <div class="sidebar-group1">
        <div
          class="sidebar-title pa-2"
        >
          Category 
          <span><v-icon
            class="px-12 icon1"
            color="black"
            size="xx-large"
          >
            mdi-menu-up
          </v-icon>
          </span>
        </div>
        <v-spacer />
        <div class="sidebar-content">
          <span class="sidebar-text">mobile</span>
        </div>
        <div class="sidebar-content">
          <span class="sidebar-text">electronic</span>
        </div>
        <div class="sidebar-content">
          <span class="sidebar-text">smartphone</span>
        </div>
        <div class="sidebar-content">
          <span class="sidebar-text">computer</span>
        </div>
        <div class="sidebar-add">
          See All
        </div>
        <div class="sidebar-add" />
      </div>
      <div class="sidebar-group1">
        <div class="sidebar-title pa-2">
          Brands
          <span><v-icon
            class="px-12 icon1"
            color="black"
            size="xx-large"
          >
            mdi-menu-up
          </v-icon>
          </span>
        </div>

        <div class="sidebar-content">
          <label><input
            type="checkbox"
            value="amazon"
          > Amazon</label>
        </div>
        <div class="sidebar-content">
          <label><input
            type="checkbox"
            value="ebay"
          > <span class="sidebar-text">eBay</span></label>
        </div>
        <div class="sidebar-content">
          <label><input
            type="checkbox"
            value="aliexpress"
          > AliExpress</label>
        </div>
      </div>
    </ul>
  </div>
</template>

<script>
  export default {
    data: () => ({
    }),
  }
</script>
<style scoped>
.sidebar-sidebar {
  width: 240px;
  height: 500px;
  display: flex;
  position: relative;
  align-items: flex-start;

  justify-content: flex-start;
  background-color: whitesmoke;
}
.sidebar-title{
  width: 240px;
  height: 40px;
  display: flex;
  position: static;
  font-size:large;
  align-items: flex-start;
  background-color:white;
  border-style: outset;
  border-width: 2px;
  border-color: #ECEFF1 transparent transparent transparent;
}
.icon1{
    position:absolute;
    align-self: auto;
    left:70%;
}
.sidebar-content{
  width: 240px;
  height: 30px;
  display: flex;
  position:relative;
  padding-left:10px;
  padding-top:5px;
  background-color:white;
}
.sidebar-text{
    font-size:15px;
    font-weight: 400;
}
.checkbox1{
    height:30px;
}
.sidebar-add{
    width:240px;
    height:30px;
    display:flex;
    position:relative;
    padding-left:5px;
    padding-top:5px;
    color:#039BE5;
    background-color:white;
}
</style>