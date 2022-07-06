<template>

  <div v-if="state.resolution == '1920x1080(横)'" class="huabu" style="display: flex;justify-content: space-around">
    <div class="left">
     
      <div class="d1">
        <img class="img1" :src="state.imgUrl" alt="" style="height:100%;width:100%">
      </div>
      
    </div>
    <div class="right">
      <div>素材：</div>
      <div v-for="(item, index) in state.imgList" :key="index" class="content">
        <img :src="item.url" style="height:100px;width:100px;border:0" @click="showimg(index)">
      </div>
    </div>
  </div>
  <div v-else class="huabu2" style="display: flex;justify-content: space-around">
    <div class="left">
      <el-button type="primary" @click="handleCreate" >新建节目</el-button>
      <div class="d2">
        <img class="img1" :src="state.imgUrl" alt="" style="height:100%;width:100%">
      </div>
    </div>
    <div class="right">
      <div>素材：</div>
      <div v-for="(item, index) in state.imgList" :key="index" class="content">
        <img :src="item.url" style="height:100px;width:100px;border:0" @click="showimg(index)">
      </div>

    </div>
  </div>
   <el-button type="primary" @click="handleCreate" style="margin:10px 50%">新建节目</el-button>
</template>

<script>
import { reactive } from '@vue/reactivity';
import { useRouter } from "vue-router";
import { MaterialData, MaterialCreate, userEdit } from "../api/index";
import { ElMessage } from 'element-plus';
export default {
  setup() {
    const router = useRouter();
    const query = reactive({
      programName: "",
      resolution: "",
      duration: "",
      programMaterial: "",
      programState: "未使用",
      programSize: "",
      author:localStorage.getItem("ms_realName")
    });
    const state = reactive({
      programName: "",
      resolution: "",
      duration: "",
      imgUrl: "",
      imgSize:"",
      imgList: [
        //   {pic:"https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg"},
        //  {pic:"https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"},
        //  {pic:"https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"},

      ]
    })
    const getData = () => {
      MaterialData().then((res) => {
        var list = res.data

        // state.imgList = list.records;
        state.imgList = list.records;
        // console.log(state.imgList)
        for (let i = 0; i < list.records.length; i++) {
          state.imgList[i].url = '/api/file/' + state.imgList[i].url
        }
          console.log(state.imgList)
        state.imgUrl = state.imgList[0].url
      });
    };
    getData();
    // console.log(router.currentRoute.value.query.form1.progranName)
    state.programName = router.currentRoute.value.query.name
    state.resolution = router.currentRoute.value.query.resolution
    state.duration = router.currentRoute.value.query.duration

    // console.log(state.duration)
    // console.log(state.programName)
    // console.log(state.resolution)
    console.log(query.author)
    const handleCreate=()=>{
      query.duration=state.duration
      query.programMaterial=state.imgUrl.slice(10)
      query.programName=state.programName
      query.resolution=state.resolution
      query.programSize=state.imgSize
      // console.log(query.imgUrl)
      MaterialCreate(query).then((res)=>{
        console.log(res.msg)
        ElMessage.success('节目创建成功')
      })
    }
    const showimg = (index) => {
      state.imgUrl = state.imgList[index].url
      state.imgSize=state.imgList[index].size
      // console.log(state.imgSize)
    }
    return {
      state,
      query,
      showimg,
      handleCreate
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

.d2 {
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