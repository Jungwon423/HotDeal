<template>
  <v-app-bar
    color="white"
    elevation="0"
  >
    <span class="pt-2"><v-btn rounded>
      <v-icon
        color="black"
        size="xx-large"
      > mdi-home</v-icon>
    </v-btn></span>
    
    <div
      class="
        d-flex
        align-center
        flex-grow-1
        rounded"
    >
      <v-text-field
        class="flex-grow-1 text-align-center pa-5"
        bg-color="white"
        density="comfortable"
        hide-details="auto"
        append-inner-icon="mdi-magnify"
        prefix="검색어를 입력하세요"
        outlined="false"
      />
    </div>
  </v-app-bar>
  <v-system-bar
    color="black"
    height="2"
  />

  <container>
    <v-tabs
      v-model="tab"
      justify="center"
      bg-color="transparent"
      color="black"
      grow
    >
      <v-tab
        v-for="tab in info"
        :key="tab"
      >
        {{ tab }}
      </v-tab>
    </v-tabs>
    <div>1 다운베스트</div>
    <div>2 age20s 퍼펙트글래스에센스 커버</div>
    <div>3 꽈베기허리띠</div>
    <div>4 후드티</div>
    <div>5 목도리</div>
    <v-spacer />>
    <div>최근검색어</div>
    <div>#아마존</div>
    <div>#이베이</div>
    <div>#패셔너블</div>
  </container>
</template>

<script>
export default {
    data() {
      return {
        info: ["인기검색어", "최근검색어"],
        links: [
          'Home',
          'About Us',
          'Services',
          'Contact Us',
        ],
      }
    },
  }
  </script>