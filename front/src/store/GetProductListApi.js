import axios from "axios";

export default {
  // namespaced : 하나의 store에서 모듈화하여 사용될 수 있음을 명시적으로 나타내는 개념
  namespaced: true,

  // state : 실제로 취급해야하는 데이터
  state: {
    currentCategory: "all",
    currentMarket: "all",
    isCategory: true,
    productList: [],
  },

  // getters: 계산된 상태를 만들어내는 속성이다.
  // computed와 기능이 유사하다.
  getters: {},

  // mutations, actions : methods와 유사하다.
  // 1. mutations : 변이 메서드, 우리가 관리하는 데이터(state)를 변경시켜줄 수 있다.
  // 즉, state 안의 데이터는 오로지 mutations에서만 데이터를 변경시킬 수 있다.
  mutations: {
    SET_CURRENTCATEGORY(state, payload) {
      state.currentCategory = payload;
    },

    SET_PRODUCTLISTBYCATEGORY(state, payload) {
      state.isCategory = true;
      state.productList = payload;
    },
    SET_PRODUCTLISTBYMARKET(state, payload) {
      state.isCategory = false;
      state.productList = payload;
    },
  },

  // 2. actions : 특정한 데이터를 직접적으로 수정하는 것이 허용되지 않는다.
  // 위 사항이 가장 주의해야 할 사항이고, 또한 비동기로 동작한다는 점도 유의해야 한다.
  // async, await
  actions: {
    async FETCH_PRODUCTLIST_API(context) {
      try {
        if (context.state.isCategory) {
          let res = await axios.get(
            "https://www.zigdeal.shop:8080/api/category/" +
              context.state.currentCategory +
              "/list"
          );
          console.log("FETCH_PRODUCTLIST_API 성공");
          context.commit("SET_PRODUCTLISTBYCATEGORY", res.data["result"]);
        } else {
          let res = await axios.get(
            "https://www.zigdeal.shop:8080/api/category/" +
              context.state.currentMarket +
              "/listMarket"
          );
          console.log("FETCH_PRODUCTLIST_API 성공");
          context.commit("SET_PRODUCTLISTBYCMARKET", res.data["result"]);
        }
      } catch (error) {
        console.log("FETCH_PRODUCTLIST_API 실패");
        console.log(error);
      }
    },

    // async FETCH_EXCHANGERATE_API(context){

    //   try {
    //     let res = await axios.get('https://localhost:8080/api/exchange')

    //   } catch (error) {
    //     console.log(error)
    //   }

    // }
  },
};
