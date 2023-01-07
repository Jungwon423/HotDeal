<template>
  <v-app-bar
    color="white"
    elevation="0"
  >
    <!--  
    <span class="pt-2"><v-btn rounded>
      <v-icon
        color="black"
        size="xx-large"
      > mdi-home</v-icon>
    </v-btn></span>
    -->
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
        prepend-icon="mdi-home"
        append-inner-icon="mdi-magnify"
        outlined="false"
        placeholder="검색어를 입력하세요"
        @click:prepend="$router.go(-1)"
        @click:append-inner="$router.go(-1)"
      />
    </div>
  </v-app-bar>

  <container>
    <v-tabs
      align-tabs="center"
      color="black"
      grow
    >
      <v-tab>
        인기검색어
      </v-tab>
      <v-tab>
        최근검색어
      </v-tab>
    </v-tabs>
    <!-- 
    <v-tabs
      v-model="tab"
      align-tabs="center"
      bg-color="white"
      grow
    >
      <v-tab
        v-for="tab in info"
        :key="tab"
      >
        {{ tab }}
      </v-tab>
    </v-tabs>
     -->
    <v-tabs
      v-model="tab"
      bg-color="transparent"
      color="basil"
      grow
    >
      <v-tab
        v-for="item in items"
        :key="item"
        :value="item"
      >
        {{ item }}
      </v-tab>
    </v-tabs>

    <v-window v-model="tab">
      <v-window-item
        v-for="item in items"
        :key="item"
        :value="item"
      >
        <v-card
          color="basil"
          flat
        >
          <v-card-text>{{ text }}</v-card-text>
        </v-card>
      </v-window-item>
    </v-window>

    <v-spacer />
    <div
      style="background-color:grey"
      class="d-flex grow"
    >
      ""
    </div>
    <div class="ma-5">
      1 &nbsp; 다운베스트
      <div>2 &nbsp; age20s 퍼펙트글래스에센스 커버</div>
      <div>3 &nbsp; 꽈베기허리띠</div>
      <div>4 &nbsp; 후드티</div>
      <div>5 &nbsp; 목도리</div>
      <v-spacer />>
      <div>최근검색어</div>
      <div>#아마존</div>
      <div>#이베이</div>
      <div>#패셔너블</div>
    </div>
  </container>
</template>

<script>
export default {
    data() {
      return {
        tab: null,
        model:'tab-2',
        items: [
          'web', 'shopping', 'videos', 'images', 'news',
        ],
        text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
        info: ["인기검색어", "최근검색어"],
      }
    },
  }
  </script>