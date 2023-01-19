<template>
  <v-container class="no-padding">
    <v-row
      class="my-5 text-center pa-0"
      justify="center"
      style="background-color:#B2DFDB"
      dense
    >
      <v-col
        cols="2"
      >
        <v-img
          src="@/assets/아마존색.png"
        />
      </v-col>
    </v-row>
    <v-row
      class="text-center"
      justify="center"
      style="background-color:#E1F5FE"
    >
      <v-col
        cols="12"
      >
        <div>
          글로벌 스토어 쇼핑가이드
        </div>
      </v-col>
    </v-row>
  </v-container>
  <v-container style="background-color:white">
    <v-row>
      <v-col cols="2">
        <v-avatar
          class="ma-2"
          color="#FCE4EC"
          size="40"
        >
          <v-img
            style="width:20px"
            src="@/assets/guide1.svg"
          />
        </v-avatar>
      </v-col>
      
      <v-col cols="10">
        <div
          class="flex-row"
          style="font-size:15px"
        >
          개인통관 고유번호
        </div>
        <div
          class="pa-2"
          style="font-size:13px"
        >
          개인 통관 부호를 잘못 입력하거나 미입력 하는 경우, 통관 및 배송이 지연될
          수 있습니다.
        </div>
      </v-col>
    </v-row>
    <v-spacer />
    <hr>
    <v-row>
      <v-col cols="2">
        <v-avatar
          class="ma-2"
          color="#F0F4C3"
          size="40"
        >
          <v-img src="@/assets/guide2.svg" />
        </v-avatar>
      </v-col>
      
      <v-col cols="10">
        <div 
          style="font-size:15px"
        >
          관부가세 및 통관 및 통관대행료 안내
        </div>
        <div
          class="pa-2"
          style="font-size:13px"
        >
          총 구매금액 $200(미국 기준 목록 통관 제품) / $150(미국 일반 통관
          제품 및 미국 이외 지역) 초과 시 관세가 발생할 수 있습니다.
        </div>
      </v-col>
    </v-row>
    <hr>
    <v-row>
      <v-col cols="2">
        <v-avatar
          class="ma-2"
          color="#D7CCC8"
          size="40"
        >
          <v-img
            style="width:20px"
            src="@/assets/guide3.svg"
          />
        </v-avatar>
      </v-col>
      
      <v-col cols="10">
        <div
          class="flex-row"
          style="font-size:15px"
        >
          구매 수량 및 주의사항
        </div>
        <div
          class="pa-2"
          style="font-size:13px"
        >
          <ul class="px-3">
            <li>
              전자 제품 : 모델 별로 1일 1개 통관 가능
            </li>
            <li>
              건강 식품 : 1일 6개 통관 가능
            </li>
          </ul>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>
<style>
.no-padding {
  padding: 0 !important;
}
.go{
  background-color: skyblue;
  color:white
}
  </style>