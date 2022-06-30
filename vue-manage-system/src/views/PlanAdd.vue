<template>
  <div class="planadd">
    <div class="title">
      <h4>设置日程</h4>
    </div>
    <div class="planInfo">
      <div class="planname">
        <span>计划名称：</span>
        <el-input v-model="input" class="handle-input mr10"></el-input>
      </div>
      <div class="synchronous">
        <span>播放策略：</span> 
        <el-radio-group v-model="radio1" class="ml-4">
          <el-radio label="1" size="large">替换</el-radio>
          <el-radio label="2" size="large">插播</el-radio>
        </el-radio-group> 
      </div> 

    </div>
  </div>
</template>

<script>
import { ref } from 'vue'

export default {
  setup() {
    const input = ref('');
    const radio1 = ref('1')
    const radio2 = ref('1')
    console.log(input)
    return {
    input,
    radio1,
    radio2
  }
  }
  
  
}
</script>

<style scoped>
.title{
  margin-top: 15px;
  margin-left: 10px;
}
.planInfo{
  margin-top: 15px;
  margin-left: 45px;
}
.synchronous {
  margin-top: 15px;
}
.handle-input {
    width: 150px;
    display: inline-block;
}
.mr10 {
    margin-right: 10px;
}
</style>