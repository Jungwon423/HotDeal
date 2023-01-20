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
        style="font-size: 25px; font-family: dream"
      >
        Kagu
      </span>
      <div />
      <div>
        <div class="group1">
          <span class="group1-text px-10">HOME</span>
          <span class="group1-text px-10">핫딜</span>
          <span class="group1-text px-10">메뉴</span>
          <span class="group1-text px-10">CONTACT</span>
          <span class="group2-text">로그인</span>
          <span class="group2-text px-10">무료 회원가입</span>
        </div>
      </div>
    </div>
  </container>
</template>
<script>
export default {
  metaInfo: {
    // title 입력하기
    title: '페이지 타이틀',
    // link tag 입력하기
    link: [{rel: "canonical", href: 'www.site.com'}],
    // meta tag 입력하기
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=1000px, initial-scale=1' },
      { vmid: "description", name: "description", content: 'description' }
      //vmid ↑ 메타 태그를 고유하게 만들어준다.
    ]
  }
}
</script>
<style>

  .group1 {
  display: flex;
  padding:50px;
  position: relative;
  background-color: transparent;
}
.group2-text {
  left:40%;
  position: relative;
  background-color: transparent;
  font-family: Poppins;
  font-size: 22px;
  font-weight: 600;
  line-height: 36px;
  letter-spacing: 0em;
  text-align: left;
}
.group1-text{
  font-family: Poppins;
  font-size: 22px;
  font-weight: 600;
  line-height: 36px;
  letter-spacing: 0em;
  text-align: left;
  position: relative;
}
</style>