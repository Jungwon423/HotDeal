<template>
  <v-container class="px-3">
    <div
      class="pa-1"
      style="font-size:13px;"
    >
      상품 주요 정보
    </div>
    <ul
      class="mx-3"
      style="font-size:11px"
    >
      <li>손으로 분류 된 메모리 칩은 넉넉한 오버클로킹</li>
      <li>벤전슨 LPX는 최신 인텔 및 AMD DDR4 마더보드와 광범위하게 호환되도록 최적화되었습니다.</li>
    </ul>
    <br>
    <div
      class="pa-1"
      style="font-size:13px;"
    >
      상세설명
    </div>
    <div
      class="mx-1"
      style="font-size:11px"
    >
      VERSION LPX 메모리는 고성능 오버클럭을 위해 설계되었습니다. 열 분산기는 순수 알루미늄으로 
      제작되어 더 빠르게 열을 방출합니다.
    </div>
    <br>
    <div
      class="pa-1"
      style="font-size:13px;"
    >
      주요 사양
    </div>
    <v-spacer />
    <table>
      <thead class="pa-1">
        <th
          v-for="item in header"
          :key="item"
          color="grey"
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
  </v-container>
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
}

th{
  background-color:#ECEFF1;
}
th,td{
  font-size:12px;
  display:block;
  padding: 8px;
  border: 0.01px solid #ccc;
  border-collapse: collapse;
}
</style>