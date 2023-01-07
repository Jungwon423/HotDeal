<template>
  <v-tabs
    color="black"
    grow
  >
    <v-tab @click="goto('scroll1')">
      제품사진
    </v-tab>
    <v-tab @click="goto('scroll2')">
      상세정보
    </v-tab>
    <v-tab @click="goto('scroll3')">
      쇼핑가이드
    </v-tab>
  </v-tabs>
  <!--  
  <v-tabs
    v-model="tab"
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
  </v-tabs>-->
  <div
    ref="scroll1"
    class="py-3"
  >
    <v-img src="https://m.media-amazon.com/images/W/WEBP_402378-T2/images/S/aplus-media-library-service-media/baf925ef-8d72-4b5e-9f8e-45268757be6f.__CR0,0,1464,600_PT0_SX1464_V1___.jpg" />
    <v-img src="https://m.media-amazon.com/images/W/WEBP_402378-T2/images/S/aplus-media-library-service-media/78ec524a-3178-4a16-bd7c-94492654f62e.__CR0,0,1464,600_PT0_SX1464_V1___.jpg" />
    <v-img src="https://m.media-amazon.com/images/W/WEBP_402378-T2/images/S/aplus-media-library-service-media/0b1a01a0-d5c2-465a-941d-cd8e0e17b04c.__CR0,0,1464,600_PT0_SX1464_V1___.jpg" />
  </div>
  <div ref="scroll2">
    <ProductDetail /> 
  </div>
  <div ref="scroll3">
    <ShoppingGuide />
  </div>
</template>

<script>
import ProductDetail from '@/components/organisms/ProductInfo/additional/ProductDetail.vue';
import ShoppingGuide from '@/components/organisms/ProductInfo/additional/ShoppingGuide.vue'

export default {
  components: {
    ProductDetail,
    ShoppingGuide,
  },
  data() {
    return {
      info: ["제품사진","상세정보","문의"],
      links: [
        'Home',
        'About Us',
        'Services',
        'Contact Us',
      ],
    }
  },
  methods: {
    goto(refName) {
      var element = this.$refs[refName];
      var moveTo = element.offsetTop-50; //-50해야 제자리 찾는듯
      //moveEle.scrollIntoView({block: "start", behavior: "smooth" });
      window.scrollTo({top : moveTo, behavior: "smooth"});
    }
  }
}
</script>
<style>
.div {
  height: 1000px;
  padding: 20px;
  border: 2px solid yellow;
}
</style>