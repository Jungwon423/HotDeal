<template>
  <div>
    Product Information
  </div>
  <v-spacer />
  <div id="app">
    <table>
      <thead class="pa-1">
        <th
          v-for="item in header"
          :key="item"
        >
          {{ item }}
        </th>
      </thead>
      <tbody class="pa-1">
        <td
          v-for="item in info"
          :key="item"
        >
          {{ item }}
        </td>
      </tbody>
    </table>
  </div>
</template>
<script>
export default {
    data() {
      return {
        header:["브랜드명","무게","규격","원산지","모델 넘버","색상"],
        info: ["TCL","7.5 pounds","3.1 x 28.1 x 16.6 inches","China","32S359","Black"]
        }
    }
}

</script>
<style>
table{
    display:flex;
    display: -webkit-box;
    display: -ms-flexbox;
    overflow-x: auto;
    overflow-y: hidden;
}

tbody{
    display:block
}

th,td{
    display:block
    }
</style>