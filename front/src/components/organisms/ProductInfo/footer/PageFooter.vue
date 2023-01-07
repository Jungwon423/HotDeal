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
          height="15"
          width="90"
        >
          <div class="btn">
            {{ link }}
          </div>
        </v-btn>

        <v-col class="info pa-5 mx-3">
          <div>당사는 고객님의 안전거래를 위해 관련 법률에 의거하여</div>
          <div>(주)카구 사업자 정보</div>
          <div>일부 상품의 경우 주식회사 무신사는</div>
          <div>통신판매의 당사가 아닌 통신 판매 중개자로서</div>
          <div>상품, 상품정보, 거래에 대한 책임이 제한 될 수 있으므로,</div>
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