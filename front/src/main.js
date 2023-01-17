import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import vuetify from "./plugins/vuetify";
import { loadFonts } from "./plugins/webfontloader";
import store from "./store/index.js";

loadFonts();

const app = createApp(App);

var categoryMap = new Map();
categoryMap.set("all", "홈");
categoryMap.set("life_health", "생활/건강");
categoryMap.set("duty-free", "면세점");
categoryMap.set("travel_culture", "여행/문화");
categoryMap.set("sports_leisure", "스포츠/레저");
categoryMap.set("food", "식품");
categoryMap.set("childbirth_parenting", "출산/육아");
categoryMap.set("furniture_interior","가구/인테리어");
categoryMap.set("digital_consumer","디지털/가전" );
categoryMap.set("cosmetics_beauty","화장품/미용");
categoryMap.set("fashion-accessories", "패션잡화");
categoryMap.set("fashion-clothes", "패션의류");

app.config.globalProperties.$categoryMap = categoryMap;

app.use(vuetify).use(router).use(store).mount("#app");
