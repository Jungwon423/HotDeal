<template>
  <footer style="background-color:#F5F5F5">
    <v-row 
      class="text-center"
      justify="center"
    >
      <v-col cols="12">
        <v-btn
          v-for="link in links"
          :key="link"
          color="#F5F5F5"
          elevation="0"
          class="py-2 my-2 rounded-0"
          height="50"
          width="90"
        >
          <div class="btn">
            {{ link }}
          </div>
        </v-btn>

        <v-col class="info pa-5 mx-3">
          <div>당사는 고객님의 안전거래를 위해 관련 법률에 의거하여</div>
          <br>
          <div>(주)카구 사업자 정보</div>
          <div>카구(주)는 통신판매중개자로서 오픈마켓의 거래당사자가</div>
          <div> 아니며, 입점판매자가 등록한 상품정보 및 거래에 대해</div>
          <div>카구(주)는 일체의 책임을 지지 않습니다.</div>
          <br>
          <div>각 상품 페이지에서 구체적인 내용을 확인하시기 바랍니다.</div>
        </v-col>
      </v-col>
    </v-row>
  </footer>
</template>

<script>
export default {
  data() {
    return {
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