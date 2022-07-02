<template>
  <div v-if="state.resolutionRatio=='1920x1080(横)'" class="huabu" style="display: flex;justify-content: space-around">
    <div class="left">
      <div class="d1">
        <img class="img1" :src="state.imgUrl" alt="" style="height:100%;width:100%">
      </div>
    </div>
    <div class="right">
        <div></div>
    </div>
  </div>
  <div v-else class="huabu2" style="display: flex;justify-content: space-around">
     <div class="left">
      <div class="d2">
        <img class="img1" :src="state.imgUrl" alt="" style="height:100%;width:100%">
      </div>
    </div>
    <div class="right">
        <div>素材：</div>
        <div v-for="(item,index) in state.imgList" :key="index" class="content">
          <img :src="item.pic" style="height:100px;width:100px;border:0" @click="showimg(index)">
        </div>

    </div>
  </div>
</template>

<script>
import { reactive } from '@vue/reactivity';
import { useRouter } from "vue-router";
export default {
  setup () {
    const router = useRouter();
    const state = reactive({
      progranName:"",
       resolutionRatio:"",
       imgUrl:"https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg",
       imgList:[{pic:"https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg"},
       {pic:"https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"},
       {pic:"https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"},
       {pic:"https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"},
       {pic:"https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"},
       {pic:"https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"},
       {pic:"https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"},
       {pic:"https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"},
       {pic:"https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"},
       {pic:"https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"},
       ]
    })
    // console.log(router.currentRoute.value.query.form1.progranName)
    state.progranName = router.currentRoute.value.query.name
     state.resolutionRatio = router.currentRoute.value.query.resolution
    console.log(state)
    const showimg = (index) => {
      state.imgUrl = state.imgList[index].pic
    }
    return {
      state,
      showimg
    }
  }
  
}
</script>
<style scoped>
.huabu {
  display: flex,  
}
.left {
  width: 84%;
  /* height: 1000px; */
  height: 1100px;
  border: 1px solid black;
}
.right {
    margin-left: 15px;
    width: 15%;
    /* height: 1000px; */
    height: 1100px;
    border: 1px solid black;
    overflow: auto;
}
.d1 {
  /* width: 1020;
  height: 1920; */
  height: 500px;
  margin: 300px 20%;
  border: 1px solid black;
}
.d2{
  height: 900px;
  margin: 100px 37%;
  border: 1px solid black;
}
.content {
  display: flex;
  float: left;
  width: 150px;
  height: 120px;
}
</style>