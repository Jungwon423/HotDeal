import axios from "axios";

export default {
  namespaced: true,

  state: {
    currentCategory: "all",
    currentMarket: "all",
    productList: [],
  },

  getters: {},

  mutations: {
    SET_CURRENTCATEGORY(state, payload) {
      state.currentCategory = payload;
    },

    SET_CURRENTMARKET(state, payload) {
      state.currentMarket = payload;
    },
    SET_PRODUCTLISTBYCATEGORY(state, payload) {
      state.productList = payload;
    },
  },

  actions: {
    async FETCH_PRODUCTLIST_API(context) {
      try {
        let res = await axios.get(
          "http://localhost:8080/api/category/" +
            context.state.currentCategory +
            "/" +
            context.state.currentMarket +
            "/list"
        );
        console.log("FETCH_PRODUCTLIST_API SUCCESS");
        context.commit("SET_PRODUCTLISTBYCATEGORY", res.data["result"]);

      } catch (error) {
        console.log("FETCH_PRODUCTLIST_API FAIL");
        console.log(error);
      }
    },
  },
};
