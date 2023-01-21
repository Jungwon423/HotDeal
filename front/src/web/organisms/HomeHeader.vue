<template>
  <container class="ple">
    <div class="pa-10">
      <v-avatar
        color="white"
        size="56"
      >
        <v-img
          max-height="100"
          max-width="100"
          src="@/assets/kamua.png"
        />
      </v-avatar>

      <span
        class="mx-1"
        style="font-size: 22px; font-family: dream"
      >
        Kagu
      </span>
    </div>
    <div>
      <div class="group1">
        <span class="group1-text px-10">카테고리</span>
        <span class="v-line" />
        <span class="group1-text px-10">홈</span>
        <span class="group1-text px-10">핫딜</span>
        <span class="group1-text px-10">기획전</span>
        <span class="group2-text px-10">로그인</span>
      </div>
    </div>
  </container>
</template>
<script>
export default {
}
</script>
<style>

  .group1 {
  display: flex;
  padding:0px;
  position: relative;
  background-color: transparent;
  border-style:solid;
  border-width:1px 0px 1px 0px;
  border-color:#E0E0E0;
}
.group2-text {
  left: 30%;
  position: relative;
  background-color: transparent;
  font-family: Poppins;
  font-size: 16px;
  font-weight: 600;
  line-height: 36px;
  letter-spacing: 0em;
  text-align: left;
}
.group1-text{
  font-family: Poppins;
  font-size: 16px;
  font-weight: 600;
  line-height: 36px;
  letter-spacing: 0em;
  text-align: left;
  position: relative;
}
.v-line{
  margin-top:5px;
  border-left: solid;
  border-width:1px;
  border-color:#E0E0E0;
  height:25px;
}
</style>